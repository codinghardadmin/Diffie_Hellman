package diffie_hellman;

public class Participant {
	
	private String name;
	private PublicSharingValues publicValues;
	private int PRIVATE;
	private int EXCHANGEVALUE;
	private int OTHEREXCHANGEVALUE;
	private int sharedSecret;
	
	public Participant(String name) {
		this.name = name;
		System.out.println("[DH] Created Participant " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void gegenerateSharingValues(int p, int g) {
		this.setPublicValues(new PublicSharingValues(g, p));
		
		System.out.println("[DH] " + name + " generated public values: p=" + p + " g=" + g);
	}

	public PublicSharingValues getPublicValues() {
		return publicValues;
	}

	public void setPublicValues(PublicSharingValues publicValues) {
		this.publicValues = publicValues;
	}

	public void sendSharingValues(Participant bob) {
		bob.setPublicValues(getPublicValues());
		
		System.out.println("[DH] " + name + " send public values to " + bob.getName());
	}

	public void generatePrivateNumber(int PRIVATE) {
		this.PRIVATE = PRIVATE;
		
		System.out.println("[DH] " + name + " generated private value: PRIVATE=" + PRIVATE);
	}
	
	public void calculatePublicExchangeNumber() {
		EXCHANGEVALUE = pow(publicValues.getG(), PRIVATE) % publicValues.getP();
		
		System.out.println("[DH] " + name + " calculated exchange value: EXCHANGEVALUE=" + EXCHANGEVALUE);
	}
	
	private int pow(int a, int b) {
		int retVal = 1;
		for (int i = 0; i < b; i++) {
			retVal *= a;
		}
		return retVal;
	}

	public void sendExchangeValue(Participant bob) {
		bob.setOTHEREXCHANGEVALUE(EXCHANGEVALUE);
		
		System.out.println("[DH] " + name + " send exchange value to " + bob.getName());
	}

	public void calculateSharedSecret() {
		sharedSecret = pow(OTHEREXCHANGEVALUE, PRIVATE) % publicValues.getP();
		
		System.out.println("[DH] " + name + " calculated private shared value: sharedSecret=" + sharedSecret);
	}

	public int getSharedSecret() {
		return this.sharedSecret;
	}
	
	public String encrypt(String plainText) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < plainText.length(); i++) {
			char character = (char) (plainText.codePointAt(i) + sharedSecret);
			stringBuilder.append(character);
		}
		return stringBuilder.toString();
	}

	public String decrypt(String cipherText) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < cipherText.length(); i++) {
			char character = (char) (cipherText.codePointAt(i) - sharedSecret);
			stringBuilder.append(character);
		}
		return stringBuilder.toString();
	}

	public int getOTHEREXCHANGEVALUE() {
		return OTHEREXCHANGEVALUE;
	}

	public void setOTHEREXCHANGEVALUE(int oTHEREXCHANGEVALUE) {
		OTHEREXCHANGEVALUE = oTHEREXCHANGEVALUE;
	}
	
}
