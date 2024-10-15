import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(12345)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String receivedData = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Атрымана: " + receivedData);

                StringTokenizer tokenizer = new StringTokenizer(receivedData);
                double x = Double.parseDouble(tokenizer.nextToken());
                double y = Double.parseDouble(tokenizer.nextToken());
                double z = Double.parseDouble(tokenizer.nextToken());

                double result = calculateFunction(x, y, z);

                String response = String.valueOf(result);
                byte[] sendBuffer = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(sendPacket);

                saveToFile(x, y, z, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double calculateFunction(double x, double y, double z) {
        double numerator = 1 + Math.pow(Math.sin(x + y), 2) * Math.pow(x, Math.abs(y));
        double denominator = Math.abs(Math.exp(x) - (2 * y / (1 + Math.pow(x, 2) * Math.pow(y, 3))));
        double arctgPart = Math.cos(Math.atan(1 / z));
        return (numerator / denominator) + Math.pow(arctgPart, 2);
    }

    public static void saveToFile(double x, double y, double z, double result) {
        try (FileWriter writer = new FileWriter("results.txt", true)) {
            writer.write("x: " + x + ", y: " + y + ", z: " + z + " -> Вынік: " + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
