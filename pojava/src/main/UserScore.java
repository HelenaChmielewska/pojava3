package main;

/**
 * Klasa wyniku gracza
 * @author Helena Chmielewska
 */

public class UserScore {
	
	private String uzytkownik;
	 private int najwyzszyWynik;
	 private int ostatniWynik;

	 public UserScore(String u, int najw, int w) {
	  this.uzytkownik = u;
	  this.najwyzszyWynik = najw;
	  this.ostatniWynik = w;
	 }

	 public String getUzytkownik() {
	  return uzytkownik;
	 }

	 public void setUzytkownik(String uzytkownik) {
	  this.uzytkownik = uzytkownik;
	 }

	 public int getNajwyzszyWynik() {
	  return najwyzszyWynik;
	 }

	 public void setNajwyzszyWynik(int najwyzszyWynik) {
	  this.najwyzszyWynik = najwyzszyWynik;
	 }

	 public int getOstatniWynik() {
	  return ostatniWynik;
	 }

	 public void setOstatniWynik(int ostatniWynik) {
	  this.ostatniWynik = ostatniWynik;
	 }
	

}
