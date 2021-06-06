FROM gradle:7.0.2-jdk11 as builder
COPY . .
RUN gradle --no-daemon build

FROM gcr.io/distroless/java:11
ENV JAVA_TOOL_OPTIONS -XX:+ExitOnOutOfMemoryError
#COPY --from=builder /home/gradle/build/deps/external/*.jar /data/
#COPY --from=builder /home/gradle/build/deps/fint/*.jar /data/
COPY --from=builder /home/gradle/build/libs/fint-ldap-janitor-*.jar /data/fint-ldap-janitor.jar
CMD ["/data/fint-ldap-janitor.jar"]