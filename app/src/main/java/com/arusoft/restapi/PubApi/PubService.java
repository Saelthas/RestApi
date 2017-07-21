package com.arusoft.restapi.PubApi;

import com.arusoft.restapi.models.Pub;
import com.arusoft.restapi.models.PubRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Saelthas on 20/6/2017.
 */

public interface PubService {
    @GET("users/index.php")
    //Call<ArrayList<Pub>> obtenerPubs();
    Call<PubRespuesta> obtenerPubs();
}
