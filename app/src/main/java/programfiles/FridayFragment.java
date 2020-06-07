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

public class FridayFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter FridayAnime ;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Anime> Friday = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/schedule/friday" ;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.friday, container, false) ;
        recyclerView=root.findViewById(R.id.FridayRecyclevView) ;
        final ProgressBar gifImageView = root.findViewById( R.id.loadingFriday ) ;
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity()) ;
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 )) ;
        FridayAnime = new MyAdapter(Friday,getActivity()) ;
        Button checkConection = root.findViewById( R.id.tryagainfriday );
        recyclerView.setAdapter(FridayAnime) ;
        TextView noConnection = root.findViewById( R.id.CheckconcetionFriday ) ;
        new request(url,Friday,FridayAnime,getContext(),"friday",gifImageView , noConnection ,checkConection);
        return root;
    }
}

