package programfiles;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animekingdom.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopSeiries extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter TopAiring ;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Anime> Topairing = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/top/anime/" + 1  ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate( R.layout.top_series , container, false ) ;
        recyclerView = root.findViewById( R.id.AnimeSeries ) ;
        final ProgressBar LoadingAiring = root.findViewById( R.id.loadingseries ) ;
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3) ;
        recyclerView.setLayoutManager( layoutManager) ;
        TopAiring = new MyAdapter(Topairing,getActivity()) ;
        recyclerView.setAdapter(TopAiring) ;
        final TextView noInternet = root.findViewById( R.id.CheckconcetionSeries ) ;
        final Button checkConection = root.findViewById( R.id.tryagainTopseries ) ;
        new request( url , Topairing , TopAiring , getActivity() ,"top" , LoadingAiring ,noInternet , checkConection ) ;
        new recyclescrolling(Topairing , "https://api.jikan.moe/v3/top/anime/" , "" , LoadingAiring , recyclerView , layoutManager , TopAiring , getActivity() , "top",noInternet ) ;
        checkConection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Topairing , TopAiring , getActivity() ,"top" , LoadingAiring ,noInternet , checkConection ) ;
            }
        });
        return root ;

    }
}
