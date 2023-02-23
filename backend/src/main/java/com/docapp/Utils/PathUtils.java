package com.docapp.Utils;

import com.docapp.springjwt.models.Documento;
import org.apache.commons.lang3.mutable.Mutable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public class PathUtils {
    public static String generate_random_filename(String originalFileName) throws Exception {
        // retrieve the name of the file


        // generate a random filename based on the original file name
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        // create a Path object with the new filename

        return UUID.randomUUID() + extension;
    }

    public static String save_file_to_path(MultipartFile file, String path) throws IOException {
        // save the file to the path
        File uploadDir = new File(Documento.basepath);
        if (!uploadDir.exists()) {
            if (uploadDir.mkdirs())
                System.out.println("Cartella creata");
        } else {
            System.out.println("Cartella già esistente");
        }
        //write the file to the path

        Path filePath = uploadDir.toPath().resolve(path);

        //get the absolute path of the file after saving it
        Files.write(filePath, file.getBytes());
        System.out.println("path: " + filePath);


        return filePath.toAbsolutePath().toString();

    }
}
