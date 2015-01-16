package cn.edu.bjtu.elctronicmall.view;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class FirstView extends BaseView {

	private TextView textView;

	public FirstView(Context context) {
		super(context);
		// 在这里初始化，因为每个secondview只能创建一次，所以，secondview中的布局只需要初始化一次就可以了
		init(context);
	}

	/**
	 * 这个界面的初始化
	 */
	private void init(Context context) {
		textView = new TextView(context);
		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		textView.setLayoutParams(layoutParams);
		textView.setBackgroundColor(Color.YELLOW);
		textView.setText("这是first界面");
	}

	public View getView(Context context) {
		// TODO Auto-generated method stub
		return textView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 1;
	}
}
