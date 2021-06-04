package com.github.p2g3.s3testing.ops;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;

public class Operations {
    
    public Operations() {
    }

    public void runOperations(Dataset<Row> data, SparkSession session, InputVals val){

        data.createOrReplaceTempView("dataset0");
        //data.show();
        //headers for the read in table
        String[] headers = data.columns();
        
        /*System.out.println("Choose your category: \n"+
        "3 Genre\n"+
        "4 ESRB_Rating\n"+
        "5 Platform\n"+
        "6 Publisher\n"+
        "7 Developer");
        int firstH = Integer.parseInt(System.console().readLine());//primary category - string
        System.out.println("\nChoose your independent variable: \n"+
        "8 VGChartz_Score\n"+
        "9 Critic_Score\n"+
        "10 User_Score\n"+
        "11 Global_Sales\n"+
        "12 NA_Sales\n"+
        "13 PAL_Sales\n"+
        "14 JP_Sales\n"+
        "15 Other_Sales\n"+
        "16 Year\n"+
        "17 Vgchartzscore\n");
        int thirdH = Integer.parseInt(System.console().readLine());//independent - int/double
        System.out.println("\nChoose your dependent variable: \n"+
        "8 VGChartz_Score\n"+
        "9 Critic_Score\n"+
        "10 User_Score\n"+
        "11 Global_Sales\n"+
        "12 NA_Sales\n"+
        "13 PAL_Sales\n"+
        "14 JP_Sales\n"+
        "15 Other_Sales\n"+
        "16 Year\n"+
        "17 Vgchartzscore\n");
        int seconH = Integer.parseInt(System.console().readLine());//depependent - int/double
        System.out.println("\nChoose your function: \n"+
        "0 SUM\n"+
        "1 AVG\n");
        int choice2 = Integer.parseInt(System.console().readLine());//choice of function*/
        String[] option1 = {"SUM","AVG"};
        int firstH = val.catg;
        int seconH = val.col2;
        int thirdH = val.col1;
        int choice2 = val.opChoice;


        //drops null values using .na().drop() from table
        Dataset<Row> selectedCategoryPre = session.sql("SELECT "+headers[firstH]+", "
            +headers[seconH]+", "+headers[thirdH]+" FROM dataset0").na().drop();

        //replaces the independent and dependent variables with the double type for calculations
        Dataset<Row> selectedCategory = selectedCategoryPre
            .withColumn(headers[seconH], selectedCategoryPre.col(headers[seconH]).cast(DataTypes.DoubleType))
            .withColumn(headers[thirdH], selectedCategoryPre.col(headers[thirdH]).cast(DataTypes.DoubleType))
            .cache();
        selectedCategory.createOrReplaceTempView("newtable");

        //creates a string array of all unique values in the category selection
        Dataset<Row> sCCount = session.sql("SELECT "+headers[firstH]+
            " ,COUNT(*) AS New_Column FROM newtable GROUP BY "+headers[firstH]).cache();
        //sCCount.createOrReplaceTempView("tableOfCategories");
        int selectCount = (int) sCCount.count();
        List<Row> list1 = new ArrayList<>();
        list1 = sCCount.select(headers[firstH]).takeAsList(selectCount);

        List<Dataset<Row>> addList = new ArrayList<>();

        for (int i =0; i< selectCount; i++){
            //System.out.println(list1.get(i).get(0).toString());

            Dataset<Row> pickTable = session.sql("SELECT "+headers[seconH]+
            ", "+headers[thirdH]+" FROM newtable WHERE "+headers[firstH]+
            "=\'"+list1.get(i).get(0).toString().replace("'", "\'\'")+"\'").cache();
            pickTable.createOrReplaceTempView("pickTable");
            Dataset<Row> yearSales = session.sql("SELECT "+headers[thirdH]+
                " ,ROUND("+option1[choice2]+"("+headers[seconH]+"),2) AS Totals FROM pickTable GROUP BY "+headers[thirdH]);
            yearSales.createOrReplaceTempView("ysTable");
            Dataset<Row> createdYearsTables = session.sql("SELECT * FROM ysTable ORDER BY "+headers[thirdH]);
            addList.add(createdYearsTables);
        }

        //int flag1 = 0;
        //while(flag1 ==0){
            /*for (int i =0; i< selectCount; i++){
                System.out.print(i + "-");
                System.out.println(list1.get(i).get(0).toString());}

            System.out.print("Pick Row to display: ");*/
            //int choice = Integer.parseInt(System.console().readLine());
                int choice = val.subCatChoice;
            //if (choice == -1) {
            //    flag1 = 1;
            //}
            //else{
                addList.get(choice).show(50);
                System.out.println(list1.get(choice).get(0).toString());
                addList.get(choice)
                    .coalesce(1)
                    .write()
                    .mode("overwrite")
                    .option("header", "true")
                    .csv("s3a://project-2-group-3-bucket-cpbc/Output/hello.csv");
            }
        //}
    }


//}