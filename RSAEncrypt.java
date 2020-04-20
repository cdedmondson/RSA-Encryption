/*
    RSAEncrypt: Takes in three files plaintext, destination and keys then encrypts.
 */

import java.io.*;
import java.math.BigInteger;

public class RSAEncrypt {

    private static final FileHandler fileHandler = new FileHandler();
    private static final ArgumentHandler argumentHandler = new ArgumentHandler();
    private static final Keys keys = new Keys();

    public static void main(String[] args) throws IOException {
        argumentHandler.checkNumberOfArguments(args);   // Make sure the number of arguments supplied by user is correct
        fileHandler.checkIfFilePathSExists(args);
        keys.getKeys(fileHandler.getKeyFilePath());
        fileHandler.getText(argumentHandler.getSourceFilePath());
        BigInteger cipherText = encrypt();
        fileHandler.writeToFile(argumentHandler.getDestinationFilePath(), cipherText);
    }

    /*
        Method: encrypt
        Purpose:  Encrypt text with public key and modulus.
     */
    private static BigInteger encrypt() {
        BigInteger modulus = new BigInteger(keys.getModulus());
        BigInteger publicKey = new BigInteger(keys.getPublicKey());
        BigInteger message = new BigInteger(fileHandler.getText());
        return message.modPow(publicKey, modulus);
    }
}
