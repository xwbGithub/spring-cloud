import urls from '@/api/urls.js'
//APP更新
const appMethods={
	appUpdate(){
		console.log('================================appUpdate')
		uni.request({
			method:'GET',
			url: urls.appUpdate, //检查更新的服务器地址
			data: {
				//Runtime模块管理运行环境，可用于获取当前运行环境信息、与其它程序进行通讯等。通过plus.runtime可获取运行环境管理对象。
				//当前应用的APPID
				appid: plus.runtime.appid,
				//客户端的版本名称
				version: plus.runtime.version,
				//设备的国际移动设备身份码
				imei: plus.device.imei
			},
			success: (res) => {
				console.log("=== "+JSON.stringify(res));
				plus.runtime.getProperty(plus.runtime.appid, function(wgtinfo) {
						uni.downloadFile({
							url: urls.updateAppUrl,
							success: (downloadResult) => {
								console.log(downloadResult.tempFilePath)
								if (downloadResult.statusCode === 200) {
									plus.nativeUI.toast(`正在热更新!${res.data.versionCode}`);
									plus.runtime.install(downloadResult.tempFilePath, {
										force: false
									}, function() {
										plus.nativeUI.toast("热更新成功");
										plus.runtime.restart();
									}, function(e) {
										console.log(e)
										plus.nativeUI.toast(`热更新失败:${e.message}`);
									});
								}
							}
						});
					/* } */

				});
			}
		})
	}
};

export default appMethods;
