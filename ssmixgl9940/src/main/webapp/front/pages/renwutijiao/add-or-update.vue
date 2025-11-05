<template>
<view class="content">
	<view :style='{"width":"100%","padding":"40rpx 0 0","position":"relative","background":"#fff","height":"100%"}'>
		<form :style='{"width":"100%","padding":"0 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">任务名称</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.renwumingcheng" v-model="ruleForm.renwumingcheng" placeholder="任务名称"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">任务类型</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.renwuleixing" v-model="ruleForm.renwuleixing" placeholder="任务类型"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="tupianTap">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">图片</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.tupian" :src="baseUrl+ruleForm.tupian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">学号</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.xuehao" v-model="ruleForm.xuehao" placeholder="学号"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">姓名</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.xingming" v-model="ruleForm.xingming" placeholder="姓名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">用户名</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.yonghuming" v-model="ruleForm.yonghuming" placeholder="用户名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">家长姓名</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.jiazhangxingming" v-model="ruleForm.jiazhangxingming" placeholder="家长姓名"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">提交时间</view>
				<input :disabled="ro.tijiaoshijian" :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' v-model="ruleForm.tijiaoshijian" placeholder="提交时间" @tap="toggleTab('tijiaoshijian')"></input>
			</view>
             
			<view :style='{"padding":"10rpx 20rpx","margin":"0","borderColor":"#ccc","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#333","fontWeight":"600"}' class="title">任务内容</view>
				<xia-editor ref="renwuneirong" :style='{"minHeight":"300rpx","border":"2rpx solid #666","width":"100%","padding":"20rpx","borderRadius":"8rpx","height":"auto"}' v-model="ruleForm.renwuneirong" placeholder="任务内容" @editorChange="renwuneirongChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","margin":"40rpx 0","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 40rpx 0 0","color":"rgb(255, 255, 255)","borderRadius":"8rpx","background":"#a4adb3","width":"250rpx","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx"}' @tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

		<w-picker  mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="tijiaoshijianConfirm" ref="tijiaoshijian" themeColor="#333333"></w-picker>
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
				renwumingcheng: '',
				renwuleixing: '',
				tupian: '',
				renwuneirong: '',
				xuehao: '',
				xingming: '',
				yonghuming: '',
				jiazhangxingming: '',
				tijiaoshijian: '',
				crossuserid: '',
				crossrefid: '',
				shhf: '',
				},
				// 登录用户信息
				user: {},
				ro:{
				   renwumingcheng : false,
				   renwuleixing : false,
				   tupian : false,
				   renwuneirong : false,
				   xuehao : false,
				   xingming : false,
				   yonghuming : false,
				   jiazhangxingming : false,
				   tijiaoshijian : false,
				   crossuserid : false,
				   crossrefid : false,
				   sfsh : false,
				   shhf : false,
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
			this.ruleForm.tijiaoshijian =  this.$utils.getCurDateTime();
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取
			this.ruleForm.xuehao = this.user.xuehao
			this.ro.xuehao = true;
			this.ruleForm.xingming = this.user.xingming
			this.ro.xingming = true;
			this.ruleForm.yonghuming = this.user.yonghuming
			this.ro.yonghuming = true;
			this.ruleForm.jiazhangxingming = this.user.jiazhangxingming
			this.ro.jiazhangxingming = true;

			this.ro.xuehao = true;
			this.ro.xingming = true;
			this.ro.yonghuming = true;
			this.ro.jiazhangxingming = true;
			this.ro.tijiaoshijian = true;


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
				res = await this.$api.info(`renwutijiao`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='renwumingcheng'){
						this.ruleForm.renwumingcheng = obj[o];
						this.ro.renwumingcheng = true;
						continue;
					}
					if(o=='renwuleixing'){
						this.ruleForm.renwuleixing = obj[o];
						this.ro.renwuleixing = true;
						continue;
					}
					if(o=='tupian'){
						this.ruleForm.tupian = obj[o].split(",")[0];
						this.ro.tupian = true;
						continue;
					}
					if(o=='renwuneirong'){
						this.ruleForm.renwuneirong = obj[o];
						this.ro.renwuneirong = true;
						continue;
					}
					if(o=='xuehao'){
						this.ruleForm.xuehao = obj[o];
						this.ro.xuehao = true;
						continue;
					}
					if(o=='xingming'){
						this.ruleForm.xingming = obj[o];
						this.ro.xingming = true;
						continue;
					}
					if(o=='yonghuming'){
						this.ruleForm.yonghuming = obj[o];
						this.ro.yonghuming = true;
						continue;
					}
					if(o=='jiazhangxingming'){
						this.ruleForm.jiazhangxingming = obj[o];
						this.ro.jiazhangxingming = true;
						continue;
					}
					if(o=='tijiaoshijian'){
						this.ruleForm.tijiaoshijian = obj[o];
						this.ro.tijiaoshijian = true;
						continue;
					}
					if(o=='crossuserid'){
						this.ruleForm.crossuserid = obj[o];
						this.ro.crossuserid = true;
						continue;
					}
					if(o=='crossrefid'){
						this.ruleForm.crossrefid = obj[o];
						this.ro.crossrefid = true;
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
			renwuneirongChange(e) {
				this.ruleForm.renwuneirong = e
			},
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.app-update-pv . .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
					// })
				})
			},

			// 多级联动参数


			// 日长控件选择日期时间
			tijiaoshijianConfirm(val) {
				console.log(val)
				this.ruleForm.tijiaoshijian = val.result;
				this.$forceUpdate();
			},


			tupianTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.tupian = 'upload/' + res.file;
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
				if((!this.ruleForm.renwuneirong)){
					this.$utils.msg(`任务内容不能为空`);
					return
				}
				if((!this.ruleForm.xuehao)){
					this.$utils.msg(`学号不能为空`);
					return
				}
				if((!this.ruleForm.xingming)){
					this.$utils.msg(`姓名不能为空`);
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
					let res = await this.$api.list(`renwutijiao`, params);
					if (res.data.total >= crossoptnum) {
						this.$utils.msg(uni.getStorageSync('tips'));
						uni.removeStorageSync('crossCleanType');
						return false;
					} else {
				//跨表计算
						let oet = {}
						if(this.ruleForm.id){
							await this.$api.update(`renwutijiao`, this.ruleForm);
						}else{
							oet = await this.$api.add(`renwutijiao`, this.ruleForm);
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
						await this.$api.update(`renwutijiao`, this.ruleForm);
					}else{
						oet = await this.$api.add(`renwutijiao`, this.ruleForm);
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
