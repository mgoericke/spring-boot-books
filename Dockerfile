FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
# set /tmp folder as working directory
WORKDIR /tmp/

# SECOND STAGE
RUN mvn package

FROM openjdk:8u212-jdk-alpine
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/*jar app.jar
HEALTHCHECK --interval=1m --timeout=5s --start-period=1m CMD curl -f http://localhost:8080/api/books || exit 1

CMD ["java", "-jar", "app.jar"]