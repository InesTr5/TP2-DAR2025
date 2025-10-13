package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args) {
        String hote = "localhost"; // ou IP du serveur
        int port = 1234;

        try (Socket socket = new Socket(hote, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connecté au serveur " + hote + " sur le port " + port);
            System.out.println("Entrez une opération (ex: 12 * 3) ou 'exit' pour quitter :");

            while (true) {
                System.out.print("Opération : ");
                String operation = sc.nextLine();

                if (operation.equalsIgnoreCase("exit")) break;

                if (!validerOperation(operation)) {
                    System.out.println("Erreur : format incorrect. Exemple : 3 + 4");
                    continue;
                }

                out.println(operation);
                String resultat = in.readLine();
                System.out.println("→ Réponse du serveur : " + resultat);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Vérifie que l'opération est valide
    private static boolean validerOperation(String op) {
        return op.matches("\\s*\\d+(\\s*[\\+\\-\\*/]\\s*\\d+)\\s*");
    }
}