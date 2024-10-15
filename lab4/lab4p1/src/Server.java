//Распрацаваць прыкладанне для вызначэння шчасліўчыка латарэі. На серверы
//захоўваюцца нумары білетаў. На кожным білеце ёсць 10 выпадковых лікаў ад
//1 да 100. На кліенцкай частцы ўводзяцца 10 лікаў ад 1 да 100, і сервер
//павінен вызначыць нумар білета, у якім ёсць больш за ўсё супадзенняў з уведзенымі лікамі.

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static final int TICKET_COUNT = 10;
    private static final int NUMBER_COUNT = 10;
    private static final int MAX_NUMBER = 100;

    private static List<int[]> tickets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        generateTickets();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запушчаны і чакае падключэння кліентаў...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Кліент падключаны.");
                new ClientHandler(clientSocket).start();
            }
        }
    }

    private static void generateTickets() {
        Random random = new Random();
        for (int i = 0; i < TICKET_COUNT; i++) {
            int[] ticket = new int[NUMBER_COUNT];
            for (int j = 0; j < NUMBER_COUNT; j++) {
                ticket[j] = random.nextInt(MAX_NUMBER) + 1;
            }
            tickets.add(ticket);
        }
    }

    private static int findBestMatchingTicket(int[] userNumbers) {
        int maxMatches = -1;
        int bestTicketIndex = -1;
        for (int i = 0; i < tickets.size(); i++) {
            int matches = countMatches(tickets.get(i), userNumbers);
            if (matches > maxMatches) {
                maxMatches = matches;
                bestTicketIndex = i;
            }
        }
        return bestTicketIndex;
    }

    private static int countMatches(int[] ticket, int[] userNumbers) {
        int matches = 0;
        for (int number : userNumbers) {
            for (int ticketNumber : ticket) {
                if (number == ticketNumber) {
                    matches++;
                    break;
                }
            }
        }
        return matches;
    }

    private static String formatTicketWithMatches(int[] ticket, int[] userNumbers) {
        StringBuilder sb = new StringBuilder();
        int matches = 0;
        sb.append("[");
        for (int i = 0; i < ticket.length; i++) {
            if (contains(userNumbers, ticket[i])) {
                sb.append("*").append(ticket[i]).append("*");
                matches++;
            } else {
                sb.append(ticket[i]);
            }
            if (i < ticket.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("] (Супадзенняў: ").append(matches).append(")");
        return sb.toString();
    }

    private static boolean contains(int[] arr, int number) {
        for (int num : arr) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    private static String getAllTicketsWithMatches(int[] userNumbers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tickets.size(); i++) {
            sb.append("Білет ").append(i + 1).append(": ").append(formatTicketWithMatches(tickets.get(i), userNumbers)).append("\n");
        }
        return sb.toString();
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String input = in.readLine();
                int[] userNumbers = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int bestTicketIndex = findBestMatchingTicket(userNumbers);
                out.println("Найбольшая колькасць супадзенняў у білеце нумар " + (bestTicketIndex + 1));

                out.println("Усе білеты:");
                out.println(getAllTicketsWithMatches(userNumbers));

                System.out.println("Вынік адпраўлены кліенту.");
            } catch (IOException e) {
                System.err.println("Памылка ў працы з кліентам: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Памылка пры закрыцці сокета: " + e.getMessage());
                }
            }
        }
    }
}
