package com.grupouno.josporttech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactanosActivity extends AppCompatActivity {
    EditText etMsj, etCel;


    Button btnW;
    FloatingActionButton btnMicro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);


        etMsj = findViewById(R.id.texto);
        etCel = findViewById(R.id.nro);
        //etCel.setText("987381215");
        etMsj.setText("Necesito mayor información acerca de mi reserva");

        btnW = findViewById(R.id.botonenviar);
        btnW.setOnClickListener(view -> {


            if (etCel.getText().toString().isEmpty()) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, etMsj.getText().toString());
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                startActivity(intent);
            }else {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);

                String uri = "whatsapp://send?phone="+etCel.getText().toString()+"&text="+etMsj.getText().toString();

                intent.setData(Uri.parse(uri));
                startActivity(intent);

            }
        });

        btnMicro = findViewById(R.id.btnMicro);
        btnMicro.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"es-MX");
            startActivityForResult(intent,200);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String voice = arrayList.get(0);
            etMsj.setText(voice);
        }else{
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
}