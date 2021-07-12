package com.mkemp.artbooktestingjava.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.roomdb.Art;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ArtRecyclerAdapter extends RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>
{
    public static class ArtViewHolder extends RecyclerView.ViewHolder
    {
        public ArtViewHolder(@NonNull final View itemView)
        {
            super(itemView);
        }
    }

    private final DiffUtil.ItemCallback<Art> diffUtil = new DiffUtil.ItemCallback<Art>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull final Art oldItem, @NonNull final Art newItem)
        {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull final Art oldItem, @NonNull final Art newItem)
        {
            return oldItem == newItem;
        }
    };

    private final AsyncListDiffer<Art> recyclerListDiffer = new AsyncListDiffer<>(this, diffUtil);
    private final RequestManager glide;

    @Inject
    public ArtRecyclerAdapter(RequestManager glide)
    {
        this.glide = glide;
    }

    public List<Art> getArts()
    {
        return recyclerListDiffer.getCurrentList();
    }

    public void setArts(List<Art> value)
    {
        recyclerListDiffer.submitList(value);
    }

    @NonNull
    @Override
    public ArtViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.art_row, parent, false);
        return new ArtViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ArtViewHolder holder, final int position)
    {
        final ImageView imageView = holder.itemView.findViewById(R.id.artRowImageView);
        final TextView nameText = holder.itemView.findViewById(R.id.artRowNameText);
        final TextView artistNameText = holder.itemView.findViewById(R.id.artRowArtistNameText);
        final TextView yearText = holder.itemView.findViewById(R.id.artRowYearText);
        final Art art = getArts().get(position);
        nameText.setText("Name: " + art.getName());
        artistNameText.setText("Artist Name: " + art.getArtistName());
        yearText.setText("Year: " + art.getYear());
        glide.load(art.getImageUrl()).into(imageView);
    }

    @Override
    public int getItemCount()
    {
        return getArts().size();
    }
}
