package com.example.a14553.localdocument;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText edt;
    String path;
    //String path = "/storage/emulated/0/Android/data/com.example.a14553.localdocument/files/exter_test/aaaTest";
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = (EditText)findViewById(R.id.editText);
        path =getExternalFilesDir("exter_test").getPath();
        fileName = "test.txt";
    }
    public void onClick(View view){
        newDirectory(path,"Test2");
        //check();
        //newFile(path,fileName);
        //edt.setText(readFile(path,fileName));
        // save(edt.getText().toString());
    }
    public void save(String inputText){
            String data = "data to save";
            FileOutputStream out = null;
            BufferedWriter writer = null;
        try{
            //创建其他程序不可见的文件
            //out = openFileOutput(path+"/"+fileName, Context.MODE_PRIVATE);
            //打开本地文件
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"/"+fileName)));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null)
                    writer.close();
            }catch (IOException e){
                //Toast.makeText(this,"Wrong1",Toast.LENGTH_LONG).show();
            }
        }

    }

    public void check(){
        String res = "";
        Log.i("codecraeer", "getFilesDir = "+getFilesDir());
        res+="getFilesDir = "+getFilesDir()+"\n";
        Log.i("codecraeer", "getExternalFilesDir = "+getExternalFilesDir("exter_test").getAbsolutePath());
        res+="getExternalFilesDir = "+getExternalFilesDir("exter_test").getAbsolutePath()+"\n";
        Log.i("codecraeer", "getDownloadCacheDirectory = " + Environment.getDownloadCacheDirectory().getAbsolutePath());
        res+="getDownloadCacheDirectory = "+Environment.getDownloadCacheDirectory().getAbsolutePath()+"\n";
        Log.i("codecraeer", "getDataDirectory = " + Environment.getDataDirectory().getAbsolutePath());
        res+="getDataDirectory = "+Environment.getDataDirectory().getAbsolutePath()+"\n";
        Log.i("codecraeer", "getExternalStorageDirectory = " + Environment.getExternalStorageDirectory().getAbsolutePath());
        res+="getExternalStorageDirectory = "+Environment.getExternalStorageDirectory().getAbsolutePath()+"\n";
        Log.i("codecraeer", "getExternalStoragePublicDirectory = " + Environment.getExternalStoragePublicDirectory("pub_test"));
        res+="getExternalStoragePublicDirectory = "+Environment.getExternalStoragePublicDirectory("pub_test")+"\n";
        edt.setText(res);
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state))
        {
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            //We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else{
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }

    }
    public void newDirectory(String _path,String dirName){
        File file = new File(_path+"/"+dirName);
        try {
            if (!file.exists()) {
                file.mkdir();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void newFile(String _path,String _fileName){
        File file=new File(_path+"/"+_fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readFile(String _path,String _fileName){
        String res = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(_path+"/"+_fileName)));
            String line = "";
            while ((line = reader.readLine())!=null){
                res+=line;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }
}
