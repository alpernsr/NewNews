package com.alperen.newnews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<ArticleEntity> articleList;
    ProgressBar progressBar;
    public FragmentHome() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar);
        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        recyclerView = view.findViewById(R.id.r_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        articleList = new ArrayList<>();
        newsAdapter = new NewsAdapter(articleList);
        recyclerView.setAdapter(newsAdapter);
    }
    public void getNews(String search) {
        progressBar.setVisibility(View.VISIBLE);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://newsapi.org/v2/everything?q=" + search + "&page=1&apiKey=90f78b5e459f4557a6d285161db89387")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Failed to fetch news!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String respStr = response.body().string();
                BaseResponseEntity responseEntity = new Gson().fromJson(respStr, BaseResponseEntity.class);
                articleList.clear();
                articleList.addAll(responseEntity.articles);
                getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    newsAdapter.notifyDataSetChanged();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }
                );
            }
        });
    }
}