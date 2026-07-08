package com;

public class EmailValidationTest {
    public static void main(String[] args) {
        // 测试用例1：合法邮箱
        boolean result1 = isValidEmail("user@example.com");
        verify("用例1：合法邮箱", result1, true);

        // 测试用例2：缺少@符号
        boolean result2 = isValidEmail("userexample.com");
        verify("用例2：无效邮箱", result2, false);
    }

    // 邮箱格式验证逻辑（简化版）
    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static void verify(String caseName, boolean actual, boolean expected) {
        System.out.printf("%s - 实际结果: %s | 预期结果: %s | 测试结果: %s%n",
                caseName, actual, expected,
                (actual == expected ? "✅ 通过" : "❌ 失败"));
        System.out.println("--------------------------");
    }
}