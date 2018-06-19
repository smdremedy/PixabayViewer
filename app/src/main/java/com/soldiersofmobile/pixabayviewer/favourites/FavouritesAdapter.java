package com.soldiersofmobile.pixabayviewer.favourites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.soldiersofmobile.pixabayviewer.R;
import com.soldiersofmobile.pixabayviewer.db.Favourite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavouritesAdapter extends BaseAdapter {

    private final OnFavouriteItemClickedListener listener;

    public void clear() {
        favourites.clear();
        notifyDataSetInvalidated();
    }

    interface OnFavouriteItemClickedListener {
        void onDeleteItemClicked(Favourite favourite);
        void onOpenInBrowserClicked(Favourite favourite);
    }

    public FavouritesAdapter(OnFavouriteItemClickedListener listener) {

        this.listener = listener;
    }

    private List<Favourite> favourites = new ArrayList<>();

    public void addAll(Collection<Favourite> favourites) {
        this.favourites.addAll(favourites);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return favourites.size();
    }

    @Override
    public Favourite getItem(int position) {
        return favourites.get(position);
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
            view = inflater.inflate(R.layout.item_favourite, parent, false);
            view.setTag(new ViewHolder(view));
        }

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        Favourite favourite = getItem(position);
        viewHolder.setFavourite(favourite);

        Picasso.with(parent.getContext())
                .load(favourite.getPreviewURL())
                .into(viewHolder.itemImageView);
        viewHolder.tagsTextView.setText(favourite.getTags());

        return view;
    }

    class ViewHolder {
        @BindView(R.id.item_image_view)
        ImageView itemImageView;
        @BindView(R.id.tags_text_view)
        TextView tagsTextView;

        private Favourite favourite;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.delete_button)
        public void onDeleteClicked() {
            listener.onDeleteItemClicked(favourite);
        }

        @OnClick(R.id.open_in_browser_button)
        public void onOpenInBrowser() {
            listener.onOpenInBrowserClicked(favourite);
        }

        public void setFavourite(Favourite favourite) {
            this.favourite = favourite;
        }
    }
}
