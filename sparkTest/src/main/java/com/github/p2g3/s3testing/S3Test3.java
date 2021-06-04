package com.github.p2g3.s3testing;

import java.io.IOException;

import com.github.p2g3.s3testing.ops.InputVals;
import com.github.p2g3.s3testing.ops.Operations;
import com.github.p2g3.s3testing.startup.CreateSparkSession;
import com.github.p2g3.s3testing.startup.FileParser;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class S3Test3 {
    public static void main(String[] args) throws IOException
    {  
        CreateSparkSession startSession = CreateSparkSession.getInstance(); //Starts SparkSession
        SparkSession session = startSession.getSession(); //pulls a reference to the session

        Dataset<Row> data = new FileParser().parseFile();
        //data.printSchema();

        InputVals val = new InputVals(args);
        
        new Operations().runOperations(data, session, val);

        
//spark.sparkContext().hadoopConfiguration().addResource("conf.xml");

    }
}