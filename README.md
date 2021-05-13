# RecommendationSystem
**Q1) What are the titles of top 5 most popular movies i.e. have the most ranking in the whole dataset?**

**Below are the Movie Titles which are rated 5 with number of count**
<table>
<thead>
  <tr>
    <th>MovieID</th>
    <th>Title                                                       </th>
    <th>RANK</th>
    <th>Rating</th>
    <th>COUNT</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>356</td>
    <td>Forrest Gump   (1994)                                         </td>
    <td>1</td>
    <td>5</td>
    <td>46304</td>
  </tr>
  <tr>
    <td>296</td>
    <td>Pulp Fiction   (1994)                                         </td>
    <td>1</td>
    <td>5</td>
    <td>44919</td>
  </tr>
  <tr>
    <td>593</td>
    <td>Silence of the Lambs,   The (1991)                            </td>
    <td>1</td>
    <td>5</td>
    <td>39162</td>
  </tr>
  <tr>
    <td>260</td>
    <td>Star Wars: Episode IV   - A New Hope (a.k.a. Star Wars) (1977)</td>
    <td>1</td>
    <td>5</td>
    <td>37557</td>
  </tr>
  <tr>
    <td>608</td>
    <td>Fargo (1996)                                                </td>
    <td>1</td>
    <td>5</td>
    <td>36428</td>
  </tr>
</tbody>
</table>

**(Q2) What are the top 5 ranked movie genres on average in the whole dataset?**

<table>
<thead>
  <tr>
    <th>Genres</th>
    <th>AVG_GENRE_RATE</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Film-Noir<br>War<br>Documentary<br>IMAX<br>Drama</td>
    <td>3.913657365189373<br>3.6828568008421616<br>3.650731064591302<br>3.607518632178869<br>3.5717668496126738</td>
  </tr>
</tbody>
</table>

**(Q3) How many movies have been ranked the most consecutive days?**

The Number of Distinct Movies Rated daily -> 9066

**Used Random Split to split the data into 80% training Data and 20% testing Data**

**(Q4) What are the top 5 recommended movies made to one user, e.g. , UserID = 122 (any user can be selected)**

**Top 5 Movies Suggested for User 122**

<table>
<thead>
  <tr>
    <th>UserID</th>
    <th>recommendations   </th>
    <th>MovieID</th>
    <th>Title                                                                </th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>122</td>
    <td>[42783, 5.4747925]</td>
    <td>42783</td>
    <td>Shadows of Forgotten&nbsp;&nbsp;&nbsp;Ancestors (1964)                                </td>
  </tr>
  <tr>
    <td>122</td>
    <td>[32657, 5.4232507]</td>
    <td>32657</td>
    <td>Man Who Planted&nbsp;&nbsp;&nbsp;Trees, The (Homme qui plantait des arbres, L') (1987)</td>
  </tr>
  <tr>
    <td>122</td>
    <td>[53355, 5.3582325]</td>
    <td>53355</td>
    <td>Sun Alley&nbsp;&nbsp;&nbsp;(Sonnenallee) (1999)                                       </td>
  </tr>
  <tr>
    <td>122</td>
    <td>[33264, 5.3312764]</td>
    <td>33264</td>
    <td>Satan's Tango&nbsp;&nbsp;&nbsp;(Sátántangó) (1994)                                    </td>
  </tr>
  <tr>
    <td>122</td>
    <td>[46231,&nbsp;&nbsp;&nbsp;5.254885] </td>
    <td>46231</td>
    <td>Stoned (2005)                                                        </td>
  </tr>
</tbody>
</table>

**(Q5) What are the top 5 movies that are most frequently recommended by your model? (use training set)**

**Most Frequently Recommended Movies**

<table>
<thead>
  <tr>
    <th>UserID</th>
    <th>recommendations   </th>
    <th>MovieID</th>
    <th>Title                                                                </th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>122</td>
    <td>[42783, 5.4747925]</td>
    <td>42783</td>
    <td>Shadows of Forgotten&nbsp;&nbsp;&nbsp;Ancestors (1964)                                </td>
  </tr>
  <tr>
    <td>122</td>
    <td>[32657, 5.4232507]</td>
    <td>32657</td>
    <td>Man Who Planted&nbsp;&nbsp;&nbsp;Trees, The (Homme qui plantait des arbres, L') (1987)</td>
  </tr>
  <tr>
    <td>122</td>
    <td>[53355, 5.3582325]</td>
    <td>53355</td>
    <td>Sun Alley&nbsp;&nbsp;&nbsp;(Sonnenallee) (1999)                                       </td>
  </tr>
  <tr>
    <td>122</td>
    <td>[33264, 5.3312764]</td>
    <td>33264</td>
    <td>Satan's Tango&nbsp;&nbsp;&nbsp;(Sátántangó) (1994)                                    </td>
  </tr>
  <tr>
    <td>122</td>
    <td>[46231,&nbsp;&nbsp;&nbsp;5.254885] </td>
    <td>46231</td>
    <td>Stoned (2005)                                                        </td>
  </tr>
</tbody>
</table>

**(Q6) Calculate the RMSE of your model for your test set.**

The RMSE of the Model on test Data is -> **0.8148586812127675**