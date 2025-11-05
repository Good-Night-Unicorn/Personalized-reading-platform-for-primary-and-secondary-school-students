<template>
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
		<!--header end-->
		<view class="list" :style='{"padding":"20rpx 20rpx"}'>
			<view v-for="(item,index) in list " v-bind:key="index" :style='{"border":"2rpx solid #eee","padding":"20rpx","margin":"0 0 20rpx","borderRadius":"20rpx","background":"#fff","display":"flex","width":"100%","justifyContent":"space-between"}'>
				<image v-if="item.cover" :src="baseUrl + item.cover.split(',')[0]" mode="widthFix" :style='{"width":"176rpx","margin":"10rpx 0 0","borderRadius":"10rpx","height":"176rpx"}'></image>
				<view :style='{"width":"calc(100% - 216rpx)"}'>
					<view :style='{"margin":"0 0 16rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#000","width":"100%","fontSize":"30rpx","textOverflow":"ellipsis"}'>{{item.title}}</view>
					<view :style='{"width":"100%","margin":"0 0 16rpx"}'>
						<button v-if="item.isdone=='开放'" :style='{"padding":"0 20rpx","color":"#fff","borderRadius":"10rpx","background":"#999","display":"inline-flex","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}'>公开</button>
						<button v-if="item.isdone=='关闭'" :style='{"padding":"0 20rpx","color":"#fff","borderRadius":"10rpx","background":"#888","display":"inline-flex","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}'>私人</button>
					</view>
					<view :style='{"width":"100%","margin":"0 0 16rpx"}' v-if="item.delflag==1" style="color: #f00;font-weight: bold;font-size: 12px">
						违规删除
					</view>
					<view :style='{"color":"#999"}'>
						{{item.typename}}
					</view>
					<view :style='{"color":"#999"}'>
						发布时间：{{item.addtime.split(' ')[0]}}					</view>
					<view :style='{"width":"100%","margin":"20rpx 0","display":"flex"}'>
						<button @tap="onDetailTap(item.id)" :style='{"padding":"0 30rpx","color":"#fff","borderRadius":"10rpx","background":"#666","lineHeight":"66rpx","fontSize":"28rpx","height":"66rpx"}'>查看</button>
						<button @tap="onUpdateTap(item.id)" :style='{"padding":"0 30rpx","margin":"0 0 0 20rpx","color":"#fff","borderRadius":"10rpx","background":"#999","lineHeight":"66rpx","fontSize":"28rpx","height":"66rpx"}'>修改</button>
						<button @tap="onDeleteTap(item.id)" :style='{"padding":"0 30rpx","margin":"0 0 0 20rpx","color":"#fff","borderRadius":"10rpx","background":"#ccc","lineHeight":"66rpx","fontSize":"28rpx","height":"66rpx"}'>删除</button>
					</view>
				</view>
			</view>
		</view>
		<!--list end-->
		<button :style='{"padding":"0 30rpx","margin":"40rpx 10rpx 20rpx 0","color":"#fff","borderRadius":"40rpx","textAlign":"center","background":"#999","width":"auto","lineHeight":"68rpx","float":"right","height":"68rpx"}' @click="onUpdateTap()">新增</button>
	</mescroll-uni>
</template>

<script>
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	import uniNoticeBar from "@/components/uni-ui/lib/uni-notice-bar/uni-notice-bar.vue"
	export default {
		components: {
			uniIcons,
			uniNoticeBar
		},
		data() {
			return {
				//轮播
				swiperList: [],
				list: [],
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					page: {
						// num: 0, // 当前页码,默认0,回调之前会加1,即callback(page)会从1开始
						// size: 5 // 每页数据的数量
					},
					noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true
			}
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		async onShow() {
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		methods: {
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let res = await this.$api.myForum(mescroll.num, mescroll.size);
				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(id) {
				this.$utils.jump(`../forum-detail/forum-detail?id=${id}`)
			},
			onUpdateTap(id) {
				if(id){
					this.$utils.jump(`../forum-add-or-update/forum-add-or-update?id=${id}`)
				}else{
					this.$utils.jump(`../forum-add-or-update/forum-add-or-update`)
				}
			},
			onDeleteTap(id) {
				let _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await _this.$api.del('forum',JSON.stringify([id]));
							let rs2 = await _this.$api.list('storeup',{
								page: 1,
								limit: 100,
								tablename: 'forum',
								refid: id,
							})
							if(rs2.data.list.length){
								let arr = []
								for(let x in rs2.data.list){
									arr.push(rs2.data.list[x].id)
								}
								await _this.$api.del('storeup',JSON.stringify(arr))
							}
							_this.$utils.msg('删除成功');
							_this.hasNext = true
							// 重新加载数据
							if (_this.mescroll) _this.mescroll.resetUpScroll()
						}
					}
				});
			}
		}
	}
</script>

<style>
	view {
		font-family: '\5FAE\8F6F\96C5\9ED1';
		font-size: 30upx;
	}

	page {
		background: #ededed;
	}

	.header {
		background: #f39800;
		padding: 15upx 20upx 255upx;
		position: relative;
	}

	.ssbox {
		background: rgba(255, 255, 255, 0.35);
		width: 530upx;
		border-radius: 60rpx;
		padding: 10upx 15upx;
		box-sizing: border-box;
	}

	.ss_input {
		border: none;
		width: 450upx;
		font-size: 30upx;
		color: #fff;
		background: none;
		height: 45upx;
		line-break: 45upx;
	}

	.headerb {
		position: absolute;
		left: 0;
		top: 60upx;
		width: 100%;
		box-sizing: border-box;
		padding: 0 20upx;
	}

	.headerb image {
		width: 710upx;
		box-shadow: 0 10upx 10px rgba(0, 0, 0, 0.3);
		border-radius: 40upx;
		position: relative;
		z-index: 10;
	}

	.notice {
		position: relative;
		z-index: 5;
		padding: 0 50upx;
	}

	.noticem {
		background: #fff;
		padding: 55upx 30upx 15upx;
		border-radius: 10upx;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
		margin-top: -45upx;
	}

	.noticer {
		width: 480upx;
		height: 50upx;
	}

	.noticer .swiper-item {
		white-space: nowrap;
		text-overflow: ellipsis;
		overflow: hidden;
		height: 50upx;
		line-height: 50upx;
		font-size: 24upx;
	}

	.list {
		padding: 60upx 20upx 20upx;
	}

	.listm {
		background: #fff;
		border-radius: 15upx;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
		margin-bottom: 20upx;
		padding: 30upx;
	}

	.listmpic {
		border-radius: 10upx;
		width: 166upx;
	}

	.listmr {
		width: 460upx;
		display: inline-block;
	}

	/* #ifdef MP-WEIXIN */
	.noticer .swiper-item {
		margin-top: 5upx;
	}

	/* #endif */
	/* #ifdef MP-BAIDU */
	.noticer .swiper-item {
		margin-top: 3upx;
	}

	/* #endif */
	/* #ifdef MP-ALIPAY */
	.noticer .swiper-item {
		margin-top: 2upx;
	}

	/* #endif */
	/* #ifdef MP-QQ */
	.noticer .swiper-item {
		margin-top: 4upx;
	}

	/* #endif */
	/* #ifdef MP-TOUTIAO */
	.noticer .swiper-item {
		margin-top: 4upx;
	}

	.add-btn {
		position: fixed;
		left: 30upx;
		right: 30upx;
		bottom: 120upx;
		z-index: 95;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		font-size: 32upx;
		color: #fff;
		background-color: red;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
	/* #endif */
</style>
