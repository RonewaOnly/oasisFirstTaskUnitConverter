package com.example.oasis_first_task_unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private   EditText value;
    private Spinner unitChange;
    private Button changebtn;
    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        value = findViewById(R.id.unit);
        unitChange = findViewById(R.id.unitSpinner);
        changebtn = findViewById(R.id.changeButton);
        display = findViewById(R.id.displayValue);

        String[] array = {"m to km","km to m", "cm to inch", "inch to cm","mm to m","feet to inch","mile to feet"};
        ArrayAdapter<String> giveValues = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,array);
        giveValues.setDropDownViewResource(android.R.layout.simple_spinner_item);
        unitChange.setAdapter(giveValues);

        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertValues();
            }
        });
    }
    private void convertValues(){
        String input = value.getText().toString();
        if (input.isEmpty()){
            display.setText("Enter Value ");
            return;
        }

        double checkValues = Double.parseDouble(input);
        String selectedUnit = unitChange.getSelectedItem().toString();
        double changedValues = 0;

        switch (selectedUnit){
            case "m to km":
                changedValues = checkValues/1000;
                break;
            case "km to m":
                changedValues = checkValues*1000;
                break;
            case "cm to inch":
                changedValues = checkValues* 2.54;
                break;
            case "mm to m":
                changedValues = checkValues/ 1000;
                break;
            case "feet to inch":
                changedValues = checkValues * 12;
                break;
            case "inch to cm":
                changedValues = checkValues * 2.54;
                break;
            case "mile to feet":
                changedValues = checkValues * 5280;
                break;
        }
        display.setText(String.valueOf(changedValues));
    }
}