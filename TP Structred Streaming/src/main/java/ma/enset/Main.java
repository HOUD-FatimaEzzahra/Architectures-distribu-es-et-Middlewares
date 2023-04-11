package ma.enset;

import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;

import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws TimeoutException, StreamingQueryException {
        Logger.getLogger("org").setLevel(org.apache.log4j.Level.OFF);
        SparkSession spark = SparkSession
                .builder()
                .appName("TP Structred Streaming")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dfLines=spark.readStream()
                .format("socket")
                .option("host","localhost")
                .option("port",8000)
                .load();
        dfLines.printSchema();
        StreamingQuery query = dfLines.writeStream()
                .format("console")
                .trigger(Trigger.ProcessingTime("5 seconds"))
                .outputMode("append")
                .start();
        query.awaitTermination();
    }
}