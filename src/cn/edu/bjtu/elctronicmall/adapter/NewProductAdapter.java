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

public class NewProductAdapter extends BaseAdapter {
	private Context context;
	private List<Good> newproducts;

	public NewProductAdapter(Context context, List<Good> newproducts) {
		this.context = context;
		this.newproducts = newproducts;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newproducts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return newproducts.get(position);
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
			view = View.inflate(context, R.layout.new_product_item, null);
			holder = new ViewHolder();
			holder.tv_xinpin_name = (TextView) view
					.findViewById(R.id.tv_xinpin_name);
			holder.iv_xinpin_icon = (ImageView) view
					.findViewById(R.id.iv_xinpin_icon);
			holder.tv_xinpin_date = (TextView) view
					.findViewById(R.id.tv_xinpin_date);
			view.setTag(holder);
		}
		Good good = newproducts.get(position);
		holder.tv_xinpin_name.setText(good.getName());
		// 设置显示的图标
		showImage(holder.iv_xinpin_icon, good.getPic());
		holder.tv_xinpin_date.setText(good.getShangjiadate());
		// holder.iv_xinpin_icon.setim
		return view;
	}

	/**
	 * 根据路径显示图片
	 * 
	 * @param iv_xinpin_icon
	 * @param pic
	 */
	private void showImage(ImageView iv_xinpin_icon, String pic) {
		Options opt = new Options();
		opt.inSampleSize = 1;
		Bitmap bm = BitmapFactory.decodeFile(pic, opt);
		iv_xinpin_icon.setImageBitmap(bm);
	}

	private class ViewHolder {
		TextView tv_xinpin_name;
		ImageView iv_xinpin_icon;
		TextView tv_xinpin_date;
	}

}
