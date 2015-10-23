import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Crypt 
{	
	public String lineSeparator = System.getProperty("line.separator");
	
	public void encrypt (String inputFilename, String outputFilename, String keyword)
	{
		// KEYWORD CONTAINERS
		
		String trimmedKeyword = "";
		String TrimmedKeyword = "";
		
		
		// CREATES STANDARD ALPHABET REFERENCE
		
		String standardAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String StandardAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		
		// TRIMS KEYWORD FOR REPEATS
		
		for (int i = 0; i < keyword.length(); i++)
		{
			if (trimmedKeyword.indexOf(keyword.charAt(i)) == -1)
			{
				trimmedKeyword += keyword.charAt(i);
			}
		}
		
		TrimmedKeyword = trimmedKeyword.toUpperCase();
		
		
		// CREATES REVERSED ALPHABET REFERENCE
		
		StringBuffer alphabet = new StringBuffer(trimmedKeyword + "zyxwvutsrqponmlkjihgfedcba");
		StringBuffer Alphabet = new StringBuffer(TrimmedKeyword + "ZYXWVUTSRQPONMLKJIHGFEDCBA");
		
		
		// REMOVES KEYWORD FROM ALPHABET
		
		for (int i = 0; i < trimmedKeyword.length(); i++)
		{
			for (int j = trimmedKeyword.length(); j < alphabet.length(); j++)
			{
				if (trimmedKeyword.charAt(i) == alphabet.charAt(j))
				{
					alphabet.deleteCharAt(j);
					Alphabet.deleteCharAt(j);
					j--;
				}
			}
		}
		
		
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
			
			while (in.hasNextLine())
			{
				String input = in.nextLine();
				StringBuffer changeableData = new StringBuffer(input);
				
				
				// ENCRYPT LOWERCASE LETTERS
				
				for (int i = 0; i < changeableData.length(); i++)
				{
					int index = standardAlphabet.indexOf(changeableData.charAt(i));
					
					if (index != -1)
					{
						changeableData.setCharAt(i, alphabet.charAt(index));
					}
				}
				
				
				// ENCRYPT UPPERCASE LETTERS
				
				for (int i = 0; i < changeableData.length(); i++)
				{
					int index = StandardAlphabet.indexOf(changeableData.charAt(i));
					
					if (index != -1)
					{
						changeableData.setCharAt(i, Alphabet.charAt(index));
					}
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
		
				String trimmedKeyword = "";
				String TrimmedKeyword = "";
				
				
				// CREATES STANDARD ALPHABET REFERENCE
				
				String standardAlphabet = "abcdefghijklmnopqrstuvwxyz";
				String StandardAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				
				
				// TRIMS KEYWORD FOR REPEATS
				
				for (int i = 0; i < keyword.length(); i++)
				{
					if (trimmedKeyword.indexOf(keyword.charAt(i)) == -1)
					{
						trimmedKeyword += keyword.charAt(i);
					}
				}
				
				TrimmedKeyword = trimmedKeyword.toUpperCase();
				
				
				// CREATES REVERSED ALPHABET REFERENCE
				
				StringBuffer alphabet = new StringBuffer(trimmedKeyword + "zyxwvutsrqponmlkjihgfedcba");
				StringBuffer Alphabet = new StringBuffer(TrimmedKeyword + "ZYXWVUTSRQPONMLKJIHGFEDCBA");
				
				
				// REMOVES KEYWORD FROM ALPHABET
				
				for (int i = 0; i < trimmedKeyword.length(); i++)
				{
					for (int j = trimmedKeyword.length(); j < alphabet.length(); j++)
					{
						if (trimmedKeyword.charAt(i) == alphabet.charAt(j))
						{
							alphabet.deleteCharAt(j);
							Alphabet.deleteCharAt(j);
							j--;
						}
					}
				}
				
				
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
					
					while (in.hasNextLine())
					{
						String input = in.nextLine();
						StringBuffer changeableData = new StringBuffer(input);
						
						
						// DECRYPT LOWERCASE LETTERS
						
						for (int i = 0; i < changeableData.length(); i++)
						{
							int index = alphabet.indexOf("" + changeableData.charAt(i)); // standardAlphabet.indexOf(changeableData.charAt(i));
							
							if (index != -1)
							{
								changeableData.setCharAt(i, standardAlphabet.charAt(index));
							}
						}
						
						
						// DECRYPT UPPERCASE LETTERS
						
						for (int i = 0; i < changeableData.length(); i++)
						{
							int index = Alphabet.indexOf("" + changeableData.charAt(i)); // StandardAlphabet.indexOf(changeableData.charAt(i));
							
							if (index != -1)
							{
								changeableData.setCharAt(i, StandardAlphabet.charAt(index));
							}
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
