package com.mkemp.artbooktestingjava.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.adapter.ImageRecyclerAdapter;
import com.mkemp.artbooktestingjava.databinding.FragmentImageApiBinding;
import com.mkemp.artbooktestingjava.model.ImageResponse;
import com.mkemp.artbooktestingjava.model.ImageResult;
import com.mkemp.artbooktestingjava.viewmodel.ArtViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

public class ImageApiFragment extends Fragment
{
    private final ImageRecyclerAdapter imageRecyclerAdapter;
    private ArtViewModel viewModel;
    private FragmentImageApiBinding fragmentBinding;

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

        viewModel = new ViewModelProvider(requireActivity()).get(ArtViewModel.class);

        fragmentBinding = FragmentImageApiBinding.bind(view);
        fragmentBinding.searchText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after)
            {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count)
            {

            }

            @Override
            public void afterTextChanged(final Editable s)
            {
                if (! s.toString().isEmpty())
                {
                    viewModel.searchForImage(s.toString());
                }
            }
        });

        subscribeToObservers();

        fragmentBinding.imageRecyclerView.setAdapter(imageRecyclerAdapter);
        fragmentBinding.imageRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        imageRecyclerAdapter.setOnItemClickListener(imageUrl ->
        {
            Navigation.findNavController(view).popBackStack();
            viewModel.setSelectedImage(imageUrl);
        });
    }

    private void subscribeToObservers()
    {
        viewModel.getImageList().observe(getViewLifecycleOwner(), resource ->
        {
            switch (resource.status)
            {
                case SUCCESS:
                {
                    final ImageResponse data = resource.data;
                    if (data != null)
                    {
                        final List<ImageResult> hits = data.getHits();
                        final ArrayList<String> urls = new ArrayList<>();
                        if (hits != null)
                        {
                            for (ImageResult hit : hits)
                            {
                                urls.add(hit.getPreviewURL());
                            }
                        }

                        imageRecyclerAdapter.setImages(urls);
                        fragmentBinding.progressBar.setVisibility(View.GONE);
                    }
                    break;
                }
                case ERROR:
                {
                    fragmentBinding.progressBar.setVisibility(View.GONE);
                    break;
                }
                case LOADING:
                {
                    fragmentBinding.progressBar.setVisibility(View.VISIBLE);
                    break;
                }
            }
        });
    }
}

