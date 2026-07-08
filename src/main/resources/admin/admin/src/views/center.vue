<template>
	<div :style='{"padding":"20px 30px","margin":"0 0 0 30px","fontSize":"15px"}'>
		<el-form
			:style='{"padding":"30px 25% 30px 15%","borderColor":"#eee","margin":"0 20px 0 40px","borderWidth":"0px 0 0","background":"#FFFFFF","width":"100%","borderStyle":"solid"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			label-width="180px"
		>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xiangmujingli'"  label="经理工号" prop="jingligonghao">
					<el-input v-model="ruleForm.jingligonghao" readonly						placeholder="经理工号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xiangmujingli'"  label="经理名称" prop="jinglimingcheng">
					<el-input v-model="ruleForm.jinglimingcheng" 						placeholder="经理名称" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='xiangmujingli'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in xiangmujinglixingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='xiangmujingli'"  label="联系方式" prop="lianxifangshi">
					<el-input v-model="ruleForm.lianxifangshi" 						placeholder="联系方式" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='xiangmujingli'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="xiangmujinglitouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='ceshijingli'"  label="经理账号" prop="jinglizhanghao">
					<el-input v-model="ruleForm.jinglizhanghao" readonly						placeholder="经理账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='ceshijingli'"  label="经理姓名" prop="jinglixingming">
					<el-input v-model="ruleForm.jinglixingming" 						placeholder="经理姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='ceshijingli'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="ceshijinglitouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='ceshijingli'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in ceshijinglixingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='ceshijingli'"  label="联系电话" prop="lianxidianhua">
					<el-input v-model="ruleForm.lianxidianhua" 						placeholder="联系电话" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='ceshigongchengshi'"  label="工程师号" prop="gongchengshihao">
					<el-input v-model="ruleForm.gongchengshihao" readonly						placeholder="工程师号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='ceshigongchengshi'"  label="工程师名" prop="gongchengshiming">
					<el-input v-model="ruleForm.gongchengshiming" 						placeholder="工程师名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='ceshigongchengshi'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in ceshigongchengshixingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='ceshigongchengshi'"  label="手机" prop="shouji">
					<el-input v-model="ruleForm.shouji" 						placeholder="手机" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='ceshigongchengshi'"  label="团队名称" prop="tuanduimingcheng">
					<el-select v-model="ruleForm.tuanduimingcheng"  placeholder="请选择团队名称">
						<el-option
							v-for="(item,index) in ceshigongchengshituanduimingchengOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='kaifarenyuan'"  label="开发账号" prop="kaifazhanghao">
					<el-input v-model="ruleForm.kaifazhanghao" readonly						placeholder="开发账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='kaifarenyuan'"  label="开发姓名" prop="kaifaxingming">
					<el-input v-model="ruleForm.kaifaxingming" 						placeholder="开发姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='kaifarenyuan'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in kaifarenyuanxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='kaifarenyuan'"  label="开发电话" prop="kaifadianhua">
					<el-input v-model="ruleForm.kaifadianhua" 						placeholder="开发电话" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='kaifarenyuan'"  label="团队名称" prop="tuanduimingcheng">
					<el-select v-model="ruleForm.tuanduimingcheng"  placeholder="请选择团队名称">
						<el-option
							v-for="(item,index) in kaifarenyuantuanduimingchengOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='fenxishi'"  label="分析师号" prop="fenxishihao">
					<el-input v-model="ruleForm.fenxishihao" readonly						placeholder="分析师号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='fenxishi'"  label="分析师名" prop="fenxishiming">
					<el-input v-model="ruleForm.fenxishiming" 						placeholder="分析师名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='fenxishi'"  label="性别" prop="xingbie">
					<el-select v-model="ruleForm.xingbie"  placeholder="请选择性别">
						<el-option
							v-for="(item,index) in fenxishixingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}'   v-if="flag=='fenxishi'"  label="电话号码" prop="dianhuahaoma">
					<el-input v-model="ruleForm.dianhuahaoma" 						placeholder="电话号码" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='users'" label="用户名" prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"0","margin":"0 0 26px 0","display":"inline-block"}' v-if="flag=='users'" label="头像" prop="image">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.image?ruleForm.image:''"
						@change="usersimageUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","padding":"0","margin":"20px 0 0"}'>
					<el-button class="btn3" :style='{"border":"2px solid #000000","cursor":"pointer","margin":"0 10px 0 10px","color":"#000000","borderRadius":"5px","background":"#fff","width":"130px","fontSize":"16px","height":"40px"}' type="primary" @click="onUpdateHandler">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
						提交
					</el-button>
				</el-form-item>
		</el-form>
	</div>
</template>
<script>
// 校验引入
import { 
	isMobile,
} from "@/utils/validate";

