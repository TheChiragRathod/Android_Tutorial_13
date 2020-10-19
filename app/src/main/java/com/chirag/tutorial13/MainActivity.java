package com.chirag.tutorial13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    EditText editTextMessage;

    protected static final int REQUESTCODE_CALL=1;
    protected static final int REQUESTCODE_SMS=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnCall(View view)
    {

    }

    public void btnMessage(View view)
    {

    }
}