package vcs;

/*:
 * 
 * @author: Hemanth
 * email : hemanth.bm12@gmail.com
 */
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//Contains all major file manupulation functions
public class Manipulator {
	static String root;
	
	//Handles function of creating a new repo.
	public static void replicator(File folder, int create_flag) throws Exception {
		String relative = new File(ArgParser.source_path).toURI().relativize(new File(folder.getAbsolutePath()).toURI()).getPath();
		if(relative.isEmpty())
		{
			root=folder.getName();
		}
		
		File dest_repo=new File(Paths.get(ArgParser.dest_path,root,relative).toString());
	if(create_flag==1)
	{
		if (!dest_repo.exists()) {
            if (dest_repo.mkdirs()) {
                System.out.println("Directory is created!");
            } 

		}
		else
		{
			System.err.println("Repo with the same name already exists in the target path..!");
			System.exit(-1);
		}
	}
		
	}

//Handles function of updating terminal files and logging into the manifest..
	public static void updateFile(String filename) throws Exception {
		 
         File source_leaf = new File(filename);
         String relative = new File(ArgParser.source_path).toURI().relativize(new File(source_leaf.getAbsolutePath()).toURI()).getPath();
         File dest_leaf=new File(Paths.get(ArgParser.dest_path,root,relative).toString());
         if(!dest_leaf.exists())
         {
        	 if(dest_leaf.mkdirs())
        	 {
//                 System.out.println("Directory is created!");
        	 }
        	 else {
                 System.out.println("Failed to create directory at "+dest_leaf.getAbsolutePath());
             }
         }
         Checksum chk = new Checksum();
         String Checksum = Integer.toString(chk.getChecksum(filename));
         String filesize=Integer.toString((int)source_leaf.length());
         File encoded_leaf=new File(Paths.get(ArgParser.dest_path,root,relative,Checksum+"."+filesize+filename.substring(filename.lastIndexOf("."))).toString());

    	 //  Input "Type" 0=action label, 1=changed file name entry, 2=unchanged file name entry
    	 System.out.println(filename);
    	 SCM.mh.addUpdatedArtifact(encoded_leaf);
    	 Files.copy(source_leaf.toPath(), encoded_leaf.toPath(), StandardCopyOption.REPLACE_EXISTING);

         
	}    
    
}