export default {
	data() {
		return {
			ruleForm: {},
			flag: '',
			usersFlag: false,
			xiangmujinglixingbieOptions: [],
			ceshijinglixingbieOptions: [],
			ceshigongchengshixingbieOptions: [],
			ceshigongchengshituanduimingchengOptions: [],
			kaifarenyuanxingbieOptions: [],
			kaifarenyuantuanduimingchengOptions: [],
			fenxishixingbieOptions: [],
		};
	},
	mounted() {
		var table = this.$storage.get("sessionTable");
		this.flag = table;
		this.$http({
			url: `${this.$storage.get("sessionTable")}/session`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.ruleForm = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
		this.xiangmujinglixingbieOptions = "男,女".split(',')
		this.ceshijinglixingbieOptions = "男,女".split(',')
		this.ceshigongchengshixingbieOptions = "男,女".split(',')
		this.$http({
			url: `option/ceshituandui/tuanduimingcheng`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.ceshigongchengshituanduimingchengOptions = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
		this.kaifarenyuanxingbieOptions = "男,女".split(',')
		this.$http({
			url: `option/ceshituandui/tuanduimingcheng`,
			method: "get"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.kaifarenyuantuanduimingchengOptions = data.data;
			} else {
				this.$message.error(data.msg);
			}
		});
		this.fenxishixingbieOptions = "男,女".split(',')
	},
	methods: {
		xiangmujinglitouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		ceshijinglitouxiangUploadChange(fileUrls) {
			this.ruleForm.touxiang = fileUrls;
		},
		usersimageUploadChange(fileUrls) {
			this.ruleForm.image = fileUrls;
		},
		onUpdateHandler() {
			if((!this.ruleForm.jingligonghao)&& 'xiangmujingli'==this.flag){
				this.$message.error('经理工号不能为空');
				return
			}


			if((!this.ruleForm.mima)&& 'xiangmujingli'==this.flag){
				this.$message.error('密码不能为空');
				return
			}

			if((!this.ruleForm.jinglimingcheng)&& 'xiangmujingli'==this.flag){
				this.$message.error('经理名称不能为空');
				return
			}


			if((!this.ruleForm.xingbie)&& 'xiangmujingli'==this.flag){
				this.$message.error('性别不能为空');
				return
			}


			if((!this.ruleForm.lianxifangshi)&& 'xiangmujingli'==this.flag){
				this.$message.error('联系方式不能为空');
				return
			}


			if( 'xiangmujingli' ==this.flag && this.ruleForm.lianxifangshi&&(!isMobile(this.ruleForm.lianxifangshi))){
				this.$message.error(`联系方式应输入手机格式`);
				return
			}


			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}







			if(this.ruleForm.touxiang!=null) {
				this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
			}




			if( 'ceshijingli' ==this.flag && this.ruleForm.lianxidianhua&&(!isMobile(this.ruleForm.lianxidianhua))){
				this.$message.error(`联系电话应输入手机格式`);
				return
			}
			if((!this.ruleForm.gongchengshihao)&& 'ceshigongchengshi'==this.flag){
				this.$message.error('工程师号不能为空');
				return
			}


			if((!this.ruleForm.gongchengshiming)&& 'ceshigongchengshi'==this.flag){
				this.$message.error('工程师名不能为空');
				return
			}


			if((!this.ruleForm.mima)&& 'ceshigongchengshi'==this.flag){
				this.$message.error('密码不能为空');
				return
			}





			if( 'ceshigongchengshi' ==this.flag && this.ruleForm.shouji&&(!isMobile(this.ruleForm.shouji))){
				this.$message.error(`手机应输入手机格式`);
				return
			}


			if((!this.ruleForm.kaifazhanghao)&& 'kaifarenyuan'==this.flag){
				this.$message.error('开发账号不能为空');
				return
			}


			if((!this.ruleForm.kaifaxingming)&& 'kaifarenyuan'==this.flag){
				this.$message.error('开发姓名不能为空');
				return
			}


			if((!this.ruleForm.mima)&& 'kaifarenyuan'==this.flag){
				this.$message.error('密码不能为空');
				return
			}





			if( 'kaifarenyuan' ==this.flag && this.ruleForm.kaifadianhua&&(!isMobile(this.ruleForm.kaifadianhua))){
				this.$message.error(`开发电话应输入手机格式`);
				return
			}
			if((!this.ruleForm.tuanduimingcheng)&& 'kaifarenyuan'==this.flag){
				this.$message.error('团队名称不能为空');
				return
			}


			if((!this.ruleForm.fenxishihao)&& 'fenxishi'==this.flag){
				this.$message.error('分析师号不能为空');
				return
			}


			if((!this.ruleForm.fenxishiming)&& 'fenxishi'==this.flag){
				this.$message.error('分析师名不能为空');
				return
			}


			if((!this.ruleForm.mima)&& 'fenxishi'==this.flag){
				this.$message.error('密码不能为空');
				return
			}





			if( 'fenxishi' ==this.flag && this.ruleForm.dianhuahaoma&&(!isMobile(this.ruleForm.dianhuahaoma))){
				this.$message.error(`电话号码应输入手机格式`);
				return
			}
			if('users'==this.flag && this.ruleForm.username.trim().length<1) {
				this.$message.error(`用户名不能为空`);
				return	
			}
			if(this.flag=='users'){
				this.ruleForm.image = this.ruleForm.image.replace(new RegExp(this.$base.url,"g"),"")
			}
			this.$http({
				url: `${this.$storage.get("sessionTable")}/update`,
				method: "post",
				data: this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "修改信息成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							if(this.flag=='users'){
								this.$storage.set('headportrait',this.ruleForm.image)
							}
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>
<style lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
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
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
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
	
	.add-update-preview .btn3 {
				border: 2px solid #000000;
				cursor: pointer;
				border-radius: 5px;
				margin: 0 10px 0 10px;
				color: #000000;
				background: #fff;
				width: 130px;
				font-size: 16px;
				height: 40px;
			}
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
	
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
