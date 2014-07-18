package com.example.listentomusic.module;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
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
import com.example.listentomusic.ListenToMusicApp;
import com.example.listentomusic.model.FSMusicInfo;
import com.example.listentomusic.model.MusicModelManager;
import com.example.listentomusic.util.CommonConst;
import com.example.listentomusic.util.StringRequestEx;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class TtpodSearchMusicClient extends BaseSearchClient {
	private RequestQueue mVolleyQueue;
	@Override
	public void searchMusic(Context context, final String strKeyword) 
	{

		mVolleyQueue = Volley.newRequestQueue(context); 
		
		String strKeyword2=strKeyword;
		
		
		try {
			//url = URLEncoder.encode(url,"UTF-8");
			strKeyword2 = URLEncoder.encode(strKeyword,"UTF-8");
//			url=URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//String url = String.format("http://soso.music.qq.com/fcgi-bin/music_json.fcg?catZhida=1&lossless=1&json=1&w=%s&num=30&t=y1&p=1&utf8=1",strKeyword);
		String url = String.format("http://so.ard.iyyin.com/v2/songs/search?q=%s&page=1&size=50",strKeyword2);
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) 
			{
				System.out.print(response);
				JSONObject dataJsonObject= formatTtpodReceivedData(response);
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

			}
		});
	
		stringRequest.setShouldCache(true);

		mVolleyQueue.add(stringRequest);
	}
	
	private JSONObject formatTtpodReceivedData(String aimString)  {		
		JSONObject dataJson=null;

	
			try {
				 dataJson = new JSONObject(aimString);
				 android.util.Log.i("json", dataJson.toString()); 

			} catch (JSONException e) {

				e.printStackTrace();
			}  
			return dataJson;
		
	}
	
	protected void fillModel(String keyString,JSONObject dataJsonObject) throws JSONException 
	{
		
		MusicModelManager musicmodelmanager=MusicModelManager.getInstance();
		if (!keyString.equals(musicmodelmanager.getMusicKeyString())) 
		{
			return;
		}

		
//		 FSMusicInfoManager *musicinfomanager=[FSMusicInfoManager sharedMusicInfoManager];
//		    if (![strkey isEqualToString:musicinfomanager.searchkey]) {
//		        return;
//		    }
//		    
//		        for (NSDictionary *songDic in searhArray)
//		        {
//		            NSString *artist = [songDic objectForKey:@"singer_name"];
//		            NSString *albumName = [songDic objectForKey:@"album_name"];
//		            NSString *songName = [songDic objectForKey:@"song_name"];
//		            NSArray *urlList = [songDic objectForKey:@"url_list"];
//		            NSString *duration = nil;
//		            NSString *size = nil;
//		            NSString *urlStr = nil;
//		            for (NSDictionary *sourceInfo in urlList)
//		            {
//		                NSString *format = [sourceInfo objectForKey:@"format"];
//		                NSNumber *bitNumber = [sourceInfo objectForKey:@"bitrate"];
//		                if ([format isEqualToString:@"mp3"] && [bitNumber intValue]==64)
//		                { // 64, 128， 压缩 品质
//		                    duration = [sourceInfo objectForKey:@"duration"];
//		            //        size = [sourceInfo objectForKey:@"size"];
//		                    urlStr = [sourceInfo objectForKey:@"url"];
//		                    
//		                    if (urlStr==nil)
//		                    {
//		                        int k=333;
//		                        k++;
//		                        urlStr = [sourceInfo objectForKey:@"url"];
//		                    }
//		                    
//		                    break;
//		                }
//		            }
//		            //TODO: need photo
//		            NSLog(@"%s, urlStr:%@, title:%@",__func__,urlStr, songName);
//		            
//		            FSMusicInfo *musicInfo = [[FSMusicInfo alloc] init];
//		            
//		            musicInfo.title = songName;
//		            musicInfo.artist = artist;
//		            musicInfo.album = albumName;
//		            musicInfo.duration = duration;
//		            musicInfo.sourceUrlStr = urlStr;
//		            
//		            if (musicInfo.sourceUrlStr==nil) {
//		                continue;
//		            }
//		            
//		            assert(musicInfo.sourceUrlStr);
//		            [musicinfomanager.searchMusicArray addObject:musicInfo];
//		            
//		            
//
//		        }
//
//		    [[NSNotificationCenter defaultCenter] postNotificationName:kSearchMusicResult
//		                                                        object:nil];

		
		
		
		
		
		
	    
		JSONArray songlist= dataJsonObject.getJSONArray("data");//获取JSONArray 
		
        int length = songlist.length();  

        for(int i = 0; i < length; i++){//遍历JSONArray  
//            Log.d("debugTest",Integer.toString(i));  
            JSONObject oj = songlist.getJSONObject(i);  
            
            String artist = oj.getString("singer_name");
            String albumName = oj.getString("album_name");
            String songName = oj.getString("song_name"); 
            JSONArray  urlList = oj.getJSONArray("url_list");
      //      NSArray *urlList = [songDic objectForKey:@"url_list"];
            String duration = null;
            String size = null;
            String urlStr = null;
 
            for(int j = 0; j < urlList.length();j++)
            {
            	 JSONObject obj = urlList.getJSONObject(j);  
            	 String format = obj.getString("format");
                 int bitNumber =obj.getInt("bitrate");
                 
                 if (format.equals("mp3") && (bitNumber==64||bitNumber==128))
                 {
                	 duration = obj.getString("duration");
                	 urlStr = obj.getString("url");
                	 
                	 break;
                 }
                 
//                 if ([format isEqualToString:@"mp3"] && [bitNumber intValue]==64)
//                 { // 64, 128， 压缩 品质
//                     duration = [sourceInfo objectForKey:@"duration"];
//             //        size = [sourceInfo objectForKey:@"size"];
//                     urlStr = [sourceInfo objectForKey:@"url"];
//                     
//                     if (urlStr==nil)
//                     {
//                         int k=333;
//                         k++;
//                         urlStr = [sourceInfo objectForKey:@"url"];
//                     }
//                     
//                     break;
//                 }
            	
            }
            
            
//            for (NSDictionary *sourceInfo in urlList)
//            {
//                NSString *format = [sourceInfo objectForKey:@"format"];
//                NSNumber *bitNumber = [sourceInfo objectForKey:@"bitrate"];
//                if ([format isEqualToString:@"mp3"] && [bitNumber intValue]==64)
//                { // 64, 128， 压缩 品质
//                    duration = [sourceInfo objectForKey:@"duration"];
//            //        size = [sourceInfo objectForKey:@"size"];
//                    urlStr = [sourceInfo objectForKey:@"url"];
//                    
//                    if (urlStr==nil)
//                    {
//                        int k=333;
//                        k++;
//                        urlStr = [sourceInfo objectForKey:@"url"];
//                    }
//                    
//                    break;
//                }
//            }
//            //TODO: need photo
//            NSLog(@"%s, urlStr:%@, title:%@",__func__,urlStr, songName);
//            
            FSMusicInfo musicInfo = new FSMusicInfo();
//            
            musicInfo.title = songName;
            musicInfo.artist = artist;
            musicInfo.album = albumName;
            musicInfo.duration = duration;
            musicInfo.sourceUrlStr = urlStr;
            
            if (musicInfo.sourceUrlStr ==null || musicInfo.sourceUrlStr.isEmpty()) {
                continue;
            }            

            musicmodelmanager.addMusicInfo(musicInfo);
        }  

        //发送广播消息
	    
        Intent intent = new Intent(CommonConst.ACTION_SEARCHMUSIC);
        LocalBroadcastManager.getInstance(ListenToMusicApp.getInstance()).sendBroadcast(intent);
		
	}
	
	static TtpodSearchMusicClient createSearchMusicClient()
	{
		return new TtpodSearchMusicClient();
		
	}
}
