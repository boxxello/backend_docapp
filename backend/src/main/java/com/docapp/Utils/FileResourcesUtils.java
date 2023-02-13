package com.docapp.Utils;


import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class FileResourcesUtils {
    static java.util.logging.Logger logger = Logger.getLogger(FileResourcesUtils.class.getName());


    public synchronized static void saveProperties(Properties props, String realPath) {
        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        String path=Objects.requireNonNull(classLoader.getResource(realPath)).getPath();
        logger.info(path);
        try {
            logger.info("Saving properties");
            props.store(new  FileOutputStream(path), "Email properties");
            logger.info("Properties saved");
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("Properties not saved");
        }
    }

    public InputStream getFileFromResourceAsStream(String fileName) throws PropsNotFoundException {

        ClassLoader classLoader = getClass().getClassLoader();
        logger.info(Objects.requireNonNull(classLoader.getResource(fileName)).getPath());
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new PropsNotFoundException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {


            return new File(resource.toURI());
        }

    }

    private static void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printFile(File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}