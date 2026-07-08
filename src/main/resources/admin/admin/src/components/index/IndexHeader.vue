<template>
	<div class="navbar">
		<div class="title">
			<span class="title-name">{{this.$project.projectName}}</span>
		</div>
		<!-- 天气 -->
		<div class="weather" v-if="weather.day">
			<div>{{weather.city}}</div>
			<div>{{weather.day.tem}}°</div>
			<div>{{weather.day.wea}}</div>
			<div>{{weather.day.win}}</div>
			<div>{{weather.day.win_speed}}</div>
		</div>
		<!-- 时间 -->
		<div class="times">{{times}}</div>
		<!-- 系统通知-2 -->
		<div id="div1">
			<div>公告</div>
			<ul>
				<li class="ql-snow ql-editor" v-html="noticeDetail.content"></li>
			</ul>
		</div>
		<el-dropdown class="dropdown-box" @command="handleCommand" trigger="click">
			<div class="el-dropdown-link">
				<el-image v-if="user" :src="avatar?this.$base.url + avatar : require('@/assets/img/avator.png')" fit="cover"></el-image>
				<span class="label">欢迎您，</span>
				<span class="nickname">{{this.$storage.get('adminName')}}</span>
				<span class="icon iconfont icon-xiala"></span>
			</div>
			<el-dropdown-menu class="top-el-dropdown-menu" slot="dropdown">
				<el-dropdown-item class="item1" :command="''">
					<span class="icon iconfont icon-home19"></span>
					首页
				</el-dropdown-item>
				<el-dropdown-item class="item2" :command="'center'">
					<span class="icon iconfont icon-touxiang03"></span>
					个人中心
				</el-dropdown-item>
				<el-dropdown-item v-if="this.$storage.get('role')=='管理员'" class="item3" :command="'backUp'">
					<span class="icon iconfont icon-fanhui12"></span>
					数据备份
				</el-dropdown-item>
				<el-dropdown-item class="item4" :command="'logout'">
					<span class="icon iconfont icon-fanhui13"></span>
					退出登录
				</el-dropdown-item>
			</el-dropdown-menu>
		</el-dropdown>
	</div>
</template>

