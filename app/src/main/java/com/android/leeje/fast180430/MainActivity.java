package com.android.leeje.fast180430;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    TextView preview;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonPls, buttonMns, buttonMtp, buttonDiv, buttonErs, buttonEql;
    Button buttonLeft, buttonRight, buttonDot, buttonBack;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fly);

        result = findViewById(R.id.result);
        preview = findViewById(R.id.preview);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonPls = findViewById(R.id.buttonPls);
        buttonMns = findViewById(R.id.buttonMns);
        buttonMtp = findViewById(R.id.buttonMtp);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonEql = findViewById(R.id.buttonEql);
        buttonErs = findViewById(R.id.buttonErs);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);
        buttonDot = findViewById(R.id.buttonDot);
        buttonBack = findViewById(R.id.buttonBack);


        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonPls.setOnClickListener(this);
        buttonMns.setOnClickListener(this);
        buttonMtp.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonErs.setOnClickListener(this);
        buttonEql.setOnClickListener(this);
        buttonLeft.setOnClickListener(this);
        buttonRight.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String temp = preview.getText().toString();
        String temp2 = result.getText().toString();
        String PREVIEW = getResources().getString(R.string.preview);
        if (temp.equals(PREVIEW)) temp = "";
        switch (view.getId()) {
            case R.id.button0:
                temp += "0";
                break;
            case R.id.button1:
                temp += "1";
                break;
            case R.id.button2:
                temp += "2";
                break;
            case R.id.button3:
                temp += "3";
                break;
            case R.id.button4:
                temp += "4";
                break;
            case R.id.button5:
                temp += "5";
                break;
            case R.id.button6:
                temp += "6";
                break;
            case R.id.button7:
                temp += "7";
                break;
            case R.id.button8:
                temp += "8";
                break;
            case R.id.button9:
                temp += "9";
                break;
            case R.id.buttonPls:
                temp += "+";
                break;
            case R.id.buttonMns:
                temp += "-";
                break;
            case R.id.buttonMtp:
                temp += "*";
                break;
            case R.id.buttonDiv:
                temp += "/";
                break;
            case R.id.buttonErs:
                temp = PREVIEW;
                temp2 = "0";
                break;
            case R.id.buttonLeft:
                temp += "(";
                break;
            case R.id.buttonRight:
                temp += ")";
                break;
            case R.id.buttonDot:
                temp += ".";
                break;
            case R.id.buttonBack:
                temp = temp.substring(0, temp.length() - 1);
                break;
            case R.id.buttonEql:
                temp = calc(temp);
                temp2 = temp;
                break;
        }

        view.startAnimation(animation);
        preview.setText(temp);
        result.setText(temp2);
    }

    private String calc(String temp) {
        String result = "";
        double answer = 0;

        String arrNum[] = temp.split("\\+|-|\\*|/|\\."); // op로 분리하기
        String arrOp[] = temp.split("\\d+"); // num으로 분리하기

//        // 당최 뭐가 문제일까요??
//        for (int i = 0; i < arrOp.length; i++) {
//            System.out.println("op[" + i + "] : " + arrOp[i]);
//        }
//        for (int i = 0; i < arrNum.length; i++) {
//            System.out.println("num[" + i + "] : " + arrNum[i]);
//        }

        Double arr[] = new Double[arrNum.length];

        for (int i = 0; i < arrNum.length; i++) {
            arr[i] = Double.parseDouble(arrNum[i]);
        }

        answer = arr[0];
        for (int i = 1; i < arrOp.length; i++) {
            switch (arrOp[i]) { // \\d+ 는 숫자를 제외한 문자를 반환, 배열의 첫번째 인덱스는 공백이 된다.
                case "+":
                    answer += arr[i];
                    break;
                case "-":
                    answer -= arr[i];
                    break;
                case "*":
                    answer *= arr[i];
                    break;
                case "/":
                    answer /= arr[i];
                    break;
                case "(":
                    break;
                case ")":
                    break;
                case ".":
                    if (!arrOp[i - 1].isEmpty())
                        switch (arrOp[i - 1]) {
                            case "+":
                                answer -= arr[i - 1]; // 이전 연산 취소
                                answer += arr[i - 1] + arr[i] / Math.pow(10, arrNum[i].length());
                                break;
                            case "-":
                                answer += arr[i - 1];
                                answer -= arr[i - 1] + arr[i] / Math.pow(10, arrNum[i].length());
                                break;
                            case "*":
                                answer /= arr[i - 1];
                                answer *= arr[i - 1] + arr[i] / Math.pow(10, arrNum[i].length());
                                break;
                            case "/":
                                answer *= arr[i - 1];
                                answer /= arr[i - 1] + arr[i] / Math.pow(10, arrNum[i].length());
                                break;
                        }
                    else
                        answer += arr[i] / Math.pow(10, arrNum[i].length()); // 거듭제곱함수 : Math.pow(밑, 지수)
                    break;
            }
        }

//        result = Double.toString(answer);
        result = ("" + answer);
        return result;
    }
}
