package cn.edu.bjtu.elctronicmall.manager;

import java.util.Observable;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.view.BaseView;

/**
 * 控制中间部分信息的管理者
 * 
 * @author dong
 * 
 */
public class UIManager extends Observable {
	private RelativeLayout middle;

	public void setMiddle(RelativeLayout middle) {
		this.middle = middle;
	}

	private UIManager() {

	}

	private static UIManager manager;

	public static UIManager getInstance() {
		if (manager == null) {
			manager = new UIManager();
		}
		return manager;
	}

	public boolean changeView(BaseView secondView) {
		// FadeAnimUtil.fadeOut(view1, 2000);
		View view2 = secondView.getView(getContext());
		middle.addView(view2);
		// FadeAnimUtil.fadeIn(view2, 2000, 2000);
		view2.startAnimation(AnimationUtils.loadAnimation(getContext(),
				R.anim.ia_view_change));
		return true;
	}

	public Context getContext() {
		return middle.getContext();
	}
}
