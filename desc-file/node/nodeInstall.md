# node 安装
>下载网址：https://nodejs.org/zh-cn/download/  
## msi安装版
![node](nodeDownload.png)
#### 1、安装后查看版本
```shell script
node -v #分别查看node
npm -v #npm的版本号
```
#### 将路径添加到path中********
> 将node的跟目录添加到path中 E:\software\node\node-v14.15.1-win-x64\
#### 2、更换安装全局模块node_global和缓存node_cache的路径
+ 第一种方法
```properties
npm config set prefix "E:\software\node\node-v14.15.1-win-x64\node_global"
npm config set cache "E:\software\node\node-v14.15.1-win-x64\node_cache"
npm config set registry https://registry.npm.taobao.org/
```
+ 第二种方法：或者使用以下方法C:Users\Administrator\.npmrc
```properties
prefix="E:\software\node\node-v14.15.1-win-x64\node_global"
cache="E:\software\node\node-v14.15.1-win-x64\node_cache"
registry="http://registry.npm.taobao.org/"
```
#### 安装依赖
```properties
#安装vue.js
npm install vue -g
#安装vue-route
npm install vue-router -g
#安装vue-cli脚手架
npm install vue-cli -g
#安装webpack
npm install webpack -g
#使用yarn替换npm下载
npm install -g yarn
#安装cnpm
npm install -g cnpm
```
## 具体的安装
![node安装](node-install.png)
## zip解压版使用
![node安装](node-unzip.png)