package vcs;

/*:
 * 
 * @author: Hemanth
 * email : hemanth.bm12@gmail.com
 */
import java.io.File;


//Parses the source repo recursively. 

public class TreeWalker {
	public static void listFilesNFolder(final File folder) throws Exception {
		
		
	    for (final File fileEntry : folder.listFiles()) {

	        if (fileEntry.isDirectory()) {
	        	Manipulator.replicator(fileEntry, 0);
	            listFilesNFolder(fileEntry);
	        } 
	        else {
	            Manipulator.updateFile(fileEntry.getAbsolutePath().toString());	            
	        }
	    }
	}

}
