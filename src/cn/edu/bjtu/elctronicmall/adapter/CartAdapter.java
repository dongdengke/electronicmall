package cn.edu.bjtu.elctronicmall.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Good;

/**
 * 购物车的适配器
 * 
 * @author dong
 * 
 */
public class CartAdapter extends BaseAdapter {
	private Context context;
	private List<Good> cartGoods;
	private int totalCount;

	public CartAdapter(List<Good> cartGoods, Context context, int totalCount) {
		this.context = context;
		this.cartGoods = cartGoods;
		this.totalCount = totalCount;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cartGoods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cartGoods.get(position);
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
		ViewHolder holder = null;
		if (convertView != null) {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		} else {
			view = View.inflate(context, R.layout.cart_item, null);
			holder = new ViewHolder();
			holder.iv_good_icon = (ImageView) view
					.findViewById(R.id.iv_cart_good_icon);
			holder.tv_good_name = (TextView) view
					.findViewById(R.id.tv_cart_good_name);
			holder.tv_total_count = (TextView) view
					.findViewById(R.id.tv_good_count);
			holder.tv_good_price = (TextView) view
					.findViewById(R.id.tv_good_price);
			holder.tv_small_totalmoney = (TextView) view
					.findViewById(R.id.tv_good_small_total);
			view.setTag(holder);
		}
		// 设置数据
		final Good good = cartGoods.get(position);
		// 设置显示图片
		show(holder.iv_good_icon, good.getPic());
		holder.tv_good_name.setText(good.getName());
		holder.tv_total_count.setText(totalCount + "");
		holder.tv_good_price.setText(good.getNewprice() + "");
		double price = good.getNewprice();
		holder.tv_small_totalmoney.setText(totalCount * price + "");
		return view;
	}

	class ViewHolder {
		ImageView iv_good_icon;
		TextView tv_good_name;
		TextView tv_total_count;
		TextView tv_good_price;
		TextView tv_small_totalmoney;
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
