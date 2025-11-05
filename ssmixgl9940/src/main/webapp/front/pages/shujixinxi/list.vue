<template>
<!-- category 1 -->
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
		<view class="content">
			<view :style='{"minHeight":"100vh","width":"100%","padding":"0 24rpx 128rpx","position":"relative","background":"#fff","height":"auto"}'>
				<view class="cu-bar bg-white search" :style='{"width":"calc(100% - 90rpx)","padding":"20rpx 0","background":"none","display":"flex","height":"auto"}'>
					<view :style='{"margin":"0 12rpx","flex":"1","position":"relative"}' class="search-form round">
						<text class="icon iconfont icon-fangdajing07" :style='{"color":"rgb(153, 153, 153)","left":"0px","textAlign":"center","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
						<input placeholder-style="color: #333" :style='{"border":"2rpx solid #ccc","padding":"12rpx 20rpx 12rpx 80rpx","color":"#333","borderRadius":"40rpx","background":"#f5f5f5","width":"100%","lineHeight":"56rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.shujimingcheng" type="text" placeholder="书籍名称" ></input>
					</view>
					<button :style='{"border":"2rpx solid #ccc","padding":"0px","margin":"0","color":"#333","borderRadius":"40rpx","background":"#f5f5f5","width":"136rpx","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
				</view>
			

				<view :style='{"padding":"20rpx","margin":"0px auto","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","justifyContent":"center"}'>
					<view @click="sortClick('addtime')" :style='{"border":"0","padding":"0 12rpx","margin":"0 8rpx 0 0","outline":"0","borderRadius":"8rpx","background":"none","display":"flex"}'>
						<text :style='{"color":"inherit","lineHeight":"48rpx","fontSize":"inherit"}'>按日期</text>
						<text v-if="listSort!='addtime'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"inherit","color":"inherit"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='asc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"inherit","color":"inherit"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='desc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"inherit","color":"inherit"}'></text>
					</view>
				</view>
				<view :style='{"width":"100%","background":"none","height":"auto"}'>
					<scroll-view scroll-x="true" class="category-one" :style='{"width":"100%","margin":"0 auto","whiteSpace":"nowrap","background":"none","display":"flex","height":"auto"}'>
						<view :class='categoryName === item.shujifenlei ? "active" : ""' class="tab" v-for="(item,index) in categoryList" :key="index" @tap="categoryClick(item.shujifenlei)">{{item.shujifenlei}}</view>
					</scroll-view>
					<!-- 样式1 -->
					<view class="uni-product-list" :style='{"padding":"0","margin":"40rpx 0 0","color":"#888","flexWrap":"wrap","display":"flex","width":"100%","fontSize":"26rpx","justifyContent":"space-between","height":"auto"}'>
						<view @tap="onDetailTap(product)" class="uni-product" :style='{"border":"2rpx solid #ccc","padding":"20rpx 10rpx","margin":"0 0 40rpx","backgroundColor":"#fff","flexWrap":"wrap","display":"flex","width":"48%","height":"auto"}' v-for="(product,index) in list" :key="index">
							<view class="uni-product-title" :style='{"padding":"0px 0","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","textOverflow":"ellipsis"}'>{{product.shujimingcheng}}</view>
							<view class="uni-product-title" :style='{"padding":"0px 0","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","textOverflow":"ellipsis"}'>{{product.shujifenlei}}</view>
							<image :style='{"padding":"0","margin":"0 0 6rpx","objectFit":"cover","display":"block","width":"100%","height":"240rpx","order":"-1"}' mode="aspectFill" class="uni-product-image" v-if="preHttp(product.tupian)" :src="product.tupian.split(',')[0]"></image>
							<image :style='{"padding":"0","margin":"0 0 6rpx","objectFit":"cover","display":"block","width":"100%","height":"240rpx","order":"-1"}' mode="aspectFill" class="uni-product-image" v-else :src="product.tupian?baseUrl+product.tupian.split(',')[0]:''"></image>

							<view :style='{"margin":"0 10rpx 0 0"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit","color":"inherit"}'></text>
								<text :style='{"color":"inherit","lineHeight":"1.5","fontSize":"inherit"}'>{{product.addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
							</view>
							<view :style='{"margin":"0 10rpx 0 0"}'>
								<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit","color":"inherit"}'></text>
								<text :style='{"color":"inherit","lineHeight":"1.5","fontSize":"inherit"}'>{{product.zuozhezhanghao}}</text>
							</view>
							<view :style='{"margin":"0 10rpx 0 0"}'>
								<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"inherit","color":"inherit"}'></text>
								<text :style='{"color":"inherit","lineHeight":"1.5","fontSize":"inherit"}'>{{product.storeupnum}}</text>
							</view>
							<!-- #ifdef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shujixinxi','修改')) || (!userid && isAuthFront('shujixinxi','修改'))" @tap.stop.proevent="onUpdate" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shujixinxi','删除')) || (!userid && isAuthFront('shujixinxi','删除'))" @tap.stop.proevent="onDelete" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
							<!-- #ifndef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shujixinxi','修改')) || (!userid && isAuthFront('shujixinxi','修改'))" @tap.stop.proevent="onUpdateTap(product)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shujixinxi','删除')) || (!userid && isAuthFront('shujixinxi','删除'))" @tap.stop.proevent="onDeleteTap(product.id)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
						</view>
					</view>
			
			
			


			
			
			
			
				</view>
				<button :style='{"border":"0","color":"#fff","right":"10rpx","outline":"none","borderRadius":"100%","top":"20rpx","background":"#a4adb3","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"999"}' class="add-btn" @click="screenBoxShow=true">筛</button>
				<button :style='{"border":"0","boxShadow":"0 0px 0px rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","bottom":"120rpx","right":"120rpx","outline":"none","borderRadius":"80rpx","background":"#999","width":"88rpx","lineHeight":"88rpx","fontSize":"28rpx","position":"absolute","height":"88rpx","zIndex":"999"}' v-if="userid && isAuth('shujixinxi','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","boxShadow":"0 0px 0px rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","bottom":"120rpx","right":"120rpx","outline":"none","borderRadius":"80rpx","background":"#999","width":"88rpx","lineHeight":"88rpx","fontSize":"28rpx","position":"absolute","height":"88rpx","zIndex":"999"}' v-if="!userid && isAuthFront('shujixinxi','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<view :style='{"top":"0","left":"0","background":"rgba(0, 0, 0, .3)","width":"100%","position":"absolute","height":"100%","zIndex":"665"}' v-if="screenBoxShow" @click="screenBoxShow=false"></view>
				<view class="screenBox" :class="screenBoxShow?'screenBoxActive':''">
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<view :style='{"width":"auto","padding":"0 20rpx 0 20rpx","fontSize":"24rpx","whiteSpace":"nowrap"}'>年份</view>
						<input :style='{"border":"0","padding":"0 20rpx 0 20rpx","margin":"0 16rpx 0 0","borderRadius":"40rpx","background":"#fff","flex":"1","width":"75%","fontSize":"28rpx","height":"60rpx"}' placeholder="请输入年份" v-model="searchForm.nianfen">
					</view>
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0"}'>
						<view :style='{"width":"100%","padding":"0 0 20rpx 20rpx","fontSize":"24rpx"}'>出版社</view>
						<view :style='{"width":"100%","flexWrap":"wrap","justifyContent":"space-around","display":"flex"}'>
							<view @click="chubansheChange(item)" v-for="(item,index) in chubansheOptions" :key="index" class="screenTab" :class="searchForm.chubanshe==item?'screenTabActive':''">{{item}}</view>
						</view>
					</view>
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<view :style='{"width":"auto","padding":"0 20rpx 0 20rpx","fontSize":"24rpx","whiteSpace":"nowrap"}'>ISBM</view>
						<input :style='{"border":"0","padding":"0 20rpx 0 20rpx","margin":"0 16rpx 0 0","borderRadius":"40rpx","background":"#fff","flex":"1","width":"75%","fontSize":"28rpx","height":"60rpx"}' placeholder="请输入ISBM" v-model="searchForm.bianhao">
					</view>
					<view :style='{"width":"100%","padding":"40rpx 0 0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<div :style='{"width":"30%","lineHeight":"60rpx","color":"#888","textAlign":"center","background":"#fff","height":"60rpx"}' @click="screenReset">重置</div>
						<div :style='{"width":"30%","lineHeight":"60rpx","color":"#fff","textAlign":"center","background":"#138bff","height":"60rpx"}' @click="search">搜索</div>
					</view>
				</view>
			</view>
		</view>
	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
				chubansheOptions:[],
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				list: [],
				lists: [],
                userid: '',
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				searchForm:{
					shujimingcheng: '',
					nianfen: '',
					chubanshe: '',
					bianhao: '',
				},
				categoryList:[],
				categoryName :'全部',
				CustomBar: '0',
				listSort: 'id',
				listOrder: 'desc',
				screenBoxShow: false,
			};
		},
		watch: {
		},
		mounted() {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onShow() {
            if(uni.getStorageSync("useridTag")==1){
                this.userid=uni.getStorageSync("useridTag");
                uni.removeStorageSync("useridTag");
            } else {
                this.userid = "";
            }
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
            let res = {};
            if(this.userid) {
                res = await this.$api.page('shujifenlei', {page:1,limit:100});
            } else {
                res = await this.$api.list('shujifenlei', {page:1,limit:100});
            }
			res.data.list.splice(0,0,{id:0,shujifenlei:'全部'})
			this.categoryList = res.data.list;
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		async onLoad(options) {
            if(uni.getStorageSync("useridTag")==1){
                this.userid=uni.getStorageSync("useridTag");
                uni.removeStorageSync("useridTag");
            } else {
                this.userid = "";
            }
			this.hasNext = true
			// 重新加载数据
			// if (this.mescroll) this.mescroll.resetUpScroll()
			let res = {};
			this.chubansheOptions = '北京教育出版社,中信出版社,人民文学出版社,人民邮电出版社,陕西人民教育出版社,北京联合出版公司,汕头大学出版社,辽宁人民出版社,机械工业出版社,接力出版社'.split(',')
		},
		components: {
		},
		methods: {
			chubansheChange(e){
				this.searchForm.chubanshe = e
				this.$forceUpdate()
			},
			screenReset(){
				this.searchForm = {}
				this.search()
				this.$forceUpdate()
			},
			uGetRect(selector, all) {
				return new Promise(resolve => {
					uni.createSelectorQuery()
					.in(this)
					[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect(rect => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect);
						}
						if (!all && rect) {
							resolve(rect);
						}
					})
					.exec();
				});
			},
			cloneData(data) {
				return JSON.parse(JSON.stringify(data));
			},
			sortClick(type){
				if(this.listSort==type){
					if(this.listOrder == 'desc'){
						this.listOrder = 'asc'
					}else{
						this.listOrder = 'desc'
					}
				}else{
					this.listSort = type
					this.listOrder = 'desc'
				}
				this.search()
			},
            priceChange(price) {
                return Number(price).toFixed(2);
            },
            preHttp(str) {
                return str && str.substr(0,4)=='http';
            },
			//类别搜索
			categoryClick(categoryName){
				this.categoryName = categoryName;
				this.mescroll.resetUpScroll();
			},
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
				let params = {
					page: mescroll.num,
					limit: mescroll.size,
				}
				params['sort'] = this.listSort;
				params['order'] = this.listOrder;

				if(this.categoryName!='全部'){
					params.shujifenlei = '%' + this.categoryName + '%'
				}
				if(this.searchForm.shujimingcheng){
					params['shujimingcheng'] = '%' + this.searchForm.shujimingcheng + '%'
				}
				if(this.searchForm.nianfen){
					params['nianfen'] = '%' + this.searchForm.nianfen + '%'
				}
				if(this.searchForm.chubanshe){
					params['chubanshe'] = '%' + this.searchForm.chubanshe + '%'
				}
				if(this.searchForm.bianhao){
					params['bianhao'] = '%' + this.searchForm.bianhao + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
                let res = {}
                if(this.userid) {
                    res = await this.$api.page(`shujixinxi`, params);
                } else {
                    res = await this.$api.list(`shujixinxi`, params);
                }

				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				this.$forceUpdate()
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(item) {
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			onUpdate(e){
				this.onUpdateTap(e.currentTarget.dataset.row)
			},
			// 修改
			onUpdateTap(row){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update?id=${row.id}`)
			},
			// 添加
			onAddTap(){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update`)
			},
			onDelete(e){
				this.onDeleteTap(e.currentTarget.dataset.row.id)
			},
			onDeleteTap(id){
				var _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await _this.$api.del('shujixinxi', JSON.stringify([id]));
							let obj = await _this.$api.list('storeup',{
								page: 1,
								limit: 100,
								tablename: 'shujixinxi',
								refid: id,
							})
							if(obj.data.list.length){
								let arr = []
								for(let x in obj.data.list){
									arr.push(obj.data.list[x].id)
								}
								await _this.$api.del('storeup',JSON.stringify(arr))
							}
							_this.$utils.msg('删除成功');
							_this.hasNext = true
							// 重置分页参数页数为1
							_this.search()
						}
					}
				});
			},
			// 搜索
			async search(){
				this.mescroll.num = 1
				let searchForm = {
					page: this.mescroll.num,
					limit: this.mescroll.size,
				}
				searchForm['sort'] = this.listSort;
				searchForm['order'] = this.listOrder;

				if(this.searchForm.shujimingcheng){
					searchForm['shujimingcheng'] = '%' + this.searchForm.shujimingcheng + '%'
				}
				if(this.categoryName!='全部'){
					searchForm.shujifenlei = '%' + this.categoryName + '%'
				}
				if(this.searchForm.nianfen){
					searchForm['nianfen'] = '%' + this.searchForm.nianfen + '%'
				}
				if(this.searchForm.chubanshe){
					searchForm['chubanshe'] = '%' + this.searchForm.chubanshe + '%'
				}
				if(this.searchForm.bianhao){
					searchForm['bianhao'] = '%' + this.searchForm.bianhao + '%'
				}
                let res = {};
                if(this.userid) {
                    res = await this.$api.page(`shujixinxi`, searchForm);
                } else {
                    res = await this.$api.list(`shujixinxi`, searchForm);
                }
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
				this.screenBoxShow = false
			}
		}
	};
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.category-one .tab {
		cursor: pointer;
		padding: 0 0px;
		margin: 0 40rpx 0 0;
		color: #333;
		background: #fff;
		display: inline-block;
		width: auto;
		font-size: 30rpx;
		border-color: #fff;
		border-width: 0 0 6rpx;
		line-height: 80rpx;
		border-style: solid;
	}
	
	.category-one .tab.active {
		cursor: pointer;
		padding: 0 0px;
		margin: 0 40rpx 0 0;
		color: #000;
		background: none;
		display: inline-block;
		width: auto;
		font-size: 30rpx;
		border-color: #333;
		border-width: 0 0 6rpx;
		line-height: 80rpx;
		border-style: solid;
	}
	.screenBox {
		padding: 120rpx 0 20rpx 0;
		transform: translate3d(100%, 0, 0);
		z-index: 6666;
		top: 0;
		background: #a7cff9;
		width: 80%;
		position: fixed;
		right: 0;
		transition: transform .3s;
		height: 100%;
		.screenTab {
			border-radius: 40rpx;
			margin: 10rpx 0 10rpx 0;
			background: #fff;
			width: calc(100% / 3 - 24rpx);
			line-height: 72rpx;
			text-align: center;
		}
		.screenTabActive {
			border-radius: 40rpx;
			margin: 10rpx 0 10rpx 0;
			background: #138bff;
			width: calc(100% / 3 - 20rpx);
			line-height: 72rpx;
			text-align: center;
		}
	}
	.screenBoxActive {
		transform: translate3d(0, 0, 0);
	}
</style>
