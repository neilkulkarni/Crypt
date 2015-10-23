
public class Main 
{
	public static void main(String[] args) 
	{
		// ENCRYPTS AND DECRYPTS FILES
		
		Crypt file = new Crypt();
		file.encrypt("Romeo.txt", "RomeoEncrypted.txt", "crypt");
		file.decrypt("RomeoEncrypted.txt", "RomeoDecrypted.txt", "crypt");
	}
}
