package edu.missouri.mortgageassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView amount_textView;
    private TextView interestRate_textView;
    private TextView years_textView;
    private TextView monthly_textView;
    private TextView total_textView;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount_textView=findViewById(R.id.textView5);
        interestRate_textView=findViewById(R.id.textView6);
        years_textView=findViewById(R.id.textView7);
        monthly_textView=findViewById(R.id.textView8);
        total_textView=findViewById(R.id.textView9);
        back_button=findViewById(R.id.button2);

        Intent intent = getIntent();
        Double amount = intent.getDoubleExtra("AMOUNT", 0);
        Double interestRate = intent.getDoubleExtra("INTEREST_RATE",0);
        int years = intent.getIntExtra("YEARS",0);
        Double monthlyPayment = intent.getDoubleExtra("MONTHLY_PAYMENT",0);
        Double totalPayment = intent.getDoubleExtra("TOTAL_PAYMENT",0);

        amount_textView.setText("Mortgage Amount is $"+String.format("%.2f",amount)+".");
        interestRate_textView.setText("Monthly Interest Rate is "+String.format("%.2f",interestRate)+"%.");
        years_textView.setText("Mortgage Period is "+years+" years.");
        monthly_textView.setText("Monthly Payment is $"+String.format("%.2f",monthlyPayment)+".");
        total_textView.setText("Total Payment is $"+String.format("%.2f",totalPayment)+".");

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.super.onBackPressed();
            }
        });
    }
}
