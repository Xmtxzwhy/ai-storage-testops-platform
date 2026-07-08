<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="项目编号" prop="xiangmubianhao" >
					<el-input v-model="ruleForm.xiangmubianhao" placeholder="项目编号" clearable  :readonly="ro.xiangmubianhao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目编号" prop="xiangmubianhao" >
					<el-input v-model="ruleForm.xiangmubianhao" placeholder="项目编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="任务名称" prop="renwumingcheng" >
					<el-input v-model="ruleForm.renwumingcheng" placeholder="任务名称" clearable  :readonly="ro.renwumingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="任务名称" prop="renwumingcheng" >
					<el-input v-model="ruleForm.renwumingcheng" placeholder="任务名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="项目名称" prop="xiangmumingcheng" >
					<el-input v-model="ruleForm.xiangmumingcheng" placeholder="项目名称" clearable  :readonly="ro.xiangmumingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目名称" prop="xiangmumingcheng" >
					<el-input v-model="ruleForm.xiangmumingcheng" placeholder="项目名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="项目类型" prop="xiangmuleixing" >
					<el-input v-model="ruleForm.xiangmuleixing" placeholder="项目类型" clearable  :readonly="ro.xiangmuleixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目类型" prop="xiangmuleixing" >
					<el-input v-model="ruleForm.xiangmuleixing" placeholder="项目类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info'&& !ro.renwuwenjian" label="任务文件" prop="renwuwenjian" >
					<file-upload
						tip="点击上传任务文件"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="true"
						:fileUrls="ruleForm.renwuwenjian?ruleForm.renwuwenjian:''"
						@change="renwuwenjianUploadChange"
					></file-upload>
				</el-form-item>  
				<el-form-item v-else-if="ruleForm.renwuwenjian" label="任务文件" prop="renwuwenjian" >
					<el-button class="downBtn" type="text" size="small" @click="download($base.url+ruleForm.renwuwenjian)">
						<span class="icon iconfont icon-xiazai6"></span>
						下载
					</el-button>
				</el-form-item>
				<el-form-item v-else-if="!ruleForm.renwuwenjian" label="任务文件" prop="renwuwenjian" >
					<el-button class="unBtn" type="text" size="small">
						<span class="icon iconfont icon-xihuan"></span>
						暂无
					</el-button>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="下发时间" prop="xiafashijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.xiafashijian" 
						type="datetime"
						:readonly="ro.xiafashijian"
						placeholder="下发时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.xiafashijian" label="下发时间" prop="xiafashijian" >
					<el-input v-model="ruleForm.xiafashijian" placeholder="下发时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="经理账号" prop="jinglizhanghao" >
					<el-input v-model="ruleForm.jinglizhanghao" placeholder="经理账号" clearable  :readonly="ro.jinglizhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="经理账号" prop="jinglizhanghao" >
					<el-input v-model="ruleForm.jinglizhanghao" placeholder="经理账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="经理姓名" prop="jinglixingming" >
					<el-input v-model="ruleForm.jinglixingming" placeholder="经理姓名" clearable  :readonly="ro.jinglixingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="经理姓名" prop="jinglixingming" >
					<el-input v-model="ruleForm.jinglixingming" placeholder="经理姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'" label="工程师号" prop="gongchengshihao" >
					<el-select :disabled="ro.gongchengshihao" @change="gongchengshihaoChange" v-model="ruleForm.gongchengshihao" placeholder="请选择工程师号">
						<el-option
							v-for="(item,index) in gongchengshihaoOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.gongchengshihao" label="工程师号" prop="gongchengshihao" >
					<el-input v-model="ruleForm.gongchengshihao" placeholder="工程师号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="工程师名" prop="gongchengshiming" >
					<el-input v-model="ruleForm.gongchengshiming" placeholder="工程师名" clearable  :readonly="ro.gongchengshiming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="工程师名" prop="gongchengshiming" >
					<el-input v-model="ruleForm.gongchengshiming" placeholder="工程师名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="团队名称" prop="tuanduimingcheng" >
					<el-input v-model="ruleForm.tuanduimingcheng" placeholder="团队名称" clearable  :readonly="ro.tuanduimingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="团队名称" prop="tuanduimingcheng" >
					<el-input v-model="ruleForm.tuanduimingcheng" placeholder="团队名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="任务状态" prop="renwuzhuangtai" >
					<el-select :disabled="ro.renwuzhuangtai" v-model="ruleForm.renwuzhuangtai" placeholder="请选择任务状态" >
						<el-option
							v-for="(item,index) in renwuzhuangtaiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="任务状态" prop="renwuzhuangtai" >
					<el-input v-model="ruleForm.renwuzhuangtai"
						placeholder="任务状态" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'"  label="任务内容" prop="renwuneirong" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.renwuneirong" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.renwuneirong" label="任务内容" prop="renwuneirong" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.renwuneirong"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	export default {
		data() {
			return {
				id: '',
				type: '',
			
			
				ro:{
					xiangmubianhao : false,
					renwumingcheng : false,
					xiangmumingcheng : false,
					xiangmuleixing : false,
					renwuwenjian : false,
					renwuneirong : false,
					xiafashijian : false,
					jinglizhanghao : false,
					jinglixingming : false,
					gongchengshihao : false,
					gongchengshiming : false,
					tuanduimingcheng : false,
					renwuzhuangtai : false,
				},
			
				ruleForm: {
					xiangmubianhao: '',
					renwumingcheng: '',
					xiangmumingcheng: '',
					xiangmuleixing: '',
					renwuwenjian: '',
					renwuneirong: '',
					xiafashijian: '',
					jinglizhanghao: '',
					jinglixingming: '',
					gongchengshihao: '',
					gongchengshiming: '',
					tuanduimingcheng: '',
					renwuzhuangtai: '待完成',
				},
				gongchengshihaoOptions: [],
				renwuzhuangtaiOptions: [],

				rules: {
					xiangmubianhao: [
					],
					renwumingcheng: [
					],
					xiangmumingcheng: [
						{ required: true, message: '项目名称不能为空', trigger: 'blur' },
					],
					xiangmuleixing: [
					],
					renwuwenjian: [
						{ required: true, message: '任务文件不能为空', trigger: 'blur' },
					],
					renwuneirong: [
						{ required: true, message: '任务内容不能为空', trigger: 'blur' },
					],
					xiafashijian: [
					],
					jinglizhanghao: [
					],
					jinglixingming: [
					],
					gongchengshihao: [
						{ required: true, message: '工程师号不能为空', trigger: 'blur' },
					],
					gongchengshiming: [
					],
					tuanduimingcheng: [
					],
					renwuzhuangtai: [
						{ required: true, message: '任务状态不能为空', trigger: 'blur' },
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.xiafashijian = this.getCurDateTime()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='xiangmubianhao'){
							this.ruleForm.xiangmubianhao = obj[o];
							this.ro.xiangmubianhao = true;
							continue;
						}
						if(o=='renwumingcheng'){
							this.ruleForm.renwumingcheng = obj[o];
							this.ro.renwumingcheng = true;
							continue;
						}
						if(o=='xiangmumingcheng'){
							this.ruleForm.xiangmumingcheng = obj[o];
							this.ro.xiangmumingcheng = true;
							continue;
						}
						if(o=='xiangmuleixing'){
							this.ruleForm.xiangmuleixing = obj[o];
							this.ro.xiangmuleixing = true;
							continue;
						}
						if(o=='renwuwenjian'){
							this.ruleForm.renwuwenjian = obj[o];
							this.ro.renwuwenjian = true;
							continue;
						}
						if(o=='renwuneirong'){
							this.ruleForm.renwuneirong = obj[o];
							this.ro.renwuneirong = true;
							continue;
						}
						if(o=='xiafashijian'){
							this.ruleForm.xiafashijian = obj[o];
							this.ro.xiafashijian = true;
							continue;
						}
						if(o=='jinglizhanghao'){
							this.ruleForm.jinglizhanghao = obj[o];
							this.ro.jinglizhanghao = true;
							continue;
						}
						if(o=='jinglixingming'){
							this.ruleForm.jinglixingming = obj[o];
							this.ro.jinglixingming = true;
							continue;
						}
						if(o=='gongchengshihao'){
							this.ruleForm.gongchengshihao = obj[o];
							this.ro.gongchengshihao = true;
							continue;
						}
						if(o=='gongchengshiming'){
							this.ruleForm.gongchengshiming = obj[o];
							this.ro.gongchengshiming = true;
							continue;
						}
						if(o=='tuanduimingcheng'){
							this.ruleForm.tuanduimingcheng = obj[o];
							this.ro.tuanduimingcheng = true;
							continue;
						}
						if(o=='renwuzhuangtai'){
							this.ruleForm.renwuzhuangtai = obj[o];
							this.ro.renwuzhuangtai = true;
							continue;
						}
					}
					this.ruleForm.renwuzhuangtai = '待完成'; 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.jinglizhanghao!=''&&json.jinglizhanghao) || json.jinglizhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.jinglizhanghao = json.jinglizhanghao
							this.ro.jinglizhanghao = true;
						}
						if(((json.jinglixingming!=''&&json.jinglixingming) || json.jinglixingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.jinglixingming = json.jinglixingming
							this.ro.jinglixingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/ceshigongchengshi/gongchengshihao`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.gongchengshihaoOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.renwuzhuangtaiOptions = "已完成,待完成".split(',')
			
			},
			// 下二随
			gongchengshihaoChange () {
				this.$http({
					url: `follow/ceshigongchengshi/gongchengshihao?columnValue=`+ this.ruleForm.gongchengshihao,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						if(data.data.gongchengshiming){
							this.ruleForm.gongchengshiming = data.data.gongchengshiming
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `ceshirenwu/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.renwuneirong = this.ruleForm.renwuneirong.replace(reg,'../../../springbootarkc6v1u/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.renwuwenjian!=null) {
						this.ruleForm.renwuwenjian = this.ruleForm.renwuwenjian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `ceshirenwu/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.ceshirenwuCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.ceshirenwuCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			renwuwenjianUploadChange(fileUrls) {
				this.ruleForm.renwuwenjian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
		width: 100%;
	}
	.add-update-preview {
		padding: 30px 25% 30px 15%;
		margin: 0 20px 0 40px;
		background: #FFFFFF;
		width: 100%;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 100%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 600;
		width: 180px;
		font-size: 14px;
		font-family: Source Han Sans, Source Han Sans;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		border: 1px solid #a7a7a7;
		border-radius: 0px;
		padding: 10px;
		color: #000000;
		background: #fff;
		display: block;
		width: 100%;
		font-size: 16px;
		line-height: 24px;
		text-align: left;
		min-width: 50%;
		height: auto;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #C9C9C9;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #C9C9C9;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0  40px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px solid #C9C9C9;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0  40px;
		color: #666;
		background: none;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #a7a7a7;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		text-align: left;
		min-width: 50%;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			display: none;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #a7a7a7;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		text-align: left;
		min-width: 50%;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			display: none;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 1px solid #a7a7a7;
		border-radius: 0px;
		padding: 0 12px;
		color: #000000;
		background: #fff;
		width: 100%;
		font-size: 16px;
		text-align: left;
		min-width: 50%;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px  solid #BFBFBF;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px  solid #BFBFBF;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px  solid #BFBFBF;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #C9C9C9;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 14px;
		min-width: 100%;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 1px solid #C9C9C9;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: #fff;
				width: auto;
				font-size: 14px;
				min-width: 100%;
				height: 120px;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		width: 100%;
		.btn1 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 2px solid #000000;
			cursor: pointer;
			border-radius: 5px;
			margin: 0 10px 0 10px;
			color: #000000;
			background: #fff;
			width: 130px;
			font-size: 16px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
