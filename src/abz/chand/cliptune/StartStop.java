package abz.chand.cliptune;

import android.os.Parcel;
import android.os.Parcelable;

public class StartStop implements Parcelable{

	private long startTime;
	private long stopTime;
	
	public StartStop(long startTime) {
		this.startTime = startTime;
	}

	public void setStopTime(long stopTime){
		this.stopTime = stopTime;
	}
	
	public long getStartTime() {
		return startTime;
	}

	public long getStopTime() {
		return stopTime;
	}

	public long getDuration() {
		return stopTime-startTime;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}	
	
}
