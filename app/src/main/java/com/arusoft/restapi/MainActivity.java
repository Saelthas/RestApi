package com.arusoft.restapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arusoft.restapi.PubApi.PubService;
import com.arusoft.restapi.models.Pub;
import com.arusoft.restapi.models.PubRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Pupps";
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.112/sdk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();
    }

    private void obtenerDatos() {
        PubService pubService = retrofit.create(PubService.class);
        Call<PubRespuesta> pubRespuestaCall = pubService.obtenerPubs();
        pubRespuestaCall.enqueue(new Callback<PubRespuesta>() {
            @Override
            public void onResponse(Call<PubRespuesta> call, Response<PubRespuesta> response) {
                if(response.isSuccessful()){
                    PubRespuesta pubRespuesta= response.body();
                    ArrayList<Pub> listaPub= pubRespuesta.getResults();

                    for (int i = 0; i < listaPub.size(); i++) {
                        Pub p = listaPub.get(i);
                        Toast.makeText(MainActivity.this, "Pub: "+ p.getFirst_name(), Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Pub: "+ p.getFirst_name());
                        //Log.i(TAG, "apellido: "+ p.getLast_name());
                    }
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PubRespuesta> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}
