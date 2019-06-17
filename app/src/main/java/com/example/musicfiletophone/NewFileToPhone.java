package com.example.musicfiletophone;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewFileToPhone {


    public static String TAG = "NewFileToPhone";

    public void newFileToMusic(){

        File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.d(TAG, "newFileToMusic: music path == "+musicDir.getAbsolutePath());

        File mFile = new File(musicDir.getAbsolutePath()+"/"+"temp.mp3");

        if (!mFile.exists()){
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(mFile);
            fileOutputStream.write("guan".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

            Log.e(TAG, "Successful");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to write file");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to write file");
        }

    }
}
