package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculator.adapters.ItemAdapter;
import com.example.calculator.interfaces.Updatable;
import com.example.calculator.models.OperationItem;
import com.example.calculator.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Updatable {

    private TextView resultValue, errorTextView;
    private EditText secondOperandValue;
    private Button undoButton, equalButton, redoButton;
    private int buttonID;
    private RecyclerView previousOperationRecyclerView;
    private List<OperationItem> list = new ArrayList<>();
    private List<OperationItem> redoList = new ArrayList<>();
    private ItemAdapter adapter;

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
                if (TextUtils.isEmpty(s))
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
        previousOperationRecyclerView = findViewById(R.id.recyclerView_previous_operations);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        previousOperationRecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void handleEqualButton() {
        double firstOperand, secondOperand, result = 0.0;
        firstOperand = getLastSavedResult();
        secondOperand = Utils.parseDouble(secondOperandValue.getText().toString().trim());
        Operation operation = Operation.PLUS;
        int code = Utils.PLUS_OPERATION_CODE;
        String value = String.valueOf(secondOperand);
        switch (buttonID) {
            case R.id.btn_plus:
                result = Operation.PLUS.calculate(firstOperand, secondOperand);
                operation = Operation.PLUS;
                code = Utils.PLUS_OPERATION_CODE;
                break;
            case R.id.btn_minus:
                result = Operation.MINUS.calculate(firstOperand, secondOperand);
                operation = Operation.MINUS;
                code = Utils.MINUS_OPERATION_CODE;
                break;
            case R.id.btn_multiply:
                result = Operation.MULTIPLY.calculate(firstOperand, secondOperand);
                operation = Operation.MULTIPLY;
                code = Utils.MULTIPLY_OPERATION_CODE;
                break;
            case R.id.btn_divide:
                if (secondOperand != 0) {
                    result = Operation.DIVIDE.calculate(firstOperand, secondOperand);
                    operation = Operation.DIVIDE;
                    code = Utils.DIVIDE_OPERATION_CODE;
                    break;
                }
            default:
                result = 0.0;
        }
        secondOperandValue.setText("");
        secondOperandValue.setEnabled(false);
        if (buttonID == R.id.btn_divide && secondOperand == 0) {
            errorTextView.setVisibility(View.VISIBLE);
            resultValue.setVisibility(View.GONE);
        } else {
            list.add(new OperationItem(operation, value, code));
            undoButton.setEnabled(true);
            if (adapter == null) {
                adapter = new ItemAdapter(this, list , this);
                previousOperationRecyclerView.setAdapter(adapter);
            } else
                adapter.notifyDataSetChanged();
            resultValue.setVisibility(View.VISIBLE);
            resultValue.setText(String.valueOf(Utils.round(result, 2)));
            errorTextView.setVisibility(View.GONE);
        }
    }

    private double getLastSavedResult() {
        double firstOperand = 0.0;
        // in case of user made at least one operation
        if (!TextUtils.isEmpty(resultValue.getText().toString().trim()))
            firstOperand = Utils.parseDouble(resultValue.getText().toString().trim());
        return firstOperand;
    }

    public void handleOperatorClick(View view) {
        buttonID = view.getId();
        secondOperandValue.setEnabled(true);
        //findViewById(buttonID).setBackground(getResources().getDrawable(R.drawable.button_selected));
    }

    public void handleButton(View view) {
        int ClickedButton = view.getId();
        errorTextView.setVisibility(View.GONE);
        if (ClickedButton == R.id.btn_undo) {
            OperationItem item = list.get(list.size()-1);
            handleUndoEvent(item);
        }
        else if(ClickedButton == R.id.btn_redo) {
            undoButton.setEnabled(true);
            int index = redoList.size() - 1;
            OperationItem item = redoList.get(index);
            list.add(item);
            redoList.remove(item);
            if(redoList!=null &&  redoList.size() ==0)
                redoButton.setEnabled(false);
            adapter.notifyDataSetChanged();
            updateResult(Utils.getOperationByCode((item.getCode())),item.getValue());
        }
    }

    private void handleUndoEvent(OperationItem item) {
        redoButton.setEnabled(true);
        redoList.add(item);
        list.remove(item);
        if (list!=null &&  list.size() == 0)
            undoButton.setEnabled(false);
        adapter.notifyDataSetChanged();
        // reverse operation code
        updateResult(Utils.getOperationByCode((item.getCode() * -1)),item.getValue());
    }

    private void updateResult(Operation operation , String value) {
        double firstOperand = getLastSavedResult();
        double result = operation.calculate(firstOperand, Utils.parseDouble(value));
        resultValue.setVisibility(View.VISIBLE);
        resultValue.setText(String.valueOf(Utils.round(result, 2)));
    }

    @Override
    public void update(OperationItem operationItem) {
        // in case of user divide by zero before click item
        errorTextView.setVisibility(View.GONE);
        handleUndoEvent(operationItem);
    }
}