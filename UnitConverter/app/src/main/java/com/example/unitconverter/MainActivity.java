package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner spinnerFrom, spinnerTo;
    private Button btnConvert;
    private TextView resultView;
    private String fromUnit, toUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputValue = findViewById(R.id.inputValue);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        btnConvert = findViewById(R.id.btnConvert);
        resultView = findViewById(R.id.resultView);

        // Set up the spinners with unit options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Listeners for Spinners
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Button click event for conversion
        btnConvert.setOnClickListener(v -> {
            String valueStr = inputValue.getText().toString();
            if (!valueStr.isEmpty()) {
                double inputValueDouble = Double.parseDouble(valueStr);
                double result = convertUnits(inputValueDouble, fromUnit, toUnit);
                resultView.setText(String.format("%.2f", result) + " " + toUnit);
            }
        });
    }

    // Conversion Logic
    private double convertUnits(double value, String from, String to) {
        if (from.equals("Centimeters") && to.equals("Meters")) {
            return value / 100;
        } else if (from.equals("Meters") && to.equals("Centimeters")) {
            return value * 100;
        } else if (from.equals("Grams") && to.equals("Kilograms")) {
            return value / 1000;
        } else if (from.equals("Kilograms") && to.equals("Grams")) {
            return value * 1000;
        }
        return value;  // Default case where no conversion is needed
    }
}
