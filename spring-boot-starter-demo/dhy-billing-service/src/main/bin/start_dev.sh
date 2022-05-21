# 本地启动的参数(jar包名，调试端口)；拼写在命令行里
name="billing-service"
port=8000
jarfile=$(dirname $0)/../${name}.jar

java -Xmx512m -Xss512k \
  -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSFullGCsBeforeCompaction=5 -XX:+UseCMSCompactAtFullCollection -Xdebug -Xnoagent \
  -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=${port} \
  -jar "${jarfile}"
