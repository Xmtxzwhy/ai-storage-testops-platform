# AI 瀛樺偍鎬ц兘娴嬭瘯绠＄悊骞冲彴鎶€鏈璁?
## 1. 姒傝堪

鏈枃妗ｆ弿杩板浣曞皢鐜版湁 Spring Boot 娴嬭瘯绠＄悊绯荤粺鍗囩骇涓?AI 杈呭姪鐨勫瓨鍌ㄦ€ц兘 TestOps 骞冲彴銆?
绗竴鐗堥噸鐐瑰疄鐜帮細

- 鑷劧璇█浠诲姟涓庢姤鍛婂叆鍙?- 缁撴瀯鍖栨祴璇曚换鍔¤崏绋?- 缁撴瀯鍖栨姤鍛婅崏绋?- 瀛樺偍鏍峰搧銆佽妭鐐广€佺敤渚嬨€佷换鍔°€佺粨鏋滃拰鎶ュ憡妯″瀷
- Mock Runner 妯℃嫙鎵ц
- 鍩轰簬鏍囧噯鍖栨祴璇曠粨鏋滅殑鐙珛鎶ュ憡鐢熸垚
- 鍩虹娴嬭瘯鏁版嵁闂瓟
- Excel 瀵煎嚭

璁捐涓婃槑纭尯鍒嗏€滄祴璇曟墽琛屸€濆拰鈥滄姤鍛婄敓鎴愨€濄€傛姤鍛婂彲浠ユ潵鑷崟涓换鍔°€佸涓换鍔°€佸巻鍙叉暟鎹€佺珵鍝佹暟鎹垨鎵嬪姩瀵煎叆鏁版嵁锛屼笉寮虹粦瀹氭煇涓€娆℃墽琛屻€?
## 2. 鎬讳綋鏋舵瀯

```mermaid
flowchart LR
    User["娴嬭瘯宸ョ▼甯?]
    UI["Vue / Element UI 绠＄悊鍚庡彴"]
    Spring["Spring Boot 骞冲彴"]
    DB["MySQL"]
    Agent["FastAPI Agent 鏈嶅姟"]
    Runner["Mock Runner"]
    Report["鎶ュ憡鐢熸垚鍣?]

    User --> UI
    UI --> Spring
    Spring --> DB
    Spring --> Agent
    Agent --> Spring
    Spring --> Runner
    Runner --> DB
    Spring --> Report
    Report --> DB
    Report --> UI
```

## 3. 妯″潡鑱岃矗

### 3.1 鍓嶇绠＄悊鍚庡彴

鑱岃矗锛?
- 鑷劧璇█杈撳叆
- 娴嬭瘯浠诲姟鑽夌纭
- 鎶ュ憡鑽夌纭
- 鏍峰搧銆佽妭鐐广€佺敤渚嬬鐞?- 浠诲姟鐘舵€佸睍绀?- 鎶ュ憡棰勮鍜?Excel 涓嬭浇

### 3.2 Spring Boot 骞冲彴

鑱岃矗锛?
- 鎻愪緵鍙俊涓氬姟 API
- 鏁版嵁搴撹闂?- 娴嬭瘯浠诲姟鍒涘缓鍜岀姸鎬佹祦杞?- 鑺傜偣銆佹墜鏈鸿繛鎺ャ€丄DB 鐘舵€佹牎楠?- Mock Runner 璋冨害
- 娴嬭瘯缁撴灉鏌ヨ
- 鎶ュ憡鐢熸垚
- Excel 鏂囦欢涓嬭浇

### 3.3 FastAPI Agent 鏈嶅姟

鑱岃矗锛?
- 鎰忓浘璇嗗埆
- 瀛楁鎶藉彇
- 缂哄け瀛楁璇嗗埆
- 杈撳嚭缁撴瀯鍖?JSON
- 鐢ㄤ緥鍜岃妭鐐规帹鑽愯鍒?- 娴嬭瘯鏁版嵁鏌ヨ鎰忓浘瑙ｆ瀽
- 鍚庣画鎺ュ叆 LLM銆丷AG 鍜屽伐鍏疯皟鐢?
### 3.4 Mock Runner

