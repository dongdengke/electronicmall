package cn.edu.bjtu.elctronicmall.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.R;

public class HomeCategoryAdapter extends BaseAdapter {
	private Context context;
	private List<Integer> categoryImageIds;
	private String[] categoryNames;

	public HomeCategoryAdapter(Context context, List<Integer> categoryImageIds,
			String[] categoryNames) {
		super();
		this.context = context;
		this.categoryImageIds = categoryImageIds;
		this.categoryNames = categoryNames;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categoryImageIds.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categoryImageIds.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		View view = null;
		if (convertView != null) {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		} else {
			view = View.inflate(context, R.layout.list_home_item, null);
			holder = new ViewHolder();
			holder.icon = (ImageView) view.findViewById(R.id.icon_category);
			holder.name = (TextView) view.findViewById(R.id.tv_category);
			view.setTag(holder);
		}
		String name = categoryNames[position];
		holder.name.setText(name);
		holder.icon.setImageResource(categoryImageIds.get(position));
		return view;
	}

	static class ViewHolder {
		ImageView icon;
		TextView name;
	}
}
