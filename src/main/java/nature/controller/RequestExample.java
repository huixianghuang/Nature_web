package nature.controller;

import nature.bean.Article;
import nature.bean.Author;
import nature.mapper.ArticleMapper;
import nature.mapper.AuthorMapper;
import nature.pojo.Graph;
import nature.service.InitGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/test")
public class RequestExample {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private InitGraph initGraph;

    @RequestMapping(value = "hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "article")
    public Article article(@RequestParam("article") int articleId) {
        return articleMapper.findById(articleId);
    }

    @RequestMapping(value = "authors")
    public ArrayList<Author> authors(@RequestParam("start") int start, @RequestParam("end") int end) {
        return authorMapper.findByIdRange(start, end);
    }

    @RequestMapping(value = "graph")
    public Graph graph(){
        return initGraph.init();
    }
}
