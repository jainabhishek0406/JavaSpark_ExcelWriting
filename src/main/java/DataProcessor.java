package main.java;

import main.java.model.ExcelUtilModel;
import main.java.utility.MyReportGenerator;
import org.apache.spark.sql.*;

import java.io.IOException;
import java.util.List;

//import org.apache.poi.ss.usermodel.Row;

/**
 * @author Abhishek Jain
 *
 * https://allaboutscala.com/big-data/spark/
 * https://spark.apache.org/docs/latest/sql-getting-started.html
 */
public class DataProcessor {
    public void processListData(SparkSession sparkSession) throws AnalysisException, IOException {
        System.out.println("##### Inside processListData #####");
        System.out.println("sparkSession = " + sparkSession);

        Dataset<Row> custDS = sparkSession
                .read()
                .format("csv")
                .option("header", "true")
                .option("inferSchema", "true")
                .option("dateFormat","yyyy-MM-dd HH:mm:ss")
                .load("data/customers.csv");

        Dataset<Row> orderDS = sparkSession
                .read()
                .format("csv")
                .option("header", "true")
                .option("inferSchema", "true")
                .option("dateFormat","yyyy-MM-dd HH:mm:ss")
                .load("data/orders.csv");

        //dataFrame1.printSchema();

        custDS.createTempView("customers");
        orderDS.createTempView("orders");

        custDS.select("name").show();
        orderDS.select("type").show();

        //Dataset<Row> custOrderJoinDS = custDS.join(orderDS.filter(col("unit").gt(4)), custDS.col("custid_pk").equalTo(orderDS.col("custid_fk")));

        Dataset<Row> custOrderJoinDS = custDS.join(orderDS, custDS.col("custid_pk").equalTo(orderDS.col("custid_fk")));

        //custOrderJoinDS.show();

        Dataset<ExcelUtilModel> modelDS =
                custOrderJoinDS.as(Encoders.bean(ExcelUtilModel.class));

        List<ExcelUtilModel> excelUtilModelList = modelDS.collectAsList();

        for (ExcelUtilModel excelUtilModel : excelUtilModelList) {
            System.out.println(excelUtilModelList.toString());
        }
        MyReportGenerator myReportGenerator = new MyReportGenerator();
        myReportGenerator.generateReport(excelUtilModelList);
    }
}


