package com.example.listentomusic.util;

import java.io.UnsupportedEncodingException;

import org.apache.http.protocol.HTTP;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

public class StringRequestEx extends StringRequest {

	public StringRequestEx(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {

		super(method, url, listener, errorListener);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // TODO Auto-generated method stub
        String str = null;
        String strTypeString=response.headers.get(HTTP.CONTENT_TYPE);
        try {
            str = new String(response.data,"GB18030");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
		
//		try {
//            String type = response.headers.get(HTTP.CONTENT_TYPE);
//            if (type == null) {
//                Log.d(LOG_TAG, "content type was null");
//                type = TYPE_UTF8_CHARSET;
//                response.headers.put(HTTP.CONTENT_TYPE, type);
//            } else if (!type.contains("UTF-8")) {
//                Log.d(LOG_TAG, "content type had UTF-8 missing");
//                type += ";" + TYPE_UTF8_CHARSET;
//                response.headers.put(HTTP.CONTENT_TYPE, type);
//            }
//        } catch (Exception e) {
//            //print stacktrace e.g.
//        }
    }
	
}
