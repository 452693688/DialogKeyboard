package app.gm.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import app.gm.com.dialogkeyboard.R;


public class KeyboardView extends LinearLayout implements OnClickListener {
     private OnPressListener keyboardable;

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
         init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.key_board, this);
        initClickListener();
    }

    public void setOnPressListener(OnPressListener callBack) {
        this.keyboardable = callBack;
    }

    /**
     * 初始化键盘按钮监听
     */
    public void initClickListener() {
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.zero_left).setOnClickListener(this);
        findViewById(R.id.zero_right).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                keyboardable.onPress("1", false);
                break;
            case R.id.two:
                keyboardable.onPress("2", false);
                break;
            case R.id.three:
                keyboardable.onPress("3", false);
                break;
            case R.id.four:
                keyboardable.onPress("4", false);
                break;
            case R.id.five:
                keyboardable.onPress("5", false);
                break;
            case R.id.six:
                keyboardable.onPress("6", false);
                break;
            case R.id.seven:
                keyboardable.onPress("7", false);
                break;
            case R.id.eight:
                keyboardable.onPress("8", false);
                break;
            case R.id.nine:
                keyboardable.onPress("9", false);
                break;
            case R.id.zero:
                keyboardable.onPress("0", false);
                break;
            case R.id.zero_left:
                keyboardable.onPress(".", false);
                break;
            case R.id.zero_right:
                keyboardable.onPress(null, true);
                break;

        }
    }

    public interface OnPressListener {
        /**
         * 回调@param number 对应的输入数字键 @param isDelete 是否是删除键
         */
        public void onPress(String number, boolean isDelete);
    }
}
