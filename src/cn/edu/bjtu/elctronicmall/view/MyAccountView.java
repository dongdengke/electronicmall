package cn.edu.bjtu.elctronicmall.view;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Collection;
import cn.edu.bjtu.elctronicmall.bean.User;
import cn.edu.bjtu.elctronicmall.dao.CollectionDao;
import cn.edu.bjtu.elctronicmall.dao.UserDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

public class MyAccountView extends BaseView {

	private Button btn_myebuy_login;
	private RelativeLayout rl_collection_not_empty;
	private LinearLayout rl_collection_empty;
	private SQLiteDatabase database;
	private CollectionDao collectionDao;
	private List<Collection> collections;
	private TextView tv_username;

	public MyAccountView(final Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.myaccount, null);
		btn_myebuy_login = (Button) showView
				.findViewById(R.id.btn_myebuy_login);

		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		rl_collection_not_empty = (RelativeLayout) showView
				.findViewById(R.id.rl_collection_not_empty);
		tv_username = (TextView) showView.findViewById(R.id.tv_username);

		btn_myebuy_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIManager.getInstance().changeVew(LoginView.class, bundle);

			}
		});
		// 代支付
		TextView tv_pay = (TextView) showView.findViewById(R.id.tv_pay);
		tv_pay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "目前此网站紧支持货到付款", Toast.LENGTH_LONG)
						.show();
			}
		});
		// 待收货
		RelativeLayout btn_wait_for_receipt = (RelativeLayout) showView
				.findViewById(R.id.btn_wait_for_receipt);
		btn_wait_for_receipt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "请关注物流信息，我们会随时为您提供最新物流信息", 0).show();
			}
		});
		// 待评价
		RelativeLayout btn_wait_for_evaluation = (RelativeLayout) showView
				.findViewById(R.id.btn_wait_for_evaluation);
		btn_wait_for_evaluation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "请收到货后给予我们评价哦", 0).show();
			}
		});
		// 我的订单
		TextView my_allorder = (TextView) showView
				.findViewById(R.id.my_allorder);
		my_allorder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (GlobalData.LOGIN_SUCCES == -1) {
					UIManager.getInstance().changeVew(LoginView.class, bundle);
				} else {
					UIManager.getInstance().changeVew(MyAllOrderView.class,
							bundle);
				}
			}
		});

		// 用户收藏
		RelativeLayout rl_collection = (RelativeLayout) showView
				.findViewById(R.id.rl_collection);

		rl_collection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (GlobalData.LOGIN_SUCCES == -1) {
					UIManager.getInstance().changeVew(LoginView.class, bundle);
				} else {
					UIManager.getInstance().changeVew(CollectionView.class,
							bundle);
				}
			}
		});
		// 浏览历史
		RelativeLayout rl_history = (RelativeLayout) showView
				.findViewById(R.id.rl_history);
		rl_history.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (GlobalData.LOGIN_SUCCES == -1) {
					UIManager.getInstance().changeVew(LoginView.class, bundle);
				} else {
					UIManager.getInstance()
							.changeVew(HistoryView.class, bundle);
				}
			}
		});
		// 地址管理
		RelativeLayout rl_addressmanager = (RelativeLayout) showView
				.findViewById(R.id.rl_addressmanager);
		// 进入地址页面
		rl_addressmanager.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (GlobalData.LOGIN_SUCCES == -1) {
					UIManager.getInstance().changeVew(LoginView.class, bundle);
				} else {
					UIManager.getInstance()
							.changeVew(AddressView.class, bundle);
				}
			}
		});
		// 进入用户反馈
		RelativeLayout rl_feedback = (RelativeLayout) showView
				.findViewById(R.id.rl_feedback);
		rl_feedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (GlobalData.LOGIN_SUCCES == -1) {
					UIManager.getInstance().changeVew(LoginView.class, bundle);
				} else {
					UIManager.getInstance().changeVew(FeedBackView.class,
							bundle);
				}
			}
		});
	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		if (GlobalData.LOGIN_SUCCES == -1) {
			btn_myebuy_login.setVisibility(View.VISIBLE);
			tv_username.setVisibility(View.GONE);
		} else {
			btn_myebuy_login.setVisibility(View.GONE);
			tv_username.setVisibility(View.VISIBLE);
			UserDao userDao = new UserDao();
			User user = userDao.findByUserId(database, GlobalData.LOGIN_SUCCES);
			System.out.println(user.getUsername());
			tv_username.setText(user.getUsername());
		}
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.MYACCOUNTVIEW;
	}

}
