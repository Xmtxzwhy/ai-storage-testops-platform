<template>
  <div class="storage-agent-page">
    <div class="page-head">
      <div>
        <div class="eyebrow">AI Storage TestOps</div>
        <h2>AI测管演示台</h2>
        <p>用自然语言生成测试任务，模拟节点执行，汇总结果并生成Excel报告。</p>
      </div>
    </div>

    <el-card shadow="never" class="panel chat-panel">
      <div slot="header" class="panel-title">
        <span>对话式Agent</span>
        <el-tag size="small" type="warning">LLM-ready / Fallback可演示</el-tag>
      </div>
      <div class="chat-examples">
        <el-button v-for="item in chatExamples" :key="item" size="mini" @click="chatInput = item">{{ item }}</el-button>
      </div>
      <div class="chat-window">
        <div v-for="(message, index) in chatMessages" :key="index" :class="['chat-message', message.role]">
          <div class="bubble">
            <div class="message-role">{{ message.role === 'user' ? '你' : 'Agent' }}</div>
            <div class="message-text">{{ message.content }}</div>
            <div v-if="message.toolCalls && message.toolCalls.length" class="tool-trace">
              <div v-for="(tool, toolIndex) in message.toolCalls" :key="toolIndex" class="tool-item">
                <el-tag size="mini" :type="tool.status === 'success' ? 'success' : 'info'">{{ tool.status }}</el-tag>
                <span>{{ tool.tool }}</span>
                <em>{{ tool.summary }}</em>
              </div>
            </div>
            <div v-if="message.actions && message.actions.length" class="action-row">
              <el-button
                v-for="action in message.actions"
                :key="action.actionId"
                size="mini"
                type="warning"
                @click="confirmChatAction(action)"
              >
                {{ action.label }}
              </el-button>
            </div>
          </div>
        </div>
      </div>
      <div class="chat-input-row">
        <el-input
          v-model="chatInput"
          placeholder="和Agent对话，例如：CDM 顺序读最高的是哪个样品哪个版本？"
          @keyup.enter.native="sendChatMessage(null)"
        />
        <el-button type="primary" :loading="loading.chat" @click="sendChatMessage(null)">
          发送
        </el-button>
      </div>
    </el-card>

    <el-row :gutter="16">
      <el-col :lg="10" :md="24">
        <el-card shadow="never" class="panel">
          <div slot="header" class="panel-title">
            <span>Agent输入</span>
            <el-button type="text" @click="fillReportExample">切换报告示例</el-button>
          </div>
          <el-input
            v-model="agentText"
            type="textarea"
            :rows="5"
            placeholder="请输入测试需求，例如：Node-3 上挂了 Project-A Flash-X 256G FW-v2，跑 CDM clean"
          />
          <div class="button-row">
            <el-button type="primary" :loading="loading.parse" @click="parseAgentText">
              解析需求
            </el-button>
            <el-button @click="resetDemo">重置</el-button>
          </div>

          <el-descriptions v-if="parsed" title="解析结果" :column="2" border size="small">
            <el-descriptions-item label="意图">{{ parsed.intent || '-' }}</el-descriptions-item>
            <el-descriptions-item label="置信度">{{ percent(parsed.confidence) }}</el-descriptions-item>
            <el-descriptions-item label="项目">{{ parsed.projectName || '-' }}</el-descriptions-item>
            <el-descriptions-item label="目标版本">{{ parsed.targetVersion || '-' }}</el-descriptions-item>
            <el-descriptions-item label="颗粒">{{ parsed.particle || '-' }}</el-descriptions-item>
            <el-descriptions-item label="容量">{{ parsed.capacity || '-' }}</el-descriptions-item>
            <el-descriptions-item label="节点">{{ parsed.nodeCode || '-' }}</el-descriptions-item>
            <el-descriptions-item label="套件">{{ listText(parsed.testSuites) || parsed.suite || '-' }}</el-descriptions-item>
            <el-descriptions-item label="场景">{{ listText(parsed.scenes) || '-' }}</el-descriptions-item>
            <el-descriptions-item label="需确认">{{ parsed.needConfirm ? '是' : '否' }}</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>

      <el-col :lg="14" :md="24">
        <el-card shadow="never" class="panel">
          <div slot="header" class="panel-title">
            <span>测试任务</span>
            <el-tag v-if="task.id" size="small">Task #{{ task.id }}</el-tag>
          </div>
          <el-form :model="taskForm" label-width="90px" size="small" class="task-form">
            <el-row :gutter="12">
              <el-col :span="12">
                <el-form-item label="任务名">
                  <el-input v-model="taskForm.taskName" />
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="样品ID">
                  <el-input-number v-model="taskForm.sampleId" :min="1" :controls="false" />
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="节点ID">
                  <el-input-number v-model="taskForm.nodeId" :min="1" :controls="false" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="项目">
                  <el-input v-model="taskForm.projectName" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="版本">
                  <el-input v-model="taskForm.targetVersion" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="用例套件">
                  <el-select v-model="taskForm.testSuites" multiple>
                    <el-option label="CDM" value="CDM" />
                    <el-option label="AS SSD benchmark" value="AS SSD benchmark" />
                    <el-option label="FIO" value="FIO" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="测试场景">
                  <el-select v-model="taskForm.scenes" multiple>
                    <el-option label="clean" value="clean" />
                    <el-option label="dirty" value="dirty" />
                    <el-option label="burn_after" value="burn_after" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div class="button-row">
            <el-button type="primary" :loading="loading.createTask" @click="createTask">
              创建任务
            </el-button>
            <el-button type="success" :disabled="!task.id" :loading="loading.runTask" @click="runTask">
              模拟执行
            </el-button>
            <el-button :disabled="!task.id" :loading="loading.results" @click="loadResults">
              拉取结果
            </el-button>
          </div>

          <el-alert
            v-if="task.id"
            :title="taskStatusText"
            type="info"
            :closable="false"
            show-icon
          />
        </el-card>
      </el-col>
    </el-row>

    <el-card shadow="never" class="panel">
      <div slot="header" class="panel-title">
        <span>测试结果</span>
        <el-tag size="small">{{ results.length }} 条</el-tag>
      </div>
      <el-table :data="results" border size="small" height="260">
        <el-table-column prop="suite" label="套件" width="140" />
        <el-table-column prop="scene" label="场景" width="120" />
        <el-table-column prop="metricName" label="指标" min-width="180" />
        <el-table-column prop="round1Value" label="第1轮" width="90" />
        <el-table-column prop="round2Value" label="第2轮" width="90" />
        <el-table-column prop="round3Value" label="第3轮" width="90" />
        <el-table-column prop="averageValue" label="平均值" width="100" />
        <el-table-column prop="unit" label="单位" width="80" />
        <el-table-column prop="resultStatus" label="状态" width="90" />
        <el-table-column prop="logSummary" label="日志摘要" min-width="220" />
      </el-table>
    </el-card>

    <el-row :gutter="16">
      <el-col :lg="14" :md="24">
        <el-card shadow="never" class="panel">
          <div slot="header" class="panel-title">
            <span>报告生成</span>
            <el-tag v-if="report.id" size="small">Report #{{ report.id }}</el-tag>
          </div>
          <el-form :model="reportForm" label-width="90px" size="small">
            <el-row :gutter="12">
              <el-col :span="12">
                <el-form-item label="报告名">
                  <el-input v-model="reportForm.reportName" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="样品ID">
                  <el-select v-model="reportForm.sampleIds" multiple>
                    <el-option v-for="id in sampleOptions" :key="id" :label="'样品ID ' + id" :value="id" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div class="button-row">
            <el-button type="primary" :loading="loading.createReport" @click="createReport">
              创建报告
            </el-button>
            <el-button type="success" :disabled="!report.id" :loading="loading.generateReport" @click="generateReport">
              生成报告
            </el-button>
            <el-button :disabled="!report.id || !report.excelFilePath" :loading="loading.downloadReport" @click="downloadReport">
              下载Excel
            </el-button>
          </div>
          <el-alert v-if="report.summary" :title="report.summary" type="success" :closable="false" show-icon />

          <el-table :data="previewRows" border size="small" height="260" class="preview-table">
            <el-table-column prop="suite" label="套件" width="120" />
            <el-table-column prop="scene" label="场景" width="100" />
            <el-table-column prop="metricName" label="指标" min-width="160" />
            <el-table-column prop="targetAverage" label="目标均值" width="110" />
            <el-table-column prop="baselineAverage" label="基准均值" width="110" />
            <el-table-column prop="deltaDisplay" label="差异" width="90" />
            <el-table-column prop="status" label="结论" width="90" />
          </el-table>
        </el-card>
      </el-col>

      <el-col :lg="10" :md="24">
        <el-card shadow="never" class="panel">
          <div slot="header" class="panel-title">
            <span>指标问答</span>
            <el-button type="text" @click="fillMetricQuestion">填入示例</el-button>
          </div>
          <el-form :model="metricForm" label-width="90px" size="small">
            <el-form-item label="套件">
              <el-input v-model="metricForm.suite" />
            </el-form-item>
            <el-form-item label="指标">
              <el-input v-model="metricForm.metricName" />
            </el-form-item>
            <el-form-item label="聚合">
              <el-radio-group v-model="metricForm.aggregation">
                <el-radio-button label="MAX">最高</el-radio-button>
                <el-radio-button label="MIN">最低</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <div class="button-row">
            <el-button type="primary" :loading="loading.metric" @click="queryMetric">
              查询
            </el-button>
          </div>
          <el-empty v-if="metricAnswers.length === 0" description="暂无查询结果" />
          <div v-for="(item, index) in metricAnswers" :key="index" class="answer-box">
            <div class="answer-title">{{ item.answer || '查询结果' }}</div>
            <div class="answer-meta">
              {{ item.projectName || '-' }} / {{ item.fwVersion || '-' }} / {{ item.scene || '-' }} / {{ item.averageValue }} {{ item.unit || '' }}
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'StorageAgentDemo',
  data() {
    return {
      agentText: 'Node-3 上挂了 Project-A Flash-X 256G FW-v2，跑 CDM clean',
      parsed: null,
      task: {},
      results: [],
      report: {},
      reportDetail: null,
      metricAnswers: [],
      sessionId: `demo-${Date.now()}`,
      chatInput: '你能做什么？',
      chatMessages: [
        {
          role: 'assistant',
          content: '你好，我是存储测试平台Agent。你可以问我测试任务、性能指标、报告生成，也可以让我说明能力范围。'
        }
      ],
      chatExamples: [
        '你能做什么？',
        'CDM 顺序读最高的是哪个样品哪个版本？',
        'Node-3 跑 Project-A FW-v2 的 CDM clean',
        '帮我订一张去上海的机票',
        '现在几点？'
      ],
      sampleOptions: [1, 2, 3],
      taskForm: {
        taskName: 'AI-CDM-clean-FW-v2',
        rawUserInput: '',
        projectName: 'Project-A',
        targetVersion: 'FW-v2',
        sampleId: 1,
        nodeId: 3,
        testSuites: ['CDM'],
        scenes: ['clean']
      },
      reportForm: {
        reportName: 'Project-A FW-v2 vs FW-v1 性能对比报告',
        reportType: 'COMPARISON',
        sampleIds: [1, 2, 3]
      },
      metricForm: {
        suite: 'CDM',
        metricName: 'SEQ R 1M Q8T1',
        aggregation: 'MAX',
        rankLimit: 1
      },
      loading: {
        parse: false,
        createTask: false,
        runTask: false,
        results: false,
        createReport: false,
        generateReport: false,
        downloadReport: false,
        chat: false,
        metric: false
      }
    }
  },
  computed: {
    taskStatusText() {
      return `当前任务：${this.task.taskName || this.taskForm.taskName}，状态：${this.task.taskStatus || this.task.status || 'CREATED'}`
    },
    previewRows() {
      if (!this.reportDetail || !this.reportDetail.preview || !this.reportDetail.preview.rows) {
        return []
      }
      return this.reportDetail.preview.rows
    }
  },
  methods: {
    async sendChatMessage(confirmActionId) {
      const safeConfirmActionId = typeof confirmActionId === 'string' ? confirmActionId : null
      const messageText = this.chatInput.trim()
      if (!messageText && !safeConfirmActionId) {
        this.$message.warning('请输入要发送给Agent的内容')
        return
      }
      if (!safeConfirmActionId) {
        this.chatMessages.push({ role: 'user', content: messageText })
      }
      this.loading.chat = true
      try {
        const data = await this.request('post', 'storage-agent/chat', {
          sessionId: this.sessionId,
          message: safeConfirmActionId ? `确认执行 ${safeConfirmActionId}` : messageText,
          confirmActionId: safeConfirmActionId
        })
        this.chatMessages.push({
          role: 'assistant',
          content: data.reply,
          toolCalls: data.toolCalls || [],
          actions: data.actions || []
        })
        this.chatInput = ''
      } catch (error) {
        this.showError(error, 'Agent对话失败，请确认agent-service已启动')
      } finally {
        this.loading.chat = false
      }
    },
    confirmChatAction(action) {
      this.chatMessages.push({
        role: 'user',
        content: `确认：${action.label}`
      })
      this.chatMessages.push({
        role: 'assistant',
        content: '当前版本已收到确认，但高风险动作仍保留为人工按钮执行。请在下方工作流区域继续创建任务或生成报告。',
        toolCalls: [
          {
            tool: action.type,
            status: 'confirmed',
            summary: action.actionId
          }
        ]
      })
    },
    async parseAgentText() {
      if (!this.agentText.trim()) {
        this.$message.warning('请先输入自然语言测试需求')
        return
      }
      this.loading.parse = true
      try {
        const data = await this.request('post', 'storage-agent/parse', { text: this.agentText })
        this.parsed = data || {}
        this.applyParsedToTaskForm()
        this.$message.success('Agent解析完成')
      } catch (error) {
        this.showError(error, 'Agent服务不可用，请先启动 agent-service')
      } finally {
        this.loading.parse = false
      }
    },
    applyParsedToTaskForm() {
      const parsed = this.parsed || {}
      const suiteList = parsed.testSuites && parsed.testSuites.length ? parsed.testSuites : (parsed.suite ? [parsed.suite] : ['CDM'])
      const sceneList = parsed.scenes && parsed.scenes.length ? parsed.scenes : ['clean']
      this.taskForm.rawUserInput = this.agentText
      this.taskForm.projectName = parsed.projectName || this.taskForm.projectName
      this.taskForm.targetVersion = parsed.targetVersion || this.taskForm.targetVersion
      this.taskForm.testSuites = suiteList
      this.taskForm.scenes = sceneList
      this.taskForm.nodeId = this.resolveNodeId(parsed.nodeCode)
      this.taskForm.sampleId = this.resolveSampleId(parsed)
      this.taskForm.taskName = `${this.taskForm.projectName}-${this.taskForm.targetVersion}-${suiteList.join('+')}-${sceneList.join('+')}`
    },
    async createTask() {
      if (!this.taskForm.sampleId || !this.taskForm.nodeId) {
        this.$message.warning('请确认样品ID和节点ID')
        return
      }
      this.loading.createTask = true
      try {
        this.task = await this.request('post', 'storage-tasks/create', this.taskForm)
        this.$message.success('测试任务已创建')
      } catch (error) {
        this.showError(error, '创建任务失败')
      } finally {
        this.loading.createTask = false
      }
    },
    async runTask() {
      if (!this.task.id) {
        this.$message.warning('请先创建任务')
        return
      }
      this.loading.runTask = true
      try {
        const data = await this.request('post', `storage-tasks/${this.task.id}/run`)
        this.task = Object.assign({}, this.task, data)
        await this.loadResults()
        this.$message.success('模拟执行完成')
      } catch (error) {
        this.showError(error, '模拟执行失败')
      } finally {
        this.loading.runTask = false
      }
    },
    async loadResults() {
      if (!this.task.id) {
        this.$message.warning('请先创建任务')
        return
      }
      this.loading.results = true
      try {
        this.results = await this.request('get', `storage-tasks/${this.task.id}/results`)
      } catch (error) {
        this.showError(error, '拉取结果失败')
      } finally {
        this.loading.results = false
      }
    },
    async createReport() {
      if (!this.reportForm.sampleIds.length) {
        this.$message.warning('请至少选择一个样品ID')
        return
      }
      this.loading.createReport = true
      try {
        this.report = await this.request('post', 'storage-reports/create', this.reportForm)
        this.reportDetail = null
        this.$message.success('报告草稿已创建')
      } catch (error) {
        this.showError(error, '创建报告失败')
      } finally {
        this.loading.createReport = false
      }
    },
    async generateReport() {
      if (!this.report.id) {
        this.$message.warning('请先创建报告')
        return
      }
      this.loading.generateReport = true
      try {
        this.report = await this.request('post', `storage-reports/${this.report.id}/generate`)
        this.reportDetail = await this.request('get', `storage-reports/${this.report.id}`)
        this.$message.success('报告已生成')
      } catch (error) {
        this.showError(error, '生成报告失败')
      } finally {
        this.loading.generateReport = false
      }
    },
    async downloadReport() {
      if (!this.report.id) {
        this.$message.warning('请先生成报告')
        return
      }
      this.loading.downloadReport = true
      try {
        const response = await this.$http({
          method: 'get',
          url: `storage-reports/${this.report.id}/download`,
          responseType: 'blob'
        })
        const fileName = this.resolveDownloadFileName(response.headers['content-disposition'])
        const objectUrl = window.URL.createObjectURL(new Blob([response.data], {
          type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
        }))
        const link = document.createElement('a')
        link.href = objectUrl
        link.download = fileName
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
        window.URL.revokeObjectURL(objectUrl)
        this.$message.success('Excel报告已开始下载')
      } catch (error) {
        this.showError(error, '下载报告失败，请确认登录状态')
      } finally {
        this.loading.downloadReport = false
      }
    },
    async queryMetric() {
      if (!this.metricForm.suite || !this.metricForm.metricName) {
        this.$message.warning('请填写套件和指标')
        return
      }
      this.loading.metric = true
      try {
        this.metricAnswers = await this.request('post', 'storage-metrics/query', this.metricForm)
      } catch (error) {
        this.showError(error, '指标查询失败')
      } finally {
        this.loading.metric = false
      }
    },
    async request(method, url, data) {
      const response = await this.$http({ method, url, data })
      if (response.data && response.data.code !== 0) {
        throw new Error(response.data.msg || '请求失败')
      }
      return response.data ? response.data.data : null
    },
    resolveNodeId(nodeCode) {
      const matched = String(nodeCode || '').match(/(\d+)/)
      return matched ? Number(matched[1]) : this.taskForm.nodeId
    },
    resolveSampleId(parsed) {
      if (!parsed) {
        return this.taskForm.sampleId
      }
      if (parsed.projectName === 'Project-A' && parsed.particle === 'Flash-X' && parsed.targetVersion === 'FW-v2') {
        return 1
      }
      if (parsed.projectName === 'Project-A' && parsed.targetVersion === 'FW-v1') {
        return 2
      }
      if (parsed.competitor || parsed.particle === 'Flash-Y') {
        return 3
      }
      return this.taskForm.sampleId
    },
    percent(value) {
      if (value === null || value === undefined || value === '') {
        return '-'
      }
      return `${Math.round(Number(value) * 100)}%`
    },
    listText(value) {
      return Array.isArray(value) ? value.join(', ') : value
    },
    fillReportExample() {
      this.agentText = '生成 Project-A FW-v2 和 FW-v1，再加竞品 Competitor-X Flash-Y 的 CDM 对比报告'
    },
    fillMetricQuestion() {
      this.metricForm = {
        suite: 'CDM',
        metricName: 'SEQ R 1M Q8T1',
        aggregation: 'MAX',
        rankLimit: 1
      }
    },
    resolveDownloadFileName(contentDisposition) {
      if (!contentDisposition) {
        return `storage-report-${this.report.id}.xlsx`
      }
      const matched = contentDisposition.match(/filename="?([^"]+)"?/)
      if (!matched) {
        return `storage-report-${this.report.id}.xlsx`
      }
      try {
        return decodeURIComponent(matched[1])
      } catch (error) {
        return matched[1]
      }
    },
    resetDemo() {
      this.parsed = null
      this.task = {}
      this.results = []
      this.report = {}
      this.reportDetail = null
      this.metricAnswers = []
      this.agentText = 'Node-3 上挂了 Project-A Flash-X 256G FW-v2，跑 CDM clean'
    },
    showError(error, fallback) {
      const message = error && error.message ? error.message : fallback
      this.$message.error(message || fallback)
    }
  }
}
</script>

