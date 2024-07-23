package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Clientrcv implements Runnable{
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    public Clientrcv(Socket sc,BufferedReader rd,PrintWriter pwr){
        clientSocket = sc;
        in = rd;
        out = pwr;
    }
    public void run() {
        String msg;
        try {
          msg = in.readLine();
          while(msg!=null){
             System.out.println("Serveur : "+msg);
             msg = in.readLine();
          }
          System.out.println("Serveur déconecté");
          out.close();
          clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}