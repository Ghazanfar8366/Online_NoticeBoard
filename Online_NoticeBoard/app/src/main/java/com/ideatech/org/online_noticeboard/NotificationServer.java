package com.ideatech.org.online_noticeboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by MianGhazanfar on 12/4/2016.
 */
public class NotificationServer {
    Context context;
    private ProgressDialog pDialog;
    public NotificationServer(Context ctx) {
      context=ctx;
        pDialog=new ProgressDialog(context);
        pDialog.setMessage("Loading...");

    }


    int flag=0;
    public void makeJsonArrayRequest(final Context ctx, final String URL) throws Exception{
showDialog();
        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("responce", response.toString());
                        MainActivity.notificationList.clear();
                        try {

                            for (int i = 0; i < response.length()-1; i++) {
                                JSONObject person = (JSONObject) response
                                        .get(i);

                                try {
                                    String event_id = person.getString("id");
                                    String event_date = person.getString("date");
                                    String event_time = person.getString("time");
                                    String event_message = person.getString("message");
                                    String title = person.getString("title");
                                    Notification notice=new Notification(event_id,title,event_message,title,event_time,event_date);
                                    notice.setId(event_id);
                                    notice.setTitle(title);
                                    notice.setMessage(event_message);
                                    notice.setType(title);
                                    notice.setTime(event_time);
                                    notice.setDate(event_date);
                                    MainActivity.notificationList.add(notice);
                                }catch (NumberFormatException e){
                                    e.printStackTrace();
                                }
                            }


                            MainActivity.adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(),
//                                    "Error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG).show();
                            Log.e("Exception",e.getMessage());
                        }
                        hideDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", "Error: " + error.getMessage());

//                MapsActivity.isListPopulated=false;
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_SHORT).show();

                hideDialog();
                if(flag<=3) {
                    Toast.makeText(ctx, "Network Problem", Toast.LENGTH_SHORT).show();
                    try {

                        makeJsonArrayRequest(ctx,URL);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    flag++;
                }
            }
        });
        // Adding request to request queue
        AppController.getInstance().addTORequestQueue(req,"NewReq");
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
