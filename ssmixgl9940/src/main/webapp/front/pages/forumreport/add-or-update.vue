<template>
<view class="content">
	<view :style='{"width":"100%","padding":"40rpx 0 0","position":"relative","background":"#fff","height":"100%"}'>
		<form :style='{"width":"100%","padding":"0 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">论坛id</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.forumid" v-model="ruleForm.forumid" placeholder="论坛id"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">帖子标题</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.title" v-model="ruleForm.title" placeholder="帖子标题"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">举报用户名</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.username" v-model="ruleForm.username" placeholder="举报用户名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">被举报用户id</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.reporteduserid" v-model="ruleForm.reporteduserid" placeholder="被举报用户id"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">被举报用户名</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.reportedusername" v-model="ruleForm.reportedusername" placeholder="被举报用户名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="pictureTap">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">图片补充</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.picture" :src="baseUrl+ruleForm.picture.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">举报类型</view>
				<picker :disabled="ro.reporttype" :style='{"width":"100%","flex":"1","height":"auto"}' @change="reporttypeChange" :value="reporttypeIndex" :range="reporttypeOptions">
					<view :style='{"border":"2rpx solid #666","padding":"0 20rpx","color":"#666","borderRadius":"8rpx","width":"100%","lineHeight":"80rpx","fontSize":"28rpx"}' class="uni-input">{{ruleForm.reporttype?ruleForm.reporttype:"请选择举报类型"}}</view>
				</picker>
			</view>
           
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">举报原因</view>
				<textarea :style='{"border":"2rpx solid #666","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.reason" placeholder="举报原因" :maxlength="-1"></textarea>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">处理建议</view>
				<textarea :style='{"border":"2rpx solid #666","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.handleadvise" placeholder="处理建议" :maxlength="-1"></textarea>
			</view>
			
			<view :style='{"width":"100%","margin":"40rpx 0","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 40rpx 0 0","color":"rgb(255, 255, 255)","borderRadius":"8rpx","background":"#a4adb3","width":"250rpx","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx"}' @tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

	</view>
