package sm.com.httprequestsample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MyActivity extends Activity {

    JSONObject result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        new NetworkCall().execute();
    }

    public class NetworkCall extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String url = "https://api.uwaterloo.ca/v2/foodservices/menu.json?key=8cfbc3bab4ba44063542969015b3e56d";
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try{
                HttpResponse httpResponse = httpClient.execute(httpGet);
                String output = inputStreamToString(httpResponse.getEntity().getContent()).toString();
                Log.d("OUTPUT", output);
                Log.d("OUTPUT", "TEST");
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }

    public static StringBuilder inputStreamToString(InputStream is){
        String line;
        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}
