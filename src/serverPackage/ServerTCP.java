package serverPackage;

import java.io.*;
import java.net.*;

public class ServerTCP {
    public static void main(String[] args) {
        int port = 1234;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur en attente sur le port " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connecté : " + socket.getInetAddress());

                // On gère le client dans une méthode séparée
                gererClient(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gererClient(Socket socket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String operation;
            while ((operation = in.readLine()) != null) {
                if (operation.equalsIgnoreCase("exit")) {
                    System.out.println("Client déconnecté.");
                    break;
                }

                System.out.println("Opération reçue : " + operation);
                String resultat = calculer(operation);
                out.println(resultat);
                System.out.println("Résultat envoyé : " + resultat);
            }

        } catch (IOException e) {
            System.out.println("Connexion interrompue avec le client.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String calculer(String operation) {
        try {
            String[] elements = operation.trim().split(" ");
            if (elements.length != 3) return "Erreur : format incorrect. Exemple : 5 * 3";

            double op1 = Double.parseDouble(elements[0]);
            String operateur = elements[1];
            double op2 = Double.parseDouble(elements[2]);
            double resultat;

            switch (operateur) {
                case "+": resultat = op1 + op2; break;
                case "-": resultat = op1 - op2; break;
                case "*": resultat = op1 * op2; break;
                case "/":
                    if (op2 == 0) return "Erreur : division par zéro !";
                    resultat = op1 / op2;
                    break;
                default: return "Erreur : opérateur non reconnu (" + operateur + ")";
            }
            return "Résultat = " + resultat;

        } catch (NumberFormatException e) {
            return "Erreur : opérandes non valides.";
        }
    }
}