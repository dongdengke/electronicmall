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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.ViewPagerAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

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
	private Button btn_add_cart;
	private Button btn_collection;
	private TextView tv_good_name;
	private TextView tv_good_price;
	private TextView tv_good_newprice;
	private EditText ed_good_count;
	private ImageView iv_score_1;
	private ImageView iv_score_2;
	private ImageView iv_score_3;
	private ImageView iv_score_4;
	private ImageView iv_score_5;
	private TextView tv_good_location;
	private TextView tv_good_remain;
	private TextView tv_good_comment;

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
		tv_good_name = (TextView) showView.findViewById(R.id.tv_good_name);
		tv_good_price = (TextView) showView.findViewById(R.id.tv_good_price);
		tv_good_newprice = (TextView) showView
				.findViewById(R.id.tv_good_newprice);

		iv_score_1 = (ImageView) showView.findViewById(R.id.iv_score_1);
		iv_score_2 = (ImageView) showView.findViewById(R.id.iv_score_2);
		iv_score_3 = (ImageView) showView.findViewById(R.id.iv_score_3);
		iv_score_4 = (ImageView) showView.findViewById(R.id.iv_score_4);
		iv_score_5 = (ImageView) showView.findViewById(R.id.iv_score_5);
		tv_good_location = (TextView) showView
				.findViewById(R.id.tv_good_location);
		tv_good_remain = (TextView) showView.findViewById(R.id.tv_good_remain);
		tv_good_comment = (TextView) showView
				.findViewById(R.id.tv_good_comment);
		tv_good_name.setText(good.getName());
		tv_good_price.setText(good.getPrice() + "");
		tv_good_newprice.setText(good.getNewprice() + "");
		tv_good_location.setText(good.getLocation());
		tv_good_remain.setText(good.getInventory() + "");
		int score = good.getScore();
		if (score < 5) {
			iv_score_1.setImageResource(R.drawable.score_off);
			iv_score_2.setImageResource(R.drawable.score_off);
			iv_score_3.setImageResource(R.drawable.score_off);
			iv_score_4.setImageResource(R.drawable.score_off);
			iv_score_5.setImageResource(R.drawable.score_off);
		} else if (score >= 5 && score < 10) {
			iv_score_1.setImageResource(R.drawable.score_on);
			iv_score_2.setImageResource(R.drawable.score_off);
			iv_score_3.setImageResource(R.drawable.score_off);
			iv_score_4.setImageResource(R.drawable.score_off);
			iv_score_5.setImageResource(R.drawable.score_off);
		} else if (score >= 10 && score < 15) {
			iv_score_1.setImageResource(R.drawable.score_on);
			iv_score_2.setImageResource(R.drawable.score_on);
			iv_score_3.setImageResource(R.drawable.score_off);
			iv_score_4.setImageResource(R.drawable.score_off);
			iv_score_5.setImageResource(R.drawable.score_off);
		} else if (score >= 15 && score < 20) {
			iv_score_1.setImageResource(R.drawable.score_on);
			iv_score_2.setImageResource(R.drawable.score_on);
			iv_score_3.setImageResource(R.drawable.score_on);
			iv_score_4.setImageResource(R.drawable.score_off);
			iv_score_5.setImageResource(R.drawable.score_off);
		} else if (score >= 20 && score < 25) {
			iv_score_1.setImageResource(R.drawable.score_on);
			iv_score_2.setImageResource(R.drawable.score_on);
			iv_score_3.setImageResource(R.drawable.score_on);
			iv_score_4.setImageResource(R.drawable.score_on);
			iv_score_5.setImageResource(R.drawable.score_off);
		} else if (score >= 25) {
			iv_score_1.setImageResource(R.drawable.score_on);
			iv_score_2.setImageResource(R.drawable.score_on);
			iv_score_3.setImageResource(R.drawable.score_on);
			iv_score_4.setImageResource(R.drawable.score_on);
			iv_score_5.setImageResource(R.drawable.score_on);
		}
		// 处理按钮的点击事件
		btn_add_cart = (Button) showView.findViewById(R.id.btn_add_cart);
		btn_collection = (Button) showView.findViewById(R.id.btn_collection);
		btn_add_cart.setOnClickListener(new OnClickListener() {

			private int count;

			@Override
			public void onClick(View v) {
				if (GlobalData.LOGIN_SUCCES == -1) {
					// 进入登陆界面
					UIManager.getInstance().changeVew(LoginView.class, bundle);
				} else {
					// 进行购物车相关的业务逻辑
					ed_good_count = (EditText) showView
							.findViewById(R.id.ed_good_count);
					String counStr = ed_good_count.getText().toString().trim();
					try {
						count = Integer.parseInt(counStr);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("异常");
					}
					if (count <= 0) {
						Toast.makeText(context, "请至少选择一件商品", Toast.LENGTH_SHORT)
								.show();
						return;
					}
					if (count > good.getInventory()) {
						Toast.makeText(context, "库存不足，请稍后重试",
								Toast.LENGTH_SHORT).show();
						return;
					}
				}
			}
		});
		btn_collection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

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
