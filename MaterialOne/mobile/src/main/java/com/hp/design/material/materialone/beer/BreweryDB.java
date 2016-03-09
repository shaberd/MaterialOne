package com.hp.design.material.materialone.beer;

import com.hp.design.material.materialone.Constants;
import com.hp.design.material.materialone.beer.pojo.Beer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by dashab on 3/7/16.
 */
public interface BreweryDB {
    @Headers({
            "Accept: application/json",
    })
    @GET(Constants.GET_BEERS)
    Call<Beer> getBeers(@Query("key")String key, @Query("abv")String abvValue, @Query("p")int p);
}
