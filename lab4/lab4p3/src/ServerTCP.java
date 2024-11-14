import java.net.*;
import java.io.*;

class ServerTCP {
    static int countclients = 0;

    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            sock = new ServerSocket(1024);
            while (true) {
                Socket client = sock.accept();
                countclients++;
                System.out.println("Кліент " + countclients + " падключаны");

                is = client.getInputStream();
                os = client.getOutputStream();

                byte[] bytes = new byte[1024];
                is.read(bytes);
                String str = new String(bytes, "UTF-8");
                String[] numbers = str.trim().split(" ");

                int count = 0;
                for (String num : numbers) {
                    try {
                        if (Integer.parseInt(num) % 3 == 0) {
                            count++;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                os.write(Integer.toString(count).getBytes());
                System.out.println("Кліенту адпраўлена: " + count);
            }
        } catch (Exception e) {
            System.out.println("Памылка: " + e.toString());
        } finally {
            if (is != null) is.close(); // закрыццё ўваходнага патоку
            if (os != null) os.close(); // закрыццё выхаднога патоку
            if (sock != null) sock.close(); // закрыццё сокета
            System.out.println("Кліент " + countclients + " адключаны");
        }
    }
}
