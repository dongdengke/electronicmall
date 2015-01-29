package cn.edu.bjtu.elctronicmall.view;

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
import cn.edu.bjtu.elctronicmall.bean.Address;
import cn.edu.bjtu.elctronicmall.bean.User;
import cn.edu.bjtu.elctronicmall.dao.AddressDao;
import cn.edu.bjtu.elctronicmall.dao.UserDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

/**
 * 添加地址
 * 
 * @author dong
 * 
 */
public class AddAddressView extends BaseView {

	private SQLiteDatabase database;
	private UserDao userDao;
	private AddressDao addressDao;
	private EditText ed_address_userId;
	private EditText ed_address_name;
	private EditText ed_address_phone;
	private EditText ed_address_zipecode;
	private EditText ed_address_status;
	private EditText ed_address_detailInfo;
	private User user;
	private Button btn_addrss_save;
	private Button btn_address_clear;

	public AddAddressView(Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View
				.inflate(context, R.layout.add_address, null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		addressDao = new AddressDao();
		TitleManager.getInstance().showOneText();
		TitleManager.getInstance().setOneText("添加地址");
		TitleManager.getInstance().setLeftButtonText("返回");
		init();
		TitleManager.getInstance().getBtn_name()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
	}

	private void init() {
		userDao = new UserDao();
		user = userDao.findByUserId(database, GlobalData.LOGIN_SUCCES);

		// 初始化控件
		ed_address_userId = (EditText) showView
				.findViewById(R.id.ed_address_userId);
		ed_address_name = (EditText) showView
				.findViewById(R.id.ed_address_name);
		ed_address_phone = (EditText) showView
				.findViewById(R.id.ed_address_phone);
		ed_address_zipecode = (EditText) showView
				.findViewById(R.id.ed_address_zipecode);
		ed_address_status = (EditText) showView
				.findViewById(R.id.ed_address_status);
		ed_address_detailInfo = (EditText) showView
				.findViewById(R.id.ed_address_detailInfo);
		ed_address_userId.setText(GlobalData.LOGIN_SUCCES + "");
		ed_address_name.setText(user.getUsername());
		ed_address_phone.setText(user.getPhone());
		btn_addrss_save = (Button) showView.findViewById(R.id.btn_addrss_save);
		btn_address_clear = (Button) showView
				.findViewById(R.id.btn_address_clear);
		btn_addrss_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phone = ed_address_phone.getText().toString().trim();
				String name = ed_address_name.getText().toString().trim();
				String zipecode = ed_address_zipecode.getText().toString()
						.trim();
				String statusStr = ed_address_status.getText().toString()
						.trim();
				String detailInfo = ed_address_detailInfo.getText().toString()
						.trim();
				if (TextUtils.isEmpty(phone)) {
					Toast.makeText(context, "请输入电话", 0).show();
					return;
				}
				if (TextUtils.isEmpty(name)) {
					Toast.makeText(context, "请输入收货人姓名", 0).show();
					return;
				}
				if (TextUtils.isEmpty(zipecode)) {
					Toast.makeText(context, "请输入邮编", 0).show();
					return;
				}
				if (TextUtils.isEmpty(statusStr)) {
					Toast.makeText(context, "请输入是否时默认地址", 0).show();
					return;
				}
				if (TextUtils.isEmpty(detailInfo)) {
					Toast.makeText(context, "请输入详细地址", 0).show();
				}
				int status = Integer.parseInt(statusStr);
				Address address = new Address();
				address.setUserId(GlobalData.LOGIN_SUCCES);
				address.setName(name);
				address.setPhone(phone);
				address.setZipecode(zipecode);
				address.setStatus(status);
				address.setDetailInfo(detailInfo);
				long rawId = addressDao.addAddress(database, address);
				if (rawId != -1) {
					Toast.makeText(context, "地址添加成功", 0).show();
					UIManager.getInstance()
							.changeVew(AddressView.class, bundle);

				} else {
					Toast.makeText(context, "地址添加失败", 0).show();

				}
			}
		});
		btn_address_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ed_address_name.setText("");
				ed_address_phone.setText("");
				ed_address_zipecode.setText("");
				ed_address_status.setText("");
				ed_address_detailInfo.setText("");
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
		return GlobalData.ADD_ADDRESSVIEW;
	}

}
