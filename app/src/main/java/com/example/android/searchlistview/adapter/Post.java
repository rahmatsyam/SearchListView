package com.example.android.searchlistview.adapter;

/**
 * Created by Rahmat Syam on 9/9/2017.
 */

public class Post {

    private String postJudul;

    private String postIsiJudul;

    public String getPostJudul() {
        return postJudul;
    }

    public void setPostJudul(String postJudul) {
        this.postJudul = postJudul;
    }

    public String getPostIsiJudul() {
        return postIsiJudul;
    }

    public void setPostIsiJudul(String postIsiJudul) {
        this.postIsiJudul = postIsiJudul;
    }

    public Post(String postJudul, String postIsiJudul) {
        this.postJudul = postJudul;
        this.postIsiJudul = postIsiJudul;
    }
}