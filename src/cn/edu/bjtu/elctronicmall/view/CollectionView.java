package cn.edu.bjtu.elctronicmall.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.CollectionAdapter;
import cn.edu.bjtu.elctronicmall.bean.Collection;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.CollectionDao;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

public class CollectionView extends BaseView {

	private SQLiteDatabase database;
	private CollectionDao collectionDao;
	private ListView lv_collection;
	private List<Collection> collections;
	private GoodDao goodDao;
	private List<Good> goods;
	private Good good;
	private CollectionAdapter saleAdapter;
	private RelativeLayout rl_collection_empty;
	private RelativeLayout rl_collection_not_empty;

	public CollectionView(Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.collection, null);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setTwoText("收藏夹");
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("去逛逛");
		rl_collection_empty = (RelativeLayout) showView
				.findViewById(R.id.rl_collection_empty);
		rl_collection_not_empty = (RelativeLayout) showView
				.findViewById(R.id.rl_collection_not_empty);
		TitleManager.getInstance().getBtn_name_left()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		TitleManager.getInstance().getBtn_name_right()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		collectionDao = new CollectionDao();
		lv_collection = (ListView) showView.findViewById(R.id.lv_collection);
		goodDao = new GoodDao(context);
		goods = new ArrayList<Good>();
		collections = collectionDao.queryByUserId(database,
				GlobalData.LOGIN_SUCCES);

	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		if (collections.size() == 0) {
			rl_collection_empty.setVisibility(View.VISIBLE);
			rl_collection_not_empty.setVisibility(View.GONE);
		} else {
			rl_collection_empty.setVisibility(View.INVISIBLE);
			rl_collection_not_empty.setVisibility(View.VISIBLE);
			for (Collection collection : collections) {
				int goodId = collection.getGoodId();
				good = goodDao.findGoodById(database, goodId);
				goods.add(good);
			}
			saleAdapter = new CollectionAdapter(goods, context);
			lv_collection.setAdapter(saleAdapter);
			lv_collection.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Collection collection = collections.get(position);
					int goodId = collection.getGoodId();
					GloableParams.LOOKHISTORY.addFirst(goodId);
					UIManager.getInstance().changeVew(GoodInfoView.class,
							bundle);
				}
			});
		}

		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.COLLECTIONVIEW;
	}

}
