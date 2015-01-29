package cn.edu.bjtu.elctronicmall.view;

import java.security.NoSuchAlgorithmException;

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

/**
 * 用户注册
 * 
 * @author dong
 * 
 */
public class RegisterView extends BaseView {

	private EditText ed_username;
	private EditText ed_email;
	private EditText ed_phone;
	private EditText ed_password;
	private EditText ed_confirm_password;
	private String username;
	private String email;
	private String phone;
	private String password;
	private String confirm_password;
	private SQLiteDatabase database;
	private Button btn_register;
	private UserDao dao;

	public RegisterView(Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.register, null);
		TitleManager.getInstance().showOneText();
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
				SQLiteDatabase.OPEN_READWRITE);
		dao = new UserDao();

		try {
			init();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 初始化
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	private void init() throws NoSuchAlgorithmException {
		ed_username = (EditText) showView.findViewById(R.id.username);
		ed_email = (EditText) showView.findViewById(R.id.email);
		ed_phone = (EditText) showView.findViewById(R.id.phone);
		ed_password = (EditText) showView.findViewById(R.id.password);
		ed_confirm_password = (EditText) showView
				.findViewById(R.id.confirm_password);
		btn_register = (Button) showView.findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				username = ed_username.getText().toString();
				email = ed_email.getText().toString();
				phone = ed_phone.getText().toString();
				password = ed_password.getText().toString();
				confirm_password = ed_confirm_password.getText().toString();
				password = DigestUtils.md5Hex(password);
				confirm_password = DigestUtils.md5Hex(confirm_password);

				if (TextUtils.isEmpty(username)) {
					Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(email)) {
					Toast.makeText(context, "邮箱不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(phone)) {
					Toast.makeText(context, "电话号码不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(password)) {
					Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (TextUtils.isEmpty(confirm_password)) {
					Toast.makeText(context, "确认密码不能为空格", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				if (!password.equals(confirm_password)) {
					Toast.makeText(context, "两次输入的密码不一致，请重新输入",
							Toast.LENGTH_SHORT).show();
					return;
				}

				User user = new User();
				user.setEmail(email);
				user.setUsername(username);
				user.setPhone(phone);
				user.setPassword(password);
				boolean flag = dao.addUser(database, user);
				if (flag) {
					Toast.makeText(context, "恭喜您，注册成功", Toast.LENGTH_SHORT)
							.show();
					UIManager.getInstance().changeVew(LoginView.class, bundle);
				} else {
					Toast.makeText(context, "很遗憾，注册失败", Toast.LENGTH_SHORT)
							.show();
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
		return GlobalData.REGISTERVIEW;
	}

}
