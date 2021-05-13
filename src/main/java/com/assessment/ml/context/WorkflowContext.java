package com.assessment.ml.context;

import lombok.Data;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;


public class WorkflowContext {

    public Dataset<Row> getMovieDf() {
        return movieDf;
    }

    public void setMovieDf(Dataset<Row> movieDf) {
        this.movieDf = movieDf;
    }

    public Dataset<Row> getRatingDf() {
        return ratingDf;
    }

    public void setRatingDf(Dataset<Row> ratingDf) {
        this.ratingDf = ratingDf;
    }

    private Dataset<Row> movieDf;
    private Dataset<Row> ratingDf;

    public Dataset<Row> getJoinDf() {
        return joinDf;
    }

    public void setJoinDf(Dataset<Row> joinDf) {
        this.joinDf = joinDf;
    }

    private Dataset<Row> joinDf;

}
