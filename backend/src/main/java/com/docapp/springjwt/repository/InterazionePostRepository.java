/**

 The InterazionePostRepository interface is a JpaRepository for the InterazionePost model class. It provides methods to
 interact with the database to retrieve and save InterazionePost entities.
 */
package com.docapp.springjwt.repository;

import com.docapp.springjwt.models.InterazionePost;
import com.docapp.springjwt.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterazionePostRepository  extends JpaRepository<InterazionePost, Long> {

    /**
     * Returns a List of InterazionePost entities that match the specified post ID.
     *
     * @param postId the ID of the post to find InterazionePost entities for
     * @return a List of InterazionePost entities
     */
    List<InterazionePost> findAllByPostId(Long postId);
    /**
     * Returns a List of InterazionePost entities that match the specified post.
     *
     * @param post the Post object to find InterazionePost entities for
     * @return a List of InterazionePost entities
     */
    List<InterazionePost> findAllByPost(Post post);


}
