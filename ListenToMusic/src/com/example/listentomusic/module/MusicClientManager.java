package com.example.listentomusic.module;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;

import com.example.listentomusic.model.MusicModelManager;
import com.example.listentomusic.module.BaseSearchClient;
import com.example.listentomusic.util.CommonConst;
public class MusicClientManager {
	public   Map<String, BaseSearchClient>   mMusicClientMap = 
			new HashMap<String, BaseSearchClient>();

    private static MusicClientManager uniqueInstance = null;  
    
    public MusicClientManager() {
		init();
	}
   
    
    private void init() {
		
    	initData();
	}
    
    private void initData(){
    	
    	loadMusicClientList();
    }
    
    private void loadMusicClientList()
    {
    	ArrayList<String> searchMusicClientArrayList= new ArrayList<String>();
    	searchMusicClientArrayList.add(CommonConst.sosoSearchMusic);
    	searchMusicClientArrayList.add(CommonConst.ttpodSearchMusic);
    	
    	for(int i = 0;i < searchMusicClientArrayList.size(); i ++)
    	{
    		String searchMusicNameString= searchMusicClientArrayList.get(i);
    		BaseSearchClient client=MusicClientFactory.createMusicClient(searchMusicNameString);
    		mMusicClientMap.put(searchMusicNameString, client);
    	}
    }
    
    

    
    public static MusicClientManager getInstance() {  
       if (uniqueInstance == null) {  
           uniqueInstance = new MusicClientManager();  
       }  
       return uniqueInstance;  
    }
    
//    -(void)searchMusic:(NSString*)strKeyword
//    {
//        for (FSBaseMusicClient* musicClient in [self.musicClientDic allValues]) {
//            
//            if (musicClient) {
//                [musicClient searchMusic:strKeyword];
//            }
//        }
//
//    }
    
    public void searchMusic(Context context, String keywordString) {
		
    	MusicModelManager  musicmodelmanager = MusicModelManager.getInstance();
    	musicmodelmanager.setMusicKeyString(keywordString);  
    	musicmodelmanager.clearMusic();
    	Iterator<Entry<String, BaseSearchClient>> iter = mMusicClientMap.entrySet().iterator();

    	while (iter.hasNext()) {

    		BaseSearchClient searchClient = iter.next().getValue();
    		searchClient.searchMusic(context,keywordString);
    	}
    	
	}
    
}
