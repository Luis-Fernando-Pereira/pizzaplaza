FROM maven:3.9.16-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY . .

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package \
    -pl api-gateway \
    -am \
    -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/api-gateway/target/quarkus-app/lib/ ./lib/
COPY --from=build /workspace/api-gateway/target/quarkus-app/app/ ./app/
COPY --from=build /workspace/api-gateway/target/quarkus-app/quarkus/ ./quarkus/
COPY --from=build /workspace/api-gateway/target/quarkus-app/*.jar ./

EXPOSE 8080

ENTRYPOINT ["java","-jar","quarkus-run.jar"]
