package com.example.testme1;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	private EditText user, pass;
	private Button mSubmit, mRegister;

	 // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //php login script location:

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";

    //testing on Emulator:
    private static final String LOGIN_URL = "http://192.168.56.1/webservice/login.php";

  //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";

    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		//setup input fields
		user = (EditText)findViewById(R.id.editText1);
		pass = (EditText)findViewById(R.id.editText2);

		
		//setup buttons
		mSubmit = (Button)findViewById(R.id.button1);
		mRegister = (Button)findViewById(R.id.button1);

		//register listeners
		mSubmit.setOnClickListener(this);
		mRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			if(user.getText().equals("")||user==null){
				user.setError("USER NAME NOT ENTERED");
			}
			else if(pass.getText().equals("")||pass==null){
				user.setError("Password NOT ENTERED");
			}
			else{
				//new AttemptLogin().execute();
			}
			
			break;
		/*case R.id.button1:
				Intent i = new Intent(this, Register.class);
				startActivity(i);
			break;*/

		default:
			break;
		}
	}

	class AttemptLogin extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                       LOGIN_URL, "POST", params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Login Successful!", json.toString());
                	Intent i = new Intent(LoginActivity.this, MainActivity.class);
                	finish();
    				startActivity(i);
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

		}
		/**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }

	}

}

//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.*;
//
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import android.os.Build;
//
//public class LoginActivity extends ActionBarActivity {
//
//	private EditText user, pass;
//	private Button mSubmit, mRegister;
//
//	 // Progress Dialog
//    private ProgressDialog pDialog;
//
//    // JSON parser class
//    JSONParser jsonParser = new JSONParser();
//
//    //php login script location:
//
//    //localhost :
//    //testing on your device
//    //put your local ip instead,  on windows, run CMD > ipconfig
//    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
//   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";
//
//    //testing on Emulator:
//    private static final String LOGIN_URL = "http://192.168.56.1/webservice/login.php";
//
//  //testing from a real server:
//    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";
//
//    //JSON element ids from repsonse of php script:
//    private static final String TAG_SUCCESS = "success";
//    private static final String TAG_MESSAGE = "message";
//	
//	
//	String url_all_products="http://192.168.56.1:80/webservice/login.php";
//	JSONParser jParser = new JSONParser();
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_login);
//
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
//		return true;
//	}
//	public void onClick(View v)
//    {
//		user=(EditText)findViewById(R.id.editText1);
//		pass=(EditText)findViewById(R.id.editText2);
//		String user1=user.getText().toString();
//		if(user1.equals("")||user1==null){
//			
//			user.setError("Username Empty");
//		}
//
//		else{            
//            
//			new AttemptLogin().execute();
//			
//		}
//		
//		
//    }
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_login,
//					container, false);
//			return rootView;
//		}
//	}
//	
//	class AttemptLogin extends AsyncTask<String, String, String> {
//
//		 /**
//        * Before starting background thread Show Progress Dialog
//        * */
//		boolean failure = false;
//
//       @Override
//       protected void onPreExecute() {
//           super.onPreExecute();
//           pDialog = new ProgressDialog(LoginActivity.this);
//           pDialog.setMessage("Attempting login...");
//           pDialog.setIndeterminate(false);
//           pDialog.setCancelable(true);
//           pDialog.show();
//       }
//
//		@Override
//		protected String doInBackground(String... args) {
//			// TODO Auto-generated method stub
//			 // Check for success tag
//           int success;
//           String username = user.getText().toString();
//           String password = pass.getText().toString();
//           try {
//               // Building Parameters
//               List<NameValuePair> params = new ArrayList<NameValuePair>();
//               params.add(new BasicNameValuePair("username", username));
//               params.add(new BasicNameValuePair("password", password));
//
//               Log.d("request!", "starting");
//               // getting product details by making HTTP request
//               JSONObject json = jsonParser.makeHttpRequest(
//                      LOGIN_URL, "POST", params);
//
//               // check your log for json response
//               Log.d("Login attempt", json.toString());
//
//               // json success tag
//               success = json.getInt(TAG_SUCCESS);
//               if (success == 1) {
//               	Log.d("Login Successful!", json.toString());
//               	
//               	finish();
//   				
//               	return json.getString(TAG_MESSAGE);
//               }else{
//               	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
//               	return json.getString(TAG_MESSAGE);
//
//               }
//           } catch (JSONException e) {
//               e.printStackTrace();
//           }
//
//           return null;
//
//		}
//		/**
//        * After completing background task Dismiss the progress dialog
//        * **/
//       protected void onPostExecute(String file_url) {
//           // dismiss the dialog once product deleted
//           pDialog.dismiss();
//           if (file_url != null){
//           	Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
//           }
//
//       }
//
//	}
//
//}
