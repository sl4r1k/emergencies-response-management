FROM maven:3-openjdk-11-slim as build
RUN curl -sL https://deb.nodesource.com/setup_14.x | bash -
RUN apt-get update -qq && apt-get install -qq --no-install-recommends nodejs

RUN useradd -m myuser
WORKDIR /usr/src/app/
RUN chown myuser:myuser /usr/src/app/
USER myuser

COPY --chown=myuser pom.xml ./
RUN mvn dependency:go-offline -Pproduction

COPY --chown=myuser:myuser src src
COPY --chown=myuser:myuser frontend frontend
COPY --chown=myuser:myuser package.json ./

COPY --chown=myuser:myuser package-lock.json* pnpm-lock.yaml* webpack.config.js* ./


RUN mvn clean package -DskipTests -Pproduction

FROM openjdk:11-jdk-slim
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 8080
CMD java -jar /usr/app/app.jar
