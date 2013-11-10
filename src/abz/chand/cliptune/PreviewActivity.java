package abz.chand.cliptune;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import abz.chand.cliptune.VideoDto.Clip;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.gson.Gson;

public class PreviewActivity extends Activity{

	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_play);
		
		VideoView preview = (VideoView) findViewById(R.id.preview);
		String viewSource ="/sdcard/cliptune.mp4";
		preview.setVideoURI(Uri.parse(viewSource));
		preview.setMediaController(new MediaController(this));
		preview.requestFocus();
		preview.start();		

	} 

}
