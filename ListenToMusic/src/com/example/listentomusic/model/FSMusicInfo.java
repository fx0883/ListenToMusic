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
	public String duration; // �ܲ���ʱ��
	public String sourceUrlStr; // ��������
	public double curTime; // ��ǰ����ʱ��
	public double musicType; // ��ǰ����ʱ��
	//
	// // ���ϱ�������
	public String imageURLString;
	public String albumPictureUrlString; // ר������url !!!!!
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
