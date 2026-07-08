<template>
	<div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__backInDown" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">测试管理系统注册</div>
					<el-form-item class="list-item" v-if="tableName=='xiangmujingli'">
						<div class="lable" :class="changeRules('jingligonghao')?'required':''">经理工号：</div>
						<el-input  v-model="ruleForm.jingligonghao"  autocomplete="off" placeholder="经理工号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xiangmujingli'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xiangmujingli'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xiangmujingli'">
						<div class="lable" :class="changeRules('jinglimingcheng')?'required':''">经理名称：</div>
						<el-input  v-model="ruleForm.jinglimingcheng"  autocomplete="off" placeholder="经理名称"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xiangmujingli'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in xiangmujinglixingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xiangmujingli'">
						<div class="lable" :class="changeRules('lianxifangshi')?'required':''">联系方式：</div>
						<el-input  v-model="ruleForm.lianxifangshi"  autocomplete="off" placeholder="联系方式"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xiangmujingli'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="xiangmujinglitouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshijingli'">
						<div class="lable" :class="changeRules('jinglizhanghao')?'required':''">经理账号：</div>
						<el-input  v-model="ruleForm.jinglizhanghao"  autocomplete="off" placeholder="经理账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshijingli'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshijingli'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshijingli'">
						<div class="lable" :class="changeRules('jinglixingming')?'required':''">经理姓名：</div>
						<el-input  v-model="ruleForm.jinglixingming"  autocomplete="off" placeholder="经理姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshijingli'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="ceshijinglitouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshijingli'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in ceshijinglixingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshijingli'">
						<div class="lable" :class="changeRules('lianxidianhua')?'required':''">联系电话：</div>
						<el-input  v-model="ruleForm.lianxidianhua"  autocomplete="off" placeholder="联系电话"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshigongchengshi'">
						<div class="lable" :class="changeRules('gongchengshihao')?'required':''">工程师号：</div>
						<el-input  v-model="ruleForm.gongchengshihao"  autocomplete="off" placeholder="工程师号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshigongchengshi'">
						<div class="lable" :class="changeRules('gongchengshiming')?'required':''">工程师名：</div>
						<el-input  v-model="ruleForm.gongchengshiming"  autocomplete="off" placeholder="工程师名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshigongchengshi'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshigongchengshi'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshigongchengshi'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in ceshigongchengshixingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshigongchengshi'">
						<div class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
						<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='ceshigongchengshi'">
						<div class="lable" :class="changeRules('tuanduimingcheng')?'required':''">团队名称：</div>
						<el-select v-model="ruleForm.tuanduimingcheng" placeholder="请选择团队名称" >
							<el-option
								v-for="(item,index) in ceshigongchengshituanduimingchengOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='kaifarenyuan'">
						<div class="lable" :class="changeRules('kaifazhanghao')?'required':''">开发账号：</div>
						<el-input  v-model="ruleForm.kaifazhanghao"  autocomplete="off" placeholder="开发账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='kaifarenyuan'">
						<div class="lable" :class="changeRules('kaifaxingming')?'required':''">开发姓名：</div>
						<el-input  v-model="ruleForm.kaifaxingming"  autocomplete="off" placeholder="开发姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='kaifarenyuan'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='kaifarenyuan'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='kaifarenyuan'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in kaifarenyuanxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='kaifarenyuan'">
						<div class="lable" :class="changeRules('kaifadianhua')?'required':''">开发电话：</div>
						<el-input  v-model="ruleForm.kaifadianhua"  autocomplete="off" placeholder="开发电话"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='kaifarenyuan'">
						<div class="lable" :class="changeRules('tuanduimingcheng')?'required':''">团队名称：</div>
						<el-select v-model="ruleForm.tuanduimingcheng" placeholder="请选择团队名称" >
							<el-option
								v-for="(item,index) in kaifarenyuantuanduimingchengOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fenxishi'">
						<div class="lable" :class="changeRules('fenxishihao')?'required':''">分析师号：</div>
						<el-input  v-model="ruleForm.fenxishihao"  autocomplete="off" placeholder="分析师号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fenxishi'">
						<div class="lable" :class="changeRules('fenxishiming')?'required':''">分析师名：</div>
						<el-input  v-model="ruleForm.fenxishiming"  autocomplete="off" placeholder="分析师名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fenxishi'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fenxishi'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fenxishi'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in fenxishixingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='fenxishi'">
						<div class="lable" :class="changeRules('dianhuahaoma')?'required':''">电话号码：</div>
						<el-input  v-model="ruleForm.dianhuahaoma"  autocomplete="off" placeholder="电话号码"  type="text"  />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
				<div class="idea-box1">注册</div>
				<div class="idea-box2">输入您的账号和密码以注册帐户</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
            xiangmujinglixingbieOptions: [],
            ceshijinglixingbieOptions: [],
            ceshigongchengshixingbieOptions: [],
            ceshigongchengshituanduimingchengOptions: [],
            kaifarenyuanxingbieOptions: [],
            kaifarenyuantuanduimingchengOptions: [],
            fenxishixingbieOptions: [],
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='xiangmujingli'){
				this.ruleForm = {
					jingligonghao: '',
					mima: '',
					jinglimingcheng: '',
					xingbie: '',
					lianxifangshi: '',
					touxiang: '',
				}
			}
			if(this.tableName=='ceshijingli'){
				this.ruleForm = {
					jinglizhanghao: '',
					mima: '',
					jinglixingming: '',
					touxiang: '',
					xingbie: '',
					lianxidianhua: '',
				}
			}
			if(this.tableName=='ceshigongchengshi'){
				this.ruleForm = {
					gongchengshihao: '',
					gongchengshiming: '',
					mima: '',
					xingbie: '',
					shouji: '',
					tuanduimingcheng: '',
				}
			}
			if(this.tableName=='kaifarenyuan'){
				this.ruleForm = {
					kaifazhanghao: '',
					kaifaxingming: '',
					mima: '',
					xingbie: '',
					kaifadianhua: '',
					tuanduimingcheng: '',
				}
			}
			if(this.tableName=='fenxishi'){
				this.ruleForm = {
					fenxishihao: '',
					fenxishiming: '',
					mima: '',
					xingbie: '',
					dianhuahaoma: '',
				}
			}
			if ('xiangmujingli' == this.tableName) {
				this.rules.jingligonghao = [{ required: true, message: '请输入经理工号', trigger: 'blur' }]
			}
			if ('xiangmujingli' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('xiangmujingli' == this.tableName) {
				this.rules.jinglimingcheng = [{ required: true, message: '请输入经理名称', trigger: 'blur' }]
			}
			if ('xiangmujingli' == this.tableName) {
				this.rules.xingbie = [{ required: true, message: '请输入性别', trigger: 'blur' }]
			}
			if ('xiangmujingli' == this.tableName) {
				this.rules.lianxifangshi = [{ required: true, message: '请输入联系方式', trigger: 'blur' }]
			}
			if ('ceshigongchengshi' == this.tableName) {
				this.rules.gongchengshihao = [{ required: true, message: '请输入工程师号', trigger: 'blur' }]
			}
			if ('ceshigongchengshi' == this.tableName) {
				this.rules.gongchengshiming = [{ required: true, message: '请输入工程师名', trigger: 'blur' }]
			}
			if ('ceshigongchengshi' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('kaifarenyuan' == this.tableName) {
				this.rules.kaifazhanghao = [{ required: true, message: '请输入开发账号', trigger: 'blur' }]
			}
			if ('kaifarenyuan' == this.tableName) {
				this.rules.kaifaxingming = [{ required: true, message: '请输入开发姓名', trigger: 'blur' }]
			}
			if ('kaifarenyuan' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('kaifarenyuan' == this.tableName) {
				this.rules.tuanduimingcheng = [{ required: true, message: '请输入团队名称', trigger: 'blur' }]
			}
			if ('fenxishi' == this.tableName) {
				this.rules.fenxishihao = [{ required: true, message: '请输入分析师号', trigger: 'blur' }]
			}
			if ('fenxishi' == this.tableName) {
				this.rules.fenxishiming = [{ required: true, message: '请输入分析师名', trigger: 'blur' }]
			}
			if ('fenxishi' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
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
		}
	},
	created() {
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        xiangmujinglitouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        ceshijinglitouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.jingligonghao) && `xiangmujingli` == this.tableName){
				this.$message.error(`经理工号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `xiangmujingli` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `xiangmujingli` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.jinglimingcheng) && `xiangmujingli` == this.tableName){
				this.$message.error(`经理名称不能为空`);
				return
			}
			if((!this.ruleForm.xingbie) && `xiangmujingli` == this.tableName){
				this.$message.error(`性别不能为空`);
				return
			}
			if((!this.ruleForm.lianxifangshi) && `xiangmujingli` == this.tableName){
				this.$message.error(`联系方式不能为空`);
				return
			}
			if(`xiangmujingli` == this.tableName && this.ruleForm.lianxifangshi &&(!this.$validate.isMobile(this.ruleForm.lianxifangshi))){
				this.$message.error(`联系方式应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `ceshijingli` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`ceshijingli` == this.tableName && this.ruleForm.lianxidianhua &&(!this.$validate.isMobile(this.ruleForm.lianxidianhua))){
				this.$message.error(`联系电话应输入手机格式`);
				return
			}
			if((!this.ruleForm.gongchengshihao) && `ceshigongchengshi` == this.tableName){
				this.$message.error(`工程师号不能为空`);
				return
			}
			if((!this.ruleForm.gongchengshiming) && `ceshigongchengshi` == this.tableName){
				this.$message.error(`工程师名不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `ceshigongchengshi` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `ceshigongchengshi` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`ceshigongchengshi` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
				this.$message.error(`手机应输入手机格式`);
				return
			}
			if((!this.ruleForm.kaifazhanghao) && `kaifarenyuan` == this.tableName){
				this.$message.error(`开发账号不能为空`);
				return
			}
			if((!this.ruleForm.kaifaxingming) && `kaifarenyuan` == this.tableName){
				this.$message.error(`开发姓名不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `kaifarenyuan` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `kaifarenyuan` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`kaifarenyuan` == this.tableName && this.ruleForm.kaifadianhua &&(!this.$validate.isMobile(this.ruleForm.kaifadianhua))){
				this.$message.error(`开发电话应输入手机格式`);
				return
			}
			if((!this.ruleForm.tuanduimingcheng) && `kaifarenyuan` == this.tableName){
				this.$message.error(`团队名称不能为空`);
				return
			}
			if((!this.ruleForm.fenxishihao) && `fenxishi` == this.tableName){
				this.$message.error(`分析师号不能为空`);
				return
			}
			if((!this.ruleForm.fenxishiming) && `fenxishi` == this.tableName){
				this.$message.error(`分析师名不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `fenxishi` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `fenxishi` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`fenxishi` == this.tableName && this.ruleForm.dianhuahaoma &&(!this.$validate.isMobile(this.ruleForm.dianhuahaoma))){
				this.$message.error(`电话号码应输入手机格式`);
				return
			}
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
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
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20240920/c1dd29229033480faa17cc36709463ef.png);
	background-repeat: no-repeat;
	background-size: cover !important;
	background: url(http://codegen.caihongy.cn/20240920/c1dd29229033480faa17cc36709463ef.png);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: flex-end;
	align-items: center;
	position: relative;
	.rgs-form {
		.rgs-form2 {
		flex-direction: column;
		display: flex;
		width: 100%;
		flex-wrap: wrap;
		}
		padding: 20px 40px 40px 40px;
		margin: 0 351px 0 0;
		z-index: 1000;
		background-size: cover;
		display: flex;
		min-height: 100%;
		flex-wrap: wrap;
		border-radius: 0;
		box-shadow: inset 0px 0px 0px 0px #000;
		flex-direction: column;
		width: 600px;
		align-items: flex-start;
		position: relative;
		height: auto;
		.title {
			padding: 0;
			margin: 0 0% 20px 0;
			color: #000000;
			top: 60px;
			left: 0;
			background: none;
			font-weight: 400;
			width: 100%;
			font-size: 28px;
			line-height: 40px;
			text-align: left;
		}
		.list-item {
			padding: 0 ;
			margin: 0 0 15px  80px;
			width: 84%;
			position: relative;
			height: auto;
			/deep/ .el-form-item__content {
				display: block;
			}
			.lable {
				color: #000;
				left: -78px;
				letter-spacing: 1px;
				width: 120px;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				position: absolute!important;
				border-style: solid;
				text-align: left;
				height: 46px;
			}
			.el-input {
				border-radius: 0;
				flex: 1;
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 2px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input-number {
				border-radius: 0;
				flex: 1;
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 2px 0;
				border-style: solid;
				height: 46px;
			}
			.el-input-number /deep/ .el-input-number__decrease {
				display: none;
			}
			.el-input-number /deep/ .el-input-number__increase {
				display: none;
			}
			.el-select {
				width: 100%;
			}
			.el-select /deep/ .el-input__inner {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border-radius: 0;
				padding: 0 30px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border-radius: 0;
				padding: 0 10px;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color: #000;
				border-width: 0 0 2px 0;
				border-style: solid;
				height: 46px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			/deep/ .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			/deep/ .upload .upload-img {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				margin: 0 0 0 45px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				margin: 0 0 0 45px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				margin: 0 0 0 45px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				margin: 0 0 0 45px;
				color: #666;
				font-size: 15px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #999;
				font-size: 16px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: -10px;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				margin: 0 0 0 40px;
				background: #fff;
				width: 92%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color:  #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			input:focus {
				border-radius: 0;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: #fff;
				flex: 1;
				font-size: 16px;
				border-color:  #D8D8D8;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: 46px;
			}
			input::placeholder {
				color: #999;
				font-size: 16px;
			}
			button {
				border: 0px solid #efeff7;
				cursor: pointer;
				border-radius: 0 4px 4px 0;
				padding: 0;
				margin: 1px 0 0;
				color: #fff;
				background: #FFC144;
				width: 100px;
				font-size: 15px;
				position: absolute;
				right: 0px;
				height: 38px;
			}
			button:hover {
				opacity: 0.8;
			}
		}
		.register-btn {
			margin: 20px auto;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			order: 2;
		}
		.register-btn1 {
			order: 3;
		}
		.register-btn2 {
			width: 100%;
		}
		.r-btn {
			border: 0px solid rgba(0, 0, 0, 1);
			cursor: pointer;
			padding: 0 10px;
			margin: 0 0 10px  ;
			color: #fff;
			font-weight: 500;
			letter-spacing: 1px ;
			font-size: 36px;
			border-radius: 10px;
			background: #FFC144;
			width: 520px;
			min-width: 68px;
			height: 80px;
		}
		.r-btn:hover {
			border: 0px solid rgba(0, 0, 0, 1);
			opacity: 0.8;
		}
		.r-login {
			cursor: pointer;
			padding: 0;
			color: #666;
			display: inline-block;
			text-decoration: underline;
			width: 100%;
			font-size: 15px;
			line-height: 40px;
			text-align: right;
		}
		.r-login:hover {
			opacity: 1;
		}
	}
	.idea-box1 {
		background: none;
		font-weight: 600;
		display: none;
		width: 560px;
		font-size: 20px;
		height: 30px;
		order: -2;
	}
	.idea-box2 {
		margin: 5px 0 40px;
		background: #fff;
		display: none;
		width: 100%;
		font-size: 16px;
		height: 30px;
		order: -1;
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
