package com;

public class PermissionTest {
    public static void main(String[] args) {
        // 测试用例1：管理员访问后台 → 允许
        boolean result1 = checkPermission("admin", "/admin/dashboard");
        verify("用例1：管理员权限", result1, true);

        // 测试用例2：普通用户访问后台 → 拒绝
        boolean result2 = checkPermission("user", "/admin/dashboard");
        verify("用例2：普通用户权限", result2, false);
    }

    // 权限校验逻辑
    public static boolean checkPermission(String role, String path) {
        return path.startsWith("/admin") ? "admin".equals(role) : true;
    }

    public static void verify(String caseName, boolean actual, boolean expected) {
        System.out.printf("%s - 实际结果: %s | 预期结果: %s | 测试结果: %s%n",
                caseName, actual, expected,
                (actual == expected ? "✅ 通过" : "❌ 失败"));
        System.out.println("--------------------------");
    }
}