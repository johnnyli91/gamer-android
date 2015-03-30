package co.johnnyli.gamer;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class EditProfile extends ActionBarActivity implements View.OnClickListener {
    private static final String postURL =
            "http://ec2-52-11-112-83.us-west-2.compute.amazonaws.com/api/profile/";
    private String pk;
    private EditText bio;
    private EditText dob;
    private EditText gender;
    private EditText location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        setTitle("Edit Profile");
        //ActionBar Color
        String color = Feed.color;
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        pk = this.getIntent().getExtras().getString("pk");
        bio = (EditText) findViewById(R.id.bio);
        dob = (EditText) findViewById(R.id.dob);
        gender = (EditText) findViewById(R.id.gender);
        location = (EditText) findViewById(R.id.location);
        bio.setText(this.getIntent().getExtras().getString("bio"));
        dob.setText(this.getIntent().getExtras().getString("dob"));
        gender.setText(this.getIntent().getExtras().getString("gender"));
        location.setText(this.getIntent().getExtras().getString("location"));
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {
        RequestParams params = new RequestParams();
        params.put("bio", bio.getText().toString());
        params.put("dob", dob.getText().toString());
        params.put("gender", gender.getText().toString());
        params.put("location", location.getText().toString());
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-CSRFToken", Info.csrftoken);
        client.addHeader("Authorization", Login.auth);

        client.put(postURL + pk, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                finish();
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
