package com.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.entity.EIException;
import com.service.ConfigService;
import com.utils.R;

/**
 * 上传文件映射表
 */
@RestController
@RequestMapping("file")
@SuppressWarnings({"unchecked","rawtypes"})
public class FileController {
	@Autowired
	private ConfigService configService;

	@RequestMapping("/upload")
	@IgnoreAuth
	public R upload(@RequestParam("file") MultipartFile file, String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("上传文件不能为空");
		}

		// 获取安全的文件扩展名
		String originalFilename = file.getOriginalFilename();
		String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

		// 生成纯时间戳文件名
		String fileName = new Date().getTime() + "." + fileExt;

		// 创建上传目录
		File path = new File(ResourceUtils.getURL("classpath:").getPath());
		if (!path.exists()) path = new File("");

		File upload = new File(path.getAbsolutePath(), "/upload");
		if (!upload.exists()) upload.mkdirs();

		// 保存文件
		File dest = new File(upload.getAbsolutePath() + "/" + fileName);
		file.transferTo(dest);

		// 配置文件更新（保持时间戳格式）
		if (StringUtils.isNotBlank(type) && "1".equals(type)) {
			ConfigEntity configEntity = configService.selectOne(
					new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));

			if (configEntity == null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
				configService.insert(configEntity);
			} else {
				configEntity.setValue(fileName);
				configService.updateById(configEntity);
			}
		}

		return R.ok().put("file", fileName);
	}

	/**
	 * 下载文件（保持不变）
	 */
	@IgnoreAuth // 添加这个注解允许匿名访问
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			// 安全验证可以在此处添加
			File path = new File(ResourceUtils.getURL("classpath:static").getPath());
			if (!path.exists()) {
				path = new File("");
			}
			File upload = new File(path.getAbsolutePath(), "/upload/");
			File file = new File(upload.getAbsolutePath() + "/" + fileName);

			if (file.exists()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", fileName);
				return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}