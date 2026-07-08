INSERT INTO storage_sample (project_name, soc, particle, capacity, fw_version, sample_code, batch_no, sample_type, remark)
VALUES
('WM6000','MT6762','N38B','256G','V2.0.3','WM6000-N38B-256G-203','B01','BASELINE','baseline version'),
('WM6000','MT6762','N38B','256G','V2.0.4','WM6000-N38B-256G-204','B02','SELF','target version'),
('2730AB','2730AB','AHGB','256G','V2.2.5','2730AB-AHGB-256G-225','C01','COMPETITOR','competitor sample');

INSERT INTO storage_test_node (node_code, node_name, ip_address, node_status, phone_status, adb_state, device_serial, current_sample_id, capabilities, last_heartbeat_time, last_adb_check_time)
VALUES
('Node-1','Windows Perf Node 1','192.168.10.11','IDLE','CONNECTED','DEVICE','SN-NODE1',2,'ADB,WINDOWS,CDM,AS_SSD,FIO',NOW(),NOW()),
('Node-2','Linux Perf Node 2','192.168.10.12','BUSY','CONNECTED','DEVICE','SN-NODE2',1,'ADB,UBUNTU,FIO',NOW(),NOW()),
('Node-3','Hybrid Perf Node 3','192.168.10.13','IDLE','CONNECTED','DEVICE','SN-NODE3',2,'ADB,WINDOWS,UBUNTU,CDM,AS_SSD,FIO',NOW(),NOW()),
('Node-4','Offline Node 4','192.168.10.14','OFFLINE','NOT_CONNECTED','NOT_FOUND',NULL,NULL,'ADB,WINDOWS',NULL,NULL);

INSERT INTO storage_test_case (case_name, suite, scene, metric_name, unit, priority, command_template, enabled)
VALUES
('CDM clean SEQ R 1M Q8T1','CDM','clean','SEQ R 1M Q8T1','MB/s','P0','mock:cdm:clean:seq_read',1),
('CDM clean SEQ W 1M Q8T1','CDM','clean','SEQ W 1M Q8T1','MB/s','P0','mock:cdm:clean:seq_write',1),
('CDM clean RND R 4K Q32T16','CDM','clean','RND R 4K Q32T16','MB/s','P1','mock:cdm:clean:rnd_read_q32',1),
('CDM clean RND W 4K Q32T16','CDM','clean','RND W 4K Q32T16','MB/s','P1','mock:cdm:clean:rnd_write_q32',1),
('CDM dirty SEQ R 1M Q8T1','CDM','dirty','SEQ R 1M Q8T1','MB/s','P0','mock:cdm:dirty:seq_read',1),
('CDM dirty SEQ W 1M Q8T1','CDM','dirty','SEQ W 1M Q8T1','MB/s','P0','mock:cdm:dirty:seq_write',1),
('AS SSD clean SEQ R','AS_SSD','clean','SEQ R','MB/s','P0','mock:asssd:clean:seq_read',1),
('AS SSD clean SEQ W','AS_SSD','clean','SEQ W','MB/s','P0','mock:asssd:clean:seq_write',1),
('AS SSD dirty RAN 4K R','AS_SSD','dirty','RAN 4K R','MB/s','P1','mock:asssd:dirty:ran4k_read',1),
('AS SSD dirty RAN 4K W','AS_SSD','dirty','RAN 4K W','MB/s','P1','mock:asssd:dirty:ran4k_write',1),
('FIO clean seq_read','FIO','clean','seq_read','MB/s','P0','mock:fio:clean:seq_read',1),
('FIO clean seq_write','FIO','clean','seq_write','MB/s','P0','mock:fio:clean:seq_write',1),
('FIO dirty rand_read_4k','FIO','dirty','rand_read_4k','MB/s','P1','mock:fio:dirty:rand_read_4k',1),
('FIO dirty rand_write_4k','FIO','dirty','rand_write_4k','MB/s','P1','mock:fio:dirty:rand_write_4k',1);

