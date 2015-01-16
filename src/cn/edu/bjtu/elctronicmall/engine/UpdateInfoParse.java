package cn.edu.bjtu.elctronicmall.engine;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;
import cn.edu.bjtu.elctronicmall.bean.UpdateInfo;

/**
 * 解析服务器端跟新信息的xml文件
 * 
 * @author dong
 * 
 */
public class UpdateInfoParse {
	/**
	 * 返回更新信息的业务对象
	 * 
	 * @param in
	 * @return
	 */
	public static UpdateInfo getUpdateInfo(InputStream in) {
		UpdateInfo updateInfo = new UpdateInfo();
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(in, "utf-8");
			int type = parser.getEventType();
			while (type != XmlPullParser.END_DOCUMENT) {
				if (type == XmlPullParser.START_TAG) {
					if ("version".equals(parser.getName())) {
						String version = parser.nextText();
						updateInfo.setVersion(version);
					} else if ("description".equals(parser.getName())) {
						String description = parser.nextText();
						updateInfo.setDescription(description);
					} else if ("aplurl".equals(parser.getName())) {
						String aplurl = parser.nextText();
						updateInfo.setAplurl(aplurl);
					}
				}
				type = parser.next();
			}
			return updateInfo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
