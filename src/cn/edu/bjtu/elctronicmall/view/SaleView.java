package cn.edu.bjtu.elctronicmall.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.SaleAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;

public class SaleView extends BaseView {
	private SQLiteDatabase database;
	private GoodDao dao;
	private ListView lv_sale;
	private List<Good> goods = new ArrayList<Good>();
	private SaleAdapter adapter;

	public SaleView(Context context) {
		super(context);
		showView = (ViewGroup) View.inflate(context, R.layout.sale, null);
		lv_sale = (ListView) showView.findViewById(R.id.lv_sale);
		// 设置标题头
		TitleManager.getInstance().setOneText("促销产品");
		TitleManager.getInstance().setButtonText("返回");
		BottomManager.getInstance().showBottom();
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READONLY);
		dao = new GoodDao(context);
		initView();
	}

	private void initView() {
		goods = dao.findGoodByCategory(database, 2);
		adapter = new SaleAdapter(goods, context);
		lv_sale.setAdapter(adapter);
	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.SALESVIEW;
	}

}
