package com.mkemp.artbooktestingjava.view;

import com.bumptech.glide.RequestManager;
import com.mkemp.artbooktestingjava.adapter.ArtRecyclerAdapter;
import com.mkemp.artbooktestingjava.adapter.ImageRecyclerAdapter;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

public class ArtFragmentFactory extends FragmentFactory
{
    private final RequestManager glide;
    private final ArtRecyclerAdapter artRecyclerAdapter;
    private final ImageRecyclerAdapter imageRecyclerAdapter;

    @Inject
    public ArtFragmentFactory(RequestManager glide,
                              ArtRecyclerAdapter artRecyclerAdapter,
                              ImageRecyclerAdapter imageRecyclerAdapter)
    {
        this.glide = glide;
        this.artRecyclerAdapter = artRecyclerAdapter;
        this.imageRecyclerAdapter = imageRecyclerAdapter;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull final ClassLoader classLoader, @NonNull final String className)
    {
        final Class<? extends Fragment> clazz = loadFragmentClass(classLoader, className);
        if (clazz == ArtDetailsFragment.class)
        {
            return new ArtDetailsFragment(glide);
        }
        else if (clazz == ArtFragment.class)
        {
            return new ArtFragment(artRecyclerAdapter);
        }
        else if (clazz == ImageApiFragment.class)
        {
            return new ImageApiFragment(imageRecyclerAdapter);
        }
        else
        {
            return super.instantiate(classLoader, className);
        }
    }
}
