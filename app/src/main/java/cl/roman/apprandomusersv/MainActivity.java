package cl.roman.apprandomusersv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.roman.apprandomusersv.adapter.UserAdapter;
import cl.roman.apprandomusersv.model.User;
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

            List<User> list = new ArrayList<>();

            for(int i=0; i<results.length();i++) {

                JSONObject user1 = results.getJSONObject(i);
                String email = user1.getString("email");
                String phone = user1.getString("phone");

                JSONObject name = user1.getJSONObject("name");
                String first = name.getString("first");
                String last = name.getString("last");

                JSONObject picture = user1.getJSONObject("picture");
                String thumbnail = picture.getString("thumbnail");

                User user = new User(first,last,email,phone,thumbnail);
                list.add(user);
            }

            RecyclerView rc = findViewById(R.id.rc_user);

            LinearLayoutManager lm = new LinearLayoutManager(this);
            lm.setOrientation(RecyclerView.VERTICAL);

            UserAdapter adapter = new UserAdapter(this,list,R.layout.item_user);

            rc.setLayoutManager(lm);
            rc.setAdapter(adapter);




        }catch(Exception e){

        }
    }


}