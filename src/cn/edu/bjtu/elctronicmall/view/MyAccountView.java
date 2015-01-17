package cn.edu.bjtu.elctronicmall.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.global.GlobalData;

public class MyAccountView extends BaseView {

	public MyAccountView(Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.myaccount, null);
	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.MYACCOUNTVIEW;
	}

}
