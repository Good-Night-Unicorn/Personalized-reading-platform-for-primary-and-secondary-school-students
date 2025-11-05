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
				<el-form-item class="input" v-if="type!='info'"  label="书籍名称" prop="shujimingcheng" >
					<el-input v-model="ruleForm.shujimingcheng" placeholder="书籍名称" clearable  :readonly="ro.shujimingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="书籍名称" prop="shujimingcheng" >
					<el-input v-model="ruleForm.shujimingcheng" placeholder="书籍名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="书籍分类" prop="shujifenlei" >
					<el-select :disabled="ro.shujifenlei" v-model="ruleForm.shujifenlei" placeholder="请选择书籍分类" >
						<el-option
							v-for="(item,index) in shujifenleiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="书籍分类" prop="shujifenlei" >
					<el-input v-model="ruleForm.shujifenlei"
						placeholder="书籍分类" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.tupian" label="图片" prop="tupian" >
					<file-upload
						tip="点击上传图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
						@change="tupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.tupian" label="图片" prop="tupian" >
					<img v-if="ruleForm.tupian.substring(0,4)=='http'&&ruleForm.tupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian" width="100" height="100">
					<img v-else-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="年份" prop="nianfen" >
					<el-input v-model="ruleForm.nianfen" placeholder="年份" clearable  :readonly="ro.nianfen"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="年份" prop="nianfen" >
					<el-input v-model="ruleForm.nianfen" placeholder="年份" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="出版社" prop="chubanshe" >
					<el-select :disabled="ro.chubanshe" v-model="ruleForm.chubanshe" placeholder="请选择出版社" >
						<el-option
							v-for="(item,index) in chubansheOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="出版社" prop="chubanshe" >
					<el-input v-model="ruleForm.chubanshe"
						placeholder="出版社" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="ISBM" prop="bianhao" >
					<el-input v-model="ruleForm.bianhao" placeholder="ISBM" clearable  :readonly="ro.bianhao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="ISBM" prop="bianhao" >
					<el-input v-model="ruleForm.bianhao" placeholder="ISBM" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="作者账号" prop="zuozhezhanghao" >
					<el-input v-model="ruleForm.zuozhezhanghao" placeholder="作者账号" clearable  :readonly="ro.zuozhezhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="作者账号" prop="zuozhezhanghao" >
					<el-input v-model="ruleForm.zuozhezhanghao" placeholder="作者账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="作者姓名" prop="zuozhexingming" >
					<el-input v-model="ruleForm.zuozhexingming" placeholder="作者姓名" clearable  :readonly="ro.zuozhexingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="作者姓名" prop="zuozhexingming" >
					<el-input v-model="ruleForm.zuozhexingming" placeholder="作者姓名" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="书籍简介" prop="shujijianjie" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="书籍简介"
					v-model="ruleForm.shujijianjie" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.shujijianjie" label="书籍简介" prop="shujijianjie" >
				<span class="text">{{ruleForm.shujijianjie}}</span>
			</el-form-item>
			<el-form-item v-if="type!='info'"  label="图书介绍" prop="tushujieshao" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.tushujieshao" 
					class="editor"
					myQuillEditor="tushujieshao"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.tushujieshao" label="图书介绍" prop="tushujieshao" >
				<span class="text ql-snow ql-editor" v-html="ruleForm.tushujieshao"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					确定
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
	import { 
		isNumber,
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					shujimingcheng : false,
					shujifenlei : false,
					tupian : false,
					nianfen : false,
					chubanshe : false,
					bianhao : false,
					shujijianjie : false,
					tushujieshao : false,
					zuozhezhanghao : false,
					zuozhexingming : false,
					clicktime : false,
					discussnum : false,
					totalscore : false,
					storeupnum : false,
				},
			
				ruleForm: {
					shujimingcheng: '',
					shujifenlei: '',
					tupian: '',
					nianfen: '',
					chubanshe: '',
					bianhao: '',
					shujijianjie: '',
					tushujieshao: '',
					zuozhezhanghao: '',
					zuozhexingming: '',
					clicktime: '',
				},
				shujifenleiOptions: [],
				chubansheOptions: [],

				rules: {
					shujimingcheng: [
						{ required: true, message: '书籍名称不能为空', trigger: 'blur' },
					],
					shujifenlei: [
						{ required: true, message: '书籍分类不能为空', trigger: 'blur' },
					],
					tupian: [
					],
					nianfen: [
						{ required: true, message: '年份不能为空', trigger: 'blur' },
					],
					chubanshe: [
						{ required: true, message: '出版社不能为空', trigger: 'blur' },
					],
					bianhao: [
						{ required: true, message: 'ISBM不能为空', trigger: 'blur' },
					],
					shujijianjie: [
						{ required: true, message: '书籍简介不能为空', trigger: 'blur' },
					],
					tushujieshao: [
					],
					zuozhezhanghao: [
					],
					zuozhexingming: [
					],
					clicktime: [
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					totalscore: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
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
						if(o=='shujimingcheng'){
							this.ruleForm.shujimingcheng = obj[o];
							this.ro.shujimingcheng = true;
							continue;
						}
						if(o=='shujifenlei'){
							this.ruleForm.shujifenlei = obj[o];
							this.ro.shujifenlei = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o];
							this.ro.tupian = true;
							continue;
						}
						if(o=='nianfen'){
							this.ruleForm.nianfen = obj[o];
							this.ro.nianfen = true;
							continue;
						}
						if(o=='chubanshe'){
							this.ruleForm.chubanshe = obj[o];
							this.ro.chubanshe = true;
							continue;
						}
						if(o=='bianhao'){
							this.ruleForm.bianhao = obj[o];
							this.ro.bianhao = true;
							continue;
						}
						if(o=='shujijianjie'){
							this.ruleForm.shujijianjie = obj[o];
							this.ro.shujijianjie = true;
							continue;
						}
						if(o=='tushujieshao'){
							this.ruleForm.tushujieshao = obj[o];
							this.ro.tushujieshao = true;
							continue;
						}
						if(o=='zuozhezhanghao'){
							this.ruleForm.zuozhezhanghao = obj[o];
							this.ro.zuozhezhanghao = true;
							continue;
						}
						if(o=='zuozhexingming'){
							this.ruleForm.zuozhexingming = obj[o];
							this.ro.zuozhexingming = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='totalscore'){
							this.ruleForm.totalscore = obj[o];
							this.ro.totalscore = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
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
						if(((json.zuozhezhanghao!=''&&json.zuozhezhanghao) || json.zuozhezhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zuozhezhanghao = json.zuozhezhanghao
							this.ro.zuozhezhanghao = true;
						}
						if(this.$storage.get("role")!="管理员") {
							this.ro.zuozhezhanghao = true;
						}
						if(((json.zuozhexingming!=''&&json.zuozhexingming) || json.zuozhexingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zuozhexingming = json.zuozhexingming
							this.ro.zuozhexingming = true;
						}
						if(this.$storage.get("role")!="管理员") {
							this.ro.zuozhexingming = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/shujifenlei/shujifenlei`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.shujifenleiOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.chubansheOptions = "北京教育出版社,中信出版社,人民文学出版社,人民邮电出版社,陕西人民教育出版社,北京联合出版公司,汕头大学出版社,辽宁人民出版社,机械工业出版社,接力出版社".split(',')
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `shujixinxi/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.tushujieshao = this.ruleForm.tushujieshao.replace(reg,'../../../ssmixgl9940/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.tupian!=null) {
						this.ruleForm.tupian = this.ruleForm.tupian.replace(new RegExp(this.$base.url,"g"),"");
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
								url: `shujixinxi/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.shujixinxiCrossAddOrUpdateFlag = false;
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
				this.parent.shujixinxiCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.add-update-preview {
		padding: 40px 25% 40px 10%;
		background: none;
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
		color: #6e6e6e;
		font-weight: 500;
		width: 180px;
		font-size: 15px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #666;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		min-width: 100%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 15px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
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
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 34px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: none;
		width: auto;
		font-size: 15px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 15px;
		line-height: 40px;
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
		border: 1px solid #E8E8E8;
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
		border: 1px solid #E8E8E8;
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
		border: 1px solid #E8E8E8;
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
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		min-width: 100%;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: none;
				width: auto;
				font-size: 15px;
				min-width: 400px;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #1a3194;
			width: auto;
			font-size: 16px;
			min-width: 110px;
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
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #1fc3cb;
			width: auto;
			font-size: 16px;
			min-width: 110px;
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
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #32c67a;
			width: auto;
			font-size: 16px;
			min-width: 110px;
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
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #b3b3b3;
			width: auto;
			font-size: 16px;
			min-width: 110px;
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
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #156286;
			width: auto;
			font-size: 16px;
			min-width: 110px;
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
