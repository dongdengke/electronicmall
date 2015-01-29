package cn.edu.bjtu.elctronicmall.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;

/**
 * 更多功能界面
 * 
 * @author dong
 * 
 */
public class MoreView extends BaseView {

	private RelativeLayout rl_cache;
	private RelativeLayout rl_version;
	private TextView version_name;
	private RelativeLayout rl_share;
	private RelativeLayout rl_contact;
	private TextView hotline_number;
	private String number;

	public MoreView(final Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.more, null);
		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().setOneText("更多");
		rl_cache = (RelativeLayout) showView.findViewById(R.id.rl_cache);
		rl_cache.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "抱歉！此功能还在尽力完善中", Toast.LENGTH_SHORT)
						.show();
			}
		});
		rl_version = (RelativeLayout) showView.findViewById(R.id.rl_version);
		version_name = (TextView) showView.findViewById(R.id.version_name);
		// 点击时联接服务器，检查版本，看是否需要更新
		rl_version.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				version_name.setText(getVersion());
				Toast.makeText(context, "当前版本号：" + getVersion(),
						Toast.LENGTH_SHORT).show();

			}
		});
		rl_share = (RelativeLayout) showView.findViewById(R.id.rl_share);
		rl_share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				shareApplication();
			}
		});
		rl_contact = (RelativeLayout) showView.findViewById(R.id.rl_contact);
		hotline_number = (TextView) showView.findViewById(R.id.hotline_number);
		number = hotline_number.getText().toString().trim();
		rl_contact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel://" + number));
				context.startActivity(intent);
			}
		});
		Button logon_out_btn = (Button) showView
				.findViewById(R.id.logon_out_btn);
		logon_out_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (GlobalData.LOGIN_SUCCES != -1) {

					GlobalData.LOGIN_SUCCES = -1;
					Toast.makeText(context, "退出登陆成功", 0).show();
					return;
				}
				Toast.makeText(context, "您没有登陆，不需要退出", 0).show();
			}
		});
	}

	/**
	 * 获取应用程序的版本号
	 * 
	 * @return
	 */
	private String getVersion() {
		// TODO Auto-generated method stub
		try {
			// 获取包管理器
			PackageManager manager = context.getPackageManager();
			// 获取有关包的信息
			PackageInfo packageInfo = manager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// can not reach
			return "";
		}
	}

	/**
	 * 分享软件
	 */
	private void shareApplication() {
		// TODO Auto-generated method stub
		// 获取包管理器
		PackageManager manager = context.getPackageManager();
		// 获取有关包的信息
		PackageInfo packageInfo;
		try {
			packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
			Intent intent = new Intent();
			intent.setAction(intent.ACTION_SEND);
			intent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.setType("text/plain");
			intent.putExtra(
					Intent.EXTRA_TEXT,
					"推荐您使用一款软件,软件的名称叫:胖妞电子商城，商品多多，价格便宜"
							+ "下载地址:https://play.google.com/store/apps/details?id="
							+ packageInfo.packageName);
			context.startActivity(intent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.MOREVIEW;
	}

}
