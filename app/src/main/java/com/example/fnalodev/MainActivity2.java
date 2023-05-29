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