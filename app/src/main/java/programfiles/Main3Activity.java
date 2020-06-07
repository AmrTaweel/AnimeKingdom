package programfiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.animekingdom.R;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Main3Activity extends AppCompatActivity {
        ArrayList<Anime>Searchs=new ArrayList<Anime>();
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter SearchAnime ;
    private RecyclerView.LayoutManager layoutManager ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbars);
        recyclerView=findViewById(R.id.SearchRecycleview);
        final ProgressBar loading=findViewById(R.id.loadingsearch);
        loading.setVisibility(View.GONE);
        final TextView eeror=findViewById(R.id.error);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext()) ;
        recyclerView.setLayoutManager( new GridLayoutManager( getApplicationContext(), 3 )) ;
        SearchAnime = new MyAdapter(Searchs, getApplicationContext()) ;
        recyclerView.setAdapter(SearchAnime) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Button checkConnection =findViewById( R.id.tryagain ) ;
        final ImageButton Search=findViewById(R.id.SearchButton);
        final EditText AnimeName=findViewById(R.id.AnimeSearch);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main3Activity.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
        AnimeName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId== EditorInfo.IME_ACTION_DONE){
                    Searchs.clear();
                    String url = "https://api.jikan.moe/v3/search/anime?q=" + AnimeName.getText().toString();
                    new request( url , Searchs ,SearchAnime , Main3Activity.this , "results" , loading , eeror ,checkConnection );
                }
            return false;}

        });
        checkConnection.setVisibility(View.GONE);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnimeName.getText().toString()!=null) {
                    loading.setVisibility(View.VISIBLE);
                    Searchs.clear();
                    String url = "https://api.jikan.moe/v3/search/anime?q=" + AnimeName.getText().toString();
                    OkHttpClient okHttpClient = new OkHttpClient();
                    final Request request = new Request.Builder()
                            .url(url)
                            .build();
                   new request(url,Searchs,SearchAnime,Main3Activity.this,"results",loading,eeror,checkConnection);
        }
            }
        });


    }

    }
