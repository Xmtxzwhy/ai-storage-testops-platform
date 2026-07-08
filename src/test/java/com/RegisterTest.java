package com;

public class RegisterTest {
    public static void main(String[] args) {
        // 测试用例1：注册新用户
        String result1 = register("newUser");
        verify("用例1：注册新用户", result1, "注册成功");

        // 测试用例2：重复注册
        String result2 = register("admin");
        verify("用例2：重复注册", result2, "用户名已存在");
    }

    // 模拟注册逻辑
    public static String register(String username) {
        // 模拟已存在用户
        boolean exists = "admin".equals(username);
        return exists ? "用户名已存在" : "注册成功";
    }

    // 验证结果
    public static void verify(String caseName, String actual, String expected) {
        System.out.println(caseName + " - 实际结果: " + actual);
        System.out.println(caseName + " - 预期结果: " + expected);
        System.out.println(caseName + " - 测试结果: " + (actual.equals(expected) ? "✅ 通过" : "❌ 失败"));
        System.out.println("--------------------------");
    }
}