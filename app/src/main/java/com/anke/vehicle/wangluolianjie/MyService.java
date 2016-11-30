package com.anke.vehicle.wangluolianjie;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    public static Handler handler;

    @Override
    public void onStart(Intent intent, int startId) {
        register();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
