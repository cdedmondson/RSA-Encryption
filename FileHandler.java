/*
    FileHandler: Handles all writing and reading, to and from files.
 */

import java.io.*;
import java.math.BigInteger;

public class FileHandler {

    private File keyFilePath;
    private byte[] text;
    private final Keys keys = new Keys();

    /*
        Method: checkIfFilePathExists
        Purpose: Receive absolute file path from user and
                 return true if path exists - false otherwise.
    */
    public void checkIfFilePathSExists(String[] args) {
        File sourceFilePath = new File(args[0]);
        File destinationFilePath = new File(args[1]);
        this.keyFilePath = new File(args[2]);
        if (sourceFilePath.exists() & destinationFilePath.exists() & keyFilePath.exists())  // Check if files exist
            assert true;    // If they do exist do nothing
        else {   // Else file does not exist
            System.out.println("[!] The file path for one or more of your files does not exist. Please provide the absolute path for each file");
            System.exit(0); // Terminate program
        }

    }

    /*
        Method: checkIfFilePathExists
        Purpose: Receive absolute file path from user and
                 return true if path exists - false otherwise.
    */
    public void checkIfKeyFilePathExists(String[] arg) {
        this.keyFilePath = new File(arg[0]);
        if (keyFilePath.exists())  // Check if file exists
            assert true;    // If it does do nothing
        else {   // Else file does not exist
            System.out.println("The file path " + keyFilePath + " does not exist");
            System.exit(0); // Terminate program
        }
    }

    /*
        Method: writeToFile
        Purpose: Write byte stream to file
     */
    public void writeToFile(String filePath, BigInteger byteStream) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(byteStream.toByteArray());
        fileOutputStream.close();
    }

    /*
        Method: getText
        Purpose: Read text from file as byte array
     */
    public void getText(String sourceFilePath) throws IOException {
        this.text = keys.inputStreamByteArray(new FileInputStream(sourceFilePath));
    }

    public File getKeyFilePath() {
        return keyFilePath;
    }

    public byte[] getText() {
        return text;
    }

}
