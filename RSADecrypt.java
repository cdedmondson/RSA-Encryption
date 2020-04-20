/*
    RSADecrypt: Takes in three files encrypted, destination and keys then decrypts.
 */

import java.io.*;
import java.math.BigInteger;

public class RSADecrypt {

    private static final FileHandler fileHandler = new FileHandler();
    private static final ArgumentHandler argumentHandler = new ArgumentHandler();
    private static final Keys keys = new Keys();

    public static void main(String[] args) throws IOException {
        argumentHandler.checkNumberOfArguments(args);   // Make sure the number of arguments supplied by user is correct
        fileHandler.checkIfFilePathSExists(args);
        keys.getKeys(fileHandler.getKeyFilePath());
        fileHandler.getText(argumentHandler.getSourceFilePath());
        BigInteger decryptedFile = decrypt();
        fileHandler.writeToFile(argumentHandler.getDestinationFilePath(), decryptedFile);
    }

    /*
        Method: decrypt
        Purpose: Decrypt text with private key and modulus.
     */
    private static BigInteger decrypt() {
        BigInteger modulus = new BigInteger(keys.getModulus());
        BigInteger message = new BigInteger(fileHandler.getText());
        BigInteger privateKey = new BigInteger(keys.getPrivateKey());
        return message.modPow(privateKey, modulus);
    }

}
