package cn.edu.bjtu.elctronicmall.view;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Address;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.bean.Orderlist;
import cn.edu.bjtu.elctronicmall.dao.AddressDao;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.dao.OrderListDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;
import cn.edu.bjtu.elctronicmall.view.MyAllOrderView.MyAllOrderAdapter.ViewHolder;

public class MyAllOrderView extends BaseView {

	private SQLiteDatabase database;
	private OrderListDao orderListDao;
	private List<Orderlist> orderlists;
	private ListView lv_my_allorder;
	private MyAllOrderAdapter myAllOrderAdapter;
	private ViewHolder holder;

	public MyAllOrderView(Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.my_all_order,
				null);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setTwoText("我的订单");
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("去逛逛");
		TitleManager.getInstance().getBtn_name_left()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		TitleManager.getInstance().getBtn_name_right()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		orderListDao = new OrderListDao();
		orderlists = orderListDao.queryOrder(database, GlobalData.LOGIN_SUCCES);
		lv_my_allorder = (ListView) showView.findViewById(R.id.lv_my_allorder);
		myAllOrderAdapter = new MyAllOrderAdapter(orderlists, context);
		lv_my_allorder.setAdapter(myAllOrderAdapter);
		lv_my_allorder.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Orderlist orderlist = orderlists.get(position);
				bundle.putInt("orderAddressId", orderlist.getAddressId());
				bundle.putInt("orderGoodId", orderlist.getGoodId());
				bundle.putString("orderno", orderlist.getOrderno());
				bundle.putInt("cartId", orderlist.getCartId());
				bundle.putInt("goodId", orderlist.getGoodId());
				UIManager.getInstance().changeVew(OrderItemView.class, bundle);
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
		return GlobalData.MYALLORDERVIEW;
	}

	class MyAllOrderAdapter extends BaseAdapter {
		private Context context;
		private List<Orderlist> orderlists;
		private GoodDao goodDao;
		private AddressDao addressDao;
		private SQLiteDatabase database;

		public MyAllOrderAdapter(List<Orderlist> orderlists, Context context) {
			this.context = context;
			this.orderlists = orderlists;
			database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
					SQLiteDatabase.OPEN_READWRITE);
			goodDao = new GoodDao(context);
			addressDao = new AddressDao();
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return orderlists.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return orderlists.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = null;
			holder = null;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(context, R.layout.my_all_order_item, null);
				holder = new ViewHolder();
				holder.iv_good_icon = (ImageView) view
						.findViewById(R.id.iv_good_icon);
				holder.tv_allorder_orderno_name = (TextView) view
						.findViewById(R.id.tv_allorder_orderno_name);
				holder.tv_allorder_goodname = (TextView) view
						.findViewById(R.id.tv_allorder_goodname);
				holder.tv_receive_good_name = (TextView) view
						.findViewById(R.id.tv_receive_good_name);
				view.setTag(holder);
			}
			// 设置数据
			final Orderlist orderlist = orderlists.get(position);
			int goodId = orderlist.getGoodId();
			int addressId = orderlist.getAddressId();
			Address address = addressDao.queryAddressByAddressId(database,
					addressId);
			Good good = goodDao.findGoodById(database, goodId);
			// 设置显示图片
			show(holder.iv_good_icon, good.getPic());
			holder.tv_allorder_orderno_name.setText(orderlist.getOrderno());
			holder.tv_allorder_goodname.setText(good.getName());
			holder.tv_receive_good_name.setText(address.getName());
			// 点击之后进入商品的详细信息界面，让用户加入购物车
			return view;
		}

		class ViewHolder {
			ImageView iv_good_icon;
			TextView tv_allorder_orderno_name;
			TextView tv_allorder_goodname;
			TextView tv_receive_good_name;
		}

		/**
		 * 显示sd中的图片
		 * 
		 * @param view
		 */
		public void show(ImageView iv, String path) {
			Options opts = new Options();
			opts.inSampleSize = 1;
			Bitmap bm = BitmapFactory.decodeFile(path, opts);
			iv.setImageBitmap(bm);
		}

	}

}
