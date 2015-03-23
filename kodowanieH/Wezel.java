package kodowanieH;

public class Wezel {

	private byte wartoscByte;
	private int czestotliwosc;
	private Wezel lewyPotomek;
	private Wezel prawyPotomek;

	public Wezel(byte wartosc, int czestotliwosc) {
		this.wartoscByte = wartosc;
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

	public void setWartosc(byte wartosc) {
		this.wartoscByte = wartosc;
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
	public byte getWartosc() {
		return wartoscByte;
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
