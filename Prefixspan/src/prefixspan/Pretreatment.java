package prefixspan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pretreatment {

	public void treat()
	{
		File file = new File("F:\\jtsg.txt");  
        ArrayList<String[]> dataArray = new ArrayList<String[]>();  
  
        try {  
            BufferedReader in = new BufferedReader(new FileReader(file));  
            String str;  
            String[] tempArray;  
            while ((str = in.readLine()) != null) {  
                tempArray = str.split(",");  
                dataArray.add(tempArray);  
            }  
            in.close();  
        } catch (IOException e) {  
            e.getStackTrace();  
        } 
        File file1 = new File("F:\\jtsg1.txt");
        try{
        	BufferedWriter writer =new BufferedWriter(new FileWriter(file1));
        	for (String[] str : dataArray)
        	{
        		int i=1;
        		for (String s : str)
        		{
        			writer.write(s);
        			i++;
        			if(i==7)
        			{
        				writer.write(" ");
        			}
        		}
        		writer.newLine();
        	}
        	writer.flush();
        	writer.close();
        }catch (IOException e) {  
            e.getStackTrace();  
        } 
            
	}
}
