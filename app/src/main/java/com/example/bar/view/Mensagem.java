package com.example.bar.view;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.bar.R;


public class Mensagem extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button enviarBtt;
    EditText txtnCelular;
    EditText txtMensagem;
    String phoneNo;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);

        enviarBtt= (Button) findViewById(R.id.bttEnviar);
        txtnCelular = (EditText) findViewById(R.id.etNumero);
        txtMensagem = (EditText) findViewById(R.id.etSMS);

        enviarBtt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                sendSMSMensagem();
            }
        });
    }

    protected void sendSMSMensagem() {

        phoneNo = txtnCelular.getText().toString();
        message = txtMensagem.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Enviado!.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS Falhou... Tente Novamente!", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}
