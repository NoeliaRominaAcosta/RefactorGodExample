package Factory;

import Files.DocFile;
import Files.JsonFile;
import Files.TxtFile;
import Files.XmlFile;
import Processor.FileProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FilesProccesorFactory {
//Su única función es saber qué especialista (qué FileProcessor) es el adecuado para cada tipo de material (cada fileType).
    private static final Map<String, FileProcessor> fileProcessors = new HashMap<>();


    static {
         fileProcessors.put("txt", new TxtFile());
         fileProcessors.put("xml", new XmlFile());
         fileProcessors.put("json", new JsonFile());
         fileProcessors.put("doc", new DocFile());
    }
//Un método estático es aquel que no necesita que creemos un objeto de la clase para ser usado (no hacemos new FileProcessorFactory())

    public static FileProcessor getFileProcessor(String fileType) {
        return Optional.ofNullable(fileProcessors.get(fileType.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de archivo no soportado: " + fileType));

    }
}
