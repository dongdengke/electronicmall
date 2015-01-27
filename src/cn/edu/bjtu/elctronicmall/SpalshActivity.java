package cn.edu.bjtu.elctronicmall;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.bean.UpdateInfo;
import cn.edu.bjtu.elctronicmall.engine.UpdateInfoParse;
import cn.edu.bjtu.elctronicmall.global.GlobalData;

public class SpalshActivity extends Activity {

	protected static final int SERVER_ERROR = 1;
	protected static final int URL_ERROR = 2;
	protected static final int NETWORK_ERROR = 3;
	protected static final int PARSE_XML_ERROR = 4;
	protected static final int PARSE_SUCCESS = 5;
	private TextView tv_splash_version;
	// 更新信息的业务对象
	private UpdateInfo updateInfo;
	// 消息机制,处理在子线程中更新ui
	Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SERVER_ERROR:
				Toast.makeText(getApplicationContext(), "服务器错误!",
						Toast.LENGTH_SHORT).show();
				loadMainUI();
				break;
			case URL_ERROR:
				Toast.makeText(getApplicationContext(), "更新地址错误!",
						Toast.LENGTH_SHORT).show();
				loadMainUI();
				break;
			case NETWORK_ERROR:
				Toast.makeText(getApplicationContext(), "网络联接错误!",
						Toast.LENGTH_SHORT).show();
				loadMainUI();
				break;
			case PARSE_XML_ERROR:
				Toast.makeText(getApplicationContext(), "解析失败!",
						Toast.LENGTH_SHORT).show();
				loadMainUI();
				break;
			case PARSE_SUCCESS:
				// 检查版本,确定是否需要更新
				checkVersion();
				break;
			}
		}

	};
	private RelativeLayout relativeLayout;
	private TextView tv_splash_progress;
	private SharedPreferences sp;

	/**
	 * 检查版本,确定是否需要更新
	 */
	private void checkVersion() {
		// TODO Auto-generated method stub
		if (getVersion().equals(updateInfo.getVersion())) {
			// 版本相同,不需要更新
			loadMainUI();
		} else {

			// 版本不同,弹出对话框,提示更新信息
			AlertDialog.Builder builder = new AlertDialog.Builder(
					SpalshActivity.this);
			builder.setTitle("更新提示!");
			builder.setMessage(updateInfo.getDescription());
			builder.setPositiveButton("立即更新", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					// 下载之前把显示下载进度的textview设为可见
					tv_splash_progress.setVisibility(View.VISIBLE);
					// 版本不同,下载更新,使用多线程断点下载开源框架
					FinalHttp finalHttp = new FinalHttp();
					File file = new File(Environment
							.getExternalStorageDirectory(), "temp.apk");
					finalHttp.download(updateInfo.getAplurl(),
							file.getAbsolutePath(), new AjaxCallBack<File>() {
								/**
								 * 下载失败时调用
								 */
								@Override
								public void onFailure(Throwable t, int errorNo,
										String strMsg) {
									// TODO Auto-generated method stub
									super.onFailure(t, errorNo, strMsg);
									t.printStackTrace();
								}

								/**
								 * 下载过程中调用
								 */
								@Override
								public void onLoading(long count, long current) {
									// TODO Auto-generated method stub
									super.onLoading(count, current);
									int progress = (int) (current * 100 / count);
									tv_splash_progress.setText("下载进度:"
											+ progress + "%");
								}

								/**
								 * 下载成功时调用
								 */
								@Override
								public void onSuccess(File t) {
									// TODO Auto-generated method stub
									super.onSuccess(t);
									tv_splash_progress
											.setVisibility(View.INVISIBLE);
									Toast.makeText(SpalshActivity.this,
											"下载完成,替换安装", Toast.LENGTH_SHORT)
											.show();
									// 安装apk
									installApk(t);
								}

							});
				}
			});
			builder.setNegativeButton("稍后提醒", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					loadMainUI();
				}

			});
			builder.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					// 按返回键时加载主界面
					loadMainUI();
				}
			});
			builder.show();
		}
	}

	/**
	 * 加载主ui
	 */
	private void loadMainUI() {
		Intent intent = new Intent(SpalshActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		GlobalData.SP = getSharedPreferences("saveAsDefault", MODE_PRIVATE);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("版本号:" + getVersion());
		relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
		tv_splash_progress = (TextView) findViewById(R.id.tv_splash_progress);
		// 检查版本下载更新
		checkVersionTask();
		// 实现splash界面的动画效果
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(2000);
		relativeLayout.startAnimation(aa);
		// 创建快捷图标
		installShortCut();
		// 把raw下的图片拷贝到sdcard中
		copyRawToSdcard();
		// 把数据库拷贝到/data/data/cn.edu.bjtu.electronicmall/files/ec.db
		copyAssetDBFile("ec.db");
	}

	/**
	 * 创建快捷图标
	 */
	private void installShortCut() {
		// TODO Auto-generated method stub
		if (sp.getBoolean("shortcut", false)) {
			System.out.println("快捷图标已经创建,无需重复创建");
		} else {
			Intent intent = new Intent();
			// 动作
			intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
			// 快捷图标的名称
			intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "电子商城");
			// 快捷图标的图标
			intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, BitmapFactory
					.decodeResource(getResources(), R.drawable.ic_launcher));
			// 快捷图标需要激活的组件
			Intent homeIntent = new Intent();
			homeIntent.setAction("cn.edu.bjtu.electronicmall.SHORTCUT");
			homeIntent.addCategory(Intent.CATEGORY_DEFAULT);
			intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, homeIntent);
			sendBroadcast(intent);
			Toast.makeText(this, "已经创建电子商城快捷图标", 1).show();
			Editor editor = sp.edit();
			editor.putBoolean("shortcut", true);
			editor.commit();
		}
	}

	/**
	 * 检查版本信息, 看服务器端是否有新的版本
	 */
	private void checkVersionTask() {
		// TODO Auto-generated method stub
		final Message msg = Message.obtain();
		new Thread() {
			// 开始的时间
			long startTime = System.currentTimeMillis();

			@Override
			public void run() {
				int code;
				try {
					String path = "http://172.28.33.19:8080/ecupdate.xml";
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					code = conn.getResponseCode();
					if (code == 200) {
						// 请求成功
						InputStream in = conn.getInputStream();
						updateInfo = UpdateInfoParse.getUpdateInfo(in);
						if (updateInfo != null) {
							// 解析成功
							msg.what = PARSE_SUCCESS;
						} else {
							// 解析失败
							msg.what = PARSE_XML_ERROR;

						}
					} else {
						// 请求失败
						msg.what = SERVER_ERROR;
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg.what = URL_ERROR;
					// url不合法
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// 网络联接错误
					msg.what = NETWORK_ERROR;
				} finally {
					long endTime = System.currentTimeMillis();
					// splash 界面的停留时间
					long dTime = endTime - startTime;
					if (dTime < 2000) {
						try {
							Thread.sleep(2000 - dTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					handler.sendMessage(msg);
				}
			};
		}.start();
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
			PackageManager manager = getPackageManager();
			// 获取有关包的信息
			PackageInfo packageInfo = manager.getPackageInfo(getPackageName(),
					0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// can not reach
			return "";
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		loadMainUI();
	}

	/**
	 * 安装apk
	 * 
	 * @param t
	 */
	private void installApk(File t) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(t),
				"application/vnd.android.package-archive");
		startActivityForResult(intent, 0);
	}

	/**
	 * 首次加载时把raw下的图片拷贝到sdcard中
	 */
	private void copyRawToSdcard() {
		Field[] fields = R.raw.class.getFields();
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_UNMOUNTED)) {
			Toast.makeText(this, "sd卡状态异常", Toast.LENGTH_SHORT).show();
		}
		File name = Environment.getExternalStorageDirectory();
		for (final Field r : fields) {
			try {
				final File path = new File(name, r.getName() + ".png");
				if (path.exists() && path.length() > 0) {
					System.out.println("文件已经存在，无需重复拷贝");
				} else {
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								FileOutputStream fos = new FileOutputStream(
										path);
								BufferedOutputStream outputStream = new BufferedOutputStream(
										fos);
								int id = getResources().getIdentifier(
										r.getName(), "raw", getPackageName());
								InputStream in = getResources()
										.openRawResource(id);
								BufferedInputStream bufin = new BufferedInputStream(
										in);
								byte[] buff = new byte[20 * 1024];
								int len;
								while ((len = bufin.read(buff)) != -1) {
									outputStream.write(buff, 0, len);
								}
								outputStream.flush();
								outputStream.close();
								in.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}).start();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "服务器端繁忙，请稍后重试", Toast.LENGTH_SHORT).show();
			}

		}
	}

	/**
	 * 拷贝数据库文件到系统根目录 /data/data/cn.edu.bjtu.electronicmall/files/ec.db
	 * 
	 * @param string
	 */
	private void copyAssetDBFile(final String fileName) {
		// TODO Auto-generated method stub
		File file = new File(getFilesDir(), fileName);
		if (file.exists() && file.length() > 0) {
			System.out.println("数据库文件已经存在,不需要重复拷贝");
		} else {

			new Thread() {
				@Override
				public void run() {
					try {
						InputStream in = getAssets().open(fileName);
						File file = new File(getFilesDir(), fileName);
						FileOutputStream fos = new FileOutputStream(file);
						int len = 0;
						byte[] buffer = new byte[1024];
						while ((len = in.read(buffer)) != -1) {
							fos.write(buffer, 0, len);
						}
						fos.close();
						in.close();
						System.out.println("数据库拷贝成功");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("数据库拷贝失败");
					}
				};
			}.start();
		}
	}

}