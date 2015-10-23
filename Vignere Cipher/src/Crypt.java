import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Crypt 
{
	public String lineSeparator = System.getProperty("line.separator");

	public void encrypt (String inputFilename, String outputFilename, String keyword)
	{
		// KEYWORD CONTAINERS

		String key = keyword;
		String Key = keyword.toUpperCase();


		// CREATES STANDARD ALPHABET REFERENCE

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


		// FILE READING

		BufferedReader breader = null;
		FileReader reader; 
		String fileData = null;
		Scanner in = null;

		BufferedWriter bwriter = null;
		FileWriter writer = null; 

		try 
		{
			reader = new FileReader(inputFilename);
			breader = new BufferedReader(reader);
			in = new Scanner(breader);

			writer = new FileWriter(outputFilename);
			bwriter = new BufferedWriter(writer);

			StringBuffer changingFileData = new StringBuffer();

			int cipherState = 0;

			while (in.hasNextLine())
			{
				String input = in.nextLine();
				StringBuffer changeableData = new StringBuffer(input);

				ArrayList<Integer> nonLetterIndices = new ArrayList<Integer>();
				ArrayList<Character> nonLetterCharacters = new ArrayList<Character>();

				// REMOVE SPACES

				for (int i = 0; i < changeableData.length(); i++)
				{
					Character c = changeableData.charAt(i);


					if (Character.isLetter(c) == false)
					{
						nonLetterIndices.add(i);
						nonLetterCharacters.add(c);
						changeableData.deleteCharAt(i);	
					}
				}

				// ENCRYPT

				for (int i = 0; i < changeableData.length(); i++)
				{
					Character c = changeableData.charAt(i);

					if (Character.isLetter(c))
					{

						char currentChar = changeableData.charAt(i);
						char encryptedChar = ' ';

						int a = cipherState %= 5;

						int keyCurrent = key.charAt(a) - 97;
						int KeyCurrent = Key.charAt(a) - 65;


						if (alphabet.indexOf(currentChar) != -1)
						{
							int index = currentChar + keyCurrent;

							if (index > alphabet.length()+96)
							{
								index -= alphabet.length();
							}

							encryptedChar = (char) (index);
						}
						else if (Alphabet.indexOf(currentChar) != -1)
						{
							int index = currentChar + KeyCurrent;

							if (index > Alphabet.length()+64)
							{
								index -= Alphabet.length();
							}

							encryptedChar = (char) (index);
						}


						changeableData.setCharAt(i, encryptedChar);
						cipherState++;
					}	
				}

				// INSERT SPACES

				for (int i = 0; i < nonLetterIndices.size(); i++)
				{
					changeableData.insert(nonLetterIndices.get(i)+i, nonLetterCharacters.get(i));
				}


				bwriter.write(changeableData.toString() + "\n");
				bwriter.flush();
			}

			fileData = changingFileData.toString();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace(); 
		}
		finally
		{
			if (in != null)
			{
				in.close();
			}
			if (bwriter != null)
			{
				try 
				{
					bwriter.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}


		System.out.println ("File encrypted.");
	}

	public void decrypt (String inputFilename, String outputFilename, String keyword)
	{
		// KEYWORD CONTAINERS

		String key = keyword;
		String Key = keyword.toUpperCase();


		// CREATES STANDARD ALPHABET REFERENCE

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


		// FILE READING

		BufferedReader breader = null;
		FileReader reader; 
		String fileData = null;
		Scanner in = null;

		BufferedWriter bwriter = null;
		FileWriter writer = null; 

		try 
		{
			reader = new FileReader(inputFilename);
			breader = new BufferedReader(reader);
			in = new Scanner(breader);

			writer = new FileWriter(outputFilename);
			bwriter = new BufferedWriter(writer);

			StringBuffer changingFileData = new StringBuffer();

			int cipherState = 0;

			while (in.hasNextLine())
			{
				String input = in.nextLine();
				StringBuffer changeableData = new StringBuffer(input);

				ArrayList<Integer> nonLetterIndices = new ArrayList<Integer>();
				ArrayList<Character> nonLetterCharacters = new ArrayList<Character>();

				// REMOVE SPACES

				for (int i = 0; i < changeableData.length(); i++)
				{
					Character c = changeableData.charAt(i);


					if (Character.isLetter(c) == false)
					{
						nonLetterIndices.add(i);
						nonLetterCharacters.add(c);
						changeableData.deleteCharAt(i);	
					}
				}

				// DECRYPT

				for (int i = 0; i < changeableData.length(); i++)
				{
					Character c = changeableData.charAt(i);

					if (Character.isLetter(c))
					{

						char currentChar = changeableData.charAt(i);
						char encryptedChar = ' ';

						int a = cipherState %= 5;

						int keyCurrent = key.charAt(a) - 97;
						int KeyCurrent = Key.charAt(a) - 65;


						if (alphabet.indexOf(currentChar) != -1)
						{
							int index = currentChar - keyCurrent;

							if (index < 97)
							{
								index += alphabet.length();
							}

							encryptedChar = (char) (index);
						}
						else if (Alphabet.indexOf(currentChar) != -1)
						{
							int index = currentChar - KeyCurrent;

							if (index < 65)
							{
								index += Alphabet.length();
							}

							encryptedChar = (char) (index);
						}


						changeableData.setCharAt(i, encryptedChar);
						cipherState++;
					}	
				}

				// INSERT SPACES

				for (int i = 0; i < nonLetterIndices.size(); i++)
				{
					changeableData.insert(nonLetterIndices.get(i)+i, nonLetterCharacters.get(i));
				}


				bwriter.write(changeableData.toString() + "\n");
				bwriter.flush();
			}

			fileData = changingFileData.toString();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace(); 
		}
		finally
		{
			if (in != null)
			{
				in.close();
			}
			if (bwriter != null)
			{
				try 
				{
					bwriter.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}


		System.out.println ("File decrypted.");
	}


}
