```shell
echo "=========进入远程服务器脚本==========="
source ~/.bash_profile
source /etc/profile

PROJECT_NAME=ruoyi-ui
echo "=========="
# cd 
cd /root/.jenkins/workspace/ruoyi-ui/cloud-ruoyi/ruoyi-ui
mkdir -vp /usr/local/software/vueProject/$PROJECT_NAME/
\cp -R ../$PROJECT_NAME/* /usr/local/software/vueProject/$PROJECT_NAME/
cd /usr/local/software/vueProject/$PROJECT_NAME/
npm install  audit fix
pIDa=`netstat -tunlp | grep 9800|grep -v "PID" | awk '{print $2}'`  
echo 查询的端口是$pIDa  
if [ "$pIDa" != "" ];  
then  
   echo "=====1"  
else  
   echo "=======0"  
fi

echo "==========启动命令================"
BUILD_ID=vueProject
nohup npm run dev>nohup.out 2>&1 &
```
## 定时构建
### Jenkins cron表达式
+ 标准cron
```shell
https://crontab.guru
```
Jenkins cron不是标准的cron表达式

```
第一个 * 表示每个小时的第几分钟，取值0~59

H * * * *
H：每小时执行一次

第二颗 * 表示小时，取值0~23

* 15 * * * 表示每天下午3点
* 1 * * *  表示每天凌晨1点

第三颗 * 表示一个月的第几天，取值1~31
* 1 5 * *  表示每月5日凌晨1点

第四颗 * 表示第几月，取值1~12
* 15 5 1 *  表示每年几月执行

第五颗 * 表示一周中的第几天，取值0~7，其中0和7代表的都是周日

```

**“/”**

表示每隔多长时间，比如 */10 * * * * 表示 每隔10分钟

**“H”**

hash散列值，以job名取值，获取到以job名为入参的唯一值，相同名称值也相同，这个偏移量会和实际时间相加，获得一个真实的运行时间

意义在于：不同的项目在不同的时间运行，即使配置的值是一样的，比如 都是`15 * * * * ` ，表示每个小时的第15分钟开始执行任务，那么会造成同一时间内在Jenkins中启动很多job，换成`H/15 * * * *`,那么在首次启动任务时，会有随机值参与进来，有的会在17分钟启动 有的会在19分钟启动，随后的启动时间也是这个值。这样就能错开相同cron值的任务执行了。

H的值也可以设置范围



`H * * * *`表示一小时内的任意时间

`*/10 * * * *`每10分钟

`H/10 * * * *`每10分钟,可能是7,17,27，起始时间hash，步长不变

`45 3 * * 1-6 ` 每个周一至周六，凌晨3点45 执行1次

`45 3-5 * * 1-6 ` 每个周一至周六，凌晨3点45 ，凌晨4点45，凌晨5点45 各执行1次

`H(40-48) 3-5 * * 1-6 ` 在40~48之间取值 其他同上

`45 3-5/2 * * 1-6 ` 每个周一至周六，凌晨3点45 ，凌晨5点45 各执行1次

` 45 0-6/2 * * 1-6 * * 1-6 ` 0点开始，每间隔2小时执行一次 0:45、2:45、4:45
