package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Clientsend implements Runnable{
    PrintWriter out;
    Scanner sc;
    public Clientsend(PrintWriter pwr,Scanner scan){
        out = pwr;
        sc = scan;
    }
    public void run() {
        String msg;
        while(true){
          msg = sc.nextLine();
          out.println(msg);
          out.flush();
        }
    }
}