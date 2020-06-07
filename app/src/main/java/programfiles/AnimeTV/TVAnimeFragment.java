package programfiles.AnimeTV;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import  programfiles.request;
import  programfiles.recyclescrolling;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import programfiles.MyAdapter;
import com.example.animekingdom.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import programfiles.Anime;
import java.util.ArrayList;
import java.util.List;


public class TVAnimeFragment extends Fragment   {

    private RecyclerView recyclerView ;
    private RecyclerView.Adapter TopTV ;
    private RecyclerView.LayoutManager layoutManager ;
    TextView textView;
    Button checkInternetConnection;
    ProgressBar loadingtv;
    private ArrayList<Anime> Toptv = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/top/anime/" + 1 + "/tv" ;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.tv, container, false ) ;
        checkInternetConnection = root.findViewById( R.id.tryagaintv ) ;
        recyclerView = root.findViewById( R.id.tvseries ) ;
         loadingtv = root.findViewById( R.id.Tvloading ) ;
        recyclerView.setHasFixedSize( true );
        layoutManager = new GridLayoutManager( getActivity(),3 );
        recyclerView.setLayoutManager( layoutManager ) ;
        TopTV = new MyAdapter( Toptv, getActivity() ) ;
        recyclerView.setAdapter( TopTV ) ;
         textView=root.findViewById( R.id.conctections ) ;
         int loader_id=12;

        checkInternetConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Toptv , TopTV , getActivity() , "top" , loadingtv , textView , checkInternetConnection ) ;
            }
        });
        new request( url , Toptv , TopTV , getActivity() , "top" , loadingtv , textView , checkInternetConnection ) ;
        new recyclescrolling( Toptv , "https://api.jikan.moe/v3/top/anime/" , "/tv" , loadingtv , recyclerView , layoutManager ,TopTV ,getActivity() , "top" ,textView ) ;
        return root;
    }

    }