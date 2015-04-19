package com.example.jackjack.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jackjack.myapplication.R;


public class SearchFragment extends Fragment {

    private static final String TAG = SearchFragment.class.getSimpleName();
    private final SearchFragment self = this;

    private SearchView searchView;
    private String searchWord = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FragmentでMenuを表示する為に必要
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Menuの設定
        inflater.inflate(R.menu.search, menu);

        // app:actionViewClass="android.support.v7.widget.SearchView"のItemの取得
        MenuItem menuItem = menu.findItem(R.id.search_menu_search_view);


        // API level 11以上の場合はこっちを使う
        searchView = (SearchView)menuItem.getActionView();


        // ActionViewの取得
        //this.searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        // 虫眼鏡アイコンを最初表示するかの設定
        searchView.setIconifiedByDefault(true);

        // Submitボタンを表示するかどうか
        searchView.setSubmitButtonEnabled(false);


        if (!searchWord.equals("")) {
            // TextView.setTextみたいなもの
            searchView.setQuery(searchWord, false);
        } else {
            String queryHint = self.getResources().getString(R.string.search_menu_query_hint_text);
            // placeholderみたいなもの
            searchView.setQueryHint(queryHint);
        }
        searchView.setOnQueryTextListener(self.onQueryTextListener);
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String searchWord) {
            // SubmitボタンorEnterKeyを押されたら呼び出されるメソッド
            return self.setSearchWord(searchWord);
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // 入力される度に呼び出される
            return false;
        }
    };

    private boolean setSearchWord(String searchWord) {
        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle(searchWord);
        actionBar.setDisplayShowTitleEnabled(true);
        if (searchWord != null && searchWord.length() != 0) {
            // searchWordがあることを確認
            searchWord = searchWord;
        }
        // 虫眼鏡アイコンを隠す
        searchView.setIconified(false);
        // SearchViewを隠す
        searchView.onActionViewCollapsed();
        // Focusを外す
        searchView.clearFocus();
        return false;
    }
}

