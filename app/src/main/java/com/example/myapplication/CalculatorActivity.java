package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

public class CalculatorActivity extends AppCompatActivity {

    private EditText GWeight_editText;
    private EditText GValue_editText;
    private TextView TotValue_GWeight_MinusUruf;
    private TextView zakat_payable_text_view;
    private TextView totZakat_textView;
    private RadioGroup GType_radioGroup;
    private RadioButton GType_keep;
    private RadioButton GType_wear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        // Find the back button by its ID
        ImageButton backButton = findViewById(R.id.float_bckbutton);

// Set an OnClickListener to handle the button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);

                // Start the MainActivity
                startActivity(intent);

                // Finish the current activity (CalculatorActivity)
                finish();
            }
        });


        GWeight_editText = findViewById(R.id.GWeight_editText);
        GValue_editText = findViewById(R.id.GValue_editText);
        TotValue_GWeight_MinusUruf = findViewById(R.id.TotValue_GWeight_MinusUruf);
        zakat_payable_text_view = findViewById(R.id.zakat_payable_text_view);
        totZakat_textView = findViewById(R.id.totZakat_textView);
        GType_radioGroup = findViewById(R.id.GType_radioGroup);
        GType_keep = findViewById(R.id.GType_keep);
        GType_wear = findViewById(R.id.GType_wear);

        Button calculateButton = findViewById(R.id.calcButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });

        // Add this block to get the Reset button reference and set an OnClickListener
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm(v);
            }
        });
    }
    private void calculateZakat() {
        String goldWeightStr = GWeight_editText.getText().toString().trim();
        String goldValueStr = GValue_editText.getText().toString().trim();

        // Find the selected RadioButton in the RadioGroup
        RadioButton selectedRadioButton = findViewById(GType_radioGroup.getCheckedRadioButtonId());

        if (!goldWeightStr.isEmpty() && !goldValueStr.isEmpty() && selectedRadioButton != null) {
            String goldType = selectedRadioButton.getText().toString().toLowerCase();

            BigDecimal goldWeight = new BigDecimal(goldWeightStr);
            BigDecimal goldValue = new BigDecimal(goldValueStr);

            BigDecimal goldWeightminUruf = goldWeight.subtract(new BigDecimal(85));
            BigDecimal zakatPayable = BigDecimal.ZERO;

            if (goldType.equals("keep")) {
                zakatPayable = goldWeightminUruf.max(BigDecimal.ZERO).multiply(goldValue);
            } else if (goldType.equals("wear")) {
                goldWeightminUruf = goldWeight.subtract(new BigDecimal(200));
                zakatPayable = goldWeightminUruf.max(BigDecimal.ZERO).multiply(goldValue);
            }

            BigDecimal totalZakat = zakatPayable.multiply(new BigDecimal(0.025)).setScale(2, BigDecimal.ROUND_HALF_UP);

            TotValue_GWeight_MinusUruf.setText(goldWeightminUruf.toString());
            zakat_payable_text_view.setText(zakatPayable.toString());
            totZakat_textView.setText(totalZakat.toString());
        } else {
            Toast.makeText(this, "Please fill in all fields and select a gold type.", Toast.LENGTH_SHORT).show();
        }
    }

    // This method should be outside of calculateZakat
    public void resetForm(View view) {
        // Add code to reset or clear the form fields
        // For example, you can clear text in EditText fields or reset checkboxes.
        // You may also want to update any other UI components or variables accordingly.
        GWeight_editText.setText("");
        GValue_editText.setText("");
        GType_radioGroup.clearCheck(); // Clear the selected RadioButton in the RadioGroup
        TotValue_GWeight_MinusUruf.setText("");
        zakat_payable_text_view.setText("");
        totZakat_textView.setText("");
    }


}
