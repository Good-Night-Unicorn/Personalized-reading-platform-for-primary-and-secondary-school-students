<template>
<view class="content">
	<view class="box" :style='{"minHeight":"100vh","width":"100%","padding":"48rpx 24rpx 24rpx","background":"url(http://codegen.caihongy.cn/20241211/73f47ee0ebab4dbaa00adc26ff65743f.jpg) no-repeat center top / cover,#fff","height":"auto"}'>
		<view :style='{"border":"2rpx solid #fff","padding":"24rpx 80rpx 0 20rpx","margin":"240rpx 0 0","borderRadius":"20rpx","textAlign":"center","background":"rgba(255,255,255,.3)","display":"block","width":"100%","position":"relative","height":"auto"}'>
			<image :style='{"margin":"0 auto 24rpx auto","borderRadius":"8rpx","top":"-240rpx","left":"33%","display":"block","width":"240rpx","position":"absolute","height":"200rpx"}' src="http://codegen.caihongy.cn/20241210/a070a7bee956481aa8b99c71013ebc1b.png" mode="aspectFill"></image>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">用户名：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.yonghuming"  type="text"  class="uni-input" name="" placeholder="用户名" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">密码：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.mima" type="password"  class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">确认密码：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">家长姓名：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.jiazhangxingming"  type="text"  class="uni-input" name="" placeholder="家长姓名" />
			</view>
            <view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" @tap="jiazhangtouxiangTap" class="">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">头像：</view>
				<image :style='{"width":"180rpx","margin":"0px 0 0","borderRadius":"8rpx","objectFit":"cover","display":"block","height":"120rpx"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
                <image :style='{"width":"180rpx","margin":"0px 0 0","borderRadius":"8rpx","objectFit":"cover","display":"block","height":"120rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
            </view>
            <view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">性别：</view>
				<picker :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 180rpx)","height":"auto"}' v-if="tableName=='jiazhang'"  @change="jiazhangxingbieChange" :value="jiazhangxingbieIndex" :range="jiazhangxingbieOptions">
					<view>
						<view :style='{"border":"2rpx solid #666","padding":"0 20rpx","color":"#666","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
					</view>
				</picker>
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">年龄：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.nianling"  type="text"  class="uni-input" name="" placeholder="年龄" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">邮箱：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.youxiang"  type="text"  class="uni-input" name="" placeholder="邮箱" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='jiazhang'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">手机：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.shouji"  type="text"  class="uni-input" name="" placeholder="手机" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">学号：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.xuehao"  type="text"  class="uni-input" name="" placeholder="学号" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">密码：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.mima" type="password"  class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">确认密码：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">姓名：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.xingming"  type="text"  class="uni-input" name="" placeholder="姓名" />
			</view>
            <view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">性别：</view>
				<picker :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 180rpx)","height":"auto"}' v-if="tableName=='xuesheng'"  @change="xueshengxingbieChange" :value="xueshengxingbieIndex" :range="xueshengxingbieOptions">
					<view>
						<view :style='{"border":"2rpx solid #666","padding":"0 20rpx","color":"#666","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
					</view>
				</picker>
			</view>
            <view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" @tap="xueshengtouxiangTap" class="">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">头像：</view>
				<image :style='{"width":"180rpx","margin":"0px 0 0","borderRadius":"8rpx","objectFit":"cover","display":"block","height":"120rpx"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
                <image :style='{"width":"180rpx","margin":"0px 0 0","borderRadius":"8rpx","objectFit":"cover","display":"block","height":"120rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
            </view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">邮箱：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.youxiang"  type="text"  class="uni-input" name="" placeholder="邮箱" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">手机：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.shouji"  type="text"  class="uni-input" name="" placeholder="手机" />
			</view>
            <view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">用户名：</view>
				<picker :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 180rpx)","height":"auto"}' v-if="tableName=='xuesheng'"  @change="xueshengyonghumingChange" :value="xueshengyonghumingIndex" :range="xueshengyonghumingOptions">
					<view>
						<view :style='{"border":"2rpx solid #666","padding":"0 20rpx","color":"#666","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' class="uni-input">{{ruleForm.yonghuming?ruleForm.yonghuming:"请选择用户名"}}</view>
					</view>
				</picker>
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='xuesheng'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">家长姓名：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' :disabled="true" v-model="ruleForm.jiazhangxingming"  type="text"  class="uni-input" name="" placeholder="家长姓名" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">作者账号：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.zuozhezhanghao"  type="text"  class="uni-input" name="" placeholder="作者账号" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">密码：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.mima" type="password"  class="uni-input" name="" placeholder="密码" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">确认密码：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' v-model="ruleForm.mima2" type="password" class="uni-input" name="" placeholder="确认密码" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">作者姓名：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.zuozhexingming"  type="text"  class="uni-input" name="" placeholder="作者姓名" />
			</view>
            <view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" @tap="zuozhetouxiangTap" class="">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">头像：</view>
				<image :style='{"width":"180rpx","margin":"0px 0 0","borderRadius":"8rpx","objectFit":"cover","display":"block","height":"120rpx"}' v-if="ruleForm.touxiang" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
                <image :style='{"width":"180rpx","margin":"0px 0 0","borderRadius":"8rpx","objectFit":"cover","display":"block","height":"120rpx"}' v-else class="avator" src="../../static/gen/upload.png" mode=""></image>
            </view>
            <view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">性别：</view>
				<picker :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 180rpx)","height":"auto"}' v-if="tableName=='zuozhe'"  @change="zuozhexingbieChange" :value="zuozhexingbieIndex" :range="zuozhexingbieOptions">
					<view>
						<view :style='{"border":"2rpx solid #666","padding":"0 20rpx","color":"#666","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}' class="uni-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
					</view>
				</picker>
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">年龄：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.nianling"  type="text"  class="uni-input" name="" placeholder="年龄" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">邮箱：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.youxiang"  type="text"  class="uni-input" name="" placeholder="邮箱" />
			</view>
			<view :style='{"border":"0","margin":"0 0 24rpx 0","borderRadius":"20rpx","flexWrap":"wrap","background":"none","display":"flex","width":"calc(100% - 0px)","height":"auto"}' v-if="tableName=='zuozhe'" class="uni-form-item uni-column">
				<view :style='{"width":"auto","lineHeight":"80rpx","fontSize":"28rpx","minWidth":"180rpx","color":"#333","textAlign":"right"}' class="label">手机：</view>
				<input :style='{"border":"2rpx solid #666","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","textAlign":"left","flex":"1","background":"rgba(255, 255, 255,.8)","fontSize":"28rpx","lineHeight":"80rpx","height":"80rpx"}'  v-model="ruleForm.shouji"  type="text"  class="uni-input" name="" placeholder="手机" />
			</view>
			<button :style='{"border":"0","padding":"0 40rpx","margin":"24rpx auto","color":"rgb(255, 255, 255)","borderRadius":"8rpx","background":"#a4adb3","display":"inline-block","width":"calc(100% - 0px)","lineHeight":"88rpx","fontSize":"36rpx","minWidth":"200rpx","height":"88rpx"}' class="btn-submit" @tap="register" type="primary">注册</button>
			
			<view class="idea1" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea1</view>
			<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
			<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
		</view>
	</view>
