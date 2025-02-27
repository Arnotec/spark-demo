package org.arnotec.spark.job.controller;

import org.arnotec.spark.job.service.SparkSubmitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spark/submit")
public class SparkController {

    private SparkSubmitService submitService;

    public SparkController(SparkSubmitService submitService) {
        this.submitService = submitService;
    }

    @GetMapping("/pi-job")
    public String submitPiJob() {
         return submitService.submitPiSparkJob();
    }

    @GetMapping("/count-job")
    public String submitCountCharJob() {
        return submitService.submitCountCharSparkJob();
    }
}
