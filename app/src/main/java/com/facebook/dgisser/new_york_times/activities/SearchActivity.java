package com.facebook.dgisser.new_york_times.activities;

import android.content.Intent;
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
import com.facebook.dgisser.new_york_times.Models.Settings;
import com.facebook.dgisser.new_york_times.R;
import com.facebook.dgisser.new_york_times.api.Example;
import com.facebook.dgisser.new_york_times.api.Response;
import com.facebook.dgisser.new_york_times.frontApi.NytFront;
import com.facebook.dgisser.new_york_times.frontApi.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    String queryString;
    String baseURL;
    StaggeredGridLayoutManager layoutManager;
    int page;
    int flag;
    int flags;
    int pos;
    String startDate;
    final int REQUEST_CODE = 20;

    ArrayList<Article> articles;
    ArticleArrayAdapter adapter;
    @BindView(R.id.rvResults) RecyclerView rvResults;

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
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rvResults.setLayoutManager(layoutManager);
        rvResults.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                customLoadMoreDataFromApi(page);
            }
        });
        queryString = "";
        page = 0;
        baseURL = "https://api.nytimes.com/svc/topstories/v2/home.json";
        flag = 1;
        getPage();

    }

    public void launchSettings(MenuItem settings) {
        Intent i = new Intent(getApplicationContext(), FilterActivity.class);
        i.putExtra("CODE",REQUEST_CODE);
        startActivityForResult(i, REQUEST_CODE);
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
        adapter.notifyDataSetChanged();
        page = 0;
        baseURL = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
        flag = 0;
        getPage();
    }

    // Append more data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void customLoadMoreDataFromApi(int offset) {
        page++;
        baseURL = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
        getPage();
    }

    public void getPage() {
        AsyncHttpClient client = new AsyncHttpClient();
        String apiKey = getResources().getString(R.string.nyt_api_key);
        RequestParams params = new RequestParams();
        params.put("api-key", apiKey);
        params.put("page", page);
        if (pos == 1)
            params.put("sort","newest");
        if (pos == 2)
            params.put("sort","oldest");
        if (startDate != null && !startDate.isEmpty())
            params.put("begin_date", startDate);
        if (queryString != null && !queryString.isEmpty())
            params.put("q", queryString);
        String newsDesk = "";
        if ((flags & (int)1) == 1)
            newsDesk = ",\"Arts\"";
        if ((flags & (int)2) == 2)
            newsDesk = ",\"fashion%26style\"";
        if ((flags & (int)4) == 4)
            newsDesk = ",\"sports\"";
        if (!newsDesk.isEmpty())
            params.put("fq",String.format("news_desk:(%s)",newsDesk));
        client.get(baseURL, params, new JsonHttpResponseHandler() {
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
                if (flag == 0) {
                    Response responseObj = gson.fromJson(response.toString(), Example.class).getResponse();
                    for (int x = 0; x < responseObj.getDocs().size(); x++) {
                        String url;
                        if (responseObj.getDocs().get(x).getMultimedia().size() == 0) {
                            url = "";
                        } else {
                            url = responseObj.getDocs().get(x).getMultimedia().get(0).getUrl();
                        }
                        articles.add(new Article((responseObj.getDocs().get(x)).getWebUrl(),
                                responseObj.getDocs().get(x).getHeadline().getMain(),
                                "http://www.nytimes.com/" + url));
                    }
                } else {
                    List<Result> resultsObj = gson.fromJson(response.toString(), NytFront.class).getResults();
                    for (int x = 0; x < resultsObj.size(); x++) {
                        String url;
                        if (resultsObj.get(x).getMultimedia().size() == 0) {
                            url = "";
                        } else {
                            url = resultsObj.get(x).getMultimedia().get(0).getUrl();
                        }
                        articles.add(new Article((resultsObj.get(x)).getUrl(),
                                resultsObj.get(x).getTitle(), url));
                    }
                }
                int curSize = adapter.getItemCount();
                adapter.notifyItemRangeChanged(curSize, articles.size() - curSize - 1);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("searchactivity",errorResponse.toString());
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // Extract name value from result extras
            Settings settings = (Settings) Parcels.unwrap(data.getParcelableExtra("settings"));
            flags = settings.getFlags();
            pos = settings.getPos() + 1;
            startDate = settings.getDate();
            Log.d("searchActivity","startdate: " + startDate);
        }
    }
}
