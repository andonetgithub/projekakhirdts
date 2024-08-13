package com.example.projekakhirdts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    Button blogin;
    TextView baccount;
    EditText loguser, logpass;
    DatabaseHelper dblogin;
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ini adalah perintah memanggil button di View
        loguser = (EditText) findViewById(R.id.loginusername);
        logpass = (EditText) findViewById(R.id.loginpassword);
        blogin = (Button) findViewById(R.id.btnlogin);
        baccount = (TextView) findViewById(R.id.btnaccount);
        dblogin = new DatabaseHelper(this);
        
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suser = loguser.getText().toString();
                String spassword = logpass.getText().toString();
                Boolean checkUserPassword = dblogin.checkUserPassword(suser, spassword);
                if (checkUserPassword) {
                    Toast.makeText(getApplicationContext(), "LoginSuccessfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        //ini adalah perintah click untuk button untuk beralih ke register
        baccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}