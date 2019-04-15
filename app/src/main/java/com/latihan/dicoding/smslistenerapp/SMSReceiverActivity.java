package com.latihan.dicoding.smslistenerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SMSReceiverActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvSMSFrom;
    TextView tvSMSMessage;
    Button btnClose;

    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE = "extra_sms_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        setTitle("Incoming Message");

        tvSMSFrom = findViewById(R.id.tv_no);
        tvSMSMessage = findViewById(R.id.tv_message);
        btnClose = findViewById(R.id.btn_close);

        btnClose.setOnClickListener(this);

        String senderNo = getIntent().getStringExtra(EXTRA_SMS_NO);
        String senderMessage = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);

        tvSMSFrom.setText(String.format("from : %s", senderNo));
        tvSMSMessage.setText(senderMessage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_close) {
            finish();
        }
    }
}
