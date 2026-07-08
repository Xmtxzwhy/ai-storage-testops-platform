package com;

public class FileUploadTest {
    public static void main(String[] args) {
        // 测试用例1：5MB文件 → 允许上传
        boolean result1 = checkFileSize(5 * 1024 * 1024);
        verify("用例1：5MB文件", result1, true);

        // 测试用例2：11MB文件 → 拒绝上传
        boolean result2 = checkFileSize(11 * 1024 * 1024);
        verify("用例2：11MB文件", result2, false);
    }

    // 文件大小校验逻辑（最大10MB）
    public static boolean checkFileSize(long fileSizeBytes) {
        return fileSizeBytes <= 10 * 1024 * 1024;
    }

    public static void verify(String caseName, boolean actual, boolean expected) {
        System.out.printf("%s - 实际结果: %s | 预期结果: %s | 测试结果: %s%n",
                caseName, actual, expected,
                (actual == expected ? "✅ 通过" : "❌ 失败"));
        System.out.println("--------------------------");
    }
}