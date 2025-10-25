package Files;

import Processor.FileProcessor;

import java.io.File;

public class TxtFile implements FileProcessor {
    @Override
    public String processFile(File fileType) {
        System.out.println("Procesando archivo de texto: " + fileType);
        return "Contenido del TXT";
    }
}
