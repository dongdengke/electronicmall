package cn.edu.bjtu.elctronicmall.view;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
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
import cn.edu.bjtu.elctronicmall.view.AddressView.AddressAdapter.ViewHolder;

/**
 * 地址管理列表
 * 
 * @author dong
 * 
 */
public class AddressView extends BaseView {

	private SQLiteDatabase database;
	private AddressDao addressDao;
	private List<Address> addressInfos;
	private ListView lv_address;
	private ViewHolder holder;

	public AddressView(Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.address, null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setTwoText("地址管理");
		TitleManager.getInstance().setRightButtonText("添加地址");
	}

	@Override
	public View getView(Context context) {
		TitleManager.getInstance().getBtn_name_right()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (GlobalData.LOGIN_SUCCES == -1) {

							UIManager.getInstance().changeVew(LoginView.class,
									bundle);
						} else {

							UIManager.getInstance().changeVew(
									AddAddressView.class, bundle);
						}
					}
				});
		lv_address = (ListView) showView.findViewById(R.id.lv_address);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		addressDao = new AddressDao();
		addressInfos = addressDao.queryCartByUserId(database,
				GlobalData.LOGIN_SUCCES);
		if (addressInfos.size() < 1) {
			Toast.makeText(context, "您还没有输入地址信息，请输入地址信息", Toast.LENGTH_SHORT)
					.show();
		} else {
			// 显示地址列表，共用户选择送货地址
			AddressAdapter addressAdapter = new AddressAdapter(context);
			lv_address.setAdapter(addressAdapter);
			lv_address.setOnItemClickListener(new OnItemClickListener() {
				int count = 0;

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Address address = addressInfos.get(position);
					System.out.println(address.getZipecode());
					if (count % 2 == 0) {
						holder.cb_address_default.setChecked(true);
					} else {
						holder.cb_address_default.setChecked(false);
					}
					count++;
				}
			});
		}
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.ADDRESSVIEW;
	}

	class AddressAdapter extends BaseAdapter {
		private Context context;
		private SQLiteDatabase database;

		public AddressAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			return addressInfos.size();
		}

		@Override
		public Object getItem(int position) {
			return addressInfos.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			holder = null;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(context, R.layout.address_item, null);
				holder = new ViewHolder();
				holder.tv_item_addresss_name = (TextView) view
						.findViewById(R.id.tv_item_addresss_name);
				holder.tv_item_addresss_zipecode = (TextView) view
						.findViewById(R.id.tv_item_addresss_zipecode);
				holder.tv_item_addresss_phone = (TextView) view
						.findViewById(R.id.tv_item_addresss_phone);
				holder.tv_item_addresss_detail = (TextView) view
						.findViewById(R.id.tv_item_addresss_detail);
				holder.cb_address_default = (CheckBox) view
						.findViewById(R.id.cb_address_default);
				view.setTag(holder);
			}
			// 设置数据
			Address address = addressInfos.get(position);
			UserDao userDao = new UserDao();
			database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
					SQLiteDatabase.OPEN_READWRITE);
			User user = userDao.findByUserId(database, GlobalData.LOGIN_SUCCES);
			if (user != null) {
				holder.tv_item_addresss_name.setText(user.getUsername());
				holder.tv_item_addresss_phone.setText(user.getPhone());
			}
			holder.tv_item_addresss_detail.setText(address.getDetailInfo());
			holder.tv_item_addresss_zipecode.setText(address.getZipecode());
			return view;
		}

		class ViewHolder {
			TextView tv_item_addresss_name;
			TextView tv_item_addresss_phone;
			TextView tv_item_addresss_detail;
			TextView tv_item_addresss_zipecode;
			CheckBox cb_address_default;
		}
	}
}
