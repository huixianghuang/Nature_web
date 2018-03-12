package nature.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAuthors {

    private long authorId;
    private String authorName;
    private String authorInformation;
    private Long articleId;
    private int indexId;
    private String url;
    private String title;
    private java.sql.Date recevied;
    private java.sql.Date accepted;
    private java.sql.Date published_online;
    private int web_of_Science;
    private int crossRef;
    private int scopus;
    private int altmetric;
}
