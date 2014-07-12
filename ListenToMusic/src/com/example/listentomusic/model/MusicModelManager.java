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
	
	public List<FSMusicInfo> mSearchFsMusicInfos = new ArrayList<FSMusicInfo>();
	
}
