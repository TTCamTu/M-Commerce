package com.example.k22411c_firstdegree;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    Button btnEnglish;
    Button btnVietnamese;
    Button btnKorean;
    Button btnFrench;

    boolean isLoadViewFinished=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        isLoadViewFinished = true;
        updateLanguage(getResources());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void updateLanguage(Resources resources) {
        if (!isLoadViewFinished) return;
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
    }

    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        updateLanguage(getResources());
        recreate();
    }

    private void addEvents() {
        btnEnglish.setOnClickListener(v -> changeLanguage("en"));
        btnVietnamese.setOnClickListener(v -> changeLanguage("vi"));
        btnKorean.setOnClickListener(v -> changeLanguage("ko"));
        btnFrench.setOnClickListener(v -> changeLanguage("fr"));

        btnSolution.setOnClickListener(this::do_solution);
        btnNext.setOnClickListener(this::do_next);
        btnExit.setOnClickListener(this::do_exit);
    }

    private void addControls() {
        txtChooseLanguage = findViewById(R.id.txtChooseLanguage);
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
        btnEnglish = findViewById(R.id.btnEnglish);
        btnVietnamese = findViewById(R.id.btnVietnamese);
        btnKorean = findViewById(R.id.btnKorean);
        btnFrench = findViewById(R.id.btnFrench);
    }

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