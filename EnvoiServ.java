package serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class EnvoiServ implements Runnable{
    String msg;
    BufferedReader in;
    PrintWriter out;
    Scanner sc;
    public EnvoiServ(BufferedReader bf,PrintWriter pwr,Scanner scan){
        in = bf;
        out = pwr;
        sc = scan;
    }
    public void run() {
        while(true){
            msg = sc.nextLine();
            out.println(msg);
            out.flush();
        }
    }
}