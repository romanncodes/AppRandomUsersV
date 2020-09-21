package cl.roman.apprandomusersv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    String name, email, phone, url;
    TextView txt_name, txt_phone, txt_email;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //controls
        txt_name = findViewById(R.id.detail_name);
        txt_email = findViewById(R.id.detail_email);
        txt_phone = findViewById(R.id.detail_phone);
        img = findViewById(R.id.detail_img);


        //data
        name = getIntent().getStringExtra("NAME");
        email = getIntent().getStringExtra("EMAIL");
        phone = getIntent().getStringExtra("PHONE");
        url = getIntent().getStringExtra("IMAGE");

        txt_name.setText(name);
        txt_email.setText(email);
        txt_phone.setText(phone);

        Glide.with(this).load(url).into(img);


        Log.d("INFO",name+" "+email+" "+url);


    }
}