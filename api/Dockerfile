FROM gradle:7.3.1-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:11
ENV POSTGRESQL_URL="jdbc:postgresql://postgresql-server/wallet" POSTGRESQL_USERNAME="postgres" POSTGRESQL_PASSWORD="postgres" POSTGRES_DB=wallet
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]