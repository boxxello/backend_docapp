package com.docapp.springjwt.controllers;


import com.docapp.springjwt.exceptions.ResourceNotFoundException;
import com.docapp.springjwt.models.Documento;
import com.docapp.springjwt.models.User;
import com.docapp.springjwt.repository.DocumentoRepository;
import com.docapp.springjwt.repository.UserRepository;
import com.docapp.springjwt.security.jwt.JwtUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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



    @PostMapping("/add_documento")
    public ResponseEntity<?> uploadFile(Documento documento, BindingResult result, @RequestParam("file") MultipartFile file,
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
        HashMap<String, Object> response = new HashMap<>();
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
            List<Documento> documenti = documentoRepository.findAllByStudente(user_to_retrieve_from)
                    .orElse(null);

            if (documenti == null) {

                response.put("documenti", Collections.emptyList());
                response.put("numero_documenti", 0);
            }
            else{
                response.put("documenti", documenti);
                response.put("numero_documenti", documenti.size());
            }


        } else {
            Documento documento = documentoRepository.findByIdAndStudente(id, user_to_retrieve_from)
                    .orElse(null);
            if (documento == null) {

                response.put("documento", null);
                response.put("message", "Documento non trovato.");
            }
            else{
                response.put("documento", documento);
            }


        }
        System.out.println(response);
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadDocumento(@AuthenticationPrincipal UserDetails userDetails,
                                                                @RequestParam(required = false) Long id,
                                                                @RequestParam(required = false) String username) throws IOException {

        // if the username is not specified in the request, return the current user's documents
        User user_to_retrieve_from;
        if (username == null) {
            user_to_retrieve_from = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } else {
            user_to_retrieve_from = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }

        // if the id is not specified in the request, return all the documents of the user
        if (id == null) {
            List<Documento> documenti = documentoRepository.findAllByStudente(user_to_retrieve_from)
                    .orElseThrow(() -> new ResourceNotFoundException("No document found for this user " + user_to_retrieve_from.getUsername() + "."));
            logger.info("Downloading all the documents of the user " + user_to_retrieve_from.getUsername() + ".");
            // create a temp file to hold the zip output stream
            //Tmp file appends a random string to the file name to avoid collisions
            File zipFile = File.createTempFile("docapp_zip_" + user_to_retrieve_from.getUsername(), ".zip");
            logger.info("Created temp file " + zipFile.getName() + " to hold the zip output stream.");
            // create a zip output stream
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));

            // loop through all the documents and add them to the zip file
            for (Documento documento : documenti) {
                File file = new File(documento.getPath());
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(file);
                IOUtils.copy(fileInputStream, zipOutputStream);
                fileInputStream.close();
                zipOutputStream.closeEntry();
            }

            // close the zip output stream
            zipOutputStream.close();

            // return the zip file as a FileSystemResource
            FileSystemResource fileSystemResource = new FileSystemResource(zipFile);
            logger.info(zipFile.getName());
            zipFile.deleteOnExit();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + zipFile.getName())
                    .body(fileSystemResource);

        } else {
            // return a single file
            Documento documento = documentoRepository.findByIdAndStudente(id, user_to_retrieve_from)
                    .orElseThrow(() -> new ResourceNotFoundException("Documento not found"));
            FileSystemResource fileSystemResource = new FileSystemResource(new File(documento.getPath()));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileSystemResource.getFilename())
                    .body(fileSystemResource);
        }
    }


}
