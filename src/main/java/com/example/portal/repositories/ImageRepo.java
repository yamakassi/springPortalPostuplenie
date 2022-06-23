package com.example.portal.repositories;

import com.example.portal.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

    @Query("select i.name from Image i where i.user.id =:start")
    List<String> findNamesByUserId(@Param("start") Long start);
}