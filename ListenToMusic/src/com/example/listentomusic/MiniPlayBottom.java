package com.example.listentomusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MiniPlayBottom extends Fragment 
{
	
	
//    <ImageButton
//    android:id="@+id/btn_miniPlay"
//    android:layout_width="40dp"
//    android:layout_height="40dp"
//	android:layout_gravity="center"
//	android:layout_marginLeft="10dp"
//    android:background="@drawable/mini_btnplay" />
//
//<ImageButton
//android:id="@+id/btn_miniNext"
//android:layout_width="30dp"
//android:layout_height="30dp"
//android:layout_gravity="center"
//android:layout_marginLeft="10dp"
//android:background="@drawable/mini_btnnext" />
	
	private ImageButton playbImageButton=null;
	private ImageButton nextbImageButton=null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_playbottom, container,
				false);
		
		
		
		
		initView(rootView);
		
		return rootView;
	}
	
	private void initView(View v)
	{
		playbImageButton = (ImageButton)v.findViewById(R.id.btn_miniPlay);
		
		playbImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ListenToMusicApp.getInstance().getPlayerEngineInterface().isPlaying())
				{
					ListenToMusicApp.getInstance().getPlayerEngineInterface().pause();
					
				}
				else {
					ListenToMusicApp.getInstance().getPlayerEngineInterface().play();
				}
			}
		});

		
		
		nextbImageButton = (ImageButton)v.findViewById(R.id.btn_miniNext);
		nextbImageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ListenToMusicApp.getInstance().getPlayerEngineInterface().next();
			}
		});
	}
}
