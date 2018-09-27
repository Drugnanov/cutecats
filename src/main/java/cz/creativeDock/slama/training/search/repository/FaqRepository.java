package cz.creativeDock.slama.training.search.repository;

import cz.creativeDock.slama.training.search.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

    //ToDo not so nice - is there default way how to do it?
    @Query("SELECT DISTINCT f.category FROM Faq f")
    List<String> findDistinctCategory();

    List<Faq> findByCategory(String category);

    boolean existsByCategory(String category);

    @Query("SELECT DISTINCT f.category FROM Faq f " +
            "WHERE f.category LIKE %:searchString% " +
            "or f.question LIKE %:searchString% " +
            "or f.answer LIKE %:searchString%")
    List<String> filterTopicsByText(@Param("searchString") String searchString);
}