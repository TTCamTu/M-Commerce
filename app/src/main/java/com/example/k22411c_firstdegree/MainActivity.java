package com.example.k22411c_firstdegree;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Khai báo các biến để quản lý ô nhớ của các view:
    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResult;

    TextView titleCoefficientA;
    TextView titleCoefficientB;
    TextView titleResult;
    TextView title_app;
    Button btnSolution;
    Button btnNext;
    Button btnExit;
    TextView txtChooseLanguage;
    Spinner spinnerLanguage;
    ArrayAdapter<String> adapterLanguage;
    boolean isLoadViewFinished=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addControls();

        addEvents();

//        addViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void UpdateLanguage(Resources resources)
    {
        if(isLoadViewFinished==false)return;
        txtChooseLanguage.setText(resources.getString(R.string.strChooseLanguage));
        edtCoefficientA.setHint(resources.getString(R.string.title_coefficient_a));
        edtCoefficientB.setHint(resources.getString(R.string.title_coefficient_b));
        btnSolution.setText(resources.getString(R.string.title_solution));
        btnNext.setText(resources.getString(R.string.title_next));
        btnExit.setText(resources.getString(R.string.title_exit));
        txtResult.setText(resources.getString(R.string.title_result));
        titleCoefficientA.setText(resources.getString(R.string.title_coefficient_a));
        titleCoefficientB.setText(resources.getString(R.string.title_coefficient_b));
        titleResult.setText(resources.getString(R.string.title_result));
        title_app.setText(resources.getString(R.string.title_app));
        setTitle(resources.getString(R.string.app_name));
        adapterLanguage.clear();
        adapterLanguage.addAll(resources.getStringArray(R.array.arrLanguage));
        adapterLanguage.notifyDataSetChanged();
    }
    private void addEvents() {
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              String lang="en";
              if(i==0)
              {
                  lang="en";
              }
              else if(i==1)
              {
                  lang="vi";
              }
              else if(i==2)
              {
                  lang="ko";
              }
              else if(i==3)
                  {
                  lang="fr";
              }
              Locale locale = new Locale(lang);
              Locale.setDefault(locale);
              Configuration configuration = getResources().getConfiguration();
              configuration.setLocale(locale);
              Context context= createConfigurationContext(configuration);
              Resources resources = context.getResources();
              UpdateLanguage(resources);
          }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void addControls() {
        txtChooseLanguage= (TextView)
                findViewById(R.id.txtChooseLanguage);
        edtCoefficientA = findViewById(R.id.edtCoefficientA);
        edtCoefficientB = findViewById(R.id.edtCoefficientB);
        txtResult = findViewById(R.id.txtResult);
        titleCoefficientA = findViewById(R.id.titleCoefficientA);
        titleCoefficientB = findViewById(R.id.titleCoefficientB);
        titleResult = findViewById(R.id.titleResult);
        title_app = findViewById(R.id.title_app);
        btnSolution = findViewById(R.id.btnSolution);
        btnNext = findViewById(R.id.btnNext);
        btnExit = findViewById(R.id.btnExit);
        spinnerLanguage= (Spinner)
                findViewById(R.id.spinnerLanguage);
        adapterLanguage=new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item
        );
        adapterLanguage.addAll(getResources().getStringArray(R.array.arrLanguage));
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapterLanguage);
        isLoadViewFinished=true;
    }

//    private void addViews() {
//        edtCoefficientA = findViewById(R.id.edtCoefficientA);
//        edtCoefficientB = findViewById(R.id.edtCoefficientB);
//        txtResult = findViewById(R.id.txtResult);
//    }

    public void do_solution(View view) {

        //Lấy hệ số a trên giao diện
        String hsa=edtCoefficientA.getText().toString();
        double a=Double.parseDouble(hsa);

        //Lấy hệ số b trên giao diện
        double b=Double.parseDouble(edtCoefficientB.getText().toString());

        if (a==0 && b==0)
        {
            //txtResult.setText("Infinity!");
            txtResult.setText(getResources().getText(R.string.title_infinity));
        }
        else if (a==0 && b!=0)
        {
            //txtResult.setText("No Solution");
            txtResult.setText(getResources().getText(R.string.title_no_solution));
        }
        else
        {
            double x=-b/a;
            txtResult.setText("x="+x);
        }
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        txtResult.setText("");
        //Di chuyển con trỏ nhập liệu vào hệ số A
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }
}