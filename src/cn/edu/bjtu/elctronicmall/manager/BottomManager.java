package cn.edu.bjtu.elctronicmall.manager;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Cart;
import cn.edu.bjtu.elctronicmall.dao.CartDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.view.CartView;
import cn.edu.bjtu.elctronicmall.view.HomeView;
import cn.edu.bjtu.elctronicmall.view.LoginView;
import cn.edu.bjtu.elctronicmall.view.MoreView;
import cn.edu.bjtu.elctronicmall.view.MyAccountView;
import cn.edu.bjtu.elctronicmall.view.SearchView;

/**
 * 控制底部显示的管理者
 * 
 * @author dong
 * 
 */
public class BottomManager implements Observer {
	private ImageView iv_home;
	private ImageView iv_search;
	private ImageView iv_cart;
	private ImageView iv_myaccount;
	private ImageView iv_more;
	private LinearLayout linerlayout_bottom;

	// 底部导航：单例设计
	private BottomManager() {

	}

	private static BottomManager manager;

	public static BottomManager getInstance() {
		if (manager == null) {
			manager = new BottomManager();
		}
		return manager;
	}

	public void init(Activity activity) {
		linerlayout_bottom = (LinearLayout) activity
				.findViewById(R.id.linerlayout_bottom);

		iv_home = (ImageView) activity.findViewById(R.id.iv_home);
		iv_home.setImageResource(R.drawable.tab_home_pressed);
		iv_search = (ImageView) activity.findViewById(R.id.iv_search);
		iv_cart = (ImageView) activity.findViewById(R.id.iv_cart);
		iv_myaccount = (ImageView) activity.findViewById(R.id.iv_myaccount);
		iv_more = (ImageView) activity.findViewById(R.id.iv_more);
		setOnClickListener();
	}

	/**
	 * 设置监听
	 */
	private void setOnClickListener() {
		// TODO Auto-generated method stub
		final Bundle bundle = new Bundle();
		iv_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showHome();
				UIManager.getInstance().changeVew(HomeView.class, bundle);
			}
		});
		iv_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSearch();
				UIManager.getInstance().changeVew(SearchView.class, bundle);
			}
		});
		iv_cart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (GlobalData.LOGIN_SUCCES == -1) {
					showMyAccount();
					UIManager.getInstance().changeVew(LoginView.class, bundle);
					return;
				}
				CartDao cartDao = new CartDao();
				SQLiteDatabase database = SQLiteDatabase.openDatabase(
						GloableParams.PATH, null, SQLiteDatabase.OPEN_READONLY);
				List<Cart> cartInfos = cartDao.queryCartByUserId(database,
						GlobalData.LOGIN_SUCCES);
				if (cartInfos.size() >= 0) {
					showCart();
					UIManager.getInstance().changeVew(CartView.class, bundle);
				}
			}
		});
		iv_myaccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showMyAccount();
				UIManager.getInstance().changeVew(MyAccountView.class, bundle);
			}
		});
		iv_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 每次点击都会创建secondView实例，改用反射来完成
				showMore();
				UIManager.getInstance().changeVew(MoreView.class, bundle);
			}
		});
	}

	/**
	 * 显示底部
	 */
	public void showBottom() {
		linerlayout_bottom.setVisibility(View.VISIBLE);
	}

	/**
	 * 隐藏底部
	 */
	public void hiddenBottom() {
		linerlayout_bottom.setVisibility(View.GONE);
	}

	public Context getContext() {
		return linerlayout_bottom.getContext();
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if (data != null) {
			if (StringUtils.isNumeric(data.toString())) {
				int id = Integer.parseInt(data.toString());
				switch (id) {
				case GlobalData.HOMEVIEW:
				case GlobalData.PANICBUYINGVIEW:
				case GlobalData.SALESVIEW:
				case GlobalData.NEWPRODUCTVIEW:
				case GlobalData.HOTPRODUCTVIEW:
					showHome();
					break;

				case GlobalData.MYACCOUNTVIEW:
					showMyAccount();
					break;
				case GlobalData.MOREVIEW:
					showMore();
					break;
				case GlobalData.CARTVIEW:
					showCart();
					break;
				case GlobalData.SEARCHVIEW:
					showSearch();
					break;
				case 2:
				case GlobalData.GOOGINFOVIEW:
				case GlobalData.ADDRESSVIEW:
				case GlobalData.UPDATEADDRESS:
				case GlobalData.ORDERVIEW:
				case GlobalData.SELECT_ADDRESSVIEW:
					hiddenBottom();
					break;

				default:
					break;
				}
			}
		}
	}

	/**
	 * home为选中状态
	 */
	public void showHome() {
		showBottom();
		iv_home.setImageResource(R.drawable.tab_home_pressed);
		iv_search.setImageResource(R.drawable.tab_category_search_normal);
		iv_cart.setImageResource(R.drawable.tab_shopping_normal);
		iv_myaccount.setImageResource(R.drawable.tab_myebuy_normal);
		iv_more.setImageResource(R.drawable.tab_more_normal);
	}

	/**
	 * search为选中状态
	 */
	public void showSearch() {
		showBottom();
		iv_home.setImageResource(R.drawable.tab_home_normal);
		iv_search.setImageResource(R.drawable.tab_category_search_pressed);
		iv_cart.setImageResource(R.drawable.tab_shopping_normal);
		iv_myaccount.setImageResource(R.drawable.tab_myebuy_normal);
		iv_more.setImageResource(R.drawable.tab_more_normal);
	}

	/**
	 * cart为选中状态
	 */
	public void showCart() {
		showBottom();
		iv_home.setImageResource(R.drawable.tab_home_normal);
		iv_search.setImageResource(R.drawable.tab_category_search_normal);
		iv_cart.setImageResource(R.drawable.tab_shopping_pressed);
		iv_myaccount.setImageResource(R.drawable.tab_myebuy_normal);
		iv_more.setImageResource(R.drawable.tab_more_normal);
	}

	/**
	 * myaccount为选中状态
	 */
	public void showMyAccount() {
		showBottom();
		iv_home.setImageResource(R.drawable.tab_home_normal);
		iv_search.setImageResource(R.drawable.tab_category_search_normal);
		iv_cart.setImageResource(R.drawable.tab_shopping_normal);
		iv_myaccount.setImageResource(R.drawable.tab_myebuy_pressed);
		iv_more.setImageResource(R.drawable.tab_more_normal);
	}

	/**
	 * more为选中状态
	 */
	public void showMore() {
		showBottom();
		iv_home.setImageResource(R.drawable.tab_home_normal);
		iv_search.setImageResource(R.drawable.tab_category_search_normal);
		iv_cart.setImageResource(R.drawable.tab_shopping_normal);
		iv_myaccount.setImageResource(R.drawable.tab_myebuy_normal);
		iv_more.setImageResource(R.drawable.tab_more_pressed);
	}
}
