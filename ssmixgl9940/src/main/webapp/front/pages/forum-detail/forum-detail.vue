<template>
	<view :style='{"alignContent":"flex-start","width":"100%","padding":"0","flexWrap":"wrap","background":"#fff","display":"flex"}'>
		<view :style='{"boxShadow":"0 2rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"10%","background":"#fff","width":"60rpx","lineHeight":"60rpx","position":"absolute","right":"5%","height":"60rpx","zIndex":"11"}' @click="reportClick">
			<span class="icon iconfont icon-zhangjie18" :style='{"color":"#000"}'></span>
		</view>
		<swiper :style='{"width":"100%","background":"#fff","height":"360rpx"}' class="swiper" :indicator-dots='false' :autoplay='false' :circular='false' indicator-active-color='#000000' indicator-color='rgba(0, 0, 0, .3)' :duration='500' :interval='5000' :vertical='false'>
			<swiper-item :style='{"width":"100%","background":"#fff","height":"360rpx"}' v-for="(swiper,index) in swiperList" :key="index">
				<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"360rpx"}' mode="aspectFill" v-if="swiper.substring(0,4)=='http'" :src="swiper" @tap="imgView2(swiper)"></image>
				<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"360rpx"}' mode="aspectFill" v-else :src="baseUrl+swiper" @tap="imgView2(baseUrl+swiper)"></image>
			</swiper-item>
		</swiper>
		<view :style='{"margin":"20rpx auto 0","borderRadius":"20rpx 20rpx 0 0","textAlign":"center","background":"#fff","width":"calc(100% - 48rpx)","fontSize":"32rpx","lineHeight":"88rpx"}'>
			{{forum.title}}
		</view>
		<view :style='{"width":"calc(100% - 48rpx)","margin":"0 auto","fontSize":"28rpx","color":"#888","textAlign":"center","background":"#fff"}'>
			<span class="icon iconfont icon-touxiang18" :style='{"padding":"0 20rpx 0 0"}'></span>
			<span :style='{"padding":"0 20rpx 0 0"}'>发布人：</span>
			<span :style='{"padding":"0 20rpx 0 0"}'>{{forum.userid!=userid&&forum.isanon==1?'匿名':forum.username}}</span>
		</view>
		<view class="operateView" :style='{"margin":"40rpx auto 0","alignItems":"center","background":"none","display":"flex","width":"calc(100% - 48rpx)","fontSize":"30rpx","justifyContent":"center","order":"2"}'>
			<view v-if="!likeType" :style='{"padding":"20rpx 40rpx","margin":"0 20rpx 0 0","color":"#666","borderRadius":"20rpx","textAlign":"center","background":"#999","width":"auto"}' @click="likeClick">
				<span class="icon iconfont icon-zan10" :style='{"color":"#fff"}'></span>
				<span :style='{"color":"#fff"}'>赞({{allZan}})</span>
			</view>
			<view v-if="likeType" :style='{"width":"auto","padding":"20rpx 40rpx","margin":"0 20rpx 0 0","color":"#fff","borderRadius":"20rpx","background":"#ccc"}' @click="likeClick">
				<span class="icon iconfont icon-zan10" :style='{"color":"#fff"}'></span>
				<span :style='{"color":"#fff"}'>取消赞({{allZan}})</span>
			</view>
			<view v-if="!collectType" :style='{"width":"auto","padding":"20rpx 40rpx","color":"#666","borderRadius":"20rpx","textAlign":"center","background":"#999"}' @click="collectClick">
				<span class="icon iconfont icon-shoucang10" :style='{"color":"#fff"}'></span>
				<span :style='{"color":"#fff"}'>收藏({{allCollect}})</span>
			</view>
			<view v-if="collectType" :style='{"width":"auto","padding":"20rpx 40rpx","color":"#fff","borderRadius":"20rpx","background":"#ccc"}' @click="collectClick">
				<span class="icon iconfont icon-shoucang10" :style='{"color":"#fff"}'></span>
				<span :style='{"color":"#fff"}'>取消收藏({{allCollect}})</span>
			</view>
		</view>
		<view :style='{"border":"0px solid #eee","padding":"20rpx","margin":"0 auto","borderRadius":"0 0 20rpx 20rpx","color":"#666","background":"#fff","width":"calc(100% - 48rpx)","fontSize":"30rpx","lineHeight":"48rpx"}'>
			<rich-text :nodes="forum.content"></rich-text>
		</view>
		<view :style='{"width":"calc(100% - 48rpx)","margin":"40rpx auto 168rpx","background":"none","order":"11"}'>
			<view :style='{"width":"100%","margin":"0 0 40rpx","borderRadius":"20rpx","background":"#fff"}' v-for="comment in commentData" :key="comment.id">
				<view :style='{"padding":"20rpx","alignItems":"center","color":"#ccc","justifyContent":"flex-start","display":"flex"}'>
					<image class="image" :src="baseUrl+comment.avatarurl" mode="widthFix" :style='{"width":"60rpx","height":"60rpx"}'></image>
					<view :style='{"padding":"0 0 0 20rpx","flexDirection":"column","display":"flex","width":"100%","fontSize":"24rpx","lineHeight":"1","justifyContent":"center"}'>
						<view :style='{"color":"#000","lineHeight":"2","fontSize":"26rpx"}'>
							{{comment.username}}
						</view>
						<view :style='{"lineHeight":"1","fontSize":"28rpx"}'> {{comment.addtime.split(' ')[0]}}</view>
					</view>
				</view>
				<view :style='{"border":"2rpx solid #eee","padding":"0 20rpx 20rpx 100rpx","fontSize":"28rpx","color":"#333","borderWidth":"0 0 2rpx"}'>
					<rich-text :style='{"color":"#000"}' :nodes="comment.content"></rich-text>
					<view :style='{"width":"100%","alignItems":"center","justifyContent":"flex-end","display":"flex"}'>
						<text @tap="onReplyTap(comment.id)" :style='{"padding":"6rpx 20rpx","margin":"0 20rpx 0 0","color":"#fff","background":"#999"}'>回复</text>
						<text @tap="delClick(comment.id)" v-if="userid==comment.userid" :style='{"border":"0px solid #ccc","padding":"4rpx 20rpx","color":"#666","background":"#ededed"}'>删除</text>
					</view>
				</view>
				<view :style='{"width":"100%","padding":"0 0 0 80rpx"}' v-if="comment.childs">
					<view :style='{"width":"100%"}' v-for="item in comment.childs" :key="item.id">
						<view :style='{"padding":"20rpx","alignItems":"center","color":"#ccc","justifyContent":"flex-start","display":"flex"}'>
							<image :style='{"width":"60rpx","height":"60rpx"}' :src="baseUrl+item.avatarurl" mode="widthFix"></image>
							<view :style='{"padding":"0 0 0 20rpx","flexDirection":"column","display":"flex","width":"100%","fontSize":"24rpx","lineHeight":"1","justifyContent":"center"}'>
								<view :style='{"color":"#000","lineHeight":"2","fontSize":"26rpx"}'>
									{{item.username}}
								</view>
								<view :style='{"lineHeight":"1","fontSize":"28rpx"}'>{{item.addtime.split(' ')[0]}}</view>
							</view>
						</view>
						<view :style='{"border":"2rpx solid #eee","padding":"0 20rpx 20rpx 100rpx","fontSize":"28rpx","color":"#333","borderWidth":"0 0 2rpx"}'>
							<rich-text :style='{"color":"#000"}' :nodes="item.content"></rich-text>
							<view :style='{"width":"100%","alignItems":"center","justifyContent":"flex-end","display":"flex"}'>
								<text :style='{"border":"0px solid #ccc","padding":"4rpx 20rpx","color":"#666","background":"#ededed"}' @tap="delClick(item.id)" v-if="userid==item.userid">删除</text>
							</view>
						</view>
					</view>
				</view>
			</view>
			<view class="kong"></view>
			
			<view class="bottom-content">
				<button @tap="onReplyTap(forum.id)" class="bg-red margin-tb-sm">评论</button>
			</view>
			<uni-popup class="popup-content" ref="reports" type="center">
				<form class="popup-form" style="background: #fff;width: 350px;display: block;border-radius: 10px;padding: 20px;text-align: center;">
					<view>
						<textarea placeholder="举报原因" style="width: 100%;border: 1px solid #eee;border-radius: 10px;text-align: left;padding: 10px;font-size: 14px;" v-model="reportForm.reason"></textarea>
					</view>
					<view style="display: flex;align-items: center;padding: 10px 0;">
						<image @click="imgView(item,index)" v-for="(item,index) in picList" :key="index" :src="baseUrl + item" style="width: 50px;margin: 0 5px 0 0;" mode="widthFix"></image>
						<view style="padding: 0 10px;line-height: 20px;background: #ccc;color: #333;" @click="uploadImg">上传图片</view>
					</view>
					<view class="btn-content" style="padding: 5px 0;">
						<button style="margin: 0 10px" @tap="onCloseWinTap" class="cu-btn bg-cyan">取消</button>
						<button style="margin: 0 10px" @tap="onFinishTap" class="cu-btn bg-red">确认</button>
					</view>
				</form>
			</uni-popup>
		</view>
	</view>
