FROM alpine

MAINTAINER divyajk33@gmail.com

RUN mkdir /opt/tomcat/

WORKDIR /opt/tomcat
RUN CURL -O https://downloads.apache.org/tomcat/tomcat-8/v8.5.61/bin/apache-tomcat-8.5.61.tar.gz
RUN tar xvfz apache*.tar.gz
RUN mv apache-tomcat-8.5.61/* /opt/tomcat/.

RUN java -version

WORKDIR /opt/tomcat/webapps
RUN CURL -O -L https://github.com/divyajk33/SampleApp/blob/master/SampleApp/SampleApp-0.0.1-SNAPSHOT.war

EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]
