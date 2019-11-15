/**
 * Note- I created method for both JavaSparkContext & SQLContext for your reference.
 * you can use whichever is applicable according to your project requirement.
 */
package main.java.utility;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

/**
 * @author Abhishek Jain
 */
public class SparkUtils {

    /* call this method to collect SparkSession. */
    public static SparkSession getSparkSession()
    {
        SparkConf sparkConf = new SparkConf().setAppName("SparkFirstApp").setMaster("local[*]");
        SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
        return sparkSession;
    }

    /* call this method to collect SQLContext. */
    public static SQLContext getSqlContext()
    {
        return (new SQLContext(getJavaSparkContext()));
    }

    /* call this method to collect JavaSparkContext. */
    public static JavaSparkContext getJavaSparkContext()
    {
        SparkConf sparkConf = new SparkConf().setAppName("SparkFirstApp").setMaster("local[*]");
        return (new JavaSparkContext(sparkConf));
    }
}

