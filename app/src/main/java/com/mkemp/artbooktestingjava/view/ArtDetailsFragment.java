package com.mkemp.artbooktestingjava.view;

import android.os.Bundle;
import android.view.View;

import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.databinding.FragmentArtDetailsBinding;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ArtDetailsFragment extends Fragment
{
    private FragmentArtDetailsBinding fragmentBinding;

    public ArtDetailsFragment()
    {
        super(R.layout.fragment_art_details);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        fragmentBinding = FragmentArtDetailsBinding.bind(view);
        fragmentBinding.artImageView.setOnClickListener(v ->
                navController.navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        );

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true)
        {
            @Override
            public void handleOnBackPressed()
            {
                navController.popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        fragmentBinding = null;
        super.onDestroyView();
    }
}
