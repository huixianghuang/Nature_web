package nature.pojo;

import lombok.Data;
import nature.bean.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Data
public class Vertex implements VertexInterface {

    private long id;
    private StringBuilder informations;
    private String name;
    private List<Article> articles;
    private List<Edge> edges;
    private boolean visited;

    public Vertex() {
        informations = new StringBuilder("");
        articles = new ArrayList<>();
        edges = new ArrayList<>();
        visited = false;
    }

    public boolean connect(Vertex vertex, Article article) {

        //添加的顶点不是自己
        if (!this.equals(vertex)) {

            //判断该顶点是否有邻接点
            //如果没有，则直接添加
            //如果有，则判断所添加的点是否是已存在的邻接点
            if (edges.isEmpty()) {
                Edge edge = new Edge(vertex.id, article.getWeight());
                edges.add(edge);

                addArticle(this, vertex, article);

            } else {

                //添加的点是已存在的邻接点，则权重相加
                Iterator<Edge> iterator = edges.iterator();
                while (iterator.hasNext()) {
                    Edge edge = iterator.next();
                    if (vertex.getId() == edge.getVertexId()) {
                        double weight = edge.getWeight();
                        weight += article.getWeight();
                        edge.setWeight(weight);

                        addArticle(this, vertex, article);

                        return true;
                    }
                }

                //添加的点不是已存在的邻接点
                Edge edge = new Edge(vertex.getId(), article.getWeight());
                edges.add(edge);
                addArticle(this, vertex, article);
            }

            return true;
        }

        return false;
    }

    private void addArticle(Vertex src, Vertex dis, Article article) {
        if (src.getArticles().size() > 0) {
            addArticleDetail(src.getArticles(), article);
        }else {
            src.getArticles().add(article);
        }
        if (dis.getArticles().size() > 0) {
            addArticleDetail(dis.getArticles(), article);
        } else {
            dis.getArticles().add(article);
        }
    }

    private void addArticleDetail(List<Article> articles, Article article) {
        Iterator<Article> articleIterator = articles.iterator();
        while (articleIterator.hasNext()) {
            if (articleIterator.next().equals(article))
                return;
        }

        articles.add(article);
    }

    @Override
    public boolean isVisited(){
        return visited;
    }


    public boolean equals(Object o){
        boolean result;
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        } else {
            Vertex v = (Vertex) o;
            result = v.getName().equals(name);
        }
        return result;
    }

}
