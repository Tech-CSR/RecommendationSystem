package com.assessment.ml;

import com.assessment.ml.analytics.MovieAnalytics;
import com.assessment.ml.context.WorkflowContext;
import com.assessment.ml.estimate.MovieRecommender;
import com.assessment.ml.load.LoadData;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.SparkSession;

public class Main {

    public static void main(String[] args) {

        System.setProperty("hadoop.home.dir", "c:/hadoop");
        Logger.getLogger("org.apache").setLevel(Level.ERROR);

        SparkSession spark = SparkSession.builder().appName("Movie Recommendation System")
                .config("spark.sql.warehouse.dir", "file:///c:/tmp/")
                .master("local[*]").getOrCreate();

        spark.sqlContext().setConf("spark.sql.shuffle.partitions","24");

        WorkflowContext context = new WorkflowContext();

        LoadData.loadData(context,spark);
        MovieAnalytics.getStats(context);
        MovieRecommender.recommendMovie(context);

    }
}
