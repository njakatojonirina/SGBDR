package serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import fnct.*;
public class Serveur{
    private ServerSocket serveurSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc=new Scanner(System.in);
    private Fonction fonct = new Fonction();
    private String port;
    private String[] tab;
    public void Activation(){
        try{
            port = fonct.lire_file("fichier_de_configuration");
            tab = port.split(" ");
            int po = Integer.parseInt(tab[6]);
            serveurSocket = new ServerSocket(po);
            clientSocket = serveurSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
            Thread envoi= new Thread(new EnvoiServ(in,out,sc));
            envoi.start();
            Thread recevoir = new Thread(new ReceivServ(in,out,sc,serveurSocket,clientSocket));
            recevoir.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}