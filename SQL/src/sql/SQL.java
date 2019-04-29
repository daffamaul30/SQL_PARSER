package sql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.SplittableRandom;
import javafx.scene.control.TableCell;
import jdk.nashorn.internal.objects.NativeString;
import oracle.jrockit.jfr.VMJFR;

public class SQL {
    
    public boolean cekPool(String pool)throws FileNotFoundException{
        String x = "pool.txt";
        String text;
        File f = new File(x);
        Scanner s = new Scanner(f);
        while(s.hasNextLine()){            
            text = s.nextLine();
            if (text.equals(pool)) {
                return false;
            }
        }
        return true;
    }
    
    public void wrPool(String pool)throws IOException{
        try {
            File f = new File("pool.txt");
            FileWriter fw = new FileWriter(f,true);
            BufferedWriter br = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(br);
            
            pw.println(pool);
            pw.close();
            
        } catch (IOException e) {
        }
        
    }
    
    public void menu5(ArrayList pool) throws FileNotFoundException{
        String x = "pool.txt";
        String text;
        File f = new File(x);
        Scanner s = new Scanner(f);
        while(s.hasNextLine()){            
            text = s.nextLine();
            System.out.println(text);
        }
    }
    
    public void readFile(ArrayList tabel,ArrayList BFR) throws FileNotFoundException{
        String x = "belajar.txt";
        String text = null;
        int i=0;
        int j,l;
        boolean sukses = false;
        boolean joins = false;File f = new File(x);
        Scanner s = new Scanner(f);while(s.hasNextLine()){
            sukses = false;
            text = s.nextLine();
            i=0;
            while(text.charAt(i) != ';'){
                i++;
            }
            if(Character.isDigit(text.charAt(i-1))){
                BFR.add(Integer.parseInt(text.subSequence(2, i).toString()));
            }
            else{
                tabel.add(text.subSequence(0, i));
                sukses = true;
            }
            i++;
            j=i;
            while(text.charAt(j) != '#'){
                if(text.charAt(j) == ';'){
                    if(Character.isDigit(text.charAt(j-1))){
                        BFR.add(Integer.parseInt(text.subSequence(i+2, j).toString()));
                    }
                    else{
                        tabel.add(text.subSequence(i, j));
                        sukses = true;
                    }
                    i=j+1;
                }
                j++;
            }
            if(sukses){
                tabel.add(" ");
            }
        }
    }
    
    public static int logb( double a, double b ){
        return (int)Math.ceil(Math.log(b) / Math.log(a));
    }
    public void clrscr() throws IOException{
        try {
            Process exitCode;
            if( System.getProperty( "os.name" ).startsWith( "Window" ) ) {
                exitCode = Runtime.getRuntime().exec("cls");
            }else{
                exitCode = Runtime.getRuntime().exec("clear");
            }
        }catch(IOException e){
            for(int i = 0; i < 1000; i++){
                System.out.println();
            }
        }
    }
    
