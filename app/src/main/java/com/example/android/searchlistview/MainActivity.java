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

/**
 * Created by Rahmat Syam on 9/9/2017.
 */

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MyAppAdapter myAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        ArrayList<Post> postArrayList = new ArrayList<>();
        postArrayList.add(new Post("September", "Wake Me Up When September End"));
        postArrayList.add(new Post("Oktober", "October Fast"));
        postArrayList.add(new Post("November", "Rain in My Heart"));
        postArrayList.add(new Post("Desember", "Last Month"));
        postArrayList.add(new Post("Januari", "First Month"));
        postArrayList.add(new Post("Februari", "Born Year"));
        postArrayList.add(new Post("Maret", "Place Where The Idiot Go"));
        postArrayList.add(new Post("April", "Not For Funny Day"));
        postArrayList.add(new Post("Mei", "May You?"));
        postArrayList.add(new Post("Juni", "War In Darkness"));
        postArrayList.add(new Post("Juli", "When Last Light Gone"));
        postArrayList.add(new Post("Agustus", "Will Always"));


        myAppAdapter = new MyAppAdapter(postArrayList, MainActivity.this);
        listView.setAdapter(myAppAdapter);
    }

    private class MyAppAdapter extends BaseAdapter {

        class ViewHolder {
            TextView txtTitle, txtSubTitle;


        }

        List<Post> parkingList;

        Context context;
        ArrayList<Post> arraylist;

        private MyAppAdapter(List<Post> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<>();
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

            viewHolder.txtTitle.setText(parkingList.get(position).getPostJudul() + "");
            viewHolder.txtSubTitle.setText(parkingList.get(position).getPostIsiJudul() + "");
            return rowView;


        }

        void filter(String charText) {

            charText = charText.toLowerCase(Locale.getDefault());

            parkingList.clear();
            if (charText.length() == 0) {
                parkingList.addAll(arraylist);

            } else {
                for (Post postDetail : arraylist) {
                    if (charText.length() != 0 && postDetail.getPostJudul().toLowerCase(Locale.getDefault()).contains(charText)) {
                        parkingList.add(postDetail);
                    } else if (charText.length() != 0 && postDetail.getPostIsiJudul().toLowerCase(Locale.getDefault()).contains(charText)) {
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
