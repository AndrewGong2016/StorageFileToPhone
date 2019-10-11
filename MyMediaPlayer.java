package com.example.andrewgong.androidxtest;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyMediaPlayer extends MediaPlayer{

    private static final String TAG = "MyMediaPlayer";
    private Context mContext;
    private MyBinder mBinder;

    MyMediaPlayer(Context context){
        mContext = context;
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        ContextWrapper cw = new ContextWrapper(mContext);
        Intent intent = new Intent(cw,MyMediaPlayerService.class);
        mContext.startService(intent);

        mContext.bindService(intent,mConnection,0);

    }


    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Because we have bound to an explicit
            // service that is running in our own process, we can
            // cast its IBinder to a concrete class and directly access it.
            Log.d(TAG, "on Service Connected: ");
            MyBinder binder = (MyBinder) service;
            binder.getMediaPlayer();
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.e(TAG, "on Service Disconnected");
        }
    };

    public class MyBinder extends Binder {

        public MyMediaPlayer getMediaPlayer(){
            return MyMediaPlayer.this;
        }

    }
    private class MyMediaPlayerService extends Service{
        @Override
        public IBinder onBind(Intent intent) {
            return mBinder;
        }
        @Override
        public void onCreate() {
            super.onCreate();
        }
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }

        private void updateNotification(){
//            startForeground();
            Notification status = new Notification.Builder(this)
                    .setOngoing(true)
                    .setContentTitle("Guan")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setChannelId("MyMediaPlayer").build();
            startForeground(1,status);
        }
    }

    @Override
    protected void finalize() {
        super.finalize();
        Log.d("guantbb", "finalize in MyMediaPlayer.... ");
    }
}
