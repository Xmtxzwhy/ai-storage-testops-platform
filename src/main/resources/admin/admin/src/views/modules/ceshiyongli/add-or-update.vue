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
				<el-form-item class="input" v-if="type!='info'"  label="用例名称" prop="yonglimingcheng" >
					<el-input v-model="ruleForm.yonglimingcheng" placeholder="用例名称" clearable  :readonly="ro.yonglimingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="用例名称" prop="yonglimingcheng" >
					<el-input v-model="ruleForm.yonglimingcheng" placeholder="用例名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info'&& !ro.yongliwenjian" label="用例文件" prop="yongliwenjian" >
					<file-upload
						tip="点击上传用例文件"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="true"
						:fileUrls="ruleForm.yongliwenjian?ruleForm.yongliwenjian:''"
						@change="yongliwenjianUploadChange"
					></file-upload>
				</el-form-item>  
				<el-form-item v-else-if="ruleForm.yongliwenjian" label="用例文件" prop="yongliwenjian" >
					<el-button class="downBtn" type="text" size="small" @click="download($base.url+ruleForm.yongliwenjian)">
						<span class="icon iconfont icon-xiazai6"></span>
						下载
					</el-button>
				</el-form-item>
				<el-form-item v-else-if="!ruleForm.yongliwenjian" label="用例文件" prop="yongliwenjian" >
					<el-button class="unBtn" type="text" size="small">
						<span class="icon iconfont icon-xihuan"></span>
						暂无
					</el-button>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="经理账号" prop="jinglizhanghao" >
					<el-input v-model="ruleForm.jinglizhanghao" placeholder="经理账号" clearable  :readonly="ro.jinglizhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="经理账号" prop="jinglizhanghao" >
					<el-input v-model="ruleForm.jinglizhanghao" placeholder="经理账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="登记时间" prop="dengjishijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.dengjishijian" 
						type="datetime"
						:readonly="ro.dengjishijian"
						placeholder="登记时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.dengjishijian" label="登记时间" prop="dengjishijian" >
					<el-input v-model="ruleForm.dengjishijian" placeholder="登记时间" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="功能要求" prop="gongnengyaoqiu" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="功能要求"
					v-model="ruleForm.gongnengyaoqiu" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.gongnengyaoqiu" label="功能要求" prop="gongnengyaoqiu" >
				<span class="text">{{ruleForm.gongnengyaoqiu}}</span>
			</el-form-item>
			<el-form-item class="textarea" v-if="type!='info'" label="存在问题" prop="cunzaiwenti" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="存在问题"
					v-model="ruleForm.cunzaiwenti" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.cunzaiwenti" label="存在问题" prop="cunzaiwenti" >
				<span class="text">{{ruleForm.cunzaiwenti}}</span>
			</el-form-item>
			<el-form-item class="textarea" v-if="type!='info'" label="解决需求" prop="jiejuexuqiu" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="解决需求"
					v-model="ruleForm.jiejuexuqiu" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.jiejuexuqiu" label="解决需求" prop="jiejuexuqiu" >
				<span class="text">{{ruleForm.jiejuexuqiu}}</span>
			</el-form-item>
			<el-form-item v-if="type!='info'"  label="详情信息" prop="xiangqingxinxi" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.xiangqingxinxi" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.xiangqingxinxi" label="详情信息" prop="xiangqingxinxi" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.xiangqingxinxi"></span>
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
					yonglimingcheng : false,
					yongliwenjian : false,
					gongnengyaoqiu : false,
					cunzaiwenti : false,
					jiejuexuqiu : false,
					xiangqingxinxi : false,
					jinglizhanghao : false,
					dengjishijian : false,
				},
			
				ruleForm: {
					yonglimingcheng: '',
					yongliwenjian: '',
					gongnengyaoqiu: '',
					cunzaiwenti: '',
					jiejuexuqiu: '',
					xiangqingxinxi: '',
					jinglizhanghao: '',
					dengjishijian: '',
				},

				rules: {
					yonglimingcheng: [
					],
					yongliwenjian: [
					],
					gongnengyaoqiu: [
					],
					cunzaiwenti: [
					],
					jiejuexuqiu: [
					],
					xiangqingxinxi: [
					],
					jinglizhanghao: [
					],
					dengjishijian: [
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
			this.ruleForm.dengjishijian = this.getCurDateTime()
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
						if(o=='yonglimingcheng'){
							this.ruleForm.yonglimingcheng = obj[o];
							this.ro.yonglimingcheng = true;
							continue;
						}
						if(o=='yongliwenjian'){
							this.ruleForm.yongliwenjian = obj[o];
							this.ro.yongliwenjian = true;
							continue;
						}
						if(o=='gongnengyaoqiu'){
							this.ruleForm.gongnengyaoqiu = obj[o];
							this.ro.gongnengyaoqiu = true;
							continue;
						}
						if(o=='cunzaiwenti'){
							this.ruleForm.cunzaiwenti = obj[o];
							this.ro.cunzaiwenti = true;
							continue;
						}
						if(o=='jiejuexuqiu'){
							this.ruleForm.jiejuexuqiu = obj[o];
							this.ro.jiejuexuqiu = true;
							continue;
						}
						if(o=='xiangqingxinxi'){
							this.ruleForm.xiangqingxinxi = obj[o];
							this.ro.xiangqingxinxi = true;
							continue;
						}
						if(o=='jinglizhanghao'){
							this.ruleForm.jinglizhanghao = obj[o];
							this.ro.jinglizhanghao = true;
							continue;
						}
						if(o=='dengjishijian'){
							this.ruleForm.dengjishijian = obj[o];
							this.ro.dengjishijian = true;
							continue;
						}
					}
				}
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
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `ceshiyongli/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.xiangqingxinxi = this.ruleForm.xiangqingxinxi.replace(reg,'../../../springbootarkc6v1u/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.yongliwenjian!=null) {
						this.ruleForm.yongliwenjian = this.ruleForm.yongliwenjian.replace(new RegExp(this.$base.url,"g"),"");
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
								url: `ceshiyongli/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.ceshiyongliCrossAddOrUpdateFlag = false;
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
				this.parent.ceshiyongliCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			yongliwenjianUploadChange(fileUrls) {
				this.ruleForm.yongliwenjian = fileUrls;
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
