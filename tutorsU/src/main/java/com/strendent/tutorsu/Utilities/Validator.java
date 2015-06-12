package com.strendent.tutorsu.Utilities;

import android.view.View;
import android.widget.EditText;

public class Validator {

	public static boolean etReqiurd(View v){

		if(v instanceof EditText){

			EditText et=(EditText) v;
			if(et!=null && !et.getText().toString().equals("")){
				return true;
			}

		}
		return false;

	}
}
