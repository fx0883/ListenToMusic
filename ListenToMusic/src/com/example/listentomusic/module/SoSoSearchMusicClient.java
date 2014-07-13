package com.example.listentomusic.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;

import android.R.bool;
import android.content.Context;

import com.android.volley.AuthFailureError;
//import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.mani.volleydemo.ssl.SslHttpStack;
import com.example.listentomusic.ListenToMusicApp;
import com.example.listentomusic.util.StringRequestEx;

public class SoSoSearchMusicClient extends BaseSearchClient {
	private RequestQueue mVolleyQueue;
	@Override
	public void searchMusic(Context context,String strKeyword) 
	{
		// TODO Auto-generated method stub
	//	"http://soso.music.qq.com"
		
		//"/fcgi-bin/music_json.fcg?catZhida=1&lossless=1&json=1&w=%@&num=30&t=y1&p=1&utf8=1


//		private final String TAG_REQUEST = "MY_TAG";
//		RequestQueue mVolleyQueue = Volley.newRequestQueue(getApplicationContext());
//		RequestQueue mVolleyQueue = Volley.newRequestQueue(ListenToMusicApp.getListenToMusicApp()); 
		mVolleyQueue = Volley.newRequestQueue(context); 
		//String url = String.format("http://soso.music.qq.com/fcgi-bin/music_json.fcg?catZhida=1&lossless=1&json=1&w=%s&num=30&t=y1&p=1&utf8=1",strKeyword);
		String url = String.format("http://soso.music.qq.com/fcgi-bin/music_json.fcg?catZhida=1&lossless=1&json=1&w=%s&num=30&t=y1&p=1&utf8=1","wangfei");
		StringRequestEx stringRequest = new StringRequestEx(Request.Method.GET, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) 
			{
				System.out.print(response);
				formatSOSOReceivedData(response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// Handle your error types accordingly.For Timeout & No connection error, you can show 'retry' button.
				// For AuthFailure, you can re login with user credentials.
				// For ClientError, 400 & 401, Errors happening on client side when sending api request.
				// In this case you can check how client is forming the api and debug accordingly.
				// For ServerError 5xx, you can do retry or handle accordingly.
				if( error instanceof NetworkError) {
					android.util.Log.w("1111", "222222");
				} 
//				else if( error instanceof ClientError) 
//				{ 
//				} 
				
				else if( error instanceof ServerError) {
				} else if( error instanceof AuthFailureError) {
				} else if( error instanceof ParseError) {
				} else if( error instanceof NoConnectionError) {
				} else if( error instanceof TimeoutError) {
				}

//				stopProgress();
//				showToast(error.getMessage());
			}
		});

		//To EXPERIMENT. Enable response caching to quickly fetch the response from cache, if set true.
		//Volley decides whether to cache the response or not, based on response headers obtained. Some of the parameters
		//to look for are Cache-control,maxAge, Expires.
		//
		//In case of weather api, the response headers has "Cache-Control: no-cache, must-revalidate" In this case, even if 
		//setShouldCache() api is set true, Volley decides not to store the response, because server has sent response headers as "must-revalidate"
		//So storing response doesn't make sense in this api. Some of these intelligences are implemented already in Volley, you need not take the burden of
		//parsing response headers.
		
		stringRequest.setShouldCache(true);
//		stringRequest.setTag("MYTAG");	
		mVolleyQueue.add(stringRequest);
	}
	private String formatSOSOReceivedData(String aimString)  {
	  
//	    if ([aimString hasPrefix:@"searchJsonCallback("] && [receiveString hasSuffix:@")"]) {
//	        NSString *resultString = [[receiveString substringToIndex:receiveString.length-1] substringFromIndex:19];
//	        return resultString;
//	    } else {
//	        NSLog(@"搜索歌曲时，解析出的数据出现问题");
//	        return nil;
//	    }
		

		Pattern pattern = Pattern.compile("^searchJsonCallback\\(.*\\)$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(aimString);
		System.out.println(matcher.matches());
		boolean bisMatch=matcher.matches();
		if (bisMatch) {

			Pattern pattern2 = Pattern.compile("^searchJsonCallback\\(", Pattern.DOTALL);
			Matcher matcher2 = pattern.matcher(aimString);
			String string = matcher2.replaceAll("1111");
			System.out.println(string);
			return string;
		}
		
		
		
		
		
		
	    return aimString;
	}
	
//	private void makeSampleHttpRequest() {
//		
//		String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk";
//		
//		StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//			@Override
//			public void onResponse(String response) {
//				mResultView.setText(response);
//				stopProgress();
//			}
//		}, new Response.ErrorListener() {
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				// Handle your error types accordingly.For Timeout & No connection error, you can show 'retry' button.
//				// For AuthFailure, you can re login with user credentials.
//				// For ClientError, 400 & 401, Errors happening on client side when sending api request.
//				// In this case you can check how client is forming the api and debug accordingly.
//				// For ServerError 5xx, you can do retry or handle accordingly.
//				if( error instanceof NetworkError) {
//				} else if( error instanceof ClientError) { 
//				} else if( error instanceof ServerError) {
//				} else if( error instanceof AuthFailureError) {
//				} else if( error instanceof ParseError) {
//				} else if( error instanceof NoConnectionError) {
//				} else if( error instanceof TimeoutError) {
//				}
//
//				stopProgress();
//				showToast(error.getMessage());
//			}
//		});
//
//		//To EXPERIMENT. Enable response caching to quickly fetch the response from cache, if set true.
//		//Volley decides whether to cache the response or not, based on response headers obtained. Some of the parameters
//		//to look for are Cache-control,maxAge, Expires.
//		//
//		//In case of weather api, the response headers has "Cache-Control: no-cache, must-revalidate" In this case, even if 
//		//setShouldCache() api is set true, Volley decides not to store the response, because server has sent response headers as "must-revalidate"
//		//So storing response doesn't make sense in this api. Some of these intelligences are implemented already in Volley, you need not take the burden of
//		//parsing response headers.
//		
//		stringRequest.setShouldCache(true);
//		stringRequest.setTag(TAG_REQUEST);	
//		mVolleyQueue.add(stringRequest);
//	}
	
	static SoSoSearchMusicClient createSearchMusicClient()
	{
		return new SoSoSearchMusicClient();
		
	}
}
