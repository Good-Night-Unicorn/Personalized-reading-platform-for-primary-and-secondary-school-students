<template>
<view class="content">
	<view :style='{"width":"100%","padding":"40rpx 0 0","position":"relative","background":"#fff","height":"100%"}'>
		<form :style='{"width":"100%","padding":"0 0 48rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">任务名称</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.renwumingcheng" v-model="ruleForm.renwumingcheng" placeholder="任务名称"  type="text"></input>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">任务类型</view>
				<picker :disabled="ro.renwuleixing" :style='{"width":"100%","flex":"1","height":"auto"}' @change="renwuleixingChange" :value="renwuleixingIndex" :range="renwuleixingOptions">
					<view :style='{"border":"2rpx solid #666","padding":"0 20rpx","color":"#666","borderRadius":"8rpx","width":"100%","lineHeight":"80rpx","fontSize":"28rpx"}' class="uni-input">{{ruleForm.renwuleixing?ruleForm.renwuleixing:"请选择任务类型"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="tupianTap">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">图片</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.tupian" :src="baseUrl+ruleForm.tupian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">倒计结束时间</view>
				<input :disabled="ro.reversetime" :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' v-model="ruleForm.reversetime" placeholder="倒计结束时间" @tap="toggleTab('reversetime')"></input>
			</view>
      
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">任务要求</view>
				<textarea :style='{"border":"2rpx solid #666","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.renwuyaoqiu" placeholder="任务要求" :maxlength="-1"></textarea>
			</view>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"padding":"0 20rpx 0 0","color":"#333","textAlign":"right","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","fontWeight":"600"}' class="title">任务奖励</view>
				<textarea :style='{"border":"2rpx solid #666","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.renwujiangli" placeholder="任务奖励" :maxlength="-1"></textarea>
			</view>
			
			<view :style='{"width":"100%","margin":"40rpx 0","justifyContent":"center","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0","margin":"0 40rpx 0 0","color":"rgb(255, 255, 255)","borderRadius":"8rpx","background":"#a4adb3","width":"250rpx","lineHeight":"80rpx","fontSize":"32rpx","height":"80rpx"}' @tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

		<w-picker  mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="reversetimeConfirm" ref="reversetime" themeColor="#333333"></w-picker>
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
				renwuyaoqiu: '',
				renwujiangli: '',
				reversetime: '',
				},
				renwuleixingOptions: [],
				renwuleixingIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   renwumingcheng : false,
				   renwuleixing : false,
				   tupian : false,
				   renwuyaoqiu : false,
				   renwujiangli : false,
				   reversetime : false,
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
			
			// ss读取


			// 下拉框
			res = await this.$api.option(`renwuleixing`,`renwuleixing`,{});
			this.renwuleixingOptions = res.data;
			this.renwuleixingOptions.unshift("请选择任务类型");

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
				res = await this.$api.info(`renwuxinxi`, this.ruleForm.id);
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
					if(o=='renwuyaoqiu'){
						this.ruleForm.renwuyaoqiu = obj[o];
						this.ro.renwuyaoqiu = true;
						continue;
					}
					if(o=='renwujiangli'){
						this.ruleForm.renwujiangli = obj[o];
						this.ro.renwujiangli = true;
						continue;
					}
					if(o=='reversetime'){
						this.ruleForm.reversetime = obj[o];
						this.ro.reversetime = true;
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


			// 日长控件选择日期时间
			reversetimeConfirm(val) {
				console.log(val)
				this.ruleForm.reversetime = val.result;
				this.$forceUpdate();
			},

			// 下拉变化
			renwuleixingChange(e) {
				this.renwuleixingIndex = e.target.value
				this.ruleForm.renwuleixing = this.renwuleixingOptions[this.renwuleixingIndex]
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
				if((!this.ruleForm.renwumingcheng)){
					this.$utils.msg(`任务名称不能为空`);
					return
				}
				if((!this.ruleForm.renwuleixing)){
					this.$utils.msg(`任务类型不能为空`);
					return
				}
				if((!this.ruleForm.renwuyaoqiu)){
					this.$utils.msg(`任务要求不能为空`);
					return
				}
				if((!this.ruleForm.renwujiangli)){
					this.$utils.msg(`任务奖励不能为空`);
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
					let res = await this.$api.list(`renwuxinxi`, params);
					if (res.data.total >= crossoptnum) {
						this.$utils.msg(uni.getStorageSync('tips'));
						uni.removeStorageSync('crossCleanType');
						return false;
					} else {
				//跨表计算
						let oet = {}
						if(this.ruleForm.id){
							await this.$api.update(`renwuxinxi`, this.ruleForm);
						}else{
							oet = await this.$api.add(`renwuxinxi`, this.ruleForm);
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
						await this.$api.update(`renwuxinxi`, this.ruleForm);
					}else{
						oet = await this.$api.add(`renwuxinxi`, this.ruleForm);
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
