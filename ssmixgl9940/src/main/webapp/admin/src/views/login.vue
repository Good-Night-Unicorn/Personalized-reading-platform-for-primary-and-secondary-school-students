<template>
	<div>
		<div class="login-container">
			<el-form class="login_form animate__animated animate__">
				<div class="login_form2">
					<div class="title-container">基于JAVA的中小学生个性化阅读平台的设计与实现</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							账号：
						</div>
						<input placeholder="请输入账号：" name="username" type="text" v-model="rulesForm.username">
					</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							密码：
						</div>
						<div class="password-box">
							<input placeholder="请输入密码：" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item " v-if="roles.length>1">
						<div class="lable">
							角色：
						</div>
						<div prop="loginInRole" class="list-type">
							<el-radio v-if="loginType==1||(loginType==2&&item.roleName!='管理员')" v-for="item in roles" v-bind:key="item.roleName" v-model="rulesForm.role" :label="item.roleName">{{item.roleName}}</el-radio>
						</div>
					</div>

		
					<div class="login-btn">
						<div class="login-btn1">
							<el-button v-if="loginType==1" type="primary" @click="login()" class="loginInBt">登录</el-button>
						</div>
						<div class="login-btn2">
							<el-button type="primary" @click="register('zuozhe')" class="register">
								注册作者							</el-button>
						</div>
						<div class="login-btn3">
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script>
	import 'animate.css'
	import menu from "@/utils/menu";
	export default {
		data() {
			return {
				verifyCheck2: false,
				flag: false,
				baseUrl:this.$base.url,
				loginType: 1,
				rulesForm: {
					username: "",
					password: "",
					role: "",
				},
				menus: [],
				roles: [],
				tableName: "",
				showPassword: false,
			};
		},
		mounted() {
			let menus = menu.list();
			this.menus = menus;

			for (let i = 0; i < this.menus.length; i++) {
				if (this.menus[i].hasBackLogin=='是') {
					this.roles.push(this.menus[i])
				}
			}

		},
		created() {

		},
		destroyed() {
		},
		components: {
		},
		methods: {

			//注册
			register(tableName){
				this.$storage.set("loginTable", tableName);
				this.$router.push({path:'/register',query:{pageFlag:'register'}})
			},
			// 登陆
			login() {

				if (!this.rulesForm.username) {
					this.$message.error("请输入用户名");
					return;
				}
				if (!this.rulesForm.password) {
					this.$message.error("请输入密码");
					return;
				}
				if(this.roles.length>1) {
					if (!this.rulesForm.role) {
						this.$message.error("请选择角色");
						return;
					}

					let menus = this.menus;
					for (let i = 0; i < menus.length; i++) {
						if (menus[i].roleName == this.rulesForm.role) {
							this.tableName = menus[i].tableName;
						}
					}
				} else {
					this.tableName = this.roles[0].tableName;
					this.rulesForm.role = this.roles[0].roleName;
				}
		
				this.loginPost()
			},
			loginPost() {
				this.$http({
					url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
					method: "post"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", this.rulesForm.username);
						this.$router.replace({ path: "/" });
					} else {
						this.$message.error(data.msg);
					}
				});
			},
		}
	}
</script>

