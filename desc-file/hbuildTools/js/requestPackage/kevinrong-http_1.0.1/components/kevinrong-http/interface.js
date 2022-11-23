import {baseApi} from 'baseApi.js'

export default {
    config: {
        //请求的url地址前缀
        baseUrl: baseApi,
        header: {
            'Content-Type': 'application/json;charset=UTF-8'
            //'Content-Type': 'application/x-www-form-urlencoded'
        },
        data: {},
        method: "GET",
        dataType: "json",  /* 如设为json，会对返回的数据做一次 JSON.parse */
        responseType: "text",
        success() {
        },
        fail() {
        },
        complete() {
        }
    },
    interceptor: {
        request: null,
        response: null
    },
    request(options) {
        if (!options) {
            options = {}
        }
        options.baseUrl = options.baseUrl || this.config.baseUrl
        options.dataType = options.dataType || this.config.dataType
        options.url = options.baseUrl + options.url
        options.data = options.data || {}
        options.method = options.method || this.config.method
        options.timeout = options.timeout || 100000

        return new Promise((resolve, reject) => {
            let requestConfig = null

            options.complete = (response) => {
                let statusCode = response.statusCode
                response.config = requestConfig
                if (process.env.NODE_ENV === 'development') {
                    if (statusCode === 200) {
                        ////console.log("【" + _config.requestId + "】 结果：" + JSON.stringify(response.data))
                    }
                }
                if (this.interceptor.response) {

                    let newResponse = this.interceptor.response(response)
                    if (newResponse) {
                        response = newResponse
                    }
                }
                // 统一的响应日志记录
                resLog(response)
                if (statusCode === 200) { //成功
                    resolve(response);
                } else {
                    reject(response)
                }
            }
            //方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象。它将返回目标对象。
            requestConfig = Object.assign({}, this.config, options)
            requestConfig.requestId = new Date().getTime()

            if (this.interceptor.request) {
                this.interceptor.request(requestConfig)
            }

            // 统一的请求日志记录
            //reqLog(_config)

            if (process.env.NODE_ENV === 'development') {
                //console.log("【" + _config.requestId + "】 地址：" + _config.url)
                if (requestConfig.data) {
                    //console.log("【" + _config.requestId + "】 参数：" + JSON.stringify(_config.data))
                }
            }
            //发送请求
            uni.request(requestConfig);
        });
    }
}


/**
 * 请求接口日志记录
 */
function reqLog(req) {
    if (process.env.NODE_ENV === 'development') {
        //console.log("【" + req.requestId + "】 地址：" + req.url)
        if (req.data) {
            //console.log("【" + req.requestId + "】 请求参数：" + JSON.stringify(req.data))
        }
    }
    //TODO 调接口异步写入日志数据库
}

/**
 * 响应接口日志记录
 */
function resLog(res) {
    let statusCode = res.statusCode;
    if (process.env.NODE_ENV === 'development') {
        //console.log("【" + res.config.requestId + "】 地址：" + res.config.url)
        if (res.config.data) {
            //console.log("【" + res.config.requestId + "】 请求参数：" + JSON.stringify(res.config.data))
        }
        //console.log("【" + res.config.requestId + "】 响应结果：" + JSON.stringify(res))
    }
    //TODO 除了接口服务错误外，其他日志调接口异步写入日志数据库
    switch (statusCode) {
        case 200:
            break;
        case 401:
            break;
        case 404:
            break;
        default:
            break;
    }
}

