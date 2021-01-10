package com.example.tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private Button reset, increment, reinit;
    private TextView number, date;
    private long compteur = 0;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = findViewById(R.id.reset);
        reinit= findViewById(R.id.initdate);
        increment = findViewById(R.id.increment);
        number = findViewById(R.id.number);
        date = findViewById((R.id.date));
        sp = getSharedPreferences("MyUserPrefs", MODE_PRIVATE);
        loadDate();
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
        reinit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.setText(getCurrentDate());
                saveDate();
            }
        });
    }

    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void saveDate() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("date", date.getText().toString());
        editor.commit();
    }

    public void loadDate() {
        if (sp.contains("date")) {
            date.setText(sp.getString("date", getCurrentDate()));
        }

    }

    public void saveResult() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("result", number.getText().toString());
        editor.commit();
    }

    public void loadResult() {
        if (sp.contains("result")) {
            compteur = Integer.parseInt(sp.getString("result", "0"));
            number.setText(String.valueOf(compteur));
        }
    }

}
