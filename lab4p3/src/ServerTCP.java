import java.net.*;
import java.io.*;

class ServerTCP {
    static int countclients = 0; // лічыльнік падключаных кліентаў

    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            sock = new ServerSocket(1024); // сервер працуе на порце 1024
            while (true) {
                Socket client = sock.accept(); // чакаем падключэння кліента
                countclients++;
                System.out.println("Кліент " + countclients + " падключаны");

                is = client.getInputStream(); // атрымліваем уваходны паток ад кліента
                os = client.getOutputStream(); // атрымліваем выхадны паток для адпраўкі дадзеных

                byte[] bytes = new byte[1024];
                is.read(bytes); // чытаем дадзеныя, адпраўленыя кліентам
                String str = new String(bytes, "UTF-8");
                String[] numbers = str.trim().split(" "); // разбіваем радок на асобныя лікі

                int count = 0; // лічыльнік лікаў, кратных тром
                for (String num : numbers) {
                    try {
                        if (Integer.parseInt(num) % 3 == 0) {
                            count++;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                // Адпраўляем колькасць лікаў, кратных тром, назад кліенту
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
