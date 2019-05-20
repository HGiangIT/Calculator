package com.gumiho.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Khai bao nut
    private EditText edtInput;
    private TextView tvResult;

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    private Button btnCong;
    private Button btnTru;
    private Button btnNhan;
    private Button btnChia;
    private Button btnPhay;
    private Button btnBang;

    private Button btnDel;
    private Button btnDelAll;

    //Khai bao bien tag de in ra man hinh
    //private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventListener();

    }

    public void initWidget() {
        //Gán các nút với view
        edtInput = (EditText) findViewById(R.id.edt_input);
        tvResult = (TextView) findViewById(R.id.tv_result);

        btn0 = (Button) findViewById(R.id.btn_0);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);

        btnCong = (Button) findViewById(R.id.btn_cong);
        btnTru = (Button) findViewById(R.id.btn_tru);
        btnNhan = (Button) findViewById(R.id.btn_nhan);
        btnChia = (Button) findViewById(R.id.btn_chia);
        btnBang = (Button) findViewById(R.id.btn_bang);

        btnPhay = (Button) findViewById(R.id.btn_phay);
        btnDel = (Button) findViewById(R.id.btn_de1);
        btnDelAll = (Button) findViewById(R.id.btn_delclear);
    }

    public void setEventListener() {
        //Xét sự kiện OnCLick lên các nút
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
        btnPhay.setOnClickListener(this);
        btnBang.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnDelAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                edtInput.append("0");       //Khi bấm nút, edit hiển thị tiếp theo. Không dùng get text vì sẽ bị thay thế
                break;

            case R.id.btn_1:
                edtInput.append("1");
                break;

            case R.id.btn_2:
                edtInput.append("2");
                break;

            case R.id.btn_3:
                edtInput.append("3");

                break;

            case R.id.btn_4:
                edtInput.append("4");

                break;

            case R.id.btn_5:
                edtInput.append("5");

                break;

            case R.id.btn_6:
                edtInput.append("6");

                break;

            case R.id.btn_7:
                edtInput.append("7");

                break;

            case R.id.btn_8:
                edtInput.append("8");
                break;

            case R.id.btn_9:
                edtInput.append("9");
                break;

            case R.id.btn_cong:
                edtInput.append("+");
                break;

            case R.id.btn_tru:
                edtInput.append("-");
                break;

            case R.id.btn_nhan:
                edtInput.append("x");
                break;

            case R.id.btn_chia:
                edtInput.append("÷");
                break;

            case R.id.btn_phay:
                edtInput.append(".");
                break;

            case R.id.btn_bang:
                DecimalFormat df = new DecimalFormat("###.##");
                Double result = 0.0;
                int i, k;
                addOperation(edtInput.getText().toString());
                addNumber(edtInput.getText().toString());

                //Xu li thuat toan. Tao 2 mang so va phep tinh.
                // neu arrNumber[0] thi arrNumber[0] arrOperation[0] arrNumber[1]
                // sai, result = result arrOperation[i] arrNumber[i+1]
                if (arrNumber.size() == 1) {
                    tvResult.setText(edtInput.getText());
                } else {
                    if ((arrOperation.size() >= arrNumber.size())) {
                        Toast.makeText(this, "Lỗi định dạng. Phép tính sai", Toast.LENGTH_SHORT).show();
                    } else {
                        Double temp = 0.0;
                        //System.out.println(arrOperation.get(0));
                        for (k = 0; k < arrOperation.size(); ) {

                            if (arrOperation.get(k).equals("x") || arrOperation.get(k).equals("÷")) {
                                if (arrOperation.get(k).equals("x")) {
                                    temp = arrNumber.get(k) * arrNumber.get(k + 1);
                                } else if (arrOperation.get(k).equals("÷")) {

                                    temp = arrNumber.get(k) / arrNumber.get(k + 1);
                                }
//                            System.out.println("temp:" + temp);
//                            System.out.println("k:" + k);
                                arrNumber.set(k, temp);
                                arrNumber.remove(k + 1);
                                arrOperation.remove(k);
                            }
                            k++;
                        }

                        for (k = 0; k < arrOperation.size(); ) {

                            if (arrOperation.get(k).equals("+")) {
                                temp = arrNumber.get(k) + arrNumber.get(k + 1);
                            } else if (arrOperation.get(k).equals("-")) {

                                temp = arrNumber.get(k) - arrNumber.get(k + 1);
                            }
//                            System.out.println("temp:" + temp);
//                            System.out.println("k:" + k);
                            arrNumber.set(k, temp);
                            arrNumber.remove(k + 1);
                            arrOperation.remove(k);
                        }
                        tvResult.setText(String.valueOf(df.format(arrNumber.get(0))));
                    }




//                        for (i = 0; i < arrNumber.size() - 1; i++) {
//                            switch (arrOperation.get(i)) {
//                                case "+":
//                                    if (i == 0) {
//                                        result = arrNumber.get(i) + arrNumber.get(i + 1);
//                                    } else result = result + arrNumber.get(i + 1);
//                                    break;
//
//                                case "-":
//                                    if (i == 0) {
//                                        result = arrNumber.get(i) - arrNumber.get(i + 1);
//                                    } else result = result - arrNumber.get(i + 1);
//                                    break;
//
//                                case "x":
//                                    if (i == 0) {
//                                        result = arrNumber.get(i) * arrNumber.get(i + 1);
//                                    } else result = result * arrNumber.get(i + 1);
//                                    break;
//
//                                case "÷":
//                                    if (i == 0) {
//                                        result = arrNumber.get(i) / arrNumber.get(i + 1);
//                                    } else result = result / arrNumber.get(i + 1);
//                                    break;
//
//                                default:
//                                    break;
//
//                            }
//
//                        }
//
//                        tvResult.setText(df.format(result).toString());

        }
        break;

        case R.id.btn_de1:
        BaseInputConnection textFieldConnection = new BaseInputConnection(edtInput, true);
        textFieldConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
        break;

        case R.id.btn_delclear:
        edtInput.setText("");
        break;
    }

}

    //Khai bao mang chứa các phép tính
    public ArrayList<String> arrOperation;
    //Khai báo mảng chứa các số
    public ArrayList<Double> arrNumber;

    //Lấy tất cả các phép tính lưu vào mảng arrOpertion
    public int addOperation(String input) {
        //khởi tạo arrOperation
        arrOperation = new ArrayList<String>();

        //gán các phần tử trong input vào mảng char
        char[] cArray = input.toCharArray();

        int i;
        for (i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;

                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;

                case 'x':
                    arrOperation.add(cArray[i] + "");
                    break;

                case '÷':
                    arrOperation.add(cArray[i] + "");
                    break;
            }
        }
        return 0;
    }

    public void addNumber(String input) {
        //Khởi tạo arrNumber
        arrNumber = new ArrayList<Double>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(input);

        while (matcher.find()) {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }

    }
}
