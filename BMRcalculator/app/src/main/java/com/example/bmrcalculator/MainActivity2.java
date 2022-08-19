package com.example.bmrcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Button cancel2;
    Button send2;
    TextView creatingormodifying;
    EditText inputname;
    RadioButton female;
    RadioButton male;
    RadioGroup inputgender;
    String o_sex;
    String sex;
    String o_name;
    String o_weight;
    String o_height;
    String o_age;
    String mode;
    EditText inputage;
    EditText inputheight;
    EditText inputweight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //initialization
        initViewElement();

        //get title string
        Intent it = this.getIntent();
        if(it != null){
            Bundle bundle = it.getExtras();
            if(bundle != null){
                String inputStr = bundle.getString("title");
                if(inputStr != null && !inputStr.equals("")){
                    creatingormodifying.setText(inputStr);
                    mode = inputStr;
                    if(inputStr.equals("Modifying Record")){
                        inputname.setText(bundle.getString("name"));
                        inputweight.setText(bundle.getString("weight"));
                        inputheight.setText(bundle.getString("height"));
                        inputage.setText(bundle.getString("age"));

                        o_name = bundle.getString("name");
                        o_weight = bundle.getString("weight");
                        o_height = bundle.getString("height");
                        o_age = bundle.getString("age");
                        sex = bundle.getString("sex");

                        if(bundle.getString("sex").equals("male")){
                            male.setChecked(true);
                            o_sex = bundle.getString("sex");
                        }
                        else{
                            female.setChecked(true);
                            o_sex = bundle.getString("sex");
                        }
                    }
                }
            }
        }

        //radiobutton gender
        inputgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton gender = (RadioButton) findViewById(i);
                sex = gender.getText().toString();
            }
        });
        //button send
        send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("mode",mode);
                bundle.putString("o_name",o_name);
                bundle.putString("o_weight",o_weight);
                bundle.putString("o_height",o_height);
                bundle.putString("o_age",o_age);
                bundle.putString("o_sex",o_sex);
                bundle.putString("name",inputname.getText().toString());
                bundle.putString("age",inputage.getText().toString());
                bundle.putString("height",inputheight.getText().toString());
                bundle.putString("weight",inputweight.getText().toString());
                bundle.putString("gender",sex);
                Intent it = new Intent();
                it.putExtras(bundle);
                it.setClass(MainActivity2.this, MainActivity3.class);
                startActivity(it);
            }
        });

        //button cancel
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initViewElement(){
        cancel2 = (Button) findViewById(R.id.cancel2);
        send2 = (Button) findViewById(R.id.send2);
        creatingormodifying = (TextView) findViewById(R.id.creatingormodifying);
        inputname = (EditText) findViewById(R.id.inputname);
        inputage = (EditText) findViewById(R.id.inputage);
        inputheight = (EditText) findViewById(R.id.inputheight);
        inputweight = (EditText) findViewById(R.id.inputweight);
        female = (RadioButton) findViewById(R.id.female);
        male = (RadioButton) findViewById(R.id.male);
        inputgender = (RadioGroup) findViewById(R.id.inputgender);
    }
}