鑱岃矗锛?
- 妯℃嫙鑺傜偣鎵ц
- 鐢熸垚澶氳疆鎬ц兘鏁版嵁
- 鐢熸垚 pass/warning/fail 缁撴灉
- 鐢熸垚鎵ц鏃ュ織
- 鍐欏叆鏍囧噯鍖栫粨鏋滆〃
- 妯℃嫙澶辫触鍦烘櫙

### 3.5 鎶ュ憡鐢熸垚鍣?
鑱岃矗锛?
- 鏌ヨ鎶ュ憡鏁版嵁闆?- 鏌ヨ鏍囧噯鍖栨祴璇曠粨鏋?- 璁＄畻骞冲潎鍊煎拰宸紓鐧惧垎姣?- 搴旂敤 warning/fail 闃堝€?- 鐢熸垚缃戦〉棰勮 JSON
- 浣跨敤 Apache POI 濉厖 Excel 妯℃澘
- 淇濆瓨鎶ュ憡鏂囦欢璺緞

## 4. 鏍稿績娴佺▼

### 4.1 鍒涘缓娴嬭瘯浠诲姟

```mermaid
sequenceDiagram
    participant U as 鐢ㄦ埛
    participant FE as 鍓嶇
    participant BE as Spring Boot
    participant AG as Agent 鏈嶅姟
    participant DB as MySQL
    participant MR as Mock Runner

    U->>FE: 杈撳叆鑷劧璇█娴嬭瘯闇€姹?    FE->>BE: POST /api/storage-agent/parse
    BE->>AG: 瑙ｆ瀽闇€姹?    AG-->>BE: 杩斿洖缁撴瀯鍖栬崏绋?    BE->>DB: 鏌ヨ鏍峰搧銆佽妭鐐广€佺敤渚?    BE-->>FE: 杩斿洖鑽夌銆佹帹鑽愰」銆佺己澶卞瓧娈?    U->>FE: 纭浠诲姟
    FE->>BE: 鍒涘缓/纭浠诲姟
    BE->>DB: 淇濆瓨浠诲姟
    FE->>BE: 瑙﹀彂鎵ц
    BE->>MR: 鎵ц妯℃嫙浠诲姟
    MR->>DB: 鍐欏叆缁撴灉鍜屾棩蹇?    MR-->>BE: 鎵ц瀹屾垚
    BE-->>FE: 杩斿洖鏈€鏂颁换鍔＄姸鎬?```

### 4.2 鐢熸垚鎶ュ憡

```mermaid
sequenceDiagram
    participant U as 鐢ㄦ埛
    participant FE as 鍓嶇
    participant BE as Spring Boot
    participant AG as Agent 鏈嶅姟
    participant DB as MySQL
    participant RG as 鎶ュ憡鐢熸垚鍣?
    U->>FE: 杈撳叆瀵规瘮鎶ュ憡闇€姹?    FE->>BE: POST /api/storage-agent/parse
    BE->>AG: 瑙ｆ瀽鎶ュ憡鎰忓浘
    AG-->>BE: 杩斿洖鎶ュ憡鑽夌
    BE-->>FE: 杩斿洖鏁版嵁闆嗚崏绋?    U->>FE: 纭鎴栦慨鏀规暟鎹泦
    FE->>BE: 鍒涘缓鎶ュ憡
    BE->>DB: 淇濆瓨鎶ュ憡鍜屾暟鎹泦
    FE->>BE: 鐢熸垚鎶ュ憡
    BE->>RG: 鐢熸垚棰勮鍜?Excel
    RG->>DB: 鏌ヨ鏍囧噯鍖栫粨鏋?    RG->>DB: 淇濆瓨鎶ュ憡杈撳嚭
    BE-->>FE: 杩斿洖棰勮鍜屼笅杞藉湴鍧€
```

