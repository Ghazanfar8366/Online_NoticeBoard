package com.ideatech.org.online_noticeboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class DetailActivity extends AppCompatActivity {
    TextView titletv, datetv, timetv, msgtv;
    String title, date, time, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.olxdetail);
        Intent extras = getIntent();
        title = extras.getStringExtra("title");
        date = extras.getStringExtra("date");
        time = extras.getStringExtra("time");
        msg = extras.getStringExtra("msg");

        //ImageView imgvew= (ImageView) findViewById(R.id.detail_image);
        titletv = (TextView) findViewById(R.id.txt_title);
        datetv = (TextView) findViewById(R.id.txt_date);
        timetv = (TextView) findViewById(R.id.txt_time);
        msgtv = (TextView) findViewById(R.id.txt_msg);
        titletv.setText(title);
        datetv.setText(date);
        timetv.setText(time);
        msgtv.setText(msg);
    }

    public void makeCall(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0419200161"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }
}
