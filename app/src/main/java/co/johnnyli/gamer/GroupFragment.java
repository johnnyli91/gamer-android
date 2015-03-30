package co.johnnyli.gamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

public class GroupFragment extends ListFragment implements AdapterView.OnItemClickListener{

    private ListView listView;
    GroupJSONAdapter mJSONAdapter;
    private static final String URL = "http://ec2-52-11-112-83.us-west-2.compute.amazonaws.com/api/myinfo";
    private TextView noGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_fragment, container, false);
        listView = (ListView) view.findViewById(android.R.id.list);
        noGroup = (TextView) view.findViewById(R.id.no_groups);
        noGroup.setVisibility(View.GONE);
        getGroup();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mJSONAdapter = new GroupJSONAdapter(getActivity(), getActivity().getLayoutInflater());
        listView.setAdapter(mJSONAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JSONObject jsonObject = (JSONObject) mJSONAdapter.getItem(position);
        String name = jsonObject.optString("name");
        String pk = jsonObject.optString("pk");
        Intent groupIntent = new Intent(GroupFragment.this.getActivity(), Group.class);
        groupIntent.putExtra("name", name);
        groupIntent.putExtra("pk", pk);
        startActivity(groupIntent);
    }

    private void getGroup() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", Login.auth);
        client.get(URL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(JSONArray jsonArray) {
                try {
                    JSONObject jsonData = jsonArray.getJSONObject(0);
                    if (jsonData.optJSONArray("groups").length() > 0) {
                        mJSONAdapter.updateData(jsonData.optJSONArray("groups"));
                    } else {
                        noGroup.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Toast.makeText(GroupFragment.this.getActivity(), "Error: " + statusCode + " " +
                        throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
