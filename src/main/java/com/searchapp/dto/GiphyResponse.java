package com.searchapp.dto;

import java.util.List;

public class GiphyResponse {

    List<GiphyDto> data;

    public List<GiphyDto> getData() {
        return data;
    }

    public void setData(List<GiphyDto> data) {
        this.data = data;
    }
}
