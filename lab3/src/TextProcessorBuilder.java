package src;

import exception.FileReadException;
import exception.InvalidFileFormatException;

public class TextProcessorBuilder {
    public static TextConverter buildBilingualProcessor(String dictionaryPath)
            throws FileReadException, InvalidFileFormatException {
        EnglishRussianTranslator processor = new EnglishRussianTranslator();
        processor.initializeWordBase(dictionaryPath);
        return processor;
    }
}