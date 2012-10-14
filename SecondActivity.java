package ru.sixteenfourteen.translater;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class SecondActivity extends Activity {
	Context context;
	private ImageView imgView;

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(SecondActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
		return;
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		context = getApplicationContext();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		// Translation
		String engWord = getIntent().getExtras().getString("engWord");
		String ruWord = engWord;
		TextView initalWord = (TextView) findViewById(R.id.initalWord);
		initalWord.setText(engWord);
		try {
			ruWord = new Translater().getTranslate(engWord);
		} catch (Exception e) {
			e.printStackTrace();
			ruWord = "Check your Internet connection";
		}
		TextView rusWord = (TextView) findViewById(R.id.rusWord);
		rusWord.setText(ruWord);

		// image from URL
		String url, fun = "http://airs-design.ru/meme/imgs/efdf2591.jpeg";
		// get images function request
//		String urls[] = new String[10];
		String urls[] = new PicturesFinder().getURLs(engWord, 10);

		imgView = (ImageView) findViewById(R.id.img0);
		if (urls[0] == null)
			url = fun;
		else
			url = urls[0];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img1);
		if (urls[1] == null)
			url = fun;
		else
			url = urls[1];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img2);
		if (urls[2] == null)
			url = fun;
		else
			url = urls[2];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img3);
		if (urls[3] == null)
			url = fun;
		else
			url = urls[3];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img4);
		if (urls[4] == null)
			url = fun;
		else
			url = urls[4];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img5);
		if (urls[5] == null)
			url = fun;
		else
			url = urls[5];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img6);
		if (urls[6] == null)
			url = fun;
		else
			url = urls[6];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img7);
		if (urls[7] == null)
			url = fun;
		else
			url = urls[7];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img8);
		if (urls[8] == null)
			url = fun;
		else
			url = urls[8];
		new DownloadImageTask(imgView).execute(url);

		imgView = (ImageView) findViewById(R.id.img9);
		if (urls[9] == null)
			url = fun;
		else
			url = urls[9];
		new DownloadImageTask(imgView).execute(url);
	}
}