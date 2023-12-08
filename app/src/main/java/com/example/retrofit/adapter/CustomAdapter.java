package com.example.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.R;
import com.example.retrofit.activity.RecyclerViewInterface;
import com.example.retrofit.model.Movie;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private RecyclerViewInterface recyclerViewInterface;
    private List<Movie> dataList;
    private Context context;

    public CustomAdapter(Context context, List<Movie> dataList, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.dataList = dataList != null ? dataList : new ArrayList<>();
        this.recyclerViewInterface = recyclerViewInterface;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        TextView txtYear;
        TextView txtGenres;
        private ImageView coverImage;

        CustomViewHolder(View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
            txtYear = mView.findViewById(R.id.year);
            txtGenres = mView.findViewById(R.id.genres);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface !=null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
        //OnClick packege to send the movie
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        String release_date = dataList.get(position).getRelease_date();
        if(release_date != null){
            String year = release_date.substring(0,4);
            holder.txtYear.setText(year);
        }
        else{
            holder.txtYear.setText("");
        }
        String genres = "";
        for (String genre: dataList.get(position).getGenres()){
            genres += genre + ", ";
        }
        genres = genres.substring(0,genres.lastIndexOf(","));

        holder.txtGenres.setText(genres);
        holder.txtTitle.setText(dataList.get(position).getTitle());


        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}




