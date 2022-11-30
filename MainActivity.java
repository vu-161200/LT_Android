package com.example.projectandroid;




import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectandroid.R;

public class MainActivity extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_Add, btn_Sub, btn_Mul, btn_Div, btn_calc, btn_dec, btn_clear, btn_CE, btn_BS;
    EditText ed1;

    String pheptinh = "";

    boolean calculated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);

        btn_0 = (Button) findViewById(R.id.button0);
        btn_1 = (Button) findViewById(R.id.button1);
        btn_2 = (Button) findViewById(R.id.button2);
        btn_3 = (Button) findViewById(R.id.button3);
        btn_4 = (Button) findViewById(R.id.button4);
        btn_5 = (Button) findViewById(R.id.button5);
        btn_6 = (Button) findViewById(R.id.button6);
        btn_7 = (Button) findViewById(R.id.button7);
        btn_8 = (Button) findViewById(R.id.button8);
        btn_9 = (Button) findViewById(R.id.button9);
        btn_Add = (Button) findViewById(R.id.buttonCong);
        btn_Div = (Button) findViewById(R.id.buttonChia);
        btn_Sub = (Button) findViewById(R.id.buttonTru);
        btn_Mul = (Button) findViewById(R.id.buttonNhan);
        btn_calc = (Button) findViewById(R.id.buttonBang);
        btn_dec = (Button) findViewById(R.id.buttonPhay);
        btn_clear = (Button) findViewById(R.id.buttonC);
        btn_CE = (Button) findViewById(R.id.buttonCE);
        btn_BS = (Button) findViewById(R.id.buttonBS);
        ed1 = (EditText) findViewById(R.id.textView);

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("0");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input("9");
            }
        });

        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(ed1.getText() + ".");
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pheptinh = "+";

                ed1.setText(ed1.getText() + " " + pheptinh + " ");
            }
        });

        btn_Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pheptinh = "-";

                ed1.setText(ed1.getText() + " " + pheptinh + " ");
            }
        });

        btn_Mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pheptinh = "x";

                ed1.setText(ed1.getText() + " " + pheptinh + " ");
            }
        });

        btn_Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pheptinh = "/";
                ed1.setText(ed1.getText() + " " + pheptinh + " ");
            }
        });

        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String[] elements = ed1.getText().toString().split(pheptinh);

                    float param1 = Float.parseFloat(elements[0].trim());
                    float param2 = Float.parseFloat(elements[1].trim());

                    String result;

                    switch (pheptinh) {
                        case "+":
                            result = String.valueOf(param1 + param2);
                            break;
                        case "-":
                            result = String.valueOf(param1 - param2);
                            break;
                        case "x":
                            result = String.valueOf(param1 * param2);
                            break;
                        case "/":
                            result = String.valueOf(param1 / param2);
                            break;
                        default:
                            result = "ERROR";
                            break;
                    }

                    ed1.setText(result);
                    calculated = true;
                } catch (Exception e) {
                    ed1.setText(e.toString());
                }
            }
        });
        btn_CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = ed1.getText().toString();

                int index = currentText.indexOf(pheptinh);

                if(index < 0){
                    ed1.setText("0");
                    pheptinh = "";
                }else{
                    String newText = currentText.substring(0, index + 2);

                    newText += "0";

                    ed1.setText(newText);
                }
            }
        }

        );
        btn_BS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ed1.getText().toString();
                String newString;

                if(text.length() == 1){
                    newString = "0";
                    pheptinh = "";
                }else{
                    newString = text.substring(0,text.length()-1);
                }

                ed1.setText(newString.trim());
            }
        }

        );


        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText("0");
                pheptinh = "";
            }
        });
    }

    void input(String s) {
        String text = ed1.getText().toString();
        if(calculated){
            text = "0";
            pheptinh = "";
            calculated = false;
        }

        int index = pheptinh.equals("") ? -1 : text.indexOf(pheptinh);

        if(index >= 0){
            String[] param = text.replaceAll(" ", "").split(pheptinh);

            if(param.length > 1 && param[1].equals("0")){
                text = param[0] + " " + pheptinh + " ";
            }

            text += s;

        }else {
            if (text.equals("0")) {
                text = s;
            } else {
                text += s;
            }
        }

        ed1.setText(text);
    }
}


