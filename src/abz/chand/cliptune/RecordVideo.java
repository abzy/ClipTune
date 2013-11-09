package abz.chand.cliptune;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RecordVideo extends SurfaceView implements SurfaceHolder.Callback {

	MediaRecorder recorder;
	SurfaceHolder holder;
	private long time;
	
	private List<StartStop> startStopTimes = new ArrayList<StartStop>();
	
	private String outputFile = "/sdcard/cliptune.mp4";

	public RecordVideo(Context context, AttributeSet attrs) {
		super(context, attrs);

		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		startRecording();
	}

	private void foo(SurfaceHolder holder){
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
		recorder.setVideoSize(640, 480);
		recorder.setOrientationHint(90);
		// recorder.setVideoFrameRate(15);
		//		recorder.setMaxDuration(20000);
		recorder.setOutputFile(outputFile);
		recorder.setPreviewDisplay(holder.getSurface());
		if (recorder != null) {
			try {
				recorder.prepare();
			} catch (IllegalStateException e) {
				Log.e("IllegalStateException", e.toString());
			} catch (IOException e) {
				Log.e("IOException", e.toString());
			}
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	boolean started = false;

	public void startRecording()
	{		
		if (!started){
			foo(holder);
			try {
				recorder.start();
				time = System.currentTimeMillis();
				started = true;
			} catch (Exception e){

			}
		}
	}

	boolean recording = false;
	
	public void pauseRecording(){
		if (!recording){
			recording = true;
			Log.e("LLL", "AAA:" + (System.currentTimeMillis()-time));

			getStartStopTimes().add(new StartStop(System.currentTimeMillis()-time));
		} else {
			
			Log.e("LLL", "BBB:" + (System.currentTimeMillis()-time));
			recording = false;			
			getStartStopTimes().get(getStartStopTimes().size()-1).setStopTime(System.currentTimeMillis()-time);
		}
	}
	
	public int getTime(){
		long duration = 0;
		for (int i = 0; i < getStartStopTimes().size(); i++){
			if (i == (getStartStopTimes().size()-1)){
				duration += ((System.currentTimeMillis()-time) - getStartStopTimes().get(i).getStartTime());
			} else {
				duration += getStartStopTimes().get(i).getDuration();
			}
		}
		
		int timeLeft = (int) ((20000 - duration)/1000);
		if (timeLeft <= 0){
			getStartStopTimes().get(getStartStopTimes().size()-1).setStopTime(System.currentTimeMillis()-time);
			stopRecording();
		}
		
		if (recording){
			return timeLeft; 
		}
		return -1;
	}
	
	public void stopRecording()
	{
		if (started){
			try {
				recorder.stop();
				recorder.release();
				started = false;
			} catch (Exception e){

			}
		}
	}

	public List<StartStop> getStartStopTimes() {
		return startStopTimes;
	}	
	
}

