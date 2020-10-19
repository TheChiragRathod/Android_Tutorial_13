package com.chirag.tutorial13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber;
    EditText editTextMessage;

    protected static final int REQUESTCODE_CALL=1;
    protected static final int REQUESTCODE_SMS=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumber=findViewById(R.id.edtNumber);
        editTextMessage=findViewById(R.id.edtMessage);

    }

    public void btnCall(View view)
    {
        if(editTextNumber.getText().toString().isEmpty()) {
            editTextNumber.requestFocus();
            editTextNumber.setError("Please enter mobile number!");
            return;
        }

        if(isCallPermissionAllowed())
        {
            doCall();
        }
    }

    public void btnMessage(View view)
    {
        boolean result=true;
        if(editTextMessage.getText().toString().isEmpty()) {
            editTextMessage.requestFocus();
            editTextMessage.setError("Please enter message!");
            result=false;

        }
        if(editTextNumber.getText().toString().isEmpty()) {
            editTextNumber.requestFocus();
            editTextNumber.setError("Please enter mobile number!");
            result=false;
        }

        if(result==false)
            return;
        if(isSMSPermissionAllowed())
        {
            doMessage();
        }

    }


    private boolean isCallPermissionAllowed()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(checkSelfPermission(Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},REQUESTCODE_CALL);
                return false;
            }
        }
        else
        {
            //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    private boolean isSMSPermissionAllowed()
    {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},REQUESTCODE_SMS);
                return false;
            }
        }
        else
        {
            //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
    protected void doCall()
    {
        try
        {
            String number = editTextNumber.getText().toString();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }
        catch (Exception objExc)
        {
            Toast.makeText(this, objExc.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    protected void doMessage()
    {
        try
        {
            String number = editTextNumber.getText().toString();
            String Message=editTextMessage.getText().toString();
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,Message,null,null);
            Toast.makeText(this, "SMS Send Successfully", Toast.LENGTH_SHORT).show();

        }
        catch (Exception objExc)
        {
            Toast.makeText(this, objExc.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}