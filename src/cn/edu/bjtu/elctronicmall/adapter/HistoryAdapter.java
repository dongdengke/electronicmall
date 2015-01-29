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
 * 收藏夹列表的适配器
 * 
 * @author dong
 * 
 */
public class HistoryAdapter extends BaseAdapter {
	protected static final int TIME = 1;
	private Context context;
	private List<Good> historyGoods;

	public HistoryAdapter(List<Good> historyGoods, Context context) {
		this.context = context;
		this.historyGoods = historyGoods;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return historyGoods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return historyGoods.get(position);
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
			view = View.inflate(context, R.layout.history_item, null);
			holder = new ViewHolder();
			holder.iv_history_icon = (ImageView) view
					.findViewById(R.id.iv_history_icon);
			holder.tv_history_name = (TextView) view
					.findViewById(R.id.tv_history_name);
			holder.tv_history_price = (TextView) view
					.findViewById(R.id.tv_history_price);
			holder.tv_history_newprice = (TextView) view
					.findViewById(R.id.tv_history_newprice);
			view.setTag(holder);
		}
		// 设置数据
		final Good good = historyGoods.get(position);
		// 设置显示图片
		show(holder.iv_history_icon, good.getPic());
		holder.tv_history_name.setText(good.getName());
		holder.tv_history_price.setText(good.getPrice() + "");
		holder.tv_history_newprice.setText(good.getNewprice() + "");
		// 点击之后进入商品的详细信息界面，让用户加入购物车
		return view;
	}

	class ViewHolder {
		ImageView iv_history_icon;
		TextView tv_history_name;
		TextView tv_history_price;
		TextView tv_history_newprice;
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
