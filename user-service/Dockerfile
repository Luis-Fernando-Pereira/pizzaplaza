FROM maven:3.9.16-eclipse-temurin-25 AS build

WORKDIR /workspace

COPY . .

RUN mvn clean package \
    -pl user-service \
    -am \
    -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /workspace/user-service/target/quarkus-app/lib/ ./lib/
COPY --from=build /workspace/user-service/target/quarkus-app/app/ ./app/
COPY --from=build /workspace/user-service/target/quarkus-app/quarkus/ ./quarkus/
COPY --from=build /workspace/user-service/target/quarkus-app/*.jar ./

EXPOSE 8081

ENTRYPOINT ["java","-jar","quarkus-run.jar"]
