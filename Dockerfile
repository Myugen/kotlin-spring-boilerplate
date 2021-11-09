ARG GRADLE_VERSION="7-jdk11"
ARG JAVA_VERSION="11-alpine"

FROM gradle:${GRADLE_VERSION} as cached_gradle

RUN mkdir -p /home/gradle/cached

# Specifies the gradle user home where dependencies and plugins are downloaded
ENV GRADLE_USER_HOME /home/gradle/cached

COPY build.gradle.kts /home/gradle
WORKDIR /home/gradle

RUN gradle clean build -i --stacktrace -x bootWar


FROM gradle:${GRADLE_VERSION} as builder

# Specifies the gradle user home where dependencies and plugins are downloaded
ENV GRADLE_USER_HOME /home/builder

COPY --from=cached_gradle --chown=builder:builder /home/gradle/cached/ /home/builder/.gradle/
COPY --chown=builder:builder . /home/builder
WORKDIR /home/builder
RUN gradle bootJar -x test -i --stacktrace


FROM amazoncorretto:${JAVA_VERSION} as app

WORKDIR /app
COPY --from=builder /home/builder/build/libs/kotlin-spring-boilerplate-0.0.1-SNAPSHOT.jar ./kotlin-spring-boilerplate.jar

EXPOSE 8080

CMD ["java","-jar","kotlin-spring-boilerplate.jar"]