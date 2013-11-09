package abz.chand.cliptune;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

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
		ImageView imageView;
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		        imageView.setImageResource(mThusmbIds[position]);
//		imageView.setImageURI(new Uri.Builder().path(mThumbUrls[position]).build());
		return imageView;
	}

	// references to our images
	private Integer[] mThusmbIds = {
			R.drawable.search, R.drawable.search,
			R.drawable.forward, R.drawable.forward
	};

	private String[] mThumbUrls = {
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg",
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg",
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg",
			"http://cdn.7static.com/static/img/sleeveart/00/028/943/0002894390_50.jpg"
	};

}