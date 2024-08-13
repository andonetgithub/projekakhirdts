package com.example.projekakhirdts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    Button btnreg;
    EditText adduser,addpass;
    DatabaseHelper dblogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnreg = findViewById(R.id.btnregister);
        adduser = findViewById(R.id.registerusername);
        addpass = findViewById(R.id.registerpassword);
        dblogin = new DatabaseHelper(this);

       btnreg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String user = adduser.getText().toString();
               String password = addpass.getText().toString();
               Boolean checkUser = dblogin.checkUser(user);
               if(checkUser == false) {
                   Boolean insert = dblogin.insertUser(user, password);
                   if(insert == true) {
                       Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                       finish();
                   } else {
                       Toast.makeText(getApplicationContext(), "Registered Failed", Toast.LENGTH_SHORT).show();
                   }
               } else {
                   Toast.makeText(getApplicationContext(), "User Already Exit", Toast.LENGTH_SHORT).show();
               }


           }
       });

    }

}