<style lang="scss" scoped>
.storage-agent-page {
  padding: 18px;
  background: #f5f7fb;
  min-height: 100%;
}

.page-head {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 16px;
  padding: 18px 20px;
  background: #fff;
  border: 1px solid #e6eaf2;
}

.eyebrow {
  font-size: 12px;
  color: #409eff;
  font-weight: 700;
  margin-bottom: 6px;
}

.page-head h2 {
  margin: 0 0 8px;
  font-size: 22px;
  color: #1f2d3d;
}

.page-head p {
  margin: 0;
  color: #667085;
}

.panel {
  margin-bottom: 16px;
  border-radius: 6px;
}

.panel-title {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-weight: 700;
}

.button-row {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin: 12px 0;
}

.chat-panel {
  border-color: #dbeafe;
}

.chat-examples {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-bottom: 12px;
}

.chat-window {
  height: 300px;
  overflow-y: auto;
  padding: 12px;
  border: 1px solid #e6eaf2;
  background: #f8fbff;
  border-radius: 6px;
}

.chat-message {
  display: flex;
  margin-bottom: 12px;
}

.chat-message.user {
  justify-content: flex-end;
}

.chat-message.assistant {
  justify-content: flex-start;
}

.bubble {
  max-width: 78%;
  padding: 10px 12px;
  border-radius: 6px;
  background: #fff;
  border: 1px solid #e6eaf2;
}

