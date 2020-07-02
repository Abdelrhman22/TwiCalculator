package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView resultValue , errorTextView;
    private EditText secondOperandValue;
    private Button undoButton, equalButton, redoButton;
    private int buttonID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiUI();
    }

    private void intiUI() {
        resultValue = findViewById(R.id.textView_result_value);
        errorTextView = findViewById(R.id.textView_error);
        secondOperandValue = findViewById(R.id.editText_operand_value);
        secondOperandValue.setEnabled(false);
        secondOperandValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s))
                    equalButton.setEnabled(false);
                else
                    equalButton.setEnabled(true);
            }
        });
        undoButton = findViewById(R.id.btn_undo);
        equalButton = findViewById(R.id.btn_equal);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleEqualButton();
            }
        });
        redoButton = findViewById(R.id.btn_redo);
    }

    private void handleEqualButton() {
        double firstOperand, secondOperand, result = 0.0;
        if (!TextUtils.isEmpty(resultValue.getText().toString().trim())) {
            firstOperand = Utils.parseDouble(resultValue.getText().toString().trim());
        } else
            firstOperand = 0.0;
        secondOperand = Utils.parseDouble(secondOperandValue.getText().toString().trim());

        switch (buttonID) {
            case R.id.btn_plus:
                result = Operation.PLUS.calculate(firstOperand, secondOperand);
                break;
            case R.id.btn_minus:
                result = Operation.MINUS.calculate(firstOperand, secondOperand);
                break;
            case R.id.btn_multiply:
                result = Operation.MULTIPLY.calculate(firstOperand, secondOperand);
                break;
            case R.id.btn_divide:
                if (secondOperand != 0)
                    result = Operation.DIVIDE.calculate(firstOperand, secondOperand);
                break;
            default:
                result = 0.0;
        }
        secondOperandValue.setText("");
        if (buttonID == R.id.btn_divide && secondOperand == 0){
            errorTextView.setVisibility(View.VISIBLE);
            resultValue.setVisibility(View.GONE);
        }
        else {
            resultValue.setVisibility(View.VISIBLE);
            resultValue.setText(String.valueOf(Utils.round(result,2)));
            errorTextView.setVisibility(View.GONE);
            }
    }

    public void handleOperatorClick(View view) {
        buttonID = view.getId();
        secondOperandValue.setEnabled(true);
    }
}