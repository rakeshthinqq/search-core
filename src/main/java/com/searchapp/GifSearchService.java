package com.searchapp;

import com.searchapp.dto.Gif;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GifSearchService {

    private final AtomicLong counter = new AtomicLong();

    public List<Gif> searchGifs() {

        List<Gif> gifs = new ArrayList<>();
        gifs.add(new Gif(String.valueOf(counter.incrementAndGet()), "http://abc.com/123"));
        return gifs;
    }
}
