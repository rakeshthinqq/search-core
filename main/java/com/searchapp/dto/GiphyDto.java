package com.searchapp.dto;

import java.util.Objects;

public class GiphyDto {

    private String id;
    private String url;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GiphyDto{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiphyDto giphyDto = (GiphyDto) o;
        return Objects.equals(id, giphyDto.id) &&
                Objects.equals(url, giphyDto.url) &&
                Objects.equals(title, giphyDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, title);
    }
}
