package com.example.listentomusic.module;

import com.example.listentomusic.util.CommonConst;

public class MusicClientFactory {
//	+(FSBaseMusicClient*)createMusicClient : (NSString*)strClientName;
//
//	+(FSBaseLrcClient*)createLrcClient : (NSString*)strClientName;
	
	static BaseSearchClient createMusicClient(String strClientName)
	{
		
		BaseSearchClient client=null;
		
		if (strClientName.equals(CommonConst.sosoSearchMusic)) {
			client = SoSoSearchMusicClient.createSearchMusicClient();
		}
		else if (strClientName.equals(CommonConst.ttpodSearchMusic)) {
			client = TtpodSearchMusicClient.createSearchMusicClient();
		}		
	    return client;

	}
}
