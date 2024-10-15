import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Увядзіце x: ");
            double x = scanner.nextDouble();
            System.out.print("Увядзіце y: ");
            double y = scanner.nextDouble();
            System.out.print("Увядзіце z: ");
            double z = scanner.nextDouble();

            String message = x + " " + y + " " + z;
            byte[] buffer = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 12345);
            socket.send(packet);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Вынік: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
