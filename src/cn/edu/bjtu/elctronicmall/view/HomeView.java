package cn.edu.bjtu.elctronicmall.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.HomeCategoryAdapter;
import cn.edu.bjtu.elctronicmall.adapter.ViewPagerAdapter;
import cn.edu.bjtu.elctronicmall.global.GlobalData;

/**
 * 主界面的中间部分
 * 
 * @author dong
 * 
 */
public class HomeView extends BaseView {
	private View view;
	protected static final int UPDATEUI = 1;
	private TextView text_hint;
	private ArrayList<View> dots;
	// 存放图片的list
	private List<ImageView> icons;
	private ViewPagerAdapter adapter;
	private ViewPager pager;
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
	// 存放图片的id的数组
	private int imageIds[] = new int[] { R.drawable.t1, R.drawable.t2,
			R.drawable.t3, R.drawable.t4, R.drawable.t5 };
	private ListView lv_category;
	private List<Integer> categoryImageIds = new ArrayList<Integer>();
	// 各大种类的名称
	private String[] categoryNames = new String[] { "限时抢购", "促销快报", "新品上架",
			"热门单品", "推荐品牌", "二手商品", "火爆商品" };

	public HomeView(Context context) {
		super(context);
		showView = (LinearLayout) View
				.inflate(context, R.layout.mid_home, null);
		initView();
		lv_category = (ListView) showView.findViewById(R.id.lv_category);
		HomeCategoryAdapter categoryAdapter = new HomeCategoryAdapter(context,
				categoryImageIds, categoryNames);
		lv_category.setAdapter(categoryAdapter);
		lv_category.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					// UIManager.getInstance().changeVew(PanicBuyingView.class);
					break;
				case 1:
					// UIManager.getInstance().changeVew(SalesView.class);
					break;
				case 2:
					// Toast.makeText(context, "新品上架商品信息", Toast.LENGTH_SHORT)
					// .show();
					// UIManager.getInstance().changeVew(NewProductView.class);
					break;
				case 3:
					// Toast.makeText(context, "热门商品信息", Toast.LENGTH_SHORT)
					// .show();
					// UIManager.getInstance().changeVew(HotProductView.class);
					break;
				case 4:
					// Toast.makeText(context, "推荐品牌商品信息", Toast.LENGTH_SHORT)
					// .show();
					// UIManager.getInstance().changeVew(
					// RecommendedBrandView.class);
					break;
				case 5:
					// Toast.makeText(context, "二手商品信息", Toast.LENGTH_SHORT)
					// .show();
					break;
				case 6:
					// Toast.makeText(context, "暂时没有火爆商品信息", Toast.LENGTH_SHORT)
					// .show();
					break;

				default:
					break;
				}
			}
		});
		// 启动一个定时器，每隔两秒钟更换一张图片
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				currentPostion = (currentPostion + 1) % imageIds.length;
				Message message = Message.obtain();
				message.what = UPDATEUI;
				handler.sendEmptyMessage(message.what);
			}
		}, 0, 2000);
	}

	private void initView() {
		text_hint = (TextView) showView.findViewById(R.id.text_hint);
		icons = new ArrayList<ImageView>();
		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setBackgroundResource(imageIds[i]);
			icons.add(imageView);
		}
		// 初始化点
		dots = new ArrayList<View>();
		dots.add(showView.findViewById(R.id.dot_0));
		dots.add(showView.findViewById(R.id.dot_1));
		dots.add(showView.findViewById(R.id.dot_2));
		dots.add(showView.findViewById(R.id.dot_3));
		dots.add(showView.findViewById(R.id.dot_4));
		// 初始化种类中所包含的图片的id
		categoryImageIds.add(R.drawable.clock);
		categoryImageIds.add(R.drawable.sale);
		categoryImageIds.add(R.drawable.news);
		categoryImageIds.add(R.drawable.renmen);
		categoryImageIds.add(R.drawable.tuijian);
		categoryImageIds.add(R.drawable.ershou);
		categoryImageIds.add(R.drawable.huobao);
		adapter = new ViewPagerAdapter(icons, context);
		pager = (ViewPager) showView.findViewById(R.id.viewpager);
		// pager.setAdapter(adapter);
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
	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.HOMEVIEW;
	}

}
