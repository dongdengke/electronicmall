package cn.edu.bjtu.elctronicmall;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RelativeLayout;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;
import cn.edu.bjtu.elctronicmall.utils.PromptManager;
import cn.edu.bjtu.elctronicmall.view.HomeView;

public class HomeActivity extends Activity {
	private RelativeLayout middle;

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
		TitleManager.getInstance().showHome();
		BottomManager.getInstance().init(this);
		BottomManager.getInstance().showBottom();
		Bundle bundle = new Bundle();
		UIManager.getInstance().changeVew(HomeView.class, bundle);
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
				PromptManager.showExitSystem(this);
			}
			return goBack;
		}
		return super.onKeyDown(keyCode, event);
	}
}
