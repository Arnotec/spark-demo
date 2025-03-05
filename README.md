# spark-demo

[Architecture]
- Cluster spark (1 master et 2 workers - docker-compose.yml)
- Une application spring boot (deployée dans un conteneur - compose.yml) qui submits les jobs via spark-submit
- Deux jobs PiSparkJob (calcul de pi méthode Monte Carlo) et CountCharSparkJob (compte le nombre de ligne contenant les lettres a et b d'un fichier)
