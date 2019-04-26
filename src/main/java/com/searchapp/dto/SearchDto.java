package com.searchapp.dto;

import java.util.List;

public class SearchDto {

    private List<Gif> data;

    public List<Gif> getData() {
        return data;
    }

    public void setData(List<Gif> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchDto{" +
                "data=" + data +
                '}';
    }
}
