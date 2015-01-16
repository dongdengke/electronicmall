package cn.edu.bjtu.elctronicmall.manager;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.R;

/**
 * 控制顶部显示的管理者
 * 
 * @author dong
 * 
 */
public class TitleManager {
	// 顶部导航，单例
	private TitleManager() {

	}

	private static TitleManager manager;
	private RelativeLayout relativeLayout_home;
	private RelativeLayout relativeLayout_one;
	private RelativeLayout relativeLayout_two;
	private Button btn_name;
	private Button btn_name_left;
	private Button btn_name_right;
	private TextView tv_one_name;
	private TextView tv_two_name;

	public static TitleManager getInstance() {
		if (manager == null) {
			manager = new TitleManager();
		}
		return manager;
	}

	/**
	 * 初始化
	 * 
	 * @param activity
	 */
	public void init(Activity activity) {
		relativeLayout_home = (RelativeLayout) activity
				.findViewById(R.id.relativeLayout_home);
		relativeLayout_one = (RelativeLayout) activity
				.findViewById(R.id.relativeLayout_one);
		relativeLayout_two = (RelativeLayout) activity
				.findViewById(R.id.relativeLayout_two);
		btn_name = (Button) activity.findViewById(R.id.btn_name);
		btn_name_left = (Button) activity.findViewById(R.id.btn_name_left);
		btn_name_right = (Button) activity.findViewById(R.id.btn_name_right);
		tv_one_name = (TextView) activity.findViewById(R.id.tv_one_name);
		tv_two_name = (TextView) activity.findViewById(R.id.tv_two_name);
		setOnclickListener();
	}

	/**
	 * 设置监听
	 */
	private void setOnclickListener() {
		// TODO Auto-generated method stub
		btn_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		btn_name_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		btn_name_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 显示主界面的头部
	 */
	public void showHome() {
		initTitle();
		relativeLayout_home.setVisibility(View.VISIBLE);
	}

	/**
	 * 显示只含有一个文本的头部信息
	 */
	public void showOneText() {
		initTitle();
		relativeLayout_one.setVisibility(View.VISIBLE);
	}

	/**
	 * 显示只含有二个文本的头部信息
	 */
	public void showTwoText() {
		initTitle();
		relativeLayout_two.setVisibility(View.VISIBLE);
	}

	public void initTitle() {
		relativeLayout_home.setVisibility(View.GONE);
		relativeLayout_one.setVisibility(View.GONE);
		relativeLayout_two.setVisibility(View.GONE);
	}

	public TextView getTv_one_name() {
		return tv_one_name;
	}

	public void setTv_one_name(TextView tv_one_name) {
		this.tv_one_name = tv_one_name;
	}

	public TextView getTv_two_name() {
		return tv_two_name;
	}

	public void setTv_two_name(TextView tv_two_name) {
		this.tv_two_name = tv_two_name;
	}

}
