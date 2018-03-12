import nature.Applications;
import nature.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;


@SpringBootTest(classes = Applications.class)
@RunWith(SpringRunner.class)
public class ControllerTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void indexTest() {


    }
}
