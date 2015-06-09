package com.strendent.tutorsu.Adapters;

import java.util.ArrayList;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Models.Model_Drawer_Items;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adpater_DrawerAdapter extends BaseAdapter {
	 private Activity activity;  
		ArrayList<Model_Drawer_Items> arrayitms; 

	   public Adpater_DrawerAdapter(Activity activity,ArrayList<Model_Drawer_Items>  listarry) {  
	       super();  
	       this.activity = activity;  
	       this.arrayitms=listarry;
	       }     
	   @Override
	   public Object getItem(int position) {       
	       return arrayitms.get(position);
	   }   
	    public int getCount() {  
	        return arrayitms.size();  
	    }    
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }   
	    public static class Fila  
	    {  
	    		TextView drawer_title;
	    		ImageView drawer_icon;
	    }  
	   public View getView(int position, View convertView, ViewGroup parent) {  
	      // TODO Auto-generated method stub  
		   Fila view;  
	       LayoutInflater inflator = activity.getLayoutInflater();  
	      if(convertView==null)  
	       {  
	           view = new Fila();
	           Model_Drawer_Items itm=arrayitms.get(position);
	           convertView = inflator.inflate(R.layout.drawer_items , null);
	           view.drawer_title = (TextView) convertView.findViewById(R.id.drawer_title);
	           view.drawer_title.setText(itm.getTitle());           
	           view.drawer_icon = (ImageView) convertView.findViewById(R.id.drawer_icon);
	           view.drawer_icon.setImageResource(itm.getIcon());           
	           convertView.setTag(view);  
	        }  
	        else  
	        {  
	           view = (Fila) convertView.getTag();  
	        }  
	        return convertView;  
	    }
	   
}
