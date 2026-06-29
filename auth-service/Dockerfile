FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY . .

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package \
    -pl auth-service \
    -am \
    -DskipTests

FROM eclipse-temurin:21-jre

RUN apt-get update && apt-get install -y --no-install-recommends curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=build /workspace/auth-service/target/quarkus-app/lib/ ./lib/
COPY --from=build /workspace/auth-service/target/quarkus-app/app/ ./app/
COPY --from=build /workspace/auth-service/target/quarkus-app/quarkus/ ./quarkus/
COPY --from=build /workspace/auth-service/target/quarkus-app/*.jar ./

EXPOSE 8082

ENTRYPOINT ["java","-jar","quarkus-run.jar"]
