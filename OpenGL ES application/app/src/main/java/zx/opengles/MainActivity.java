package zx.opengles;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

/**
 @author Marek Kulawiak
 */

public class MainActivity extends Activity
{
	protected ESSurfaceView glSurfaceView;
	private Button orangeBtn ;
	private Button whiteBtn;
	SeekBar seekBar;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		glSurfaceView = new ESSurfaceView(this);

		setContentView(R.layout.activity_main);
		View tempView = findViewById(R.id.tempView);
		ViewGroup parent = (ViewGroup) tempView.getParent();
		int index = parent.indexOfChild(tempView);
		parent.removeView(tempView);
		parent.addView(glSurfaceView, index);

		orangeBtn = (Button)findViewById(R.id.lightOrangeButton);
		whiteBtn = (Button)findViewById(R.id.lightWhiteButton);
		seekBar = (SeekBar)findViewById(R.id.lightSeekBar);

		orangeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				float a [] = {1.0f,0.4f,0.2f,1.0f};
        		glSurfaceView.renderer.lightCol = a;
			}
		});

		whiteBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				float a [] = {1.0f,1.0f,1.0f,1.0f};
				glSurfaceView.renderer.lightCol = a;
			}
		});

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
										  boolean fromUser) {
				// TODO Auto-generated method stub
				glSurfaceView.renderer.specExp = (float)progress*2;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	protected void onResume() 
	{
		// The activity must call the GL surface view's onResume() on activity onResume().
		super.onResume();
		glSurfaceView.onResume();
	}

	@Override
	protected void onPause() 
	{
		// The activity must call the GL surface view's onPause() on activity onPause().
		super.onPause();
		glSurfaceView.onPause();
	}
}
