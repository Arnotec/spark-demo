# Utiliser une image de base avec Java et Spark préinstallés
FROM bitnami/spark:latest

# Exposer les ports nécessaires
EXPOSE 4040 7077 8080 8081

# Définir les variables d'environnement
ENV SPARK_MODE="master"
ENV SPARK_RPC_AUTHENTICATION_ENABLED="no"
ENV SPARK_RPC_ENCRYPTION_ENABLED="no"
ENV SPARK_LOCAL_STORAGE_ENCRYPTION_ENABLED="no"
ENV SPARK_SSL_ENABLED="no"