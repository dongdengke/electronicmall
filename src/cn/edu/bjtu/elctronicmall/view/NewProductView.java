package cn.edu.bjtu.elctronicmall.view;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.NewProductAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

/**
 * 新品上架的界面
 * 
 * @author dong
 * 
 */
public class NewProductView extends BaseView {

	private SQLiteDatabase database;
	private GoodDao dao;
	private ListView lv_new_product;
	private static final int NEWPRODUCTINDEX = 3;
	private NewProductAdapter adapter;
	private List<Good> newproducts;

	public NewProductView(Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View
				.inflate(context, R.layout.new_product, null);
		// 设置标题的信息
		TitleManager.getInstance().showOneText();
		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().setOneText("新品上架");
		BottomManager.getInstance().showBottom();
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READONLY);
		dao = new GoodDao(context);
		// 进行初始化
		init();
	}

	/**
	 * 进行初始化
	 */
	private void init() {
		lv_new_product = (ListView) showView.findViewById(R.id.lv_new_product);
		newproducts = dao.findGoodByCategory(database,
				NEWPRODUCTINDEX);
		adapter = new NewProductAdapter(context, newproducts);
		lv_new_product.setAdapter(adapter);
		lv_new_product.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Good good = newproducts.get(position);
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
		return GlobalData.NEWPRODUCTVIEW;
	}

}
