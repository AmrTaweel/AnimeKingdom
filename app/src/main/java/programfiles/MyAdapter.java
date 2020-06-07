package programfiles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater ;
import android.view.View ;
import android.view.ViewGroup ;
import android.widget.ImageView ;
import android.widget.TextView ;

import androidx.recyclerview.widget.RecyclerView ;

import com.bumptech.glide.Glide;
import com.example.animekingdom.R;
;
import java.util.ArrayList ;

public class MyAdapter extends RecyclerView.Adapter< MyAdapter.MyViewHolder > {
    ArrayList<Anime> anime;
    Context context;



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView AnimeName;
        public TextView Animescore;
        public ImageView AnimeImage;
        View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
            AnimeName = v.findViewById(R.id.AnimeName);
            Animescore = v.findViewById(R.id.AnimeRank);
            AnimeImage = v.findViewById(R.id.AnimePhoto);

        }
    }

    public MyAdapter(ArrayList<Anime> anime,Context context) {
        this.anime = anime;
        this.context=context;
    }

    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_layout, parent, false);
        return new MyViewHolder(v);
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.AnimeName.setText(anime.get(position).getTitle());
        holder.Animescore.setText(String.valueOf(anime.get(position).getScore()));
        Glide.with(context).load(anime.get(position).getImage_url()).placeholder(0).error(0).into(holder.AnimeImage);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("Name",anime.get(position).getTitle());
                intent.putExtra("mal_id",String.valueOf(anime.get(position).getMal_id()));
                context.startActivity(intent);

        }
        });
    }

    @Override
    public int getItemCount() {
        return anime.size();
    }

}