### 4.3 鏌ヨ娴嬭瘯鎸囨爣

```mermaid
sequenceDiagram
    participant U as 鐢ㄦ埛
    participant FE as 鍓嶇
    participant BE as Spring Boot
    participant AG as Agent 鏈嶅姟
    participant DB as MySQL

    U->>FE: 璇㈤棶鎸囨爣闂
    FE->>BE: POST /api/storage-agent/parse
    BE->>AG: 璇嗗埆鏌ヨ鎰忓浘骞舵娊鍙栨潯浠?    AG-->>BE: 杩斿洖鏌ヨ鏉′欢
    BE->>DB: 鏌ヨ鏍囧噯鍖栫粨鏋滆〃
    DB-->>BE: 杩斿洖鍖归厤缁撴灉
    BE-->>FE: 杩斿洖缁撴瀯鍖栫瓟妗堝拰鍙€夊悗缁姩浣?```

## 5. 鏁版嵁妯″瀷

### 5.1 `storage_test_node`

琛ㄧず娴嬭瘯鎵ц鑺傜偣锛屼篃灏辨槸瀹為獙瀹ら噷鐨勫皬鐢佃剳銆?
鍏抽敭瀛楁锛?
- `id`
- `node_code`
- `node_name`
- `ip_address`
- `node_status`锛歚IDLE`銆乣BUSY`銆乣OFFLINE`
- `phone_status`锛歚CONNECTED`銆乣NOT_CONNECTED`銆乣ERROR`
- `adb_state`锛歚DEVICE`銆乣UNAUTHORIZED`銆乣OFFLINE`銆乣NOT_FOUND`
- `device_serial`
- `current_sample_id`
- `capabilities`
- `last_heartbeat_time`
- `last_adb_check_time`

### 5.2 `storage_sample`

琛ㄧず DUT銆佹牱鍝佹垨绔炲搧鏍峰搧銆?
鍏抽敭瀛楁锛?
- `id`
- `project_name`
- `soc`
- `particle`
- `capacity`
- `fw_version`
- `sample_code`
- `batch_no`
- `sample_type`锛歚SELF`銆乣BASELINE`銆乣COMPETITOR`
- `remark`

### 5.3 `storage_test_case`

琛ㄧず CDM銆丄S SSD銆丗IO 鐨勬爣鍑嗙敤渚嬫垨鎸囨爣銆?
鍏抽敭瀛楁锛?
- `id`
- `case_name`
- `suite`锛歚CDM`銆乣AS_SSD`銆乣FIO`
- `scene`锛歚clean`銆乣dirty`
- `metric_name`
- `unit`
- `priority`
- `command_template`
- `enabled`

### 5.4 `storage_test_task`

琛ㄧず娴嬭瘯鎵ц浠诲姟銆傝琛ㄥ彧璐熻矗鎵ц娴佺▼锛屼笉鏄姤鍛婄敓鎴愮殑蹇呰鍓嶆彁銆?
鍏抽敭瀛楁锛?
- `id`
- `task_name`
- `raw_user_input`
- `project_name`
- `target_version`
- `sample_id`
- `node_id`
- `test_suites`
- `scenes`
- `task_status`
- `created_time`
- `started_time`
- `finished_time`

浠诲姟鐘舵€侊細

- `DRAFT`锛氬緟纭
- `CONFIRMED`锛氬凡纭
- `QUEUED`锛氬緟鎵ц
- `RUNNING`锛氭墽琛屼腑
- `COMPLETED`锛氬凡瀹屾垚
- `FAILED`锛氬け璐?
### 5.5 `storage_test_result`

