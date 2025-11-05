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
	import news from '@/views/modules/news/list'
	import xuesheng from '@/views/modules/xuesheng/list'
	import storeup from '@/views/modules/storeup/list'
	import shujixinxi from '@/views/modules/shujixinxi/list'
	import forum from '@/views/modules/forum/list'
	import chaptershujixinxi from '@/views/modules/chaptershujixinxi/list'
	import forumtype from '@/views/modules/forumtype/list'
	import jiazhang from '@/views/modules/jiazhang/list'
	import renwutijiao from '@/views/modules/renwutijiao/list'
	import popupremind from '@/views/modules/popupremind/list'
	import shujifenlei from '@/views/modules/shujifenlei/list'
	import renwujiangli from '@/views/modules/renwujiangli/list'
	import zuozhe from '@/views/modules/zuozhe/list'
	import forumreport from '@/views/modules/forumreport/list'
	import renwuleixing from '@/views/modules/renwuleixing/list'
	import renwuxinxi from '@/views/modules/renwuxinxi/list'
	import discussshujixinxi from '@/views/modules/discussshujixinxi/list'
	import config from '@/views/modules/config/list'
	import newstype from '@/views/modules/newstype/list'


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
		path: '/news',
		name: '公告资讯',
		component: news
	}
	,{
		path: '/xuesheng',
		name: '学生',
		component: xuesheng
	}
	,{
		path: '/storeup',
		name: '我的收藏',
		component: storeup
	}
	,{
		path: '/shujixinxi',
		name: '书籍信息',
		component: shujixinxi
	}
	,{
		path: '/forum',
		name: '论坛交流',
		component: forum
	}
	,{
		path: '/chaptershujixinxi',
		name: '书籍信息章节',
		component: chaptershujixinxi
	}
	,{
		path: '/forumtype',
		name: '论坛分类',
		component: forumtype
	}
	,{
		path: '/jiazhang',
		name: '家长',
		component: jiazhang
	}
	,{
		path: '/renwutijiao',
		name: '任务提交',
		component: renwutijiao
	}
	,{
		path: '/popupremind',
		name: '弹窗提醒',
		component: popupremind
	}
	,{
		path: '/shujifenlei',
		name: '书籍分类',
		component: shujifenlei
	}
	,{
		path: '/renwujiangli',
		name: '任务奖励',
		component: renwujiangli
	}
	,{
		path: '/zuozhe',
		name: '作者',
		component: zuozhe
	}
	,{
		path: '/forumreport',
		name: '举报记录',
		component: forumreport
	}
	,{
		path: '/renwuleixing',
		name: '任务类型',
		component: renwuleixing
	}
	,{
		path: '/renwuxinxi',
		name: '任务信息',
		component: renwuxinxi
	}
	,{
		path: '/discussshujixinxi',
		name: '书籍信息评论',
		component: discussshujixinxi
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/newstype',
		name: '公告资讯分类',
		component: newstype
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
