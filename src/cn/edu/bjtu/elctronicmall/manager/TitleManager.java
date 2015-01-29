package cn.edu.bjtu.elctronicmall.manager;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.global.GlobalData;

/**
 * 控制顶部显示的管理者
 * 
 * @author dong
 * 
 */
public class TitleManager implements Observer {
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

	/**
	 * 一个头部导航都不显示
	 */
	public void showNoneTitle() {
		initTitle();
	}

	/**
	 * 设置有一个按钮的头部导航的按钮的text
	 * 
	 * @param info
	 */
	public void setButtonText(String info) {
		btn_name.setText(info);
	}

	public Button getBtn_name() {
		return btn_name;
	}

	public Button getBtn_name_left() {
		return btn_name_left;
	}

	public Button getBtn_name_right() {
		return btn_name_right;
	}

	/**
	 * 设置有2个按钮的头部导航的按钮的text
	 * 
	 * @param info
	 */
	public void setLeftButtonText(String info) {
		btn_name_left.setText(info);
	}

	/**
	 * 设置有2个按钮的头部导航的按钮的text
	 * 
	 * @param info
	 */
	public void setRightButtonText(String info) {
		btn_name_right.setText(info);
	}

	/**
	 * 设置顶部有一个按钮导航的名称
	 * 
	 * @param info
	 */
	public void setOneText(String info) {
		tv_one_name.setText(info);
	}

	/**
	 * 设置顶部有两个按钮导航的名称
	 * 
	 * @param info
	 */
	public void setTwoText(String info) {
		tv_two_name.setText(info);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if (data != null) {
			if (StringUtils.isNumeric(data.toString())) {
				int id = Integer.parseInt(data.toString());
				switch (id) {
				case GlobalData.HOMEVIEW:
					showHome();
					break;
				case GlobalData.PANICBUYINGVIEW:
				case GlobalData.SALESVIEW:
				case GlobalData.NEWPRODUCTVIEW:
				case GlobalData.HOTPRODUCTVIEW:
				case GlobalData.MOREVIEW:
				case GlobalData.ADD_ADDRESSVIEW:
				case GlobalData.UPDATEADDRESS:
				case GlobalData.SELECT_ADDRESSVIEW:
				case GlobalData.SEARCHVIEW:
				case GlobalData.FEEDBACK:
					showOneText();
					break;
				case GlobalData.GOOGINFOVIEW:
				case GlobalData.CARTVIEW:
				case GlobalData.ADDRESSVIEW:
				case GlobalData.ORDERVIEW:
				case GlobalData.COLLECTIONVIEW:
					showTwoText();
					break;
				case GlobalData.MYACCOUNTVIEW:
					showNoneTitle();
					break;

				default:
					break;
				}
			}
		}
	}
}