鏍囧噯鍖栨€ц兘娴嬭瘯缁撴灉琛ㄣ€傛姤鍛婃ā鍧楀彧璇诲彇璇ヨ〃锛屼笉鍏冲績鏁版嵁鏉ユ簮銆?
鍏抽敭瀛楁锛?
- `id`
- `source_type`锛歚MOCK_RUNNER`銆乣REAL_NODE`銆乣IMPORT`銆乣HISTORY`
- `task_id`
- `sample_id`
- `node_id`
- `suite`
- `scene`
- `metric_name`
- `round1_value`
- `round2_value`
- `round3_value`
- `average_value`
- `unit`
- `result_status`锛歚PASS`銆乣WARNING`銆乣FAIL`銆乣N_A`
- `log_summary`
- `error_reason`
- `executed_time`

### 5.6 `storage_test_report`

琛ㄧず涓€浠芥姤鍛婅崏绋挎垨宸茬敓鎴愭姤鍛娿€?
鍏抽敭瀛楁锛?
- `id`
- `report_name`
- `report_type`锛歚SINGLE`銆乣COMPARISON`
- `report_status`锛歚DRAFT`銆乣GENERATING`銆乣COMPLETED`銆乣FAILED`
- `preview_json`
- `excel_file_path`
- `summary`
- `created_time`
- `generated_time`

### 5.7 `storage_report_dataset`

