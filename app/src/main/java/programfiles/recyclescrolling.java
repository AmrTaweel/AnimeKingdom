package programfiles;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.tls.OkHostnameVerifier;

public class recyclescrolling {
    int aa=1;
    int i=0;
    ArrayList<Anime>anime;
    RecyclerView.Adapter adapter;
    ProgressBar loading;
    TextView NOCONCTEION;
    public recyclescrolling(final ArrayList<Anime>anime, final String url, final String h, final ProgressBar loading, RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager, final RecyclerView.Adapter adapter, final Context context, final String word, final TextView noConncetion){
        this.anime = anime ;
        this.NOCONCTEION=noConncetion;
        this.loading = loading ;
        this.adapter = adapter;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if ((layoutManager.getChildCount() + ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition()) >= layoutManager.getItemCount()) {
                    i = i + 1;
                    if (i == 1) {
                        loading.setVisibility(View.VISIBLE);
                        noConncetion.setVisibility(View.VISIBLE);
                        aa = aa + 1;
                        OkHttpClient okHttpClient = new OkHttpClient();
                        String finals = url + aa + h;
                        Log.d("tagsss", finals);
                        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        boolean isconncted = activeNetwork != null && activeNetwork.isConnected();
                        Request request = new Request.Builder().url(finals).build();
                        if (isconncted == false) {
                            noConncetion.setVisibility(View.VISIBLE);
                            noConncetion.setText("There is no internet conncetion");
                            loading.setVisibility(View.GONE);
                        } else {
                            noConncetion.setVisibility(View.GONE);
                            OkHttpHandler okHttpHandler=new OkHttpHandler();
                            okHttpHandler.execute(finals);
                            i=0;
                        }
                    }
                }
            }

        });
    }
    private class OkHttpHandler extends AsyncTask<String, Void, ArrayList<Anime>> {
        OkHttpClient client = new OkHttpClient();
        String a;
        @Override
        protected ArrayList<Anime> doInBackground(String... params) {
            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();
            try {
                a="A";
                JsonArray friday = ((JsonObject) JsonParser.parseString(client.newCall(request).execute().body().string())).getAsJsonArray("top");
                for (int i = 0; i < friday.size(); i++) {
                    JsonObject moreInfo = (JsonObject) friday.get(i);
                    String title = String.valueOf(moreInfo.get("title"));
                    title = title.substring(1, title.length() - 1);
                    String image = String.valueOf(moreInfo.get("image_url"));
                    String score = (String.valueOf(moreInfo.get("score")));
                    int mal_id = Integer.valueOf(String.valueOf(moreInfo.get("mal_id")));
                    image = image.substring(1, image.length() - 1);
                    if (score.equals("0")||score.equals("null")) {
                        anime.add(new Anime(title, "N/A", mal_id, image));
                    } else {
                        anime.add(new Anime(title, score, mal_id, image));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                a=new String();
            }
            return anime;
        } protected void onPostExecute(ArrayList<Anime>result) {
            if (a.length()!=0) {
                adapter.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
            }else {
                NOCONCTEION.setText("There is an error");

                loading.setVisibility(View.GONE);
            }
        }
    }
}
