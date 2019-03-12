package android.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            String str = "用户名或密码错误";
            if(message.what == 1) str = "登录成功";
            Toast.makeText(loginactivity.this, str, Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_login);

        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        final String email = pref.getString("email","");
        final String password = pref.getString("password","");
        ((TextView)findViewById(R.id.email)).setText((String)email);
        ((TextView)findViewById(R.id.password)).setText((String)password);

        Button reg = (Button)findViewById(R.id.btnLogin);
        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView text = (TextView)findViewById(R.id.email);
                final String te1 = text.getText().toString();
                TextView text1 = (TextView)findViewById(R.id.password);
                final String te2 = text1.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        if(email.equals(te1) && password.equals(te2)){
                            msg.what = 1;
                            Intent i = new Intent(loginactivity.this , mian.class);
                            startActivity(i);
                        }
                        else {
                            msg.what = 0;
                        }
                        handler.sendMessage(msg);
                    }
                }).start();

            }
        });

        Button creat = (Button)findViewById(R.id.btnLinkToRegisterScreen);
        creat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(loginactivity.this , MainActivity.class);
                startActivity(i);
            }
        });

    }
}
