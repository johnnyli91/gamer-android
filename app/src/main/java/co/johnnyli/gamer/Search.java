package co.johnnyli.gamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by johnnyli on 3/9/15.
 */
public class Search extends ActionBarActivity implements View.OnClickListener {

    EditText searchField;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        searchField = (EditText) findViewById(R.id.search_field);
        searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
//            case R.id.action_search:
//                //insert search function here
//                return true;
//            case R.id.action_home:
//                Intent home = new Intent(this, Feed.class);
//                startActivity(home);
//                return true;
//            case R.id.action_notification:
//                //insert notification function here
//                return true;
            case R.id.action_profile:
                return true;
            case R.id.action_group:
                Intent group = new Intent(this, Group.class);
                startActivity(group);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        searchField.setText("");
    }
}
