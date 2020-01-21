package com.example.bar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.bar.R;

public class SMS extends AppCompatActivity {


    private static final int MY_PERMISSION_REQUEST_RECEIVER_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        /**
         * VERIFICA SE A PERMISSÃO NÃO FOI CONCEDIDA
         */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
        }

        /**
         * SE A PERMISSÃO NÃO FOI CONCEDIDA, VERIFICA SE O USUÁRIO NEGOU A PERMISSÃO
         */
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS))
        {
            /**
             * NÃO FAZ NADA CASO O USUÁRIO NEGUE A PERMISSÃO
             */
        }
        else
        {
            /**
             * UM POP-UP APARECERÁ PARA PEDIR A PERMISSÃO NECESSÁRIA OU PERMITIR A NEGAÇÃO
             */
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSION_REQUEST_RECEIVER_SMS);
        }
        /**
         * APÓS OBTER O RESULTADO DA SOLICITAÇÃO DE PERMISSÃO, O RESULTADO SERÁ APROVADO POR ESTE MÉTODO
         */
        @Override
        public void onRequesPermissionResult(int requestCode, String permissions[])
    }
}














































































































































































/**
 * Aqui na Classe -SMS- Nos Vamos Escrever o Código Para Pedir Permissão
 */