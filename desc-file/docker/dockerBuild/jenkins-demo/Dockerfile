FROM java:8
MAINTAINER xwb
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/locatime
#暴露的端口
EXPOSE 8881
#用来指定当前工作目录（或者称为当前目录） 当使用相对目录的情况下，采用上一个WORKDIR指定的目录作为基准
#相当与cd 命令，但不同的是指定了WORKDIR后，容器启动时执行的命令会在该目录下执行
WORKDIR /usr/local/software/dockerImg/jenkins-demo
#RUN ls -a
# 将targer目录下的jar包复制到docker容器/目录下面
ADD jenkins-demo-1.1.0.jar /jenkins-demo.jar

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jenkins-demo.jar","&"]
