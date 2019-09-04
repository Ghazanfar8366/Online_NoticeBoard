package com.ideatech.org.online_noticeboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView noticeListView;
    public static ArrayList<Notification> notificationList;
    public static CustumListAdapter adapter;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noticeListView = (ListView) findViewById(R.id.notice_listview);
        notificationList = new ArrayList<>();
        adapter = new CustumListAdapter(this, notificationList);
        noticeListView.setAdapter(adapter);
        noticeListView.setClickable(false);
        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(MainActivity.this, noticeListView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                TextView titletv= (TextView) view.findViewById(R.id.titlee);
                TextView date= (TextView) view.findViewById(R.id.dateee);
                TextView time= (TextView) view.findViewById(R.id.timeee);
                TextView msg= (TextView) view.findViewById(R.id.messagee);

                String title=titletv.getText().toString();
                String date_data=date.getText().toString();
                String time_data=time.getText().toString();
                String msg_data=msg.getText().toString();
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("date",date_data);
                intent.putExtra("time",time_data);
                intent.putExtra("msg",msg_data);

                startActivity(intent);
            }
        });

        NotificationServer server=new NotificationServer(this);
        try {
            server.makeJsonArrayRequest(this,AppConfig.WORKSHOP_URL);
        }catch (Exception e){
            e.printStackTrace();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_workshop) {
            if(Utils.isConnected(this)){
                NotificationServer server=new NotificationServer(this);
                try {
                    server.makeJsonArrayRequest(this,AppConfig.WORKSHOP_URL);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Utils.showSettingsAlert("DATA",this);

            }
            // Handle the camera action
        } else if (id == R.id.nav_entertainment) {
            if(Utils.isConnected(this)){
                NotificationServer server=new NotificationServer(this);
                try {
                    server.makeJsonArrayRequest(this,AppConfig.ENTERTAINMENT_URL);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Utils.showSettingsAlert("DATA",this);

            }

        } else if (id == R.id.nav_exhibition) {
            if(Utils.isConnected(this)){
                NotificationServer server=new NotificationServer(this);
                try {
                    server.makeJsonArrayRequest(this,AppConfig.EXHIBITION_URL);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Utils.showSettingsAlert("DATA",this);

            }

        } else if (id == R.id.nav_logout) {



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
