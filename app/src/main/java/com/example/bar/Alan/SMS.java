/**
 * Aqui na Classe -SMS- Nos Vamos Escrever o Código Para Pedir Permissão
 */

package com.example.bar.Alan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bar.R;

public class SMS extends AppCompatActivity {

    private static final String SMS_RECEIVED = "android.provider.Celular.SMS.RECEIVED";
    private static final int MY_PERMISSION_REQUEST_RECEIVER_SMS = 0;
    TextView tvMensagem, tvNumero;

    SMSReceiver receiver = new SMSReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            tvMensagem.setText(msg);
            tvNumero.setText(NCelular);
        }
    };

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(receiver, new IntentFilter(SMS.SMS_RECEIVED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        tvMensagem = findViewById(R.id.Mensagem);
        tvNumero = findViewById(R.id.Numero);

        //check if the permission is not granted
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            //if the permission is not been granted then check if the user has denied the permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS))
            {
                //do nothing as user has denied
            }
            else
            {
                //a pop up will apear asking for required permission i.e allow or deny
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSION_REQUEST_RECEIVER_SMS);
            }
        }
    }//onCreate
    //after getting the result of permission requests the result wil be passed through this method
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        //will check the requestCode
        switch(requestCode)
        {
            case MY_PERMISSION_REQUEST_RECEIVER_SMS:
            {
                //check whether the length of grantResults is greater than 0 and is equal to PERMISSION_GRANTED
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    //now broadcastreceiver will work in background
                    Toast.makeText(this, "Obrigado Por Permitir!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"Bem, não posso fazer nada até que você me permita", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}













































































































































































