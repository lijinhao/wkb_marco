package com.apj.wkb.dataprovider;

import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
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
    public static class PlaceholderFragment extends ListFragment {

        private CommentsDataSource datasource;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);


            datasource = new CommentsDataSource(this.getActivity());
            datasource.open();

            List<Comment> values = datasource.getAllComments();

            // use the SimpleCursorAdapter to show the
            // elements in a ListView
            ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this.getActivity(),
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);

        }

        // Will be called via the onClick attribute
        // of the buttons in main.xml
        public void onClick(View view) {
            @SuppressWarnings("unchecked")
            ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
            Comment comment = null;
            switch (view.getId()) {
                case R.id.add:
                    String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                    int nextInt = new Random().nextInt(3);
                    // save the new comment to the database
                    comment = datasource.createComment(comments[nextInt]);
                    adapter.add(comment);
                    break;
                case R.id.delete:
                    if (getListAdapter().getCount() > 0) {
                        comment = (Comment) getListAdapter().getItem(0);
                        datasource.deleteComment(comment);
                        adapter.remove(comment);
                    }
                    break;
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onResume() {
            datasource.open();
            super.onResume();
        }

        @Override
        public void onPause() {
            datasource.close();
            super.onPause();
        }

    }
}
