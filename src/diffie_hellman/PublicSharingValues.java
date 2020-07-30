package diffie_hellman;

public class PublicSharingValues {

	private int g;
	private int p;
	
	public PublicSharingValues(int g, int p) {
		super();
		this.g = g;
		this.p = p;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

}
