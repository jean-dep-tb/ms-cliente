FROM openjdk:8
VOLUME /tmp
EXPOSE 8020
ADD ./target/spring-boot-webflu-ms-cliente-0.0.1-SNAPSHOT.jar ms.cliente.jar
ENTRYPOINT ["java","-jar","/ms.cliente.jar"]