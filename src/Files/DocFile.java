package Files;

import Processor.FileProcessor;

import java.io.File;

public class DocFile implements FileProcessor {
    @Override
    public String processFile(File filePath) {
        System.out.println("Procesando archivo DOC: " + filePath);
        // Lógica para procesar un archivo DOC
        return "Contenido del DOC";
    }
}
