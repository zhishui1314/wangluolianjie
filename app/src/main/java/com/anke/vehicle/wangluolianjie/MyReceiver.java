package com.anke.vehicle.wangluolianjie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public static Handler handler;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager con=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if(wifi|| internet){
            Log.e("在线","111");
            handler.sendEmptyMessage(1);
        }else{
            Log.e("离线","222");
            handler.sendEmptyMessage(2);
        }
    }
}
