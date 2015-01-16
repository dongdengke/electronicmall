package cn.edu.bjtu.elctronicmall.manager;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.view.SecondView;

/**
 * 控制底部显示的管理者
 * 
 * @author dong
 * 
 */
public class BottomManager {
	private ImageView iv_home;
	private ImageView iv_search;
	private ImageView iv_cart;
	private ImageView iv_myaccount;
	private ImageView iv_more;
	private LinearLayout linerlayout_bottom;

	// 底部导航：单例设计
	private BottomManager() {

	}

	private static BottomManager manager;

	public static BottomManager getInstance() {
		if (manager == null) {
			manager = new BottomManager();
		}
		return manager;
	}

	public void init(Activity activity) {
		linerlayout_bottom = (LinearLayout) activity
				.findViewById(R.id.linerlayout_bottom);

		iv_home = (ImageView) activity.findViewById(R.id.iv_home);
		iv_search = (ImageView) activity.findViewById(R.id.iv_search);
		iv_cart = (ImageView) activity.findViewById(R.id.iv_cart);
		iv_myaccount = (ImageView) activity.findViewById(R.id.iv_myaccount);
		iv_more = (ImageView) activity.findViewById(R.id.iv_more);
		setOnClickListener();
	}

	/**
	 * 设置监听
	 */
	private void setOnClickListener() {
		// TODO Auto-generated method stub
		iv_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		iv_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		iv_cart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		iv_myaccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		iv_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SecondView secondView = new SecondView(getContext());
				UIManager.getInstance().changeView(secondView);
			}
		});
	}

	/**
	 * 显示底部
	 */
	public void showBottom() {
		linerlayout_bottom.setVisibility(View.VISIBLE);
	}

	/**
	 * 隐藏底部
	 */
	public void hiddenBottom() {
		linerlayout_bottom.setVisibility(View.GONE);
	}

	public Context getContext() {
		return linerlayout_bottom.getContext();
	}
}
