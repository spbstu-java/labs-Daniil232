package src;

import exception.FileReadException;
import exception.InvalidFileFormatException;

public interface TextConverter {
    void initializeWordBase(String filePath) throws FileReadException, InvalidFileFormatException;
    String convertText(String text);
    boolean hasEntry(String word);
}