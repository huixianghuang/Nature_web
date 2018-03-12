package nature.mapper;

import nature.bean.ArticleAuthors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Mapper
@Service
public interface ArticleAuthorsMapper {

    @Select("SELECT " +
            "author.ID AS authorId, " +
            "author.Name AS authorName, " +
            "author.Information AS authorInformation, " +
            "article.ID AS articleId, " +
            "article.Index_id AS indexId, " +
            "article.Title AS title, " +
            "article.URL AS url, " +
            "article.Received AS recevied, " +
            "article.Accepted AS accepted, " +
            "article.Published_online AS published_online, " +
            "article.Web_of_Science AS web_of_Science, " +
            "article.CrossRef AS crossRef, " +
            "article.Scopus AS scopus, " +
            "article.Altmetric AS altmetric " +
            "FROM author, article, author_article " +
            "WHERE article.Index_id = #{indexId} " +
            "AND article.ID = author_article.article_id " +
            "AND author.ID = author_article.author_id  " +
            "ORDER BY articleId limit 17"
            )
    ArrayList<ArticleAuthors> findByIndexId(Long indexId);
}
