package org.arnotec.spark.job;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.util.Arrays;

public class CountCharSparkJob {
    public static void main(String[] args) {
        System.out.println("=============== In Count Chars Job ======================");



        if (args.length < 1) {
            System.err.println("Usage : SparkCountCharJob <fichier>");
            System.exit(1);
        }
        String logFile = args[0];

//        File directory = new File(logFile);
//        if (directory.exists() && directory.isDirectory()) {
//            System.out.println("++++++++++ liste des elements du repertoire : " + Arrays.asList(directory.list()));
//        } else {
//            throw new RuntimeException("Le répertoire n'existe pas ou n'est pas accessible.");
//        }

        SparkSession spark = SparkSession.builder()
                    .appName("SparkCountCharJob")
                    .getOrCreate();

        try {
            Dataset<String> logData = spark.read().textFile(logFile).cache();

            long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
            long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

            System.out.println("=========> Lines with a: " + numAs + ", lines with b: " + numBs);
        } catch (Exception e) {
            System.err.println("Erreur lors du traitement du fichier : " + e.getMessage());
        } finally {
            spark.stop();
        }
    }
}