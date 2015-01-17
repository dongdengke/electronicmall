package cn.edu.bjtu.elctronicmall.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Good;

/**
 * 限时抢购列表的适配器
 * 
 * @author dong
 * 
 */
public class SaleAdapter extends BaseAdapter {
	protected static final int TIME = 1;
	private Context context;
	private List<Good> saleGoods;

	public SaleAdapter(List<Good> saleGoods, Context context) {
		this.context = context;
		this.saleGoods = saleGoods;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return saleGoods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return saleGoods.get(position);
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
			view = View.inflate(context, R.layout.sale_item, null);
			holder = new ViewHolder();
			holder.iv_sale_icon = (ImageView) view
					.findViewById(R.id.iv_sale_icon);
			holder.tv_sale_name = (TextView) view
					.findViewById(R.id.tv_sale_name);
			holder.tv_sale_price = (TextView) view
					.findViewById(R.id.tv_sale_price);
			holder.tv_sale_newprice = (TextView) view
					.findViewById(R.id.tv_sale_newprice);
			holder.btn_sale_buy = (Button) view.findViewById(R.id.btn_sale_buy);
			view.setTag(holder);
		}
		// 设置数据
		Good good = saleGoods.get(position);
		// 设置显示图片
		show(holder.iv_sale_icon, good.getPic());
		holder.tv_sale_name.setText(good.getName());
		holder.tv_sale_price.setText(good.getPrice() + "");
		holder.tv_sale_newprice.setText(good.getNewprice() + "");
		return view;
	}

	class ViewHolder {
		ImageView iv_sale_icon;
		TextView tv_sale_name;
		TextView tv_sale_price;
		TextView tv_sale_newprice;
		Button btn_sale_buy;
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
