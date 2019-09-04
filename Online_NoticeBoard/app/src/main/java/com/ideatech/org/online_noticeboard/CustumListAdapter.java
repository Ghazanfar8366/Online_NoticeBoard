package com.ideatech.org.online_noticeboard;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MianGhazanfar on 10/30/2016.
 */
public class CustumListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Notification> notificationList;

    public CustumListAdapter(Activity activity, List<Notification> notificationList) {
        this.activity = activity;
        this.notificationList = notificationList;
    }
    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
            inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            convertView=inflater.inflate(R.layout.list_row,null);

            TextView eventid= (TextView) convertView.findViewById(R.id.id);
            TextView eventDate= (TextView) convertView.findViewById(R.id.dateee);
            TextView eventTime= (TextView) convertView.findViewById(R.id.timeee);
            TextView eventMsg= (TextView) convertView.findViewById(R.id.messagee);

        TextView eventTitle= (TextView) convertView.findViewById(R.id.titlee);

            Notification notice=notificationList.get(position);
            eventid.setText("Notification Id: "+notice.getId());
            eventDate.setText("Event Date: "+notice.getDate());
            eventTime.setText("Time: "+notice.getTime());
            eventMsg.setText("Discription: "+notice.getMessage());
//            tvlatitude.setText(notice.getLatitude()+"");
//            tvlongitude.setText(order.getLongitude()+"");
        eventTitle.setText(notice.getTitle()+"");

        return convertView;
    }
}
