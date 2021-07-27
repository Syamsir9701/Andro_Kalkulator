package com.juaracoding.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    private int[] angka = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9};
    private int[] operator = {R.id.btnKali, R.id.btnPembagian, R.id.btnNambah, R.id.btnKurang};

    private TextView hasil;
    private boolean errcheck;
    private boolean angkaAkhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasil = findViewById(R.id.txtVw);
        SettingAngkaOnClickListener(); //pemanggilan fungsi
        SettingOperatorOnClicklistener();
    }

    private void SettingAngkaOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tombol = (Button) v;
                if (errcheck) {
                    hasil.setText(tombol.getText());
                    errcheck = false;
                } else {
                    hasil.append(tombol.getText());
                }
                angkaAkhir=true;
            }
        };
        for (int id : angka) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void SettingOperatorOnClicklistener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button tombol = (Button) v;
                if(angkaAkhir && !errcheck) {
                    hasil.append(tombol.getText());
                    angkaAkhir=false;
                }
            }
        };
        for (int id : operator) {
            findViewById(id).setOnClickListener(listener);
        }

        findViewById(R.id.btnTitik).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (angkaAkhir && !errcheck){
                    hasil.append(".");
                    angkaAkhir=false;
                }
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasil.setText("");
                errcheck=false;
                angkaAkhir=false;
            }
        });

        findViewById(R.id.btnSamadengan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hasilAkhir = hasil.getText().toString();
                if (angkaAkhir && !errcheck) {
                    Expression expression = new ExpressionBuilder(hasilAkhir).build();
                    try {
                        double result = expression.evaluate();
                        hasil.setText(Double.toString(result));
                    } catch (ArithmeticException e) {
                        errcheck = true;
                    }
                }
            }
        });
    }
}


