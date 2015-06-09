package com.strendent.tutorsu.Activities;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.strendent.tutorsu.R;

public class Activity_VerifyCode extends Activity {

	private EditText etCode;
	private Context context;
	private Button btnVerifyCode;
	private Button btnResendCode;
	private TextView tvVerified;
	String phoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity__verify_code);

		initViews();

		context=Activity_VerifyCode.this;
		//		etCode=(EditText)findViewById(R.id.etCode);

		Bundle bundle=getIntent().getExtras();
		phoneNumber=bundle.getString("PHONE_NUMBER", "121313");
		
		

		etCode.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

				btnVerifyCode.setEnabled(true);
			}
		});


	}
	
	
	
	public void onClickEtCode(View view){
		
		etCode.setText("");
		etCode.setTextColor(Color.BLACK);
		
	}

	private void initViews(){
		etCode=(EditText)findViewById(R.id.etCode);
		btnVerifyCode=(Button)findViewById(R.id.btnVerifyCode);
		btnResendCode=(Button)findViewById(R.id.btnResendCode);
		tvVerified=(TextView)findViewById(R.id.tvVerified);

	}

	public void onClickVerifyCode(View view){

		// If Phone number is verified then it will prompt to Sweet(Welcome)  screen
		if(btnVerifyCode.getTag().equals("ic_log_confirm")){
			Intent intent=new Intent(context, Activity_Verified.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
		
		if(com.strendent.tutorsu.Utilities.Validator.etReqiurd(etCode)){

			Map<String, String> map=new HashMap<String, String>();
			map.put("phoneNumber", phoneNumber);
			map.put("codeEntry", etCode.getText().toString());

			ParseCloud.callFunctionInBackground("logIn", map,new FunctionCallback<Object>() {

				@Override
				public void done(Object object, ParseException e) {

					if(e==null){
						btnVerifyCode.setBackground(getResources().getDrawable(R.drawable.ic_log_confirm));
						btnVerifyCode.setTag("ic_log_confirm");
						tvVerified.setVisibility(View.VISIBLE);
						tvVerified.setText(getResources().getString(R.string.you_are_verified));
					}else{
						btnVerifyCode.setBackground(getResources().getDrawable(R.drawable.ic_error));
						btnVerifyCode.setTag("ic_error");
						tvVerified.setVisibility(View.VISIBLE);
						tvVerified.setText(getResources().getString(R.string.re_enter_or_request_new_code));
//						tvVerified.setTextColor(Color.red(12));
						etCode.setText(getResources().getString(R.string.re_enter_code));
						etCode.setTextColor(Color.RED);
					}

				}
			});

		}else
			Toast.makeText(context, "Please enter phone number", Toast.LENGTH_LONG).show();

	}

	public void onClickResendCode(View view){
		Map<String, String> map=new HashMap<String, String>();
		map.put("phoneNumber", phoneNumber);

		ParseCloud.callFunctionInBackground("sendCode", map,new FunctionCallback<Object>() {

			@Override
			public void done(Object object, ParseException e) {

				if(e==null){

					Toast.makeText(context, "Verification code has been re-sent", Toast.LENGTH_LONG).show();

				}else{

				}
			}
		});
	}


//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity__mobile_varifecation, menu);
//		return true;
//	}


}
