package com.strendent.tutorsu;




import com.joshdholtz.sentry.Sentry;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.parse.Parse;
import com.strendent.tutorsu.Utilities.Constants_MixPannel;
import com.strendent.tutorsu.Utilities.Constants_Sentry;

import android.app.Application;

public class TutorsUApplication extends Application {

	 public static final String TAG = "TutorsU";


	@Override
	public void onCreate() {
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "seelmA8JpbqmYqYFEGAJ4CGRII1US0xSToAwPPXZ", "knS8cOSNmViSzuL5SrAp27JwczHTBn9JMGQ5LuOE");
		Sentry.init(this.getApplicationContext(), Constants_Sentry.sentryDns);


//		   Parse.initialize(this, 
//			        "eb5cbf2f0fc36a3cbd83ff7bc7500b98",
//			        "knS8cOSNmViSzuL5SrAp27JwczHTBn9JMGQ5LuOE"
//			    );
	}

	
	
}
