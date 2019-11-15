package main.java;

import main.java.utility.LogManager;
import main.java.utility.SparkUtils;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.spark.sql.SparkSession;

/**
 * @author Abhishek Jain
 */

public class EntryPoint extends Configured implements Tool {
    //private static SQLContext sqlContext;
    //private static JavaSparkContext javaSparkContext;
    private static SparkSession sparkSession;

    public static void main(String[] args) throws Exception {
        System.out.println("############## START ##############");
        ToolRunner.run(new EntryPoint(), args);
        LogManager.logger.info("Testing logging");
        System.out.println("############## END ##############");
    }

    public int run(String[] strings) throws Exception {
        DataProcessor dataProcessor = new DataProcessor();
        sparkSession = SparkUtils.getSparkSession();
        dataProcessor.processListData(sparkSession);
        return 0;
    }
}
