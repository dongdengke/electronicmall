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
		rl_collection_empty = (LinearLayout) showView
				.findViewById(R.id.rl_collection_empty);
		rl_collection_not_empty = (RelativeLayout) showView
				.findViewById(R.id.rl_collection_not_empty);
		tv_username = (TextView) showView.findViewById(R.id.tv_username);
		if (GlobalData.LOGIN_SUCCES == -1) {
			btn_myebuy_login.setVisibility(View.VISIBLE);
			tv_username.setVisibility(View.GONE);
		} else {
			btn_myebuy_login.setVisibility(View.GONE);
			btn_myebuy_login.setEnabled(false);
			tv_username.setVisibility(View.VISIBLE);
			UserDao userDao = new UserDao();
			User user = userDao.findByUserId(database, GlobalData.LOGIN_SUCCES);
			System.out.println(user.getUsername());
			tv_username.setText(user.getUsername());
		}
		btn_myebuy_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIManager.getInstance().changeVew(LoginView.class, bundle);

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
					collectionDao = new CollectionDao();
					collections = collectionDao.queryByUserId(database,
							GlobalData.LOGIN_SUCCES);
					if (collections.size() <= 0) {
						// rl_collection_empty.setVisibility(View.VISIBLE);
						// rl_collection_not_empty.setVisibility(View.INVISIBLE);
						Toast.makeText(context, "收藏夹为空", 0).show();
					} else {
						// rl_collection_empty.setVisibility(View.INVISIBLE);
						// rl_collection_not_empty.setVisibility(View.VISIBLE);
					}
					UIManager.getInstance().changeVew(CollectionView.class,
							bundle);
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
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.MYACCOUNTVIEW;
	}

}
