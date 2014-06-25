/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model;

import harusame.core.model.map.TileMap;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Hikari
 */
public class MapLoader 
{
    char[][] map;
    private TileMap tileMap;
    int w;
    int h;
    
    
    public MapLoader(int width, int height)
    {
        map = new char[height/10][width/10];
        w = width;
        h = height;
    }
    
    public char[][] readMap(String level)
    {
        tileMap = new TileMap(level);
        BufferedReader br = null;

           
		try {                     
			String sCurrentLine;
                        int counterX = 0;
                        int counterY = 0;
 
			br = new BufferedReader(new FileReader("Resources/Maps/" +level +".txt"));
 
			while ((sCurrentLine = br.readLine()) != null) 
                        {
                            for(int i = 0; i < sCurrentLine.length(); i++)
                            {
                                map[counterY][counterX] = sCurrentLine.charAt(i);
                                counterX++;
                                if(counterX == w/10)
                                {
                                    counterX = 0;
                                    counterY++;                                   
                                }                                
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
    
    public Image getTile(char symbol)
    {               
        return tileMap.getTile(symbol);
    }
    
    
}
