package com.facebook.dgisser.new_york_times.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.dgisser.new_york_times.Models.Article;
import com.facebook.dgisser.new_york_times.R;
import com.facebook.dgisser.new_york_times.activities.ArticleActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dgisser on 6/19/16.
 */
public class ArticleArrayAdapter extends RecyclerView.Adapter<ArticleArrayAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.ivImage) ImageView ivImage;
        private Context context;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(Context context, View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.context = context;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
            Article article = mArticles.get(position);
            // We can access the data within the views

            //create an intent to display the article
            Intent i = new Intent(view.getContext(), ArticleActivity.class);
            //get the article to display
            //pass in that article into intent
            i.putExtra("article", article);
            //launch activity
            view.getContext().startActivity(i);
        }
    }

    private List<Article> mArticles;

    public ArticleArrayAdapter (List<Article> articles) {
        mArticles = articles;
    }

    @Override
    public ArticleArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View articleView = inflater.inflate(R.layout.item_article_result, parent, false);

        // Return a new holder instance
        return new ViewHolder(context, articleView);
    }

    @Override
    public void onBindViewHolder(ArticleArrayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Article article = mArticles.get(position);

        // Set item views based on the data model
        TextView textView = viewHolder.tvTitle;
        textView.setText(article.getHeadline());

        ImageView ivImage = viewHolder.ivImage;
        ivImage.setImageResource(0);

        String thumbnail = article.getThumbNail();

        if (!TextUtils.isEmpty(thumbnail)) {
            Glide.with(ivImage.getContext()).load(thumbnail).into(ivImage);
        }

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Article article = this.getItem(position);
        //check to see if existing view being reused
        //not using a recycled view -> inflate layout
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_article_result, parent, false);
        }
        //find image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        //clear out recycled image from convertView from last time
        ivImage.setImageResource(0);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

        tvTitle.setText(article.getHeadline());

        //populate thumbnail image
        //remote download image in background

        String thumbnail = article.getThumbNail();

        if (!TextUtils.isEmpty(thumbnail)) {
            Log.d("articleArrayAdapter",thumbnail);
            Picasso.with(getContext()).load(thumbnail).into(ivImage);
        }

        return convertView;
    }

        //hook up listener for grid click
        rvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create an intent to display the article
                Intent i = new Intent(getApplicationContext(), ArticleActivity.class);
                //get the article to display
                Log.d("SearchActivity",String.format("%d",articles.size()));
                Article article = articles.get(position);
                //pass in that article into intent
                i.putExtra("article", article);
                //launch activity
                startActivity(i);
            }
        });
    */
}
