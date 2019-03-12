package android.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class mian extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = "登录成功";
        Toast.makeText(mian.this, str, Toast.LENGTH_SHORT).show();
        Button reg1 = (Button)findViewById(R.id.btnLogout);
        reg1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(mian.this , loginactivity.class);
                startActivity(i);
            }
        });
    }
}
