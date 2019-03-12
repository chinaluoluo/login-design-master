package android.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            String str = "注册失败";
            if(message.what == 1) str = "注册成功";
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        //setContentView(R.layout.layout_login);
        //setContentView(R.layout.activity_main);

        Button reg = (Button)findViewById(R.id.btnRegister);
        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
                final TextView text = (TextView)findViewById(R.id.name);
                final TextView text1 = (TextView)findViewById(R.id.email);
                final TextView text2 = (TextView)findViewById(R.id.password);
                editor.putString("name",text.getText().toString());
                editor.putString("email",text1.getText().toString());
                editor.putString("password",text2.getText().toString());
                editor.apply();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        if(text == null||text1 == null||text2 == null) {
                            msg.what = 0;
                            msg.obj =  "输入不能为空.";
                        }
                        else {
                            msg.what = 1;
                        }
                        handler.sendMessage(msg);
                    }
                }).start();

            }
    });
        Button rebtnLink = (Button)findViewById(R.id.btnLinkToLoginScreen);
        rebtnLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this , loginactivity.class);
                startActivity(i);
            }
        });


    }
}
