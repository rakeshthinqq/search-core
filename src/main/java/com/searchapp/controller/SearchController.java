package com.searchapp.controller;

import com.searchapp.service.GifSearchService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    @Autowired
    GifSearchService gifSearchService;

    @RequestMapping(path="/search/{query}", method = RequestMethod.GET)
    public String  search(@RequestParam(value="type", defaultValue="gif", required=false) String type,
                          @PathVariable("query") String query) {

        JSONObject output = new JSONObject();
        output.put("data", gifSearchService.searchGifs(query));
        return output.toString();
    }
}
