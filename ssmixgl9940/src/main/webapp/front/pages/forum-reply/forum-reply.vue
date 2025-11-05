<template>
<view class="content">
	<view :style='{"width":"100%","padding":"40rpx 0 0","position":"relative","background":"#fff","height":"100%"}'>
		<form :style='{"width":"100%","padding":"0","background":"none","display":"block","height":"auto"}'>
			<view :style='{"padding":"4rpx 40rpx","margin":"0 0 8rpx 0","borderColor":"#ccc","alignItems":"center","borderRadius":"0","borderWidth":"0 0 0px 0","background":"#fff","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<xia-editor :style='{"minHeight":"300rpx","border":"2rpx solid #666","padding":"20rpx","margin":"0px","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","width":"100%","height":"auto"}' v-model="content" @editorChange="contentChange" placeholder="回复"></xia-editor>
			</view>
			<view :style='{"width":"100%","margin":"40rpx 0 0","justifyContent":"center","display":"flex","height":"auto"}'>
				<button :style='{"border":"0","padding":"0px","margin":"0 20rpx","color":"rgb(255, 255, 255)","borderRadius":"8rpx","background":"#a4adb3","width":"300rpx","lineHeight":"88rpx","fontSize":"32rpx","height":"88rpx"}' @tap="onReplyTap" class="bg-red margin-tb-sm">提交回复</button>
			</view>
		</form>
	</view>
</view>
</template>

<script>
	import xiaEditor from '@/components/xia-editor/xia-editor'
	export default {
		data() {
			return {
				pid: '',
				content: '',
				username: '',
				avatarurl: '',
				user: {},
			}
		},
		components: {
			xiaEditor
		},
		async onLoad(options) {
			this.pid = options.pid;
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			if(table == `jiazhang`){
				this.username = this.user.yonghuming
			}
			if(table == `xuesheng`){
				this.username = this.user.xuehao
			}
			if(table == `zuozhe`){
				this.username = this.user.zuozhezhanghao
			}
		},
		methods: {
			contentChange(e) {
				this.content = e
			},
			async onReplyTap() {
				if(this.content==''){
					this.$utils.msg('内容不能为空');
					return false
				}
				this.avatarurl = uni.getStorageSync('headportrait')?uni.getStorageSync('headportrait'):'';
				
				await this.$api.save('forum',{
					parentid: this.pid,
					content: this.content,
					avatarurl: this.avatarurl,
					username: this.username,
					toptime: this.$utils.getCurDateTime()
				});
				this.$utils.msgBack('回复成功');
				uni.setStorageSync('forumComType', true);
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