</view>
</template>

<script>
    import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
                jiazhangxingbieOptions: [],
                jiazhangxingbieIndex: 0,
                xueshengxingbieOptions: [],
                xueshengxingbieIndex: 0,
                xueshengyonghumingOptions: [],
                xueshengyonghumingIndex: 0,
                zuozhexingbieOptions: [],
                zuozhexingbieIndex: 0,
				ruleForm: {
                yonghuming: '',
                mima: '',
                jiazhangxingming: '',
                touxiang: '',
                xingbie: '',
                nianling: '',
                youxiang: '',
                shouji: '',
                xuehao: '',
                mima: '',
                xingming: '',
                xingbie: '',
                touxiang: '',
                youxiang: '',
                shouji: '',
                yonghuming: '',
                jiazhangxingming: '',
                zuozhezhanghao: '',
                mima: '',
                zuozhexingming: '',
                touxiang: '',
                xingbie: '',
                nianling: '',
                youxiang: '',
                shouji: '',
                status: '',
				},
				tableName:""
			}
		},
        components: {
            multipleSelect
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
		async onLoad() {
			let res = [];
			let table = uni.getStorageSync("loginTable");
            this.tableName = table;

                        // 自定义下拉框值
			if(this.tableName=='jiazhang'){
                this.jiazhangxingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.jiazhangxingbieOptions[0]
			}
                        // 自定义下拉框值
			if(this.tableName=='xuesheng'){
                this.xueshengxingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.xueshengxingbieOptions[0]
			}
            res = await this.$api.option(`jiazhang`,`yonghuming`,{});
            this.xueshengyonghumingOptions = res.data;
                        // 自定义下拉框值
			if(this.tableName=='zuozhe'){
                this.zuozhexingbieOptions = "男,女".split(',');
				this.ruleForm.xingbie=this.zuozhexingbieOptions[0]
			}
			
			this.styleChange()
		},
		methods: {

            jiazhangtouxiangTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
                });
            },
            // 下拉变化
            jiazhangxingbieChange(e) {
                    this.jiazhangxingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.jiazhangxingbieOptions[this.jiazhangxingbieIndex]
            },
            // 下拉变化
            xueshengxingbieChange(e) {
                    this.xueshengxingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.xueshengxingbieOptions[this.xueshengxingbieIndex]
            },
            xueshengtouxiangTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
                });
            },
            zuozhetouxiangTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
                });
            },
            // 下拉变化
            zuozhexingbieChange(e) {
                    this.zuozhexingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.zuozhexingbieOptions[this.zuozhexingbieIndex]
            },

            // 下二随
            async xueshengyonghumingChange (e) {
                this.xueshengyonghumingIndex = e.target.value
                this.ruleForm.yonghuming = this.xueshengyonghumingOptions[this.xueshengyonghumingIndex]
                let res = await this.$api.follow(`jiazhang`, `yonghuming`,{
                    columnValue: this.ruleForm.yonghuming
                });
                if(res.data.jiazhangxingming){
                    this.ruleForm.jiazhangxingming = res.data.jiazhangxingming
                }
            },
            toggleTab(str) {
                this.$refs[str].show();
            },

			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.registerFrom.content.input.backgroundColor
					// })
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 注册
			async register() {
				
				if((!this.ruleForm.yonghuming) && `jiazhang` == this.tableName){
					this.$utils.msg(`用户名不能为空`);
					return
				}
				if((!this.ruleForm.mima) && `jiazhang` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
                if(`jiazhang` == this.tableName && (this.ruleForm.mima!=this.ruleForm.mima2)){
                    this.$utils.msg(`两次密码输入不一致`);
                    return
                }
				if((!this.ruleForm.jiazhangxingming) && `jiazhang` == this.tableName){
					this.$utils.msg(`家长姓名不能为空`);
					return
				}
				if(`jiazhang` == this.tableName && this.ruleForm.nianling&&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
					this.$utils.msg(`年龄应输入整数`);
					return
				}
				if(`jiazhang` == this.tableName && this.ruleForm.youxiang&&(!this.$validate.isEmail(this.ruleForm.youxiang))){
					this.$utils.msg(`邮箱应输入邮件格式`);
					return
				}
				if(`jiazhang` == this.tableName && this.ruleForm.shouji&&(!this.$validate.isMobile(this.ruleForm.shouji))){
					this.$utils.msg(`手机应输入手机格式`);
					return
				}
				if((!this.ruleForm.xuehao) && `xuesheng` == this.tableName){
					this.$utils.msg(`学号不能为空`);
					return
				}
				if((!this.ruleForm.mima) && `xuesheng` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
                if(`xuesheng` == this.tableName && (this.ruleForm.mima!=this.ruleForm.mima2)){
                    this.$utils.msg(`两次密码输入不一致`);
                    return
                }
				if((!this.ruleForm.xingming) && `xuesheng` == this.tableName){
					this.$utils.msg(`姓名不能为空`);
					return
				}
				if(`xuesheng` == this.tableName && this.ruleForm.youxiang&&(!this.$validate.isEmail(this.ruleForm.youxiang))){
					this.$utils.msg(`邮箱应输入邮件格式`);
					return
				}
				if(`xuesheng` == this.tableName && this.ruleForm.shouji&&(!this.$validate.isMobile(this.ruleForm.shouji))){
					this.$utils.msg(`手机应输入手机格式`);
					return
				}
				if((!this.ruleForm.yonghuming) && `xuesheng` == this.tableName){
					this.$utils.msg(`用户名不能为空`);
					return
				}
				if((!this.ruleForm.zuozhezhanghao) && `zuozhe` == this.tableName){
					this.$utils.msg(`作者账号不能为空`);
					return
				}
				if((!this.ruleForm.mima) && `zuozhe` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}
                if(`zuozhe` == this.tableName && (this.ruleForm.mima!=this.ruleForm.mima2)){
                    this.$utils.msg(`两次密码输入不一致`);
                    return
                }
				if((!this.ruleForm.zuozhexingming) && `zuozhe` == this.tableName){
					this.$utils.msg(`作者姓名不能为空`);
					return
				}
				if(`zuozhe` == this.tableName && this.ruleForm.nianling&&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
					this.$utils.msg(`年龄应输入整数`);
					return
				}
				if((!this.ruleForm.youxiang) && `zuozhe` == this.tableName){
					this.$utils.msg(`邮箱不能为空`);
					return
				}
				if(`zuozhe` == this.tableName && this.ruleForm.youxiang&&(!this.$validate.isEmail(this.ruleForm.youxiang))){
					this.$utils.msg(`邮箱应输入邮件格式`);
					return
				}
				if((!this.ruleForm.shouji) && `zuozhe` == this.tableName){
					this.$utils.msg(`手机不能为空`);
					return
				}
				if(`zuozhe` == this.tableName && this.ruleForm.shouji&&(!this.$validate.isMobile(this.ruleForm.shouji))){
					this.$utils.msg(`手机应输入手机格式`);
					return
				}
				if(`zuozhe` == this.tableName && this.ruleForm.status&&(!this.$validate.isIntNumer(this.ruleForm.status))){
					this.$utils.msg(`状态应输入整数`);
					return
				}
				await this.$api.register(`${this.tableName}`, this.ruleForm);
				this.$utils.msgBack('注册成功');;
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
