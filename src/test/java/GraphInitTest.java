import nature.Applications;
import nature.service.InitGraph;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Applications.class)
@RunWith(SpringRunner.class)
public class GraphInitTest {

    @Autowired
    InitGraph initGraph;
    @Test
    public void initTest() {

        System.out.println(initGraph.init());

    }
}
