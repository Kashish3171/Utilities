package com.example.kashishgoyal.utilitytools;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class QR_Code_Scanner extends AppCompatActivity implements View.OnClickListener,ZXingScannerView.ResultHandler{
    private Button qrcode;
    private Button back;
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_qr__code__scanner);
        qrcode = (Button)findViewById(R.id.qrcode);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);

        qrcode.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==back)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        if(view==qrcode)
        {
            mScannerView=new ZXingScannerView(this);
            setContentView(mScannerView);
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.v("handle result",result.getText());
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

      //  mScannerView.resumeCameraPreview(this);


    }
}
