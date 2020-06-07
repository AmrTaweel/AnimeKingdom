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
import com.example.animekingdom.R;
import java.util.ArrayList;

public class SaturdayFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter MondayAnime;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Anime> Monday = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/schedule/saturday" ;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.saturday, container, false) ;
        recyclerView = root.findViewById(R.id.SaturdayRecyclevView) ;
        final ProgressBar LoadingSaturday = root.findViewById( R.id.loadingSaturday ) ;
        recyclerView.setHasFixedSize(true) ;
        layoutManager = new LinearLayoutManager(getActivity()) ;
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 )) ;
        MondayAnime = new MyAdapter(Monday, getActivity()) ;
        recyclerView.setAdapter(MondayAnime) ;
        final Button checkConncetion = root.findViewById( R.id.tryagainsaturday );
        final TextView noInternet = root.findViewById( R.id.ChecconcetionSaturday ) ;
        checkConncetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Monday , MondayAnime , getActivity() ,"saturday" , LoadingSaturday ,noInternet , checkConncetion ) ;
            }
        });
        new request( url , Monday , MondayAnime , getActivity() ,"saturday" , LoadingSaturday ,noInternet , checkConncetion ) ;
        return root ;
    }
}
