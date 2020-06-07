package programfiles.TopAiringAnime;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import  programfiles.request;
import programfiles.recyclescrolling;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import programfiles.MyAdapter;
import com.example.animekingdom.R;
import programfiles.Anime;
import java.util.ArrayList;

public class TopAiringAnimeFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter TopAiring ;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Anime> Topairing = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/top/anime/" + 1 + "/airing" ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate( R.layout.topairing, container, false ) ;
        recyclerView = root.findViewById( R.id.TopCompletedAnime ) ;
        final ProgressBar LoadingAiring = root.findViewById( R.id.gIF ) ;
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3) ;
        final Button checkConncetion = root.findViewById( R.id.tryagainTopAiring );
        recyclerView.setLayoutManager( layoutManager) ;
        TopAiring = new MyAdapter(Topairing,getActivity()) ;
        recyclerView.setAdapter(TopAiring) ;
        final TextView noConnection = root.findViewById( R.id.CheckconcetionTopAiring ) ;
        new request( url , Topairing , TopAiring , getActivity() ,"top" , LoadingAiring , noConnection , checkConncetion ) ;
        checkConncetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Topairing , TopAiring , getActivity() ,"top" , LoadingAiring , noConnection , checkConncetion ) ;
            }
        });
        new recyclescrolling(Topairing , "https://api.jikan.moe/v3/top/anime/" , "/airing" , LoadingAiring , recyclerView , layoutManager , TopAiring , getActivity() , "top" , checkConncetion ) ;
        return root ;

    }
}
