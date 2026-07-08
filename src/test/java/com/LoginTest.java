package com;

public class LoginTest {
    public static void main(String[] args) {
        // 测试用例1：正确用户名和密码
        boolean result1 = login("admin", "123456");
        verify("用例1：正确账号密码", result1, true);

        // 测试用例2：错误密码
        boolean result2 = login("admin", "wrongpass");
        verify("用例2：错误密码", result2, false);
    }

    // 模拟登录逻辑
    public static boolean login(String username, String password) {
        return "admin".equals(username) && "123456".equals(password);
    }

    // 验证结果
    public static void verify(String caseName, boolean actual, boolean expected) {
        System.out.println(caseName + " - 实际结果: " + actual);
        System.out.println(caseName + " - 预期结果: " + expected);
        System.out.println(caseName + " - 测试结果: " + (actual == expected ? "✅ 通过" : "❌ 失败"));
        System.out.println("--------------------------");
    }
}