package clientPackage;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import model.Operation;

public class ClientObjetTCP {
	public static void main(String[] args) throws ClassNotFoundException {
		String hote = "localhost";
        int port = 1234;
        try(Socket socket = new Socket(hote, port)){
        	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            System.out.println("Connecté au serveur " + hote + " sur le port " + port);
            System.out.print("Entrez le premier nombre : ");
            double a = sc.nextDouble();
            System.out.print("Entrez l’opérateur (+, -, *, /) : ");
            String op = sc.next();
            System.out.print("Entrez le deuxième nombre : ");
            double b = sc.nextDouble();
            Operation opp = new Operation(a , op , b);
            out.writeObject(opp);
            double resultat = (double) in.readObject();
            System.out.println("→ Résultat reçu : " + resultat);
        }catch(IOException e) {
        	e.printStackTrace();
        }
	}

}
