package com.example.listentomusic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.android.volley.RequestQueue;
import com.android.volley.Cache.Entry;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.example.listentomusic.R.id;
import com.example.listentomusic.common.MusicControl;
import com.example.listentomusic.model.FSMusicInfo;
import com.example.listentomusic.model.MusicModelManager;
import com.example.listentomusic.util.CommonConst;





import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

//import android.support.v7.widget.SearchView;
//import android.support.v7.widget.*;
//import android.support.v7.widget.SearchView.SearchAutoComplete;

//import android.support.v7.widget.SearchView;
//import android.support.v7.widget.SearchView.SearchAutoComplete;


import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
//import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

//import android.view.ViewGroup.MarginLayoutParams;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;


public class SearchMusicFragment extends Fragment implements SearchView.OnQueryTextListener{
	
	private List<String> data ;
	//private ArrayAdapter<String> adapter;
	private SearchView searchView;
	private LocalBroadcastManager mBroadcastManager;
	private IntentFilter intentFilter;
	private SearchMusicAdapter searchmusicadapter=null;
	
	
	

	
	private BroadcastReceiver receiver =new BroadcastReceiver() {
		
		 
		
		@Override
		
		public void onReceive(Context context, Intent intent) {
	
//		int num = intent.getIntExtra("name", 0);//获得后台传过来的，键值为name对应的值

//		tv.setText(num+"");//更新主界面UI
			Log.i("1111","onReceive=================>");
			
			if(searchmusicadapter!=null)
			{
				searchmusicadapter.notifyDataSetChanged();
				
			}
		}

	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_searchmusic, container,
				false);
		
		
		
		initData();
		initView(rootView);
		
		
		return rootView;
	}
	
	private void initData()
	{
		mBroadcastManager = LocalBroadcastManager.getInstance(this.getActivity());
		   intentFilter = new IntentFilter();
	        intentFilter.addAction(CommonConst.ACTION_SEARCHMUSIC);
	        searchmusicadapter=new SearchMusicAdapter(this.getActivity());
	        
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
//		data = new ArrayList<String>() ;
//		data.add("Hello"); data.add("Hebe") ; data.add("nick") ; data.add("ahha") ; data.add("jack") ; data.add("hello") ; data.add("welcome") ;
//		data.add("Hello"); data.add("Hebe") ; data.add("nick") ; data.add("ahha") ; data.add("jack") ; data.add("hello") ; data.add("welcome") ;
//		adapter = new ArrayAdapter<String>(getActivity(), R.layout.searchmusicitem, data);
		listview.setAdapter(searchmusicadapter) ;
		
//		searchView.setOnClickListener()
		searchView.setOnQueryTextListener(this);
//		searchView.setSubmitButtonEnabled(true);
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		mBroadcastManager.registerReceiver(receiver, intentFilter);
	}

	@Override
	public void onStop()
	{
		super.onStop();
		mBroadcastManager.unregisterReceiver(receiver);//取消动态注册
	}
	
	@Override
	public boolean onQueryTextChange(String arg0) {
		Log.i("onQueryTextChange",arg0);
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
//		Log.i("onQueryTextSubmit",arg0);
		if (arg0.length()==0) {
			return true;
		}
		MusicControl.getInstance().searchMusic(getActivity(),arg0);
		
		searchView.clearFocus();
		
		return true;
	}
	
	
	
	/*
	 * Extends from DisckBasedCache --> Utility from volley toolbox.
	 * Also implements ImageCache, so that we can pass this custom implementation
	 * to ImageLoader. 
	 */
	public  class DiskBitmapCache extends DiskBasedCache implements ImageCache {
		 
	    public DiskBitmapCache(File rootDirectory, int maxCacheSizeInBytes) {
	        super(rootDirectory, maxCacheSizeInBytes);
	    }
	 
	    public DiskBitmapCache(File cacheDir) {
	        super(cacheDir);
	    }
	 
	    public Bitmap getBitmap(String url) {
	        final Entry requestedItem = get(url);
	 
	        if (requestedItem == null)
	            return null;
	 
	        return BitmapFactory.decodeByteArray(requestedItem.data, 0, requestedItem.data.length);
	    }
	 
	    public void putBitmap(String url, Bitmap bitmap) {
	        
	    	final Entry entry = new Entry();
	        
/*			//Down size the bitmap.If not done, OutofMemoryError occurs while decoding large bitmaps.
 			// If w & h is set during image request ( using ImageLoader ) then this is not required.
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Bitmap downSized = BitmapUtil.downSizeBitmap(bitmap, 50);
			
			downSized.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] data = baos.toByteArray();
	        entry.data = data ; */
//			
//	        entry.data = BitmapUtil.convertBitmapToBytes(bitmap) ;
	    	entry.data = com.example.listentomusic.util.BitmapUtil.convertBitmapToBytes(bitmap);
	        put(url, entry);
	    }
	}
	
    public final class MusicItemViewHolder{  
        public ImageView songImg;  
        public TextView songTitleTextView;  
        public TextView songArtistTextView;  

    }  

    public class SearchMusicAdapter extends BaseAdapter{

    	private LayoutInflater mInflater;  
        private MusicModelManager musicmodelmanager=MusicModelManager.getInstance();
        
    	private RequestQueue mVolleyQueue;
    	private ImageLoader mImageLoader;
        
        public SearchMusicAdapter(Context context){  
            this.mInflater = LayoutInflater.from(context); 
            initData(context);
        }  
        
        private void initData(Context context){
    		mVolleyQueue = Volley.newRequestQueue(context);

    		int max_cache_size = 1000000;
    		mImageLoader = new ImageLoader(mVolleyQueue, new DiskBitmapCache(context.getCacheDir(),max_cache_size));
    		
    		
    		Log.i("mImageLoader",mImageLoader.toString());

        }
    	
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			int count=0;
			count = musicmodelmanager.getSearchFsMusicInfos().size();
			return count;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MusicItemViewHolder holder = null;  
            if (convertView == null) {  
                   
                holder=new MusicItemViewHolder();   
                   
                convertView = mInflater.inflate(R.layout.musiclist_item, null);  
                holder.songImg = (ImageView)convertView.findViewById(R.id.imgSong);  
                holder.songTitleTextView = (TextView)convertView.findViewById(R.id.songTitle);  
                holder.songArtistTextView = (TextView)convertView.findViewById(R.id.songArtist);  
                convertView.setTag(holder);  
                   
            }else {  
                   
                holder = (MusicItemViewHolder)convertView.getTag();  
            }  

            
            FSMusicInfo  fsmusicinfo= musicmodelmanager.getSearchFsMusicInfos().get(position);
            String imageSourceUrlString = fsmusicinfo.albumPictureUrlString;
               


               
            ImageListener listener = ImageLoader.getImageListener(holder.songImg, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);    
            mImageLoader.get(imageSourceUrlString, listener);    
               
            holder.songTitleTextView.setText(fsmusicinfo.title);
            holder.songArtistTextView.setText(fsmusicinfo.artist);
            
            return convertView; 
		}
    	
    }
    
}
