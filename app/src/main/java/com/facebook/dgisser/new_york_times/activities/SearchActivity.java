package com.facebook.dgisser.new_york_times.activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.dgisser.new_york_times.Adapters.ArticleArrayAdapter;
import com.facebook.dgisser.new_york_times.EndlessRecyclerViewScrollListener;
import com.facebook.dgisser.new_york_times.Models.Article;
import com.facebook.dgisser.new_york_times.R;
import com.facebook.dgisser.new_york_times.api.Example;
import com.facebook.dgisser.new_york_times.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    String queryString;
    StaggeredGridLayoutManager layoutManager;
    int page;

    ArrayList<Article> articles;
    ArticleArrayAdapter adapter;
    @BindView(R.id.rvResults)
    RecyclerView rvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupViews();
    }

    public void setupViews() {
        articles = new ArrayList<>();
        adapter = new ArticleArrayAdapter(articles);
        assert rvResults != null;
        rvResults.setAdapter(adapter);
        layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvResults.setLayoutManager(layoutManager);
        rvResults.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                customLoadMoreDataFromApi(page);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                queryString = query;
                articleSearch();
                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void articleSearch() {
        articles.clear();
        page = 0;
        AsyncHttpClient client = new AsyncHttpClient();
        String apiKey = getResources().getString(R.string.nyt_api_key);
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
        RequestParams params = new RequestParams();
        params.put("api-key", apiKey);
        params.put("page", page);
        params.put("q", queryString);

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();
                Response responseObj = gson.fromJson(response.toString(), Example.class).getResponse();
                for (int x = 0; x < responseObj.getDocs().size(); x++) {
                    String url;
                    if (responseObj.getDocs().get(x).getMultimedia().size() == 0) {
                        url = "";
                    } else {
                        url = responseObj.getDocs().get(x).getMultimedia().get(0).getUrl();
                        Log.d("url","ok "+ responseObj.getDocs().get(x).getMultimedia().get(0).getUrl());
                    }
                    articles.add(new Article(responseObj.getDocs().get(x).getWebUrl(),
                            responseObj.getDocs().get(x).getHeadline().getMain(), url));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    // Append more data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void customLoadMoreDataFromApi(int offset) {
        Log.d("searchactivity","called");
        // Send an API request to retrieve appropriate data using the offset value as a parameter.
        // Deserialize API response and then construct new objects to append to the adapter
        // Add the new objects to the data source for the adapter
        page++;
        AsyncHttpClient client = new AsyncHttpClient();
        String apiKey = getResources().getString(R.string.nyt_api_key);
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
        RequestParams params = new RequestParams();
        params.put("api-key", apiKey);
        params.put("page", page);
        params.put("q", queryString);

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                rvResults.clearOnScrollListeners();
                rvResults.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        customLoadMoreDataFromApi(page);
                    }
                });
                Gson gson = new GsonBuilder().create();
                Response responseObj = gson.fromJson(response.toString(), Example.class).getResponse();
                for (int x = 0; x < responseObj.getDocs().size(); x++) {
                    String url;
                    if (responseObj.getDocs().get(x).getMultimedia().size() == 0) {
                        url = "";
                    } else {
                        url = responseObj.getDocs().get(x).getMultimedia().get(0).getUrl();
                    }
                    articles.add(new Article((responseObj.getDocs().get(x)).getWebUrl(),
                            responseObj.getDocs().get(x).getHeadline().getMain(), url));
                }
                int curSize = adapter.getItemCount();
                adapter.notifyItemRangeChanged(curSize, articles.size() - curSize - 1);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("SearchActivity",responseString);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
        // For efficiency purposes, notify the adapter of only the elements that got changed
        // curSize will equal to the index of the first element inserted because the list is 0-indexed
        //
    }
}
