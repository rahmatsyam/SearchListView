package com.example.android.searchlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.searchlistview.adapter.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MyAppAdapter myAppAdapter;
    private ArrayList<Post> postArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        postArrayList = new ArrayList<>();
        postArrayList.add(new Post("Dummy Title", "Dummy Sub Title"));
        postArrayList.add(new Post("Searchview in actionbar", "enjoy search functionality from actionbar in android"));
        postArrayList.add(new Post("Search in listview", "search feature that filter listview item"));
        postArrayList.add(new Post("Android Search Bar", "adding search feature in toolbar using appcompat library"));
        postArrayList.add(new Post("Android Studio SearchView example", "Android SearchView tutorial in android studio"));
        postArrayList.add(new Post("Android Tutorial", "Get latest android material with simple solution"));
        postArrayList.add(new Post("nkDroid tutorials", "A to Z Android tutorials at one place"));


        myAppAdapter = new MyAppAdapter(postArrayList, MainActivity.this);
        listView.setAdapter(myAppAdapter);
    }

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            TextView txtTitle, txtSubTitle;


        }

        public List<Post> parkingList;

        public Context context;
        ArrayList<Post> arraylist;

        private MyAppAdapter(List<Post> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<Post>();
            arraylist.addAll(parkingList);

        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            ViewHolder viewHolder;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_post, null);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.txtTitle = (TextView) rowView.findViewById(R.id.title);
                viewHolder.txtSubTitle = (TextView) rowView.findViewById(R.id.subtitle);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txtTitle.setText(parkingList.get(position).getPostTitle() + "");
            viewHolder.txtSubTitle.setText(parkingList.get(position).getPostSubTitle() + "");
            return rowView;


        }

        public void filter(String charText) {

            charText = charText.toLowerCase(Locale.getDefault());

            parkingList.clear();
            if (charText.length() == 0) {
                parkingList.addAll(arraylist);

            } else {
                for (Post postDetail : arraylist) {
                    if (charText.length() != 0 && postDetail.getPostTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                        parkingList.add(postDetail);
                    } else if (charText.length() != 0 && postDetail.getPostSubTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                        parkingList.add(postDetail);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                myAppAdapter.filter(searchQuery.trim());
                listView.invalidate();
                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return id == R.id.action_search || super.onOptionsItemSelected(item);
    }

}
