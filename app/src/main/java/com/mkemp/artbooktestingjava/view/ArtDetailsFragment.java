package com.mkemp.artbooktestingjava.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.databinding.FragmentArtDetailsBinding;
import com.mkemp.artbooktestingjava.roomdb.Art;
import com.mkemp.artbooktestingjava.util.Resource;
import com.mkemp.artbooktestingjava.viewmodel.ArtViewModel;

import javax.inject.Inject;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class ArtDetailsFragment extends Fragment
{
    private final RequestManager glide;

    private FragmentArtDetailsBinding fragmentBinding;
    private ArtViewModel viewModel;

    @Inject
    public ArtDetailsFragment(RequestManager glide)
    {
        super(R.layout.fragment_art_details);
        this.glide = glide;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ArtViewModel.class);

        final NavController navController = Navigation.findNavController(view);

        fragmentBinding = FragmentArtDetailsBinding.bind(view);
        fragmentBinding.artImageView.setOnClickListener(v ->
                navController.navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        );

        subscribeToObservers(view);

        getActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true)
        {
            @Override
            public void handleOnBackPressed()
            {
                navController.popBackStack();
            }
        });

        fragmentBinding.saveButton.setOnClickListener(v ->
                viewModel.makeArt(
                        fragmentBinding.nameText.getText().toString(),
                        fragmentBinding.artistText.getText().toString(),
                        fragmentBinding.yearText.getText().toString()
                ));
    }

    private void subscribeToObservers(final View view)
    {
        viewModel.getSelectedImageUrl().observe(getViewLifecycleOwner(), url ->
                glide.load(url).into(fragmentBinding.artImageView));

        viewModel.getInsertArtMessage().observe(getViewLifecycleOwner(), new Observer<Resource<Art>>()
        {
            @Override
            public void onChanged(final Resource<Art> artResource)
            {
                switch (artResource.status)
                {
                    case SUCCESS:
                    {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).popBackStack();
                        viewModel.resetInsertArtMessage();
                        break;
                    }
                    case ERROR:
                    {
                        Toast.makeText(requireContext(), artResource.message == null ? "Error" : artResource.message, Toast.LENGTH_LONG).show();
                        break;
                    }
                    case LOADING:
                    {
                        break;
                    }
                }
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
