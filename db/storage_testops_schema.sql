DROP TABLE IF EXISTS `storage_agent_request`;
DROP TABLE IF EXISTS `storage_report_dataset`;
DROP TABLE IF EXISTS `storage_test_report`;
DROP TABLE IF EXISTS `storage_test_result`;
DROP TABLE IF EXISTS `storage_test_task`;
DROP TABLE IF EXISTS `storage_test_case`;
DROP TABLE IF EXISTS `storage_test_node`;
DROP TABLE IF EXISTS `storage_sample`;

CREATE TABLE `storage_sample` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `project_name` varchar(100) NOT NULL,
  `soc` varchar(100) DEFAULT NULL,
  `particle` varchar(100) DEFAULT NULL,
  `capacity` varchar(50) DEFAULT NULL,
  `fw_version` varchar(100) NOT NULL,
  `sample_code` varchar(100) DEFAULT NULL,
  `batch_no` varchar(100) DEFAULT NULL,
  `sample_type` varchar(30) NOT NULL DEFAULT 'SELF',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_storage_sample_lookup` (`project_name`,`fw_version`,`particle`,`capacity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_node` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `node_code` varchar(50) NOT NULL,
  `node_name` varchar(100) DEFAULT NULL,
  `ip_address` varchar(100) DEFAULT NULL,
  `node_status` varchar(30) NOT NULL DEFAULT 'IDLE',
  `phone_status` varchar(30) NOT NULL DEFAULT 'NOT_CONNECTED',
  `adb_state` varchar(30) NOT NULL DEFAULT 'NOT_FOUND',
  `device_serial` varchar(100) DEFAULT NULL,
  `current_sample_id` bigint DEFAULT NULL,
  `capabilities` varchar(500) DEFAULT NULL,
  `last_heartbeat_time` datetime DEFAULT NULL,
  `last_adb_check_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_storage_node_code` (`node_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_case` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `case_name` varchar(200) NOT NULL,
  `suite` varchar(30) NOT NULL,
  `scene` varchar(30) NOT NULL,
  `metric_name` varchar(100) NOT NULL,
  `unit` varchar(30) NOT NULL DEFAULT 'MB/s',
  `priority` varchar(30) NOT NULL DEFAULT 'P1',
  `command_template` varchar(1000) DEFAULT NULL,
  `enabled` tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `idx_storage_case_suite_scene` (`suite`,`scene`,`enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `task_name` varchar(200) NOT NULL,
  `raw_user_input` text,
  `project_name` varchar(100) DEFAULT NULL,
  `target_version` varchar(100) DEFAULT NULL,
  `sample_id` bigint DEFAULT NULL,
  `node_id` bigint DEFAULT NULL,
  `test_suites` varchar(200) DEFAULT NULL,
  `scenes` varchar(200) DEFAULT NULL,
  `task_status` varchar(30) NOT NULL DEFAULT 'DRAFT',
  `started_time` datetime DEFAULT NULL,
  `finished_time` datetime DEFAULT NULL,
  `error_message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_storage_task_status` (`task_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_result` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `source_type` varchar(30) NOT NULL,
  `task_id` bigint DEFAULT NULL,
  `sample_id` bigint DEFAULT NULL,
  `node_id` bigint DEFAULT NULL,
  `suite` varchar(30) NOT NULL,
  `scene` varchar(30) NOT NULL,
  `metric_name` varchar(100) NOT NULL,
  `round1_value` decimal(12,3) DEFAULT NULL,
  `round2_value` decimal(12,3) DEFAULT NULL,
  `round3_value` decimal(12,3) DEFAULT NULL,
  `average_value` decimal(12,3) DEFAULT NULL,
  `unit` varchar(30) NOT NULL DEFAULT 'MB/s',
  `result_status` varchar(30) NOT NULL DEFAULT 'PASS',
  `log_summary` varchar(1000) DEFAULT NULL,
  `error_reason` varchar(1000) DEFAULT NULL,
  `executed_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_storage_result_lookup` (`sample_id`,`suite`,`scene`,`metric_name`),
  KEY `idx_storage_result_task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `report_name` varchar(200) NOT NULL,
  `report_type` varchar(30) NOT NULL,
  `report_status` varchar(30) NOT NULL DEFAULT 'DRAFT',
  `preview_json` longtext,
  `excel_file_path` varchar(500) DEFAULT NULL,
  `summary` text,
  `generated_time` datetime DEFAULT NULL,
  `error_message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_report_dataset` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `report_id` bigint NOT NULL,
  `dataset_role` varchar(30) NOT NULL,
  `label` varchar(200) DEFAULT NULL,
  `project_name` varchar(100) DEFAULT NULL,
  `fw_version` varchar(100) DEFAULT NULL,
  `soc` varchar(100) DEFAULT NULL,
  `particle` varchar(100) DEFAULT NULL,
  `capacity` varchar(50) DEFAULT NULL,
  `sample_id` bigint DEFAULT NULL,
  `query_filters_json` text,
  PRIMARY KEY (`id`),
  KEY `idx_storage_report_dataset` (`report_id`,`dataset_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_agent_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `raw_input` text NOT NULL,
  `intent` varchar(50) NOT NULL,
  `parsed_json` text,
  `missing_fields` varchar(1000) DEFAULT NULL,
  `confidence` decimal(5,2) DEFAULT NULL,
  `need_confirm` tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
