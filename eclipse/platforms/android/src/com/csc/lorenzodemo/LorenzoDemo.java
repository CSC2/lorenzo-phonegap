/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.csc.lorenzodemo;

import java.io.File;

import org.apache.cordova.CordovaActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class LorenzoDemo extends CordovaActivity {

	private static final int SPLASH_TIME = 2000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// super.setIntegerProperty("splashscreen", R.drawable.icon);
		// super.loadUrl("file:///android_asset/www/index.html");
		// super.loadUrl("http://lorenzo-pbryzek.dotcloud.com", SPLASH_TIME);

		super.init();
		super.clearCache();
		super.clearHistory();

		String url = "file:///android_asset/www/Index.html";
		if (isSdPresent()) {
			String path = "/Lorenzo/Index.html";
					
			File dir = Environment.getExternalStorageDirectory();
			File f = new File(dir, path);
			
			if (f.exists()) {
				url = "file:///" + dir + path;
				createToast("HTML found on sdcard reading from there.");
			} else {
				createToast("SD card is mounted, but " + path + " not found on sdcard.  Reading compiled html.");
			}
		} else {
			createToast("SD card is not mounted, reading compiled html.");
		}

		super.loadUrl(url);
	}
	
	private void createToast(String text){
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}

	public static boolean isSdPresent() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

	}

}
