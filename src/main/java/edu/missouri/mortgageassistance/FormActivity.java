package edu.missouri.mortgageassistance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {

    private TextView title;
    private TextView amount_textView;
    private TextView interestRate_textView;
    private TextView period_textView;
    private EditText amount_editText;
    private EditText interestRate_editText;
    private RadioGroup period_radioGroup;
    private RadioButton oneYear_radioButton;
    private RadioButton threeYears_radioButton;
    private RadioButton fiveYears_radioButton;
    private Button results;
    //private double amount;
    //private double interestRate;
    private int years;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        title=findViewById(R.id.textView4);
        amount_textView=findViewById(R.id.textView);
        interestRate_textView=findViewById(R.id.textView2);
        period_textView=findViewById(R.id.textView3);
        amount_editText=findViewById(R.id.editText3);
        interestRate_editText=findViewById(R.id.editText4);
        period_radioGroup=findViewById(R.id.radioGroup);
        oneYear_radioButton=findViewById(R.id.radioButton);
        threeYears_radioButton=findViewById(R.id.radioButton2);
        fiveYears_radioButton=findViewById(R.id.radioButton3);
        results=findViewById(R.id.button);

        period_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        years = 1;
                        break;
                    case R.id.radioButton2:
                        years = 3;
                        break;
                    case R.id.radioButton3:
                        years = 5;
                        break;
                }
            }
        });

        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount, interestRate;
                Double monthlyPayment;
                Double totalPayment;
                if (amount_editText.getText().toString().isEmpty()) {
                    showAlertDialog("Amount field is missing");
                    return;
                }
                if (interestRate_editText.getText().toString().isEmpty()){
                    showAlertDialog("Interest Rate field is missing");
                    return;
                }
                if (!oneYear_radioButton.isChecked() && !threeYears_radioButton.isChecked() && !fiveYears_radioButton.isChecked()){
                    showAlertDialog(String.format("%s field is missing", oneYear_radioButton.getHint().toString()));
                    return;
                } else {
                    amount = amount_editText.getText().toString();
                    interestRate = interestRate_editText.getText().toString();
                    monthlyPayment=Double.parseDouble(amount)*(.01*Double.parseDouble(interestRate))*((Math.pow((1+(.01*Double.parseDouble(interestRate))),(years*12)))/(Math.pow((1+(.01*Double.parseDouble(interestRate))),(years*12))-1));
                    totalPayment=monthlyPayment*years*12;
                    Intent intent = new Intent(FormActivity.this, MainActivity.class);
                    intent.putExtra("AMOUNT",Double.parseDouble(amount));
                    intent.putExtra("INTEREST_RATE", Double.parseDouble(interestRate));
                    intent.putExtra("YEARS", years);
                    intent.putExtra("MONTHLY_PAYMENT", monthlyPayment);
                    intent.putExtra("TOTAL_PAYMENT", totalPayment);
                    startActivity(intent);
                }
            }
        });

    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
