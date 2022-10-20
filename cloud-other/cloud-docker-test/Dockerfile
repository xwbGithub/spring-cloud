
FROM java:8

MAINTAINER xwb
# 指定零时文件路径，在主机下的路径
VOLUME /usr/local/docker/tmp

ADD cloud-docker-test-1.0.0.jar docker-test.jar

# 运行jar
RUN bash -c 'touch /docker-test.jar' ENTRYPOINT ["java","-jar","/docker-test.jar"]

EXPOSE 8881
