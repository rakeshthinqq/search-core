package com.searchapp.dto;

import java.util.Objects;

public class Gif {

    private String gif_id;
    private String url;

    public Gif(String gif_id, String url) {
        this.gif_id = gif_id;
        this.url = url;
    }

    public String getGif_id() {
        return gif_id;
    }

    public void setGif_id(String gif_id) {
        this.gif_id = gif_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gif gif = (Gif) o;
        return Objects.equals(gif_id, gif.gif_id) &&
                Objects.equals(url, gif.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gif_id, url);
    }
}
