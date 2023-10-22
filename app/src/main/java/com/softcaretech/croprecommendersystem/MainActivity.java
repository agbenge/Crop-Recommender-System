package com.softcaretech.croprecommendersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.softcaretech.croprecommendersystem.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    double  N,	P,	K,	temperature,	humidity,	ph,	rainfally;
  String TAG ="softcare";
    private ExecutorService executor;

    private Handler handler;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        resultTextView = binding.textView;
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }


        findViewById(R.id.bnt_predict).setOnClickListener((v)->{
            predict();

        });

    }








    private void predict() {

        resultTextView.setText("Please wait...");
        String nitrogenValue = binding.etNitrogen.getEditText().getText().toString();
        String phosphorusValue = binding.etPhosphorus.getEditText().getText().toString();
        String potassiumValue = binding.etPotassium.getEditText().getText().toString();
        String temperatureValue = binding.etTemperature.getEditText().getText().toString();
        String humidityValue = binding.etHumidity.getEditText().getText().toString();
        String phValue = binding.etPh.getEditText().getText().toString();
        String rainfallValue = binding.etRainfall.getEditText().getText().toString();
        //String selectedItem = binding.spinnerAlgorthim.getSelectedItem().toString();

        if(nitrogenValue.isEmpty()){
            Util.setErrorNotification(binding.etNitrogen);
            return;
        }
        Util.clearErrorNotification(binding.etNitrogen);

        if(phosphorusValue.isEmpty()){
            Util.setErrorNotification(binding.etPhosphorus);
            return;
        }
        Util.clearErrorNotification(binding.etPhosphorus);

        if(potassiumValue.isEmpty()){
            Util.setErrorNotification(binding.etPotassium);
            return;
        }
        Util.clearErrorNotification(binding.etPotassium);

        if(temperatureValue.isEmpty()){
            Util.setErrorNotification(binding.etTemperature);
            return;
        }
        Util.clearErrorNotification(binding.etTemperature);

        if(humidityValue.isEmpty()){
            Util.setErrorNotification(binding.etHumidity);
            return;
        }
        Util.clearErrorNotification(binding.etHumidity);

        if(phValue.isEmpty()){
            Util.setErrorNotification(binding.etPh);
            return;
        }
        Util.clearErrorNotification(binding.etPh);

        if(rainfallValue.isEmpty()){
            Util.setErrorNotification(binding.etRainfall);
            return;
        }
        Util.clearErrorNotification(binding.etRainfall);
/*
        if(selectedItem.equals(" ")){
            Util.setErrorNotificationForSpinner(binding.spinnerAlgorthim, binding.spinnerTextInputLayout);
            return;
        }
        Util.clearErrorNotificationForSpinner(binding.spinnerAlgorthim, binding.spinnerTextInputLayout);
*/
//        Toast.makeText(this, "No blank fields", Toast.LENGTH_SHORT).show();
     //   Toast.makeText(this, "Selected item: " + selectedItem, Toast.LENGTH_SHORT).show();
         try {
             N= Double.parseDouble(nitrogenValue);
             P= Double.parseDouble(phosphorusValue);
             K= Double.parseDouble(potassiumValue);
            temperature = Double.parseDouble(temperatureValue);
             humidity= Double.parseDouble(humidityValue);
             ph= Double.parseDouble( phValue);
             rainfally= Double.parseDouble(rainfallValue);
             executor.execute(() -> {
                 //Background work here
                 Python python = Python.getInstance();
                 PyObject module = python.getModule("testpy");
                 // PyObject predictionDT =module.callAttr("predictDT",   N,	P,	K,	temperature,	humidity,	ph,	rainfally);
                 PyObject predictionRF  =module.callAttr("predictRF",   N,	P,	K,	temperature,	humidity,	ph,	rainfally);
                 String display = "Recommended Result: " +predictionRF.toString();
                 handler.post(() -> {
                     //UI Thread work here
                     resultTextView.setText(display);
                 });
             });
         }catch (Exception e) {
             resultTextView.setText("");
          Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show();
          e.printStackTrace();
         }

//        if(selectedItem.equals("Select an algorithm")){
//            return;
//        }else{
//
//        }
    }




}