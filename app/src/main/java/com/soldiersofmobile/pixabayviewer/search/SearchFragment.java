package com.soldiersofmobile.pixabayviewer.search;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.soldiersofmobile.pixabayviewer.App;
import com.soldiersofmobile.pixabayviewer.R;
import com.soldiersofmobile.pixabayviewer.api.PixabayApi;
import com.soldiersofmobile.pixabayviewer.api.model.Hit;
import com.soldiersofmobile.pixabayviewer.api.model.PixabayResponse;
import com.soldiersofmobile.pixabayviewer.db.Favourite;
import com.soldiersofmobile.pixabayviewer.db.FavouritesDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchAdapter.OnAddToFavouritesListener {


    @BindView(R.id.search_edit_text)
    EditText searchEditText;
    @BindView(R.id.search_button)
    Button searchButton;
    @BindView(R.id.grid)
    GridView grid;
    Unbinder unbinder;

    private SearchAdapter searchAdapter;

    private PixabayApi pixabayApi;
    private FavouritesDao favouritesDao;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        App app = (App) getActivity().getApplication();
        pixabayApi = app.getPixabayApi();
        favouritesDao = app.getFavouritesDao();
        searchAdapter = new SearchAdapter(this);

        grid.setAdapter(searchAdapter);
    }


    @OnClick(R.id.search_button)
    public void onViewClicked() {
        String query = searchEditText.getText().toString();
        Call<PixabayResponse> call = pixabayApi.getSearch(query);

        call.enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                if (response.isSuccessful()) {
                    searchAdapter.clear();
                    searchAdapter.addAll(response.body().getHits());
                } else {
                    Timber.w(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<PixabayResponse> call, Throwable t) {
                Timber.e(t);
            }
        });
    }


    @Override
    public void onAddToFavourites(Hit hit) {
        Favourite favourite = new Favourite();
        favourite.setId(hit.getId());
        favourite.setPreviewURL(hit.getPreviewURL());
        favourite.setPageURL(hit.getPageURL());
        favourite.setTags(hit.getTags());
        favouritesDao.create(favourite);
    }
}
