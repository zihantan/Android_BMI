package com.Program5.BMICalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * This is the mainactivity class. This class calculates BMI.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class MainActivity extends AppCompatActivity {
    //This page is restricted to the portrait orientation
    // declare elements
    private Button calcBMI;
    private Button getAdvice;
    private RadioButton englishUnit;
    private RadioButton metricUnit;
    private EditText weight;
    private EditText height;
    private TextView BMI;

    /**
     * This method is called when first initializing.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect the components to the layout
        calcBMI = findViewById(R.id.button);
        getAdvice = findViewById(R.id.button2);
        englishUnit = findViewById(R.id.radioButton);
        metricUnit = findViewById(R.id.radioButton2);
        metricUnit.setEnabled(true);
        weight = findViewById(R.id.editText);
        height = findViewById(R.id.editText2);
        BMI = findViewById(R.id.BMIresult);

        // Not allow generate advice before getting BMI
        getAdvice.setEnabled(false);

        //change the hint when change the selection
        englishUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //when the other radio button is checked
                weight.setHint("Enter weight in Kilograms");
                height.setHint("Enter height in Meters");
            }
        });
        metricUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                weight.setHint("Enter weight in Pounds");
                height.setHint("Enter height in Inches");
            }
        });

        // Define Button "calculate" activity
        calcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result;

                //Cannot generate with empty values
                if (weight.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Weight Value.", Toast.LENGTH_SHORT).show();
                } else if (height.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Height Value.", Toast.LENGTH_SHORT).show();
                } else {
                    double heightNum = Double.parseDouble(height.getText().toString());
                    double weightNum = Double.parseDouble(weight.getText().toString());
                    if (metricUnit.isChecked()) {
                        result = calculateBMI(heightNum, weightNum, true);
                    } else {
                        result = calculateBMI(heightNum, weightNum, false);
                    }

                    //make sure the output only have two decimal space
                    DecimalFormat df = new DecimalFormat("####0.00");
                    BMI.setText(df.format(result) + "");

                    //Enable the advice button
                    Toast.makeText(getApplicationContext(), "BMI generated! Recheck if you entered your data in correct unit!", Toast.LENGTH_SHORT).show();
                    getAdvice.setEnabled(true);
                }
            }
        });

        getAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weight.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Weight Value!  Please re-generate BMI to get advice!", Toast.LENGTH_SHORT).show();
                } else if (height.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Height Value!  Please re-generate BMI to get advice!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("BMIdata", Double.parseDouble(BMI.getText().toString()));
                    startActivity(intent);
                    getAdvice.setEnabled(false);
                    BMI.setText("");
                }
            }
        });
    }

    /**
     * Calculates BMI.
     *
     * @param height the height
     * @param weight the weight
     * @param type   metric or english unit
     * @return BMI value
     */
    public double calculateBMI(double height, double weight, boolean type) {
        // type true when metric unit, false when english unit
        if (type) {
            return weight / (height * height);
        } else {
            return (weight * 703) / (height * height);
        }
    }
}
