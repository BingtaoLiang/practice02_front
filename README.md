"# practice02_front" 
## 快速运行
1. 安装必备工具  
- Git 
- JDK
- Maven
- MySQL
- 如果在服务器上安装mysql数据库的话，建议安装宝塔面板进行操作。
##步骤
- yum update
- yum install git 
- mkdir App
- cd App
- git clone 项目地址
- yum install maven
- mvn -v
- mvn compile package
- more src/main/resources/application.properties
- cp src/main/resources/application.properties src/main/resources/application-production.properties
- vim src/main/resources/application-production.properties
- mvn package 
- java -jar -Dspring.profiles.active=production target/打的jar包
- ps -axu | grep java
- git pull
- mvn package
- java -jar -Dspring.profiles.active=production target/打的jar包
- nohup java -jar -Dspring.profiles.active=production target/打的jar包  >/dev/null 2>&1 &   //不挂断执行命令
