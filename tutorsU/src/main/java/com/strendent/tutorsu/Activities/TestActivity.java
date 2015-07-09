package com.strendent.tutorsu.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.strendent.tutorsu.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Map<String, String> map = new HashMap<String, String>();
        map.put("firstName", "irfan");
        map.put("lastName", "ali");
        map.put("email", "irfan.ali@strendent.com");
        map.put("phone", "5555555555");
        map.put("zipCodeode", "44000");
        map.put("dob", "1988-01-07");
        map.put("ssn", "111-11-2001");
        map.put("driverLicenseNumber", "F1112001");
        map.put("driverLicenseState", "CA");

        ParseCloud.callFunctionInBackground("createCheckrCandidate", map, new FunctionCallback<Object>() {

            @Override
            public void done(Object object, ParseException e) {

                JSONObject jsonObj = null;
                String candidateId = null;
                String package1 = "driver";

                if (e == null) {
                    String string = (String) object;
                    try {
                        jsonObj = new JSONObject(string);
                        candidateId = jsonObj.getString("id");
                        Map<String, String> reportMap = new HashMap<String, String>();
                        reportMap.put("candidateId", candidateId);
                        reportMap.put("package", "driver_pro");

                        ParseCloud.callFunctionInBackground("createCheckrReport", reportMap, new FunctionCallback<Object>() {

                            @Override
                            public void done(Object object, ParseException e) {

                            }
                        });


                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });

        //Calling braintree implementaion
        /*Map<String, String> map=new HashMap<String, String>();
        map.put("emailaddress", "irfan@test.com");

        ParseCloud.callFunctionInBackground("registerCustomer", map, new FunctionCallback<Object>() {

            @Override
            public void done(Object object, ParseException e) {

                JSONObject jsonObj=null;
                String customerID = null;
                if (e == null) {

                    String string= (String) object;
                    try {
                         jsonObj = new JSONObject(string);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        customerID=jsonObj.getJSONObject("success").getString("customer_id");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                    Map<String, String> map=new HashMap<String, String>();
                    map.put("customer_id", customerID);
                    ParseCloud.callFunctionInBackground("clientToken", map, new FunctionCallback<Object>() {

                        @Override
                        public void done(Object object, ParseException e) {

                            if (e == null) {

                                JSONObject jsonObject1= null;
                                try {
                                    jsonObject1 = new JSONObject(object.toString());
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                                try {
                                    onBraintreeSubmit(jsonObject1.getJSONObject("success").getString("client_token"));
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }

                            } else {

                            }
                        }
                    });

                }
                else {

                    Toast.makeText(TestActivity.this,e.getMessage(),Toast.LENGTH_LONG);
                    Log.d("Tutors",e.getMessage());

                }
            }
        });*/
    }



    // ParseCloud.callFunctionInBackground("testFunction", map);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
