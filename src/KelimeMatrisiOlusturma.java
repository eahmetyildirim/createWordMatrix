import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
 

public class KelimeMatrisiOlusturma {
    private static char tahta[][] = new char[20][20]; // private tanımadık ki diğer sınıflarda gözükmesin.
    private static ArrayList<String> kelimelerim = new ArrayList<String>(); // eğer kendimiz girmek istersek sınırsız boyutta arrayliste kelimelerimizi atıyoruz.
    private static String kelimeler[] = {"ATIK", "TAKI", "KATI", "KITA"}; // manuel tanımladığımız kelimeler
    private static String yazdirilan[] = {"ATIK", "TAKI", "KATI", "KITA"}; // manuel tanımladığımız kelimeler
    private static int N=20;
    private int satir=0;
    private int sutun=0;
    public static void OyunBaslat(){ // sadece bu fonksiyonu public tanımlayarak OOP mantığına en uygun şekilde oyunumuzu yazdık
        harfDoldur();

        
          for (int i = 0; i < yazdirilan.length; i++) {
          //      kelimeEkle(kelimeler[i]);
                kelimeEkle2(yazdirilan[i]);
        }

        tahtaGoster();
       
    }
     
    //Öncelikle rastgele kelimesiz tablo dizer
    private static void harfDoldur(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                
                tahta[i][j] ='0';
            }
        }
    }
     
    private static void kelimeEkle2(String kelime){
      int say=0;
      for(int i=0;i<20;i++){
          for(int j=0;j<20;j++){
            if(tahta[i][j]=='0'){
                say++;
                        
            }
          }
      }
      int satir,sutun;
      if(say==400){
                  Random r = new Random();
        int kelimeUzunluk = kelime.length(); // kelimenin uzunluğunu alırız
        int nasilEkleyim = r.nextInt(2); // sistem rastgele düzende ekleme yapar
        
        satir =1;
        sutun = 1;
            
        if(nasilEkleyim == 0 ){// soldan sağa
             
            for (int i = 0; i < kelime.length(); i++) {
                tahta[satir][sutun] = kelime.charAt(i);
                sutun++;
            }
        }else if(nasilEkleyim == 1 ){// yukardan aşağı
                       
            for (int i = 0; i < kelime.length(); i++) {
                tahta[satir][sutun] = kelime.charAt(i);
                satir++;
            }
        }
      
      }else{
          char ilk_harf=kelime.charAt(0);
          char son_harf=kelime.charAt(kelime.length()-1);
          System.out.print("Kelime="+kelime+" ");
          for(int i=0;i<20;i++){
              for(int j=0;j<20;j++){
                if(tahta[i][j]==ilk_harf){
                    int pozisyon=yatayMi_dikeyMi(i,j);
                    //System.out.println("Yatay mi dikey mi:"+yatayMi_dikeyMi(i,j));
                    System.out.print("-İlk:"+ilk_harf+"   = i:"+i+" j:"+j);
                    if(tahta[i][j+1]=='0' && pozisyon==1){//sağ tarafı müsaitse
                        System.out.println("Sağ taraf müsait");
                        if(tahta[i+1][j+1]=='0' && tahta[i-1][j+1]=='0' ){
                        satir=i;
                        sutun=j;
                        for (int z = 0; z < kelime.length(); z++) {
                            tahta[satir][sutun] = kelime.charAt(z);
                            sutun++;
                            }
                        return;
                        }
                    } 
                    if(tahta[i+1][j]=='0' && pozisyon==0){//alt taraf müsaitse
                          System.out.println("Alt taraf müsait");
                          if(tahta[i+1][j+1]=='0' && tahta[i+1][j-1]=='0' ){
                        satir=i;
                        sutun=j;
                          for (int z = 0; z < kelime.length(); z++) {
                               tahta[satir][sutun] = kelime.charAt(z);
                               satir++;
                         
                                }
                             return;
                          }    
                    }
                    
                    
                   
                }else if(tahta[i][j]==son_harf){
                    
                     int pozisyon=yatayMi_dikeyMi(i,j);
                     if(tahta[i][j-1]=='0' && pozisyon==1){//sağ tarafı müsaitse
                        System.out.println("Sol taraf müsait");
                        if(tahta[i+1][j-1]=='0' && tahta[i-1][j-1]=='0'){
                        satir=i;
                        sutun=j;
                          for (int z = kelime.length()-1; z >=0 ; z--) {
                              
                              System.out.println("sutun-"+sutun);
                            
                           tahta[satir][sutun] = kelime.charAt(z);
                               if(sutun-1==0){
                               matris_saga_kaydir();
                               }else{
                               sutun--;
                                }
                                }
                             return; 
                        }
                    }
                     
                     
                      if(tahta[i-1][j]=='0' && pozisyon==0){//alt taraf müsaitse
                        System.out.println("Üst taraf müsait");
                        if(tahta[i-1][j+1]=='0' && tahta[i-1][j-1]=='0'){
                        satir=i;
                        sutun=j;
                          for (int z = kelime.length()-1; z >=0 ; z--) {
                              
                              System.out.println("sutun-"+sutun);
                            
                           tahta[satir][sutun] = kelime.charAt(z);
                               if(satir-1==0){
                               matris_asagi_kaydir();
                               }else{
                               satir--;
                                }
                                }
                             return; 
                        }
                    }
                }
              
              }
          }
      
      }
      
      
      
      
    
    }
    public static int yatayMi_dikeyMi(int satir,int sutun){
        String yatay_k="",dikey_k="";
        for(int i=1;i<19;i++){
            if(tahta[satir][i]!='0'){
                yatay_k+=tahta[satir][i];
                
            }
            if(tahta[i][sutun]!='0'){
            dikey_k+=tahta[i][sutun];
            }
        }
      
        int yatay_say=0,dikey_say=0;
        for(int i=0;i<kelimeler.length;i++){
              int enk=kelimeler[i].length();
        
            
            
            for(int j=0;j<kelimeler[i].length()-1;j++){
                if(yatay_k.length()>=kelimeler[i].length() && kelimeler[i].charAt(j)==yatay_k.charAt(j) && kelimeler[i].charAt(j+1)==yatay_k.charAt(j+1)){
                yatay_say++;
                }
               
                if(dikey_k.length()>=kelimeler[i].length() && kelimeler[i].charAt(j)==dikey_k.charAt(j) && kelimeler[i].charAt(j+1)==dikey_k.charAt(j+1)){
                dikey_say++;
                }
            }
            
            if(kelimeler[i].length()==yatay_say+1){
                if(kelimeler[i].length()==dikey_say+1){
                return 3;
                }
                System.out.println("Yatay:"+yatay_k);
                return 0;
            }else if(kelimeler[i].length()==dikey_say+1){
                System.out.println("Dikey:"+dikey_k);
                return 1;
            }
        }
        
        return -1;
    }
     
    //kelimelerimizi ekler
    private static void kelimeEkle(String kelime){
        Random r = new Random();
        int kelimeUzunluk = kelime.length(); // kelimenin uzunluğunu alırız
        int nasilEkleyim = r.nextInt(2); // sistem rastgele düzende ekleme yapar
        int satir,sutun;
        if(nasilEkleyim == 0 ){// soldan sağa
            satir = r.nextInt(20);
            if(kelimeUzunluk != 20)
                sutun = r.nextInt(20-kelimeUzunluk); // sağdan taşmaması için kelimenin uzunluğundan az sutun numarası üretiriz
            else
                sutun = 0;
             
            for (int i = 0; i < kelime.length(); i++) {
                tahta[satir][sutun] = kelime.charAt(i);
                sutun++;
            }
        }else if(nasilEkleyim == 1 ){// sağdan sola
                      if(kelimeUzunluk != 20)
                satir = r.nextInt(20-kelimeUzunluk); //aşağıdan taşmaması için kelimenin uzunluğundan az sutun numarası üretiriz
            else
                satir = 0;
            sutun = r.nextInt(20);
             
            for (int i = 0; i < kelime.length(); i++) {
                tahta[satir][sutun] = kelime.charAt(i);
                satir++;
            }
        }
    }
    private static void matris_saga_kaydir(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
            tahta[i][N-j-1]=tahta[i][N-j-2];
              
            }
            tahta[i][0]='0';
        }
            
    }
    private static void matris_asagi_kaydir(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
            tahta[N-j-1][i]=tahta[N-j-2][i];
            
            }
            tahta[0][i]='0';
        }
            
    }
    
    // tablomuzu gösterir
    private static void tahtaGoster(){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {     
                System.out.print(tahta[i][j]+" ");
            }
            System.out.println();
        }
    }
}