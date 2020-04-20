/*
    Keys: Stores and retrieves public, private and modulus from file
 */

import java.io.*;

public class Keys {

    private final byte[] modulus = new byte[128];   // Modulus (n) - is 1024-bits or 128-bytes
    private final byte[] publicKey = new byte[3];   // Public key (e) - is 3-bytes
    private final byte[] privateKey = new byte[128];// Private key (d) - is 1024-buts or 128-bytes

    /*
        Method: inputStreamByteArray
        Purpose: Read bytes from inputStream and write to OutputStream,
                 then convert OutputStream to byte array.
    */
    public byte[] inputStreamByteArray(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int buffer = inputStream.read();

        // While end of file hasn't been reached
        while (buffer != -1) {
            byteArrayOutputStream.write(buffer);
            buffer = inputStream.read();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /*
        Method: getKeys
        Purpose: Read keys from keys.dat as a byte array
     */
    public void getKeys(File keyFile) throws IOException {
        byte[] byteArray = inputStreamByteArray(new FileInputStream(keyFile));
        int publicKeyIndex = 0;
        int privateKeyIndex = 0;

        for (int byteCount = 0; byteCount < 259; byteCount++) {
            if (byteCount <= 127) { // Modulus is 128 bytes
                modulus[byteCount] = byteArray[byteCount];
            } else if (byteCount <= 130) {  // Public key is 3 bytes
                publicKey[publicKeyIndex] = byteArray[byteCount];
                publicKeyIndex++;
            } else {    // Private key is teh last 128 bytes
                privateKey[privateKeyIndex] = byteArray[byteCount];
                privateKeyIndex++;
            }
        }
    }

    public byte[] getModulus() {
        return modulus;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

}
