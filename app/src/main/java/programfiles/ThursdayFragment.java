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

public class ThursdayFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter ThursdayAnime;
    private ArrayList<Anime> Monday = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/schedule/thursday" ;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.thuresday, container ,  false ) ;
        recyclerView=root.findViewById( R.id.ThuersdayRecyclevView ) ;
        final ProgressBar LoadingThurersday = root.findViewById( R.id.loadingThuersday ) ;
        recyclerView.setHasFixedSize( true ) ;
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 )) ;
        ThursdayAnime = new MyAdapter( Monday, getActivity() ) ;
        recyclerView.setAdapter( ThursdayAnime ) ;
        final TextView noIntenet = root.findViewById( R.id.CheckconcetionThursday ) ;
        final Button checkConnection = root.findViewById( R.id.tryagainThursday );
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Monday , ThursdayAnime, getActivity() ,"thursday" , LoadingThurersday , noIntenet ,checkConnection ) ;
            }
        });
        new request( url , Monday , ThursdayAnime, getActivity() ,"thursday" , LoadingThurersday , noIntenet ,checkConnection ) ;
        return root;
    }
}
