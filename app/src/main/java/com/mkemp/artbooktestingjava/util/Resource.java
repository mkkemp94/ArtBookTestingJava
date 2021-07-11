package com.mkemp.artbooktestingjava.util;

import javax.annotation.Nullable;

import androidx.annotation.NonNull;

public class Resource<T>
{
    enum Status
    {
        SUCCESS,
        ERROR,
        LOADING
    }

    @NonNull
    private final Status status;

    @Nullable
    private final T data;

    @Nullable
    private final String message;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message)
    {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data)
    {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String message, @Nullable T data)
    {
        return new Resource<>(Status.ERROR, data, message);
    }

    public static <T> Resource<T> loading(@Nullable T data)
    {
        return new Resource<>(Status.LOADING, data, null);
    }
}
