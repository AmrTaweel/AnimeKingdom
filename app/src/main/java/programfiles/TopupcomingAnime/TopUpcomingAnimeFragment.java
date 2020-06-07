package programfiles.TopupcomingAnime;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import programfiles.MyAdapter;
import com.example.animekingdom.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import programfiles.Anime;
import programfiles.load;
import  programfiles.request ;
import  programfiles.recyclescrolling ;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TopUpcomingAnimeFragment extends Fragment {
    private RecyclerView.LayoutManager layoutManager ;
    private String url="https://api.jikan.moe/v3/top/anime/1/upcoming" ;
    private RecyclerView recyclerView ;
    private ProgressBar LoadingUpcoming;
    private RecyclerView.Adapter TopUpcoming ;
    private ArrayList<Anime> Topupcoming = new ArrayList<>() ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.topupcoming, container, false) ;
        final TextView noConnection = root.findViewById(R.id.CheckconcetionTopUpcoming);
        recyclerView = root.findViewById( R.id.TopUpcoming ) ;
       LoadingUpcoming = root.findViewById( R.id.loadingUpcoing ) ;
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager( getActivity(),3 ) ;
        recyclerView.setLayoutManager( layoutManager) ;
        final Button checkConnection = root.findViewById( R.id.tryagainTopupcoming );
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Topupcoming , TopUpcoming , getActivity() , "top" , LoadingUpcoming , noConnection ,checkConnection ) ;
            }
        });
        TopUpcoming = new MyAdapter( Topupcoming,getActivity() ) ;
        recyclerView.setAdapter( TopUpcoming ) ;
        new request( url , Topupcoming , TopUpcoming , getActivity() , "top" , LoadingUpcoming , noConnection , checkConnection );
        new recyclescrolling( Topupcoming , "https://api.jikan.moe/v3/top/anime/" , "/upcoming" , LoadingUpcoming , recyclerView , layoutManager , TopUpcoming , getActivity() ,"top"  , noConnection);
        return root;
    }


}