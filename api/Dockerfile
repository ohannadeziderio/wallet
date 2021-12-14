FROM gradle:7.3.1-jdk11 AS build

ENV SPRING_PROFILES_ACTIVE=test

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle --no-daemon build

FROM openjdk:11 as runner

ENV POSTGRESQL_URL="jdbc:postgresql://postgresql-server/wallet" POSTGRESQL_USERNAME="wallet" POSTGRESQL_PASSWORD="wallet"

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/api.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]