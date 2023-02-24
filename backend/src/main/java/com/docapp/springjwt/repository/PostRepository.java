package com.docapp.springjwt.repository;


import com.docapp.springjwt.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**

     Returns a list of posts that match the given title.
     @param titolo the title to search for
     @return a list of posts that match the given title
     */
    List<Post> findAllByTitolo(String titolo);
}
