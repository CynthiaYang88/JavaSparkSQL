package com.github.p2g3.s3testing.io;

import com.github.p2g3.s3testing.startup.CreateSparkSession;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class LoadCSV {
    CreateSparkSession session = CreateSparkSession.getInstance();
    
    public Dataset<Row> getCSVFileSession(String fileName){
        
        SparkSession sparkSession = this.session.getSession();
        if (sparkSession == null){
            System.out.println("SPARKSESSION CODE FAILURE!!!");
        }
        Dataset<Row> dataCSV = sparkSession
            .read()
            .format("csv")
            .option("header", "true")
            .load(fileName); 

        return dataCSV;
    }
}