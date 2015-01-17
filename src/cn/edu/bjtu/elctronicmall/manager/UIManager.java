package cn.edu.bjtu.elctronicmall.manager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.view.BaseView;

/**
 * 控制中间部分信息的管理者
 * 
 * 
 * @author dong
 * 
 */
public class UIManager extends Observable {
	private RelativeLayout middle;
	// 用户存放点击时创建的view的集合，key是子类的简单名称
	private HashMap<String, BaseView> BASEVIEWS = new HashMap<String, BaseView>();
	// 当前正在显示的view
	private BaseView currentView;

	// 存放用户的浏览历史
	private LinkedList<String> HISTORY = new LinkedList<String>();

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

	/**
	 * 切换界面:参数传递
	 * 
	 * @param secondView
	 */
	public boolean changeVew(Class<? extends BaseView> targetViewClazz,
			Bundle bundle) {
		// 如果和当前界面相同，不进行界面的切换
		if (currentView != null && currentView.getClass() == targetViewClazz) {
			return false;
		}
		// 只能创建一次
		// 创建好的对象需要存储在集合中:便于查询
		String key = targetViewClazz.getSimpleName();
		BaseView targetView = null;
		// 如果集合中已经有了该子类对象，则直接使用，如果没有通过反射来获取
		if (BASEVIEWS.containsKey(key)) {
			// 已经创建
			targetView = BASEVIEWS.get(key);
			targetView.setBundle(bundle);
		} else {
			// 自己创建
			try {
				Constructor<? extends BaseView> constructor = targetViewClazz
						.getConstructor(Context.class, Bundle.class);
				targetView = constructor.newInstance(getContext(), bundle);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 获取之后进行存储，方便以后使用
			BASEVIEWS.put(key, targetView);
		}
		System.out.println(targetView.toString());
		middle.removeAllViews();
		// FadeAnimUtil.fadeOut(view1, 2000);
		View view = targetView.getView(getContext());
		middle.addView(view);
		// FadeAnimUtil.fadeIn(targetView, 2000, 2000);
		view.startAnimation(AnimationUtils.loadAnimation(getContext(),
				R.anim.ia_view_change));
		// 设置当前正在显示的view
		currentView = targetView;
		// 更改头部和底部的标题
		changeTitileAndBottom();
		// 浏览之后，把记录存放到用户的浏览历史
		HISTORY.addFirst(key);
		return true;
	}

	// /**
	// * 切换界面:参数传递
	// *
	// * @param secondView
	// */
	// public boolean changeVew(Class<? extends BaseView> targetViewClazz) {
	// // 如果和当前界面相同，不进行界面的切换
	// if (currentView != null && currentView.getClass() == targetViewClazz) {
	// return false;
	// }
	// // 只能创建一次
	// // 创建好的对象需要存储在集合中:便于查询
	// String key = targetViewClazz.getSimpleName();
	// BaseView targetView = null;
	// // 如果集合中已经有了该子类对象，则直接使用，如果没有通过反射来获取
	// if (BASEVIEWS.containsKey(key)) {
	// // 已经创建
	// targetView = BASEVIEWS.get(key);
	// } else {
	// // 自己创建
	// try {
	// Constructor<? extends BaseView> constructor = targetViewClazz
	// .getConstructor(Context.class);
	// targetView = constructor.newInstance(getContext());
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // 获取之后进行存储，方便以后使用
	// BASEVIEWS.put(key, targetView);
	// }
	// System.out.println(targetView.toString());
	// middle.removeAllViews();
	// // FadeAnimUtil.fadeOut(view1, 2000);
	// View view = targetView.getView(getContext());
	// middle.addView(view);
	// // FadeAnimUtil.fadeIn(targetView, 2000, 2000);
	// view.startAnimation(AnimationUtils.loadAnimation(getContext(),
	// R.anim.ia_view_change));
	// // 设置当前正在显示的view
	// currentView = targetView;
	// // 更改头部和底部的标题
	// changeTitileAndBottom();
	// // 浏览之后，把记录存放到用户的浏览历史
	// HISTORY.addFirst(key);
	// return true;
	// }

	/**
	 * 切换界面:处理连续点击时，相同界面的切换问题
	 * 
	 * @param secondView
	 */
	public boolean changeVew4(Class<? extends BaseView> targetViewClazz) {
		// 如果和当前界面相同，不进行界面的切换
		if (currentView != null && currentView.getClass() == targetViewClazz) {
			return false;
		}
		// 只能创建一次
		// 创建好的对象需要存储在集合中:便于查询
		String key = targetViewClazz.getSimpleName();
		BaseView targetView = null;
		// 如果集合中已经有了该子类对象，则直接使用，如果没有通过反射来获取
		if (BASEVIEWS.containsKey(key)) {
			// 已经创建
			targetView = BASEVIEWS.get(key);
		} else {
			// 自己创建
			try {
				Constructor<? extends BaseView> constructor = targetViewClazz
						.getConstructor(Context.class);
				targetView = constructor.newInstance(getContext());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 获取之后进行存储，方便以后使用
			BASEVIEWS.put(key, targetView);
		}
		System.out.println(targetView.toString());
		middle.removeAllViews();
		// FadeAnimUtil.fadeOut(view1, 2000);
		View view = targetView.getView(getContext());
		middle.addView(view);
		// FadeAnimUtil.fadeIn(targetView, 2000, 2000);
		view.startAnimation(AnimationUtils.loadAnimation(getContext(),
				R.anim.ia_view_change));
		// 设置当前正在显示的view
		currentView = targetView;
		// 更改头部和底部的标题
		changeTitileAndBottom();
		// 浏览之后，把记录存放到用户的浏览历史
		HISTORY.addFirst(key);
		return true;
	}

	/**
	 * 更改头部和底部的标题
	 */
	private void changeTitileAndBottom() {
		// TODO Auto-generated method stub
		// 方案一
		// if (currentView.getClass().getSimpleName().equals("HomeView")) {
		// TitleManager.getInstance().showHomeTitle();
		// BottomManager.getInstance().showBottom();
		// }
		// if (currentView.getClass().getSimpleName().equals("SecondView")) {
		// TitleManager.getInstance().showOneTitle();
		// BottomManager.getInstance().hiddenBottom();
		// }
		// 方案二观察者设计模式
		setChanged();
		notifyObservers(currentView.getId());

	}

	/**
	 * 切换界面:处理联系点击时，界面重复创建的问题
	 * 
	 * @param secondView
	 */
	public boolean changeVew2(Class<? extends BaseView> targetViewClazz) {
		// 只能创建一次
		// 创建好的对象需要存储在集合中:便于查询
		String key = targetViewClazz.getSimpleName();
		BaseView targetView = null;
		// 如果集合中已经有了该子类对象，则直接使用，如果没有通过反射来获取
		if (BASEVIEWS.containsKey(key)) {
			// 已经创建
			targetView = BASEVIEWS.get(key);
		} else {
			// 自己创建
			try {
				Constructor<? extends BaseView> constructor = targetViewClazz
						.getConstructor(Context.class);
				targetView = constructor.newInstance(getContext());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 获取之后进行存储，方便以后使用
			BASEVIEWS.put(key, targetView);
		}
		System.out.println(targetView.toString());
		middle.removeAllViews();
		// FadeAnimUtil.fadeOut(view1, 2000);
		View view = targetView.getView(getContext());
		middle.addView(view);
		// FadeAnimUtil.fadeIn(targetView, 2000, 2000);
		view.startAnimation(AnimationUtils.loadAnimation(getContext(),
				R.anim.ia_view_change));
		return true;
	}

	/**
	 * 切换界面：存在问题，相同的view对象会创建多次，相同界面间会切换，baseview子类中的布局会加载多次
	 * 
	 * @param secondView
	 */
	public boolean changeVew1(BaseView targetView) {
		middle.removeAllViews();
		// FadeAnimUtil.fadeOut(view1, 2000);
		View view = targetView.getView(getContext());
		middle.addView(view);
		// FadeAnimUtil.fadeIn(targetView, 2000, 2000);
		view.startAnimation(AnimationUtils.loadAnimation(getContext(),
				R.anim.ia_view_change));
		return true;
	}

	/**
	 * 获取上下文对象
	 * 
	 * @return
	 */
	public Context getContext() {
		return middle.getContext();
	}

	/**
	 * 返回键的处理
	 * 
	 * @return
	 */
	public boolean goBack() {
		String key = "";
		if (HISTORY.size() > 0) {
			// 如果集合中的元素大于一个，获取第一个
			key = HISTORY.getFirst();
		}
		if (StringUtils.isNotBlank(key)) {
			// 根据key获取相应的baseview的子类
			BaseView targetView = BASEVIEWS.get(key);
			if (targetView.getClass() == currentView.getClass()) {
				// 判断历史集合中是否含有，如果有大小（如果size==1）
				if (HISTORY.size() == 1) {
					return false;
				}
				// 如果相同做删除的操作
				HISTORY.removeFirst();
				// 之后再次获取栈顶元素
				return goBack();
			} else {
				middle.removeAllViews();
				middle.addView(targetView.getView(getContext()));
				// 更新当前正在显示的界面
				currentView = targetView;
				// 切换底部和标题容器
				changeTitileAndBottom();
				GloableParams.LOOKHISTORY.clear();
				return true;
			}
		}
		return false;
	}
}
