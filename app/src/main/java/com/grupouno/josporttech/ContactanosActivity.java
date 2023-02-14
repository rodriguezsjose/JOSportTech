package com.grupouno.josporttech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ContactanosActivity extends AppCompatActivity {
    EditText etMsj, etCel;


    Button btnW;
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



    }
}