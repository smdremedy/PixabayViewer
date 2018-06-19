package com.soldiersofmobile.pixabayviewer.favourites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.soldiersofmobile.pixabayviewer.App;
import com.soldiersofmobile.pixabayviewer.db.Favourite;
import com.soldiersofmobile.pixabayviewer.db.FavouritesDao;

public class FavouritesListFragment extends ListFragment implements FavouritesAdapter.OnFavouriteItemClickedListener {

    private FavouritesDao favouritesDao;
    private FavouritesAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        App app = (App) getActivity().getApplication();
        favouritesDao = app.getFavouritesDao();
        setEmptyText("No favourite images yet!");

        adapter = new FavouritesAdapter(this);
        adapter.addAll(favouritesDao.getAll());
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(adapter.getItem(position).getPageURL()));
        startActivity(i);

    }

    @Override
    public void onDeleteItemClicked(Favourite favourite) {
        favouritesDao.delete(favourite);
        adapter.clear();
        adapter.addAll(favouritesDao.getAll());
    }

    @Override
    public void onOpenInBrowserClicked(Favourite favourite) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(favourite.getPageURL()));
        startActivity(i);

    }
}
