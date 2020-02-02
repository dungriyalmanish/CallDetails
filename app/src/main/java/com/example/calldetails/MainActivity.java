package com.example.calldetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch listenerSwitch;
    BroadcastReceiver mBR = null;
    IntentFilter filters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listenerSwitch = findViewById(R.id._call_switch);
        listenerSwitch.setOnCheckedChangeListener(this);
        mBR = new PhoneDetailsListener();
        filters = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBR == null) {
            mBR = new PhoneDetailsListener();
        }
        if (listenerSwitch.isChecked()) {
            registerReceiver(mBR, filters);
        } else {
            unregisterReceiver(mBR);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            registerReceiver(mBR, filters);
        } else {
            unregisterReceiver(mBR);
        }
    }


    private class PhoneDetailsListener extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            StringBuffer sb = new StringBuffer();
            String callNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(!callNumber.isEmpty()){
                sb.append("\nCall from: "+callNumber);
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {

            } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {

            }

        }
    }
}