<script>
	import {
		Loading
	} from 'element-ui';
	import axios from 'axios';
	export default {
		data() {
			return {
				dialogVisible: false,
				ruleForm: {},
				user: null,
				// 时间
				times: '',
				// 天气
				weather: {},
				// 系统公告
				noticeDetail: {},
			};
		},
		created() {
			this.$nextTick(() => {
				// 获取时间
				this.setTimes()
			})
			// 获取天气
			this.getWeather()
			// 系统公告
			this.getNotice()
		},
		computed: {
			avatar(){
				return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
			},
		},
		mounted() {
			let sessionTable = this.$storage.get("sessionTable")
			this.$http({
				url: sessionTable + '/session',
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code === 0) {
					if(sessionTable == 'xiangmujingli') {
						this.$storage.set('headportrait',data.data.touxiang)
					}
					if(sessionTable == 'ceshijingli') {
						this.$storage.set('headportrait',data.data.touxiang)
					}
					if(sessionTable == 'users') {
						this.$storage.set('headportrait',data.data.image)
					}
					this.$storage.set('userForm',JSON.stringify(data.data))
					this.user = data.data;
					this.$storage.set('userid',data.data.id);
				} else {
					let message = this.$message
					message.error(data.msg);
				}
			});
		},
		methods: {
			// 获取当前时间
			setTimes() {
				setInterval(()=>{
					let d = new Date()
					this.times = d.getFullYear() + '-' + ((d.getMonth() + 1)<10?('0' + (d.getMonth() + 1)):(d.getMonth() + 1)) + '-' + (d.getDate()<10?('0' + d.getDate()):d.getDate()) + ' ' + (d.getHours()<10?('0' + d.getHours()):d.getHours()) + ':' + (d.getMinutes()<10?('0' + d.getMinutes()):d.getMinutes()) + ':' + (d.getSeconds()<10?('0' + d.getSeconds()):d.getSeconds())
				},1000)
			},
			// 获取当前城市天气
			getWeather(){
				axios({
					method: 'get',
					url: 'http://gfeljm.tianqiapi.com/free/v2031?appid=69475998&appsecret=rldbX1Zl'
				}).then(res => {
					this.weather = res.data
				})
			},
			// 获取系统公告
			getNotice() {
				this.$http({
					url: '/systemnotice/detail/1',
					method: "get"
				}).then(({data})=>{
					if ( data && data.code==0 ) {
						this.noticeDetail = data.data
						this.$nextTick(() => {
							this.notice()
						})
					}
				})
			},
			notice() {
				var oDiv = document.getElementById('div1');
				var oUl = oDiv.getElementsByTagName('ul')[0];
				var aLi = oUl.getElementsByTagName('li');
				var speed = 5;
				oUl.innerHTML = oUl.innerHTML + oUl.innerHTML;
				oUl.style.width = aLi[0].offsetWidth * aLi.length + 'px';
				
				function move() {
					if (oUl.offsetLeft < -oUl.offsetWidth / 2) { //因为第一个offsetLeft可能是负数，所以要加一个负号
						oUl.style.left = '0';
					}
					if (oUl.offsetLeft > 0) {
						oUl.style.left = -oUl.offsetWidth / 2 + 'px';
					}
					oUl.style.left = oUl.offsetLeft - speed + 'px';
				}
				this.timer = setInterval(move, 100);
			
				oDiv.addEventListener('mouseenter', e => {
					e.stopPropagation()
					clearInterval(this.timer);
				})
				oDiv.addEventListener('mouseleave', e => {
					e.stopPropagation()
					this.timer = setInterval(move, 100);
				})
			},
			handleCommand(name) {
				if (name == 'logout') {
					this.onLogout()
				} 
				else if (name == 'backUp'){
					this.backUp()
				}
				else {
					let router = this.$router
					name = '/'+name
					router.push(name)
				}
			},
			onLogout() {
				let storage = this.$storage
				let router = this.$router
				storage.clear()
				this.$store.dispatch('tagsView/delAllViews')
				router.replace({
					name: "login"
				});
			},
			onIndexTap(){
				window.location.href = `${this.$base.indexUrl}`
			},
			backUp() {
				this.$confirm('是否备份数据库?', '数据备份提示', {
					confirmButtonText: '是',
					cancelButtonText: '否',
					type: 'warning'
				}).then(() => {
					this.$http({
						url: '/mysqldump',
						method: "get"
					}).then(({
						data
					}) => {
						if (data) {
							const binaryData = [];
							binaryData.push(data);
							const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
								type: 'application/pdf;chartset=UTF-8'
							}))
							const a = document.createElement('a')
							a.href = objectUrl
							a.download = 'mysql.dmp'
							// a.click()
							// 下面这个写法兼容火狐
							a.dispatchEvent(new MouseEvent('click', {
								bubbles: true,
								cancelable: true,
								view: window
							}))
							window.URL.revokeObjectURL(data)
							message.message("数据备份成功")
						} else {
							let message = this.$message
							message.error(data.msg);
						}
					});
				});
			},
		}
	};
</script>


