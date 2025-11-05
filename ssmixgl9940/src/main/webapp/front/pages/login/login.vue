<template>
	<view class="content">
		<view class="box" :style='{"minHeight":"100vh","width":"100%","padding":"48rpx 24rpx 24rpx","background":"url(http://codegen.caihongy.cn/20241211/73f47ee0ebab4dbaa00adc26ff65743f.jpg) no-repeat center top / cover,#fff","height":"auto"}'>
			<view :style='{"border":"2rpx solid #fff","padding":"24rpx 80rpx 0 20rpx","margin":"240rpx 0 0","borderRadius":"20rpx","textAlign":"center","background":"rgba(255,255,255,.3)","display":"block","width":"100%","position":"relative","height":"auto"}'>
				<image :style='{"margin":"0 auto 24rpx auto","borderRadius":"8rpx","top":"-240rpx","left":"33%","display":"block","width":"240rpx","position":"absolute","height":"200rpx"}' src="http://codegen.caihongy.cn/20241210/cea5791a7042462da40aad5d9a6dad4c.png" mode="aspectFill"></image>
				<view v-if="loginType==1" :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' class="uni-form-item uni-column">
					<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">账号：</view>
					<input v-model="username" :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' type="text" class="uni-input" name="" placeholder="请输入账号" />
				</view>
				<view v-if="loginType==1" :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' class="uni-form-item uni-column">
					<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">密码：</view>
					<input v-model="password" password :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' type="password" class="uni-input" name="" placeholder="请输入密码" />
				</view>
				<view v-if="roleNum>1" :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}'>
					<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">用户类型：</view>
					<picker @change="optionsChange" :value="index" :range="options" :style='{"border":"2rpx solid #666","padding":"0 20rpx","color":"#666","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'>
						<view class="uni-picker-type">{{options[index]}}</view>
					</picker>
				</view>
				

				
				<button v-if="loginType==1" class="btn-submit" @tap="onLoginTap" type="primary" :style='{"border":"2rpx solid #666","padding":"0 40rpx","margin":"24rpx auto","color":"#333","display":"inline-block","minWidth":"200rpx","borderRadius":"8rpx","background":"#fff","width":"calc(100% - 0px)","lineHeight":"88rpx","fontSize":"32rpx","fontWeight":"600","height":"88rpx"}'>登录</button>
				<button v-if="loginType==2" class="btn-submit" @tap="onFaceLoginTap" type="primary" :style='{"border":"2rpx solid #666","padding":"0 40rpx","margin":"24rpx auto","color":"#333","display":"inline-block","minWidth":"200rpx","borderRadius":"8rpx","background":"#fff","width":"calc(100% - 0px)","lineHeight":"88rpx","fontSize":"32rpx","fontWeight":"600","height":"88rpx"}'>人脸识别登录</button>
				<view class="links" :style='{"padding":"0","margin":"24rpx 0 24rpx 0","flexWrap":"wrap","flexDirection":"column","display":"flex","width":"100%","height":"auto"}'>
					<view class="link-highlight" @tap="onRegisterTap('jiazhang')" :style='{"padding":"0 8rpx","margin":"0 0 40rpx","color":"#fff","borderRadius":"8rpx","background":"#a4adb3","width":"100%","fontSize":"32rpx","lineHeight":"88rpx","height":"88rpx"}'>注册家长</view>
					<view class="link-highlight" @tap="onRegisterTap('xuesheng')" :style='{"padding":"0 8rpx","margin":"0 0 40rpx","color":"#fff","borderRadius":"8rpx","background":"#a4adb3","width":"100%","fontSize":"32rpx","lineHeight":"88rpx","height":"88rpx"}'>注册学生</view>
				</view>
				
				<view class="idea1" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea1</view>
				<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
				<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
			</view>
		</view>
	</view>
</template>

<script>
	import menu from '@/utils/menu'
	export default {
		data() {
			return {
				username: '',
				password: '',
                loginType:1,
				codes: [{
				  num: 1,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}, {
				  num: 2,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}, {
				  num: 3,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}, {
				  num: 4,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}],
				options: [
					'请选择登录用户类型',
				],
                optionsValues: [
					'',
                    'jiazhang',
                    'xuesheng',
				],
				index: 0,
				roleNum:0,

			}
		},
		onLoad() {
			let options = ['请选择登录用户类型'];
			let menus = menu.list();
			this.menuList = menus;
			for(let i=0;i<this.menuList.length;i++){
				if(this.menuList[i].hasFrontLogin=='是'){
					options.push(this.menuList[i].roleName);
					this.roleNum++;
				}
			}
			if(this.roleNum==1) {
				this.index = 1;
			}	
			this.options = options;
			this.styleChange()
		},
		onShow() {
		},
		mounted() {
		},
		methods: {
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.loginFrom.content.input.backgroundColor
					// })
				})
			},
			onRegisterTap(tableName) {
                uni.setStorageSync("loginTable", tableName);
				this.$utils.jump('../register/register')
			},
			async onLoginTap() {
                if (!this.username) {
					this.$utils.msg('请输入用户名')
					return
				}
                if (!this.password) {
					this.$utils.msg('请输入用户密码')
					return
				}
                if (!this.optionsValues[this.index]) {
					this.$utils.msg('请选择登录用户类型')
					return
				}

				this.loginPost()

			},
			async loginPost() {
				
				let res = await this.$api.login(`${this.optionsValues[this.index]}`, {
					username: this.username,
					password: this.password
				});
				uni.removeStorageSync("useridTag");
				uni.setStorageSync("appToken", res.token);
				uni.setStorageSync("nickname",this.username);
				uni.setStorageSync("nowTable", `${this.optionsValues[this.index]}`);
				res = await this.$api.session(`${this.optionsValues[this.index]}`);
				if(res.data.touxiang) {
				    uni.setStorageSync('headportrait', res.data.touxiang);
				} else if(res.data.headportrait) {
				    uni.setStorageSync('headportrait', res.data.headportrait);
				}
				uni.setStorageSync('userSession',JSON.stringify(res.data))
				// 保存用户id
				uni.setStorageSync("appUserid", res.data.id);
				if(res.data.vip) {
					uni.setStorageSync("vip", res.data.vip);
				}
				uni.setStorageSync("appRole", `${this.options[this.index]}`);
				this.$utils.tab('../index/index');
			},
			optionsChange(e) {
				this.index = e.target.value
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;
	}
	
	.content {
		height: 100%;
		box-sizing: border-box;
	}
	
</style>
