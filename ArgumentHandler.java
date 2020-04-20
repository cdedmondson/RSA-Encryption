/*
    ArgumentHandler: Validates and stores arguments passed by command line
 */
public class ArgumentHandler {

    private String sourceFilePath;
    private String destinationFilePath;
    private String keyFilePath;

    /*
        Method: checkArgument
        Purpose: If number of arguments is exactly one continue else display error message and terminate program.
    */
    public void checkArgument(String[] args) {
        if (args.length == 1) { // If argument supplied by user is exactly one
            this.keyFilePath = args[0];   // Set file path
        } else {
            System.out.println("Please supply one argument, that is the absolute path of file");
            System.exit(0); // Terminate program
        }

    }

    /*
        Method: checkNumberOfArguments
        Purpose: Check number of arguments supplied by user.  If number of arguments
                 is exactly 3 continue else display error message and terminate program.
    */
    public void checkNumberOfArguments(String[] args) {
        if (args.length == 3) { // If argument supplied by user is exactly one
            this.sourceFilePath = args[0];
            this.destinationFilePath = args[1];
            this.keyFilePath = args[2];
        } else {
            System.out.println("[!] Please supply 3 arguments separated by a space arg1 <source_file_path> arg2 <destination_file_path arg3 <key_file_path>");
            System.out.println("[+] Example: java RSAEncrypt /root/source_file /root/destination_file /root/key_file");
            System.exit(0); // Terminate program
        }

    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getDestinationFilePath() {
        return destinationFilePath;
    }

    public String getKeyFilePath() {
        return keyFilePath;
    }

}
