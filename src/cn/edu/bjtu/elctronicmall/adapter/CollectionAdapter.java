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
public class CollectionAdapter extends BaseAdapter {
	protected static final int TIME = 1;
	private Context context;
	private List<Good> collectionGoods;

	public CollectionAdapter(List<Good> collectionGoods, Context context) {
		this.context = context;
		this.collectionGoods = collectionGoods;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return collectionGoods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return collectionGoods.get(position);
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
			view = View.inflate(context, R.layout.collection_item, null);
			holder = new ViewHolder();
			holder.iv_collection_icon = (ImageView) view
					.findViewById(R.id.iv_collection_icon);
			holder.tv_collection_name = (TextView) view
					.findViewById(R.id.tv_collection_name);
			holder.tv_collection_price = (TextView) view
					.findViewById(R.id.tv_collection_price);
			holder.tv_collection_newprice = (TextView) view
					.findViewById(R.id.tv_collection_newprice);
			view.setTag(holder);
		}
		// 设置数据
		final Good good = collectionGoods.get(position);
		// 设置显示图片
		show(holder.iv_collection_icon, good.getPic());
		holder.tv_collection_name.setText(good.getName());
		holder.tv_collection_price.setText(good.getPrice() + "");
		holder.tv_collection_newprice.setText(good.getNewprice() + "");
		// 点击之后进入商品的详细信息界面，让用户加入购物车
		return view;
	}

	class ViewHolder {
		ImageView iv_collection_icon;
		TextView tv_collection_name;
		TextView tv_collection_price;
		TextView tv_collection_newprice;
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
