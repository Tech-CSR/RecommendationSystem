package com.assessment.ml.load;

import com.assessment.ml.context.WorkflowContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static com.assessment.ml.util.Constants.*;
import static org.apache.spark.sql.functions.*;

public class LoadData {

    public static void loadData(WorkflowContext context, SparkSession spark) {

        Dataset<Row> movieDf = spark.read().text("src/main/resources/movies.dat");
        Dataset<Row> ratingDf = spark.read().text("src/main/resources/ratings.dat");

        movieDf = movieDf.withColumn(MovieID, split(col("value"), "::").getItem(0).cast(DataTypes.IntegerType))
                .withColumn(Title, split(col("value"), "::").getItem(1))
                .withColumn(Genres, split(col("value"), "::").getItem(2))
                .drop("value");

        ratingDf = ratingDf.withColumn(UserID, split(col("value"), "::").getItem(0).cast(DataTypes.IntegerType))
                .withColumn(MovieID, split(col("value"), "::").getItem(1).cast(DataTypes.IntegerType))
                .withColumn(Rating, split(col("value"), "::").getItem(2).cast(DataTypes.IntegerType))
                .withColumn(Timestamp, split(col("value"), "::").getItem(3).cast(DataTypes.IntegerType))
                .drop("value");

        ratingDf = ratingDf.withColumn(Rating,when(abs(col(Rating)).gt(5),lit(5)).otherwise(col(Rating).multiply(signum(col(Rating)))));

        context.setMovieDf(movieDf);
        context.setRatingDf(ratingDf);

        Dataset<Row> joinDf = movieDf.join(ratingDf, movieDf.col(MovieID).equalTo(ratingDf.col(MovieID)))
                .select(movieDf.col("*"), ratingDf.col(UserID), ratingDf.col(Rating),ratingDf.col(Timestamp));

        joinDf = joinDf.withColumn(Genres, explode(split(col(Genres), "\\|")));

        context.setJoinDf(joinDf);

    }


}
