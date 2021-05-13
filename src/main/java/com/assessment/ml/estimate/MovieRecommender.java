package com.assessment.ml.estimate;

import com.assessment.ml.context.WorkflowContext;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.expressions.Window;

import java.util.List;

import static com.assessment.ml.util.Constants.*;
import static org.apache.spark.sql.functions.*;

public class MovieRecommender {

    public static void recommendMovie(WorkflowContext context) {

        Dataset<Row> movieDf = context.getJoinDf();

        movieDf = movieDf.withColumn(Rating, when(col(Rating).isNull(), lit(0)).otherwise(col(Rating)));

        movieDf = movieDf.filter(col(MovieID).isNotNull())
                .filter(col(UserID).isNotNull());

        Dataset<Row>[] trainTest = movieDf.randomSplit(new double[]{0.8, 0.2});
        Dataset<Row> train = trainTest[0];
        Dataset<Row> test = trainTest[1];

        ALS als = new ALS()
                .setMaxIter(10)
                .setRegParam(0.1)
                .setUserCol(UserID)
                .setItemCol(MovieID)
                .setRatingCol(Rating);

        ALSModel model = als.fit(train);
        model.setColdStartStrategy("drop");

        Dataset<Row> userRecs = model.recommendForAllUsers(5).cache();

        userRecs = userRecs.withColumn("recommendations", explode(col("recommendations")))
                .withColumn(MovieID, col("recommendations").getItem(MovieID));

        Dataset<Row> movies = context.getMovieDf();

        userRecs = userRecs.join(movies,userRecs.col(MovieID).equalTo(movies.col(MovieID))).select(userRecs.col("*"),movies.col(Title));

        /*Top 5 Movies Suggested for User 122*/
        System.out.println("Top 5 Movies Suggested for User 122");
        userRecs.filter(col(UserID).equalTo(122)).show(false);

        /*Frequently Recommended Movies*/
        System.out.println("Most Frequently Recommended Movies");
        userRecs = userRecs.withColumn("COUNT", approx_count_distinct(UserID).over(Window.partitionBy(MovieID)))
                .withColumn(ROW_NUM,row_number().over(Window.partitionBy(MovieID).orderBy(MovieID)));

        userRecs.filter(col(ROW_NUM).equalTo(1)).select(MovieID,Title,"COUNT").orderBy(col("COUNT").desc()).show(5,false);

        /*RMSE on Test Data*/
        Dataset<Row> prediction = model.transform(test);

        RegressionEvaluator evaluator = new RegressionEvaluator()
                .setMetricName("rmse")
                .setLabelCol("Rating")
                .setPredictionCol("prediction");
        double rmse = evaluator.evaluate(prediction);

        System.out.println("The RMSE of the Model on test Data is -> " + rmse);

    }
}
