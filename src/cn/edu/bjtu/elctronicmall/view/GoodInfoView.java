package cn.edu.bjtu.elctronicmall.view;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
	protected static final int UPDATEUI = 1;
	private ViewPagerAdapter adapter;
	private ArrayList<ImageView> icons;
	private Bitmap bm;
	// 上一次的位置
	private int oldPosition = 0;
	// 当前正在显示的位置
	private int currentPostion = 0;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPDATEUI:
				// 设置当前页面
				pager.setCurrentItem(currentPostion);
				break;

			default:
				break;
			}
		};
	};

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
		int id = GloableParams.LOOKHISTORY.getFirst();
		good = dao.findGoodById(database, id);
		icons = new ArrayList<ImageView>();
		String pic = good.getPic();
		// bm.setPixel(200, 150, 0);
		for (int i = 0; i < 5; i++) {
			bm = getBitmapByPath(pic);
			ImageView imageView = new ImageView(context);
			imageView.setImageBitmap(bm);
			icons.add(imageView);
		}
		dots = new ArrayList<View>();
		dots.add(showView.findViewById(R.id.dot_0));
		dots.add(showView.findViewById(R.id.dot_1));
		dots.add(showView.findViewById(R.id.dot_2));
		dots.add(showView.findViewById(R.id.dot_3));
		dots.add(showView.findViewById(R.id.dot_4));
		pager = (ViewPager) showView.findViewById(R.id.good_viewpager);
		adapter = new ViewPagerAdapter(icons, context);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			/**
			 * 当图片选中的时候调用
			 */
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				dots.get(oldPosition).setBackgroundResource(
						R.drawable.dot_normal);
				dots.get(position)
						.setBackgroundResource(R.drawable.dot_focused);
				oldPosition = position;
				currentPostion = position;
			}

			/**
			 * 当滑动时调用
			 */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			/**
			 * 滑动状态改变时调用
			 */
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		// 启动一个定时器，每隔两秒钟更换一张图片
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				currentPostion = (currentPostion + 1) % icons.size();
				Message message = Message.obtain();
				message.what = UPDATEUI;
				handler.sendEmptyMessage(message.what);
			}
		}, 0, 2000);

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
