package com.example.listentomusic.model;

import java.util.ArrayList;
import java.util.List;

import com.example.listentomusic.common.MusicControl;



public class MusicModelManager  {
	
    private static MusicModelManager uniqueInstance = null;  
    public static MusicModelManager getInstance() {  
        if (uniqueInstance == null) {  
            uniqueInstance = new MusicModelManager();  
        }  
        return uniqueInstance;  
     }
	
	
	
	
	
	
	private String musicKeyString;

	public String getMusicKeyString() {
		return musicKeyString;
	}
	public void setMusicKeyString(String musicKeyString) {
		this.musicKeyString = musicKeyString;
	}
	private List<FSMusicInfo> mSearchFsMusicInfos = new ArrayList<FSMusicInfo>();
	public List<FSMusicInfo> getSearchFsMusicInfos() {
		return mSearchFsMusicInfos;
	}
	public void setSearchFsMusicInfos(List<FSMusicInfo> searchFsMusicInfos) {
		mSearchFsMusicInfos = searchFsMusicInfos;
	}


	public void clearMusic() {
		mSearchFsMusicInfos.clear();
	}

	public void addMusicInfo(FSMusicInfo fsmusicinfo) {
		if(!mSearchFsMusicInfos.contains(fsmusicinfo))
		{
			mSearchFsMusicInfos.add(fsmusicinfo);
		}
	}



	
}
