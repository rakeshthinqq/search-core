package com.searchapp.util;

import com.searchapp.dto.Gif;
import com.searchapp.dto.GiphyDto;

import java.util.ArrayList;
import java.util.List;

public class GiphyDataTransfer {


    public static List<Gif> convertGiphyToGif(List<GiphyDto> data) {
        List<Gif> gfs = new ArrayList<>();

        for(GiphyDto dto: data){
            gfs.add(new Gif(dto.getId(), dto.getUrl()));
        }
        return gfs;
    }
}
