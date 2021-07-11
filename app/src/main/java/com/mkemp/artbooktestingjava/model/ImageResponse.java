package com.mkemp.artbooktestingjava.model;

import java.util.List;

public class ImageResponse
{
    private final List<ImageResult> hits;
    private final int total;
    private final int totalHits;

    public ImageResponse(List<ImageResult> hits,
                         int total,
                         int totalHits)
    {
        this.hits = hits;
        this.total = total;
        this.totalHits = totalHits;
    }

    public List<ImageResult> getHits()
    {
        return hits;
    }

    public int getTotal()
    {
        return total;
    }

    public int getTotalHits()
    {
        return totalHits;
    }
}
