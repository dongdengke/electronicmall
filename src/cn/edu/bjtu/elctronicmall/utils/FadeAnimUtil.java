package cn.edu.bjtu.elctronicmall.utils;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class FadeAnimUtil {
	// 当前正在显示的界面（fristView.getView）渐渐的淡出视野——执行的时间
	// 当执行完第一个界面的淡出动画之后
	// 第二个界面（secondView.getView）渐渐淡入进视野——执行的时间

	private static Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			View view = (View) msg.obj;
			ViewGroup parent = (ViewGroup) view.getParent();
			parent.removeView(view);
		};
	};

	/**
	 * 淡出
	 * 
	 * @param view
	 *            ：目标界面
	 * @param duration
	 *            ：动画的执行时间
	 */
	public static void fadeOut(final View view, long duration) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
		alphaAnimation.setDuration(duration);
		alphaAnimation.setFillAfter(true);// 停留在执行的终点状态
		// 如何处理duration之后的删除：removeView
		// remove

		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 删除第一个界面
				// 2.3模拟器：onAnimationEnd执行remove抛出异常(4.0模拟器不存在)
				// ViewGroup parent=(ViewGroup)view.getParent();
				// parent.removeView(view);

				Message message = Message.obtain();
				message.obj = view;
				handler.sendMessage(message);

			}
		});

		view.startAnimation(alphaAnimation);
	}

	/**
	 * 淡入
	 * 
	 * @param view
	 *            ：目标界面
	 * @param duration
	 *            ：动画的执行时间
	 * @param delay
	 *            ：延时多久执行（第一个界面的淡出执行时间）
	 */
	public static void fadeIn(View view, long duration, long delay) {
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(duration);
		alphaAnimation.setStartOffset(delay);// 设置时间的偏移（延时时间）

		view.startAnimation(alphaAnimation);

	}
}
