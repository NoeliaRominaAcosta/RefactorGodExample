package Files;

import Processor.FileProcessor;

import java.io.File;

public class JsonFile implements FileProcessor {

    @Override
    public String processFile(File fileType) {
        System.out.println("Procesando archivo JSON: " + fileType);
        // Lógica para procesar un archivo JSON
        return "Contenido del JSON";
    }
}
