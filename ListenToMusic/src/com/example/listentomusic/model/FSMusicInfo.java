package com.example.listentomusic.model;



public class FSMusicInfo extends Object {

	public enum MusicType {
		audioType, vedioType
	};

	//
	// @interface FSMusicInfo : FSModelObject
	public String title; // title !!!!!
	public String artist; // artist !!!!!
	public String album; // albumName !!!!!
	public String duration; // 总播放时长
	public String sourceUrlStr; // 下载链接
	public double curTime; // 当前播放时间
	public double musicType; // 当前播放时间
	//
	// // 以上必须设置
	public String imageURLString;
	public String albumPictureUrlString; // 专辑封面url !!!!!
	//
	public String location;
	//
	//
	//
	//
	public String songId;
	public String albumId;
	public String authorId;
	public String authorName;
	public String lrcLinkUrl;

}