INSERT INTO storage_test_result (source_type, task_id, sample_id, node_id, suite, scene, metric_name, round1_value, round2_value, round3_value, average_value, unit, result_status, log_summary, error_reason, executed_time)
VALUES
('HISTORY',NULL,1,1,'CDM','clean','SEQ R 1M Q8T1',308.790,300.070,315.610,308.157,'MB/s','PASS','baseline V2.0.3 CDM clean seq read',NULL,NOW()),
('HISTORY',NULL,1,1,'CDM','clean','SEQ W 1M Q8T1',294.410,293.960,291.070,293.147,'MB/s','PASS','baseline V2.0.3 CDM clean seq write',NULL,NOW()),
('HISTORY',NULL,1,1,'CDM','dirty','RND R 4K Q32T16',65.200,69.640,61.590,65.477,'MB/s','PASS','baseline V2.0.3 CDM dirty random read',NULL,NOW()),
('HISTORY',NULL,1,1,'AS_SSD','clean','SEQ R',285.570,289.070,285.490,286.710,'MB/s','PASS','baseline V2.0.3 AS SSD clean seq read',NULL,NOW()),
('HISTORY',NULL,1,1,'AS_SSD','dirty','RAN 4K W',40.980,42.260,41.390,41.543,'MB/s','PASS','baseline V2.0.3 AS SSD dirty random write',NULL,NOW()),
('HISTORY',NULL,1,1,'FIO','clean','seq_read',131.000,140.000,136.000,135.667,'MB/s','PASS','baseline V2.0.3 FIO clean seq read',NULL,NOW()),
('HISTORY',NULL,1,1,'FIO','dirty','rand_write_4k',43.500,45.200,44.100,44.267,'MB/s','PASS','baseline V2.0.3 FIO dirty rand write',NULL,NOW()),

('HISTORY',NULL,2,3,'CDM','clean','SEQ R 1M Q8T1',340.130,340.510,340.730,340.457,'MB/s','PASS','target V2.0.4 CDM clean seq read',NULL,NOW()),
('HISTORY',NULL,2,3,'CDM','clean','SEQ W 1M Q8T1',291.880,296.720,292.290,293.630,'MB/s','PASS','target V2.0.4 CDM clean seq write',NULL,NOW()),
('HISTORY',NULL,2,3,'CDM','dirty','RND R 4K Q32T16',59.490,68.230,60.780,62.833,'MB/s','WARNING','target V2.0.4 CDM dirty random read',NULL,NOW()),
('HISTORY',NULL,2,3,'AS_SSD','clean','SEQ R',312.190,313.370,311.460,312.340,'MB/s','PASS','target V2.0.4 AS SSD clean seq read',NULL,NOW()),
('HISTORY',NULL,2,3,'AS_SSD','dirty','RAN 4K W',38.010,28.540,29.950,32.167,'MB/s','WARNING','target V2.0.4 AS SSD dirty random write',NULL,NOW()),
('HISTORY',NULL,2,3,'FIO','clean','seq_read',135.000,136.000,137.000,136.000,'MB/s','PASS','target V2.0.4 FIO clean seq read',NULL,NOW()),
('HISTORY',NULL,2,3,'FIO','dirty','rand_write_4k',44.000,43.500,35.400,40.967,'MB/s','WARNING','target V2.0.4 FIO dirty rand write',NULL,NOW()),

('HISTORY',NULL,3,1,'CDM','clean','SEQ R 1M Q8T1',336.130,333.610,333.610,334.450,'MB/s','PASS','competitor 2730AB CDM clean seq read',NULL,NOW()),
('HISTORY',NULL,3,1,'CDM','clean','SEQ W 1M Q8T1',276.180,278.030,278.030,277.413,'MB/s','PASS','competitor 2730AB CDM clean seq write',NULL,NOW()),
('HISTORY',NULL,3,1,'CDM','dirty','RND R 4K Q32T16',45.400,46.350,46.350,46.033,'MB/s','FAIL','competitor 2730AB CDM dirty random read',NULL,NOW()),
('HISTORY',NULL,3,1,'AS_SSD','clean','SEQ R',308.840,307.570,306.580,307.663,'MB/s','PASS','competitor 2730AB AS SSD clean seq read',NULL,NOW()),
('HISTORY',NULL,3,1,'AS_SSD','dirty','RAN 4K W',29.050,28.980,32.550,30.193,'MB/s','WARNING','competitor 2730AB AS SSD dirty random write',NULL,NOW()),
('HISTORY',NULL,3,1,'FIO','clean','seq_read',120.000,117.000,119.000,118.667,'MB/s','PASS','competitor 2730AB FIO clean seq read',NULL,NOW()),
('HISTORY',NULL,3,1,'FIO','dirty','rand_write_4k',35.400,36.000,35.700,35.700,'MB/s','FAIL','competitor 2730AB FIO dirty rand write',NULL,NOW());
