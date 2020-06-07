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

public class SundayFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter SundayAnime;
    private ArrayList<Anime> Sunday = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/schedule/sunday" ;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.sunday, container, false ) ;
        recyclerView=root.findViewById( R.id.SundayRecyclevView ) ;
        final ProgressBar LoadingSunday = root.findViewById( R.id.loadingSunday ) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 )) ;
        SundayAnime = new MyAdapter( Sunday, getActivity() ) ;
        recyclerView.setAdapter( SundayAnime ) ;
        final TextView noIntenet = root.findViewById( R.id.CheckconcetionSunday ) ;
        final Button checkConnection = root.findViewById( R.id.tryagainsunday ) ;
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Sunday , SundayAnime , getActivity() , "sunday" , LoadingSunday , noIntenet , checkConnection ) ;
            }
        });
        new request( url , Sunday , SundayAnime , getActivity() , "sunday" , LoadingSunday , noIntenet , checkConnection ) ;
        return root ;
    }
}

