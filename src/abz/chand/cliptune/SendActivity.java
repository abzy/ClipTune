package abz.chand.cliptune;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import abz.chand.cliptune.VideoDto.Clip;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.MovieBox;
import com.google.gson.Gson;
import com.googlecode.mp4parser.authoring.Movie;

public class SendActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		long[] startTimes = getIntent().getLongArrayExtra("startTimes");
		long[] stopTimes = getIntent().getLongArrayExtra("stopTimes");

		setContentView(R.layout.activity_sending);		

		File file = new File("/sdcard/cliptune.mp4");

		//		byte[] b = new byte[(int) file.length()];


		try {

			//			FileInputStream fileInputStream = new FileInputStream(file);
			//			fileInputStream.read(b);
			//			byte[] b = ByteStreams.toByteArray(fileInputStream);
//			Log.e("b","aaaa:" + b.length);

//			FileChannel fc = new FileInputStream(file).getChannel();
			IsoFile isoFile = new IsoFile("/sdcard/cliptune.mp4");
			MovieBox moov = isoFile.getMovieBox();

			VideoDto videoDto = new VideoDto(b, "8515447");
			List<Clip> clips = new ArrayList<Clip>(); 
			for (int i = 0; i < startTimes.length; i++){
				clips.add(videoDto.new Clip(startTimes[i], stopTimes[i]));
			}
			videoDto.setClips(clips);

			new AddCliptuneServerTask().execute(new Gson().toJson(videoDto));

			File filew = new File("/sdcard/lala.mp4");
			FileOutputStream fos = new FileOutputStream(filew);
			fos.write(b);
			fos.close();

		} catch (FileNotFoundException e) {
			Log.e("vvv","Error");
			e.printStackTrace();
		}
		catch (IOException e1) {
			Log.e("vvv","Error");
			e1.printStackTrace();
		}
	} 


	public byte[] readBytes(InputStream inputStream) throws IOException {
		// this dynamically extends to take the bytes you read
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		// this is storage overwritten on each iteration with bytes
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		// we need to know how may bytes were read to write them to the byteBuffer
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}

		// and then we can return your byte array.
		return byteBuffer.toByteArray();
	}
}
