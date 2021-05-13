# RecommendationSystem
Q1) What are the titles of top 5 most popular movies i.e. have the most ranking in the whole dataset?

Below are the Movie Titles which are rated 5 with number of count
+-------+------------------------------------------------------------+----+------+-----+
|MovieID|Title                                                       |RANK|Rating|COUNT|
+-------+------------------------------------------------------------+----+------+-----+
|356    |Forrest Gump (1994)                                         |1   |5.0   |46304|
|296    |Pulp Fiction (1994)                                         |1   |5.0   |44919|
|593    |Silence of the Lambs, The (1991)                            |1   |5.0   |39162|
|260    |Star Wars: Episode IV - A New Hope (a.k.a. Star Wars) (1977)|1   |5.0   |37557|
|608    |Fargo (1996)                                                |1   |5.0   |36428|
+-------+------------------------------------------------------------+----+------+-----+

(Q2) What are the top 5 ranked movie genres on average in the whole dataset?

+-----------+------------------+
|Genres     |AVG_GENRE_RATE    |
+-----------+------------------+
|Film-Noir  |3.913657365189373 |
|War        |3.6828568008421616|
|Documentary|3.650731064591302 |
|IMAX       |3.607518632178869 |
|Drama      |3.5717668496126738|
+-----------+------------------+

(Q3) How many movies have been ranked the most consecutive days?

The Number of Distinct Movies Rated daily -> 9066

Used Random Split to split the data into 80% training Data and 20% testing Data

(Q4) What are the top 5 recommended movies made to one user, e.g. , UserID = 122 (any user can be selected)

Top 5 Movies Suggested for User 122
+------+------------------+-------+---------------------------------------------------------------------+
|UserID|recommendations   |MovieID|Title                                                                |
+------+------------------+-------+---------------------------------------------------------------------+
|122   |[42783, 5.4747925]|42783  |Shadows of Forgotten Ancestors (1964)                                |
|122   |[32657, 5.4232507]|32657  |Man Who Planted Trees, The (Homme qui plantait des arbres, L') (1987)|
|122   |[53355, 5.3582325]|53355  |Sun Alley (Sonnenallee) (1999)                                       |
|122   |[33264, 5.3312764]|33264  |Satan's Tango (Sátántangó) (1994)                                    |
|122   |[46231, 5.254885] |46231  |Stoned (2005)                                                        |
+------+------------------+-------+---------------------------------------------------------------------+

(Q5) What are the top 5 movies that are most frequently recommended by your model? (use training set)

Most Frequently Recommended Movies
+-------+--------------------------------------------------------------------------------+-----+
|MovieID|Title                                                                           |COUNT|
+-------+--------------------------------------------------------------------------------+-----+
|32657  |Man Who Planted Trees, The (Homme qui plantait des arbres, L') (1987)           |48941|
|33264  |Satan's Tango (Sátántangó) (1994)                                               |33087|
|42783  |Shadows of Forgotten Ancestors (1964)                                           |29141|
|5194   |Who's Singin' Over There? (a.k.a. Who Sings Over There) (Ko to tamo peva) (1980)|28369|
|32444  |Carmen (1983)                                                                   |21188|
+-------+--------------------------------------------------------------------------------+-----+

(Q6) Calculate the RMSE of your model for your test set.

The RMSE of the Model on test Data is -> 0.8148586812127675