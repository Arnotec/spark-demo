package org.arnotec.spark.job;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

public class PiSparkJob {
    public static void main(String[] args) {
        System.out.println("======== Dans le job calcul de Pi =============");

        // Initialiser SparkSession
        SparkSession spark = SparkSession.builder()
                .appName("SparkPiJob") // Nom de l'application
                .getOrCreate();

        // Créer un contexte Spark
        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        int numSamples = 1000000; // Nombre d'échantillons pour le calcul de Pi
        List<Integer> l = new ArrayList<>(numSamples);
        for (int i = 0; i < numSamples; i++) {
            l.add(i);
        }

        // Créer un RDD distribué
        JavaRDD<Integer> dataSet = jsc.parallelize(l);

        // Calculer Pi en utilisant une méthode Monte Carlo
        long count = dataSet.map(integer -> {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            return (x * x + y * y <= 1) ? 1 : 0;
        }).reduce(Integer::sum);

        // Afficher le résultat
        System.out.println(" ========> Pi est environ : " + 4.0 * count / numSamples + " <======== <br><br><br><br>");

        // Arrêter le contexte Spark
        jsc.stop();
    }

}