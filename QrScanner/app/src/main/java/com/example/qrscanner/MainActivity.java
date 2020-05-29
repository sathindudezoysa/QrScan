package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnScanBarcode;
    String str;
    List<String> list;
    List<Bitmap> photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        imageView = findViewById(R.id.image);
        photo  = new ArrayList<Bitmap>();
        list =  new ArrayList<String>();
        list.add("Dog");
        list.add("Cat");
        list.add("Kasun");
        list.add("Sathindu");
        list.add("Shanika");
        /*
        try {
            bytemap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        */

        qr();


        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ScannedBarcodeActivity.class));
            }
        });
    }


    public void qr(){


        for (int i = 0; i<list.size();i++) {
            String text = list.get(i);
            Bitmap bitmap = QRCodeHelper
                    .newInstance(this)
                    .setContent(text)
                    .setErrorCorrectionLevel(ErrorCorrectionLevel.Q)
                    .setMargin(2)
                    .getQRCOde();
            photo.add(bitmap);

            //setimage();



        }
    }
    public void setimage() {

        for (int x = 0; x <photo.size();x++) {
            try {
                imageView.setImageBitmap(photo.get(x));
                Thread.sleep(1000); // m is millisecond, if m = 1000 ms then delay = 1 s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void bytemap() throws UnsupportedEncodingException {

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        byte[] byteArray = stream.toByteArray();
        str = new String(byteArray,"UTF-8");
        //textView.setText(str);

    }


}
