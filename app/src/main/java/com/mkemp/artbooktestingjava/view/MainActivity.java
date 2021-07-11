package com.mkemp.artbooktestingjava.view;

import android.os.Bundle;

import com.mkemp.artbooktestingjava.R;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity
{
    @Inject
    public ArtFragmentFactory fragmentFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().setFragmentFactory(fragmentFactory);
        setContentView(R.layout.activity_main);
    }
}