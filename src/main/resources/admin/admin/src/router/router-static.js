import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import ceshituandui from '@/views/modules/ceshituandui/list'
	import ceshifenxi from '@/views/modules/ceshifenxi/list'
	import xiufugaijin from '@/views/modules/xiufugaijin/list'
	import ceshiyongli from '@/views/modules/ceshiyongli/list'
	import ceshijingli from '@/views/modules/ceshijingli/list'
	import ceshijihua from '@/views/modules/ceshijihua/list'
	import systemnotice from '@/views/modules/systemnotice/list'
	import menu from '@/views/modules/menu/list'
	import kaifarenyuan from '@/views/modules/kaifarenyuan/list'
	import zhixingrenwu from '@/views/modules/zhixingrenwu/list'
	import xiangmujindu from '@/views/modules/xiangmujindu/list'
	import ziyuanfenpei from '@/views/modules/ziyuanfenpei/list'
	import xiangmujingli from '@/views/modules/xiangmujingli/list'
	import ceshigongchengshi from '@/views/modules/ceshigongchengshi/list'
	import fenxishi from '@/views/modules/fenxishi/list'
	import ceshihuodong from '@/views/modules/ceshihuodong/list'
	import xiangmuxinxi from '@/views/modules/xiangmuxinxi/list'
	import ceshirenwu from '@/views/modules/ceshirenwu/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/ceshituandui',
		name: '测试团队',
		component: ceshituandui
	}
	,{
		path: '/ceshifenxi',
		name: '测试分析',
		component: ceshifenxi
	}
	,{
		path: '/xiufugaijin',
		name: '修复改进',
		component: xiufugaijin
	}
	,{
		path: '/ceshiyongli',
		name: '测试用例',
		component: ceshiyongli
	}
	,{
		path: '/ceshijingli',
		name: '测试经理',
		component: ceshijingli
	}
	,{
		path: '/ceshijihua',
		name: '测试计划',
		component: ceshijihua
	}
	,{
		path: '/systemnotice',
		name: '系统公告',
		component: systemnotice
	}
	,{
		path: '/menu',
		name: '菜单列表',
		component: menu
	}
	,{
		path: '/kaifarenyuan',
		name: '开发人员',
		component: kaifarenyuan
	}
	,{
		path: '/zhixingrenwu',
		name: '执行任务',
		component: zhixingrenwu
	}
	,{
		path: '/xiangmujindu',
		name: '项目进度',
		component: xiangmujindu
	}
	,{
		path: '/ziyuanfenpei',
		name: '资源分配',
		component: ziyuanfenpei
	}
	,{
		path: '/xiangmujingli',
		name: '项目经理',
		component: xiangmujingli
	}
	,{
		path: '/ceshigongchengshi',
		name: '测试工程师',
		component: ceshigongchengshi
	}
	,{
		path: '/fenxishi',
		name: 'QA分析师',
		component: fenxishi
	}
	,{
		path: '/ceshihuodong',
		name: '测试活动',
		component: ceshihuodong
	}
	,{
		path: '/xiangmuxinxi',
		name: '项目信息',
		component: xiangmuxinxi
	}
	,{
		path: '/ceshirenwu',
		name: '测试任务',
		component: ceshirenwu
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
