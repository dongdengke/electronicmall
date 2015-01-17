package cn.edu.bjtu.elctronicmall.view;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.ViewPagerAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;

/**
 * 商品详细信息界面，供用户加入购物车
 * 
 * @author dong
 * 
 */
public class GoodInfoView extends BaseView {

	private SQLiteDatabase database;
	private GoodDao dao;
	private Good good;
	private ArrayList<View> dots;
	private ViewPager pager;
	private ViewPagerAdapter adapter;
	private ArrayList<ImageView> icons;
	private Bitmap bm;

	public GoodInfoView(Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.good_info, null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READONLY);
		dao = new GoodDao(context);
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("加入购物车");
		TitleManager.getInstance().setTwoText("商品详情");
		init();
	}

	private void init() {
		int id = bundle.getInt("goodId");
		bundle = null;
		// int id = GloableParams.LOOKHISTORY.getFirst();
		good = dao.findGoodById(database, id);
		// icons = new ArrayList<ImageView>();
		// String pic = good.getPic();
		// // bm.setPixel(200, 150, 0);
		// for (int i = 0; i < 5; i++) {
		// bm = getBitmapByPath(pic);
		// ImageView imageView = new ImageView(context);
		// imageView.setImageBitmap(bm);
		// icons.add(imageView);
		// }
		// dots = new ArrayList<View>();
		// dots.add(showView.findViewById(R.id.dot_0));
		// dots.add(showView.findViewById(R.id.dot_1));
		// dots.add(showView.findViewById(R.id.dot_2));
		// dots.add(showView.findViewById(R.id.dot_3));
		// dots.add(showView.findViewById(R.id.dot_4));
		// pager = (ViewPager) showView.findViewById(R.id.good_viewpager);
		// adapter = new ViewPagerAdapter(icons, context);
		// pager.setAdapter(adapter);
		TextView textView = new TextView(context);
		LayoutParams layoutParams = textView.getLayoutParams();
		layoutParams = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		textView.setLayoutParams(layoutParams);
		textView.setBackgroundColor(Color.BLUE);
		textView.setText(good.getName());
		showView.addView(textView);
	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.GOOGINFOVIEW;
	}

	/**
	 * 根据路径转换为bitmap
	 * 
	 * @param view
	 */
	public Bitmap getBitmapByPath(String path) {
		Options opts = new Options();
		opts.inSampleSize = 1;
		return BitmapFactory.decodeFile(path, opts);
	}
}
