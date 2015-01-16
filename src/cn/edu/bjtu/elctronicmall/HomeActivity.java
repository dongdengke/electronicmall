package cn.edu.bjtu.elctronicmall;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;
import cn.edu.bjtu.elctronicmall.view.FirstView;

public class HomeActivity extends Activity {
	// Handler handler = new Handler() {
	// public void handleMessage(android.os.Message msg) {
	// SecondView secondView = new SecondView(getApplicationContext());
	// changeView(secondView);
	// };
	// };
	private RelativeLayout middle;
	private View view1;
	private View view2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		middle = (RelativeLayout) findViewById(R.id.middle);
		UIManager.getInstance().setMiddle(middle);
		// 向uimanager注册观察者
		UIManager.getInstance().addObserver(BottomManager.getInstance());
		UIManager.getInstance().addObserver(TitleManager.getInstance());
		TitleManager.getInstance().init(this);
		TitleManager.getInstance().showOneText();
		BottomManager.getInstance().init(this);
		BottomManager.getInstance().showBottom();
		// addFirstView();
		UIManager.getInstance().changeView(FirstView.class);
		// handler.sendEmptyMessageDelayed(10, 2000);
	}

	private void addFirstView() {
		FirstView firstView = new FirstView(this);
		view1 = firstView.getView(this);
		middle.addView(view1);
	}

	/**
	 * 返回键的处理
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean goBack = UIManager.getInstance().goBack();
			// 如果返回false---首页
			if (!goBack) {
				Toast.makeText(getApplicationContext(), "是否退出界面",
						Toast.LENGTH_SHORT).show();
			}
			return goBack;
		}
		return super.onKeyDown(keyCode, event);
	}
}
