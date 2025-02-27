# Utiliser une image de base avec Java 17
FROM openjdk:17-jdk-alpine

# Installer Spark
RUN apk add --no-cache bash && \
    apk add --no-cache curl tar && \
    curl -O https://downloads.apache.org/spark/spark-3.5.4/spark-3.5.4-bin-hadoop3.tgz && \
    tar -xvzf spark-3.5.4-bin-hadoop3.tgz && \
    mv spark-3.5.4-bin-hadoop3 /opt/spark && \
    rm spark-3.5.4-bin-hadoop3.tgz

# Définir les variables d'environnement Spark
ENV SPARK_HOME=/opt/spark
ENV PATH="$SPARK_HOME/bin:$PATH"

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR de l'application dans le conteneur
COPY target/spark-demo-0.0.1-SNAPSHOT.jar spark-demo-0.0.1-SNAPSHOT.jar
#COPY pi-spark-job/target/pi-spark-job-1.0-SNAPSHOT.jar pi-spark-job-1.0-SNAPSHOT.jar

# Exposer le port sur lequel l'application Spring Boot écoute
EXPOSE 8089

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "spark-demo-0.0.1-SNAPSHOT.jar"]