.chat-message.user .bubble {
  background: #ecf5ff;
  border-color: #b3d8ff;
}

.message-role {
  font-size: 12px;
  color: #409eff;
  font-weight: 700;
  margin-bottom: 4px;
}

.message-text {
  color: #1f2d3d;
  line-height: 1.6;
  white-space: pre-wrap;
}

.tool-trace {
  margin-top: 8px;
  border-top: 1px dashed #d9e2f2;
  padding-top: 8px;
}

.tool-item {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 6px;
  color: #475467;
  font-size: 12px;
}

.tool-item em {
  color: #98a2b3;
  font-style: normal;
}

.action-row {
  margin-top: 8px;
}

.chat-input-row {
  display: flex;
  gap: 10px;
  margin-top: 12px;
}

.chat-input-row .el-input {
  flex: 1;
}

.task-form .el-select,
.task-form .el-input-number,
.panel .el-select {
  width: 100%;
}

.storage-agent-page ::v-deep .el-tag__close,
.storage-agent-page ::v-deep .el-input-number__decrease,
.storage-agent-page ::v-deep .el-input-number__increase,
.storage-agent-page ::v-deep .el-select__caret,
.storage-agent-page ::v-deep .el-input__suffix {
  display: none;
}

.storage-agent-page ::v-deep .el-input-number .el-input__inner {
  text-align: left;
}

.preview-table {
  margin-top: 12px;
}

.answer-box {
  padding: 12px;
  margin-top: 10px;
  border: 1px solid #e6eaf2;
  background: #fafcff;
  border-radius: 6px;
}

.answer-title {
  color: #1f2d3d;
  font-weight: 700;
  line-height: 1.5;
}

.answer-meta {
  margin-top: 6px;
  color: #667085;
  font-size: 13px;
}
</style>
