package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        Button goToCalculatorButton = findViewById(R.id.go_to_calculator_button);

        goToCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CalculatorActivity when the button is clicked
                openCalculator();
            }
        });
    }

    private void openCalculator() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }
}
