package programfiles ;
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

public class MondayFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter MondayAnime;
    private ArrayList<Anime> Monday = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/schedule/monday" ;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.monday, container, false) ;
        recyclerView = root.findViewById(R.id.MondayRecyclevView) ;
        final ProgressBar LoadingMonday = root.findViewById( R.id.LoadingMonday ) ;
        final Button checkConnection=root.findViewById( R.id.tryagainmonday );
        recyclerView.setHasFixedSize(true) ;
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 ))  ;
        MondayAnime = new MyAdapter(Monday, getActivity()) ;
        recyclerView.setAdapter(MondayAnime) ;
        final TextView noIntenet = root.findViewById( R.id.ChecconcetionMonday ) ;
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Monday , MondayAnime , getActivity() ,"monday" , LoadingMonday ,noIntenet ,checkConnection ) ;
            }
        });
        new request( url , Monday , MondayAnime , getActivity() ,"monday" , LoadingMonday ,noIntenet ,checkConnection ) ;
        return root ;
    }
}