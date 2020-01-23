package com.example.bar.view;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FetchAddressService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    protected ResultReceiver receiver;

    public FetchAddressService(String name) {
       super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
        receiver = intent.getParcelableExtra(Constants.RECEIVER);

        List<Address> addresses = null;

        try {
            geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
        } catch (IOException e) {
            Log.e("Teste","serviço indisponivel",e);
        } catch (IllegalArgumentException e){
            Log.e("Teste","latitude ou longitude inválida",e);
        }
        if (addresses == null || addresses.isEmpty()){
            Log.e("Teste","nenhum endereço encontrado");
            deliverResultToReceiver(Constants.FAILURE_RESULT,"nenhum endereço encontrado");
        }else{
            Address address = addresses.get(0);
            List<String> addressF= new ArrayList<>();
            for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                addressF.add(address.getAddressLine(i));
            }
            deliverResultToReceiver(Constants.SUCCESS_RESULT, TextUtils.join("|",addressF));
        }
    }

    private void deliverResultToReceiver(int resultCode,String message){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY,message);
        receiver.send(resultCode,bundle);
    }
}

