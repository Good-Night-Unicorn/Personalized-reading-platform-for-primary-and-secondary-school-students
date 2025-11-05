<template>
	<div class="navbar">
		<div class="title">
			<span class="title-name">{{this.$project.projectName}}</span>
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
				<el-dropdown-item class="item4" :command="'logout'">
					<span class="icon iconfont icon-fanhui13"></span>
					退出登录
				</el-dropdown-item>
			</el-dropdown-menu>
		</el-dropdown>
		<el-dialog :title="remindForm.title" :append-to-body="true" :visible.sync="remindVisibie" width="60%">
			<div class="ql-snow ql-editor" v-html="remindForm.content"></div>
		</el-dialog>
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
				remindVisibie: false,
				remindForm: {},
				timesAll: null,
				timesOnce: null,
			};
		},
		created() {
			if (this.$storage.get('sessionTable') != 'users') {
				this.getRecommendAll()
				this.getRecommendOnce()
			}
		},
		computed: {
			avatar(){
				return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
			},
		},
		destroyed() {
			if (this.$storage.get('sessionTable') != 'users') {
				clearTimeout(this.timesAll)
				clearTimeout(this.timesOnce)
			}
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
					if(sessionTable == 'jiazhang') {
						this.$storage.set('headportrait',data.data.touxiang)
					}
					if(sessionTable == 'xuesheng') {
						this.$storage.set('headportrait',data.data.touxiang)
					}
					if(sessionTable == 'zuozhe') {
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
			compareDate(s1, s2) {
				return ((new Date(s1.replace(/-/g, "\/"))) > (new Date(s2.replace(/-/g, "\/"))));
			},
			async getRecommendAll() {
				let that = this
				this.$http({
					url: 'popupremind/list',
					method: 'get',
					params: {
						type: '全局'
					}
				}).then(async res => {
					if (res.data && res.data.code == 0) {
						for (let x in res.data.data.list) {
							if (this.compareDate(this.getCurDateTime(), res.data.data.list[x]
								.remindtime)) {
								await this.$http({
									url: 'storeup/page',
									method: 'get',
									params: {
										refid: res.data.data.list[x].id,
										tablename: 'popupremind',
										type: '61',
									}
								}).then(obj => {
									if (obj.data && obj.data.code == 0) {
										if (!obj.data.data.list.length) {
											res.data.data.list[x].content = res.data.data.list[x].content.replace(/img src/gi,"img style=\"width:100%;\" src");
											this.$notify({
												title: res.data.data.list[x].title,
												dangerouslyUseHTMLString: true,
												message: res.data.data.list[x].brief,
												duration: 0,
												position: 'bottom-right',
												onClick() {
													that.remindForm = res.data.data.list[x]
													that.remindVisibie = true
												}
											})
											this.$http({
												url: 'storeup/add',
												method: 'post',
												data: {
													userid: this.$storage.get('userid'),
													refid: res.data.data.list[x].id,
													tablename: 'popupremind',
													name: res.data.data.list[x].title,
													type: '61',
												}
											})
										}
									}
								})
							}
						}
						this.timesAll = setTimeout(() => {
							this.getRecommendAll()
						}, 10000)
					}
				})
			},
			async getRecommendOnce() {
				let that = this
				this.$http({
					url: 'popupremind/page',
					method: 'get',
					params: {
						type: '个人'
					}
				}).then(async res => {
					if (res.data && res.data.code == 0) {
						for (let x in res.data.data.list) {
							if (this.compareDate(this.getCurDateTime(), res.data.data.list[x]
								.remindtime)) {
								await this.$http({
									url: 'storeup/page',
									method: 'get',
									params: {
										refid: res.data.data.list[x].id,
										tablename: 'popupremind',
										type: '61',
									}
								}).then(obj => {
									if (obj.data && obj.data.code == 0) {
										if (!obj.data.data.list.length) {
											res.data.data.list[x].content = res.data.data.list[x].content.replace(/img src/gi,"img style=\"width:100%;\" src");
											this.$notify({
												title: res.data.data.list[x].title,
												dangerouslyUseHTMLString: true,
												message: res.data.data.list[x].brief,
												duration: 0,
												position: 'top-right',
												onClick() {
													that.remindForm = res.data.data.list[x]
													that.remindVisibie = true
												}
											})
											this.$http({
												url: 'storeup/add',
												method: 'post',
												data: {
													userid: this.$storage.get('userid'),
													refid: res.data.data.list[x].id,
													tablename: 'popupremind',
													name: res.data.data.list[x].title,
													type: '61',
												}
											})
										}
									}
								})
							}
						}
						this.timesOnce = setTimeout(() => {
							this.getRecommendOnce()
						}, 10000)
					}
				})
			},
			handleCommand(name) {
				if (name == 'logout') {
					this.onLogout()
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
				localStorage.setItem("frontToken", localStorage.getItem("Token"));
				localStorage.setItem("frontRole", localStorage.getItem("role"));
				localStorage.setItem("frontSessionTable", localStorage.getItem("sessionTable"));
				localStorage.setItem("frontHeadportrait", localStorage.getItem("headportrait"));
				localStorage.setItem("UserTableName", localStorage.getItem("sessionTable"));
				localStorage.setItem("frontUserid", localStorage.getItem("userid"));
				localStorage.setItem("username", localStorage.getItem("adminName"));
				window.location.href = `${this.$base.indexUrl}`
			},
		}
	};
</script>


<style lang="scss" scoped>
	.navbar {
		.title {
			top: 13px;
			left: 250px;
			display: block;
			position: absolute;
			.title-name {
				padding: 0;
				color: #1a3194;
				font-weight: 600;
				font-size: 18px;
				line-height: 44px;
				float: left;
			}
		}
		.dropdown-box {
			color: inherit;
			display: flex;
			font-size: inherit;
			right: 0px;
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
