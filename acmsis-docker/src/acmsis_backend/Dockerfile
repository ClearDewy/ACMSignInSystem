#docker build -t acmsis_backend .
#docker build -t registry.cn-hangzhou.aliyuncs.com/cleardewy/acmsis:acmsis_backend .
#docker push registry.cn-hangzhou.aliyuncs.com/cleardewy/acmsis:acmsis_backend
FROM openjdk:8

#定义工作目录
ENV WORK_PATH /home

ENV TZ=Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ../../../../*.jar $WORK_PATH/app.jar
COPY ./start.sh $WORK_PATH/
RUN chmod a+x $WORK_PATH/start.sh

# CMD ["java","-jar","/home/app.jar"]
ENTRYPOINT ["/home/start.sh"]

EXPOSE 8080