package programfiles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import programfiles.MyAdapter;
import com.example.animekingdom.R;
import programfiles.Anime;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AnimeMovieFragment extends Fragment {


    private RecyclerView recyclerView ;
    private RecyclerView.Adapter TopMovie;
    private RecyclerView.LayoutManager layoutManager ;
    int i=0;
    int z;
    private ArrayList<Anime> TopAnimeMovie = new ArrayList<>() ;
    OkHttpClient okHttpClient = new OkHttpClient() ;
    final String url = "https://api.jikan.moe/v3/top/anime/1/movie" ;
    final Request request = new Request.Builder()
            .url(url)
            .build();
    int aa=1;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.animemovie, container, false);
        recyclerView=root.findViewById(R.id.MovieAnime);
        final ProgressBar LoadingMovie = root.findViewById( R.id.loadingMovie ) ;
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager) ;
        TopMovie = new MyAdapter(TopAnimeMovie, getActivity()) ;
        final Button checkConnection =root.findViewById( R.id .tryagainAnimeMovie ) ;
        final TextView noCnection = root.findViewById( R.id.conctection ) ;
        recyclerView.setAdapter(TopMovie) ;
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request(url , TopAnimeMovie , TopMovie , getActivity() , "top" , LoadingMovie , noCnection, checkConnection ) ;
            }
        });
        new request(url , TopAnimeMovie , TopMovie , getActivity() , "top" , LoadingMovie , noCnection, checkConnection ) ;
        new recyclescrolling(TopAnimeMovie , "https://api.jikan.moe/v3/top/anime/" , "/movie" , LoadingMovie ,recyclerView , layoutManager , TopMovie , getActivity() , "top" , noCnection );
        return root;
    }
}