package com.example.listentomusic.module;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;

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
import com.example.listentomusic.model.FSMusicInfo;
import com.example.listentomusic.model.MusicModelManager;
import com.example.listentomusic.util.CommonConst;
import com.example.listentomusic.util.StringRequestEx;

public class SoSoSearchMusicClient extends BaseSearchClient {
	private RequestQueue mVolleyQueue;
	@Override
	public void searchMusic(Context context, final String strKeyword) 
	{


		mVolleyQueue = Volley.newRequestQueue(context); 
		
		
//		  List<NameValuePair> qparams = newArrayList<NameValuePair>();
//		    qparams.add(new BasicNameValuePair("xh","xxxxx"));
//		    qparams.add(new BasicNameValuePair("xm","孙悟空"));
//		    qparams.add(new BasicNameValuePair("NO","xxxxxxx"));
//		    URI uri = URIUtils.createURI("http","jwxt.xxxx.edu.cn", -1, "/xxxxx.asp"",
//		    URLEncodedUtils.format(qparams, "UTF-8"),null); //到这块设置编码
//		    HttpGet httpget = new HttpGet(uri);
		
		String strKeyword2=strKeyword;
		
		
		try {
			//url = URLEncoder.encode(url,"UTF-8");
			strKeyword2 = URLEncoder.encode(strKeyword,"UTF-8");
//			url=URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = String.format("http://soso.music.qq.com/fcgi-bin/music_json.fcg?catZhida=1&lossless=1&json=1&w=%s&num=100&t=y1&p=1&utf8=1",strKeyword2);

		StringRequestEx stringRequest = new StringRequestEx(Request.Method.GET, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) 
			{
				System.out.print(response);
				JSONObject dataJsonObject= formatSOSOReceivedData(response);
				//fillModel(strKeyword,dataJsonObject);
				try {
					fillModel(strKeyword, dataJsonObject);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

				if( error instanceof NetworkError) {
					android.util.Log.w("1111", "222222");
				} 
				
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
	protected void fillModel(String keyString,JSONObject dataJsonObject) throws JSONException 
	{
		
		MusicModelManager musicmodelmanager=MusicModelManager.getInstance();
		if (!keyString.equals(musicmodelmanager.getMusicKeyString())) 
		{
			return;
		}

		JSONArray songlist= dataJsonObject.getJSONArray("list");//获取JSONArray 
		
        int length = songlist.length();  

        for(int i = 0; i < length; i++){//遍历JSONArray  
//            Log.d("debugTest",Integer.toString(i));  
            JSONObject oj = songlist.getJSONObject(i);  
            String fString=oj.getString("f");
            FSMusicInfo musicInfo = new FSMusicInfo();
            String[] arr=fString.split("\\|");
            if( arr.length>10) 
            {
				musicInfo.songId=arr[0];
				musicInfo.title=arr[1];
				musicInfo.artist=arr[3];
				musicInfo.albumId=arr[4];
				musicInfo.album=arr[5];
				
				String durationString= arr[7];
				int intDuration=Integer.parseInt(durationString);
				musicInfo.duration=String.format("%02d:%02d",intDuration/60, intDuration%60);
				
				musicInfo.location=arr[8];
				int albumIdInt = Integer.parseInt(musicInfo.albumId);
				
				musicInfo.albumPictureUrlString=String.format("http://imgcache.qq.com/music/photo/album/%d/albumpic_%d_0.jpg",albumIdInt%100, albumIdInt);
				
					
				int intlocation = Integer.parseInt(musicInfo.location);
				String sourceUrlString="";
				if (intlocation>=10) {
					sourceUrlString = String.format("http://stream%s.qqmusic.qq.com/3%07d.mp3",musicInfo.location, Integer.parseInt(musicInfo.songId));
				}
				else {
					sourceUrlString = String.format("http://stream1%s.qqmusic.qq.com/3%07d.mp3",musicInfo.location, Integer.parseInt(musicInfo.songId));					
				}
				
				musicInfo.sourceUrlStr=sourceUrlString;
			}
            if (musicInfo.sourceUrlStr.isEmpty()) {
				continue;
			}
            musicmodelmanager.addMusicInfo(musicInfo);
        }  

        //发送广播消息
	    
        Intent intent = new Intent(CommonConst.ACTION_SEARCHMUSIC);

        LocalBroadcastManager.getInstance(ListenToMusicApp.getInstance()).sendBroadcast(intent);
		
	}
	private JSONObject formatSOSOReceivedData(String aimString)  {		
		JSONObject dataJson=null;
		Pattern pattern = Pattern.compile("^searchJsonCallback\\(.*\\)$",Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(aimString);
		System.out.println(matcher.matches());
		boolean bisMatch=matcher.matches();
		if (bisMatch) {

			Pattern pattern2 = Pattern.compile("^searchJsonCallback\\(|\\)$", Pattern.DOTALL);
			Matcher matcher2 = pattern2.matcher(aimString);
			String string = matcher2.replaceAll("");
			System.out.println(string);
	
			try {
				 dataJson = new JSONObject(string);
				 
				 int d=0;
				 d++;
				 
				 android.util.Log.i("json", dataJson.toString()); 

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return dataJson;
		}
		
		
		
		
		
		
	    return dataJson;
	}
	

	
	static SoSoSearchMusicClient createSearchMusicClient()
	{
		return new SoSoSearchMusicClient();
		
	}
}
