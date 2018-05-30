package com.example.hao.mvptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyContract.IlogicView {

    private Button buttontest;
    private MyContract.IlogicPresenter mypresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttontest = (Button) findViewById(R.id.button_test);
        mypresenter= createMainPresenter();

        buttontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mypresenter.onSignIn("haoxu","haoxu");
            }
        });

    }

    @Override
    public MyContract.IlogicPresenter createMainPresenter() {
        return new Mypresenter();
    }

    @Override
    public void showtoast(String mes) {
        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mypresenter.onDetachView();
    }
}
