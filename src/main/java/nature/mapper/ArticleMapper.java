package nature.mapper;

import nature.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface ArticleMapper {

    @Select("select * from article where id = #{id}")
    Article findById(int id);

}
