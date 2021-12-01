package com.example.vaishali_tatsat_comp304sec003_lab4_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnLoginClick(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void btnRegisterClick(View view)
    {
        Intent intent = new Intent(this, RegisterNurseActivity.class);
        startActivity(intent);
    }
}