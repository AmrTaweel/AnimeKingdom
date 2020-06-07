package programfiles.ova;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import programfiles.recyclescrolling;
import programfiles.request;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import programfiles.MyAdapter;
import com.example.animekingdom.R;
import programfiles.Anime;
import java.util.ArrayList;

public class ovaFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter TopOVA;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Anime> Ovas = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/top/anime/" + 1 + "/ova" ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ova, container, false) ;
        recyclerView=root.findViewById(R.id.AnimeOVA);
        final Button checkConnection = root.findViewById( R.id.tryagainova ) ;
        final ProgressBar LoadingOva = root.findViewById( R.id.loadingOva ) ;
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(),3) ;
        recyclerView.setLayoutManager( layoutManager ) ;
        TopOVA = new MyAdapter(Ovas, getActivity()) ;
        recyclerView.setAdapter(TopOVA) ;
        final TextView Noconnection = root.findViewById( R.id.CheckconcetionOva ) ;
        new request(url , Ovas , TopOVA , getContext() , "top" , LoadingOva , Noconnection , checkConnection ) ;
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request(url , Ovas , TopOVA , getContext() , "top" , LoadingOva , Noconnection , checkConnection ) ;
            }
        });
        new recyclescrolling(Ovas , "https://api.jikan.moe/v3/top/anime/" , "/ova" , LoadingOva , recyclerView , layoutManager , TopOVA , getActivity() , "top" , Noconnection );
        return root ;
    }
}