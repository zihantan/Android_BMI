package com.Program5.BMICalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This is the secondactivity class. This class is used to show advice after calculating BMI.
 *
 * @author Zihan Tan
 * @author Jianyu Qiu
 */
public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private ImageView image;
    private TextView advice;

    /**
     * This method is called when first initializing.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        image = findViewById(R.id.imageView);
        advice = findViewById(R.id.advice);
        back = findViewById(R.id.button3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Double BMI = getIntent().getDoubleExtra("BMIdata", 0);

        if (BMI < 18.5) {
            image.setImageResource(R.drawable.underweight);
            advice.setText("Underweight");
        } else if (BMI < 25) {
            image.setImageResource(R.drawable.normal);
            advice.setText("Normal");
        } else if (BMI < 30) {
            image.setImageResource(R.drawable.overweight);
            advice.setText("Overweight");
        } else {
            image.setImageResource(R.drawable.obese);
            advice.setText("Obese");
        }
    }

    /**
     * This method is needed for the onClick listener.
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
