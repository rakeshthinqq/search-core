package com.searchapp.service;

import com.searchapp.dto.Gif;

import java.util.List;

public interface GifSearch {
    List<Gif> searchGifs(String query);
}