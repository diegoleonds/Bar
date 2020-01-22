package com.example.bar.Alan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Celular.SMS.RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";
    String msg, NCelular = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        /**
         * Recupera a Ação Geral a Ser Executada e Exibida No Log
         * ------
         * Retrieves The General Action To Be Perfomed And Display On Log
         */
        Log.i(TAG, "Intent Received" +intent.getAction());
        if (intent.getAction()==SMS_RECEIVED)
        {

            /**
             * Recupera Um Mapa De Dados Estendidos Da Intenção
             * ------
             * Retrieves a Map Of Extended Data From The Intent
             */
            Bundle dataBundle = intent.getExtras();
            if (dataBundle!=null)
            {

                /**
                 * Criando o Objeto PDU (Protocol Data Unit) Que é Um Protocolo Para Transferência De Mensagens
                 * ---------
                 * Creating PDU(Protocol Data Unit) Object Which Is a Protocol For Tranfering Message
                 */
                Object[] pdus = (Object[])dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[pdus.length];
                for (int i = 0; i<pdus.length; i++)
                {

                    /**
                     * Para Versões De Compilação> = Nível 23 Da API
                     * ------
                     * For Build Versions >= API Level 23
                     */
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        String formato = dataBundle.getString("formato");

                        /**
                         * Da PDU, Obtemos Todos os Objetos e SmsMessage Object Usando a Seguinte Linha De Código
                         * ------
                         * From PDU We Get All Object And SmsMessage Object Using Following Line Of Code
                         */
                        message[i] = SmsMessage.createFromPdu((byte[])pdus[i], formato);
                    }
                    else
                    {

                        /**
                         * <API level 23
                         */
                        message[i] = SmsMessage.createFromPdu((byte[]) pdus [i]);
                    }
                    msg = message[i].getMessageBody();
                    NCelular = message[i].getOriginatingAddress();
                }
                Toast.makeText(context,"Mensagem: Oi " +msg +"\nNumero: +5547996684724 " +NCelular, Toast.LENGTH_LONG).show();

            }
        }
    }
}
