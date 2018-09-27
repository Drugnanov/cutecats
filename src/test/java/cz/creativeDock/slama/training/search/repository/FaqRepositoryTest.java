package cz.creativeDock.slama.training.search.repository;

import cz.creativeDock.slama.training.search.SearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//ToDo repository Test
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        SearchApplication.class,
})
public class FaqRepositoryTest {

    @Autowired
    private FaqRepository faqRepository;

    @Test
    public void testFilterTopicsByText() {
        //
//        Assert.assertTrue("Result OK...", );
    }
}