</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";
	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				cross:'',
				ruleForm: {
				forumid: '',
				title: '',
				userid: '',
				username: '',
				reporteduserid: '',
				reportedusername: '',
				reason: '',
				picture: '',
				handleadvise: '',
				status: '',
				reporttype: '',
				},
				statusOptions: [],
				statusIndex: 0,
				reporttypeOptions: [],
				reporttypeIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   forumid : false,
				   title : false,
				   userid : false,
				   username : false,
				   reporteduserid : false,
				   reportedusername : false,
				   reason : false,
				   picture : false,
				   handleadvise : false,
				   status : false,
				   reporttype : false,
				},
				virtualPay: false,
			}
		},
		components: {
			wPicker,
			xiaEditor,
			multipleSelect,
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},



		},
		async onLoad(options) {
			if(options.virtualPay){
				this.virtualPay = true
			}
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			


			// 自定义下拉框值
			this.statusOptions = "处理中,已处理".split(',')
			// 自定义下拉框值
			this.reporttypeOptions = "主题帖举报".split(',')

			// 如果有登录，获取登录后保存的userid
			this.ruleForm.userid = uni.getStorageSync("appUserid")
			if (options.refid) {
				// 如果上一级页面传递了refid，获取改refid数据信息
				this.ruleForm.refid = Number(options.refid);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`forumreport`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='forumid'){
						this.ruleForm.forumid = obj[o];
						this.ro.forumid = true;
						continue;
					}
					if(o=='title'){
						this.ruleForm.title = obj[o];
						this.ro.title = true;
						continue;
					}
					if(o=='userid'){
						this.ruleForm.userid = obj[o];
						this.ro.userid = true;
						continue;
					}
					if(o=='username'){
						this.ruleForm.username = obj[o];
						this.ro.username = true;
						continue;
					}
					if(o=='reporteduserid'){
						this.ruleForm.reporteduserid = obj[o];
						this.ro.reporteduserid = true;
						continue;
					}
					if(o=='reportedusername'){
						this.ruleForm.reportedusername = obj[o];
						this.ro.reportedusername = true;
						continue;
					}
					if(o=='reason'){
						this.ruleForm.reason = obj[o];
						this.ro.reason = true;
						continue;
					}
					if(o=='picture'){
						this.ruleForm.picture = obj[o].split(",")[0];
						this.ro.picture = true;
						continue;
					}
					if(o=='handleadvise'){
						this.ruleForm.handleadvise = obj[o];
						this.ro.handleadvise = true;
						continue;
					}
					if(o=='status'){
						this.ruleForm.status = obj[o];
						this.ro.status = true;
						continue;
					}
					if(o=='reporttype'){
						this.ruleForm.reporttype = obj[o];
						this.ro.reporttype = true;
						continue;
					}
				}
			}
			this.styleChange()
			this.$forceUpdate()
			if (uni.getStorageSync('raffleType') && uni.getStorageSync('raffleType') != null) {
				uni.removeStorageSync('raffleType')
				setTimeout(() => {
					this.onSubmitTap()
				}, 300)
			}
		},
		methods: {
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.app-update-pv . .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
					// })
				})
			},

			// 多级联动参数



			// 下拉变化
			statusChange(e) {
				this.statusIndex = e.target.value
				this.ruleForm.status = this.statusOptions[this.statusIndex]
			},
			// 下拉变化
			reporttypeChange(e) {
				this.reporttypeIndex = e.target.value
				this.ruleForm.reporttype = this.reporttypeOptions[this.reporttypeIndex]
			},

			pictureTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.picture = 'upload/' + res.file;
					_this.$forceUpdate();
					_this.$nextTick(()=>{
						_this.styleChange()
					})
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap() {
				let that = this
				//跨表计算判断
				var obj;
				if((!this.ruleForm.userid)){
					this.$utils.msg(`举报用户id不能为空`);
					return
				}
				if((!this.ruleForm.reporteduserid)){
					this.$utils.msg(`被举报用户id不能为空`);
					return
				}
				if((!this.ruleForm.reason)){
					this.$utils.msg(`举报原因不能为空`);
					return
				}
				//更新跨表属性
				var crossuserid;
				var crossrefid;
				var crossoptnum;
				if(this.cross){
					var statusColumnName = uni.getStorageSync('statusColumnName');
					var statusColumnValue = uni.getStorageSync('statusColumnValue');
					if(statusColumnName!='') {
						if(!obj) {
							obj = uni.getStorageSync('crossObj');
						}
						if(!statusColumnName.startsWith("[")) {
							for (var o in obj){
								if(o==statusColumnName){
									obj[o] = statusColumnValue;
								}

							}
							var table = uni.getStorageSync('crossTable');
							await this.$api.update(`${table}`, obj);
						} else {
							   crossuserid=Number(uni.getStorageSync('appUserid'));
							   crossrefid=obj['id'];
							   crossoptnum=uni.getStorageSync('statusColumnName');
							   crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
						}
					}
				}
				if(crossrefid && crossuserid) {
					this.ruleForm.crossuserid=crossuserid;
					this.ruleForm.crossrefid=crossrefid;
					let params = {
						page: 1,
						limit:10,
						crossuserid:crossuserid,
						crossrefid:crossrefid,
					}
					let res = await this.$api.list(`forumreport`, params);
					if (res.data.total >= crossoptnum) {
						this.$utils.msg(uni.getStorageSync('tips'));
						uni.removeStorageSync('crossCleanType');
						return false;
					} else {
				//跨表计算
						let oet = {}
						if(this.ruleForm.id){
							await this.$api.update(`forumreport`, this.ruleForm);
						}else{
							oet = await this.$api.add(`forumreport`, this.ruleForm);
						}
						if(this.cross){
							uni.setStorageSync('crossCleanType',true);
						}
						this.$utils.msgBack('提交成功');
					}
				} else {
				//跨表计算
					let oet = {}
					if(this.ruleForm.id){
						await this.$api.update(`forumreport`, this.ruleForm);
					}else{
						oet = await this.$api.add(`forumreport`, this.ruleForm);
					}
					if(this.cross){
						uni.setStorageSync('crossCleanType',true);
					}
					this.$utils.msgBack('提交成功');
				}
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			bindDateChange(e) {
				this.date = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				if(this.ro[str]){
					return false
				}
				this.$refs[str].show();
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
