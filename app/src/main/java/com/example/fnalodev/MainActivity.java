package com.example.fnalodev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText txtmail,txtpassword;
    String mail,sifre;
    ImageView img1;
    RelativeLayout panelim;
    Button btngiris,btnkayit;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseUser mUser;
    HashMap<String, Object> mData;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtmail=findViewById(R.id.edittextTextEmailAddress);
        txtpassword=findViewById(R.id.edittextTextpassword);
        img1=findViewById(R.id.img1);
        panelim=findViewById(R.id.panelim);
        btngiris=findViewById(R.id.btngir);
        btnkayit=findViewById(R.id.btnkayıt);
        mAuth=FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference();

        Animation anim1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo);
        Animation anim2= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.panel);
        img1.startAnimation(anim1);
        panelim.startAnimation(anim2);

        btnkayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = txtmail.getText().toString();
                sifre = txtpassword.getText().toString();
                if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(sifre)){
                    mAuth.createUserWithEmailAndPassword(mail,sifre)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(MainActivity.this, "Firebase kayıt yapıldı", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        btngiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = txtmail.getText().toString();
                sifre = txtpassword.getText().toString();
                if (!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(sifre)){
                    mAuth.signInWithEmailAndPassword(mail,sifre)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    mUser=mAuth.getCurrentUser();
                                    mData= new HashMap<>();
                                    mData.put("Kullanıcı E-maili: ",mail);
                                    mData.put("Kullanıcı Şifresi: ",sifre);
                                    mData.put("Kullanıcı ID: ",mUser.getUid());
                                    mRef.child("Kullanıcılar").child(mUser.getUid())
                                            .setValue(mData)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(MainActivity.this,"Veri tabanına kayıt edildi",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                    Toast.makeText(MainActivity.this, "Giriş yapıldı", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });
    }
}