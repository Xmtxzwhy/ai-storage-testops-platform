# AI 瀛樺偍鎬ц兘 TestOps Demo 鑴氭湰

## 0. 婕旂ず鍓嶅噯澶?
鍏堝惎鍔?Agent 鏈嶅姟锛?
```powershell
cd agent-service
uvicorn app.main:app --reload --port 8090
```

鍐嶅惎鍔?Spring Boot锛?
```powershell
mvn spring-boot:run
```

榛樿鍚庣鍦板潃锛?
```text
http://localhost:8080/springbootarkc6v1u
```

婕旂ず鍓嶅缓璁厛瀵煎叆锛?
- `db/storage_testops_schema.sql`
- `db/storage_testops_seed.sql`

---

## Demo A锛氳嚜鐒惰瑷€鍒涘缓娴嬭瘯浠诲姟

### 鐜板満杈撳叆

```text
Node-3 涓婃寕浜?Project-A Flash-X 256G FW-v2锛岃窇 CDM銆丄S SSD銆丗IO锛屽満鏅槸 clean 鍜?dirty锛屽垱寤轰竴涓祴璇曚换鍔°€?```

### 瀹為檯鎺ュ彛鍜屽懡浠?
1. 璁?Agent 瑙ｆ瀽鑷劧璇█锛?
```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-agent/parse -Method Post -ContentType 'application/json' -Body '{"text":"Node-3 涓婃寕浜?Project-A Flash-X 256G FW-v2锛岃窇 CDM銆丄S SSD銆丗IO锛屽満鏅槸 clean 鍜?dirty锛屽垱寤轰竴涓祴璇曚换鍔°€?}'
```

