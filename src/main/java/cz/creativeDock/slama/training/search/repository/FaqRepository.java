package cz.creativeDock.slama.training.search.repository;

import cz.creativeDock.slama.training.search.model.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
}