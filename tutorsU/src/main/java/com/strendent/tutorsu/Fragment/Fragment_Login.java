package com.strendent.tutorsu.Fragment;

import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.strendent.tutorsu.R;
import com.strendent.tutorsu.Activities.Activity_Home;
import com.strendent.tutorsu.Activities.Activity_SignUp;

public class Fragment_Login extends Fragment {

	private EditText edtEmail;
	private EditText edtPassword;
	private ImageView imageViewProceed;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view=inflater.inflate(R.layout.fragment_login, null);

		initView();

		imageViewProceed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(edtEmail!=null && edtPassword!=null && !edtEmail.getText().toString().equals("") &&
						!edtPassword.getText().toString().equals("")){

					Map<String, String> params=new HashMap<String, String>();
					params.put("phoneNumber", edtEmail.getText().toString());
					params.put("codeEntry", edtPassword.getText().toString());

					ParseCloud.callFunctionInBackground("logIn", params,new FunctionCallback<Object>() {

						@Override
						public void done(Object object, ParseException e) {

							if(e==null){
								Intent intent=new Intent(getActivity(), Activity_Home.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								startActivity(intent);

							}else{
								Toast.makeText(getActivity(), getResources().getString(R.string.invalid_username_pwd), Toast.LENGTH_LONG).show();
							}
						}
					});


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




}