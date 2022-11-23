# 全局引入
```vue
import Vue from 'vue'
import App from './App'
import http from './request.js'


Vue.config.productionTip = false

Vue.prototype.$urls = ""; //线上url接口
Vue.prototype.$http = http;

App.mpType = 'app'

const app = new Vue({
	...App
})
app.$mount()
```

# 方法的使用
```vue
//url-后台接口
//data-参数，传递给后台的参数
this.$http.sendRequest(url, 'GET', {
				data:data
}).then(res => {
	//成功回调
}).catch(err => {
    //请求失败
    console.log(err)
})
//url-后台接口
//data-参数，传递给后台的参数
this.$http.sendRequest(url,'POST',{
    data：data
}).then(res => {
    //成功回调
}).catch(err => {
    //请求失败
})
```
