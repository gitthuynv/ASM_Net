package thuynv.ph30181.client_comic.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thuynv.ph30181.client_comic.R;
import thuynv.ph30181.client_comic.adapter.ComicAdapter;
import thuynv.ph30181.client_comic.api.Api_Service;
import thuynv.ph30181.client_comic.models.Comics;
import thuynv.ph30181.client_comic.views.DetailComicActivity;

public class ListComicFragment extends Fragment {
    private RecyclerView rcvListComic;
    private ComicAdapter comicAdapter;


    public ListComicFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_comic, container, false);
        rcvListComic = view.findViewById(R.id.rcvListComic);
        rcvListComic.setLayoutManager(new LinearLayoutManager(getContext()));
        comicAdapter = new ComicAdapter(getContext(), new ArrayList<>());
        rcvListComic.setAdapter(comicAdapter);
        comicAdapter.setOnItemClickListener(new ComicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Comics comic) {
                Api_Service.API_SERVICE.getComicById(comic.get_id()).enqueue(new Callback<Comics>() {
                    @Override
                    public void onResponse(Call<Comics> call, Response<Comics> response) {
                        if (response.isSuccessful()) {
                            Comics comicDetail = response.body();
                            Intent intent = new Intent(getContext(), DetailComicActivity.class);
                            intent.putExtra("comic_detail", (Serializable)comicDetail);
                            startActivity(intent);

                        } else {
                            // Xử lý khi không thành công
                        }
                    }

                    @Override
                    public void onFailure(Call<Comics> call, Throwable t) {
                        // Xử lý khi có lỗi xảy ra
                    }
                });
            }
        });

        return view;
    }
    private void loadData() {
        Api_Service.API_SERVICE.getDataComic().enqueue(new Callback<List<Comics>>() {
            @Override
            public void onResponse(Call<List<Comics>> call, Response<List<Comics>> response) {
                if (response.isSuccessful()) {
                    List<Comics> comicList = response.body();
                    comicAdapter.setComicList(comicList);
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<Comics>> call, Throwable t) {
                Toast.makeText(getContext(), "Đã xảy ra lỗi! Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}