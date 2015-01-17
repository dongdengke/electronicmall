package cn.edu.bjtu.elctronicmall.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;

/**
 * 购物车界面
 * 
 * @author dong
 * 
 */
public class CartView extends BaseView {

	public CartView(Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.cart_main, null);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("去结算");
		TitleManager.getInstance().setTwoText("购物车");
		BottomManager.getInstance().showBottom();
	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.CARTVIEW;
	}

}
