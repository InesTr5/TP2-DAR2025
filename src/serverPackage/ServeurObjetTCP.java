package serverPackage;
import java.io.*;
import java.net.*;
import model.Operation;

public class ServeurObjetTCP {
	public static void main(String[] args) throws ClassNotFoundException {
		int port = 1234;
		try(ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println("Serveur en attente sur le port " + port + "...");
			while(true) {
				Socket socket = serverSocket.accept();
                System.out.println("Client connecté : " + socket.getInetAddress());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                Operation opp = (Operation) in.readObject();
                System.out.println("Objet reçu : " + opp.getOp1() + " " + opp.getOp() + " " + opp.getOp2());
                double resultat = calculer(opp);
                out.writeObject(resultat);
                System.out.println("Résultat envoyé : " + resultat);
                socket.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	private static double calculer(Operation opp) {
		double a = opp.getOp1();
		double b = opp.getOp2();
		switch (opp.getOp()) {
		case "+": return a + b;
        case "-": return a - b;
        case "*": return a * b;
        case "/": return b != 0 ? a / b : Double.NaN;
        default: return Double.NaN;
		}
	}

}
