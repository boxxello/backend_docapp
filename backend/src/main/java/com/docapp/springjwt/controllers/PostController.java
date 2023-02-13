package com.docapp.springjwt.controllers;

import com.docapp.DAO.PostDAO;
import com.docapp.Utils.ConnPom;
import com.docapp.shared_docapp.models.Post;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/post")
public class PostController {
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public  ResponseEntity<JsonObject> allAccess() {
        PostDAO dao = new PostDAO(ConnPom.getDatasource());
        try {
            List<Post> posts = dao.doRetrieveAll();
            List<HashMap<String, ?>> list_of_hashmaps = new ArrayList<>();
            for (Post post : posts) {
                list_of_hashmaps.add(post.toHashMap());
            }

            JsonObject json = new JsonObject();
            Gson gson = new Gson();
            json.add("posts",  gson.toJsonTree(list_of_hashmaps));
            System.out.println(json);
            return ResponseEntity.ok(json);
        } catch (SQLException throwables) {
            return null;
        }

    }
}
