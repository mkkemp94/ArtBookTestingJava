package com.mkemp.artbooktestingjava.view;

import android.os.Bundle;
import android.view.View;

import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.adapter.ImageRecyclerAdapter;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageApiFragment extends Fragment
{
    private final ImageRecyclerAdapter imageRecyclerAdapter;

    @Inject
    public ImageApiFragment(ImageRecyclerAdapter imageRecyclerAdapter)
    {
        super(R.layout.fragment_image_api);
        this.imageRecyclerAdapter = imageRecyclerAdapter;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // use view binding here
    }
}

