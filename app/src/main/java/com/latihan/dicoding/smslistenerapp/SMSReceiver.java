package com.latihan.dicoding.smslistenerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

    final String TAG = SMSReceiver.class.getSimpleName();

    public SMSReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                if (pdusObj != null) {
                    for (Object aPdusObj : pdusObj) {
                        SmsMessage currentMessage = getIncomingMessage(aPdusObj, bundle);
                        String senderNum = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getDisplayMessageBody();
                        Log.d(TAG, "senderNum: " + senderNum + "; message: " + message);
                        Intent showSMSIntent = new Intent(context, SMSReceiverActivity.class);
                        showSMSIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        showSMSIntent.putExtra(SMSReceiverActivity.EXTRA_SMS_NO, senderNum);
                        showSMSIntent.putExtra(SMSReceiverActivity.EXTRA_SMS_MESSAGE, message);
                        context.startActivity(showSMSIntent);
                    }
                } else {
                    Log.d(TAG, "onReceive: SMS is null");
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception smsReceiver" + e);
        }

    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= 23) {
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }
}
