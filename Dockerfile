ARG GRADLE_VERSION="7-jdk11"
ARG TOMCAT_VERSION="10-jdk11-openjdk-slim-bullseye"

FROM gradle:${GRADLE_VERSION} as cached_gradle

RUN mkdir -p /home/gradle/cached

# Specifies the gradle user home where dependencies and plugins are downloaded
ENV GRADLE_USER_HOME /home/gradle/cached

COPY build.gradle.kts /home/gradle
WORKDIR /home/gradle

RUN gradle clean build -i --stacktrace -x bootWar


FROM gradle:${GRADLE_VERSION} as builder

COPY --from=cached_gradle --chown=builder:builder /home/gradle/cached/ /home/builder/.gradle/
COPY --chown=builder:builder . /home/builder
WORKDIR /home/builder
RUN gradle bootWar -x test -i --stacktrace


FROM tomcat:${TOMCAT_VERSION}

WORKDIR /app
COPY --from=builder /home/builder/build/libs/kotlin-spring-boilerplate-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]