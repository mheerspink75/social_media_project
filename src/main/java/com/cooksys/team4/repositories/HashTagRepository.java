package com.cooksys.team4.repositories;


import com.cooksys.team4.entities.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HashTagRepository extends JpaRepository<Hashtag, Long> {

    Hashtag findByLabelIgnoreCase(String label);
}
