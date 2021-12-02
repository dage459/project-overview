FROM adoptopenjdk/openjdk11:alpine-slim

LABEL org.opencontainers.image.authors=@dage459
LABEL org.opencontainers.image.created=02.12.2021
LABEL org.opencontainers.image.revision=02.12.2021
LABEL org.opencontainers.image.title=project-overview
LABEL org.opencontainers.image.url=https://hub.docker.com/r/dage459/project-overview
LABEL org.opencontainers.image.licenses=Apache-2.0

RUN apk --no-cache add tini \
 && apk --no-cache add curl

WORKDIR /opt/service

COPY ./build/libs .

EXPOSE 8080

ENTRYPOINT ["/sbin/tini", "--"]

CMD ["java","-jar","project-overview-0.1.0-SNAPSHOT.jar"]