package programfiles;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import programfiles.Anime;

public class load extends AsyncTaskLoader<ArrayList<Anime>> {
    String url;
    ArrayList<Anime>anime;
    public load(@NonNull Context context,String url,ArrayList<Anime>anime) {
        super(context);
        this.anime=anime;
        this.url=url;
    }

    @Nullable
    @Override
    public ArrayList<Anime> loadInBackground() {
        OkHttpClient client=new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();
        try {

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
            ArrayList<Anime>animes=new ArrayList<>();
            animes.add(new Anime("","",0,"A"));
            return animes;
        }
        return anime;
    }
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
