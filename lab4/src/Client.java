import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Увядзіце 10 лікаў ад 1 да 100:");
            StringBuilder userNumbers = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                int number = scanner.nextInt();
                userNumbers.append(number).append(" ");
            }

            out.println(userNumbers.toString().trim());

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
