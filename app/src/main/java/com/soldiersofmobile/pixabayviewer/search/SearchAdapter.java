package com.soldiersofmobile.pixabayviewer.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.soldiersofmobile.pixabayviewer.R;
import com.soldiersofmobile.pixabayviewer.api.model.Hit;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchAdapter extends BaseAdapter {

    private final OnAddToFavouritesListener listener;

    public void clear() {
        hits.clear();
        notifyDataSetInvalidated();
    }

    interface OnAddToFavouritesListener {
        void onAddToFavourites(Hit hit);
    }

    private List<Hit> hits = new ArrayList<>();

    public SearchAdapter(OnAddToFavouritesListener listener) {

        this.listener = listener;
    }

    @Override
    public int getCount() {
        return hits.size();
    }

    @Override
    public Hit getItem(int position) {
        return hits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.item_hit, parent, false);
            view.setTag(new ViewHolder(view));
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        Hit hit = getItem(position);
        viewHolder.setHit(hit);

        Picasso.with(parent.getContext())
                .load(hit.getWebformatURL())
                .into(viewHolder.itemImageView);
        viewHolder.tagsTextView.setText(hit.getTags());

        return view;
    }

    public void addAll(Collection<Hit> hits) {
        this.hits.addAll(hits);
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.item_image_view)
        ImageView itemImageView;
        @BindView(R.id.tags_text_view)
        TextView tagsTextView;
        @BindView(R.id.add_to_favourites_button)
        Button addToFavouritesButton;

        private Hit hit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.add_to_favourites_button)
        public void onAddToFavouritesClicked() {

            listener.onAddToFavourites(hit);

        }

        public void setHit(Hit hit) {
            this.hit = hit;
        }
    }
}
