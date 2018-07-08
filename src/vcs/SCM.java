package vcs;

/*:
 * 
 * @author: Hemanth
 * email : hemanth.bm12@gmail.com
 */


public class SCM {
	
	//Object for manifest handling
	static ManifestHandler mh;
	
		public static void main(String[] args) throws Exception {
			// Parsing command line arguments.
			new ArgParser(args).parse();
	}

}

