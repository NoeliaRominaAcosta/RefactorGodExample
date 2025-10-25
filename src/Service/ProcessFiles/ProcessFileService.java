package Service.ProcessFiles;

import Factory.FilesProccesorFactory;
import Processor.FileProcessor;

import java.io.File;
import java.util.Optional;

public class ProcessFileService {

    public String processFile(String filePath) {
        File file = new File(filePath);
// Obtener la extensión del archivo
        String fileType = getFileType(file.getName()).orElseThrow(() ->
            new IllegalArgumentException("Unsupported file type")
        );
        //Usar la fábrica para obtener el procesador adecuado comparando con la extension que obtuvo
        FileProcessor fileProcessor = FilesProccesorFactory.getFileProcessor(fileType);

        return fileProcessor.processFile(file);
    }
//Extensión del archivo
    private Optional<String> getFileType(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
    }

}
