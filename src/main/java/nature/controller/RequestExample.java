package nature.controller;

import nature.bean.Article;
import nature.bean.Author;
import nature.mapper.ArticleMapper;
import nature.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/nature")
public class RequestExample {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private AuthorMapper authorMapper;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello World";
    }

    @RequestMapping(value = "article")
    public Article article(){
        return articleMapper.findById(329);
    }

    @RequestMapping(value = "authors")
    public ArrayList<Author> authors(){
        return authorMapper.findByIdRange(2600, 2605);
    }
}
