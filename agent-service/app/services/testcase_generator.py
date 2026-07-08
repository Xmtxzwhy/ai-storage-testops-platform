from app.schemas.testcase import (
    GeneratedTestCase,
    GenerateTestCasesRequest,
    GenerateTestCasesResponse,
    Priority,
    TestType,
)


class TestCaseGenerator:
    """Rule-based first version of the test case generation agent.

    The service is intentionally runnable without an LLM key. A later milestone
    can replace this generator with model-based structured output while keeping
    the same request and response schema.
    """

    def generate(self, request: GenerateTestCasesRequest) -> GenerateTestCasesResponse:
        dimensions = self._select_dimensions(request)
        cases = [self._build_case(request, dimension, index + 1) for index, dimension in enumerate(dimensions)]

        return GenerateTestCasesResponse(
            agent_name="TestCaseGenerationAgent",
            generation_strategy="requirement decomposition + risk dimension expansion + structured schema validation",
            requirement_summary=self._summarize(request.requirement),
            test_cases=cases[: request.max_cases],
            review_checklist=[
                "Confirm each case is traceable to a requirement or risk point.",
                "Confirm preconditions, steps, and expected results are executable.",
                "Add real accounts, data, environment versions, and device models before execution.",
                "Promote high-risk cases into the regression suite.",
            ],
        )

    def _select_dimensions(self, request: GenerateTestCasesRequest) -> list[TestType]:
        text = f"{request.requirement} {' '.join(request.risk_keywords)}".lower()
        dimensions = [
            TestType.FUNCTIONAL,
            TestType.NEGATIVE,
            TestType.BOUNDARY,
            TestType.PERMISSION,
            TestType.DATA_CONSISTENCY,
        ]

        if any(word in text for word in ["performance", "fio", "androbench", "iops", "throughput"]):
            dimensions.append(TestType.PERFORMANCE)
        if any(word in text for word in ["compatibility", "android", "ios", "webview", "device", "browser"]):
            dimensions.append(TestType.COMPATIBILITY)
        if any(word in text for word in ["stability", "stress", "crash", "anr", "endurance", "reliability"]):
            dimensions.append(TestType.RELIABILITY)

        return dimensions

    def _build_case(self, request: GenerateTestCasesRequest, test_type: TestType, index: int) -> GeneratedTestCase:
        base = f"{request.module_name} {self._type_name(test_type)} validation"
        common_preconditions = [
            f"The latest test build of {request.project_name} is deployed.",
            f"The test account has the {request.role or 'tester'} role.",
            "Related test data is prepared and can be rolled back.",
        ]

        builders = {
            TestType.FUNCTIONAL: self._functional_case,
            TestType.NEGATIVE: self._negative_case,
            TestType.BOUNDARY: self._boundary_case,
            TestType.PERMISSION: self._permission_case,
            TestType.COMPATIBILITY: self._compatibility_case,
            TestType.RELIABILITY: self._reliability_case,
            TestType.PERFORMANCE: self._performance_case,
            TestType.DATA_CONSISTENCY: self._data_consistency_case,
        }
        steps, expected, priority, risk = builders[test_type](request)

        return GeneratedTestCase(
            title=f"TC-{index:03d} {base}",
            test_type=test_type,
            priority=priority,
            preconditions=common_preconditions,
            steps=steps,
            expected_result=expected,
            test_data=[request.module_name, request.platform or "web"],
            risk_point=risk,
            traceability=self._summarize(request.requirement),
        )

    def _functional_case(self, request: GenerateTestCasesRequest):
        return (
            [
                f"Open the {request.module_name} entry point.",
                "Fill in all required fields according to the requirement.",
                "Submit the form and check the list and detail pages.",
            ],
            "The operation succeeds, the success message is correct, and list/detail data matches the submitted data.",
            Priority.P1,
            "A broken happy path blocks acceptance testing.",
        )

    def _negative_case(self, request: GenerateTestCasesRequest):
        return (
            [
                f"Open the {request.module_name} entry point.",
                "Submit missing, invalid, or business-forbidden data.",
                "Check frontend validation, backend response, and persisted data.",
            ],
            "Invalid input is rejected with a clear message and no dirty data or stack trace is produced.",
            Priority.P1,
            "Invalid input can cause data pollution or backend exceptions.",
        )

    def _boundary_case(self, request: GenerateTestCasesRequest):
        return (
            [
                "Prepare minimum, maximum, overly long, empty, and duplicate values.",
                f"Submit boundary data in {request.module_name}.",
                "Check save, query, display, and export behavior.",
            ],
            "Boundary values follow business rules, over-limit data is blocked, and the UI remains readable.",
            Priority.P2,
            "Boundary cases are a common source of production defects.",
        )

    def _permission_case(self, request: GenerateTestCasesRequest):
        return (
            [
                "Log in as admin, normal user, and unauthorized user.",
                f"Try create, update, delete, and read operations in {request.module_name}.",
                "Check button visibility, API authorization, and direct URL access.",
            ],
            "Permissions match role configuration and unauthorized users cannot bypass frontend or API checks.",
            Priority.P0,
            "Broken authorization is a high-risk enterprise defect.",
        )

    def _compatibility_case(self, request: GenerateTestCasesRequest):
        return (
            [
                f"Open the feature on different {request.platform or 'platform'} versions, browsers, or devices.",
                "Execute the main workflow and capture UI, API, and log behavior.",
                "Compare results across environments.",
            ],
            "The feature behaves consistently across environments without layout or device-specific failures.",
            Priority.P2,
            "Compatibility issues can affect specific customers or device batches.",
        )

    def _reliability_case(self, request: GenerateTestCasesRequest):
        return (
            [
                f"Run the core workflow of {request.module_name} 100 times or for 2 hours.",
                "Collect error logs, response time, and resource usage.",
                "Check for Crash, ANR, lag, or data loss.",
            ],
            "Long-running execution is stable with no Crash/ANR and no abnormal resource growth.",
            Priority.P1,
            "Reliability issues usually create high rework cost during pilot run or ramp-up.",
        )

    def _performance_case(self, request: GenerateTestCasesRequest):
        return (
            [
                "Prepare benchmark data and performance collection tools.",
                f"Execute key operations in {request.module_name} and record latency, throughput, or IOPS.",
                "Compare the result with threshold and historical baseline.",
            ],
            "Key metrics meet threshold requirements and abnormal results are traceable.",
            Priority.P1,
            "Performance regression affects user experience and release readiness.",
        )

    def _data_consistency_case(self, request: GenerateTestCasesRequest):
        return (
            [
                "Complete a create, update, or status transition operation.",
                "Compare list, detail, chart, and database records.",
                "Refresh the page or log in again and repeat the comparison.",
            ],
            "All data entry points are consistent, status transitions are correct, and data is not lost after refresh.",
            Priority.P1,
            "Inconsistent data affects test reports and project decisions.",
        )

    def _summarize(self, text: str) -> str:
        normalized = " ".join(text.strip().split())
        return normalized[:120] + ("..." if len(normalized) > 120 else "")

    def _type_name(self, test_type: TestType) -> str:
        names = {
            TestType.FUNCTIONAL: "happy path",
            TestType.NEGATIVE: "negative input",
            TestType.BOUNDARY: "boundary value",
            TestType.PERMISSION: "permission",
            TestType.COMPATIBILITY: "compatibility",
            TestType.RELIABILITY: "reliability",
            TestType.PERFORMANCE: "performance",
            TestType.DATA_CONSISTENCY: "data consistency",
        }
        return names[test_type]

