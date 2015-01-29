package cn.edu.bjtu.elctronicmall.view;

import org.apache.commons.codec.digest.DigestUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.User;
import cn.edu.bjtu.elctronicmall.dao.UserDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

public class LoginView extends BaseView {

	private Button user_regist;
	private Button user_login;
	private EditText user_login_username;
	private EditText user_login_password;
	private String username;
	private String password;
	private SQLiteDatabase database;
	private UserDao dao;

	public LoginView(Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.login, null);
		user_regist = (Button) showView.findViewById(R.id.user_regist);
		user_login = (Button) showView.findViewById(R.id.user_login);
		user_login_username = (EditText) showView
				.findViewById(R.id.user_login_username);
		user_login_password = (EditText) showView
				.findViewById(R.id.user_login_password);

		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().setOneText("注册");
		TitleManager.getInstance().getBtn_name()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		BottomManager.getInstance().showBottom();
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READONLY);
		dao = new UserDao();
		initView();

	}

	/**
	 * 初始化
	 */
	private void initView() {
		// TODO Auto-generated method stub
		user_regist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIManager.getInstance().changeVew(RegisterView.class, bundle);
			}
		});
		/**
		 * 用户登陆的业务
		 */
		user_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username = user_login_username.getText().toString().trim();
				password = user_login_password.getText().toString().trim();
				// 根据用户名和密码去查询数据库
				if (TextUtils.isEmpty(username)) {
					Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(password)) {
					Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
					return;
				}
				password = DigestUtils.md5Hex(password.getBytes());
				User existUser = dao.login(database, username, password);
				if (existUser == null) {
					Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show();
				} else {
					// 记录登陆成功
					GlobalData.LOGIN_SUCCES = existUser.getId();
					Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
					UIManager.getInstance().goBack();
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
		return GlobalData.LOGINVIEW;
	}

}