    public void menu1(ArrayList tabel,ArrayList BFR){
        int i=0;
        int blockingfactor=0;
        int j=2; //R
        int fan=0;
        int k = 4; //V
        
        for (i=0;i<tabel.size()-1;i++){
            if(tabel.get(i) == " " || i ==0){
                blockingfactor = (int)BFR.get(1) / (int)BFR.get(j);
                fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(k));
                System.out.println("BFR "+tabel.get(i+1)+" " + blockingfactor);
                System.out.println("Fan Out Ratio "+tabel.get(i+1)+" "+fan);
                System.out.println();
                j = j+3;
                k = k+3;
            }
        }
    }
    
    public void menu2(ArrayList tabel,ArrayList BFR){
        int i;
        int blockdata,blockindeks,blockingfactor,fan;
        int j=2;
        int k = 4;
        int n = 3;
        
        for (i=0;i<tabel.size()-1;i++){
            if(tabel.get(i) == " " || i == 0){
                blockingfactor = (int)BFR.get(1) / (int)BFR.get(j);
                fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(k));
                blockdata = ((int)BFR.get(n) / blockingfactor);
                //ceil blockdata
                if((int)BFR.get(n) % blockingfactor > 0){
                    blockdata = blockdata + 1;
                }
                blockindeks = (int)BFR.get(n) / fan;
                //ceil blockindeks
                if((int)BFR.get(n) % fan > 0){
                    blockindeks = blockindeks + 1;
                }
                System.out.println("Block Data "+tabel.get(i+1)+" "+blockdata);
                System.out.println("Block Index "+tabel.get(i+1)+" "+blockindeks);
                System.out.println();
                j = j+3;
                k = k+3;
                n = n+3;
            }
        }
    }
    
    public void menu3(ArrayList tabel,ArrayList BFR){
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int j=2; //R
        int m=3;
        int k=4; //V
        int blockingfactor=0;
        int fan=0;
        int KetemuI = 0;
        int KetemuT = 0;
        int cariR=0;
        String cariT;
        
        System.out.print("Cari Rekord ke- :");
        cariR = input.nextInt();
        System.out.print("Nama Tabel : ");
        cariT = input2.nextLine();
        int i=0;
        boolean sukses = false;
        while (i<tabel.size()){
            if(tabel.get(i).toString().matches(cariT)){
                sukses = true;
            }
            i++;
        }
        
        System.out.println();
        for(i=0;i<tabel.size();i++){
            if(cariT.matches(tabel.get(i).toString())){
                blockingfactor = (int)BFR.get(1) / (int)BFR.get(j);
                fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(k));
                if(cariR <= (int)BFR.get(m)){
                KetemuI = cariR/fan;
                if((cariR%fan)>0){
                    KetemuI = KetemuI+1;
                }
                KetemuI =KetemuI + 1;
                KetemuT = cariR/blockingfactor;
                if((cariR%blockingfactor)>0){
                    KetemuT = KetemuT+1;
                }
                    System.out.println(fan);
                    System.out.println(blockingfactor);
                System.out.println("Jumlah blok yang diakses");
                System.out.println("- Menggunakan indeks : "+KetemuI);
                System.out.println("- Tanpa indeks : "+KetemuT);
                }
                else{
                    System.out.println("Jumlah rekord hanya "+BFR.get(m));
                    i=tabel.size()-1;
                }
            }
            if(tabel.get(i) == " "){
                j=j+3;
                k=k+3;
                m=m+3;
            }
        }
    }
   
    public void menu4(ArrayList tabel,ArrayList BFR)throws FileNotFoundException, IOException{
        tabel.forEach(System.out::println);
        int i=0,j=0,k=0,l=0;
        int x = 0,record=0,jumlah=0,kunci=0,record2=0,jumlah2=0,kunci2=0;
        int cost1 =0,cost2=0,blockdata=0,fan=0,blockingfactor=0,blockindeks=0;
        boolean sukses=false,where=false,joins=false,cekw=false;
        String optimal="";
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan SQL Statement : ");
        String statement = input.nextLine();
        statement = statement.toLowerCase();
        String txtpool = statement;
        String[] splited = statement.split("\\s+");
        String[] atribut = null;
            
        if(statement.contains(";")){
            if(splited[0].matches("select")){
                if(splited[2].matches("from")){
                    if(splited[splited.length-1].contains(";")){
                        if(splited.length>4){
                            if(splited[4].matches("join")){
                                if(splited[6].matches("using")){
                                    joins=true;
                                    i = 0;
                                    while(!splited[3].contains(tabel.get(i).toString())&& i!= tabel.size()-1 ){
                                        if(tabel.get(i).toString().matches(" ")){
                                            x++;    
                                        }
                                        i++;
                                    }
                                    record = 2+(x*3);
                                    jumlah = 3+(x*3);
                                    kunci = 4+(x*3);
                                    x=0;
                                    j = 0;
                                    while(!splited[5].contains(tabel.get(j).toString())&& j!= tabel.size()-1 ){
                                        if(tabel.get(j).toString().matches(" ")){
                                            x++;    
                                        }
                                        j++;
                                    }
                                    record2 = 2+(x*3);
                                    jumlah2 = 3+(x*3);
                                    kunci2 = 4+(x*3);
                                    k=i;
                                
                                    while(!tabel.get(k).toString().contains(" ") && !sukses){
                                         if(splited[5].matches(tabel.get(k).toString())){
                                            joins=true;
                                            
                                         }else{
                                            k++;
                                    }
                                }
                                }else{
                                    System.out.println("SQL Error Missing USING");
                                }
                            }else if(splited[4].matches("where")){
                                i=0;
                                while(!splited[3].contains(tabel.get(i).toString())&& i!= tabel.size()-1 ){
                                    if(tabel.get(i).toString().matches(" ")){
                                        x++;
                                        
                                    }
                                    i++;
                                }
                                    record = 2+(x*3);
                                    jumlah = 3+(x*3);
                                    kunci = 4+(x*3);
                                    
                                
                                k=i;
                                
                                while(!tabel.get(k).toString().contains(" ") && !sukses){
                                    if(splited[5].matches(tabel.get(k).toString())){
                                        sukses=true;
                                        where = true;
                                    }else{
                                        k++;
                                    }
                                }
                                if(!where){
                                System.out.println("SQL Error Atribute in Where not Found");
                                }
                            }
                        }else{
                            sukses=true;
                        }
                    }else{
                        System.out.println("SQL Error (Syntax Error)");
                    }
                }else if (statement.contains("from")){
                    System.out.println("SQL Error (Syntax Error)");
                }else{
                    System.out.println("SQL Error Missing FROM");
                }
            }else if (statement.contains("select")){
                System.out.println("SQL Error (Syntax Error)");
            }else{
                System.out.println("SQL Error Missing SELECT");
            }
            if(sukses){
                System.out.println("Output : ");
                if(splited[1].contains(",")){
                     atribut = splited[1].split(",");
                }else{
                     atribut = splited[1].split("&");
                }
                i=0;
                while(!splited[3].contains(tabel.get(i).toString())&& i!= tabel.size()-1 ){
                    i++;
                }
                int cek =0;
                k=i;
                if(i!=tabel.size()-1){
                    if(splited[3].contains(tabel.get(i).toString())){
                        System.out.print("Tabel (1) : ");
                        System.out.println(tabel.get(i));
                        j=0;
                        if((splited[1].compareTo("*")==0) && (atribut.length==1)){
                            k++;
                            System.out.print("List Kolom : ");
                            while(!tabel.get(k).toString().contains(" ")){
                                    System.out.print(tabel.get(k)+", ");
                                    k++;
                            }
                            System.out.println();
                            System.out.println();
                            //QEP 
                            if(where){
                                if(tabel.get(i+1).toString().matches(splited[5])){
                                    //Use key    
                                    //QEP #1
                                    System.out.println("QEP #1");
                                    txtpool = txtpool +" "+"QEP #1";
                                    System.out.print("SELECTION ");
                                    txtpool = txtpool +" "+"SELECTION ";
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ") && !cekw){
                                            if(tabel.get(k).toString().matches(splited[5])){
                                                System.out.print(splited[5]);
                                                txtpool = txtpool +" "+splited[5];
                                                System.out.print(splited[6]);
                                                txtpool = txtpool +" "+splited[6];
                                                System.out.print(splited[7]);
                                                txtpool = txtpool +" "+splited[7];
                                                cekw = true;
                                            }else{
                                                cekw = false;
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    System.out.println(" -- A1");
                                    txtpool = txtpool +" "+" -- A1";
                                    System.out.println(tabel.get(i).toString().toUpperCase());
                                    txtpool = txtpool +" "+tabel.get(i).toString().toUpperCase();
                                    blockingfactor = (int)BFR.get(1) / (int)BFR.get(record);
                                    blockdata = (int)BFR.get(jumlah) / blockingfactor;
                                    if((int)BFR.get(jumlah) % blockingfactor > 0){
                                        blockdata = blockdata + 1;
                                    }
                                    cost1 = blockdata/2;
                                    System.out.println("Cost : "+ cost1 + " blok");
                                    txtpool = txtpool +" "+"Cost : "+ cost1 + " blok";
                                    //QEP
                                    System.out.println();
                                    //QEP #2
                                    j=0;
                                    System.out.println("QEP #2");
                                    txtpool = txtpool +" "+"QEP #2";
                                    System.out.print("SELECTION ");
                                    txtpool = txtpool +" "+"SELECTION ";
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ")){
                                            if(tabel.get(k).toString().matches(splited[5])){
                                                System.out.print(splited[5]);
                                                txtpool = txtpool +" "+splited[5];
                                                System.out.print(splited[6]);
                                                txtpool = txtpool +" "+splited[6];
                                                System.out.print(splited[7]);
                                                txtpool = txtpool +" "+splited[7];
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    System.out.println(" -- A2");
                                    txtpool = txtpool +" "+" -- A2";
                                    System.out.println(tabel.get(i).toString().toUpperCase());
                                    txtpool = txtpool +" "+tabel.get(i).toString().toUpperCase();
                                    fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(kunci));
                                    blockindeks = (int)BFR.get(jumlah) / fan;
                                    if((int)BFR.get(jumlah) % fan > 0){
                                        blockindeks = blockindeks + 1;
                                    }
                                    cost2 = logb(fan, blockindeks)+1;
                                    System.out.println("Cost : "+ cost2 +" blok");
                                    txtpool = txtpool +" "+"Cost : "+ cost2 +" blok";
                                    if(cost1 < cost2){
                                        optimal = "QEP #1";
                                    }else{
                                        optimal = "QEP #2";
                                    }
                                    System.out.println("QEP Optimal : "+optimal);
                                    txtpool = txtpool +" "+"QEP Optimal : "+optimal+" .";
//cek shared pool                                    
//                                    if(cekPool(txtpool)){
//                                        wrPool(txtpool);
//                                        System.out.println(cekPool(txtpool)+" "+txtpool);
//                                    }
                                    //QEP
                                }else{
                                    //Not use key                                        
                                    //QEP #1
                                    System.out.println("QEP #1");
                                    txtpool = txtpool +" "+"QEP #1";
                                    System.out.print("SELECTION ");
                                    txtpool = txtpool +" "+"SELECTION ";
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ") && !cekw){
                                            if(tabel.get(k).toString().matches(splited[5])){
                                                System.out.print(splited[5]);
                                                txtpool = txtpool +" "+splited[5];
                                                System.out.print(splited[6]);
                                                txtpool = txtpool +" "+splited[6];
                                                System.out.print(splited[7]);
                                                txtpool = txtpool +" "+splited[7];
                                                cekw = true;
                                            }else{
                                                cekw = false;
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    System.out.println(" -- A1");
                                    txtpool = txtpool +" "+" -- A1";
                                    System.out.println(tabel.get(i).toString().toUpperCase());
                                    txtpool = txtpool +" "+tabel.get(i).toString().toUpperCase();
                                    blockingfactor = (int)BFR.get(1) / (int)BFR.get(record);
                                    fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(kunci));
                                    blockdata = (int)BFR.get(jumlah) / blockingfactor;
                                    if((int)BFR.get(jumlah) % blockingfactor > 0){
                                        blockdata = blockdata + 1;
                                    }
                                    cost1 = blockdata;
                                    System.out.println("Cost : "+ cost1 + " blok");
                                    txtpool = txtpool +" "+"Cost : "+ cost1 + " blok";
                                    //QEP
                                    System.out.println();
                                    //QEP #2
                                    optimal = "QEP #1";                                        
                                    System.out.println("QEP Optimal : "+optimal);
                                    txtpool = txtpool +" "+"QEP Optimal : "+optimal+" .";
                                    //cek shared pool
//                                    if(cekPool(txtpool)){
//                                        wrPool(txtpool);
//                                        System.out.println(cekPool(txtpool)+" "+txtpool);
//                                    }
                                    //QEP
                                }
                            }else{
                                cekw=true;
                            }
                            //QEP
                            if(!cekw){
                                System.out.println("Atribute Statement In Where Not Found");
                            }
                        }else{
                            System.out.print("List Kolom : ");
                            j=0;
                            while(j<atribut.length){
                                k=i;
                                while(!tabel.get(k).toString().contains(" ")){
                                    if(tabel.get(k).toString().matches(atribut[j])){
                                        System.out.print(atribut[j]+", ");
                                        cek++;
                                    }
                                    k++;
                                }
                                j++;
                            }                            
                            System.out.println();
                            System.out.println();
                            if (where) {
                                if(tabel.get(i+1).toString().matches(splited[5])){
                                    //Use key
                                    //QEP #1
                                    j=0;
                                    System.out.println("QEP #1");
                                    txtpool = txtpool +" "+"QEP #1";
                                    System.out.print("PROJECTION ");
                                    txtpool = txtpool +" "+"PROJECTION ";
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ")){
                                            if(tabel.get(k).toString().matches(atribut[j])){
                                                System.out.print(atribut[j]+", ");
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    System.out.println("-- on the fly");
                                    txtpool = txtpool +" "+"-- on the fly";
                                    System.out.print("SELECTION ");
                                    txtpool = txtpool +" "+"SELECTION ";
                                    j=0;
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ") && !cekw){
                                            if(tabel.get(k).toString().matches(splited[5])){
                                                System.out.print(splited[5]);
                                                txtpool = txtpool +" "+splited[5];
                                                System.out.print(splited[6]);
                                                txtpool = txtpool +" "+splited[6];
                                                System.out.print(splited[7]);
                                                txtpool = txtpool +" "+splited[7];
                                                cekw = true;
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    cekw = false;
                                    System.out.println(" -- A1");
                                    txtpool = txtpool +" "+" -- A1";
                                    System.out.println(tabel.get(i).toString().toUpperCase());
                                    txtpool = txtpool +" "+tabel.get(i).toString().toUpperCase();
                                    blockingfactor = (int)BFR.get(1) / (int)BFR.get(record);
                                    fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(kunci));
                                    blockdata = (int)BFR.get(jumlah) / blockingfactor;
                                    if((int)BFR.get(jumlah) % blockingfactor > 0){
                                        blockdata = blockdata + 1;
                                    }
                                    cost1 = blockdata/2;
                                    System.out.println("Cost : "+ cost1 + " blok");
                                    txtpool = txtpool +" "+"Cost : "+ cost1 + " blok";
                                    //QEP
                                    System.out.println();
                                    //QEP #2
                                    j=0;
                                    System.out.println("QEP #2");
                                    txtpool = txtpool +" "+"QEP #2";
                                    System.out.print("PROJECTION ");
                                    txtpool = txtpool +" "+"PROJECTION ";
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ")){
                                            if(tabel.get(k).toString().matches(atribut[j])){
                                                System.out.print(atribut[j]+", ");
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    System.out.println("-- on the fly");
                                    txtpool = txtpool +" "+"-- on the fly";
                                    System.out.print("SELECTION ");
                                    txtpool = txtpool +" "+"SELECTION ";
                                    j=0;
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ") && !cekw){
                                            if(tabel.get(k).toString().matches(splited[5])){
                                                System.out.print(splited[5]);
                                                txtpool = txtpool +" "+splited[5];
                                                System.out.print(splited[6]);
                                                txtpool = txtpool +" "+splited[6];
                                                System.out.print(splited[7]);
                                                txtpool = txtpool +" "+splited[7];
                                                cekw = true;
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    System.out.println(" -- A2");
                                    txtpool = txtpool +" "+" -- A2";
                                    System.out.println(tabel.get(i).toString().toUpperCase());
                                    txtpool = txtpool +" "+tabel.get(i).toString().toUpperCase();
                                    fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(kunci));
                                    blockindeks = (int)BFR.get(jumlah) / fan;
                                    if((int)BFR.get(jumlah) % fan > 0){
                                        blockindeks = blockindeks + 1;
                                    }
                                    cost2 = logb(fan, blockindeks)+1;                                    
                                    System.out.println("Cost : "+ cost2 +" blok");
                                    txtpool = txtpool +" "+"Cost : "+ cost2 +" blok";
                                    if(cost1 < cost2){
                                        optimal = "QEP #1";
                                    }else{
                                        optimal = "QEP #2";
                                    }
                                    System.out.println("QEP Optimal : "+optimal);
                                    txtpool = txtpool +" "+"QEP Optimal : "+optimal+" .";
                                    //cek shared pool
//                                    if(cekPool(txtpool)){
//                                        wrPool(txtpool);
//                                        System.out.println(cekPool(txtpool)+" "+txtpool);
//                                    }
                                    //QEP
                                }else{
                                    //Not use key                                        
                                    //QEP #1
                                    j=0;
                                    System.out.println("QEP #1");
                                    txtpool = txtpool +" "+"QEP #1";
                                    System.out.print("PROJECTION ");
                                    txtpool = txtpool +" "+"PROJECTION ";
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ")){
                                            if(tabel.get(k).toString().matches(atribut[j])){
                                                System.out.print(atribut[j]+", ");
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    System.out.println("-- on the fly");
                                    txtpool = txtpool +" "+"-- on the fly";
                                    System.out.print("SELECTION ");
                                    txtpool = txtpool +" "+"SELECTION ";
                                    j=0;
                                    while(j<atribut.length){
                                        k=i;
                                        while(!tabel.get(k).toString().contains(" ") && !cekw){
                                            if(tabel.get(k).toString().matches(splited[5])){
                                                System.out.print(splited[5]);
                                                txtpool = txtpool +" "+splited[5];
                                                System.out.print(splited[6]);
                                                txtpool = txtpool +" "+splited[6];
                                                System.out.print(splited[7]);
                                                txtpool = txtpool +" "+splited[7];
                                                cekw = true;
                                            }
                                            k++;
                                        }
                                        j++;
                                    }
                                    cekw = false;
                                    System.out.println(" -- A1");
                                    txtpool = txtpool +" "+" -- A1";
                                    System.out.println(tabel.get(i).toString().toUpperCase());
                                    txtpool = txtpool +" "+tabel.get(i).toString().toUpperCase();
                                    blockingfactor = (int)BFR.get(1) / (int)BFR.get(record);
                                    fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(kunci));
                                    blockdata = (int)BFR.get(jumlah) / blockingfactor;
                                    if((int)BFR.get(jumlah) % blockingfactor > 0){
                                        blockdata = blockdata + 1;
                                    }
                                    cost1 = blockdata;
                                    System.out.println("Cost : "+ cost1 + " blok");
                                    txtpool = txtpool +" "+"Cost : "+ cost1 + " blok";
                                    //QEP
                                    System.out.println();
                                    //QEP #2
                                    optimal = "QEP #1";
                                    System.out.println("QEP Optimal : "+optimal);
                                    txtpool = txtpool +" "+"QEP Optimal : "+optimal+" .";
                                    //cek shared pool
//                                    if(cekPool(txtpool)){
//                                        wrPool(txtpool);
//                                        System.out.println(cekPool(txtpool)+" "+txtpool);
//                                    }
                                    //QEP
                                }
                            }
                            if(!cekw){
                                System.out.println("Atribute Statement In Where Not Found");
                            }
                            if(cek!=atribut.length){
                                System.out.println("Atribute Statement Not Found");
                            }
                        }
                    }
                }else{
                    System.out.println("SQL Error Table not Found");
                }
            }else if(joins){
                System.out.println("Output : ");
                if(splited[1].contains(",")){
                    atribut = splited[1].split(",");
                }else{
                    atribut = splited[1].split("&");
                }
                i=0;l=0;
                while(!splited[3].contains(tabel.get(i).toString())&&
                        i!= tabel.size()-1 ){
                    i++;
                }
                while(!splited[5].contains(tabel.get(l).toString())&&
                        l!= tabel.size()-1 ){
                    l++;
                }
                int cek =0;
                k=i;
                if((i!=tabel.size()-1) && (l!=tabel.size()-1)){
                    if((splited[3].contains(tabel.get(i).toString())) &&
                            (splited[5].contains(tabel.get(l).toString()))){
                        System.out.print("Tabel (1) : ");
                        System.out.println(tabel.get(i));
                        j=0;
                        System.out.print("List Kolom : ");
                        while(j<atribut.length){
                            k=i;
                            while(!tabel.get(k).toString().contains(" ")){
                                if(tabel.get(k).toString().matches(atribut[j])){
                                    System.out.print(atribut[j]+", ");
                                    cek++;
                                }
                                k++;
                            }
                            j++;
                        }
                        System.out.println();
                        k=l;
                        System.out.print("Tabel (2) : ");
                        System.out.println(tabel.get(l));
                        j=0;
                        System.out.print("List Kolom : ");
                        while(j<atribut.length){
                            k=l;
                            while(!tabel.get(k).toString().contains(" ")){
                                if(tabel.get(k).toString().matches(atribut[j])){
                                    System.out.print(atribut[j]+", ");
                                    cek++;
                                }
                                k++;
                            }
                            j++;
                        }
                        System.out.println();
                        System.out.println();
                        blockingfactor = (int)BFR.get(1) / (int)BFR.get(record);
                        fan = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(kunci));
                        blockdata = (int)BFR.get(jumlah) / blockingfactor;
                        if((int)BFR.get(jumlah) % blockingfactor > 0){
                            blockdata = blockdata + 1;
                        }
                        int blockingfactor2 = (int)BFR.get(1) / (int)BFR.get(record2);
                        int fan2 = (int)BFR.get(1) / ((int)BFR.get(0) + (int)BFR.get(kunci2));
                        int blockdata2 = (int)BFR.get(jumlah2) / blockingfactor2;
                        if((int)BFR.get(jumlah2) % blockingfactor > 0){
                            blockdata2 = blockdata2 + 1;
                        }
                        cost1 = (blockdata * blockdata2)+blockdata;
                        cost2 = (blockdata2 * blockdata) +blockdata2;
                        //QEP
                        //QEP #1
                        System.out.println("QEP #1");
                        txtpool = txtpool +" "+"QEP #1";
                        System.out.print("PROJECTION ");
                        txtpool = txtpool +" "+"PROJECTION ";
                        System.out.print(splited[1]);
                        txtpool = txtpool +" "+splited[1];
                        System.out.println(" -- on the fly");
                        txtpool = txtpool +" "+" -- on the fly";
                        System.out.println("     "+splited[4]+" "+splited[5]
                                +" "+splited[6]+" "+splited[7]+" -- BNLJ");
                        txtpool = txtpool +" "+"     "+splited[4]+" "+splited[5]
                                +" "+splited[6]+" "+splited[7]+" -- BNLJ";
                        System.out.println(tabel.get(i).toString().toUpperCase()
                                +"   "+tabel.get(l).toString().toUpperCase());
                        txtpool = txtpool +" "+tabel.get(i).toString().toUpperCase()
                                +"   "+tabel.get(l).toString().toUpperCase();
                        System.out.println("Cost (worst case) : "+cost1 + " block");
                        txtpool = txtpool +" "+"Cost (worst case) : "+cost1 + " block";
                        System.out.println();
                        //QEP #2
                        System.out.println("QEP #2");
                        txtpool = txtpool +" "+"QEP #2";
                        System.out.print("PROJECTION ");
                        txtpool = txtpool +" "+"PROJECTION ";
                        System.out.print(splited[1]);
                        txtpool = txtpool +" "+splited[1];
                        System.out.println(" -- on the fly");
                        txtpool = txtpool +" "+" -- on the fly";
                        System.out.println("     "+splited[4]+" "+splited[5]
                                +" "+splited[6]+" "+splited[7]+" -- BNLJ");
                        txtpool = txtpool +" "+"     "+splited[4]+" "+splited[5]
                                +" "+splited[6]+" "+splited[7]+" -- BNLJ";
                        System.out.println(tabel.get(l).toString().toUpperCase()
                                +"   "+tabel.get(i).toString().toUpperCase());
                        txtpool = txtpool +" "+tabel.get(l).toString().toUpperCase()
                                +"   "+tabel.get(i).toString().toUpperCase();
                        System.out.println("Cost (worst case) : "+cost2 + " block");
                        txtpool = txtpool +" "+"Cost (worst case) : "+cost2 + " block";
                        if(cost1 < cost2){
                            optimal = "QEP #1";
                        }else{
                            optimal = "QEP #2";
                        }
                        System.out.println("QEP Optimal : "+optimal);
                        txtpool = txtpool +" "+"QEP Optimal : "+optimal+" .";
                        //cek shared pool
//                        if(cekPool(txtpool)){
//                            wrPool(txtpool);
//                            System.out.println(cekPool(txtpool)+" "+txtpool);
//                        }
                        //QEP
                        if((cek!=atribut.length+1) && (cek!=atribut.length)){
                            System.out.println("Atribute Statement Not Found");
                        }
                    }
                }else{
                    System.out.println("SQL Error Table not Found");
                }
            }
        }else{
            System.out.println("SQL Error Missing ;");
        }
        
    }
    
    
    
    public void menuutama(ArrayList tabel,ArrayList BFR, ArrayList pool) throws IOException{
        SQL tubes  = new SQL();
        BFR.forEach(System.out::println);
        System.out.println("Menu Utama:");
        System.out.println("    1.  Tampilkan BFR dan Fanout Ratio Setiap Tabel");
        System.out.println("    2.  Tampilkan Total Blok Data + Blok Index Setiap Tabel");
        System.out.println("    3.  Tampilkan Jumlah yang Diakses Untuk Pencarian Rekord");
        System.out.println("    4.  Tampilkan QEP dan Cost");
        System.out.println("    5.  Tampilkan Isi File Shared Pool");
        System.out.println("    0.  Keluar Program");
        System.out.print("Masukkan Pilihan Anda : ");
        int pilihan;
        String tes;
        Scanner input = new Scanner(System.in);
        pilihan = input.nextInt();
        switch(pilihan){
            case 1 :tubes.clrscr();
                    tubes.menu1(tabel,BFR);
                    input.nextLine();
                    input.nextLine();
                    tubes.menuutama(tabel, BFR, pool);
                    break;
            case 2 :tubes.clrscr();
                    tubes.menu2(tabel, BFR);
                    input.nextLine();
                    input.nextLine();
                    tubes.menuutama(tabel, BFR, pool);
                    break;
            case 3 :tubes.clrscr();
                    tubes.menu3(tabel, BFR);
                    input.nextLine();
                    input.nextLine();
                    tubes.menuutama(tabel, BFR, pool);
                    break;
            case 4 :tubes.clrscr();
                    tubes.menu4(tabel, BFR);
                    input.nextLine();
                    input.nextLine();
                    tubes.menuutama(tabel, BFR, pool);
                    break;
            case 5 :tubes.clrscr();
                    tubes.menu5(pool);
                    input.nextLine();
                    input.nextLine();
                    tubes.menuutama(tabel, BFR, pool);
                    break;
            case 0 :System.out.println("WELCOME");
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        SQL tubes = new SQL();
        ArrayList BFR = new ArrayList();
        ArrayList tabel = new ArrayList();
        ArrayList pool = new ArrayList();
        tubes.readFile(tabel,BFR);
        tubes.menuutama(tabel, BFR, pool);
    }
}