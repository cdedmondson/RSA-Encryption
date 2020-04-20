/*

    RSAKeyGen:  Generates three keys (public, private and modulus)

    1. Choose two distinct prime numbers p and q
        1a. Generate large prime for p - primeNumber1
        1b. Generate another larger prime greater than p for q - primeNumber2 = primeNumber1.nextProbablePrime
    2. Compute n = p*q
        2a. modulus = (primeNumber1 * primeNumber2)
    3. Compute Euler's totient value
    4. Compute public key e (given)
    5. Compute private key d = e - 1 (mod (n))

 */

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAKeyGen {

    private static BigInteger primeNumber1;
    private static BigInteger primeNumber2;
    private static final BigInteger publicKey = BigInteger.valueOf(65537);    // e (public key) - will remain constant 2^16+1 = 65537
    private static final SecureRandom randomNumber = new SecureRandom();  // Create a secure random number
    private static final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    private static final ArgumentHandler argumentHandler = new ArgumentHandler();
    private static final FileHandler fileHandler = new FileHandler();


    public static void main(String[] args) throws IOException {
        BigInteger privateKey;
        BigInteger modulus;
        argumentHandler.checkArgument(args);// Make sure the number of arguments supplied by user is correct
        fileHandler.checkIfKeyFilePathExists(args);    // Make sure file path exists before continuing
        do {
            modulus = generatePrimes();   // Generate primes p and q
            BigInteger totientValue = computeEulersTotientValue();    // totientValue = (p - 1) * (q - 1)
            privateKey = computePrivateKey(totientValue);  // d ≡ e^−1 (mod λ(n))
        } while (streamSize(modulus, privateKey) != 259);

        writeKeysToFile(argumentHandler.getKeyFilePath());  // Self explanatory
    }

    /*
        Method: generatePrimes
        Purpose: To generate two distinct prime numbers p and q
                 (at random) that differ in length.
    */
    private static BigInteger generatePrimes() {
        int bitLength = 512;    // 2^512 bit length
        // p - represents first distinct prime number (probablePrime generates prime of length 512 bits)
        primeNumber1 = BigInteger.probablePrime(bitLength, randomNumber);
        // q - represents second distinct prime number (must be bigger than primeNumber1)
        primeNumber2 = primeNumber1.nextProbablePrime();
        return primeNumber1.multiply(primeNumber2);  // n = p * q
    }

    /*
        Method: computeEulersTotientValue
        Purpose: Computer Euler's totient value by subtracting
                 one from primes p and q.
     */
    private static BigInteger computeEulersTotientValue() {
        BigInteger one = BigInteger.ONE;    // -1
        return primeNumber1.subtract(one).multiply(primeNumber2.subtract(one)); // totientValue = (p - 1) * (q - 1)
    }

    /*
        Method: computePrivateKey
        Purpose: Generate private key.
     */
    private static BigInteger computePrivateKey(BigInteger totientValue) {
        return publicKey.modInverse(totientValue);    // d (private key) e^−1 (mod λ(n))
    }

    /*
        Method: streamSize
        Purpose: To convert keys to byte array buffer before writing to file
     */
    private static int streamSize(BigInteger modulus, BigInteger privateKey) throws IOException {
        byteStream.reset();
        byteStream.write(modulus.toByteArray());
        byteStream.write(publicKey.toByteArray());
        byteStream.write(privateKey.toByteArray());
        return byteStream.size();
    }

    /*
        Method: writeKeysToFile
        Purpose: Write n, e,and d (i.e. modulus, public key and private key)
                 into a binary file whose path was specified by user.
     */
    private static void writeKeysToFile(String filePath) throws IOException {
        OutputStream writeKeysToFile = new FileOutputStream(filePath);
        writeKeysToFile.write(byteStream.toByteArray());
        writeKeysToFile.close();
    }

}