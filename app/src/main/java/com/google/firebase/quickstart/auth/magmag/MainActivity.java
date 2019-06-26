package com.google.firebase.quickstart.auth.magmag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.quickstart.auth.magmag.Model.Users;
import com.google.firebase.quickstart.auth.magmag.Prevalent.Prevalent;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {

    // déclaration des boutons de la page d'accueil
    private Button joinNowbutton, loginButton;
  private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        joinNowbutton = (Button) findViewById(R.id.main_join_now_btn);
        loginButton = (Button) findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        joinNowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

                if(UserPhoneKey != null && UserPasswordKey != null )
                {
                  if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey))
                  {
                       AllowAccess(UserPhoneKey, UserPasswordKey);
                      loadingBar.setTitle("Alreasy Logged in");
                      loadingBar.setMessage("Please wait....");
                      loadingBar.setCanceledOnTouchOutside(false);
                      loadingBar.show();

                  }
                }
    }

    private void AllowAccess(final String phone, final String password) {



        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("onDataChange", "On est passé par la");
                if (dataSnapshot.child("Users").child(phone).exists()) {
                    Log.v("Users", "On est passé par la aussi");
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone)) {
                        Log.v("usersData.getPhone", "On est passé par la aussi 3 ");
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        Prevalent.currentOnlineUser = usersData;
                        startActivity(intent);
                    } else {
                        Log.v("usersData.getPhone", "On est passé par la aussi 4 ");
                        loadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {

            }
        });
    }
}