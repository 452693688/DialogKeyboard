package app.gm.com.view;

/**
 * Created by Administrator on 2016/5/25.
 */
public interface KeyboardListener {
    void onVisibilityChanged(boolean isOpen, int keyboardHeight, int status,String msg);
    void onMove(float move, int moveLength);
}
