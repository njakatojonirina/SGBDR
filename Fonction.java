package fnct;
import java.io.*;
import javax.swing.*;
import java.util.Vector;
import java.lang.Character;
public class Fonction{
    public void creer_une_fichier(String nom_fichier,String directory){
        try{
            BufferedWriter out=new BufferedWriter(new FileWriter(directory+"/"+nom_fichier+".txt",true));
        }catch(Exception e){
            System.out.println(e);
        }
    } 
    public void write_dans_une_file(String mot,String nom_fichier,String directory){
        try{
            BufferedWriter out=new BufferedWriter(new FileWriter("base/"+nom_fichier+".txt",true));
            int i = 0;
            out.write(mot);
            out.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public String lire_file(String nom_fichier){
        String mis_azy = " ";
        try{
            BufferedReader out=new BufferedReader(new FileReader("base/"+nom_fichier+".txt"));
            int i = 0;
            mis_azy = out.readLine();
            out.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return mis_azy;
    } 

    public void create_base(String[] messagetab,PrintWriter out){
        if(messagetab[0].equals("create") == true){
            out.println("tafa");
            out.flush();
        }
    }
    public void create_table(String[] messagetab,PrintWriter out){
        if(messagetab[1].equals("table") == true){
            out.println("tafa");
            out.flush();
        }
    }
    public int choix_base(String database){
        String data = lire_file("data");
        int val = 65;
        if(data != null){
            String[] tab_base = data.split(",");
            for(int i = 0 ; i < tab_base.length ; i++){
                if(tab_base[i].equals(database) == true){
                    val = 1;
                    break;
                }else{
                    val = 0;
                }
            }
        }else{
            val = 0;
        }
        return val;
    }
    public int gest_table(String[] tab){
        int taille = tab.length;
        int ret = 0;
        if(tab[taille-1] == "default"){
            ret = 0;
        }else{
            ret = 1;
        }
        return ret;
    }
    public int paranthese(String[] tab){
        int val = 4;
        for(int i = 0 ; i < tab.length ; i++){
            if(tab[i].equals("(") == true){
                val = i;
                break;
            }
        }
        return val;
    }
    public int paranthese2(String[] tab){
        int val = 4;
        for(int i = 0 ; i < tab.length ; i++){
            if(tab[i].equals(")") == true){
                val = i;
                break;
            }
        }
        return val;
    }
    public String[] get_attribut(String txt){
        String[] tab_str = txt.split(" ");
        int indice = paranthese(tab_str);
        int taille = (tab_str.length-4)/3;
        String[] tab_far = new String[taille];
        int b = 0;
        int i = indice+1 ;
        while( i < tab_str.length - 1){
            tab_far[b] = tab_str[i];
            i+=3;
            b++;
        } 
        return tab_far;
    }
    public String[] get_type(String txt){
        String[] tab_str = txt.split(" ");
        int indice = paranthese(tab_str);
        int taille = (tab_str.length-4)/3;
        String[] tab_far = new String[taille];
        int b = 0;
        int i = indice+2 ;
        while( i < tab_str.length - 1){
            tab_far[b] = tab_str[i];
            i+=3;
            b++;
        } 
        return tab_far;
    }
    public int comparaison(String[] tab){
        int taille = tab.length;
        int val = 125;
        int c = 1253;
        int[] tab_int = new int[taille];
        for(int i = 0 ; i < taille ; i++){
            if(tab[i].equals("int") == true || tab[i].equals("string") == true){
                val = 1;
                tab_int[i] = val;
            }else{
                val = 0;
                tab_int[i] = val;
            }
        }
        int j = 0 ;
        for(int x = 1 ; x < tab_int.length ; x++){
            if(tab_int[j] == 1 && tab_int[x] == 1){
                c = 1;
            }else{
                c = 0;
                break;
            }
        }
        return c;
    }
    public void entrer_table(String nom_table,String nom_fichier){
        write_dans_une_file(nom_table+",",nom_fichier,"base");
    }
    public int test_existence_table(String nom_table,String nom_fichier){
        String tab = lire_file(nom_fichier);
        int val = 564;
        if(tab != null){
            String[] tab_table = tab.split(",");
            for(int i = 0 ; i < tab_table.length ; i++){
                if(tab_table[i].equals(nom_table) == true){
                    val = 0;
                    break;
                }else{
                    val = 1;
                }
            }
            if(val == 1){
                val = 1;
                entrer_table(nom_table,nom_fichier);
            }else{
                val = 0;
            }
        }else{
            entrer_table(nom_table,nom_fichier);
            val = 1;
        }
        return val;
    }
    public int test_existence_base(String nom_base,String nom_fichier){
        String tab = lire_file(nom_fichier);
        int val = 564;
        if(tab != null){
            String[] tab_base = tab.split(",");
            for(int i = 0 ; i < tab_base.length ; i++){
                if(tab_base[i].equals(nom_base) == true){
                    val = 0;
                    break;
                }else{
                    val = 1;
                }
            }
        }else{
            val = 1;
        }
        return val;
    }
    public  String[] get_valeur(String txt){
        String[] tab_str = txt.split(" ");
        int indice = paranthese(tab_str);
        Vector vect = new Vector();
        int b = 0;
        int i = indice+1 ;
        while( i < tab_str.length - 1){
            vect.add(tab_str[i]);
            i+=2;
            b++;
        } 
        int taille = vect.size();
        String[] tab_far = new String[taille];
        for(int j=0;j<taille;j++){
            tab_far[j] = vect.elementAt(j).toString();
        }
        return tab_far;
    }
    public String[] test_type(String txt){
        String[] valeur = get_valeur(txt);
        int indice = 0;
        char[][] tab_2dim = new char[valeur.length][];
        for(int i = 0 ; i < tab_2dim.length ; i++){
            tab_2dim[i] = valeur[i].toCharArray();
        }
        String[] type_de_ce_valeur = new String[valeur.length];
        for(int j = 0 ; j < valeur.length ; j++){
            if(Character.isDigit(tab_2dim[j][0]) != true){
                type_de_ce_valeur[indice] = "string";
                indice+=1;
            }else{
                type_de_ce_valeur[indice] = "int";
                indice+=1;
            }
        }
        return type_de_ce_valeur;
    }
    public int test_tb(String nom_table,String nom_fichier){
        String tab = lire_file(nom_fichier);
        int val = 564;
        if(tab != null){
            String[] tab_table = tab.split(",");
            for(int i = 0 ; i < tab_table.length ; i++){
                if(tab_table[i].equals(nom_table) == true){
                    val = 1;
                    break;
                }else{
                    val = 0;
                }
            }
        }else{
            val = 404;
        }
        return val;
    }
    public String[] get_type_attr(String nom_fichier){
        String tab = lire_file(nom_fichier);
        String[] tab_str = tab.split(" ");
        int indice = paranthese(tab_str);
        int stop = paranthese2(tab_str);
        int i = indice+2 ;
        Vector vect = new Vector();
        while( i < stop){
            vect.add(tab_str[i]);
            i+=3;
        } 
        int taille = vect.size();
        String[] tab_far = new String[taille];
        for(int j=0;j<taille;j++){
            tab_far[j] = vect.elementAt(j).toString();
        }
        return tab_far;
    }
    public int verification_type(String txt,String nom_fichier){
        String[] type_de_ce_valeur = test_type(txt);
        String[] type_attr_de_table = get_type_attr(nom_fichier);
        int test = 5654;
        if(type_de_ce_valeur.length == type_attr_de_table.length){
            for(int i = 0 ; i < type_de_ce_valeur.length ; i++){
                if(type_de_ce_valeur[i].equals(type_attr_de_table[i]) == true) {
                    test = 1;
                }else{
                    test = 0;
                    break;
                }
            }
        }else{
            test = 0;
        }
        return test;
    }
    public int test_table(String nom_fichier,String nom_table){
        String txt = lire_file(nom_fichier);
        String[] tab_txt = txt.split(",");
        int i = 0;
        int test = 56;
        if(txt != null){
            while(i < tab_txt.length){
                if(tab_txt[i].equals(nom_table) == true ){
                    test = 1;
                    break;
                }else{
                    test = 0;
                    i++;
                }
            }
        }else{
            test = 0;
        }
        return test;
    }
    public String elimination_simpl_ct(String[] txt){
        String avrn = " ";
        for(int i = 0 ; i < txt.length ; i++){
            if(txt[i].equals("'") == true){
                avrn = txt[1];
            }else{
                avrn = txt[0];
            }
        }
        return avrn;
    }
    public String[] get_val(String txt){
        String[] valeur = get_valeur(txt);
        char[][] t = new char[valeur.length][];
        for(int i = 0 ; i < valeur.length ; i++){
            t[i] = valeur[i].toCharArray();
        }
        int indice = 0;
        Vector vect = new Vector();
        int z = 0;
        String txt1 = " ";
        String txt2 = " ";
        for(int j = 0 ; j < t.length ; j++){
            if(Character.isDigit(t[j][z]) != true ){
                txt1 = t[j][1]+"";
                for(int x = 2 ; x < t[0].length-1 ; x++){
                    txt1 = txt1+""+t[j][x];
                }
                vect.add(txt1);
            }else{
                vect.add(t[j][z]);
            }
        }
        int taille = vect.size();
        String[] tab_far = new String[taille];
        for(int x=0;x<taille;x++){
            tab_far[x] = vect.elementAt(x).toString();
        }
        return tab_far;
    }
    public static void main(String[] args){
        Fonction fn = new Fonction();
        String t = "insert into personne values ( 'nirin' , 'njaka' , 4 )";
        String m = "'1'";
        // String x = fn.elimination_simpl_ct(m.split("'"));
         String[] x = fn.get_val(t);
        // String tab = t.split(" ");
    //    int c = fn.test_table("rh_table","tay");
        // String[] a = fn.get_type_attr("personne");
        // String[] a = fn.test_type(t);
        //int a = fn.comparaison(c);
        // for(int i=0;i<x.length;i++){
        //     System.out.println(x[i]); 
        // } 
        // String 
        // String[] 
        

        // // fn.get_val(t); 
        // System.out.println(po); 
    }
    
}