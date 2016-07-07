package app.gm.com.dialogkeyboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import app.gm.com.view.KeyboardView;

/**
 * Created by Administrator on 2015/11/16.
 */
public class DialogKeyActivity extends Activity implements KeyboardView.OnPressListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        View dialog = LayoutInflater.from(this).inflate(R.layout.key_board_use, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        windowDeploy(0, display.getHeight());
        setContentView(dialog, params);
        KeyboardView keyboard = (KeyboardView) findViewById(R.id.key_board);
        //-------------------------------
        keyboard.setOnPressListener(this);
    }

    // 设置窗口显示
    public void windowDeploy(int x, int y) {
        Window window = getWindow(); // 得到对话框
        // window.setWindowAnimations(R.anim.test); // 设置窗口弹出动画
        WindowManager.LayoutParams wl = window.getAttributes();
        // 根据x，y坐标设置窗口需要显示的位置
        wl.x = x;
        wl.y = -y;
        wl.gravity = Gravity.BOTTOM;
        window.setAttributes(wl);
    }


    @Override
    public void onPress(String number, boolean isDelete) {

    }
}

