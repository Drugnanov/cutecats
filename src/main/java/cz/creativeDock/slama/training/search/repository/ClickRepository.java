package cz.creativeDock.slama.training.search.repository;

import cz.creativeDock.slama.training.search.model.Click;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClickRepository extends JpaRepository<Click, Long> {

    Optional<Click> findByCategory(String category);
}