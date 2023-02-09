# <font color='red'>docker volume数据卷</font>
## 一、数据卷的特点
>1、数据卷可以在容器之间共享和重用<br> 
2、<font color='red'>对数据卷的修改会立即影响到对应容器</font><br>
3、对`数据卷`的更改，不会影响镜像<br>
4、数据卷默认一直存在，即使容器被删除<br>
## 二、对数据卷的操作
### 1、自定义数据卷目录
```shell
docker run -d -p -v 绝对路径:容器内部路径
```
### 2、自动创建数据卷
```shell
docker run -d -p -v 卷名(随便起的自动创建):容器内部路径
```
## 三、docker操作数据卷的指令
### 1、查看数据卷
```shell
docker volume ls
```
### 2、数据卷的细节(<font color='red'>显示该卷存在的位置等详细信息</font>)
```shell
docker volume inspect 卷名
```
### 3、创建数据卷
```shell
docker volume create 卷名
```
### 4、删除数据卷
```shell
docker volume rm 卷名
```
### 5、删除没有使用的卷(<font color='red'>谨慎操作</font>)
```shell
docker volume prune
```

