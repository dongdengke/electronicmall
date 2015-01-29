package cn.edu.bjtu.elctronicmall.view;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.HotProductAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

/**
 * 人们商品信息
 * 
 * @author dong
 * 
 */
public class HotProductView extends BaseView {

	private SQLiteDatabase database;
	private GoodDao dao;
	private GridView gv_hot_product;
	private List<Good> goods;
	private HotProductAdapter adapter;

	public HotProductView(Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View
				.inflate(context, R.layout.hot_product, null);
		TitleManager.getInstance().showOneText();
		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().setOneText("商品列表");
		BottomManager.getInstance().showBottom();
		TitleManager.getInstance().getBtn_name()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READONLY);
		dao = new GoodDao(context);
		init();

	}

	/**
	 * 初始化
	 */
	private void init() {
		gv_hot_product = (GridView) showView.findViewById(R.id.gv_hot_product);
		goods = dao.findAllGood(database);
		System.out.println(goods.size() + "-----------------");
		adapter = new HotProductAdapter(context, goods);
		gv_hot_product.setAdapter(adapter);
		gv_hot_product.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Good good = goods.get(position);
				GloableParams.LOOKHISTORY.addFirst(good.getId());
				UIManager.getInstance().changeVew(GoodInfoView.class, bundle);
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
		return GlobalData.HOTPRODUCTVIEW;
	}

}
