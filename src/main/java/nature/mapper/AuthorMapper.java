package nature.mapper;

import nature.bean.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Mapper
@Service
public interface AuthorMapper {

    @Select("select * from author where id = #{id}")
    Author findById(long id);

    @Select("select * from author where id >= #{start} and id <= #{end}")
    ArrayList<Author> findByIdRange(@Param("start") int start, @Param("end") int end);

    @Select("SELECT * FROM author WHERE ID IN (SELECT author_id from author_article WHERE article_id = #{id})")
    ArrayList<Author> findByArticleId(long id);
}
