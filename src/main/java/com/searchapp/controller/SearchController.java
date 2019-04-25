package com.searchapp.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.searchapp.GifSearchService;
import com.searchapp.dto.Gif;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    GifSearchService gifSearchService;

    @RequestMapping(path="/search", method = RequestMethod.GET)
    public String  search(@RequestParam(value="type", defaultValue="gif", required=false) String type) {

        JSONObject output = new JSONObject();
        output.put("data", gifSearchService.searchGifs());
        return output.toString();
    }
}
