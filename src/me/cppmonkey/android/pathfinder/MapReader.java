package me.cppmonkey.android.pathfinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class MapReader {
	private Context mContext;

	public MapReader(Context context){
		mContext = context;
	}

	public Integer[] load(String fileName){
		List<Integer> result = new ArrayList<Integer>();
		try {
			InputStream instream = mContext.getAssets().open(fileName);
			InputStreamReader instreamreader = new InputStreamReader(instream);
			BufferedReader br = new BufferedReader(instreamreader);
			try{
				int ch = -1;
				while((ch = br.read()) != -1 ){
					if(ch == '0'){
						result.add(R.drawable.black);
					}else if(ch == '1'){
						result.add(R.drawable.white);	
					}else if(ch == '2'){
						result.add(R.drawable.green);
					}else if(ch == '3'){
						result.add(R.drawable.blue);
					}else if(ch == '\r' || ch == '\n' ){
						// do nothing
					}else{
						result.add(R.drawable.redstart);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				br.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Convert from List to Array
		Integer[] ret = new Integer[result.size()];
		for(int i = 0;i < ret.length;i++)
			ret[i] = result.get(i);
		return ret;
	}
}
