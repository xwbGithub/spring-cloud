# 一、docker安装nginx

## 1、拉取镜像

```shell
docker pull nginx
```

## 2、创建挂载目录

```shell
mkdir -vp /usr/local/docker/nginx
cd /usr/local/docker/nginx
#创建用户挂在的目录
mkdir -vp logs html conf
```

## 3、启动镜像

### 1、方式一(<font color='red'>推荐</font>)

#### 1、启动

```shell
docker run -d --name nginx -p 80:80 nginx
```

#### 2、拷贝容器里面的nginx配置文件

<font color='red'>**nginx:**</font>此名称是容器的唯一id(可以是名字,确保唯一即可,因为我上面启动适用的名称是nginx)

```shell
docker cp nginx:/etc/nginx/nginx.conf /usr/local/docker/nginx/
docker cp nginx:/etc/nginx/conf.d /usr/local/docker/nginx/conf/
docker cp nginx:/usr/share/nginx/html/ /usr/local/docker/nginx/html/
docker cp nginx:/var/log/nginx/ /usr/local/docker/nginx/logs/
```

#### 3、停止容器并删除

```shell
#1、停止之前的nginx
docker stop nginx
#2、删除启动的容器
docker rm -f nginx
```

#### 4、[启动容器](#startContainer)

### 2、方式二

#### 1、准备nginx.conf文件

> vim /usr/local/docker/nginx/nginx.conf

```shell
user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;
}
```

#### 2、准备default.conf文件

> cd  /usr/local/docker/nginx/conf
> vim default.conf

```shell
server {
    listen       80;
    listen  [::]:80;
    server_name  localhost;
    #access_log  /var/log/nginx/host.access.log  main;
        location / {
            root   /usr/share/nginx/html;
            index  index.html index.htm;
        }
        #error_page  404              /404.html;
        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   /usr/share/nginx/html;
        }
        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}
        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}
        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
}
```

#### 3、准备50x.html

> vim /usr/local/docker/nginx/html/50x.html

```html
<html>
	<head>
		<title>Error</title>
		<style>
			html { color-scheme: light dark; }
			body { width: 35em; margin: 0 auto;
			font-family: Tahoma, Verdana, Arial, sans-serif; }
		</style>
	</head>
	<body>
		<h1>An error occurred.</h1>
		<p>Sorry, the page you are looking for is currently unavailable.
			<br />
			Please try again later.</p>
		<p>If you are the system administrator of this resource then you should check
			the error log for details.</p>
		<p>
			<em>Faithfully yours, nginx.</em>
		</p>
	</body>
</html>
```

#### 4、准备index.html

> vim /usr/local/docker/nginx/html/index.html

```shell
<!DOCTYPE html>
<html>
   <head>
   	<title>Welcome to nginx!</title>
   	<style>
   		html { color-scheme: light dark; }
   		body { width: 35em; margin: 0 auto;
   		font-family: Tahoma, Verdana, Arial, sans-serif; }
   	</style>
   </head>
   <body>
   	<h1>Welcome to nginx!</h1>
   	<p>If you see this page, the nginx web server is successfully installed and
   		working. Further configuration is required.</p>
   	<p>For online documentation and support please refer to<a href="http://nginx.org/">nginx.org</a>.<br />
   		Commercial support is available at
   		<a href="http://nginx.com/">nginx.com</a>.</p>
   	<p>
   		<em>Thank you for using nginx.</em>
   	</p>
   </body>
</html> 
```

#### 5、[启动容器](#startContainer)

## 3、<a id="startContainer">重新启动nginx</font>

```shell
docker run  --name nginx -m 200m -p 80:80 \
-v /usr/local/docker/nginx/nginx.conf:/etc/nginx/nginx.conf \
-v /usr/local/docker/nginx/logs:/var/log/nginx \
-v /usr/local/docker/nginx/html:/usr/share/nginx/html \
-v /usr/local/docker/nginx/conf:/etc/nginx/conf.d \
-e TZ=Asia/Shanghai \
--privileged=true -d nginx
```

## 4、访问地址

+ 开启80端口

> firewall-cmd --zone=public --add-port=80/tcp --permanent && firewall-cmd --reload

> http://192.168.11.142:80
# 二、nginxGUI
## 官网
> https://github.com/onlyGuo/nginx-gui
## centos7的安装
### 1、下载
下载对应的版本
```shell
mkdir -vp /usr/local/software/nginx-gui
cd /usr/local/software/nginx-gui
wget https://github.com/onlyGuo/nginx-gui/releases/download/1.6/Nginx-GUI-For-Linux_X64_v1.6.zip
```
### 2、配置
### 1、解压缩
```shell
#解压
unzip  Nginx-GUI-For-Linux_X64_v1.6.zip
#修改文件名称
mv Nginx-GUI-For-Linux_X64_v1.6 nginx-gui
#进入工作目录
cd nginx-gui
#修改配置文件
vim conf/conf.properties
```
### 2、修改nginx的目录环境以及登录用户名密码
```shell
cd /usr/local/software/nginx-gui/nginx-gui/conf
vim conf.properties
#配置nginx的路径
nginx.path = /usr/local/docker/nginx/
nginx.config = /usr/local/docker/nginx/conf/nginx.conf
#配置登录gui系统管理界面的用户名密码
account.admin = admin
#account.password= admin123456
```
### 3、配置端口
```shell
cd /usr/local/software/nginx-gui/nginx-gui/bin
vim application.yml
```
### 启动nginx-gui
```shell
chmod 777  /usr/local/software/nginx-gui/*
cd /usr/local/software/nginx-gui/nginx-gui
sh startup.sh
#开放端口
firewall-cmd --zone=public --add-port=8889/tcp --permanent && firewall-cmd --reload
```
# 二、nginxWebUI
## 1、拉取
```shell
#创建挂载的目录文件
mkdir -vp /usr/local/docker/nginxWebUI
docker pull cym1102/nginxwebui:latest
```
## 2、启动
```shell
docker run -itd \
  -v /usr/local/docker/nginxWebUI:/home/nginxWebUI \
  -e BOOT_OPTIONS="--server.port=8089" \
  --privileged=true \
  --net=host \
  --name=nginxWebUI  cym1102/nginxwebui:latest
```
## 3、访问信息

>http://192.168.11.142:8089
+ 第一次打开需要初始化密码:本人设置的admin/adminA12345
![1-setpassword](../nginx/nginxUI/1-setpassword.png)
![1-login](../nginx/nginxUI/1-login.png)
![1-index](../nginx/nginxUI/1-index.png)
