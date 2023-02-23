package com.docapp.springjwt.controllers;


import com.docapp.springjwt.exceptions.ResourceNotFoundException;
import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.User;
import com.docapp.springjwt.repository.DocumentoRepository;
import com.docapp.springjwt.repository.UserRepository;
import com.docapp.springjwt.security.jwt.JwtUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import static com.docapp.Utils.MD5Checksum.getMD5Checksum;
import static com.docapp.Utils.PathUtils.*;

@RestController
@RequestMapping("/api/documenti")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class DocumentoController {
    private static final Logger logger = LoggerFactory.getLogger(DocumentoController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentoRepository documentoRepository;

    @PostMapping("/upload")
    public ResponseEntity<?> addDocumento(Documento documento, BindingResult result, @RequestParam("file") MultipartFile file,
                                          @AuthenticationPrincipal UserDetails userDetails) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        User currentUser = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        try {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            if (filename.contains("..")) {
                return ResponseEntity.badRequest().body("Nome del file non valido.");
            }
            //get the extension of the file


            List<String> allowedFileTypes = Arrays.asList("pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt");
            if (!allowedFileTypes.contains(FilenameUtils.getExtension(filename).toLowerCase())) {
                return ResponseEntity.badRequest().body("File non valido. Assicurati che il file sia un documento PDF, Word, Excel o PowerPoint e non superi i 10 MB.");
            }
            // set the document name to the file name
            documento.setNome(filename);

            // set the file size
            documento.setDimensione(file.getSize());
            String path = generate_random_filename(filename);
            // save the file to the path


            String new_path = save_file_to_path(file, path);
            System.out.println("path: " + new_path);
            documento.setPath(new_path);
            // create hash from file
            String checksum = getMD5Checksum(documento.getPath());
            documento.setHash(checksum);
            //get size of file
            documento.setDimensione(file.getSize());
            documento.setStudente(currentUser);
            // save the document to the database
            documentoRepository.save(documento);

            return ResponseEntity.ok("Documento aggiunto con successo.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getDocumenti(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String username) {
        //if the username is not specified in the request, return the current user's documents
        User user_to_retrieve_from;
        if (username == null) {
            user_to_retrieve_from = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        } else {
            user_to_retrieve_from = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        }

        //if the id is not specified in the request, return all the documents of the user
        if (id == null) {
            List<Documento> documenti = documentoRepository.findAllByStudente(user_to_retrieve_from);
            return ResponseEntity.ok().body(documenti);
        } else {
            Documento documento = documentoRepository.findByIdAndStudente(id, user_to_retrieve_from)
                    .orElseThrow(() -> new ResourceNotFoundException("Documento not found"));
            return ResponseEntity.ok().body(documento);
        }

    }

    @GetMapping("/download")
    @ResponseBody
    public List<FileSystemResource> downloadDocumento(@AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestParam(required = false) Long id,
                                                      @RequestParam(required = false) String username) {
        //if the username is not specified in the request, return the current user's documents
        User user_to_retrieve_from;
        if (username == null) {
            user_to_retrieve_from = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        } else {
            user_to_retrieve_from = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        }

        //if the id is not specified in the request, return all the documents of the user
        if (id == null) {
            List<Documento> documenti = documentoRepository.findAllByStudente(user_to_retrieve_from);
            List<FileSystemResource> resources = new ArrayList<>();
            for (Documento documento : documenti) {
                resources.add(new FileSystemResource(new File(documento.getPath())));
            }
            return resources;
        } else {
            Documento documento = documentoRepository.findByIdAndStudente(id, user_to_retrieve_from)
                    .orElseThrow(() -> new ResourceNotFoundException("Documento not found"));
            return Collections.singletonList(new FileSystemResource(new File(documento.getPath())));
        }
    }


}
