package ru.ivansamborskiy.translateenru;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Title");
		try {
			builder.setMessage(new Translater().getTranslate("development"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		builder.setPositiveButton("OK", null);
		AlertDialog dialog = builder.show();

		TextView messageView = (TextView) dialog
				.findViewById(android.R.id.message);
		messageView.setGravity(Gravity.CENTER);
	}
}
