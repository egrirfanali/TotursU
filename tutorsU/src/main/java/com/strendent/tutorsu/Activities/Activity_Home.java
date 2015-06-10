package com.strendent.tutorsu.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.strendent.tutorsu.Adapters.Adpater_DrawerAdapter;
import com.strendent.tutorsu.FragmentMian.Fragment_About;
import com.strendent.tutorsu.FragmentMian.Fragment_Become_A_Tutor;
import com.strendent.tutorsu.FragmentMian.Fragment_FavouriteLocations;
import com.strendent.tutorsu.FragmentMian.Fragment_MyFamily;
import com.strendent.tutorsu.FragmentMian.Fragment_Payments;
import com.strendent.tutorsu.FragmentMian.Fragment_Profile;
import com.strendent.tutorsu.FragmentMian.Fragment_Promotions;
import com.strendent.tutorsu.FragmentMian.Fragment_Share;
import com.strendent.tutorsu.FragmentMian.Fragment_TrustedTutors;
import com.strendent.tutorsu.FragmentMian.Fragment_Tutions;
import com.strendent.tutorsu.Models.Model_Drawer_Items;
import com.strendent.tutorsu.R;

import java.util.ArrayList;

public class Activity_Home extends Activity {
	private String[] titles;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ArrayList<Model_Drawer_Items> drawerItems;
	private TypedArray drawerIcons;
	Adpater_DrawerAdapter drawerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main);
		setupDrawer();
		drawerList.setOnItemClickListener(new DrawerItemClickListener());
	}

	public void setupDrawer() {
		try {
			drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			drawerList = (ListView) findViewById(R.id.drawer_list);

			View header = getLayoutInflater().inflate(R.layout.drawer_header, null);
			drawerList.addHeaderView(header);
			drawerIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
			titles = getResources().getStringArray(R.array.nav_options);
			drawerItems = new ArrayList<Model_Drawer_Items>();
			drawerItems.add(new Model_Drawer_Items(titles[0], drawerIcons.getResourceId(0, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[1], drawerIcons.getResourceId(1, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[2], drawerIcons.getResourceId(2, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[3], drawerIcons.getResourceId(3, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[4], drawerIcons.getResourceId(4, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[5], drawerIcons.getResourceId(5, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[6], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[7], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[8], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[9], drawerIcons.getResourceId(6, -1)));
			drawerItems.add(new Model_Drawer_Items(titles[10], drawerIcons.getResourceId(6, -1)));
			drawerAdapter = new Adpater_DrawerAdapter(this, drawerItems);
			drawerList.setAdapter(drawerAdapter);
		} catch (NotFoundException e) {

			e.printStackTrace();
		}
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}

		private void selectItem(int position) {

			switch (position) {
			case 1:
				Toast.makeText(Activity_Home.this, "1 Options Clicked", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(Activity_Home.this, "2 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentTrustedTutors = new Intent(Activity_Home.this, Fragment_TrustedTutors.class);
				startActivity(intentFragmentTrustedTutors);
				break;
			case 3:
				Toast.makeText(Activity_Home.this, "3 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentMyFamily = new Intent(Activity_Home.this, Fragment_MyFamily.class);
				startActivity(intentFragmentMyFamily);

				break;
			case 4:
				Toast.makeText(Activity_Home.this, "4 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentFavLocations = new Intent(Activity_Home.this, Fragment_FavouriteLocations.class);
				startActivity(intentFragmentFavLocations);

				break;
			case 5:
				Toast.makeText(Activity_Home.this, "5 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentTutions = new Intent(Activity_Home.this, Fragment_Tutions.class);
				startActivity(intentFragmentTutions);
				break;
			case 6:
				Toast.makeText(Activity_Home.this, "6 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentProfile = new Intent(Activity_Home.this, Fragment_Profile.class);
				startActivity(intentFragmentProfile);

				break;
			case 7:
				Toast.makeText(Activity_Home.this, "7 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentPayments = new Intent(Activity_Home.this, Fragment_Payments.class);
				startActivity(intentFragmentPayments);

				break;
			case 8:
				Toast.makeText(Activity_Home.this, "8 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentShare = new Intent(Activity_Home.this, Fragment_Share.class);
				startActivity(intentFragmentShare);

				break;
			case 9:
				Toast.makeText(Activity_Home.this, "9 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentPromotions = new Intent(Activity_Home.this, Fragment_Promotions.class);
				startActivity(intentFragmentPromotions);

				break;
			case 10:
				Toast.makeText(Activity_Home.this, "10 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentAbout = new Intent(Activity_Home.this, Fragment_About.class);
				startActivity(intentFragmentAbout);

				break;
			case 11:
				Toast.makeText(Activity_Home.this, "11 Options Clicked", Toast.LENGTH_SHORT).show();
				Intent intentFragmentBecomeAtutor = new Intent(Activity_Home.this, Fragment_Become_A_Tutor.class);
				startActivity(intentFragmentBecomeAtutor);

				break;

			default:
				break;
			}

		}

	}

}
