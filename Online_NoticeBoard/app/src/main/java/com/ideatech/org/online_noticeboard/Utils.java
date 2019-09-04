package com.ideatech.org.online_noticeboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.view.View;



/**
 * Created by MianGhazanfar on 10/25/2016.
 */
public  class Utils {
    public static void showSettingsAlert(String provider ,Context ctx) {

    final String providestr=provider;
        final Context context=ctx;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        alertDialog.setTitle(" SETTINGS");
        if(provider.equalsIgnoreCase("GPS")){
            alertDialog.setMessage((R.string.gps_alert));
        }else if(provider.equalsIgnoreCase("DATA")) {
            alertDialog.setMessage(R.string.data_alert);
        }
        alertDialog.setPositiveButton((R.string.action_settings),	new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=null;
                if(providestr.equalsIgnoreCase("GPS")){
                intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(intent);
                }else if(providestr.equalsIgnoreCase("DATA")){
                 //   intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                    dialog.cancel();
                }


            }
        });
        alertDialog.setNegativeButton((R.string.Cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
    public static boolean isConnected(Context ctx){
        ConnectivityManager connMgr = (ConnectivityManager) ctx.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


}
