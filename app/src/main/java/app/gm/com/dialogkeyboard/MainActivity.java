package app.gm.com.dialogkeyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import app.gm.com.view.KeyboardEvent;
import app.gm.com.view.KeyboardListener;

public class MainActivity extends Activity implements View.OnClickListener, KeyboardListener {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.dialog_btn).setOnClickListener(this);
        tv = (TextView) findViewById(R.id.main_tv);
        KeyboardEvent keyboardEvent = new KeyboardEvent(this);
        keyboardEvent.setEventListener(this);
       // keyboardEvent.setAnimation(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_btn:
                Intent intent = new Intent();
                intent.setClass(this, DialogKeyActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onVisibilityChanged(boolean isOpen, int keyboardHeight, int status, String msg) {
        tv.setText(msg);
    }

    @Override
    public void onMove(float move, int moveLength) {
        Log.e("动画移动", "" + move + " 移动总长度" + moveLength);
    }
}
