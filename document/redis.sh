#!/bin/sh
#redis启动和关闭
redis_maater="/Users/vobile_lzl/redis_master_s"
redis_slaver_1="/Users/vobile_lzl/redis_slaver1_s"
reids_slaver_2="/Users/vobile_lzl/redis_slaver2_s"
start="start"
stop="stop"
error="命令有误！请输入start or stop"

# 开启redis
function start()
{
	echo "start redis "
	cd ~
	cd redis_master_s/
	toStart
	echo "start 6379 and sentinel end "
	cd ~
	cd redis_slaver2_s/
	toStart
	echo "start 6377 and sentinel end "
	cd ~
	cd redis_slaver1_s
	toStart
	echo "start 6377 and sentinel end "
}

function toStart()
{
	./redis-server redis.conf
	./redis-server sentinel.conf --sentinel
}
function toStop()
{
	./redis-cli shutdown
}
# 关闭redis ./redis-cli shutdown
function stop()
{
	echo "stop redis begin"
	cd ~
	cd redis_master_s/
	toStop
	echo "master stop"
	cd ~
	cd redis_slaver2_s/
	toStop
	echo "slaver2 stop"
	cd ~
	cd redis_slaver1_s
	toStop
	echo "slaver1 stop"
	echo "stop end "
}
# 判断输入是否为空，为空输出
if [ -z $1 ]
then
	echo $1 + $error
	exit
fi

if [ $1 = $start ] 
then
	echo $start
	start
elif [ $1 = $stop ]
then
	echo $stop
	stop
else
	echo $1 + $error
fi
exit