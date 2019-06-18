package com.example.musicfiletophone;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NewFileToPhone {

    private  Context mContext;
    NewFileToPhone(Context context){

        mContext = context;
    }

    public static String TAG = "NewFileToPhone";

    public void newFileToMusic(){

        File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.d(TAG, "newFileToMusic: music path == "+musicDir.getAbsolutePath());

        File mFile = new File(musicDir.getAbsolutePath()+"/"+"chenzao.mp3");

        if (!mFile.exists()){
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream inputStream = mContext.getResources().openRawResource(R.raw.chenzao);


        int length =-1;
        try {
             length = inputStream.available();

             if(length <0) return;

        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream =null;
        try {

            fileOutputStream = new FileOutputStream(mFile);
            int readLength =-1;
            byte[] temp = new byte[1024];
            while ((readLength = inputStream.read(temp))>0) {
                fileOutputStream.write(temp, 0, readLength);
            }
            fileOutputStream.flush();
            Log.e(TAG, "Successful");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to write file");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to write file");
        }finally {
            try {
                if(inputStream != null)inputStream.close();
                if(fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Close Exception");
            }

        }

    }
}
