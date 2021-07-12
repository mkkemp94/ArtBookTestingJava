package com.mkemp.artbooktestingjava.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.mkemp.artbooktestingjava.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>
{
    public interface OnItemClickListener
    {
        void onItemClick(final String imageUrl);
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder
    {
        public ImageViewHolder(@NonNull final View itemView)
        {
            super(itemView);
        }
    }

    private final DiffUtil.ItemCallback<String> diffUtil = new DiffUtil.ItemCallback<String>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull final String oldItem, @NonNull final String newItem)
        {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull final String oldItem, @NonNull final String newItem)
        {
            return oldItem == newItem;
        }
    };

    private final AsyncListDiffer<String> recyclerListDiffer = new AsyncListDiffer<>(this, diffUtil);
    private final RequestManager glide;

    private OnItemClickListener onItemClickListener;

    private List<String> images = new ArrayList<>();

    @Inject
    public ImageRecyclerAdapter(RequestManager glide)
    {
        this.glide = glide;
    }

    public List<String> getImages()
    {
        return recyclerListDiffer.getCurrentList();
    }

    public void setImages(List<String> value)
    {
        recyclerListDiffer.submitList(value);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ImageRecyclerAdapter.ImageViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType)
    {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row, parent, false);
        return new ImageRecyclerAdapter.ImageViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ImageRecyclerAdapter.ImageViewHolder holder, final int position)
    {
        final ImageView imageView = holder.itemView.findViewById(R.id.singleArtImageView);
        final String url = images.get(position);
        glide.load(url).into(imageView);
        imageView.setOnClickListener(v ->
        {
            if (onItemClickListener != null)
            {
                onItemClickListener.onItemClick(url);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return images.size();
    }
}
