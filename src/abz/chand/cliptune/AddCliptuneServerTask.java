package abz.chand.cliptune;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

class AddCliptuneServerTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... videoDto) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://10.100.93.72:8080/video/add/123");

	    try {
	    	StringEntity s = new StringEntity(videoDto[0]);
	    	s.setContentType("application/json");
	        httppost.setEntity(s);
	        HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			String responseText = EntityUtils.toString(entity);
	        Log.e("C",responseText);
			return responseText;
	    } catch (Exception e){
	    	Log.e("B", "AAA" + e.toString());
	    }

	    return "Fail";
    }
  

    protected void onPostExecute(String response) {
    	
    }
}