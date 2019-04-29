package com.searchapp.controller;

import com.searchapp.core.SearchException;
import com.searchapp.service.GifSearchServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SearchController {

    private final GifSearchServiceImpl gifSearchService;

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    public SearchController(GifSearchServiceImpl gifSearchService) {
        this.gifSearchService = gifSearchService;
    }

    @RequestMapping(path = "/search/{query}", method = RequestMethod.GET)
    public String search(@RequestParam(value = "type", defaultValue = "gif", required = false) String type,
                         @PathVariable("query") String query, HttpServletResponse response) {

        JSONObject output = new JSONObject();
        try {
            output.put("data", gifSearchService.search(query));
        } catch (SearchException e) {
            setErrorResp(e.getHttpsStatus(), e.getMessage(), response, output);
            logger.error("/search API error", e);
        } catch (Throwable t) {
            setErrorResp(HttpStatus.INTERNAL_SERVER_ERROR, t.getMessage(), response, output);
            logger.error("/search API error", t);
        }
        return output.toString();
    }


    private void setErrorResp(HttpStatus httpsStatus, String message, HttpServletResponse response, JSONObject output) {
        output.put("data", "[]");
        JSONObject error = new JSONObject();
        error.put("message", message);
        output.put("error", error);
        if (httpsStatus != null) {
            response.setStatus(httpsStatus.value());
        }
    }
}
