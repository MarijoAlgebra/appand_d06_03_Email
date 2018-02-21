package org.bubulescu.email;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etAdresa, etSubjekt, etPoruka;
    private Button btnSalji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();

        setupListeners();
    }

    private void initWidgets() {
        btnSalji = findViewById(R.id.btnSalji);
        etAdresa = findViewById(R.id.etAdresa);
        etSubjekt = findViewById(R.id.etSubjekt);
        etPoruka = findViewById(R.id.etTijelo);
    }

    private void setupListeners() {
        btnSalji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputOK()) {
                    sendEmail(etAdresa.getText().toString(), etSubjekt.getText().toString(), etPoruka.getText().toString());
                }
            }
        });
    }

    private boolean inputOK() {
        if (etAdresa.getText().length() == 0 ) {
            Toast.makeText(this, "Unesite adresu", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etSubjekt.getText().length() == 0) {
            Toast.makeText(this, "Unesite subjekt poruke", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etPoruka.getText().length() == 0) {
            Toast.makeText(this, "Unesite poruku.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void sendEmail(String adresa, String subjekt, String poruka) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{adresa});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjekt);
        emailIntent.putExtra(Intent.EXTRA_TEXT, poruka);
        startActivity(Intent.createChooser(emailIntent, "Odaberite aplikaciju"));
    }
}
