package nature.mapper;

import nature.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Mapper
@Service
public interface ArticleMapper {

    @Select("select * from article where id = #{id}")
    Article findById(int id);

    @Select("SELECT * FROM article WHERE Index_id = #{indexId} ORDER BY article.ID")
    ArrayList<Article> findByIndexId(int indexId);



}
