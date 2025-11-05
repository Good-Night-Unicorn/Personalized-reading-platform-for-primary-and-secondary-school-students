<template>
	<view @touchstart="touchStart" @touchend="touchEnd" :style='{"background": `${background}`}'>
		<view class="title" :style='{"color": `${color}`}'>
			{{detail.chaptertitle}}
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
		<view class="content">
			<rich-text :nodes="detail.content" :style='{"color": `${color}`,"font-size":`${fontSize}px`}'></rich-text>
		</view>
		<view class="btn" :style='{"background": `${background}`}'>
			<button @click="prepClick" type="primary" size="mini">上一章</button>
			<button @click="backClick" type="warn" size="mini">目录</button>
			<button @click="nextClick" type="primary" size="mini">下一章</button>
			<button @click="showType=!showType" type="primary" size="mini">操作</button>
			<view
				style="position: absolute;padding: 10rpx 0;top: -40px;left: 5%;width: 90%;display: flex;align-items: center;"
				:style='{"background": `${background}`}' v-if="showType">
				<button @click="blackClick" :type="background=='#fff'?'warn':'primary'"
					size="mini">{{background=='#fff'?'暗黑':'光亮'}}</button>
				<button @click="jiaClick" type="primary" size="mini" style="margin: 0 5px;">字体+</button>
				<button @click="jianClick" type="warn" size="mini">字体-</button>
				<button @click="addbookmark" type="primary" size="mini">加入书签</button>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			return {
				detail: {},
				id: '',
				refid: '',
				list: [],
				user: {
					
				},
				background: '#fff',
				color: '#000',
				fontSize: 16,
				showType: false,
				likeType: false,
				likeForm: {},
				allZan: 0,
				collectType: false,
				collectForm: {},
				allCollect: 0,
				progress: 0
			}
		},
		async onLoad(options) {
			this.id = options.id;
			this.refid = options.refid
			this.init()
			this.getList()
			this.user = JSON.parse(uni.getStorageSync('userSession'))
			uni.setNavigationBarColor({
				frontColor: '#000000',
				backgroundColor: '#ffffff',
				animation: {
					duration: 400,
					timingFunc: 'easeIn'
				}
			})
		},
		onPageScroll(e) {
			this.progress = e.scrollTop
		},
		methods: {
			addbookmark(){
				let that = this
				uni.showModal({
					title: '提示',
					content: '是否加入书签？',
					async success(res) {
						if (res.confirm) {
							let params = {
								chapterid: that.detail.id,
								chapternum: that.detail.chapternum,
								chaptertitle: that.detail.chaptertitle,
								progress: that.progress,
								userid: uni.getStorageSync('appUserid'),
							}
							await that.$api.add('bookmarkshujixinxi', params)
							that.$utils.msg('添加成功')
						}
					}
				})
			},
			async getAllZan() {
				let res = await this.$api.list('storeup',{
					page: 1,
					type: 21,
					tablename: 'chaptershujixinxi',
					refid: this.id
				})
				this.allZan = Number(res.data.total)
			},
			// 获取点赞状态
			async getLike() {
				let res = await this.$api.list('storeup', {
					page: 1,
					limit: 1,
					type: 21,
					userid: uni.getStorageSync('appUserid'),
					refid: this.id,
					tablename: 'chaptershujixinxi'
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
						refid: this.id,
						type: 21,
						name: this.detail.chaptertitle,
						picture: '1',
						tablename: 'chaptershujixinxi',
						remark: this.refid
					})
					this.$utils.msg('点赞成功')
					this.getLike()
					this.getAllZan()
				}
			},
			async getAllCollect() {
				let res = await this.$api.list('storeup',{
					page: 1,
					type: 1,
					tablename: 'chaptershujixinxi',
					refid: this.id
				})
				this.allCollect = Number(res.data.total)
			},
			// 获取收藏状态
			async getCollect() {
				let res = await this.$api.list('storeup', {
					page: 1,
					limit: 1,
					type: 1,
					userid: uni.getStorageSync('appUserid'),
					refid: this.id,
					tablename: 'chaptershujixinxi'
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
						content: '是否取消点赞？',
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
						refid: this.id,
						type: 1,
						name: this.detail.chaptertitle,
						picture: '1',
						tablename: 'chaptershujixinxi',
						remark: this.refid
					})
					this.$utils.msg('收藏成功')
					this.getCollect()
					this.getAllCollect()
				}
			},
			jiaClick() {
				this.fontSize++
			},
			jianClick() {
				this.fontSize--
			},
			blackClick() {
				if (this.background == '#fff') {
					this.detail.content = this.detail.content.replace(/color:.*?(.*?).*?;/gi, ``)
					this.background = '#2a2a2a'
					this.color = '#fff'
					uni.setNavigationBarColor({
						frontColor: '#ffffff',
						backgroundColor: '#000000',
						animation: {
							duration: 400,
							timingFunc: 'easeIn'
						}
					})
				} else {
					this.background = '#fff'
					this.color = '#000'
					uni.setNavigationBarColor({
						frontColor: '#000000',
						backgroundColor: '#ffffff',
						animation: {
							duration: 400,
							timingFunc: 'easeIn'
						}
					})
				}
			},
            /**
             * 触摸开始
             * @param {Object} e
             */
            touchStart: function(e) {
                if (e.touches.length == 1) {
                    //设置触摸起始点水平方向位置
                    this.startX = e.touches[0].clientX;
                }
            },
            /**
             * 触摸结束
             * @param {Object} e
             */
            touchEnd: function(e) {
                if (e.changedTouches.length == 1) {
                    //手指移动结束后水平位置
                    var endX = e.changedTouches[0].clientX;
                    let diff = endX - this.startX;
                    if (Math.abs(diff) > 20) {
                        if (diff > 0) {
                            this.prepClick()

                        } else {
                            this.nextClick()
                        }
                    }
                }

            },
			async init() {
				let that = this
				let res = await this.$api.info('chaptershujixinxi', this.id)
				this.detail = res.data;
				this.getLike()
				this.getAllZan()
				this.getCollect()
				this.getAllCollect()
				this.detail.content = this.detail.content.replace(/<img/g, '<img style="width: 100%;"');
				let rs2 = await this.$api.page('bookmarkshujixinxi',{
					chapterid: res.data.id,
					page: 1,
					limit: 1,
					sort: 'addtime',
					order: 'desc',
					userid: uni.getStorageSync('appUserid'),
				})
				if(rs2.data.list.length){
					uni.showModal({
						title: '提示',
						content: '是否跳转到书签位置？',
						async success(obj) {
							if (obj.confirm) {
								uni.pageScrollTo({
									scrollTop: rs2.data.list[0].progress,
									duration: 100,
								})
							}else {
								uni.pageScrollTo({
									scrollTop: 0,
									duration: 100,
								})
							}
						}
					})
				}else {
					uni.pageScrollTo({
						scrollTop: 0,
						duration: 100,
					})
				}
			},
			async getList() {

				let res = await this.$api.list('chaptershujixinxi', {
					refid: this.refid,
					limit: 1000
				})
				this.list = res.data.list
			},
			prepClick() {
				let that = this
				for (let x in this.list) {
					if (this.list[x].id == this.id) {
						if (x == 0) {
							this.$utils.msg('已经是第一章了');
							return false
						}
						this.id = this.list[Number(x) - 1].id
						this.init()
						break;
					}
				}
			},
			backClick() {
				uni.navigateBack({

				})
			},
			nextClick() {
				let that = this
				for (let x in this.list) {
					if (this.list[x].id == this.id) {
						if (x == this.list.length - 1) {
							this.$utils.msg('已经是最后一章了');
							return false
						}
						this.id = this.list[Number(x) + 1].id
						this.init()
						break;
					}
				}
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
		padding: 20upx;
	}

	.content {
		margin: 40upx 40upx 100upx;
		font-size: 30upx;
		line-height: 50upx;
		letter-spacing: 5upx;
		padding-bottom: 80upx;
	}

	.btn {
		background: #fff;
		display: flex;
		align-items: center;
		position: fixed;
		bottom: 0;
		width: 100%;
		left: 0;
		padding: 10rpx 0;
		box-shadow: 0 -2px 10px 0 rgba(0,0,0,.1);
		button {
			font-size: 26upx;
		}
	}
</style>
