package abz.chand.cliptune;

import java.net.URI;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordVideoActivity extends Activity {

	private RecordVideo recordVideo;
	private MediaPlayer player; 

	/** Called when the activity is first created. */ 
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_record_video); 

		player = MediaPlayer.create(this, new Uri.Builder().path("http://api.7digital.com/1.2/track/preview?trackid=8515447&country=UK&oauth_consumer_key=7dz2ey9y6h3y").build());		
		
		
		final TextView timeLeft = (TextView) findViewById(R.id.timeLeft);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		recordVideo = (RecordVideo) findViewById(R.id.camcorder_preview);
		recordVideo.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction()==MotionEvent.ACTION_DOWN) {
					recordVideo.pauseRecording();
					player.start();
					return true;
				}
				
				if (event.getAction()==MotionEvent.ACTION_MOVE) {
					if (recordVideo.getTime() >= 0){
						timeLeft.setText(""+ recordVideo.getTime());
					} else {
						player.stop();
						player.release();
					}
					return true;
				}

				if (event.getAction()==MotionEvent.ACTION_UP){
					recordVideo.pauseRecording();
					player.pause();
					return true;
				}

				return false;
			}
		});
		
		final ImageView send = (ImageView) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecordVideoActivity.this, SendActivity.class);
				List<StartStop> startStopTimes = recordVideo.getStartStopTimes();
				long[] startTimes = new long[startStopTimes.size()];
				long[] stopTimes = new long[startStopTimes.size()];
				for (int i = 0; i < startStopTimes.size(); i++){
					startTimes[i]=startStopTimes.get(i).getStartTime();
					stopTimes[i]=startStopTimes.get(i).getStopTime();
				}
				
				intent.putExtra("startTimes", startTimes);
				intent.putExtra("stopTimes", stopTimes);
				startActivity(intent);
				finish();
			}
		});
		
		final ImageView cancel = (ImageView) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	} 

	@Override
	protected void onStop() {	
		super.onStop();
		player.stop();
		player.release();
		recordVideo.stopRecording();
	}
}
