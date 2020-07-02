FROM openjdk:11.0.7-jre-slim
MAINTAINER Malcolm Spiteri <malcolm.spiteri@gov.mt>

# Install ws
RUN useradd ws

WORKDIR /home/ws/

# Copy configs
COPY ./ws /home/ws

# Create skeleton
RUN mkdir -p /home/ws/bin && \
    mkdir -p /home/ws/archive && \
    mkdir -p /home/ws/log && \
    mkdir -p /home/ws/tmp

# Copy binary
ADD ./ws/bin/dpppt-config-backend.jar /home/ws/bin/dpppt-config-backend.jar

RUN chown -R ws:ws /home/ws

# Access to webinterface
EXPOSE 8080

CMD java -jar $JAVA_OPTS -Dlogging.config=/home/ws/conf/dpppt-config-backend-ws-logback.xml -Dspring.config.location=/home/ws/conf/dpppt-config-backend-ws.properties /home/ws/bin/dpppt-config-backend.jar
