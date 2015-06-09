package com.strendent.tutorsu.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Activities.Activity_SignUp;

public class Fragment_SignUp extends Fragment {

	private EditText edtEmail;
	private EditText edtPassword;
	View view;
	private ImageView imageViewProceed;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view=inflater.inflate(R.layout.fragment_sign_up, null);
		
		initView();

		imageViewProceed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(edtEmail!=null && edtPassword!=null && !edtEmail.getText().toString().equals("") && 
						!edtPassword.getText().toString().equals("")){

					Intent intent=new Intent(getActivity(), Activity_SignUp.class);
					intent.putExtra("CLICK_SIGNUP", 2);
					intent.putExtra("EMAIL", edtEmail.getText().toString());
					intent.putExtra("PASSWORD", edtPassword.getText().toString());
					startActivity(intent);
				}else {
					Toast.makeText(getActivity(), getResources().getString(R.string.please_reqiured_fields), Toast.LENGTH_LONG).show();
				}
			}
		});

		// For enabling the proceed button
		edtPassword.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        if (actionId == EditorInfo.IME_ACTION_DONE) {
		            
		        	if(edtEmail.getText().toString().length()>0 && edtPassword.getText().toString().length()>0){
		        		
		        		imageViewProceed.setImageDrawable(getResources().getDrawable(R.drawable.ic_next_enabled));
		        		
		        	}
		        	
		        }
		        return false;
		    }
		});

		return view;
	}

	private void initView(){

		if(view!=null){
			edtEmail=(EditText) view.findViewById(R.id.edtEmail);
			edtPassword=(EditText) view.findViewById(R.id.edtPassword);
			imageViewProceed=(ImageView) view.findViewById(R.id.imageViewProceed);
		}
	}

	public void onProceedClick(View view){

		if(edtEmail!=null && edtPassword!=null && !edtEmail.getText().toString().equals("") && 
				!edtPassword.getText().toString().equals("")){

			Intent intent=new Intent(getActivity(), Activity_SignUp.class);
			intent.putExtra("EMAIL", edtEmail.getText().toString());
			intent.putExtra("PASSWORD", edtPassword.getText().toString());
			startActivity(intent);
		}else {
			Toast.makeText(getActivity(), getResources().getString(R.string.please_reqiured_fields), Toast.LENGTH_LONG).show();
		}

	}

}