package cn.edu.bjtu.elctronicmall.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseView {
	protected Context context;
	protected Bundle bundle;
	protected ViewGroup showView;// 放到中间容器中显示的内容

	public BaseView(Context context, Bundle bundle) {
		super();
		this.context = context;
		this.bundle = bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * 获取view
	 * 
	 * @param context
	 * @return
	 */
	public abstract View getView(Context context);

	/**
	 * 返回子类对象的唯一标识
	 * 
	 * @return
	 */
	public abstract int getId();

}
