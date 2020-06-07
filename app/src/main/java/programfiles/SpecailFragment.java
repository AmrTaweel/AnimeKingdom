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

public class SpecailFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter TopSpecail;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Anime> Specailss = new ArrayList<>();
    final String url = "https://api.jikan.moe/v3/top/anime/" + 1 + "/special";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.specail, container, false) ;
        recyclerView = root.findViewById( R.id.specails ) ;
        final ProgressBar LoadingSpecail=root.findViewById( R.id.loadingSpecail ) ;
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager( getActivity(),3 ) ;
        recyclerView.setLayoutManager( layoutManager ) ;
        TopSpecail = new MyAdapter( Specailss, getActivity() ) ;
        recyclerView.setAdapter( TopSpecail );
        final Button checkConnection = root.findViewById( R.id.tryagainSpecail );
        final TextView noIntenet = root.findViewById( R.id.CheckconcetionSpecails ) ;
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request( url , Specailss , TopSpecail , getActivity() ,"top" , LoadingSpecail , noIntenet , checkConnection ) ;
            }
        });
        new request( url , Specailss , TopSpecail , getActivity() ,"top" , LoadingSpecail , noIntenet , checkConnection ) ;
        new recyclescrolling( Specailss , "https://api.jikan.moe/v3/top/anime/" , "/special" ,LoadingSpecail , recyclerView , layoutManager , TopSpecail , getActivity() , "top",noIntenet);
        return  root ;
    }
}