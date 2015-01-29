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
import cn.edu.bjtu.elctronicmall.adapter.HistoryAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

public class HistoryView extends BaseView {

	private SQLiteDatabase database;
	private ListView lv_history;
	private RelativeLayout rl_history_empty;
	private RelativeLayout rl_history_not_empty;
	private GoodDao goodDao;
	private List<Good> historyGoods;
	private HistoryAdapter adapter;

	public HistoryView(Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.history, null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		lv_history = (ListView) showView.findViewById(R.id.lv_history);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setTwoText("浏览历史");
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("去逛逛");
		rl_history_empty = (RelativeLayout) showView
				.findViewById(R.id.rl_history_empty);
		rl_history_not_empty = (RelativeLayout) showView
				.findViewById(R.id.rl_history_not_empty);
		goodDao = new GoodDao(context);
		historyGoods = new ArrayList<Good>();

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

		lv_history.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// Good good = historyGoods.get(position);
				// int goodId = good.getId();
				// GloableParams.LOOKHISTORY.addFirst(goodId);
				UIManager.getInstance().changeVew(GoodInfoView.class, bundle);
			}
		});
	}

	@Override
	public View getView(Context context) {
		// 遍历集合
		if (GloableParams.LOOKHISTORY.size() == 0) {
			rl_history_empty.setVisibility(View.VISIBLE);
			rl_history_not_empty.setVisibility(View.INVISIBLE);
		} else {
			rl_history_empty.setVisibility(View.INVISIBLE);
			rl_history_not_empty.setVisibility(View.VISIBLE);
			for (int i = 0; i < GloableParams.LOOKHISTORY.size(); i++) {
				Good good = goodDao.findGoodById(database,
						GloableParams.LOOKHISTORY.get(i));
				historyGoods.add(good);
			}
			adapter = new HistoryAdapter(historyGoods, context);
			lv_history.setAdapter(adapter);
		}
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.HISTORYVIEW;
	}

}
