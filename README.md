# PROJE-QR-OLUSTURMA-

ACTİVİTY_MAİN.XML

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:background="@drawable/arkaplan">
    <RelativeLayout
        android:id="@+id/resim"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_marginStart="0dp"
        android:layout_marginTop="0dp"
        tools:ignore="NotSibling">
        <LinearLayout
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:layout_alignParentStart="true"
            android:layout_alignParentTop="true"
            android:orientation="horizontal"/>
        <ImageView
            android:id="@+id/img1"
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:layout_alignLeft="@+id/resim"
            android:background="@drawable/logo"/>
        <TextView
            android:id="@+id/text"
            android:layout_width="210dp"
            android:layout_height="150dp"
            android:layout_alignParentTop="true"
            android:layout_alignParentRight="true"
            android:layout_marginTop="29dp"
            android:layout_marginRight="-1dp"
            android:gravity="center"
            android:text="KENDİ KODUN KENDİ TARZIN"
            android:textColor="#8C0E0E"
            android:textSize="25sp"
            android:textStyle="italic"
            android:background="@drawable/shape1"/>
    </RelativeLayout>
    <RelativeLayout
        android:id="@+id/panelim"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_marginTop="290dp"
        android:layout_marginLeft="55dp"
        android:layout_marginBottom="50dp"
        android:background="@drawable/sahpe2">
        <EditText
            android:id="@+id/edittextTextEmailAddress"
            android:layout_width="wrap_content"
            android:layout_height="48dp"
            android:ems="10"
            android:layout_alignParentTop="true"
            android:layout_alignParentLeft="true"
            android:layout_marginTop="40dp"
            android:layout_marginLeft="40dp"
            android:textSize="18sp"
            android:hint="*****@*****.com"
            android:inputType="textEmailAddress"/>
        <EditText
            android:id="@+id/edittextTextpassword"
            android:layout_width="wrap_content"
            android:layout_height="48dp"
            android:ems="10"
            android:layout_alignParentTop="true"
            android:layout_alignParentLeft="true"
            android:layout_marginTop="100dp"
            android:layout_marginLeft="40dp"
            android:textSize="18sp"
            android:hint="Şifrenizi giriniz"
            android:inputType="textPassword"/>
        <Button
            android:id="@+id/btnkayıt"
            android:layout_width="150dp"
            android:layout_height="50dp"
            android:layout_alignParentLeft="true"
            android:layout_alignParentTop="true"
            android:layout_marginLeft="65dp"
            android:layout_marginTop="160dp"
            android:backgroundTint="#C3B2B2"
            android:text="KAYIT"
            android:textColor="@color/black"
            android:textSize="20sp"/>
        <Button
            android:id="@+id/btngir"
            android:layout_width="150dp"
            android:layout_height="50dp"
            android:layout_alignParentLeft="true"
            android:layout_alignParentTop="true"
            android:layout_marginLeft="65dp"
            android:layout_marginTop="220dp"
            android:backgroundTint="#C3B2B2"
            android:text="GİRİŞ"
            android:textColor="@color/black"
            android:textSize="20sp"/>
    </RelativeLayout>
    <LinearLayout
        android:layout_width="300dp"
        android:layout_height="75dp"
        android:layout_alignParentLeft="true"
        android:layout_alignParentBottom="true"
        android:layout_marginLeft="60dp"
        android:layout_marginBottom="20dp"
        android:gravity="center"
        android:padding="5dp"
        android:background="@drawable/sahpe2">
        <ImageView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:background="@drawable/fc"/>
        <ImageView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:background="@drawable/twit"/>
        <ImageView
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:background="@drawable/ins"/>
    </LinearLayout>

</RelativeLayout>


MAİNACTİVİTY.JAVA

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



ACTİVİTY_MAİN2.XML
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity2"
    android:background="@drawable/shape2">
    
    <LinearLayout
        android:id="@+id/panel"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="10dp"
        android:layout_marginTop="10dp"
        android:layout_marginEnd="10dp"
        android:layout_marginBottom="10dp"
        android:background="@drawable/sahpe3"
        android:gravity="center"
        android:orientation="vertical"
        android:paddingTop="25dp"
        android:paddingBottom="25dp">
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Barkod Oluşturma"
            android:textColor="#5E35B1"
            android:textSize="35sp"
            android:textStyle="bold"/>
    </LinearLayout>
    
    <EditText
        android:id="@+id/txt"
        android:layout_width="match_parent"
        android:layout_height="48dp"
        android:layout_marginTop="110dp"
        tools:ignore="SpeakableTextPresentCheck"/>
    
    <Button
        android:id="@+id/btnolus"
        android:layout_width="170dp"
        android:layout_height="wrap_content"
        android:layout_below="@+id/panel"
        android:layout_marginTop="50dp"
        android:layout_marginLeft="100dp"
        android:layout_alignParentStart="true"
        android:layout_alignParentEnd="true"
        android:layout_marginStart="113dp"
        android:layout_marginEnd="107dp"
        android:text="QR OLUŞTUR"
        android:textSize="20sp"
        android:textColor="#E53935"
        android:background="@drawable/myrip"/>
    <Button
        android:id="@+id/btnkaydet"
        android:layout_width="170dp"
        android:layout_height="wrap_content"
        android:layout_below="@+id/panel"
        android:layout_marginTop="110dp"
        android:layout_marginLeft="100dp"
        android:layout_alignParentStart="true"
        android:layout_alignParentEnd="true"
        android:layout_marginStart="113dp"
        android:layout_marginEnd="107dp"
        android:text="QR KAYDET"
        android:textSize="20sp"
        android:textColor="#E53935"
        android:background="@drawable/myrip1"/>
    
    <ImageView
        android:id="@+id/qr_code"
        android:layout_width="match_parent"
        android:layout_height="569dp"
        android:layout_below="@+id/btnolus"
        android:layout_marginTop="11dp"
        android:scaleType="fitCenter"/>

</RelativeLayout>

MAİNACTİVİTY2.JAVA

package com.example.fnalodev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    EditText editText;
    Button btnolus,btnkaydet;
    ImageView img;
    HashMap<String,Object> mdata;
    FirebaseFirestore myFstore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText=findViewById(R.id.txt);
        btnolus=findViewById(R.id.btnolus);
        btnkaydet=findViewById(R.id.btnkaydet);
        img=findViewById(R.id.qr_code);

        btnolus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiFormatWriter multiFormatWriter= new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix= multiFormatWriter.encode(editText.getText().toString(), BarcodeFormat.QR_CODE,300,300);
                    BarcodeEncoder barcodeEncoder= new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    img.setImageBitmap(bitmap);
                }catch (WriterException e){
                    throw new RuntimeException(e);
                }
            }
        });

        btnkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qr_adı=editText.getText().toString();
                String qr=img.toString();
                mdata= new HashMap<>();
                mdata.put("QR ADI: ",qr_adı);
                mdata.put("BARKOD: ",qr);
                myFstore.collection("BARKODLAR").document("Barkod")
                        .set(mdata)
                        .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(MainActivity2.this,"Başarıyla kaydedildi",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}