<style lang="scss" scoped>
	.navbar {
		.title {
			top: 10px;
			left: 40px;
			display: block;
			position: absolute;
			.title-name {
				padding: 0;
				color: #FFFFFF;
				font-weight: 400;
				display: none;
				font-size: 18px;
				line-height: 44px;
				float: left;
			}
		}
		.weather {
			padding: 0;
			margin: 0 0 0 34%;
			color: inherit;
			display: flex;
			font-size: inherit;
			justify-content: center;
			align-items: center;
			order: 2;
			div:nth-child(1) {
				padding: 0 4px;
				color: inherit;
				font-size: inherit;
				line-height: 44px;
			}
			div:nth-child(2) {
				padding: 0 4px;
				color: inherit;
				font-size: inherit;
				line-height: 44px;
			}
			div:nth-child(3) {
				padding: 0 4px;
				color: inherit;
				font-size: inherit;
				line-height: 44px;
			}
			div:nth-child(4) {
				padding: 0 4px;
				color: inherit;
				font-size: inherit;
				line-height: 44px;
			}
			div:nth-child(5) {
				padding: 0 4px;
				color: inherit;
				font-size: inherit;
				line-height: 44px;
			}
		}
		.times {
			padding: 0;
			margin: 0 100px 0 0;
			color: inherit;
			font-size: inherit;
			line-height: 30px;
			order: 3;
		}
		#div1 {
			margin: 0 50px  0 0;
			overflow: hidden;
			background: none;
			width: 300px;
			position: relative;
			height: 40px;
			order: 1;
			div {
				padding: 0 10px 0 40px;
				z-index: 999;
				color: #fff;
				background: url(http://codegen.caihongy.cn/20240924/df77948f469246f5b65ec9a3949ade80.png) no-repeat left center / 30px,#FFC144;
				display: inline-block;
				font-size: 16px;
				line-height: 40px;
				position: relative;
			}
			ul {
				top: 0;
				left: 80px;
				position: absolute;
				
				li {
					color: #FFFFFF;
					width: 400px;
					font-size: 16px;
					line-height: 40px;
					float: left;
					list-style: none;
				}
			}
		}
		.dropdown-box {
			color: inherit;
			display: flex;
			width: 200px;
			font-size: inherit;
			right: 20px;
			order: 4;
			.el-dropdown-link {
				display: flex;
				align-items: center;
				.el-image {
					border-radius: 100%;
					margin: 0 10px;
					object-fit: cover;
					display: inline-block;
					width: 42px;
					height: 42px;
				}
				.label {
					color: inherit;
					display: none;
					font-size: inherit;
					line-height: 32px;
				}
				.nickname {
					color: inherit;
					display: none;
					font-size: inherit;
					line-height: 32px;
				}
				.iconfont {
					margin: 0 0 0 5px;
					color: rgba(255,255,255,.6);
					font-size: 14px;
				}
			}
			.top-el-dropdown-menu {
				border: 1px solid #EBEEF5;
				border-radius: 4px;
				padding: 10px 0;
				box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
				margin: 18px 0;
				background: #FFF;
				li.el-dropdown-menu__item.item1 {
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					outline: 0;
					color: #606266;
					background: #fff;
					font-size: 14px;
					line-height: 36px;
					list-style: none;
					.iconfont {
						margin: 0 4px 0 0;
							color: #000;
							font-size: 14px;
						}
				}
				li.el-dropdown-menu__item.item1:hover {
					background: #ecf5ff;
				}
				li.el-dropdown-menu__item.item2 {
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					outline: 0;
					color: #606266;
					background: #fff;
					font-size: 14px;
					line-height: 36px;
					list-style: none;
					.iconfont {
						margin: 0 4px 0 0;
							color: #000;
							font-size: 14px;
						}
				}
				li.el-dropdown-menu__item.item2:hover {
					background: #ecf5ff;
				}
				li.el-dropdown-menu__item.item3 {
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					outline: 0;
					color: #606266;
					background: #fff;
					font-size: 14px;
					line-height: 36px;
					list-style: none;
					.iconfont {
						margin: 0 4px 0 0;
						color: #000;
						font-size: 14px;
					}
				}
				li.el-dropdown-menu__item.item3:hover {
					background: #ecf5ff;
				}
				li.el-dropdown-menu__item.item4 {
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #606266;
					background: #fff;
					font-size: 14px;
					line-height: 36px;
					list-style: none;
					.iconfont {
						margin: 0 4px 0 0;
						color: #000;
						font-size: 14px;
					}
				}
				li.el-dropdown-menu__item.item4:hover {
					background: #ecf5ff;
				}
			}
		}
	}
</style>
