package com.example.listentomusic;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.listentomusic.R.id;



import android.os.Bundle;

//import android.support.v7.widget.SearchView;
//import android.support.v7.widget.*;
//import android.support.v7.widget.SearchView.SearchAutoComplete;

//import android.support.v7.widget.SearchView;
//import android.support.v7.widget.SearchView.SearchAutoComplete;


import android.support.v4.app.Fragment;
//import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

//import android.view.ViewGroup.MarginLayoutParams;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;


public class SearchMusicFragment extends Fragment implements SearchView.OnQueryTextListener{
	
	private List<String> data ;
	private ArrayAdapter<String> adapter;
	private SearchView searchView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_searchmusic, container,
				false);
		
		
		
		
		initView(rootView);
		
		return rootView;
	}
	
	private void initView(View v)
	{
		 searchView = (SearchView)v.findViewById(R.id.searchMusic);
        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        searchPlate.getBackground().setAlpha(0);
        int textViewSearch = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText textViewSearchView = (EditText)searchView.findViewById(textViewSearch);
        textViewSearchView.setGravity(Gravity.BOTTOM);
       

        ListView listview=(ListView)v.findViewById(R.id.lvSearchMusicResult);
//    	listview.setTextFilterEnabled(true) ;
		data = new ArrayList<String>() ;
		data.add("Hello"); data.add("Hebe") ; data.add("nick") ; data.add("ahha") ; data.add("jack") ; data.add("hello") ; data.add("welcome") ;
		data.add("Hello"); data.add("Hebe") ; data.add("nick") ; data.add("ahha") ; data.add("jack") ; data.add("hello") ; data.add("welcome") ;
		adapter = new ArrayAdapter<String>(getActivity(), R.layout.searchmusicitem, data);
		listview.setAdapter(adapter) ;
		
//		searchView.setOnClickListener()
		searchView.setOnQueryTextListener(this);
//		searchView.setSubmitButtonEnabled(true);
	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		Log.i("onQueryTextChange",arg0);
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		Log.i("onQueryTextSubmit",arg0);
		searchView.clearFocus();
		
		return true;
	}

}
