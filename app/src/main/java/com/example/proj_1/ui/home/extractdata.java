package com.example.proj_1.ui.home;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.proj_1.ui.MySingleton;
import com.example.proj_1.ui.whetherReportmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class extractdata {
    private static String city_Id_url="https://www.metaweather.com/api/location/search/?query=";
    private static String byId_url="https://www.metaweather.com/api/location/";
    Context context;
    String cityId="";

    public extractdata(Context context)
    {
        this.context=context;
    }


    public interface VolleyResponseListener {

        void onError(String message);
        void onResponse(String response);

    }
    public void getcityId( String city, final extractdata.VolleyResponseListener listener) {
        String url=city_Id_url+city;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET,url, null, new Response.Listener<JSONArray> (){

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject=response.getJSONObject(0);

                            listener.onResponse(jsonObject.getString("woeid"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.toString());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    public interface ForcastbyID {

        void onError(String message);
        void onResponse(whetherReportmodel response);

    }

    public void getforcastbyId(Context context,String cityId,final extractdata.ForcastbyID listener){
        String Url=byId_url+cityId;
        List<whetherReportmodel> report=new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray=response.getJSONArray("consolidated_weather");

                    whetherReportmodel whetherreportmodel = new whetherReportmodel();


                        JSONObject first_day = jsonArray.getJSONObject(0);

                        whetherreportmodel.setWeather_abbr(first_day.getString("weather_state_abbr"));
                        whetherreportmodel.setWeather_state_name(first_day.getString("weather_state_name"));
                        whetherreportmodel.setThe_temp((float) first_day.getInt("the_temp"));
                        whetherreportmodel.setMin_temp((float) first_day.getInt("min_temp"));
                        whetherreportmodel.setMax_temp((float) first_day.getInt("max_temp"));
                        whetherreportmodel.setHumidity((float) first_day.getInt("humidity"));
                        whetherreportmodel.setPredictability((float) first_day.getInt("predictability"));
                        whetherreportmodel.setPredictability((float) first_day.getInt("wind_speed"));

                    listener.onResponse(whetherreportmodel);

                } catch (JSONException e) {
                    e.printStackTrace();
                } }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.toString());
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public interface Forcastbycity {
        void onError(String message);
        void onResponse(whetherReportmodel response);
    }

    public void getforcastbycity(Context context,String city,final extractdata.ForcastbyID listener) {
        String url=city_Id_url+city;
        getcityId(city, new extractdata.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                listener.onError(message);
            }

            @Override
            public void onResponse(String response) {
                getforcastbyId(context, response, new extractdata.ForcastbyID() {
                    @Override
                    public void onError(String message) {
                        listener.onError(message);
                    }

                    @Override
                    public void onResponse(whetherReportmodel response) {
                        listener.onResponse(response);
                    }
                });
            }
        });
    }
}
