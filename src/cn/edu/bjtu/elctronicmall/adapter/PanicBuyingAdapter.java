package cn.edu.bjtu.elctronicmall.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
public class PanicBuyingAdapter extends BaseAdapter {
	protected static final int TIME = 1;
	private Context context;
	private List<cn.edu.bjtu.elctronicmall.bean.Good> panicGoods;

	public PanicBuyingAdapter(List<Good> panicGoods, Context context) {
		this.context = context;
		this.panicGoods = panicGoods;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return panicGoods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return panicGoods.get(position);
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
			view = View.inflate(context, R.layout.panic_buying_item, null);
			holder = new ViewHolder();
			holder.iv_panic_icon = (ImageView) view
					.findViewById(R.id.iv_panic_icon);
			holder.tv_panic_name = (TextView) view
					.findViewById(R.id.tv_panic_name);
			holder.tv_panic_price = (TextView) view
					.findViewById(R.id.tv_panic_price);
			holder.tv_panic_newprice = (TextView) view
					.findViewById(R.id.tv_panic_newprice);
			holder.tv_panic_time = (TextView) view
					.findViewById(R.id.tv_panic_time);
			holder.btn_panic_buy = (Button) view
					.findViewById(R.id.btn_panic_buy);
			view.setTag(holder);
		}
		// 设置数据
		Good good = panicGoods.get(position);
		// 设置显示图片
		show(holder.iv_panic_icon, good.getPic());
		holder.tv_panic_name.setText(good.getName());
		holder.tv_panic_price.setText(good.getPrice() + "");
		holder.tv_panic_newprice.setText(good.getNewprice() + "");
		// 最后剩余时间采用随机数
		Random random = new Random();
		long remainTime = random.nextInt(200000000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:hh:mm:ss");
		String timeStr = dateFormat.format(new Date(remainTime));
		holder.tv_panic_time.setText(timeStr);
		return view;
	}

	class ViewHolder {
		ImageView iv_panic_icon;
		TextView tv_panic_name;
		TextView tv_panic_price;
		TextView tv_panic_newprice;
		TextView tv_panic_time;
		Button btn_panic_buy;
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
