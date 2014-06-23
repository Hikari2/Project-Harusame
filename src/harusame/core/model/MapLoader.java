/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Hikari
 */
public class MapLoader 
{
    char[] map;
    
    
    public MapLoader(int width, int height)
    {
        map = new char[(width*height) / 100];
    }
    
    public char[] readMap(String level)
    {
        BufferedReader br = null;
           
		try {                     
			String sCurrentLine;
                        int counter = 0;
 
			br = new BufferedReader(new FileReader(level));
 
			while ((sCurrentLine = br.readLine()) != null) 
                        {
                            for(int i = 0; i < sCurrentLine.length(); i++)
                            {
                                map[counter] = sCurrentLine.charAt(i);
                                counter++;
                            }
                            System.out.println(sCurrentLine);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}          
        return map;
    }
    
    
}
