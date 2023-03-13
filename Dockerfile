# Build stage
FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/sensor-service-*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]



#FROM openjdk:latest as builder
#ADD . /src
#WORKDIR /src
#RUN ./mvnw package -DskipTests
#
#FROM alpine:3.13 as packager
#RUN apk --no-cache add openjdk11-jdk openjdk11-jmods
#ENV JAVA_MINIMAL="/opt/java-minimal"
#RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
#    --verbose \
#    --add-modules \
#        java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
#    --compress 2 --strip-debug --no-header-files --no-man-pages \
#    --release-info="add:IMPLEMENTOR=radistao:IMPLEMENTOR_VERSION=radistao_JRE" \
#    --output "$JAVA_MINIMAL"
#
#FROM alpine:3.13
#LABEL maintainer="LABEL maintainer replace"
#ENV JAVA_HOME=/opt/java-minimal
#ENV PATH="$PATH:$JAVA_HOME/bin"
#COPY --from=packager "$JAVA_HOME" "$JAVA_HOME"
#COPY --from=builder /src/target/sensor-service-*.jar app.jar
#EXPOSE 8081
#ENTRYPOINT ["java","-jar","/app.jar"]