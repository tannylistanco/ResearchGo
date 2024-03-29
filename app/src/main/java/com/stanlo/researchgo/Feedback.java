package com.stanlo.researchgo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

public class Feedback extends AppCompatActivity {

    //feedback
    private TextInputEditText eTo;
    private TextInputEditText eSubject;
    private TextInputEditText eMsg;
    private FloatingActionButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        //actionbar
        getSupportActionBar().setTitle("Feedback");

        if(!isConnected(Feedback.this)) buildDialog(Feedback.this).show();
        else {

        }

        //feedback
        setContentView(R.layout.activity_feedback);
        eTo = findViewById(R.id.txtTo);

        eSubject = findViewById(R.id.txtSub);
        eMsg = findViewById(R.id.txtMsg);
        btn = findViewById(R.id.btnSend);
        eTo.setText ( "tannyencina@gmail.com" );
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{eTo.getText().toString()});
                it.putExtra(Intent.EXTRA_SUBJECT,eSubject.getText().toString());
                it.putExtra(Intent.EXTRA_TEXT,eMsg.getText());
                it.setType("message/rfc822");
                it.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(it,"Choose Mail App"));
            }
        });


    }




    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting ()) || (wifi != null && wifi.isConnectedOrConnecting ());
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
         builder.setIcon(R.drawable.ic_nosignal);
        builder.setTitle("No Internet Connection");
        builder.setMessage("To give feedback,connect to WiFi or Mobile Data");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

}
