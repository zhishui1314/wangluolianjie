package com.anke.vehicle.wangluolianjie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 1){
          tv.setText("在线");
        }else if (msg.what == 2){
          tv.setText("离线");
        }
    }
};
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
         // register();
        startService(new Intent(this,MyService.class));
        MyService.handler = handler;


    }

    private void register() {
        MyReceiver myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        if (myReceiver != null)
            registerReceiver(myReceiver, filter);

    }
    public class MyReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager con=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
            boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
            if(wifi|| internet){
                handler.sendEmptyMessage(1);
            }else{
                handler.sendEmptyMessage(2);
            }
        }
    }
}
