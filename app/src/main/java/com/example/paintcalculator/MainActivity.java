package com.example.paintcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*Для расчёта необходимого количества краски вам нужно выполнить следующие вычислительные процессы:

1. Найти площадь поверхности, которую нужно покрасить. Для этого умножьте ширину на высоту.

2. Умножьте площадь поверхности на количество слоёв, которые вы хотите наложить.
   Это даст нам общую площадь, которую нужно покрасить.

3. Разделить общую площадь на расход краски (квадратный метр на литр).
   Это даст нам количество литров краски, необходимых для покраски выбранной поверхности.

4. Разделить полученное количество краски на объём банки.
   Это даст нам количество банок краски, которое нужно купить.

5. Полученный расчёт количества краски, выводим через Toast или AlertDialog*/
public class MainActivity extends AppCompatActivity {

    private Button _Calculate;
    private RadioGroup _radioGroup;
    private EditText _KolvoMetrovWidth, _KolvoMetrovHeight,
                     _RashodKraski, _KolvoSloev, _ObiemBanki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RadioGroup
        RadioGroup _radioGroup = findViewById(R.id.radioGroup);
        // Установили rb BezZapasa чекнутым
        _radioGroup.check(findViewById(R.id.BezZapasa).getId());
        // EditTexts
        _KolvoMetrovWidth = findViewById(R.id.KolvoMetrovWidth);
        _KolvoMetrovHeight = findViewById(R.id.KolvoMetrovHeight);
        _RashodKraski = findViewById(R.id.RashodKraski);
        _KolvoSloev = findViewById(R.id.KolvoSloev);
        _ObiemBanki = findViewById(R.id.ObiemBanki);
        // Button
        _Calculate = findViewById(R.id.Calculate);
        // onClick на Button
        _Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float width = Float.parseFloat(_KolvoMetrovWidth.getText().toString());
                float height = Float.parseFloat(_KolvoMetrovHeight.getText().toString());
                float metrNaLitr = Float.parseFloat(_RashodKraski.getText().toString());
                float kolvoSloev = Float.parseFloat(_KolvoSloev.getText().toString());
                float obiemBanki = Float.parseFloat(_ObiemBanki.getText().toString());

                double KolvoKraski = (width * height * kolvoSloev) / (metrNaLitr * obiemBanki);

                switch (_radioGroup.getCheckedRadioButtonId()){
                    case(0):// 10% запаса
                        showInfoAlert(String.valueOf(Math.ceil(((KolvoKraski * 110) / 100))));
                        break;
                    case(1):// 15% запаса
                        showInfoAlert(String.valueOf(Math.ceil(((KolvoKraski * 115) / 100))));
                        break;
                    default:
                        showInfoAlert(String.valueOf(Math.ceil((KolvoKraski))));
                }
            }
        });
    }
    private void showInfoAlert(String s) {// Выводим рассчёт с помощю AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("РАССЧЁТ").setMessage(s);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}