2. 鎸夎В鏋愮粨鏋滃垱寤烘祴璇曚换鍔★細

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/create -Method Post -ContentType 'application/json' -Body '{"taskName":"Project-A FW-v2 CDM AS SSD FIO Demo","rawUserInput":"Node-3 涓婃寕浜?Project-A Flash-X 256G FW-v2锛岃窇 CDM銆丄S SSD銆丗IO锛屽満鏅槸 clean 鍜?dirty锛屽垱寤轰竴涓祴璇曚换鍔°€?,"projectName":"Project-A","targetVersion":"FW-v2","sampleId":2,"nodeId":3,"testSuites":["CDM","AS_SSD","FIO"],"scenes":["clean","dirty"]}'
```

3. 鍚姩 Mock Runner 鎵ц锛?
```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/run -Method Post
```

4. 鏌ョ湅鎵ц缁撴灉锛?
```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/results -Method Get
```

### 棰勬湡杈撳嚭

- Agent 杩斿洖 `intent = CREATE_TEST_TASK`
- 骞冲彴璇嗗埆椤圭洰銆佺増鏈€佽妭鐐广€佹祴璇曞浠跺拰鍦烘櫙
- Node-3 婊¤冻 `IDLE / CONNECTED / DEVICE`
- 浠诲姟鐘舵€佷粠 `DRAFT` 杩涘叆 `RUNNING`锛屾渶缁堝彉涓?`COMPLETED`
- `storage_test_result` 鐢熸垚 CDM銆丄S SSD銆丗IO 瀵瑰簲缁撴灉

### 鐜板満鍙鐨勮瘽

杩欓噷涓嶆槸璁?AI 鐩存帴鎵ц鍗遍櫓鍔ㄤ綔锛岃€屾槸鍏堣 Agent 鎶婅嚜鐒惰瑷€鎷嗘垚缁撴瀯鍖栦换鍔¤崏绋匡紝鍐嶇敱骞冲彴妫€鏌ヨ妭鐐广€佹墜鏈鸿繛鎺ャ€丄DB 鐘舵€佸拰鐢ㄤ緥鍖归厤鎯呭喌銆傜敤鎴风‘璁ゅ悗锛屽钩鍙版墠鎵ц浠诲姟銆傜涓€鐗堢敤 Mock Runner 妯℃嫙鐪熷疄鑺傜偣锛屽悗缁彲浠ユ浛鎹㈡垚 ADB 鑴氭湰銆丣enkins 鎴栫湡瀹炶妭鐐?Agent銆?
---

## Demo B锛氭櫤鑳芥祴璇曟暟鎹棶绛?
### 鐜板満杈撳叆

```text
CDM 椤哄簭璇绘渶楂橀€熺巼鏄摢涓牱鍝侊紝鍝釜鐗堟湰涓嬬殑锛?```

### 瀹為檯鎺ュ彛鍜屽懡浠?
```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-metrics/query -Method Post -ContentType 'application/json' -Body '{"suite":"CDM","metricName":"SEQ R 1M Q8T1","aggregation":"MAX","rankLimit":1}'
```

### 棰勬湡杈撳嚭

- Agent 鍙瘑鍒负 `QUERY_RESULT`
- 骞冲彴鏌ヨ `storage_test_result`
- 骞冲彴鍏宠仈 `storage_sample`
- 杩斿洖 `sampleCode`銆乣fwVersion`銆乣scene`銆乣metricName`銆乣averageValue`
- `answer` 鐢ㄤ腑鏂囪鏄庢渶楂樺€煎搴旂殑鏍峰搧銆佺増鏈€佸満鏅拰鏁板€?
### 鐜板満鍙鐨勮瘽

杩欎釜鑳藉姏浣撶幇鐨勬槸鈥滄祴璇曟暟鎹棶绛斺€濄€侫I 涓嶈嚜宸辩紪绛旀锛岃€屾槸鎶婇棶棰樼炕璇戞垚鍙楁帶鏌ヨ鏉′欢锛屽啀鐢卞钩鍙版煡璇㈡爣鍑嗗寲缁撴灉琛ㄣ€傝繖鏍峰彲浠ュ洖绛旀渶楂樺€笺€佹渶浣庡€笺€乀op N銆佺増鏈樊寮傜瓑闂銆?
---

## Demo C锛氱敓鎴愬姣旀姤鍛?
### 鐜板満杈撳叆

```text
鐢熸垚 Project-A FW-v2銆乂2.0.3 鍜岀珵鍝?Competitor-X 鐨?CDM銆丄S SSD銆丗IO 瀵规瘮鎶ュ憡銆?```

### 瀹為檯鎺ュ彛鍜屽懡浠?
1. 鍒涘缓鎶ュ憡锛?
```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/create -Method Post -ContentType 'application/json' -Body '{"reportName":"Project-A FW-v2 瀵规瘮鎶ュ憡","reportType":"COMPARISON","sampleIds":[2,1,3]}'
```

2. 鐢熸垚鎶ュ憡锛?
```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/1/generate -Method Post
```

3. 鏌ョ湅鎶ュ憡淇℃伅骞朵笅杞?Excel锛?
```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/1 -Method Get
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/1/download -Method Get -OutFile 'storage-report-1.xlsx'
```

### 棰勬湡杈撳嚭

- Agent 鍙瘑鍒负 `CREATE_REPORT`
- 骞冲彴鍒涘缓鎶ュ憡鏁版嵁闆嗭細`TARGET / BASELINE / COMPETITOR`
- 鐢熸垚 `preview_json`
- 瀵煎嚭 `upload/reports/storage-report-1.xlsx`
- 鍩哄噯鍊间负绌烘垨涓?0 鏃讹紝棰勮鍜?Excel 閮芥樉绀?`N/A`

### 鐜板満鍙鐨勮瘽

鎶ュ憡妯″潡鍜屼换鍔℃墽琛屾ā鍧楁槸瑙ｈ€︾殑銆傛姤鍛婂彲浠ユ潵鑷垰璺戝畬鐨勪换鍔★紝涔熷彲浠ユ潵鑷巻鍙叉暟鎹€佸鍏ユ暟鎹€佷笂鐗堟湰鏁版嵁鍜岀珵鍝佹暟鎹€傝繖鏍锋洿璐磋繎鐪熷疄浼佷笟閲屸€滃悗缁啀鎷夋姤鍛娿€佸啀鍔犵珵鍝佸姣斺€濈殑宸ヤ綔鏂瑰紡銆?
---

## 楠岃瘉鍖?
婕旂ず鍓嶅悗寤鸿鎸夎繖涓『搴忓揩閫熻繃涓€閬嶏細

1. `/storage-agent/parse` 杩斿洖姝ｇ‘ intent
2. `/storage-tasks/create` 鎴愬姛鍒涘缓浠诲姟
3. `/storage-tasks/{id}/run` 鐢熸垚鏍囧噯鍖栫粨鏋?4. `/storage-metrics/query` 杩斿洖涓枃绛旀
5. `/storage-reports/create` 鎴愬姛鍒涘缓鎶ュ憡鏁版嵁闆?6. `/storage-reports/{id}/generate` 鎴愬姛鐢熸垚棰勮鍜?Excel
7. `/storage-reports/{id}/download` 鍙互涓嬭浇 Excel

杩欐潯婕旂ず绾垮彲浠ュ畬鏁磋娓呮锛?
```text
鑷劧璇█鍏ュ彛 -> 缁撴瀯鍖栦换鍔?鏌ヨ/鎶ュ憡 -> 鏍囧噯鍖栨祴璇曠粨鏋?-> 鏁版嵁闂瓟 -> 瀵规瘮鎶ュ憡 -> Excel 瀵煎嚭
```
