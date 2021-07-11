package com.mkemp.artbooktestingjava.view;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

public class ArtFragmentFactory extends FragmentFactory
{
    private final RequestManager glide;

    @Inject
    public ArtFragmentFactory(RequestManager glide)
    {
        this.glide = glide;
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
        else
        {
            return super.instantiate(classLoader, className);
        }
    }
}
