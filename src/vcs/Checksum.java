package vcs;

/*:
 * 
 * @author: Darryl
 * email : darrylcdmello@gmail.com
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

//Calculates rolling 4-byte weighted checksum
public class Checksum {
	


    private Reader buffer;

	public int getChecksum(String filename)
            throws IOException {
    	Charset encoding = Charset.defaultCharset();
        InputStream in = new FileInputStream(filename);
         Reader reader = new InputStreamReader(in, encoding);
         buffer = new BufferedReader(reader);
    
        int r,checkSum= 0;
        int j = 0;
        int[] valArray = new int[]{1,3,11,17};
        
        while((r=buffer.read())!=-1){
            checkSum+=r*valArray[j];
            j++;
            j=j%4;
        }
        return checkSum;
    }
}
