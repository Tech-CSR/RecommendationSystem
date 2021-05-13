package com.assessment.ml.analytics;

import com.assessment.ml.context.WorkflowContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.expressions.Window;


import static com.assessment.ml.util.Constants.*;
import static org.apache.spark.sql.functions.*;

public class MovieAnalytics {

    public static void getStats(WorkflowContext context) {

        Dataset<Row> movieDf = context.getJoinDf();

        /*Print Top 5 most Rated Movies*/

        System.out.println("Top 5 Movies by ratings");
        movieDf.withColumn("COUNT", count(Rating).over(Window.partitionBy(MovieID, Rating)))
                .withColumn(RANK, row_number().over(Window.partitionBy(MovieID).orderBy(col(Rating).desc(), col("COUNT").desc())))
                .filter(col(RANK).equalTo(1)).select(MovieID, Title, RANK, Rating, "COUNT").orderBy(col(Rating).desc(), col("COUNT").desc()).show(5, false);

        /*Top 5 genres on Avg*/

        Dataset<Row> avgGenreRating = movieDf.withColumn(AVG_GENRE_RATE, avg(Rating).over(Window.partitionBy(Genres)))
                .withColumn(ROW_NUM, row_number().over(Window.partitionBy(Genres).orderBy(Rating)))
                .filter(col(ROW_NUM).equalTo(1));

        System.out.println("The Ave Rating for Genres");
        avgGenreRating.select(Genres, AVG_GENRE_RATE).orderBy(col(AVG_GENRE_RATE).desc()).show(5, false);


        /*Most Consicutive Ratings*/

        movieDf = movieDf.withColumn("DAYS", col(Timestamp).divide(86400))
                .withColumn("BASE_DATE", to_timestamp(lit("1970-01-01 00:00:00")))
                .withColumn("Actual_Date", expr("date_add(BASE_DATE, DAYS)"));

        movieDf = movieDf.withColumn(ROW_NUM, row_number().over(Window.partitionBy(MovieID, "Actual_Date").orderBy(col(Rating).desc())))
                .filter(col(ROW_NUM).equalTo(1));

        movieDf = movieDf.withColumn("NEXT_DATE", lead("Actual_Date", 1).over(Window.partitionBy(MovieID).orderBy("Actual_Date")));

        movieDf = movieDf.withColumn("DAY_DIFF", lit(unix_timestamp(col("NEXT_DATE")).minus(unix_timestamp(col("Actual_Date")))).divide(86400))
                .filter(col("DAY_DIFF").equalTo(1))
                .withColumn("CNT", count("Actual_Date").over(Window.partitionBy(MovieID)))
                .select(MovieID, "CNT", Rating);

        movieDf = movieDf.orderBy(col(Rating).desc(), col("CNT").desc())
                .withColumn(ROW_NUM, row_number().over(Window.partitionBy(MovieID).orderBy(col(Rating).desc())));

        movieDf.filter(col(ROW_NUM).equalTo(1)).show(50, false);

        System.out.println("The Number of Distinct Movies Rated daily -> " + movieDf.select(MovieID).distinct().count());

    }


}
