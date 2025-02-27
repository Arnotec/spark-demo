package org.arnotec.spark.job.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class SparkSubmitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkSubmitService.class);

    @Value("${spark.master}")
    private String sparkMaster;

    @Value("${job.pi.jar}")
    private String jobPiJar;

    @Value("${job.pi.class}")
    private String jobPiClass;

    @Value("${job.count.jar}")
    private String jobCountJar;

    @Value("${job.count.class}")
    private String jobCountClass;

    @Value("${job.count.args}")
    private String jobCountArgs;


    public String submitPiSparkJob() {
        try {

            String command = String.format("spark-submit --class %s --master %s %s %s", jobPiClass, sparkMaster, jobPiJar, jobCountArgs);
            LOGGER.info("********** Execution de la commande ***** : {}", command);

            // Exécution de la commande
            Process process = Runtime.getRuntime().exec(command);

            // Lecture de la sortie
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n").append("<br>");
            }

            // Lecture des erreurs (facultatif)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                output.append("ERROR: ").append(line).append("\n").append("<br>");
            }
            // Attendre la fin du processus
            int exitCode = process.waitFor();
            LOGGER.info("Job Spark terminé avec le code de sortie : {} <br>", exitCode);

            return "Job terminé avec succès." +
                    "\n ========================<br>" +
                    "\n Sortie : <br><br>" + output;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la soumission du job : " + e.getMessage();
        }
    }

    public String submitCountCharSparkJob() {

        try {

            String command = String.format("spark-submit --class %s --master %s %s %s", jobCountClass, sparkMaster, jobCountJar, jobCountArgs);
            LOGGER.info("Execution de la commande : {}", command);

            // Exécution de la commande
            Process process = Runtime.getRuntime().exec(command);

            // Lecture de la sortie
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n").append("<br>");
            }

            // Lecture des erreurs (facultatif)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                output.append("\nERROR: ").append(line).append("\n").append("<br>");
            }
            // Attendre la fin du processus
            int exitCode = process.waitFor();
            LOGGER.info("Job Spark terminé avec le code de sortie : {} <br>", exitCode);

            return "Job terminé avec succès.\nSortie : <br><br>" + output;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la soumission du job : " + e.getMessage();
        }
    }


}
