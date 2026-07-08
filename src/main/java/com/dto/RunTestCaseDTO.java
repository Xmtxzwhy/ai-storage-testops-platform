package com.dto;

import lombok.Data;

@Data
public class RunTestCaseDTO {
    private String testCaseName;

    // 必须有无参构造函数
    public RunTestCaseDTO() {}

    // Getter 和 Setter
    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }
}
