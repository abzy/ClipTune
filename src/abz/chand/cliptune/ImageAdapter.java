package abz.chand.cliptune;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbUrls.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout relativeLayout;
		ImageView imageView;
		
		if (convertView == null) {  
			relativeLayout = new RelativeLayout(mContext);
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			imageView.setScaleType(ImageView.ScaleType.FIT_START);
			imageView.setPadding(8, 8, 8, 8);
//			imageView.setImageResource(mThusmbIds[position]);
			imageView.setImageURI(new Uri.Builder().encodedPath(mThumbUrls[position]).build());
//			imageView.setBackgroundColor(0x55000000);
			
			Log.e("AAA","MAAA"+new Uri.Builder().encodedPath(mThumbUrls[position]).build());
			
			relativeLayout.addView(imageView);

		} else {
			relativeLayout = (RelativeLayout) convertView;

//			imageView = (ImageView) convertView;
		}

				
		return relativeLayout;
	}

	// references to our images
	private Integer[] mThusmbIds = {
			R.drawable.track1, R.drawable.track3,
			R.drawable.track2, R.drawable.track4
	};

	private String[] mThumbUrls = {
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg",
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg",
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg",
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg"
	};

}