package com.example.bmrcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;

public class MainActivity3 extends AppCompatActivity {

    Button save3;
    Button cancel3;
    TextView getname;
    String name;
    String o_name;
    TextView getbmi;
    double height;
    double o_height;
    double weight;
    double o_weight;
    TextView getbmr;
    int age;
    int o_age;
    String sex;
    String o_sex;
    String mode;
    DecimalFormat precision = new DecimalFormat("0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initViewElement();

        //get string
        Intent it = this.getIntent();
        if(it != null){
            Bundle bundle = it.getExtras();
            if(bundle != null){
                String inputStr = bundle.getString("name");
                if(inputStr != null && !inputStr.equals("")){
                    getname.setText(inputStr);
                    name = inputStr;
                }
                inputStr = bundle.getString("gender");
                if(inputStr != null && !inputStr.equals("")){
                    sex = inputStr;
                }
                inputStr = bundle.getString("height");
                if(inputStr != null && !inputStr.equals("")){
                    height = Double.parseDouble(inputStr);
                }
                inputStr = bundle.getString("weight");
                if(inputStr != null && !inputStr.equals("")){
                    weight = Double.parseDouble(inputStr);
                }
                inputStr = bundle.getString("age");
                if(inputStr != null && !inputStr.equals("")){
                    age = Integer.parseInt(inputStr);
                }

                if(bundle.getString("mode").equals("Modifying Record")){
                    inputStr = bundle.getString("o_name");
                    o_name = inputStr;
                    inputStr = bundle.getString("o_weight");
                    o_weight = Double.parseDouble(inputStr);
                    inputStr = bundle.getString("o_height");
                    o_height = Double.parseDouble(inputStr);
                    inputStr = bundle.getString("o_sex");
                    o_sex = inputStr;
                    inputStr = bundle.getString("o_age");
                    o_age = Integer.parseInt(inputStr);
                }

                getbmi.setText(Double.toString(Double.parseDouble(precision.format(bmi(height,weight)))));
                getbmr.setText(Double.toString(Double.parseDouble(precision.format(bmr(sex,height,weight,age)))));
                mode = bundle.getString("mode");
            }
        }

        save3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mode.equals("Modifying Record")){
                    UpdateRecord("Update");
                }
                else{
                    insertRecord("Data");
                }
                Intent it = new Intent();
                it.setClass(MainActivity3.this, MainActivity.class);
                startActivity(it);
            }
        });
        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initViewElement(){
        save3 = (Button) findViewById(R.id.save3);
        cancel3 = (Button) findViewById(R.id.cancel3);
        getname = (TextView) findViewById(R.id.getName);
        getbmi = (TextView) findViewById(R.id.getBMI);
        getbmr = (TextView) findViewById(R.id.getBMR);
    }
    //calculate BMI
    private double bmi(double h, double w){
        return w/((h/100.0)*(h/100.0));
    }
    //calculate BMR
    private double bmr(String sex, double h, double w, int age){
        //male
        if(sex.equals("male")){
            return 66+(13.7*w+5*h-6.8*age);
        }
        //female
        return 655+(9.6*w+1.8*h-4.7*age);
    }
    private synchronized void insertRecord(String var){
        Thread thread = new Thread(new Runnable() {
            String _var;
            @Override
            public void run() {
                executeHttpPost("http://10.0.2.2/android/GetData.php",_var);
            }
            public Runnable init(String var){
                _var = var;
                return this;
            }
        }.init(var));
        thread.start();
        while(thread.isAlive()){
            //waiting for internet thread
        }
    }
    private void executeHttpPost(String path, String var){
        try{
            URL urlObj = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset","UTF-8");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            //id=parameter
            String post = "pname="+name+"&page="+Integer.toString(age)+"&pweight="+Double.toString(weight)+"&pheight="+Double.toString(height)+"&psex="+sex;
            wr.writeBytes(post);
            //Log.d("output:",var);
            wr.flush();
            wr.close();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                result.append(line);
            }

            conn.disconnect();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private synchronized void UpdateRecord(String var){
        Thread thread = new Thread(new Runnable() {
            String _var;
            @Override
            public void run() {
                executeHttpPost2("http://10.0.2.2/android/UpdateData.php",_var);
            }
            public Runnable init(String var){
                _var = var;
                return this;
            }
        }.init(var));
        thread.start();
        while(thread.isAlive()){
            //waiting for internet thread
        }
    }
    private void executeHttpPost2(String path, String var){
        try{
            URL urlObj = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset","UTF-8");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            //id=parameter
            String post = "pname="+name+"&page="+Integer.toString(age)+"&pweight="+Double.toString(weight)+"&pheight="+Double.toString(height)+"&psex="+sex+"&oname="+o_name+"&oage="+Integer.toString(o_age)+"&oweight="+Double.toString(o_weight)+"&oheight="+Double.toString(o_height)+"&osex="+o_sex;
            wr.writeBytes(post);
            Log.d("update",post);
            //Log.d("output:",var);
            wr.flush();
            wr.close();

            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                result.append(line);
            }

            conn.disconnect();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}