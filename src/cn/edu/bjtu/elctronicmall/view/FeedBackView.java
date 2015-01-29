package cn.edu.bjtu.elctronicmall.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Feedback;
import cn.edu.bjtu.elctronicmall.dao.FeedBackDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

public class FeedBackView extends BaseView {

	private Button btn_submit_feedback;
	private SQLiteDatabase database;
	private FeedBackDao feedBackDao;
	private EditText ed_feedback;

	public FeedBackView(final Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.feedback, null);
		TitleManager.getInstance().showOneText();
		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().setOneText("用户反馈");
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
		feedBackDao = new FeedBackDao();
		btn_submit_feedback = (Button) showView
				.findViewById(R.id.btn_submit_feedback);
		ed_feedback = (EditText) showView.findViewById(R.id.ed_feedback);

		// 提交用户的反馈
		btn_submit_feedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String content = ed_feedback.getText().toString().trim();
				if (TextUtils.isEmpty(content)) {
					Toast.makeText(context, "反馈内容不能为空", 0).show();
					return;
				}
				Feedback feedback = new Feedback();
				feedback.setContent(content);
				feedback.setUserId(GlobalData.LOGIN_SUCCES);
				long rawId = feedBackDao.addFeedBack(database, feedback);
				if (rawId != -1) {
					Toast.makeText(context, "反馈成功", 0).show();
				} else {
					Toast.makeText(context, "反馈失败", 0).show();
				}
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
		return GlobalData.FEEDBACK;
	}

}
