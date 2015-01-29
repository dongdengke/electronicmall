package cn.edu.bjtu.elctronicmall.view;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.SaleAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

public class SearchView extends BaseView {

	private SQLiteDatabase database;
	private GoodDao goodDao;
	private List<Good> goods;
	private SaleAdapter adapter;
	private ListView lv_search_good;
	private EditText text_hint;
	private String name;
	private ImageView btn_search;
	private LinearLayout ll_task_loading;
	private LinearLayout ll_empty;
	private FrameLayout fr_not_empty;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			adapter = new SaleAdapter(goods, context);
			ll_task_loading.setVisibility(View.INVISIBLE);
			if (goods.size() <= 0) {
				Toast.makeText(context, "没有符合条件的商品", Toast.LENGTH_SHORT).show();
				ll_empty.setVisibility(View.VISIBLE);
				fr_not_empty.setVisibility(View.GONE);
			} else {
				ll_empty.setVisibility(View.GONE);
				fr_not_empty.setVisibility(View.VISIBLE);
				lv_search_good.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		}
	};

	public SearchView(final Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.search, null);
		TitleManager.getInstance().showOneText();
		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().setOneText("商品搜索");
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
				SQLiteDatabase.OPEN_READWRITE);
		goodDao = new GoodDao(context);
		text_hint = (EditText) showView.findViewById(R.id.text_hint);
		ll_task_loading = (LinearLayout) showView
				.findViewById(R.id.ll_task_loading);
		lv_search_good = (ListView) showView.findViewById(R.id.lv_search_good);
		btn_search = (ImageView) showView.findViewById(R.id.btn_search);
		ll_empty = (LinearLayout) showView.findViewById(R.id.ll_empty);
		fr_not_empty = (FrameLayout) showView.findViewById(R.id.fr_not_empty);

		lv_search_good.setOnItemClickListener(new OnItemClickListener() {

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
	public View getView(final Context context) {
		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				name = text_hint.getText().toString().trim();
				if (TextUtils.isEmpty(name)) {
					Toast.makeText(context, "请输入商品名称", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				ll_task_loading.setVisibility(View.VISIBLE);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						goods = goodDao.findGoodByName(database, name);
						handler.sendEmptyMessage(0);
					}
				}).start();
			}
		});
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.SEARCHVIEW;
	}

}
