package com.example.listentomusic.module;

import android.content.Context;

public class TtpodSearchMusicClient extends BaseSearchClient {

	@Override
	public void searchMusic(Context context,String strKeyword) {
		// TODO Auto-generated method stub
		
	}
	static TtpodSearchMusicClient createSearchMusicClient()
	{
		return new TtpodSearchMusicClient();
		
	}
}