<style lang="scss" scoped>
.login-container {
	min-height: 100vh;
	position: relative;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20240923/485f888d6fd7472b921f33d2bc986e70.jpg) no-repeat center center / 100% 100%;
	background: url(http://codegen.caihongy.cn/20240923/485f888d6fd7472b921f33d2bc986e70.jpg) no-repeat center center / 100% 100%;
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: flex-end;
	align-items: center;
	position: relative !important;

	.login_form {
		border-radius: 0;
		padding: 0 6%;
		margin: 0;
		z-index: 1000;
		background: #dee9ff;
		display: flex;
		width: 43%;
		min-height: 100vh;
		align-items: center;
		position: relative;
		flex-wrap: wrap;
		height: auto;
		.login_form2 {
			width: 100%;
		}
		.title-container {
			padding: 0;
			margin: 0 0 20px 0;
			color: #36527d;
			background: none;
			font-weight: 600;
			width: 100%;
			font-size: 24px;
			line-height: 40px;
			text-align: left;
		}
		.list-item {
			padding: 0;
			margin: 0 0 20px;
			display: flex;
			width: calc(100% - 0px);
			border-color: #36527d;
			border-width: 0 0 2px;
			align-items: center;
			position: relative;
			border-style: solid;
			flex-wrap: wrap;
			.lable {
				color: #000;
				width: auto;
				font-size: 16px;
				line-height: 30px;
				min-width: 60px;
				text-align: left;
			}
			input {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				flex: 1;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			input:focus {
				border: 0px solid #e6e6e6;
				padding: 0 10px;
				color: #666;
				flex: 1;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			.password-box {
				flex: 1;
				display: flex;
				width: 100%;
				position: relative;
				align-items: center;
				input {
					border: 0px solid #e6e6e6;
					padding: 0 10px;
					color: #666;
					background: none;
					width: 100%;
					font-size: 14px;
					height: 44px;
				}
				input:focus {
					border: 0px solid #e6e6e6;
					padding: 0 10px;
					color: #666;
					background: none;
					width: 100%;
					font-size: 14px;
					height: 44px;
				}
				.iconfont {
					cursor: pointer;
					z-index: 1;
					color: #000;
					top: 0;
					font-size: 16px;
					line-height: 44px;
					position: absolute;
					right: 5px;
				}
			}
			input::placeholder {
				color: #8890a0;
				font-size: 16px;
			}
		}
		.list-type {
			padding: 0 0 0 15px;
			margin: 0;
			background: none;
			flex: 1;
			width: calc(100% - 0px);
			line-height: 40px;
			height: auto;
			/deep/ .el-radio__input .el-radio__inner {
				background: rgba(53, 53, 53, 0);
				border-color: #666;
			}
			/deep/ .el-radio__input.is-checked .el-radio__inner {
				background: #1a3194;
				border-color: #1a3194;
			}
			/deep/ .el-radio__label {
				color: #666;
				font-size: 16px;
			}
			/deep/ .el-radio__input.is-checked+.el-radio__label {
				color: #1a3194;
				font-size: 16px;
			}
		}
		.login-btn {
			margin: 20px auto 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			.login-btn1 {
				width: 100%;
				text-align: center;
				order: 3;
			}
			.login-btn2 {
				margin: 0px 0 0;
				display: flex;
				width: 100%;
				align-items: center;
				flex-wrap: wrap;
			}
			.login-btn3 {
				width: 100%;
			}
			.loginInBt {
				border: 15px solid #152981;
				cursor: pointer;
				border-radius: 100%;
				padding: 0 10px;
				margin: 0 auto 10px;
				color: #fff;
				background: #1a3194;
				font-weight: 600;
				width: 130px;
				font-size: 22px;
				min-width: 68px;
				height: 130px;
			}
			.loginInBt:hover {
				opacity: 0.8;
			}
			.register {
				border: 0px solid rgba(0, 0, 0, 1);
				cursor: pointer;
				border-radius: 0px;
				padding: 0;
				margin: 0 20px 10px 0;
				color: #0e72fc;
				background: none;
				width: auto;
				font-size: 16px;
				height: 34px;
			}
			.register:hover {
				opacity: 0.8;
			}
			.forget {
				border: 0px solid #FC4349;
				cursor: pointer;
				border-radius: 0;
				padding: 0 0px;
				margin: 0 0px 0px 0;
				color: #333;
				background: none;
				width: auto;
				font-size: 16px;
				float: right;
				text-align: right;
				height: 34px;
			}
			.forget:hover {
				opacity: 1;
			}
		}
	}
}

</style>
