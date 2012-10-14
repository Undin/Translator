package ru.sixteenfourteen.translater;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	Context context;
	Button translateButton;
	public boolean sec = false;

	public static boolean isOneWord(String word) {
		for (int i = 0; i < word.length(); i++){
			if (word.charAt(i) < 'A' || word.charAt(i) > 'Z' && word.charAt(i) < 'a' || word.charAt(i) > 'z')
				return false;
		}
		return true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Button click
		this.translateButton = (Button) this.findViewById(R.id.translateButton);
		this.translateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TextView wordToTranslate = (TextView) findViewById(R.id.engWord);
				if (isOneWord(wordToTranslate.getText().toString())) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra("engWord", wordToTranslate.getText()
							.toString());
					startActivity(intent);
					finish();
				} else {
					TextView label = (TextView) findViewById(R.id.label1);
					label.setText("ONLY ONE ENGLISH WORD ALLOWED!");
					label.setTextColor(Color.RED);
				}	
			}
		});
	}
}