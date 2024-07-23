package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import fnct.*;
public class Client{
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner sc=new Scanner(System.in);
    private String ip;
    private String[] ip_tab;
    private Fonction fonct = new Fonction();
    public void se_connecter()throws Exception{
        try {
            ip = fonct.lire_file("fichier_de_configuration");
            ip_tab = ip.split(" ");
            int port = Integer.parseInt(ip_tab[6]);
            clientSocket = new Socket(ip_tab[2],port);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread envoyer = new Thread(new Clientsend(out,sc));
            envoyer.start();
            Thread recevoir = new Thread(new Clientrcv(clientSocket,in,out));
            recevoir.start();
        }catch (IOException e) {
            throw e;
        }
    }    
}