package programfiles;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.*;

public class TuesdayFragment extends Fragment {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter TuesdayAnime;
    private ArrayList<Anime> Tuesday = new ArrayList<>() ;
    final String url = "https://api.jikan.moe/v3/schedule/tuesday" ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.tuesday, container, false ) ;
        recyclerView=root.findViewById( R.id.TuesdayRecyckeView ) ;
        final ProgressBar LoadingTauesday = root.findViewById( R.id.loadingTuesday ) ;
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 3 )) ;
        TuesdayAnime = new MyAdapter( Tuesday,getActivity() ) ;
        final TextView noIntenet=root.findViewById(R.id.ChecconcetionTuesday);
        final Button checkConnection = root.findViewById( R.id.tryagainTuesday ) ;
        recyclerView.setAdapter(TuesdayAnime) ;
        new request(url , Tuesday , TuesdayAnime , getActivity() , "tuesday" , LoadingTauesday , noIntenet , checkConnection ) ;
        checkConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new request(url , Tuesday , TuesdayAnime , getActivity() , "tuesday" , LoadingTauesday , noIntenet , checkConnection ) ;
            }
        });

        return root;
    }
    public Boolean primenumber(int n){
        boolean a=true;
        if(n==0||n==1){
        }else {
            for (int i = 2; i <= n / 2; i++) {
                if (n % i == 0) {
                    a=false;
                }
            }
        }
    return  a;}
}
