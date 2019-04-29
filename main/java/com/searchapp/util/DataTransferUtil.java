package com.searchapp.util;

import com.google.gson.Gson;
import com.searchapp.dto.Gif;
import com.searchapp.dto.GiphyDto;
import com.searchapp.dto.GiphyResponse;

import java.util.ArrayList;
import java.util.List;

public class DataTransferUtil {

    private static final Gson gson = new Gson();

    public static List<Gif> convertGiphyToGif(List<GiphyDto> data) {
        List<Gif> gfs = new ArrayList<>();

        for(GiphyDto dto: data){
            gfs.add(new Gif(dto.getId(), dto.getUrl()));
        }
        return gfs;
    }


    public static GiphyResponse toGiphyResponse(String message, Class<GiphyResponse> giphyResponseClass) {
        return gson.fromJson(message, giphyResponseClass);
    }

    public static String convertGiphyResponse(GiphyResponse giphyResponse) {
        return gson.toJson(giphyResponse);
    }
}
