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
    public static String generate_random_filename(String  originalFileName) throws Exception {
        // retrieve the name of the file



// generate a random filename based on the original file name
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + extension;

// create a Path object with the new filename

      return newFileName;
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
        System.out.println("path: " + path);
        System.out.println("path: " + filePath.toString());
        //get the absolute path of the file after saving it
        Files.write(filePath, file.getBytes());


        return filePath.toAbsolutePath().toString();

    }
}
