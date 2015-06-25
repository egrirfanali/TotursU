package com.strendent.tutorsu;


import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.parse.Parse;

public class TutorsUApplication extends Application {

	 public static final String TAG = "TutorsU";
	 
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "seelmA8JpbqmYqYFEGAJ4CGRII1US0xSToAwPPXZ", "knS8cOSNmViSzuL5SrAp27JwczHTBn9JMGQ5LuOE");
	
//		   Parse.initialize(this, 
//			        "eb5cbf2f0fc36a3cbd83ff7bc7500b98",
//			        "knS8cOSNmViSzuL5SrAp27JwczHTBn9JMGQ5LuOE"
//			    );

		initImageLoader(getApplicationContext());
	}


	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app

		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}
	
	
}
