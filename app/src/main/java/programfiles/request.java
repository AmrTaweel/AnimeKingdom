package programfiles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class request{
    boolean connected = true;
    private ArrayList<Anime> anime;
    ProgressBar loading;
    RecyclerView.Adapter myAdapter;
    TextView NOCONCTEION;
    String url;
    Button checkConnection;
    Context a;
    public request(String url, final ArrayList<Anime> anime, final RecyclerView.Adapter myAdapter, final Context context, final String word, final ProgressBar loading, final TextView NOCONCTEION, final Button checkConnection) {
        NOCONCTEION.setVisibility(View.GONE);
        this.anime = anime;
        this.url = url;
        a=context;
        this.NOCONCTEION = NOCONCTEION;
        this.myAdapter = myAdapter;
        this.checkConnection = checkConnection;
        this.loading = loading;
        NOCONCTEION.setVisibility(View.GONE);
        checkConnection.setVisibility(View.GONE);
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isconncted = activeNetwork != null && activeNetwork.isConnected();
        if (isconncted == false) {
            NOCONCTEION.setText("There is no internet conncetion");
            loading.setVisibility(View.GONE);
            checkConnection.setVisibility(View.VISIBLE);
            NOCONCTEION.setVisibility(View.VISIBLE);
        } else {
            NOCONCTEION.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
            OkHttpHandler a=new OkHttpHandler();
            a.execute(url);

        }
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
                myAdapter.notifyDataSetChanged();
                loading.setVisibility(View.GONE);
            }else {
                NOCONCTEION.setText("There is an error");
                checkConnection.setVisibility(View.VISIBLE);
                NOCONCTEION.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
            }
        }
    }
}
