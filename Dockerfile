FROM relateiq/oracle-java8
MAINTAINER xiangyu0xf@gmail.com
ADD target/user-account-1.0-SNAPSHOT.jar /app/
WORKDIR /app