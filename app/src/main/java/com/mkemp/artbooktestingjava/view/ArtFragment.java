package com.mkemp.artbooktestingjava.view;

import android.os.Bundle;
import android.view.View;

import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.databinding.FragmentArtsBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ArtFragment extends Fragment
{
    private FragmentArtsBinding fragmentBinding;

    public ArtFragment()
    {
        super(R.layout.fragment_arts);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        fragmentBinding = FragmentArtsBinding.bind(view);
        fragmentBinding.fab.setOnClickListener(v -> Navigation.findNavController(view)
                .navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        );
    }

    @Override
    public void onDestroyView()
    {
        fragmentBinding = null;
        super.onDestroyView();
    }
}

