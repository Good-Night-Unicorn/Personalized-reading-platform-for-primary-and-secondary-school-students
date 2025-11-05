<template>
<!-- category 1 -->
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
		<view class="content">
			<view :style='{"minHeight":"100vh","width":"100%","padding":"0 24rpx 128rpx","position":"relative","background":"#fff","height":"auto"}'>
				<view class="cu-bar bg-white search" :style='{"width":"calc(100% - 90rpx)","padding":"20rpx 0","background":"none","display":"flex","height":"auto"}'>
					<view :style='{"margin":"0 12rpx","flex":"1","position":"relative"}' class="search-form round">
						<text class="icon iconfont icon-fangdajing07" :style='{"color":"rgb(153, 153, 153)","left":"0px","textAlign":"center","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
						<input placeholder-style="color: #333" :style='{"border":"2rpx solid #ccc","padding":"12rpx 20rpx 12rpx 80rpx","color":"#333","borderRadius":"40rpx","background":"#f5f5f5","width":"100%","lineHeight":"56rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.title" type="text" placeholder="标题" ></input>
					</view>
					<button :style='{"border":"2rpx solid #ccc","padding":"0px","margin":"0","color":"#333","borderRadius":"40rpx","background":"#f5f5f5","width":"136rpx","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
				</view>
			

				<view :style='{"width":"100%","background":"none","height":"auto"}'>
					<zb-table :show-header="true" :columns="column" :stripe="true"
						@rowClick="onUpdateOrDel" :data="list"></zb-table>

			
			
			
			
				</view>
				<button :style='{"border":"0","color":"#fff","right":"10rpx","outline":"none","borderRadius":"100%","top":"20rpx","background":"#a4adb3","width":"80rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"999"}' class="add-btn" @click="screenBoxShow=true">筛</button>
				<button :style='{"border":"0","boxShadow":"0 0px 0px rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","bottom":"120rpx","right":"120rpx","outline":"none","borderRadius":"80rpx","background":"#999","width":"88rpx","lineHeight":"88rpx","fontSize":"28rpx","position":"absolute","height":"88rpx","zIndex":"999"}' v-if="userid && isAuth('popupremind','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","boxShadow":"0 0px 0px rgba(0,0,0,.3)","color":"rgb(255, 255, 255)","bottom":"120rpx","right":"120rpx","outline":"none","borderRadius":"80rpx","background":"#999","width":"88rpx","lineHeight":"88rpx","fontSize":"28rpx","position":"absolute","height":"88rpx","zIndex":"999"}' v-if="!userid && isAuthFront('popupremind','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<view :style='{"top":"0","left":"0","background":"rgba(0, 0, 0, .3)","width":"100%","position":"absolute","height":"100%","zIndex":"665"}' v-if="screenBoxShow" @click="screenBoxShow=false"></view>
				<view class="screenBox" :class="screenBoxShow?'screenBoxActive':''">
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0"}'>
						<view :style='{"width":"100%","padding":"0 0 20rpx 20rpx","fontSize":"24rpx"}'>类型</view>
						<view :style='{"width":"100%","flexWrap":"wrap","justifyContent":"space-around","display":"flex"}'>
							<view @click="typeChange(item)" v-for="(item,index) in typeOptions" :key="index" class="screenTab" :class="searchForm.type==item?'screenTabActive':''">{{item}}</view>
						</view>
					</view>
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0"}'>
						<view :style='{"width":"100%","padding":"0 0 20rpx 20rpx","fontSize":"24rpx"}'>提醒时间</view>
						<view :style='{"width":"100%","alignItems":"center","flexWrap":"wrap","justifyContent":"center","display":"flex"}'>
							<input :style='{"width":"40%","lineHeight":"60rpx","borderRadius":"40rpx","textAlign":"center","background":"#fff","height":"60rpx"}' v-model="searchForm.remindtimestart" placeholder="提醒时间开始时间" @tap="toggleTab('remindtimestart')"></input>
							<view :style='{"padding":"0 10rpx","fontSize":"20rpx"}'>-</view>
							<input :style='{"width":"40%","lineHeight":"60rpx","borderRadius":"40rpx","textAlign":"center","background":"#fff","height":"60rpx"}' v-model="searchForm.remindtimeend" placeholder="提醒时间结束时间" @tap="toggleTab('remindtimeend')"></input>
						</view>
					</view>
					<view :style='{"width":"100%","padding":"40rpx 0 0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<div :style='{"width":"30%","lineHeight":"60rpx","color":"#888","textAlign":"center","background":"#fff","height":"60rpx"}' @click="screenReset">重置</div>
						<div :style='{"width":"30%","lineHeight":"60rpx","color":"#fff","textAlign":"center","background":"#138bff","height":"60rpx"}' @click="search">搜索</div>
					</view>
				</view>
			</view>
			<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="remindtimestartConfirm" ref="remindtimestart" themeColor="#333333"></w-picker>
			<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="remindtimeendConfirm" ref="remindtimeend" themeColor="#333333"></w-picker>
		</view>
	</mescroll-uni>