</template>
<script>
	import userReply from '@/components/forum-reply/forum-reply.vue'
	export default {
		components: {
			userReply
		},
		data() {
			return {
				commentData: [],
				forum: {},
				id: '',
				likeType: false,
				likeForm: {},
				allZan: 0,
				collectType: false,
				collectForm: {},
				allCollect: 0,
				reportForm: {
					forumid: '',
					title: '',
					userid: uni.getStorageSync('appUserid'),
					username: uni.getStorageSync('nickname'),
					reporteduserid: '',
					reportedusername: '',
					reason: '',
					picture: '',
					handleadvise: '',
					status: '处理中',
					reporttype: '主题帖举报'
				},
				picList: [],
				swiperList: [],
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
			userid(){
				return uni.getStorageSync('appUserid')
			}
		},
		async onLoad(options) {
			this.id = options.id;
			this.init()
		},
		async onShow() {
			let forumComType = uni.getStorageSync('forumComType');
			if(forumComType){
				uni.removeStorageSync('forumComType')
				this.init()
			}
		},
		methods: {
			async init() {
				let res = await this.$api.forumDetail(this.id)
				this.swiperList = res.data.cover ? res.data.cover.split(",") : [];
				res.data.childs = this.changeContent(res.data.childs)
				this.commentData = res.data.childs;
				res.data.content = res.data.content.replace(/img src/gi,"img style=\"width:100%;\" src");
				this.forum = res.data;
				this.getLike()
				this.getAllZan()
				this.getCollect()
				this.getAllCollect()
			},
			imgView(url,index){
				let that = this
				uni.showActionSheet({
					itemList: ['预览','删除'],
					success(res) {
						if(res.tapIndex==0){
							let arr = []
							for(let x in that.picList){
								arr.push(that.baseUrl + that.picList[x])
							}
							uni.previewImage({
								current: that.baseUrl + url,
								urls: arr
							})
						}else if(res.tapIndex==1){
							that.picList.splice(index,1)
						}
					}
				})
			},
			imgView2(url){
				let arr = []
				for(let x in this.swiperList){
					arr.push(this.swiperList[x].substr(0,4)=='http'?this.swiperList[x]:this.baseUrl + this.swiperList[x])
				}
				uni.previewImage({
					current: url,
					urls: arr
				})
			},
			async uploadImg(){
				let _this = this;
				await this.$api.upload(function(res) {
					_this.picList.push('upload/' + res.file)
					_this.$forceUpdate();
				});
			},
			reportClick(){
				this.reportForm.forumid = this.forum.id
				this.reportForm.title = this.forum.title
				this.reportForm.reporteduserid = this.forum.userid
				this.reportForm.reportedusername = this.forum.username
				this.reportForm.reason = ''
				this.reportForm.picture = ''
				this.picList = []
				this.$refs.reports.open();
			},
			onCloseWinTap(){
				this.$refs.reports.close();
			},
			async onFinishTap(){
				if(!this.reportForm.reason){
					this.$utils.msg('举报原因不能为空')
					return false
				}
				if(this.picList.length){
					this.reportForm.picture = this.picList.join(',')
				}
				await this.$api.add('forumreport',this.reportForm)
				this.$utils.msg('提交成功')
				this.$refs.reports.close();
			},
			async getAllZan() {
				let res = await this.$api.list('storeup',{
					page: 1,
					type: '21',
					tablename: 'forum',
					refid: Number(this.id),
				})
				this.allZan = Number(res.data.total)
			},
			// 获取点赞状态
			async getLike() {
				let res = await this.$api.list('storeup', {
					page: 1,
					limit: 1,
					type: '21',
					userid: uni.getStorageSync('appUserid'),
					refid: Number(this.id),
					tablename: 'forum'
				})
				if (res.data.list.length) {
					this.likeType = true
					this.likeForm = res.data.list[0]
				} else {
					this.likeType = false
				}
			},
			// 点赞按钮
			async likeClick() {
				let that = this
				if (this.likeType) {
					uni.showModal({
						title: '提示',
						content: '是否取消点赞？',
						async success(res) {
							if (res.confirm) {
								await that.$api.del('storeup', JSON.stringify([that.likeForm.id]))
								that.$utils.msg('取消成功')
								that.getLike()
								that.getAllZan()
							}
						}
					})
				} else {
					await that.$api.add('storeup', {
						userid: uni.getStorageSync('appUserid'),
						refid: Number(this.id),
						type: '21',
						name: this.forum.title,
						picture: this.swiperList[0],
						tablename: 'forum',
					})
					this.$utils.msg('点赞成功')
					this.getLike()
					this.getAllZan()
				}
			},
			async getAllCollect() {
				let res = await this.$api.list('storeup',{
					page: 1,
					type: '1',
					tablename: 'forum',
					refid: Number(this.id),
				})
				this.allCollect = Number(res.data.total)
			},
			// 获取收藏状态
			async getCollect() {
				let res = await this.$api.list('storeup', {
					page: 1,
					limit: 1,
					type: '1',
					userid: uni.getStorageSync('appUserid'),
					refid: Number(this.id),
					tablename: 'forum'
				})
				if (res.data.list.length) {
					this.collectType = true
					this.collectForm = res.data.list[0]
				} else {
					this.collectType = false
				}
			},
			// 收藏按钮
			async collectClick() {
				let that = this
				if (this.collectType) {
					uni.showModal({
						title: '提示',
						content: '是否取消收藏？',
						async success(res) {
							if (res.confirm) {
								await that.$api.del('storeup', JSON.stringify([that.collectForm.id]))
								that.$utils.msg('取消成功')
								that.getCollect()
								that.getAllCollect()
							}
						}
					})
				} else {
					await that.$api.add('storeup', {
						userid: uni.getStorageSync('appUserid'),
						refid: Number(this.id),
						type: '1',
						name: this.forum.title,
						picture: this.swiperList[0],
						tablename: 'forum',
					})
					this.$utils.msg('收藏成功')
					this.getCollect()
					this.getAllCollect()
				}
			},
			changeContent(childs){
				if(childs&&childs!=null&&childs.length){
					childs = change(childs)
				}
				return childs
				function change(childs){
					for(let x in childs){
						childs[x].content = childs[x].content.replace(/img src/gi,"img style=\"width:100%;\" src");
						if(childs[x].childs){
							childs[x].childs = change(childs[x].childs)
						}
					}
					return childs
				}
			},
			onReplyTap(pid) {
				this.$utils.jump(`../forum-reply/forum-reply?pid=${pid}`);
			},
			// 删除当前评论
			delClick(id) {
				let that = this
				uni.showModal({
					title: '提示',
					content: '是否删除此评论？',
					async success(res) {
						if (res.confirm) {
							await that.$api.del('forum', JSON.stringify([id]))
							that.$utils.msg('删除成功')
							setTimeout(async () => {
								let obj = await that.$api.forumDetail(that.id)
								obj.data.childs = that.changeContent(obj.data.childs)
								that.commentData = obj.data.childs;
							}, 1500)
						}
					}
				})
			},
		}
	}
</script>

<style scoped lang="scss">
	page {
		background: #FFFFFF;
	}

	.title {
		text-align: center;
		font-size: 40upx;
		font-weight: bold;
		margin: 20upx;
	}
	
	.username{
		text-align: center;
		font-size: 20upx;
		font-weight: bold;
		color: #888888;
		margin: 20upx;
	}
	
	.content {
		margin: 40upx;
		font-size: 30upx;
		line-height: 50upx;
		letter-spacing: 5upx;
	}

	.article-meta {
		padding: 20rpx 20rpx;
		display: flex;
		align-items: center;
		flex-direction: row;
		justify-content: flex-start;
		color: gray;
	}

	.article-header-image {
		width: 60rpx;
		height: 60rpx;
		flex-shrink: 0;
		/* border-radius: 50%; */
		overflow: hidden;
	}

	.article-header-image .image {
		width: 100%;
		height: 100%;
	}

	.article-header-content {
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding-left: 10px;
		font-size: 24rpx;
		line-height: 1;
		width: 100%;
		/* height: 60rpx; */
	}

	.article-header-title {
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 26rpx;
		line-height: 2;
		color: #000;
	}

	.article-header-info {
		font-size: 24rpx;
		line-height: 1;
	}

	.article-top {
		display: flex;
		align-items: flex-end;
		font-size: 26rpx;
		line-height: 1;
		color: #999;
	}

	.article-top .image {
		width: 30rpx;
		margin-left: 5rpx;
	}

	.uni-article__comment {
		margin-bottom: 160upx;
	}

	.uni-article__comment-title {
		width: 100%;
		line-height: 3;
		font-size: 28rpx;
		color: #666;
		background: #f5f5f5;
		padding-left: 20rpx;
	}

	.uni-article__comment-centent {
		border-bottom: 1px #eee solid;
		padding: 0 20rpx;
		padding-left: 95rpx;
		padding-bottom: 20rpx;
		font-size: 14px;
		color: #333;
	}

	.btn-replay {
		color: red;
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: flex-end;
	}
	
	.bottom-content{
		position: fixed;
		bottom: 0;
		padding: 20upx;
		width: 100%;
		left: 0;
	}

	.kong {
		width: 100%;
		height: 180rpx;
	}
</style>
