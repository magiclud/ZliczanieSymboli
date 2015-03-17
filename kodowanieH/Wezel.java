package kodowanieH;

public class Wezel {

	private String wartosc;
	private int czestotliwosc;
	private Wezel lewyPotomek;
	private Wezel prawyPotomek;

	public Wezel(String wartosc, int czestotliwosc) {
		this.wartosc = wartosc;
		this.czestotliwosc = czestotliwosc;
		this.lewyPotomek = null;
		this.prawyPotomek = null;
	}

	public Wezel() {
		
	}

//	private void wyswietlZawartoscWezla() {
//		System.out.print("[");
//		System.out.print(wartosc);
//		System.out.print(",");
//		System.out.print(czestotliwosc);
//		System.out.print("]");
//		System.out.println("[");
//	}

	public void setWartosc(String wartosc) {
		this.wartosc = wartosc;
	}
	public void setCzestotliwosc(int czestotliwosc) {
		this.czestotliwosc = czestotliwosc;
	}

	public void setLewyPotomek(Wezel lewyPotomek) {
		this.lewyPotomek = lewyPotomek;
	}

	public void setPrawyPotomek(Wezel prawyPotomek) {
		this.prawyPotomek = prawyPotomek;
	}
	public String getWartosc() {
		return wartosc;
	}

	public int getCzestotliwosc() {
		return czestotliwosc;
	}
	public Wezel getLewyPotomek() {
		return lewyPotomek;
	}

	public Wezel getPrawyPotomek() {
		return prawyPotomek;
	}
}
