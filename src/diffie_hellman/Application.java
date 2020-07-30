package diffie_hellman;

public class Application {
	public static void main(String[] args) {
		Participant alice = new Participant("Alice");
		Participant bob = new Participant("Bob");
		
		alice.gegenerateSharingValues(11, 7); //p=11 g=7
		alice.sendSharingValues(bob);
		
		alice.generatePrivateNumber(3);
		bob.generatePrivateNumber(6);
		
		alice.calculatePublicExchangeNumber();
		bob.calculatePublicExchangeNumber();
		
		alice.sendExchangeValue(bob);
		bob.sendExchangeValue(alice);
		
		alice.calculateSharedSecret();
		bob.calculateSharedSecret();
		
		String text = "morpheus";
		
		System.out.println("[Crypto] Encrypt message " + text + " ...");
		
		String encrypted = alice.encrypt(text);
		
		System.out.println("[Crypto] Encrypted message: " + encrypted);
		
		String decrypted = bob.decrypt(encrypted);
		
		System.out.println("[Crypto] Decrypted message: " + decrypted);
		
		System.out.println("=> " + (bob.getSharedSecret() == alice.getSharedSecret() ? "Diffie-Hellman successfully!" : "Diffie-Hellman Error!"));
	}
	
	
}
