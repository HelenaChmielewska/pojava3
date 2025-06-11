package main;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Klasa obługująca ranking graczy
 * @author Helena Chmielewska, Kinga Urban
 */

public class UsersRanking {

 private final String nazwaPliku = "osiagniecia.csv"; //plik w ktorym wszystko bedzie przechowywane
 public ArrayList<UserScore> rankingLista;
 public static int ilosc;

 public UsersRanking() {
  //tworzy plik do zapisu jesli taki jeszcze nie istnieje
  File file = new File(nazwaPliku);
  if(!file.exists()) {
	  try {
	    file.createNewFile();
	    rankingLista = new ArrayList<>();
   } 
	  catch (IOException e) {
	   pokazError(e.getMessage());
   }
  } 
  else {
	  rankingLista = wczytajOsiagniecia();
  }
  ilosc = rankingLista.size();
 }

 //wczytywanie danych z pliku csv
 private ArrayList<UserScore> wczytajOsiagniecia() {
  ArrayList<UserScore> lista = new ArrayList<>();
  try {
   BufferedReader reader = new BufferedReader(new FileReader(nazwaPliku));
   do {
    String linia = reader.readLine();
    if(linia == null) break;
    String[] dane = linia.split(";");
    lista.add(new UserScore(dane[0], Integer.parseInt(dane[1]), Integer.parseInt(dane[2])));
   } while (true);
   reader.close();
  } catch (IOException e) {
   pokazError(e.getMessage());
  }
  ilosc = lista.size();
  return lista;
 }

 //bledy
 private void pokazError(String wiadomosc) {
  String w = "Błąd podczas wczytywania osiągnięć. \n" + wiadomosc;
  JOptionPane.showMessageDialog(null, wiadomosc, "Błąd", JOptionPane.ERROR_MESSAGE);
 }

 public ArrayList<UserScore> getRankingLista() {
  return rankingLista;
 }

 //sortowanie listy
 public ArrayList<UserScore> posortuj() {
  ArrayList<UserScore> posortowana = new ArrayList<>(rankingLista);
  posortowana.sort((o1, o2) -> o2.getNajwyzszyWynik() - o1.getNajwyzszyWynik());
  return posortowana;
 }

 //wyniki dla uzytkownika
 public UserScore getUserRank(String uzytkownik) {
  for(UserScore rank : rankingLista) {
   if (rank.getUzytkownik().equals(uzytkownik)) {
    return rank;
   }
  }
  return null;
 }

 //dodawanie wyniku
 public void dodajUserRanking(String imie, int ostatni) {
  boolean znaleziony = false;
  for(UserScore rank : rankingLista) {
   //sprawdzanie czy ktos taki juz gral
   if(rank.getUzytkownik().equals(imie)) {
    aktualizujUserRanking(imie, ostatni);
    znaleziony = true;
   }
  }
  if(!znaleziony) {
   //dodajemy nowego gracza
   rankingLista.add(new UserScore(imie, ostatni, ostatni));
  }
  ilosc = rankingLista.size();
 }

 public void aktualizujUserRanking(String imie, int ostatni) {
  boolean znaleziony = false;
  for(UserScore rank : rankingLista) {
   //jesli istnieje to aktualizujemy najlepszy
   if(rank.getUzytkownik().equals(imie)) {
    rank.setOstatniWynik(ostatni);
    if(ostatni > rank.getNajwyzszyWynik()) {
     rank.setNajwyzszyWynik(ostatni);
    }
    znaleziony = true;
   }
  }
  if(!znaleziony) {
   //jeszcze nie gral wiec dodajemy go
   dodajUserRanking(imie, ostatni);
  }
  ilosc = rankingLista.size();
  
  /*for(UserScore rank : rankingLista) {
	  System.out.println(rank.getUzytkownik() + ";" + rank.getNajwyzszyWynik() + ";" + rank.getOstatniWynik() + "\n");
  }*/
 }

 //zapisz do pliku
 public void zapiszOsiagniecia() {
  try {
	// Sortujemy listę według najwyższego wyniku malejąco,
      // a gdy wyniki się zgadzają, to alfabetycznie po nazwie użytkownika
      rankingLista.sort((o1, o2) -> {
          int wynikCompare = Integer.compare(o2.getNajwyzszyWynik(), o1.getNajwyzszyWynik());
          if (wynikCompare != 0) {
              return wynikCompare;
          } else {
              return o1.getUzytkownik().compareToIgnoreCase(o2.getUzytkownik());
          }
      });
	 
	  FileWriter file = new FileWriter(nazwaPliku);
   
	  for(UserScore rank : rankingLista) {
    file.write(rank.getUzytkownik() + ";" + rank.getNajwyzszyWynik() + ";" + rank.getOstatniWynik() + "\n");
   }
   file.close();
  } catch (IOException e) {
   pokazError(e.getMessage());
  }
 }
}
