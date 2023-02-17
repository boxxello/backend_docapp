package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.InterazionePost;
import com.docapp.springjwt.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterazionePostRepository  extends JpaRepository<InterazionePost, Long> {

    List<InterazionePost> findAllByPostId(Long postId);
    List<InterazionePost> findAllByPost(Post post);


}
