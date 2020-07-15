package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button reset, increment;
    private TextView number;
    private long compteur = 0;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = findViewById(R.id.reset);
        increment = findViewById(R.id.increment);
        number = findViewById(R.id.number);
        sp = getSharedPreferences("MyUserPrefs", MODE_PRIVATE);
        loadResult();
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compteur++;
                number.setText(String.valueOf(compteur));
                saveResult();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compteur = 0;
                number.setText(String.valueOf(compteur));
                saveResult();
            }
        });
    }


    public void saveResult() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("result", number.getText().toString());
        editor.commit();

    }

    public void loadResult() {
        if (sp.contains("result")) {
            compteur = Integer.parseInt(sp.getString("result", ""));
            number.setText(String.valueOf(compteur));
        }
    }
}