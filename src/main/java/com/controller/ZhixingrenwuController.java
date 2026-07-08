package com.controller;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import com.dto.RunTestCaseDTO;
import com.entity.CeshiyongliEntity;
import com.service.CeshiyongliService;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ZhixingrenwuEntity;
import com.entity.view.ZhixingrenwuView;

import com.service.ZhixingrenwuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

import org.springframework.web.bind.annotation.PostMapping;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * 执行任务
 * 后端接口
 * @author
 * @email
 * @date 2025-03-03 22:28:04
 */

@RestController
@RequestMapping("/zhixingrenwu")
public class ZhixingrenwuController {
    @Autowired
    private ZhixingrenwuService zhixingrenwuService;

    @Autowired
    private CeshiyongliService ceshiyongliService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, ZhixingrenwuEntity zhixingrenwu,
                  HttpServletRequest request) {
        String tableName = request.getSession().getAttribute("tableName").toString();
        if (tableName.equals("ceshijingli")) {
            zhixingrenwu.setJinglizhanghao((String) request.getSession().getAttribute("username"));
        }
        if (tableName.equals("ceshigongchengshi")) {
            zhixingrenwu.setGongchengshihao((String) request.getSession().getAttribute("username"));
        }
        //设置查询条件
        EntityWrapper<ZhixingrenwuEntity> ew = new EntityWrapper<ZhixingrenwuEntity>();


        //查询结果
        PageUtils page = zhixingrenwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhixingrenwu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page, deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, ZhixingrenwuEntity zhixingrenwu,
                  HttpServletRequest request) {
        //设置查询条件
        EntityWrapper<ZhixingrenwuEntity> ew = new EntityWrapper<ZhixingrenwuEntity>();

        //查询结果
        PageUtils page = zhixingrenwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhixingrenwu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page, deSens);
        return R.ok().put("data", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R list(ZhixingrenwuEntity zhixingrenwu) {
        EntityWrapper<ZhixingrenwuEntity> ew = new EntityWrapper<ZhixingrenwuEntity>();
        ew.allEq(MPUtil.allEQMapPre(zhixingrenwu, "zhixingrenwu"));
        return R.ok().put("data", zhixingrenwuService.selectListView(ew));
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhixingrenwuEntity zhixingrenwu) {
        EntityWrapper<ZhixingrenwuEntity> ew = new EntityWrapper<ZhixingrenwuEntity>();
        ew.allEq(MPUtil.allEQMapPre(zhixingrenwu, "zhixingrenwu"));
        ZhixingrenwuView zhixingrenwuView = zhixingrenwuService.selectView(ew);
        return R.ok("查询执行任务成功").put("data", zhixingrenwuView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        ZhixingrenwuEntity zhixingrenwu = zhixingrenwuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhixingrenwu, deSens);
        return R.ok().put("data", zhixingrenwu);
    }

    /**
     * 前台详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        ZhixingrenwuEntity zhixingrenwu = zhixingrenwuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhixingrenwu, deSens);
        return R.ok().put("data", zhixingrenwu);
    }


    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhixingrenwuEntity zhixingrenwu, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(zhixingrenwu);
        zhixingrenwuService.insert(zhixingrenwu);
        return R.ok().put("data", zhixingrenwu.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhixingrenwuEntity zhixingrenwu, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(zhixingrenwu);
        zhixingrenwuService.insert(zhixingrenwu);
        return R.ok().put("data", zhixingrenwu.getId());
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhixingrenwuEntity zhixingrenwu, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(zhixingrenwu);
        //全部更新
        zhixingrenwuService.updateById(zhixingrenwu);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        zhixingrenwuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 执行测试用例 (动态生成BAT脚本)
     */
    @IgnoreAuth
    @PostMapping("/runTestCase")
    public R runTestCase(@RequestBody RunTestCaseDTO dto){
        // 1. 查询测试用例
        EntityWrapper<CeshiyongliEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("yonglimingcheng", dto.getTestCaseName());
        CeshiyongliEntity ceshiyongli = ceshiyongliService.selectOne(wrapper);
        if (ceshiyongli == null) {
            return R.error("用例不存在");
        }


        // 2. 获取上传目录绝对路径
        String projectRoot = System.getProperty("user.dir");
        System.out.println("[DEBUG] 项目根目录: " + projectRoot);
        String uploadPath = projectRoot + File.separator + "upload";
        System.out.println("[DEBUG] 上传目录绝对路径: " + uploadPath);

        // 获取存储的文件名
        String storedFileName = ceshiyongli.getYongliwenjian();

        // 动态去除 upload/ 前缀
        if (storedFileName.startsWith("upload/")) {
            storedFileName = storedFileName.substring(7); // 去除 "upload/"（7个字符）
        }

        // 构建 Java 文件路径
        File javaFile = new File(uploadPath, storedFileName);
        System.out.println("[DEBUG] storedfilename路径: " + storedFileName);
        System.out.println("[DEBUG] Java文件路径: " + javaFile.getAbsolutePath());

        if (!javaFile.exists()) {
            System.out.println("[ERROR] Java文件不存在: " + javaFile.getAbsolutePath());
            return R.error("Java文件不存在: " + javaFile.getAbsolutePath());
        }

        // 4. 提取Java文件中的类名
        String className = dto.getTestCaseName();
        if (className == null || className.isEmpty()) {
            System.out.println("[ERROR] 无法从文件中提取类名");
            return R.error("无法从Java文件中提取类名");
        }
        System.out.println("[DEBUG] 提取的类名: " + className);

        // 5. 创建临时工作目录
        File tempDir = new File(uploadPath, "temp_" + System.currentTimeMillis());
        if (!tempDir.mkdirs()) {
            System.out.println("[ERROR] 无法创建临时目录: " + tempDir.getAbsolutePath());
            return R.error("无法创建临时工作目录");
        }

        // 6. 复制并重命名Java文件到临时目录
        String correctFileName = className + ".java";
        File tempJavaFile = new File(tempDir, correctFileName);
        try {
            Files.copy(javaFile.toPath(), tempJavaFile.toPath());
            System.out.println("[DEBUG] 文件已复制并重命名: " + tempJavaFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("[ERROR] 复制文件失败: " + e.getMessage());
            return R.error("复制文件失败: " + e.getMessage());
        }

        // 7. 创建动态BAT脚本（使用正确的文件名和类名）
        File batFile = new File(tempDir, "run.bat");
        System.out.println("[DEBUG] BAT文件路径: " + batFile.getAbsolutePath());

        try (FileWriter batWriter = new FileWriter(batFile)) {
            batWriter.write("@echo off\r\n");
            batWriter.write("chcp 65001 > nul\r\n");
            batWriter.write("echo === 当前目录 ===\r\n");
            batWriter.write("cd\r\n");
            batWriter.write("dir /b\r\n");  // 显示目录内容
            batWriter.write("echo.\r\n");
            batWriter.write("echo === 编译文件 ===\r\n");
            // 关键修复：使用重命名后的文件名
            batWriter.write("javac -encoding UTF-8 \"" + correctFileName + "\"\r\n");
            batWriter.write("if %errorlevel% neq 0 exit /b %errorlevel%\r\n");
            batWriter.write("echo.\r\n");
            batWriter.write("echo === 类文件列表 ===\r\n");
            batWriter.write("dir /b *.class\r\n");  // 显示生成的类文件
            batWriter.write("echo.\r\n");
            batWriter.write("echo === 执行程序 ===\r\n");
            // 关键修复：使用提取的类名
            batWriter.write("java " + className + "\r\n");
            batWriter.write("if %errorlevel% neq 0 exit /b %errorlevel%\r\n");
            batWriter.write("echo.\r\n");
            batWriter.write("echo Execution completed.\r\n");
            batWriter.write("exit /b 0\r\n");
        } catch (IOException e) {
            System.out.println("[ERROR] 创建BAT文件失败: " + e.getMessage());
            return R.error("创建BAT文件失败: " + e.getMessage());
        }

        // 8. 执行BAT脚本
        Process process = null;
        StringBuilder output = new StringBuilder();
        int exitCode = -1;

        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "run.bat");
            pb.directory(tempDir);
            pb.redirectErrorStream(true);

            System.out.println("[DEBUG] 执行命令: cmd.exe /c run.bat");
            System.out.println("[DEBUG] 工作目录: " + tempDir.getAbsolutePath());

            process = pb.start();

            // 读取输出
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            exitCode = process.waitFor();
            System.out.println("[DEBUG] 进程退出码: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("[ERROR] 执行脚本失败: " + e.getMessage());
            return R.error("执行脚本失败: " + e.getMessage());
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        // 9. 生成结果文件
        String resultFileName = "TestResult_" + System.currentTimeMillis() + ".txt";
        File resultFile = new File(uploadPath, resultFileName);

        try (FileWriter resultWriter = new FileWriter(resultFile)) {
            resultWriter.write("=== 临时工作目录 ===\n");
            resultWriter.write(tempDir.getAbsolutePath() + "\n\n");
            resultWriter.write("=== BAT脚本内容 ===\n");

            // 读取BAT文件内容
            try {
                byte[] batBytes = Files.readAllBytes(batFile.toPath());
                String batContent = new String(batBytes, StandardCharsets.UTF_8);
                resultWriter.write(batContent + "\n\n");
            } catch (IOException e) {
                resultWriter.write("读取BAT文件失败: " + e.getMessage() + "\n\n");
            }

            resultWriter.write("=== 执行输出 ===\n");
            resultWriter.write(output.toString() + "\n");
            resultWriter.write("=== 退出代码 ===\n");
            resultWriter.write("Exit Code: " + exitCode + "\n");
        } catch (IOException e) {
            System.out.println("[ERROR] 创建结果文件失败: " + e.getMessage());
            return R.error("创建结果文件失败: " + e.getMessage());
        }

        // 10. 清理资源
        try {
            FileUtils.deleteDirectory(tempDir);
            System.out.println("[DEBUG] 已清理临时目录: " + tempDir.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("[WARN] 清理临时目录失败: " + e.getMessage());
        }

        // 11. 返回结果文件路径
        return R.ok().put("fileUrl", resultFileName);
    }
}


