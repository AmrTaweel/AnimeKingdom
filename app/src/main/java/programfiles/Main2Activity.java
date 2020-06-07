package programfiles;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.animekingdom.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;

import me.biubiubiu.justifytext.library.JustifyTextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbarsss);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getIntent().getStringExtra("Name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        OkHttpClient okHttpClient = new OkHttpClient() ;
        final String url = "https://api.jikan.moe/v3/anime/"+getIntent().getStringExtra("mal_id") ;
        final ImageView AnimeImage =findViewById(R.id.AniimeImage);
        final ProgressBar loading=findViewById(R.id.loadingmoreInfo);
        final LinearLayout loadings=findViewById(R.id.loadingsss);
        final TextView Animetitke=findViewById(R.id.AnimeTitle);
        final TextView Englishtitle=findViewById(R.id.Englishtitle);
        final TextView JpanessTitle=findViewById(R.id.titleJpaness);
        final TextView Type=findViewById(R.id.Type);
        final TextView synynoms=findViewById(R.id.sunynoms);
        final TextView Espoides = findViewById(R.id.Episodes);
        final TextView Aired=findViewById(R.id.Aired);
        final TextView Premired=findViewById(R.id.Premired);
        final TextView Broadcast =findViewById(R.id.Broadcast);
        final TextView Producers=findViewById(R.id.Producers);
        final TextView Licensors = findViewById(R.id.Licensors);
        final TextView Studios=findViewById(R.id.Studios);
        final TextView Source=findViewById(R.id.Source);
        final TextView Duration =findViewById(R.id.Duration);
        final TextView Status=findViewById(R.id.Status);
        final TextView Score=findViewById(R.id.score);
        final TextView Rank=findViewById(R.id.rank);
        final TextView Population=findViewById(R.id.popultion);
        final TextView backgrounds=findViewById(R.id.Background);
        final TextView Background=findViewById(R.id.background);
        final TextView Synopsis=findViewById(R.id.synopsis);
        final TextView Members=findViewById(R.id.maember);
        final TextView Adaption=findViewById(R.id.Adaptation);
        final TextView Story=findViewById(R.id.story);
        final TextView relateds=findViewById(R.id.realted);
        final TextView Genres=findViewById(R.id.Genres);
        final CardView backgroundsCard=findViewById(R.id.Backgroundcards);
        final Request request = new Request.Builder()
                .url(url)
                .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                JsonObject jsonObject = (JsonObject)JsonParser.parseString(response.body().string()).getAsJsonObject();
                final String score = String.valueOf(jsonObject.get( "score" )) ;

                final String rank = String.valueOf(jsonObject.get( "rank" )) ;
                final String popularity = String.valueOf(jsonObject.get( "popularity" ));
                final String members = String.valueOf(jsonObject.get( "members" )) ;
                String image = String.valueOf(jsonObject.get( "image_url" )) ;
                image=image.substring(1,image.length()-1);
                String synopsis = String.valueOf(jsonObject.get( "synopsis" ));
                synopsis=synopsis.substring(1,synopsis.length());
                 String type = String.valueOf(jsonObject.get( "type" ) ) ;
                type=type.substring(1,type.length()-1);
                final String episodes = String.valueOf(jsonObject.get("episodes" )) ;
                String status = String.valueOf(jsonObject.get("status")) ;
                status=status.substring(1,status.length()-1);
                String title_japanese = String.valueOf(jsonObject.get("title_japanese")) ;
                title_japanese=title_japanese.substring(1,title_japanese.length()-1);
                 String title_english = String.valueOf(jsonObject.get("title_english")) ;
                 if (title_english.equals("null")){
                     title_english=title_english;
                 }else {
                     title_english=title_english.substring(1,title_english.length()-1);
                 }
                final String title=String.valueOf(jsonObject.get("title"));
                Log.d("TAG", title_english);
                String title_synonyms =new String();
                JsonArray synynomss=jsonObject.getAsJsonArray("title_synonyms");
                if (synynomss==null){
                    title_synonyms="null";
                }else {
                    for (int i=0;i<synynomss.size();i++){
                        if (title_synonyms.length()==0){
                            String firstsynnom=String.valueOf(synynomss.get(i));
                            firstsynnom=firstsynnom.substring(1,firstsynnom.length()-1);
                            title_synonyms=firstsynnom;
                        }else {
                            String firstsynnom=String.valueOf(synynomss.get(i));
                            firstsynnom=firstsynnom.substring(1,firstsynnom.length()-1);
                            title_synonyms=title_synonyms+", "+firstsynnom;

                        }
                    }
                }
                String premiered = String.valueOf(jsonObject.get("premiered")) ;
                premiered=premiered.substring(1,premiered.length()-1);
                String broadcast = String.valueOf(jsonObject.get("broadcast")) ;
                broadcast=broadcast.substring(1,broadcast.length()-1);
                String source = String.valueOf(jsonObject.get("source")) ;
                source=source.substring(1,source.length()-1);
                 String duration = String.valueOf(jsonObject.get("duration")) ;
                 duration=duration.substring(1,duration.length()-1);
                final String background=String.valueOf(jsonObject.get("background"));
                JsonObject aired = jsonObject.getAsJsonObject("aired");
                JsonObject prop = aired.getAsJsonObject("prop");
                JsonObject from = prop.getAsJsonObject("from");
                JsonObject to = prop.getAsJsonObject("to");
                Log.d("taggg", String.valueOf(from));
                String startDay = String.valueOf(from.get("day"));
                String startMonth = String.valueOf(from.get("month"));
                String startYear = String.valueOf(from.get("year"));
                String endDay = String.valueOf(to.get("day"));
                String endMonth = String.valueOf(to.get("month"));
                 String lictensor=new String();
                lictensor=animeinformation(lictensor,jsonObject,"licensors","name");
                String endYear = String.valueOf(to.get("year"));
                String enddate=new String();

                if (startDay.equals("null") &endDay.equals("null")){
                    enddate="N/A"+" to " + "N/A" ;
                }
                else if (endDay.equals("null")){
                    String month=new DateFormatSymbols().getMonths()[Integer.parseInt(startMonth)-1];
                    enddate=startDay+month.substring(0,3)+", "+startYear+" to "+"N/A";
                }else {
                    String month=new DateFormatSymbols().getMonths()[Integer.parseInt(startMonth)-1];
                    String endmonth=new DateFormatSymbols().getMonths()[Integer.parseInt(endMonth)-1];
                    enddate=startDay+month+", "+startYear+" to "+endDay+endmonth+", "+endYear;
                }
                JsonObject related = jsonObject.getAsJsonObject("related");
                final JsonArray Adaptation = related.getAsJsonArray("Adaptation");
                final JsonArray Sidestory=related.getAsJsonArray("Side story");
                ArrayList<String> strings=new ArrayList<String>();
                String airedDate =new String();
                String producers = new String();
                String studio = new String();
                String genres = new String();
                final String storyslide=Arraygetname(Sidestory);
                final String Adaptitions=Arraygetname(Adaptation);
                Log.d("try", Adaptitions);
                producers = animeinformation(producers,jsonObject,"producers","name");
                studio = animeinformation(studio,jsonObject,"studios","name");
                genres= animeinformation(genres,jsonObject,"genres","name");
                final String finalImage = image;
                final String finalAiredDate = airedDate;
                final String finalProducers = producers;
                final String finalStudio = studio;
                final String finalGenres = genres;
                final String finalTitle_synonyms = title_synonyms;
                final String finalPremiered = premiered;
                final String finalBroadcast = broadcast;
                final String finalTitle_japanese = title_japanese;
                final String finalTitle_english = title_english;
                final String finalType = type;
                final String finalSource = source;
                final String finalLictensor = lictensor;
                final String finalDuration = duration;
                final String finalStatus = status;
                final String finalSynopsis = synopsis;
                final String finalEnddate = enddate;
                String endsss="23232328323215435345345345343458098856756556809400840980980980980809092343243243243243098799634";
               double a=Double.parseDouble(endsss);
                Log.d("TESET", String.valueOf(a));
                Main2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getApplicationContext()).load(finalImage).into(AnimeImage);
                        loading.setVisibility(View.GONE);
                        nullsString(Animetitke,title.substring(1,title.length()-1),"",false);
                        nullsString(Englishtitle, finalTitle_english,"English",false);
                        nullsString(JpanessTitle, finalTitle_japanese,"Japanese",false);
                        nullsString(synynoms, finalTitle_synonyms,"Synonyms",false);
                        nullsString(Type, finalType,"Type",true);
                        nullsString(Espoides,episodes,"Espoides",true);
                        nullsString(Premired, finalPremiered,"Premired",true);
                        nullsString(Broadcast, finalBroadcast,"Broadcast",true);
                        nullsString(Producers, finalProducers,"Producers",true);
                        nullsString(Licensors, finalLictensor,"Licensors",true);
                        nullsString(Studios, finalStudio,"Studios",true);
                        nullsString(Source, finalSource,"Source",true);
                        nullsString(Genres, finalGenres,"Genres",true);
                        nullsString(Duration, finalDuration,"Duration",true);
                        nullsString(Status, finalStatus,"Status",true);
                        nullsString(Score,score,"Score",true);
                        nullsString(Rank,rank,"Rank",true);
                        nullsString(Population,popularity,"Popularity",true);
                        nullsString(Members,members,"Members",true);
                        nullsString(Synopsis, finalSynopsis,"",true);
                        nullsString(Background,background,"",false);
                        nullsString(Adaption,Adaptitions,"Adaptation",true);
                        nullsString(Aired, finalEnddate,"Aired",true);
                        nullsString(Story,storyslide,"Side Story",true);
                        loadings.setVisibility(View.GONE);
                                if(storyslide.length()==0&Adaptitions.length()==0){
                                    relateds.setVisibility(View.GONE);
                                }
                        if (Background.getText().toString().isEmpty()){
                            backgrounds.setVisibility(View.GONE);
                            backgroundsCard.setVisibility(View.GONE);
                        }


                    }
                });
            }
        });
    }
    public String animeinformation(String endvalue ,JsonObject input,String JsonObjectGet,String Get){
        JsonArray jsonArray = input.get(JsonObjectGet).getAsJsonArray() ;
        if (jsonArray.size()==0){
            endvalue="N/A";
        }else {
            for (int i = 0 ; i < jsonArray.size(); i++){
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);
                if (endvalue.length()==0){
                    String ending = String.valueOf(jsonObject.get(Get));
                    ending = ending.substring(1,ending.length()-1);
                    endvalue = ending;
                }else {
                    String ending = String.valueOf(jsonObject.get(Get));
                    ending = ending.substring(1,ending.length()-1);
                    endvalue = endvalue+", "+ending;
                }
            }
        }
        return endvalue;
    }
    public String Arraygetname(JsonArray jsonArray){
        String name=new String();
        if (jsonArray==null){
            name=name;
        }else {
            for (int i =0 ; i<jsonArray.size() ; i++){
                JsonObject getname=(JsonObject)jsonArray.get(i);
                if (name.length()==0){
                    String endname=String.valueOf(getname.get("name"));
                    endname=endname.substring(1,endname.length()-1);
                    name=endname;
                }else {
                    String endname=String.valueOf(getname.get("name"));
                    endname=endname.substring(1,endname.length()-1);
                    name=name+", "+ endname;
                }

            }
        }
        return name;
    }
    public String nullsString(TextView textView,String a,String b,Boolean bolean){
        if (a.equals("null")& bolean==false||a.length()==0||a==null){
            textView.setVisibility(View.GONE);
        }else if (a.equals("null")&bolean==true||a.equals("ul")){
            if (b.equals("Rank")){
                textView.setText(Html.fromHtml("<b>"+b+": "+"</b>"+"#N/A"));
            }else {
                textView.setText(Html.fromHtml("<b>"+b+": "+"</b>"+"N/A"));
            }
        }else {
            if (b.length()==0){
                textView.setText(a);
            }else {
                if (b.equals("Rank")){
                    textView.setText(Html.fromHtml("<b>"+b+": "+"</b>"+"#"+a));
                }else {
                    textView.setText(Html.fromHtml("<b>"+b+": "+"</b>"+a));
                }
            }
        }
        return a;
    }
}
