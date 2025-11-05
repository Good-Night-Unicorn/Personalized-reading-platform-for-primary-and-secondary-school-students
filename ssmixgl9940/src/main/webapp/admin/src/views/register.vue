<template>
	<div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于JAVA的中小学生个性化阅读平台的设计与实现</div>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('yonghuming')?'required':''">用户名：</div>
						<el-input  v-model="ruleForm.yonghuming"  autocomplete="off" placeholder="用户名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('jiazhangxingming')?'required':''">家长姓名：</div>
						<el-input  v-model="ruleForm.jiazhangxingming"  autocomplete="off" placeholder="家长姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="jiazhangtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in jiazhangxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('youxiang')?'required':''">邮箱：</div>
						<el-input  v-model="ruleForm.youxiang"  autocomplete="off" placeholder="邮箱"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jiazhang'">
						<div class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
						<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xuehao')?'required':''">学号：</div>
						<el-input  v-model="ruleForm.xuehao"  autocomplete="off" placeholder="学号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xingming')?'required':''">姓名：</div>
						<el-input  v-model="ruleForm.xingming"  autocomplete="off" placeholder="姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in xueshengxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="xueshengtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('youxiang')?'required':''">邮箱：</div>
						<el-input  v-model="ruleForm.youxiang"  autocomplete="off" placeholder="邮箱"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
						<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('yonghuming')?'required':''">用户名：</div>
						<el-select @change="xueshengyonghumingChange" v-model="ruleForm.yonghuming" placeholder="请选择用户名" >
							<el-option
								v-for="(item,index) in xueshengyonghumingOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='xuesheng'">
						<div class="lable" :class="changeRules('jiazhangxingming')?'required':''">家长姓名：</div>
						<el-input  v-model="ruleForm.jiazhangxingming" readonly autocomplete="off" placeholder="家长姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('zuozhezhanghao')?'required':''">作者账号：</div>
						<el-input  v-model="ruleForm.zuozhezhanghao"  autocomplete="off" placeholder="作者账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('zuozhexingming')?'required':''">作者姓名：</div>
						<el-input  v-model="ruleForm.zuozhexingming"  autocomplete="off" placeholder="作者姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="zuozhetouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in zuozhexingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('youxiang')?'required':''">邮箱：</div>
						<el-input  v-model="ruleForm.youxiang"  autocomplete="off" placeholder="邮箱"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='zuozhe'">
						<div class="lable" :class="changeRules('shouji')?'required':''">手机：</div>
						<el-input  v-model="ruleForm.shouji"  autocomplete="off" placeholder="手机"  type="text"  />
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
            jiazhangxingbieOptions: [],
            xueshengxingbieOptions: [],
            xueshengyonghumingOptions: [],
            zuozhexingbieOptions: [],
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='jiazhang'){
				this.ruleForm = {
					yonghuming: '',
					mima: '',
					jiazhangxingming: '',
					touxiang: '',
					xingbie: '',
					nianling: '',
					youxiang: '',
					shouji: '',
				}
			}
			if(this.tableName=='xuesheng'){
				this.ruleForm = {
					xuehao: '',
					mima: '',
					xingming: '',
					xingbie: '',
					touxiang: '',
					youxiang: '',
					shouji: '',
					yonghuming: '',
					jiazhangxingming: '',
				}
			}
			if(this.tableName=='zuozhe'){
				this.ruleForm = {
					zuozhezhanghao: '',
					mima: '',
					zuozhexingming: '',
					touxiang: '',
					xingbie: '',
					nianling: '',
					youxiang: '',
					shouji: '',
					status: '',
				}
			}
			if ('jiazhang' == this.tableName) {
				this.rules.yonghuming = [{ required: true, message: '请输入用户名', trigger: 'blur' }]
			}
			if ('jiazhang' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('jiazhang' == this.tableName) {
				this.rules.jiazhangxingming = [{ required: true, message: '请输入家长姓名', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.xuehao = [{ required: true, message: '请输入学号', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.xingming = [{ required: true, message: '请输入姓名', trigger: 'blur' }]
			}
			if ('xuesheng' == this.tableName) {
				this.rules.yonghuming = [{ required: true, message: '请输入用户名', trigger: 'blur' }]
			}
			if ('zuozhe' == this.tableName) {
				this.rules.zuozhezhanghao = [{ required: true, message: '请输入作者账号', trigger: 'blur' }]
			}
			if ('zuozhe' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('zuozhe' == this.tableName) {
				this.rules.zuozhexingming = [{ required: true, message: '请输入作者姓名', trigger: 'blur' }]
			}
			if ('zuozhe' == this.tableName) {
				this.rules.youxiang = [{ required: true, message: '请输入邮箱', trigger: 'blur' }]
			}
			if ('zuozhe' == this.tableName) {
				this.rules.shouji = [{ required: true, message: '请输入手机', trigger: 'blur' }]
			}
			this.jiazhangxingbieOptions = "男,女".split(',')
			this.xueshengxingbieOptions = "男,女".split(',')
			this.$http({
				url: `option/jiazhang/yonghuming`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.xueshengyonghumingOptions = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.zuozhexingbieOptions = "男,女".split(',')
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
        jiazhangtouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        xueshengtouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        // 下二随
        xueshengyonghumingChange () {
            this.$http({
                url: `follow/jiazhang/yonghuming?columnValue=`+ this.ruleForm.yonghuming,
                method: "get"
            }).then(({ data }) => {
                if (data && data.code === 0) {
                    if(data.data.jiazhangxingming){
                        this.ruleForm.jiazhangxingming = data.data.jiazhangxingming
                    }
                } else {
                    this.$message.error(data.msg);
                }
            });
        },
        zuozhetouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.yonghuming) && `jiazhang` == this.tableName){
				this.$message.error(`用户名不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `jiazhang` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `jiazhang` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.jiazhangxingming) && `jiazhang` == this.tableName){
				this.$message.error(`家长姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`jiazhang` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
			if(`jiazhang` == this.tableName && this.ruleForm.youxiang &&(!this.$validate.isEmail(this.ruleForm.youxiang))){
				this.$message.error(`邮箱应输入邮件格式`);
				return
			}
			if(`jiazhang` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
				this.$message.error(`手机应输入手机格式`);
				return
			}
			if((!this.ruleForm.xuehao) && `xuesheng` == this.tableName){
				this.$message.error(`学号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `xuesheng` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `xuesheng` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.xingming) && `xuesheng` == this.tableName){
				this.$message.error(`姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`xuesheng` == this.tableName && this.ruleForm.youxiang &&(!this.$validate.isEmail(this.ruleForm.youxiang))){
				this.$message.error(`邮箱应输入邮件格式`);
				return
			}
			if(`xuesheng` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
				this.$message.error(`手机应输入手机格式`);
				return
			}
			if((!this.ruleForm.yonghuming) && `xuesheng` == this.tableName){
				this.$message.error(`用户名不能为空`);
				return
			}
			if((!this.ruleForm.zuozhezhanghao) && `zuozhe` == this.tableName){
				this.$message.error(`作者账号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `zuozhe` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `zuozhe` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.zuozhexingming) && `zuozhe` == this.tableName){
				this.$message.error(`作者姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`zuozhe` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
			if((!this.ruleForm.youxiang) && `zuozhe` == this.tableName){
				this.$message.error(`邮箱不能为空`);
				return
			}
			if(`zuozhe` == this.tableName && this.ruleForm.youxiang &&(!this.$validate.isEmail(this.ruleForm.youxiang))){
				this.$message.error(`邮箱应输入邮件格式`);
				return
			}
			if((!this.ruleForm.shouji) && `zuozhe` == this.tableName){
				this.$message.error(`手机不能为空`);
				return
			}
			if(`zuozhe` == this.tableName && this.ruleForm.shouji &&(!this.$validate.isMobile(this.ruleForm.shouji))){
				this.$message.error(`手机应输入手机格式`);
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
	background: url(http://codegen.caihongy.cn/20240923/485f888d6fd7472b921f33d2bc986e70.jpg) no-repeat center center / 100% 100%;
	background: url(http://codegen.caihongy.cn/20240923/485f888d6fd7472b921f33d2bc986e70.jpg) no-repeat center center / 100% 100%;
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: flex-end;
	align-items: center;
	position: relative !important;
	.rgs-form {
		.rgs-form2 {
		margin: 20px 0;
		width: 100%;
		}
		border-radius: 0;
		padding: 0 6% 0 10%;
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
		.title {
			padding: 0 80px;
			margin: 0 0 20px 0;
			color: #333;
			background: none;
			font-weight: 600;
			width: 100%;
			font-size: 26px;
			line-height: 40px;
			text-align: left;
		}
		.list-item {
			padding: 0 0 0 0px;
			margin: 0 0 10px;
			width: 100%;
			border-color: #36527d;
			border-width: 0 0 2px;
			position: relative;
			border-style: solid;
			height: auto;
			/deep/ .el-form-item__content {
			}
			.lable {
				padding: 0;
				color: #333;
				left: -130px;
				width: 130px;
				font-size: 16px;
				line-height: 40px;
				position: absolute !important;
				text-align: right;
			}
			.el-input {
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 40px;
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
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px;
				padding: 0 10px 0 30px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 38px;
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
				border: 2px solid #36527d80;
				cursor: pointer;
				border-radius: 0px;
				color: #36527d;
				background: none;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 2px solid #36527d80;
				cursor: pointer;
				border-radius: 0px;
				color: #36527d;
				background: none;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 2px solid #36527d80;
				cursor: pointer;
				border-radius: 0px;
				color: #36527d;
				background: none;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
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
				left: 125px;
				line-height: 42px;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				background: none;
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border: 0px solid #e6e6e6;
				border-radius: 0px 0 0 0px;
				padding: 0 10px;
				margin: 0;
				color: #666;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			input:focus {
				border: 0px solid #e6e6e6;
				border-radius: 0px 0 0 0px !important;
				padding: 0 10px;
				outline: none;
				color: #666;
				background: none;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			input::placeholder {
				color: #999;
				font-size: 16px;
			}
			button {
				border: 0px solid #e6e6e6;
				cursor: pointer;
				border-radius: 0 0px 0px 0;
				padding: 0;
				margin: 0 0 0 10px;
				color: #fff;
				background: #1a3194;
				width: 120px;
				font-size: 15px;
				height: 38px;
			}
			button:hover {
				opacity: 0.8;
			}
		}
		.register-btn {
			display: flex;
			width: 100%;
			flex-wrap: wrap;
		}
		.register-btn1 {
			width: 100%;
			text-align: center;
			order: 3;
		}
		.register-btn2 {
			width: 100%;
		}
		.r-btn {
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
		.r-btn:hover {
			border: 0px solid rgba(0, 0, 0, 1);
			opacity: 0.8;
		}
		.r-login {
			cursor: pointer;
			padding: 0;
			color: #333;
			display: inline-block;
			width: 100%;
			font-size: 15px;
			line-height: 40px;
			text-align: right;
		}
		.r-login:hover {
			opacity: 1;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
