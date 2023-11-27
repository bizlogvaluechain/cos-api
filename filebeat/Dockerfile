FROM docker.elastic.co/beats/filebeat:7.15.2

COPY filebeat.yml /usr/share/filebeat/filebeat.yml

USER root
RUN mkdir /usr/share/filebeat/dockerlogs && \
    chown -R root /usr/share/filebeat/ && \
    chmod -R go-w /usr/share/filebeat/