package cl.roman.apprandomusersv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    public static final String URL="https://randomuser.me/api/?results=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processHttp();

    }

    public void processHttp(){
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                Log.d("INFO", res);

                processJson(res);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void processJson(String res){
        try {
            JSONObject root = new JSONObject(res);

            JSONArray results = root.getJSONArray("results");

            for(int i=0; i<results.length();i++) {

                JSONObject user1 = results.getJSONObject(i);

                String gender = user1.getString("gender");
                String email = user1.getString("email");


                JSONObject name = user1.getJSONObject("name");
                String first = name.getString("first");

                Log.d("INFO", first + " " + gender + " " + email);
            }

        }catch(Exception e){

        }
    }


}