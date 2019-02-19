package com.example.unitconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Length extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner s1,s2;
    Button convert;
    TextView Output;
    EditText Input;
    int[] opp = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        Input = (EditText) findViewById(R.id.Input);
        Output = (TextView) findViewById(R.id.Output);

        s1 = (Spinner) findViewById(R.id.spinner_L1);
        s2 = (Spinner) findViewById(R.id.spinner_L2);

        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.length_array,
                 android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);

        s1.setAdapter(adapter1);
        s2.setAdapter(adapter1);

        convert = (Button) findViewById(R.id.Convert_Button);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double in = Double.parseDouble(Input.getText().toString());
                String o = Double.toString(converter(in, opp[0], opp[1]));
                Output.setText(o, TextView.BufferType.NORMAL);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        switch (parent.getId()) {
            case R.id.spinner_L1:
                opp[0] = parent.getSelectedItemPosition();
                break;
            case R.id.spinner_L2:
                opp[1] = parent.getSelectedItemPosition();
                break;
            default:
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private double converter(double toBeConverted, int in, int out) {
        double x = toBeConverted;
        double result = 0;
        switch(in) {
            case 0:
                switch(out) {
                    case 0:
                        result = x;
                        break;
                    case 1:
                        result = x / 12;
                        break;
                    case 2:
                        result = x / 36;
                        break;
                    case 3:
                        result = x / 63360;
                        break;
                    case 4:
                        result = x * 2.54;
                        break;
                    case 5:
                        result = x * 0.0254;
                        break;
                    case 6:
                        result = x * 0.0000254;
                        break;
                }
                break;
            case 1:
                switch(out) {
                    case 0:
                        result = x *12;
                        break;
                    case 1:
                        result = x;
                        break;
                    case 2:
                        result = x / 3;
                        break;
                    case 3:
                        result = x / 5280;
                        break;
                    case 4:
                        result = x * 30.48;
                        break;
                    case 5:
                        result = x * 0.3048;
                        break;
                    case 6:
                        result = x * 0.0003048;
                        break;
                }
                break;
            case 2:
                switch(out) {
                    case 0:
                        result = x * 36;
                        break;
                    case 1:
                        result = x * 3;
                        break;
                    case 2:
                        result = x;
                        break;
                    case 3:
                        result = x / 1760;
                        break;
                    case 4:
                        result = x * 91.44;
                        break;
                    case 5:
                        result = x * 0.9144;
                        break;
                    case 6:
                        result = x * 0.0009144;
                        break;
                }
                break;
            case 3:
                switch(out) {
                    case 0:
                        result = x * 63360;
                        break;
                    case 1:
                        result = x * 5280;
                        break;
                    case 2:
                        result = x * 1760;
                        break;
                    case 3:
                        result = x;
                        break;
                    case 4:
                        result = x * 160934.4;
                        break;
                    case 5:
                        result = x * 1609.344;
                        break;
                    case 6:
                        result = x * 1.609344;
                        break;
                }
                break;
            case 4:
                switch(out) {
                    case 0:
                        result = x / 2.54;
                        break;
                    case 1:
                        result = x / 30.48;
                        break;
                    case 2:
                        result = x / 91.44;
                        break;
                    case 3:
                        result = x / 160934.4;
                        break;
                    case 4:
                        result = x;
                        break;
                    case 5:
                        result = x / 100;
                        break;
                    case 6:
                        result = x / 100000;
                        break;
                }
                break;
            case 5:
                switch(out) {
                    case 0:
                        result = x * 39.37;
                        break;
                    case 1:
                        result = x * 3.281;
                        break;
                    case 2:
                        result = x * 1.094;
                        break;
                    case 3:
                        result = x / 1609.344;
                        break;
                    case 4:
                        result = x * 100;
                        break;
                    case 5:
                        result = x;
                        break;
                    case 6:
                        result = x / 1000;
                        break;
                }
                break;
            case 6:
                switch(out) {
                    case 0:
                        result = x * 39370.079;
                        break;
                    case 1:
                        result = x * 3280.84;
                        break;
                    case 2:
                        result = x * 1093.613;
                        break;
                    case 3:
                        result = x / 1.609;
                        break;
                    case 4:
                        result = x * 100000;
                        break;
                    case 5:
                        result = x * 1000;
                        break;
                    case 6:
                        result = x;
                        break;
                }
                break;
        }
        return result;
    }
}
