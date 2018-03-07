package nature.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAuthors {

    private ArrayList<Author> authors;
    private Article article;

}
