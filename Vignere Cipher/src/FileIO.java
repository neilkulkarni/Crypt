import java.io.*;
import java.util.Scanner;


public class FileIO 
{
	public String lineSeparator;
	
	
	public FileIO () 
	{
		lineSeparator = System.getProperty("line.separator");
	}
	
	
	public String readFile (String filename)
	{
		BufferedReader breader = null;
		FileReader reader; 
		String fileData = null;
		Scanner in = null;
		
		try 
		{
			reader = new FileReader(filename);
			breader = new BufferedReader(reader);
			in = new Scanner(breader);
			
			StringBuffer changingFileData = new StringBuffer();
			
			while (in.hasNextLine())
			{
				changingFileData.append(in.nextLine());
				changingFileData.append(lineSeparator);
			}
			
			fileData = changingFileData.toString();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if (in != null)
			{
				in.close();
			}
		}
		
		return fileData;
	}
	
	
	public void writeFile (String filename, String data)
	{
		BufferedWriter bwriter = null;
		FileWriter writer = null; 
		
		try 
		{
			writer = new FileWriter(filename);
			bwriter = new BufferedWriter(writer);
			
			bwriter.write(data);
			
			bwriter.flush();
		} 
		catch (FileNotFoundException e) 
		{
			// Think of a better reaction, such as asking the user for another file.
			e.printStackTrace(); 
		}
		catch (IOException e)
		{
			e.printStackTrace(); 
		}
		finally
		{
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
	}
}
