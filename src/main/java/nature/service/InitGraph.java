package nature.service;

import nature.bean.Article;
import nature.bean.Author;
import nature.mapper.ArticleAuthorsMapper;
import nature.mapper.ArticleMapper;
import nature.mapper.AuthorMapper;
import nature.pojo.Graph;
import nature.pojo.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author Prograk
 * @Return Graph
 *
 * 初始化同一类别下的学术网络
 *
 * a. 结构为带权无向图，以作者信息为结点，共同参与的文章为连接作者的边，权重为文章的总指标
 * b. 图的存储结构为邻接表
 *
 * 逻辑
 * a. 先通过articleMapper获取指定类别下的所有文章并保存在ArrayList<Article> articles
 * b. 通过遍历获取articles的元素article，查询出该文章article下的所有作者，并保存在ArrayList<Author> authors中
 * c. 然后将authors中的元素（作者结点） 加入图中
 * d. 返回构建好的学术网络Graph
 */

@Component
public class InitGraph {
    @Autowired
    AuthorMapper authorMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleAuthorsMapper articleAuthorsMapper;

    public Graph init() {

//        ArrayList<Article> articles = articleMapper.findByIndexId(58);
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(articleMapper.findById(55760));
        articles.add(articleMapper.findById(55862));
        articles.add(articleMapper.findById(56379));

        if (articles == null) {
            System.err.println("学科类别有误");
            return null;
        }

        Graph graph = new Graph();
        ArrayList<Vertex> vertices = new ArrayList<>();
        HashMap<Long, Vertex> vertexHashMap = new HashMap<>();

        Iterator<Article> iterator = articles.iterator();
        while (iterator.hasNext()) {
            Article article = iterator.next();
            initWeight(article);

            ArrayList<Author> authors = authorMapper.findByArticleId(article.getId());

            if (authors != null && authors.size() > 0) {

                //把作者结点加入图中
                System.err.println("-----开始把作者结点加入图中-----");

                ArrayList<Vertex> verticesTemp = new ArrayList<>();

                //先将每个结点初始化,然后再建立结点之间的边连接
                Iterator<Author> authorIterator = authors.iterator();
                while (authorIterator.hasNext()) {
                    Author a = authorIterator.next();

                    Vertex vertexTemp = new Vertex();
                    vertexTemp.setId(a.getId());
                    vertexTemp.setName(a.getName());
                    vertexTemp.getInformations().append(a.getInformation());

                    verticesTemp.add(vertexTemp);
                }

                //建立结点之间的边连接
                Iterator<Vertex> vertexTempIterator = verticesTemp.iterator();
                while (vertexTempIterator.hasNext()) {
                    Vertex v = vertexTempIterator.next();

                    //判断作者结点是否已在构建好的图中
                    Iterator<Vertex> vertexIterator = vertices.iterator();
                    boolean connected = false;
                    while (vertexIterator.hasNext()) {
                        Vertex v1 = vertexIterator.next();

                        //结点存在在已构建好的图中，需要将这两个结点合并成一个结点
                        if (v1.equals(v)) {
//                            v1.getInformations().append(v.getInformations().toString());

                            connectOthers(v1, verticesTemp, article, vertices, vertexHashMap);

                            connected = true;
                            break;//结点存在在已构建好的图中，且和其他结点建立了边，所以退出循环
                        }

                    }

                    //结点不在构建好的图中，则直接和其他结点建立连接,并在图中加入该结点
                    if (!connected) {
                        connectOthers(v, verticesTemp, article, vertices, vertexHashMap);
                        vertices.add(v);
                        vertexHashMap.put(v.getId(), v);
                    }


                }

                System.err.println("-----作者结点已加入图中-----");

            }

        }

        //图建立完成
        graph.setVertices(vertices);

        return graph;

    }

    private void initWeight(Article article) {

        double weight = (article.getWeb_of_Science() +
                article.getCrossRef() +
                article.getScopus()) * 0.7 +
                article.getAltmetric() * 0.3;

        article.setWeight(weight);

    }

    private void connectOthers(Vertex v, ArrayList<Vertex> verticesTemp, Article article, ArrayList<Vertex> vertices, HashMap<Long, Vertex> vertexHashMap) {

        System.err.println("和其他结点建立连接中....");

        Iterator<Vertex> verticesTempIterator = verticesTemp.iterator();
        while (verticesTempIterator.hasNext()) {

            // v 和该论文下的其他结点建立边连接
            Vertex v1 = verticesTempIterator.next();
            if (!v.equals(v1)) {
                //先判断该其他结点是否在已构建好的图中
                //如果在，则合并之后再连接，如果不在，则直接连接
                Iterator<Vertex> vertexsIterator = vertices.iterator();
                boolean flag = false;
                while (vertexsIterator.hasNext()) {
                    Vertex v2 = vertexsIterator.next();
                    if (v2.equals(v1)) {
                        //如果在，则合并之后再连接
//                        v2.getInformations().append(v1.getInformations().toString());

                        v.connect(v2, article);
                        flag = true;
                        break;
                    }
                }

                //如果不在，则直接连接
                if (!flag) {
                    v.connect(v1, article);
                }
            }
        }
    }

}
