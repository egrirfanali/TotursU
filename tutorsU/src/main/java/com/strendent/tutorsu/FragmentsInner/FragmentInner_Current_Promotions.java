package com.strendent.tutorsu.FragmentsInner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.strendent.tutorsu.R;
import android.widget.EditText;
import android.widget.Button;
/**
 * Created by user on 6/18/2015.
 */
public class FragmentInner_Current_Promotions extends Fragment {

    private View mView;
    EditText editTextCode;
    Button btnSubmitCode;

    public FragmentInner_Current_Promotions(){
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            mView = inflater.inflate(R.layout.fragment_main_promotions_current_tab_layout, container, false);
        initView();
            btnSubmitCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Your code is added?").setTitle("Congratulation").show();
                }
            });


        return mView;
    }
    private void initView() {

        if (mView != null) {
        editTextCode=(EditText) mView.findViewById(R.id.ed_promotion_code);
        btnSubmitCode=(Button) mView.findViewById(R.id.submit_button);

        }

    }
}