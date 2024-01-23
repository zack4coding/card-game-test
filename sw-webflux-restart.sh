kill -9 $(jps |grep sw-ia-webflux |awk '{print $1}')
nohup java -jar sw-ia-webflux/target/*.jar >/var/log/sw.log 2>&1 &