琛ㄧず涓€浠芥姤鍛婁腑閫夋嫨浜嗗摢浜涙暟鎹璞°€?
鍏抽敭瀛楁锛?
- `id`
- `report_id`
- `dataset_role`锛歚TARGET`銆乣BASELINE`銆乣COMPETITOR`
- `label`
- `project_name`
- `fw_version`
- `soc`
- `particle`
- `capacity`
- `sample_id`
- `query_filters_json`

### 5.8 `storage_agent_request`

璁板綍 Agent 瑙ｆ瀽杩囩▼锛屼究浜庤皟璇曘€佸洖鏀惧拰璇勬祴銆?
鍏抽敭瀛楁锛?
- `id`
- `raw_input`
- `intent`
- `parsed_json`
- `missing_fields`
- `confidence`
- `need_confirm`
- `created_time`

## 6. Agent 璁捐

### 6.1 鏀寔鐨勬剰鍥?
- `CREATE_TEST_TASK`锛氬垱寤烘祴璇曚换鍔?- `CREATE_REPORT`锛氬垱寤烘姤鍛?- `QUERY_RESULT`锛氭煡璇㈢粨鏋?- `ANALYZE_METRIC`锛氬垎鏋愭寚鏍囪〃鐜?- `ANALYZE_FAILURE`锛氬垎鏋愬け璐ワ紝鍚庣画鏀寔
- `SCHEDULE_BATCH_TEST`锛氭壒閲忔帓鏈燂紝鍚庣画鏀寔

### 6.2 绗竴鐗堝疄鐜版柟寮?
绗竴鐗堜娇鐢細

- FastAPI
- Pydantic 妯″瀷
- 瑙勫垯瑙ｆ瀽
- 鍚庣画鍙€夋帴鍏?LLM

MVP 闃舵涓嶅己渚濊禆 LangChain 鎴?LangGraph銆?
鍘熷洜锛?
- 瀹炵幇鏇村揩
- 婕旂ず鏇寸ǔ瀹?- 鏇村鏄撹皟璇?- 涓嶈妗嗘灦澶嶆潅搴︽嫋鎱笟鍔￠棴鐜?
浣跨敤 Pydantic 绾︽潫缁撴瀯鍖栬緭鍑猴紝閬垮厤 Agent 杩斿洖涓嶅彲鎺ф牸寮忋€?
鍚庣画婕旇繘锛?
- 浣跨敤 LangGraph 绠＄悊澶氭銆佸彲涓柇銆佸彲鎭㈠鐨勫伐浣滄祦銆?- 浣跨敤 LangChain 鎴栬交閲忚嚜鐮旀绱㈠疄鐜?RAG銆?- 濡傛灉缁撴瀯鍖?LLM Agent 鑳藉姏鎴愪负閲嶇偣锛屽彲寮曞叆 PydanticAI銆?
### 6.3 杈撳嚭鍗忚绀轰緥

```json
{
  "intent": "CREATE_REPORT",
  "projectName": "Project-A",
  "targetVersion": "FW-v2",
  "baselineVersion": "FW-v1",
  "competitor": "Competitor-X",
  "particle": "Flash-X",
  "capacity": "256G",
  "testSuites": ["CDM", "AS_SSD", "FIO"],
  "scenes": ["clean", "dirty"],
  "missingFields": [],
  "needConfirm": true
}
```

### 6.4 鏌ヨ绫昏緭鍑哄崗璁ず渚?
鐢ㄦ埛杈撳叆锛?
> CDM 椤哄簭璇绘渶楂橀€熺巼鏄摢涓牱鍝侊紝鍝釜鐗堟湰涓嬬殑锛?
Agent 杈撳嚭锛?
```json
{
  "intent": "QUERY_RESULT",
  "suite": "CDM",
  "metricName": "SEQ R 1M Q8T1",
  "aggregation": "MAX",
  "rankLimit": 1,
  "dimensions": ["sample", "version", "scene"],
  "missingFields": [],
  "needConfirm": false
}
```

骞冲彴鍥炵瓟搴斿熀浜庢煡璇㈢粨鏋滅敓鎴愶紝涓嶅厑璁哥紪閫犱笉瀛樺湪鐨勬暟鎹€?
## 7. 鎶ュ憡璁捐

### 7.1 鎸囨爣鑼冨洿

CDM锛?
- `SEQ R 1M Q8T1`
- `SEQ W 1M Q8T1`
- `RND R 4K Q32T16`
- `RND W 4K Q32T16`
- `RND R 4K Q1T1`
- `RND W 4K Q1T1`

AS SSD锛?
- `SEQ R`
- `SEQ W`
- `RAN 4K R`
- `RAN 4K W`
- `RAN 4K T64 R`
- `RAN 4K T64 W`

FIO锛?
- `seq_read`
- `seq_write`
- `rand_read_4k`
- `rand_write_4k`
- `rand_read_4k_qd32`
- `rand_write_4k_qd32`

### 7.2 璁＄畻瑙勫垯

骞冲潎鍊硷細

```text
average = 闈炵┖杞鏁版嵁鐨勫钩鍧囧€?```

宸紓鐧惧垎姣旓細

```text
delta = (鐩爣鐗堟湰骞冲潎鍊?- 鍩哄噯鐗堟湰骞冲潎鍊? / 鍩哄噯鐗堟湰骞冲潎鍊?* 100%
```

濡傛灉鍩哄噯鍊肩己澶辨垨涓?0锛屾樉绀?`N/A`銆?
闃堝€硷細

- 鎬ц兘涓嬮檷澶т簬绛変簬 20%锛歚FAIL`
- 鎬ц兘涓嬮檷澶т簬绛変簬 10%锛歚WARNING`
- 鍏朵粬鎯呭喌锛歚PASS`

### 7.3 Excel 瀵煎嚭

浣跨敤 Spring Boot 涓殑 Apache POI 瀹炵幇銆?
绛栫暐锛?
- 浣跨敤绠€鍖栨ā鏉裤€?- 濉厖鍥哄畾鍖哄煙銆?- 淇濈暀浜哄伐澶囨敞鍖哄煙銆?- 瀵?warning/fail 搴旂敤鏉′欢鏍峰紡銆?- 灏嗙敓鎴愬悗鐨勬枃浠惰矾寰勪繚瀛樺埌 `storage_test_report`銆?
## 8. 寮傛浠诲姟涓庣姸鎬佺鐞?
闇€瑕佸紓姝ュ寲鐨勪换鍔★細

- Mock Runner 鎵ц
- 鎶ュ憡鐢熸垚
- Excel 瀵煎叆瑙ｆ瀽锛屽悗缁敮鎸?- 鏃ュ織鍒嗘瀽锛屽悗缁敮鎸?- AI 鎬荤粨鐢熸垚锛屽悗缁敮鎸?- 鑺傜偣蹇冭烦鍜?ADB 鎵弿锛屽悗缁敮鎸?
MVP锛?
- 浣跨敤 Spring `@Async` 鎴栧畾鏃朵换鍔°€?- 鍓嶇杞浠诲姟鎴栨姤鍛婄姸鎬併€?
鍚庣画锛?
- 寮曞叆 Redis Queue銆丷abbitMQ銆並afka 鎴栧伐浣滄祦寮曟搸銆?- 浣跨敤 WebSocket 鎴?SSE 鎺ㄩ€佸疄鏃剁姸鎬併€?- 浣跨敤 LangGraph 绠＄悊鍙腑鏂?Agent 宸ヤ綔娴併€?
## 9. 閿欒澶勭悊

蹇呴』瑕嗙洊锛?
- 鑷劧璇█杈撳叆缂哄皯瀛楁
- 鑺傜偣绂荤嚎
- 鑺傜偣杩愯涓?- 鎵嬫満鏈繛鎺?- ADB 鏈巿鏉?- ADB offline
- 鏈彂鐜拌澶?- 鏍峰搧涓嶅尮閰?- 鑺傜偣涓嶆敮鎸佺洰鏍囨祴璇曞浠?- 鏈尮閰嶅埌鐢ㄤ緥
- 鎶ュ憡鏁版嵁缂哄け
- 鍩哄噯骞冲潎鍊间负 0
- Mock 鎵ц澶辫触
- Excel 妯℃澘缂哄け
- Excel 鐢熸垚澶辫触

鎶ュ憡鏁版嵁涓嶅畬鏁存椂锛岀郴缁熶紭鍏堢敓鎴愰儴鍒嗘姤鍛婂苟缁欏嚭娓呮櫚鎻愮ず锛岃€屼笉鏄洿鎺ュけ璐ャ€?
## 10. RAG 涓庝紒涓氶泦鎴?
鍦ㄤ紒涓氱幆澧冧腑锛岀郴缁熷簲浼樺厛澧炲己鍘熸湁娴嬭瘯骞冲彴锛岃€屼笉鏄浛浠ｅ師绯荤粺銆?
鎺ㄨ崘妯″紡锛?
- RAG 璇诲彇鏂囨。鍜岃鍒欍€?- Tools 閫氳繃鍙楁帶 API 鏌ヨ鏁版嵁鍜屾墽琛屽姩浣溿€?- 楂橀闄╁姩浣滃繀椤讳汉宸ョ‘璁ゃ€?
閫傚悎杩涘叆 RAG 鐨勫唴瀹癸細

- 鏁版嵁搴撹〃缁撴瀯璇存槑
- 娴嬭瘯鐢ㄤ緥鍛藉悕瑙勫垯
- 鎶ュ憡妯℃澘璇存槑
- 娴嬭瘯娴佺▼鏂囨。
- 鍘嗗彶鎶ュ憡缁撹
- 甯歌澶辫触鍘熷洜
- 鑺傜偣鎿嶄綔鎵嬪唽
- ADB 鏁呴殰鎺掓煡鏂囨。

鍙皝瑁呯殑宸ュ叿锛?
- `search_test_cases`
- `find_available_nodes`
- `query_performance_results`
- `query_metric_ranking`
- `compare_metric_between_versions`
- `create_test_task`
- `generate_report`
- `summarize_logs`

## 11. Eval 璇勬祴璁捐

璇勬祴绫诲埆锛?
1. 鎰忓浘璇嗗埆
2. 瀛楁鎶藉彇
3. 缂哄け瀛楁璇嗗埆
4. 宸ュ叿閫夋嫨鍜岃皟鐢ㄩ『搴?5. 鎶ュ憡璁＄畻
6. 鎬荤粨鏄惁鍩轰簬鐪熷疄鏁版嵁

璇勬祴鎸囨爣锛?
- 鎰忓浘鍑嗙‘鐜?- 瀛楁鍑嗙‘鐜?- 缂哄け瀛楁鍙洖鐜?- 宸ュ叿璋冪敤鍑嗙‘鐜?- 璁＄畻鍑嗙‘鐜?- 骞昏鐜?- 楂橀闄╁姩浣滄槸鍚﹁姹備汉宸ョ‘璁?
绀轰緥璇勬祴鐢ㄤ緥锛?
- 瀛楁瀹屾暣鐨勫垱寤烘祴璇曚换鍔¤姹?- 缂哄皯鑺傜偣鐨勫垱寤烘祴璇曚换鍔¤姹?- 鍖呭惈鍩哄噯鐗堟湰鍜岀珵鍝佺殑鎶ュ憡璇锋眰
- 鏌ヨ CDM 椤哄簭璇绘渶楂橀€熺巼鐨勯棶棰?- 鏌ヨ鏌愮増鏈浉姣斾笂鐗堟湰涓嬮檷瓒呰繃 10% 鐨勬寚鏍?- FIO dirty 鏁版嵁缂哄け鐨勬姤鍛婅姹?- 4K 闅忔満鍐欐€ц兘涓嬮檷鍒嗘瀽璇锋眰
- 澶辫触浠诲姟閲嶈瘯璇锋眰

## 12. 楠岃瘉璁″垝

MVP 楠岃瘉椤癸細

- Agent 绀轰緥杈撳叆杩斿洖棰勬湡 JSON銆?- Agent 鑳借瘑鍒煡璇㈢被闂骞剁敓鎴愬彈鎺ф煡璇㈡潯浠躲€?- 鑺傜偣鍙敤鎬ф鏌ュ寘鍚墜鏈鸿繛鎺ュ拰 ADB 鐘舵€併€?- Mock Runner 鍙互鐢熸垚 CDM銆丄S SSD銆丗IO 缁撴灉銆?- 鎶ュ憡鍙互鍦ㄤ笉浼犱换鍔?ID 鐨勬儏鍐典笅鐢熸垚銆?- 鎶ュ憡鏀寔鐩爣鐗堟湰銆佸熀鍑嗙増鏈拰绔炲搧鏁版嵁闆嗐€?- 鏌ヨ绫婚棶棰樿繑鍥炵殑鏁版嵁蹇呴』鏉ヨ嚜鏍囧噯鍖栫粨鏋滆〃銆?- 宸紓璁＄畻姝ｇ‘銆?- 鍩哄噯鍊肩己澶辨垨涓?0 鏃舵樉绀?`N/A`銆?- Excel 鏂囦欢鍙互鐢熸垚銆?- warning/fail 鏍峰紡鍙互搴旂敤銆?
## 13. 闈㈣瘯椤圭洰璁叉硶

绠€鐭増鏈細

> 鎴戝熀浜庝紶缁熸祴璇曠鐞嗙郴缁燂紝鍗囩骇鍑轰竴涓?AI 杈呭姪鐨勫瓨鍌ㄦ€ц兘 TestOps 骞冲彴銆傜郴缁熸敮鎸佽嚜鐒惰瑷€鍒涘缓娴嬭瘯浠诲姟鎴栨姤鍛婅崏绋匡紝鑳藉妫€鏌ヨ妭鐐广€佹墜鏈鸿繛鎺ュ拰 ADB 鐘舵€侊紝鍖归厤鐢ㄤ緥锛屾ā鎷熷垎甯冨紡鑺傜偣鎵ц锛屽皢缁撴灉鏍囧噯鍖栧叆搴擄紝骞剁敓鎴?Excel 瀵规瘮鎶ュ憡銆侫I 鍦ㄨ繖閲屼笉鏄浛浠ｆ祴璇曞钩鍙帮紝鑰屾槸浣滀负鍙楁帶鐨勫伐浣滄祦鍔╂墜锛屾墍鏈夋墽琛屽拰鎶ュ憡閮藉熀浜庡钩鍙扮湡瀹炴暟鎹€?