package nature.controller;


import nature.bean.ArticleAuthors;
import nature.mapper.ArticleAuthorsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/nature")
public class RequestController {

    @Autowired
    ArticleAuthorsMapper articleAuthorsMapper;

    @RequestMapping
    ArrayList<ArticleAuthors> searchByIndexId(@RequestParam("index") Long indexId){
        return articleAuthorsMapper.findByIndexId(indexId);
    }




}
