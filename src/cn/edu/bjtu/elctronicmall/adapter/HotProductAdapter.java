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

public class HotProductAdapter extends BaseAdapter {
	private Context context;
	private List<Good> goods;

	public HotProductAdapter(Context context, List<Good> goods) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.goods = goods;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return goods.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		ViewHolder holder = null;
		if (convertView != null) {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		} else {
			view = View.inflate(context, R.layout.hot_product_item, null);
			holder = new ViewHolder();
			holder.iv_hot_product_icon = (ImageView) view
					.findViewById(R.id.iv_hot_product_icon);
			holder.tv_hot_product_name = (TextView) view
					.findViewById(R.id.tv_hot_product_name);
			holder.tv_hot_product_price = (TextView) view
					.findViewById(R.id.tv_hot_product_price);
			view.setTag(holder);
		}
		Good good = goods.get(position);
		show(holder.iv_hot_product_icon, good.getPic());
		holder.tv_hot_product_name.setText(good.getName());
		holder.tv_hot_product_price.setText(good.getPrice() + "");
		return view;
	}

	private class ViewHolder {
		ImageView iv_hot_product_icon;
		TextView tv_hot_product_name;
		TextView tv_hot_product_price;
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