</template>

<script>
	import zbTable from '@/components/zb-table/zb-table.vue'
	export default {
		data() {
			return {
				typeOptions:[],
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				clicknumColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
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
					title: '',
					type: '',
					remindtimestart: '',
					remindtimeend: '',
				},
				CustomBar: '0',
				listSort: 'id',
				listOrder: 'desc',
				column: [
					{
						name: 'title',
						label: '标题',
						fixed: false,
						width: 80,
					},
					{
						name: 'type',
						label: '类型',
						sorter: false,
						align: 'right',
					},
					{
						name: 'remindtime',
						label: '提醒时间',
						sorter: false,
						align: 'right',
					},
				],
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
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.clicknumColor = this.clicknumColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		async onLoad(options) {
            if(options.userid) {
                this.userid=options.userid;
            } else {
                this.userid = "";
            }
			this.hasNext = true
			// 重新加载数据
			// if (this.mescroll) this.mescroll.resetUpScroll()
			let res = {};
			this.typeOptions = '个人,全局'.split(',')
		},
		components: {
			zbTable,
		},
		methods: {
			typeChange(e){
				this.searchForm.type = e
				this.$forceUpdate()
			},
			remindtimestartConfirm(e){
				this.searchForm.remindtimestart = e.result
				this.$forceUpdate()
			},
			remindtimeendConfirm(e){
				this.searchForm.remindtimeend = e.result
				this.$forceUpdate()
			},
			screenReset(){
				this.searchForm = {}
				this.search()
				this.$forceUpdate()
			},
			toggleTab(e){
				this.$refs[e].show()
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

				if(this.searchForm.title){
					params['title'] = '%' + this.searchForm.title + '%'
				}
				if(this.searchForm.type){
					params['type'] = '%' + this.searchForm.type + '%'
				}
				if(this.searchForm.remindtime){
					params['remindtime'] = '%' + this.searchForm.remindtime + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
                let res = {}
                if(this.userid) {
                    res = await this.$api.page(`popupremind`, params);
                } else {
                    res = await this.$api.list(`popupremind`, params);
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
			onUpdateOrDel(row){
				// #ifdef MP-WEIXIN
				row = row.$orig || row
				// #endif
				let that = this
				let arr = []
				if((this.userid && this.isAuth('popupremind','修改')) || (!this.userid && this.isAuthFront('popupremind','修改'))){
					arr.push('修改')
				}
				if((this.userid && this.isAuth('popupremind','删除')) || (!this.userid && this.isAuthFront('popupremind','删除'))){
					arr.push('删除')
				}
				if(!arr.length){
					return false
				}
				uni.showActionSheet({
					itemList: arr,
					success(res) {
						if(arr[res.tapIndex]=='修改'){
							that.onUpdateTap(row)
						}else if(arr[res.tapIndex]=='删除'){
							that.onDeleteTap(row.id)
						}
					}
				})
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
							await _this.$api.del('popupremind', JSON.stringify([id]));
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

				if(this.searchForm.title){
					searchForm['title'] = '%' + this.searchForm.title + '%'
				}
				if(this.searchForm.type){
					searchForm['type'] = '%' + this.searchForm.type + '%'
				}
				if(this.searchForm.remindtimestart){
					searchForm['remindtimestart'] = this.searchForm.remindtimestart
				}
				if(this.searchForm.remindtimeend){
					searchForm['remindtimeend'] = this.searchForm.remindtimeend
				}
                let res = {};
                if(this.userid) {
                    res = await this.$api.page(`popupremind`, searchForm);
                } else {
                    res = await this.$api.list(`popupremind`, searchForm);
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
