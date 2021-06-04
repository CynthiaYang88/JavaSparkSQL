package com.github.p2g3.s3testing.startup;

import com.github.p2g3.s3testing.io.LoadCSV;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;

public class FileParser {
    
    public FileParser() {
    }
    
    public Dataset<Row> parseFile(){
        //reads in CSV data from specific file
        //String fileName = "vgsales-12-4-2019.csv"; //filename
        String fileName ="s3a://project-2-group-3-bucket-cpbc/Input/vgsales-12-4-2019.csv";
        Dataset<Row> data = new LoadCSV().getCSVFileSession(fileName);
        //transforms it to collect data together
        Dataset<Row> data2 = data.select(functions.coalesce(data.col("Total_Shipped"),data.col("Global_Sales")), data.col("*"))
            .drop(data.col("Last_Update"))
            .drop(data.col("url"))
            .drop(data.col("status"))
            .drop(data.col("img_url"))
            .drop(data.col("Total_Shipped"));
        //and drop unnecessary rows
        Dataset<Row> data3 = data2.withColumn("Global_Sales", data2.col("coalesce(Total_Shipped, Global_Sales)"))
            .drop(data2.col("coalesce(Total_Shipped, Global_Sales)")).cache();

        return data3;
    }

    
}