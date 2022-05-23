package com.example.chucknorristestapi.chucknorris;

import com.example.chucknorristestapi.shared.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController()
@CrossOrigin
@RequestMapping("/api/chuck")
public class ChuckController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseObject getCategories() {
        ResponseObject res = new ResponseObject();

        String url = "https://api.chucknorris.io/jokes/categories";

        try {
            res.obj = restTemplate.getForObject(url, Object[].class);
        } catch(Exception ex) {
            res.success = false;
            res.message = ex.getMessage();
            res.httpStatus = 500;
            return res;
        }

        res.httpStatus = 200;
        res.message = "Success";
        res.success = true;

        return res;
    }

    @GetMapping("/{category}")
    public ResponseObject getRandomJokeByCategory(@PathVariable String category) {
        ResponseObject res = new ResponseObject();

        String url = "https://api.chucknorris.io/jokes/random?category=" + category;

        try {
            res.obj = restTemplate.getForObject(url, Object.class);
        } catch(Exception ex) {
            res.success = false;
            res.message = ex.getMessage();
            res.httpStatus = 500;
            return res;
        }

        res.httpStatus = 200;
        res.message = "Success";
        res.success = true;

        return res;
    }
}
