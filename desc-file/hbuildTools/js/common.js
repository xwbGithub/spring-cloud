import urls from '@/api/urls.js'
import { ACCESS_TOKEN,USER_NAME,USER_INFO} from "@/common/util/constants"
const methods = {
	
	//获取当前时间对应的月
	 getEnglishMonth(currDate){
		let monthEnglish=["January","February","March","April","May","June","July","August","September","October","November","December "];
		let englishMonth = monthEnglish[new Date(new Date(currDate)).getMonth()];
		return englishMonth;
	 },
	 getYearDay(currDate){
		 //let year=currDate.getYear(); //获取当前年份(2位)
		 let fullYear=currDate.getFullYear(); //获取完整的年份(4位)
		 //let month=currDate.getMonth(); //获取当前月份(0-11,0代表1月)
		 let currentDate=currDate.getDate(); //获取当前日(1-31)
		 //let day=currDate.getDay(); //获取当前星期X(0-6,0代表星期天)
		 //let time=currDate.getTime(); //获取当前时间(从1970.1.1开始的毫秒数)
		 //let hours=currDate.getHours(); //获取当前小时数(0-23)
		 //let minutes=currDate.getMinutes(); //获取当前分钟数(0-59)
		 //let seconds=currDate.getSeconds(); //获取当前秒数(0-59)
		 //let milliseconds=currDate.getMilliseconds(); //获取当前毫秒数(0-999)
		 //let localeDate=currDate.toLocaleDateString(); //获取当前日期
		 //let mytime=currDate.toLocaleTimeString(); //获取当前时间
		 let arr=[fullYear,currentDate];
		 return arr;
	 },
	 getYear(currDate){
		 let date=new Date(currDate);
		 return date.getFullYear();
	 },
	 getMonth(currDate){
		 let date=new Date(currDate);
		 return date.getMonth();
	 },
	 getDay(currDate){
		 let date=new Date(currDate);
		 return date.getDate();
	 },
	 showToast(title,icon){
		 uni.showToast({
		  	   title: title,
		  	   icon: null!=icon?icon:'none',
			   duration:1500
		 })
	 },
	 // 验证用户登录id
	 validateUserId(userid){
		 if(null===userid||""===userid||undefined===userid||"underfined"===userid){
			return false;
		 }else{
			 return true;
		 }
	 },
	 /** 判断是否为空， 为空返回true，不为空返回false
	  * @param {Object} str 判断的字符串
	  */
	 isNull(str){
		 if(null===str||""===str||undefined===str||"undefined"===str){
			 return true;
		 }else{
			 return false;
		 }
	 },
	 /**跳转到登录页面
	  * @param {Object} title 标题
	  * @param {Object} content 提示内容
	  * @param {Object} confirm 确认文字
	  * @param {Object} cancel 取消文字
	  */
	 jumpLogin(title,content,confirm,cancel){
		 uni.showModal({
		 	title: this.isNull(title)?'提示':title,
		 	content:  this.isNull(content)?'是否登录!':content,
		 	confirmText:this.isNull(content)?'登录':confirm,
		 	cancelText:this.isNull(content)?'暂不登录':cancel,
		 	success: function(res) {
		 		if (res.confirm) {
					uni.navigateTo({
						url:'/pages/app/user/loginAndRegister'
					})
		 		} else if (res.cancel) {
					console.log("用户取消登录");
		 		}
		 	}
		 });
	 },
	 logOut(that){
		 uni.showModal({
		 	title: '提示',
		 	content:  '是否退出!',
		 	confirmText:'退出',
		 	cancelText:'暂不退出',
		 	success: function(res) {
		 		if (res.confirm) {
					uni.clearStorageSync();
					that.$store.commit("SET_USERID",'');
		 			uni.navigateTo({
		 				url:'/pages/app/index/welcome'
		 			})
		 		} else if (res.cancel) {
		 			console.log("用户取消退出");
		 		}
		 	}
		 });
		  
	 },
	 /** 保存或者清除搜索的缓存记录
	  * @param {Object} status 0、set 1、get
	  * @param {Object} key 对应存储到key
	  * @param {Object} data 存储的数据
	  */
	 searchHistory(status,key,data){
		 if(0===status){
			 uni.setStorageSync(key,data)
		 }else{
			let oldData=uni.getStorageSync(key);
			return [...oldData];//字符串装数组
		 }
	 },
	 //复制剪切板
	 copyTranContent(tranToText){
	 	uni.setClipboardData({
	 	    data: tranToText,
	 	    success: function () {
	 			methods.showToast('复制成功','success');
	 	    },
	 		fail: function(error) {
	 			methods.showToast(error,'error');
	 		}
	 	});
	 },
	 /**密码中必须包含字母（不区分大小写）、数字，至少8个字符，最多30个字符；
	  * @param {Object} password 
	  */
	 validatePassword(password){
		//密码中必须包含大小写 字母、数字、特称字符，至少8个字符，最多30个字符；
		//var reg = new RegExp('(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[^a-zA-Z0-9]).{8,30}');
		
		// 密码中必须包含字母（不区分大小写）、数字、特称字符，至少8个字符，最多30个字符；
		// var reg = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
		
		// 密码中必须包含字母（不区分大小写）、数字，至少8个字符，最多30个字符；
		var reg = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}');
		if(!reg.test(password)){
			return false;
		}else{
			return true;
		}
		
	 },
	 /**微信分享
	  * @param {Object} url
	  * @param {Object} title
	  * @param {Object} miaoshu
	  * @param {Object} imgurl
	  */
	 shareWx(url,title,miaoshu,imgurl){
		 //分享文字
		 uni.share({
		     provider: "weixin",
		     scene: "WXSceneSession",
		     type: 1,
		     summary: "我正在使用HBuilderX开发uni-app，赶紧跟我一起来体验！",
		     success: function (res) {
		         console.log("success:" + JSON.stringify(res));
		     },
		     fail: function (err) {
		         console.log("fail:" + JSON.stringify(err));
		     }
		 });
		 // 分享图片
		 //https://uniapp.dcloud.io/api/plugins/share?id=分享到微信聊天界面示例代码
		 /* uni.share({
			 provider: "weixin",
			 scene: "WXSceneSession",
			 type: 0,
			 href: url,
			 title: title,
			 summary: miaoshu,
			 imageUrl: imgurl,
			 success: function (res) {
				 console.log(JSON.stringify(res));
				 uni.showToast({
					 title: '已分享',
					 duration: 2000
				 });
			 },
			 fail: function (err) {
				 var errrr = JSON.stringify(err);
				 if(errrr){
					 uni.showModal({
					 title: '表单不能留空',
					 content: '请完善所有信息再发起分享',
					 success: function (res) {
						 if (res.confirm) {
								 console.log('用户点击确定');
							 } else if (res.cancel) {
								 console.log('用户点击取消');
							 }
						 }
					 });
				 }
			 }
		 }); */
		}
	};

export default methods;