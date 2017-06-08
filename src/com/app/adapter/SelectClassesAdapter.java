package com.app.adapter;

import java.util.ArrayList;
import java.util.List;

import com.app.utils.ToastUtil;
import com.example.erweima.R;
import com.example.request.comfort.enty.SelectClassesBean;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class SelectClassesAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<SelectClassesBean> mData;
	

	public SelectClassesAdapter(Context context,ArrayList<SelectClassesBean> bean) {
		mContext = context;
		mData = bean;
	}

	@Override
	public int getCount() { 
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup arg2) {
		viewHolder holder;
		if (view == null) {
			holder = new viewHolder();
			view = LayoutInflater.from(mContext).inflate(
					R.layout.item_select_classes, null);
			holder.name = (TextView) view.findViewById(R.id.classes_name);
			holder.jiaoshi = (TextView) view.findViewById(R.id.classes_jiaoshi);
			holder.state = (TextView) view.findViewById(R.id.classes_state);
			holder.checkbox = (CheckBox) view.findViewById(R.id.classes_check);
			holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean isCheck) {
					if(listener!=null){
						listener.OnItemCheck(position, isCheck);
					}
				}
			});
			view.setTag(holder);
		} else {
			holder = (viewHolder) view.getTag();
		}
		SelectClassesBean bean = mData.get(position);
		holder.name.setText(bean.getName());
		if(bean.getSpecs()!=null){
			for(int i = 0;i<bean.getSpecs().size();i++){
				if("room".equals(bean.getSpecs().get(i).getName())||"classroom".equals(bean.getSpecs().get(i).getName())){
					holder.jiaoshi.setText(bean.getSpecs().get(i).getValue());	
				}
			}
			if(holder.jiaoshi.getText().toString().length()<=0){
				holder.jiaoshi.setText("未登记");
			}
		}else{
			holder.jiaoshi.setText("未登记");	
		}
		if(bean.getMy_state()!=null&&"comein".equals(bean.getMy_state())){
			holder.state.setText("已报到");
			holder.checkbox.setVisibility(View.GONE);
		}else{
			holder.state.setText("未报到");
			holder.checkbox.setVisibility(View.VISIBLE);
		}
		return view;
	}

	static class viewHolder {
		TextView name;
		TextView jiaoshi;
		TextView state;
		CheckBox checkbox;
	}
	
	public interface onItemCheckListener{
		void OnItemCheck(int position,boolean isCheck);
	}
	
	onItemCheckListener listener;
	
	public void setListener(onItemCheckListener listener) {
		this.listener = listener;
	}
}
