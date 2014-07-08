package com.example.listentomusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TransferFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_transfer, container,
				false);
		
		
		
		
		initView(rootView);
		
		return rootView;
	}
	
	private void initView(View v)
	{
		
		
	}

}
