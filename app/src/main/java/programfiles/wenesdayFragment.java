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
import androidx.recyclerview.widget.RecyclerView;
import com.example.animekingdom.R;
import java.util.ArrayList;

public class wenesdayFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter WednesdayAnime ;
    private ArrayList<Anime> Wednesday = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/schedule/wednesday" ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.wenesdays, container, false ) ;
        recyclerView = root.findViewById( R.id.WednesdayRecycleview ) ;
        final TextView noIntenet = root.findViewById(R.id.Checconcetion);
        final ProgressBar LoadingMonday = root.findViewById( R.id.loadingWednesday ) ;
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 ) ) ;
        WednesdayAnime = new MyAdapter( Wednesday,getActivity() ) ;
        recyclerView.setAdapter( WednesdayAnime ) ;
        final Button checkConnection = root.findViewById( R.id.tryagainWemeday ) ;
        new request( url , Wednesday , WednesdayAnime , getActivity() ,"wednesday" , LoadingMonday, noIntenet , checkConnection );
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Wednesday , WednesdayAnime , getActivity() ,"wednesday" , LoadingMonday, noIntenet , checkConnection );
            }
        });
        return  root ;
    }
}

