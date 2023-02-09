# 一、基本命令

https://www.cnblogs.com/mmworld/p/16012625.html

| 命令                           | 含义                                                                                                                             | 说明                                                                                                                                |
| :----------------------------------- |:-------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------|
| <font color='red'>FROM</font>        | 以哪个镜像为基础开始构建                                                                                                                   | 基础镜像不存在会在Docker Hub上拉去(例如部署的springboot依赖于java8)<br><font color='blue'>eg:FROM java:8</font>                                       |
| <font color='red'>MAINTANIER</font>  | 制作者信息                                                                                                                          | 提供dockerfile制作者<br><font color='blue'>eg:MAINTANIER 12345@qq.com</font>                                                           |
| <font color='red'>COPY</font>        | 从当前目录复制文件到容器. 只是单纯地复制文件                                                                                                        | src 原文件 --支持通配符--通常相对路径dest 目标路径--通常绝对路径<br><font color='blue'>eg:COPY /target/* /data/</font>                                    |
| <font color='red'>ADD</font>         | 类似COPY命令,从当前目录复制文件到容器. 会自动处理目录, 压缩包等情况                                                                                         | 支持URL路径----如果可以访问网络的话，会访问网络下载到本地然后打包进镜像<br><font color='blue'>eg:ADD ./target/ruoyi-admin.jar /usr/local</font>                   |
| <font color='red'>WORKDIR</font>     | 指定工作目录，类似于cd命令,RUN, CMD, ENTRYPOINT 这些命令执行时的当前目录                                                                               | 每次只会影响这个指令后续的指令<br><font color='blue'>eg:WORKDIR /user/local/data/</font>                                                         |
| <font color='red'>VOLUME</font>      | 声明一个数据卷, 可用于挂载                                                                                                                 | 只能定义docker管理的卷：VOLUME /data/mysql  运行的时候会随机在宿主机的目录下生成一个卷目录<br><font color='blue'>eg:VOLUME /usr/local/tmp</font>                  |
| <font color='red'>EXPOSE</font>      | 为容器打开指定要监听的端口以实现与外部通信                                                                                                          | 使用格式：EXPOSE 80/tcp 23/udp不加协议默认为tcp使用-P选项可以暴露这里指定的端口！但是宿主的关联至这个端口的端口是随机的！<br><font color='blue'>eg:EXPOSE 8881</font>             |
| <font color='red'>ENV</font>         | 设置环境变量，并可被Dockerfile文件中位于其后的其它命令所调用                                                                                            | 调用格式：$A 或 ${A}<br><font color='blue'>eg:ENV LANG en_US.UTF-8</font>                                                               |
| <font color='red'>RUN</font>         | 运行一条命令                                                                                                                         | <br><font color='blue'>eg:RUN mkdir -vp /vueProject/demo</font><br><font color='blue'>eg:RUN yum install wget</font>              |
| <font color='red'>CMD</font>         | 可用于执行脚本                                                                                                                        | <br><font color='blue'>eg:CMD echo "This is a test."</font><br><font color='blue'>eg:CMD java -jar  /app/gateway.jar </font>      |
| <font color='red'>ENTRYPOINT</font>  | 类似CMD指令的功能，用于为容器指定默认运行程序，从而使得容器像是一个单独的可执行程序,与CND不同的是，<br>由这个指令启动的程序不会被docker run 命令行指定的参数所覆盖而且，这些命令行参数会被当做参数传递给ENTRYPOINT指定的程序 | <br><font color='blue'>eg:["java", "-Djava.security.egd=file:/test/./urandom","-jar", "app.jar","-Duser.timezone=GMT+08"] </font> |
| <font color='red'>USER</font>        | RUN 命令执行时的用户<br>(当容器中运行的服务不需要管理员权限时，可以先建立一个特定的用户和用户组，为它分配必要的权限，使用 USER 切换到这个用户)                                                                                                             | <br><font color='blue'>eg:USER mysql</font>                                                                                       |
| <font color='red'>ONBUILD</font>     | 配置当前所创建的镜像作为其它新创建镜像的基础镜像时，所执行的操作指令。<br>意思就是，这个镜像创建后，如果其它镜像以这个镜像为基础，会先执行这个镜像的ONBUILD命令                                          | 指令：ONBUILD 要执行的Dockerfile指令<br><font color='blue'>ONBUILD RUN echo "---------- parent image's ONBUILD ----------"</font>          |
| <font color='red'>HEALTHCHECK</font> |                                                                                                                                |                                                                                                                                   |
| <font color='red'>SHELL</font>       |                                                                                                                                |                                                                                                                                   |
| <font color='red'>STOPSIGNAL</font>  |                                                                                                                                |                                                                                                                                   |
| <font color='red'>ARG</font>         | 用于docker build 的过程中使用                                                                                                          |                                                                                                                                   |


# 二、关联测试项目 
+ <font color='red'>cloud-other/cloud-docker-test</font>
> https://blog.csdn.net/qq_45297578/article/details/122377791


## cloud-docker-test
```shell

FROM java:8

MAINTAINER 12345@qq.com
# 指定零时文件路径，在主机下的路径
VOLUME /usr/local/docker/tmp

ADD cloud-docker-test-1.0.0.jar docker-test.jar

# 运行jar
RUN bash -c 'touch /docker-test.jar' ENTRYPOINT ["java","-jar","/docker-test.jar"]

EXPOSE 8881
```

# 三、docker-maven-plugins的使用
### 1、让docker可以远程访问、开放2375端口
#### 修改docker.service文件
```shell
vim /lib/systemd/system/docker.service
```
#### 修改前
>ExecStart=/usr/bin/docker -H fd:// --containerd=/run/containerd/containerd.sock
#### 修改后
>ExecStart=/usr/bin/docker -H fd:// --containerd=/run/containerd/containerd.sock ‐H tcp://0.0.0.0:2375 ‐H unix:///var/run/docker.sock
+ 开放端口
```shell
firewall-cmd --zone=public --add-port=2375/tcp --permanent
firewall-cmd --reload
```
### 2、刷新配置，重启服务
```shell
systemctl daemon-reload     //加载docker守护线程
systemctl restart docker    //重启docker
```
### 3、配置防火墙
```shell
systemctl stop firewalld (关闭防火墙)
systemctl disable firewalld (禁用防火墙，开机不会启动)
```
### 4、关闭防火墙
```shell
#编辑selinux/config文件
vi /etc/selinux/config
#SELINUX=enforcing改为disabled
SELINUX=disabled
#重启防火墙
systemctl restart firewalld # 重启防火墙
systemctl status firewalld #查看防火墙状态
```
### 5、项目中的使用
```xml
<!--docker的maven插件-->
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>1.0.0</version>
    <configuration>
        <dockerHost>http://192.168.11.131:2375</dockerHost>
        <imageName>ruoyi-admin.jar</imageName>
        <baseImage>java</baseImage>
        <maintainer>1056481167@qq.com</maintainer>
        <workdir>/ROOT</workdir>
        <cmd>["java", "-version"]</cmd>
        <entryPoint>["java", "-jar", "${project.build.finalName}.jar"]        </entryPoint>
        <!-- 这里是复制 jar 包到 docker 容器指定目录配置 -->
        <resources>
            <resource>
                <targetPath>/ROOT</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>
```
