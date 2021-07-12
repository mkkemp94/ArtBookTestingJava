package com.mkemp.artbooktestingjava.view;

import android.os.Bundle;
import android.view.View;

import com.mkemp.artbooktestingjava.R;
import com.mkemp.artbooktestingjava.adapter.ArtRecyclerAdapter;
import com.mkemp.artbooktestingjava.databinding.FragmentArtsBinding;
import com.mkemp.artbooktestingjava.roomdb.Art;
import com.mkemp.artbooktestingjava.viewmodel.ArtViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArtFragment extends Fragment
{
    private final ArtRecyclerAdapter artRecyclerAdapter;

    private FragmentArtsBinding fragmentBinding;
    private ArtViewModel viewModel;

    private final ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
    {

        @Override
        public boolean onMove(@NonNull final RecyclerView recyclerView, @NonNull final RecyclerView.ViewHolder viewHolder, @NonNull final RecyclerView.ViewHolder target)
        {
            return true;
        }

        @Override
        public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, final int direction)
        {
            final int layoutPosition = viewHolder.getLayoutPosition();
            final Art selectedArt = artRecyclerAdapter.getArts().get(layoutPosition);
            viewModel.deleteArt(selectedArt);
        }
    };

    @Inject
    public ArtFragment(ArtRecyclerAdapter artRecyclerAdapter)
    {
        super(R.layout.fragment_arts);
        this.artRecyclerAdapter = artRecyclerAdapter;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ArtViewModel.class);

        fragmentBinding = FragmentArtsBinding.bind(view);

        subscribeToObservers();

        fragmentBinding.recyclerViewArt.setAdapter(artRecyclerAdapter);
        fragmentBinding.recyclerViewArt.setLayoutManager(new LinearLayoutManager(requireContext()));
        new ItemTouchHelper(swipeCallback).attachToRecyclerView(fragmentBinding.recyclerViewArt);

        fragmentBinding.fab.setOnClickListener(v -> Navigation.findNavController(view)
                .navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        );
    }

    private void subscribeToObservers()
    {
        viewModel.getArtList().observe(getViewLifecycleOwner(), artRecyclerAdapter::setArts);
    }

    @Override
    public void onDestroyView()
    {
        fragmentBinding = null;
        super.onDestroyView();
    }
}

