<template>
	<div class="main-content" :style='{"padding":"20px 30px","margin":"0 0 0 30px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"margin":"0 10px 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"padding":"10px","flexWrap":"wrap","background":"none","display":"flex"}' >
					<div :style='{"margin":"0 10px 0 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","display":"block","width":"auto","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">项目名称</label>
						<el-input v-model="searchForm.xiangmumingcheng" placeholder="项目名称" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 0 0","display":"flex"}' class="select" label="团队名称" prop="tuanduimingcheng">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","textAlign":"right","display":"block","width":"auto","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">团队名称</label>
						<el-select clearable v-model="searchForm.tuanduimingcheng" placeholder="请选择团队名称" >
							<el-option v-for="(item,index) in tuanduimingchengOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"padding":"10px","margin":"0","flexWrap":"wrap","background":"none","display":"flex"}'>
					<el-button class="add" v-if="isAuth('ceshijihua','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia14" :style='{"margin":"0 2px","fontSize":"14px","color":"#FFFFFF","height":"34px"}'></span>
						添加
					</el-button>
					<el-button class="del" v-if="isAuth('ceshijihua','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
						删除
					</el-button>


				</el-row>
			</el-form>
			<div :style='{"width":"100%","padding":"0 10px","fontSize":"14px","color":"#000"}'>
				<el-table class="tables"
					:stripe='true'
					:style='{"padding":"0 ","borderColor":"#eee","borderRadius":"20px 20px 0 0","borderWidth":"1px 0 0 1px","background":"#F3F3F3","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('ceshijihua','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="xiangmubianhao"
						label="项目编号">
						<template slot-scope="scope">
							{{scope.row.xiangmubianhao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="xiangmumingcheng"
						label="项目名称">
						<template slot-scope="scope">
							{{scope.row.xiangmumingcheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="xiangmuleixing"
						label="项目类型">
						<template slot-scope="scope">
							{{scope.row.xiangmuleixing}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' prop="jihuafujian" label="计划附件">
						<template slot-scope="scope">
							<el-button v-if="scope.row.jihuafujian" type="text" size="small" @click="download(scope.row.jihuafujian)">下载</el-button>
							<span v-else >无</span>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jihuashijian"
						label="计划时间">
						<template slot-scope="scope">
							{{scope.row.jihuashijian}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jinglizhanghao"
						label="经理账号">
						<template slot-scope="scope">
							{{scope.row.jinglizhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jinglixingming"
						label="经理姓名">
						<template slot-scope="scope">
							{{scope.row.jinglixingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="tuanduimingcheng"
						label="团队名称">
						<template slot-scope="scope">
							{{scope.row.tuanduimingcheng}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('ceshijihua','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="btn8" v-if="isAuth('ceshijihua','测试任务')" @click="ceshirenwuCrossAddOrUpdateHandler(scope.row,'cross','','','','')" type="success">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								测试任务
							</el-button>
							<el-button class="edit" v-if=" isAuth('ceshijihua','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								修改
							</el-button>




							<el-button class="del" v-if="isAuth('ceshijihua','删除') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="上一页 "
				next-text="下一页 "
				:hide-on-single-page="false"
				:style='{"padding":"0","margin":"20px auto 0","whiteSpace":"nowrap","color":"#333","alignItems":"center","textAlign":"center","background":"#fff","display":"flex","width":"60%","fontSize":"inherit","fontWeight":"500","justifyContent":"center"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

		<ceshirenwu-cross-add-or-update v-if="ceshirenwuCrossAddOrUpdateFlag" :parent="this" ref="ceshirenwuCrossaddOrUpdate"></ceshirenwu-cross-add-or-update>




	</div>
</template>

<script>
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import ceshirenwuCrossAddOrUpdate from "../ceshirenwu/add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				tuanduimingchengOptions: [],
				searchForm: {
					key: ""
				},
				form:{},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				addOrUpdateFlag:false,
				ceshirenwuCrossAddOrUpdateFlag: false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange();
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
			ceshirenwuCrossAddOrUpdate,
		},
		methods: {
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},
			ceshirenwuCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
				this.showFlag = false;
				this.addOrUpdateFlag = false;
				this.ceshirenwuCrossAddOrUpdateFlag = true;
				this.$storage.set('crossObj',row);
				this.$storage.set('crossTable','ceshijihua');
				this.$storage.set('statusColumnName',statusColumnName);
				this.$storage.set('statusColumnValue',statusColumnValue);
				this.$storage.set('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								message: tips,
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList();
								}
							});
							this.showFlag = true;
							this.ceshirenwuCrossAddOrUpdateFlag = false;
							return;
						}
					}
				}
				this.$nextTick(() => {
					this.$refs.ceshirenwuCrossaddOrUpdate.init(row.id,type);
				});
			},





			init () {
				this.$http({
					url: `option/ceshituandui/tuanduimingcheng`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.tuanduimingchengOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
 				if(this.searchForm.xiangmumingcheng!='' && this.searchForm.xiangmumingcheng!=undefined){
					params['xiangmumingcheng'] = '%' + this.searchForm.xiangmumingcheng + '%'
				}
				if(this.searchForm.tuanduimingcheng!='' && this.searchForm.tuanduimingcheng!=undefined){
					params['tuanduimingcheng'] = this.searchForm.tuanduimingcheng
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				if(user.tuanduimingcheng){
					params['tuanduimingcheng'] = user.tuanduimingcheng
				}
				this.$http({
					url: "ceshijihua/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'&&type!='msg'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type);
				});
			},
			// 下载
			download(file){
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.$base.url + 'file/download?fileName=' + arr, {
					headers: {
						token: this.$storage.get('Token')
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] :'') + this.$base.name + '/file/download?fileName=' + arr, {
						headers: {
							token: this.$storage.get('Token')
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			// 删除
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "ceshijihua/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


		}

	};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		width: 100%;
	}
	.center-form-pv .el-input /deep/ .el-input__inner {
		border: 1px dashed  #a7a7a7;
		border-radius: 0px;
		padding: 0 5px;
		color: #666;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select /deep/ .el-input__inner {
		border: 1px dashed  #a7a7a7;
		border-radius: 0px;
		padding: 0 5px;
		color: #666;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
		border: 1px dashed  #a7a7a7;
		border-radius: 0px;
		padding: 0 5px 0 30px;
		color: #666;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		color: #fff;
		background: #FFC144;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		margin: 0 5px 5px 5px;
		color: #fff;
		background: #FFC144;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .actions .add:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .del {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		margin: 0 5px 5px 5px;
		color: #fff;
		background: #FFC144;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .actions .del:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .statis {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		color: #fff;
		background: #FFC144;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .actions .statis:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .btn18 {
		border: 0;
		cursor: pointer;
		border-radius: 5px;
		margin: 0 5px 5px 5px;
		color: #fff;
		background: #FFC144;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .actions .btn18:hover {
		opacity: 0.8;
	}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
		padding: 0 20px;
		color: #000000;
		font-weight: 400;
		width: 100%;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
		background: none;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
		background: none;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 5px;
		word-wrap: normal;
		color: #000000;
		white-space: normal;
		font-weight: bold;
		display: flex;
		vertical-align: middle;
		font-size: 14px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		align-items: center;
		position: relative;
		min-width: 110px;
	}

	.el-table /deep/ .el-table__body-wrapper {
	}
	.el-table /deep/ .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
		background: #fff;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #9E9E9E;
		background: #fff;
		font-size: inherit;
		border-color: #9E9E9E;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
		.el-table /deep/ .el-table__body-wrapper tbody tr.el-table__row--striped td {
		background: #f8f8f8;
	}
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #9E9E9E;
		background: #fff;
		font-size: inherit;
		border-color: #9E9E9E;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 5px;
		overflow: hidden;
		word-break: break-all;
		white-space: normal;
		font-size: inherit;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
		border: 0px solid #157ed2;
		cursor: pointer;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		background-size: cover;
		color: #fff;
		font-size: 14px;
		background-color: #fff;
		border-radius: 2px;
		background-repeat: no-repeat;
		background-image: url(http://codegen.caihongy.cn/20240924/c6c9fae0b42f4d529338cb5e879ca352.png) ;
		min-width: 84px;
		height: 33px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
		border: 0px solid #157ed2;
		cursor: pointer;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		background-size: cover;
		color: #fff;
		font-size: 14px;
		background-color: #fff;
		border-radius: 2px;
		background-repeat: no-repeat;
		background-image: url(http://codegen.caihongy.cn/20240924/c6c9fae0b42f4d529338cb5e879ca352.png) ;
		min-width: 84px;
		height: 33px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
		border: 0px solid #157ed2;
		cursor: pointer;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		background-size: cover;
		color: #fff;
		font-size: 14px;
		background-color: #fff;
		border-radius: 2px;
		background-repeat: no-repeat;
		background-image: url(http://codegen.caihongy.cn/20240924/c6c9fae0b42f4d529338cb5e879ca352.png) ;
		min-width: 84px;
		height: 33px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
		border: 0px solid #157ed2;
		cursor: pointer;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		background-size: cover;
		color: #fff;
		font-size: 14px;
		background-color: #fff;
		border-radius: 2px;
		background-repeat: no-repeat;
		background-image: url(http://codegen.caihongy.cn/20240924/c6c9fae0b42f4d529338cb5e879ca352.png) ;
		min-width: 84px;
		height: 33px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: block;
		vertical-align: top;
		font-size: inherit;
		line-height: 48px;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-next {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 48px;
	}

	.main-content .el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
	}

	.main-content .el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		border-radius: 2px;
		padding: 0 20px;
		margin: 0;
		color: #000;
		background: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 62px;
		text-align: center;
		height: 56px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		border-radius: 2px;
		padding: 0 20px;
		margin: 0;
		color: #fff;
		background: #FFC144;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 62px;
		text-align: center;
		height: 56px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
		cursor: default;
		border-radius: 2px;
		padding: 0 20px;
		margin: 0;
		color: #fff;
		background: #FFC144;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 62px;
		text-align: center;
		height: 56px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 100%;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 48px;
		text-align: center;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: block;
		vertical-align: top;
		font-size: 16px;
		line-height: 100%;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	// list one
	.one .list1-view {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #157ed2;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-view:hover {
		opacity: 0.8;
	}
	
	.one .list1-edit {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #409eff;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-edit:hover {
		opacity: 0.8;
	}
	
	.one .list1-del {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: rgba(255, 0, 0, 1);
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-del:hover {
		opacity: 0.8;
	}
	
	.one .list1-btn8 {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 24px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: rgba(255, 128, 0, 1);
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-btn8:hover {
		opacity: 0.8;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
		border: 1px solid #75c0d6;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		background: #75c0d6;
		display: inline-block;
		width: 42px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 20px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 1px;
		background: #fff;
		width: 16px;
		position: absolute;
		transition: all .3s;
		height: 16px;
	}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
		margin: 0 0 0 -18px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

</style>
