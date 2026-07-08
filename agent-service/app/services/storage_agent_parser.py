import re
from typing import List

from app.schemas.storage_agent import StorageAgentParseResponse


class StorageAgentParser:
    def parse(self, text: str) -> StorageAgentParseResponse:
        normalized = self._normalize(text)
        intent = self._detect_intent(normalized)
        suites = self._extract_suites(normalized)
        scenes = self._extract_scenes(normalized)
        versions = self._extract_versions(normalized)
        project = self._extract_project(normalized)
        node_code = self._extract_node_code(normalized)

        result = StorageAgentParseResponse(
            intent=intent,
            projectName=project,
            targetVersion=versions[0] if versions else None,
            baselineVersion=versions[1] if len(versions) > 1 else None,
            competitor=self._extract_competitor(normalized),
            particle=self._extract_particle(normalized),
            capacity=self._extract_capacity(normalized),
            nodeCode=node_code,
            testSuites=suites,
            scenes=scenes,
            reportTypes=["single", "comparison"] if intent == "CREATE_REPORT" else [],
            needConfirm=intent != "QUERY_RESULT",
            confidence=0.92 if intent != "QUERY_RESULT" else 0.96,
        )

        if intent == "QUERY_RESULT":
            result.suite = suites[0] if suites else None
            result.metricName = self._extract_metric_name(normalized)
            result.aggregation = self._extract_aggregation(normalized)
            result.rankLimit = 1
            result.dimensions = ["sample", "version", "scene"]

        result.missingFields = self._missing_fields(result)
        if intent != "QUERY_RESULT":
            result.needConfirm = len(result.missingFields) > 0
        return result

    def _normalize(self, text: str) -> str:
        cleaned = text.replace("，", " ").replace("、", " ").replace(",", " ")
        cleaned = cleaned.replace("：", ":")
        return re.sub(r"\s+", " ", cleaned).strip()

    def _detect_intent(self, text: str) -> str:
        upper = text.upper()
        if any(keyword in text for keyword in ["最高", "最低", "哪个样品", "哪个版本"]) or "TOP" in upper:
            return "QUERY_RESULT"
        if any(keyword in text for keyword in ["报告", "对比", "竞品"]) or "REPORT" in upper:
            return "CREATE_REPORT"
        return "CREATE_TEST_TASK"

    def _extract_suites(self, text: str) -> List[str]:
        suites: List[str] = []
        upper = text.upper()
        if "CDM" in upper:
            suites.append("CDM")
        if "AS SSD" in upper or "AS_SSD" in upper:
            suites.append("AS_SSD")
        if "FIO" in upper:
            suites.append("FIO")
        return suites

    def _extract_scenes(self, text: str) -> List[str]:
        scenes: List[str] = []
        lower = text.lower()
        if "clean" in lower:
            scenes.append("clean")
        if "dirty" in lower:
            scenes.append("dirty")
        return scenes

    def _extract_versions(self, text: str) -> List[str]:
        return re.findall(r"V\d+(?:\.\d+)+", text, flags=re.IGNORECASE)

    def _extract_project(self, text: str):
        match = re.search(r"(WM\d+|2730AB)", text, flags=re.IGNORECASE)
        return match.group(1).upper() if match else None

    def _extract_node_code(self, text: str):
        match = re.search(r"\bNode[- ]?(\d+)\b|节点\s*(\d+)", text, flags=re.IGNORECASE)
        if not match:
            return None
        node_num = match.group(1) or match.group(2)
        return f"Node-{node_num}" if node_num else None

    def _extract_competitor(self, text: str):
        lower = text.lower()
        if "竞品" not in text and "competitor" not in lower:
            return None
        match = re.search(r"(?:竞品|competitor)\s*([A-Za-z0-9]+)", text, flags=re.IGNORECASE)
        return match.group(1).upper() if match else "2730AB"

    def _extract_particle(self, text: str):
        match = re.search(r"\b(N\d+B|AHGB)\b", text, flags=re.IGNORECASE)
        return match.group(1).upper() if match else None

    def _extract_capacity(self, text: str):
        match = re.search(r"\b(\d+\s*G)\b", text, flags=re.IGNORECASE)
        return match.group(1).replace(" ", "").upper() if match else None

    def _extract_metric_name(self, text: str):
        lower = text.lower()
        if any(keyword in text for keyword in ["顺序读", "顺序读取"]) or "seq read" in lower:
            return "SEQ R 1M Q8T1"
        if any(keyword in text for keyword in ["顺序写", "顺序写入"]) or "seq write" in lower:
            return "SEQ W 1M Q8T1"
        if "4k" in lower or "随机" in text:
            return "RND R 4K Q32T16"
        return None

    def _extract_aggregation(self, text: str):
        lower = text.lower()
        if "最高" in text or "最大" in text or "top" in lower:
            return "MAX"
        if "最低" in text or "最小" in text:
            return "MIN"
        return None

    def _missing_fields(self, result: StorageAgentParseResponse):
        if result.intent == "QUERY_RESULT":
            return []

        required = ["projectName", "targetVersion"]
        if result.intent == "CREATE_TEST_TASK":
            required.append("nodeCode")

        missing = []
        for field in required:
            value = getattr(result, field)
            if value in (None, "", []):
                missing.append(field)
        return missing
