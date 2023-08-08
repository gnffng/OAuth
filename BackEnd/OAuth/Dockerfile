FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /oauth

COPY . .

RUN mvn package -DskipTests

CMD ["java", "-jar", "/oauth/target/ROOT.jar"]