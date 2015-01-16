package cn.edu.bjtu.elctronicmall;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
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
		TitleManager.getInstance().init(this);
		TitleManager.getInstance().showOneText();
		BottomManager.getInstance().init(this);
		BottomManager.getInstance().showBottom();
		addFirstView();
		// handler.sendEmptyMessageDelayed(10, 2000);
	}

	private void addFirstView() {
		FirstView firstView = new FirstView(this);
		view1 = firstView.getView(this);
		middle.addView(view1);
	}

}
