package serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import fnct.*;
public class ReceivServ implements Runnable{
    String msg;
    String message;
    ServerSocket serveurSocket;
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    Scanner sc;
    String[] msgtab;
    String[] messagetab;
    Fonction fonct = new Fonction();
    String ms_send;
    String b = "0";
    public ReceivServ(BufferedReader bf,PrintWriter pwr,Scanner scan,ServerSocket serv,Socket client){
        in = bf;
        out = pwr;
        sc = scan;
        serveurSocket = serv;
        clientSocket = client;
    }
    public void run() {
        try {
            msg = in.readLine();
            //message = in.readLine();
            while(msg!=null){
                System.out.println("Client : "+msg);
                msgtab = msg.split(" ");
                if(msgtab.length < 3){
                    msgtab = new String[3];
                    for(int i = 0 ; i < msgtab.length ; i++){
                        msgtab[i] = ""+i;
                    }
                }
                if(msgtab[1].equals("database") == true){
                    ms_send = msgtab[2];
                    int test = fonct.test_existence_base(msgtab[2],"data");
                    if(test == 1){
                        fonct.write_dans_une_file(" default","base_en_cours","base");
                        fonct.write_dans_une_file(ms_send+",","data","base");
                        fonct.creer_une_fichier(ms_send+"_table","base");
                        out.println("success");
                        out.flush();
                    }else{
                        out.println("base exist");
                        out.flush();
                    } 
                }else if(msgtab[0].equals("connect") == true && msgtab[1].equals("with") == true){
                    if(fonct.choix_base(msgtab[2]) == 1){
                        fonct.write_dans_une_file(" "+msgtab[2],"base_en_cours","base");
                        out.println("connected");
                        out.flush();
                    }else{
                        out.println("base not exist");
                        out.flush();
                    }
                }else if(msgtab[1].equals("table") == true){
                    String tb = fonct.lire_file("base_en_cours");
                    String[] table_tab = tb.split(" ");
                    ms_send = table_tab[(table_tab.length-1)];
                    int indice = fonct.paranthese(table_tab);
                    String[] attribut = fonct.get_attribut(msg);
                    String[] type = fonct.get_type(msg); 
                    int test = fonct.comparaison(type);
                    int test2 = fonct.test_existence_table(msgtab[2],ms_send+"_table"); 
                    //int test3 = fonct.test_espace(msgtab);
                    if(table_tab[table_tab.length-1].equals("default") != true ) {
                        if(test == 1 && test2 == 1){
                            String txt = " "+msgtab[2]+" ( "+attribut[0]+" "+type[0];
                            for(int c = 1 ; c < attribut.length ; c++){
                                txt += " , "+attribut[c]+" "+type[c];
                            }
                            txt = txt + " )"; 
                            fonct.creer_une_fichier(msgtab[2],"base");
                            fonct.write_dans_une_file(txt,msgtab[2],"base");
                            out.println("success");
                            out.flush();
                        }else if(test2 != 1){
                            out.println("table exist");
                            out.flush();
                        }else{
                            out.println("type not exist");
                            out.flush();
                        }
                    }else{
                        out.println("you are not connected with base");
                        out.flush();
                    }
                }else if(msgtab[0].equals("insert") == true){
                    String tb = fonct.lire_file("base_en_cours");
                    String[] table_tab = tb.split(" ");
                    ms_send = table_tab[(table_tab.length-1)];
                    int test1 = fonct.test_table(ms_send+"_table",msgtab[2]);
                    if(test1 == 1){
                        int test2 = fonct.verification_type(msg,msgtab[2]);
                        if(test2 == 1){
                            String[] valeur = fonct.get_val(msg);
                            String txt = " ( "+valeur[0];
                            for(int c = 1 ; c < valeur.length ; c++){
                                txt += " , "+valeur[c];
                            }
                            txt = txt + " )"; 
                            fonct.write_dans_une_file(txt,msgtab[2],"base");
                            out.println("success");
                            out.flush();
                        }else{
                            out.println("error");
                            out.flush();
                        }
                    }else{
                        out.println("table not exist");
                        out.flush();
                    }
                }else if(msg.equals("exit") == true){
                    fonct.write_dans_une_file(" default","base_en_cours","base");
                    out.println("connected with System");
                    out.flush();   
                }
                else{  
                    out.println("commande not exist");
                    out.flush();
                }
                msg = in.readLine();
            }
            System.out.println("Client déconecté");
            out.close();
            clientSocket.close();
            serveurSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}