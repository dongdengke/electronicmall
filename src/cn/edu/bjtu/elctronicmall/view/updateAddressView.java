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
public class updateAddressView extends BaseView {

	private SQLiteDatabase database;
	private UserDao userDao;
	private AddressDao addressDao;
	private EditText ed_update_address_userId;
	private EditText ed_update_address_name;
	private EditText ed_update_address_phone;
	private EditText ed_update_address_zipecode;
	private EditText ed_update_address_status;
	private EditText ed_update_address_detailInfo;
	private User user;
	private Button btn_update_addrsses;
	private Button btn_update_address_cancel;
	private int addressId;
	private Address address;

	public updateAddressView(Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.update_address,
				null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		addressDao = new AddressDao();
		TitleManager.getInstance().showOneText();
		TitleManager.getInstance().setOneText("修改地址");
		TitleManager.getInstance().setLeftButtonText("返回");
		init();
	}

	private void init() {
		userDao = new UserDao();
		user = userDao.findByUserId(database, GlobalData.LOGIN_SUCCES);
		addressId = bundle.getInt("id");
		address = addressDao.queryAddressByAddressId(database, addressId);
		// 初始化控件
		ed_update_address_userId = (EditText) showView
				.findViewById(R.id.ed_update_address_userId);
		ed_update_address_name = (EditText) showView
				.findViewById(R.id.ed_update_address_name);
		ed_update_address_phone = (EditText) showView
				.findViewById(R.id.ed_update_address_phone);
		ed_update_address_zipecode = (EditText) showView
				.findViewById(R.id.ed_update_address_zipecode);
		ed_update_address_status = (EditText) showView
				.findViewById(R.id.ed_update_address_status);
		ed_update_address_detailInfo = (EditText) showView
				.findViewById(R.id.ed_update_address_detailInfo);
		ed_update_address_userId.setText(GlobalData.LOGIN_SUCCES + "");
		ed_update_address_name.setText(user.getUsername());
		ed_update_address_phone.setText(user.getPhone());
		// 进入界面时，显示之前的信息
		System.out.println(address.getDetailInfo());
		ed_update_address_status.setText(address.getStatus() + "");
		ed_update_address_zipecode.setText(address.getZipecode());
		ed_update_address_detailInfo.setText(address.getDetailInfo());
		btn_update_addrsses = (Button) showView
				.findViewById(R.id.btn_update_addrsses);
		btn_update_address_cancel = (Button) showView
				.findViewById(R.id.btn_update_address_cancel);
		btn_update_addrsses.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phone = ed_update_address_phone.getText().toString()
						.trim();
				String zipecode = ed_update_address_zipecode.getText()
						.toString().trim();
				String statusStr = ed_update_address_status.getText()
						.toString().trim();
				String detailInfo = ed_update_address_detailInfo.getText()
						.toString().trim();
				if (TextUtils.isEmpty(phone)) {
					Toast.makeText(context, "请输入电话", 0).show();
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
				long rawId = addressDao.updateAddress(database,
						GlobalData.LOGIN_SUCCES, phone, detailInfo, zipecode,
						status, addressId);
				if (rawId != -1) {
					Toast.makeText(context, "地址修改成功", 0).show();
					UIManager.getInstance()
							.changeVew(AddressView.class, bundle);
				} else {
					Toast.makeText(context, "地址修改失败", 0).show();
				}
			}
		});
		btn_update_address_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UIManager.getInstance().goBack();
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
		return GlobalData.UPDATEADDRESS;
	}

}
