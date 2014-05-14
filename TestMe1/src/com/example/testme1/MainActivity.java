package com.example.testme1;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void onClick(View v)
    {
		
        switch(v.getId()){
        case R.id.Button01:
        	Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        	Bundle b = new Bundle();
    		b.putString("cat", "MATH");
    		intent.putExtras(b);
			startActivity(intent);
			finish();
        	break;
        case R.id.Button02:
        	Intent intent2 = new Intent(MainActivity.this, QuizActivity.class);
        	Bundle b2 = new Bundle();
    		b2.putString("cat", "SCIENCE");
    		intent2.putExtras(b2);
			startActivity(intent2);
			finish();
        	break;
        case R.id.Button03:
        	Intent intent3 = new Intent(MainActivity.this, QuizActivity.class);
        	Bundle b3 = new Bundle();
    		b3.putString("cat", "HISTORY");
    		intent3.putExtras(b3);
			startActivity(intent3);
			finish();
        	break;
        case R.id.Button04:
        	Intent intent4 = new Intent(MainActivity.this, QuizActivity.class);
        	Bundle b4 = new Bundle();
    		b4.putString("cat", "POLITICS");
    		intent4.putExtras(b4);
			startActivity(intent4);
			finish();
        	break;
        	
        }
    } 

}