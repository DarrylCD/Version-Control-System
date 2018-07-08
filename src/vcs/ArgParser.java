package vcs;

/*:
 * 
 * @author: Hemanth
 * email : hemanth.bm12@gmail.com
 */
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

//Apache CLI Argument parser
public class ArgParser {

	
		 private static final Logger log = Logger.getLogger(ArgParser.class.getName());
		 public static  String[] args = null;
		 private Options options = new Options();
		 public static String source_path;
		 public static String dest_path;


		 public ArgParser (String[] args) {

		  options.addOption("c", "createRepo", false, "Create a new repositry.");
		  options.addOption("s", "source", true, "Root path of the source folder..");
		  options.addOption("t", "target", true, "Repo target path..");
		  options.addOption("u","commit",false,"Commit changes made to the repo");
		  options.addOption("d","commit",true,"Date to checkout");
		  options.addOption("o", "list", false, "List all dates of changes");
		  
		  this.args = args;
		   try{
			   args[0]=args[0];
		   } catch(Exception e) {
			   help();
			   System.exit(0);
		   }

		 }

		 public void parse() throws Exception {
		  CommandLineParser parser = new DefaultParser();

		  CommandLine cmd = null;
		  try {
		   cmd = parser.parse(options, args);

		   if (cmd.hasOption("c"))
		   {
			   if (cmd.hasOption("c") && cmd.hasOption("s") && cmd.hasOption("t"))
			   {
				   createRepo(cmd);
				   

			   }else
			   {
				   log.log(Level.SEVERE, "All of the following options required to create a repo: -c,-s,-t!!");
				    help();
			   }
		   }
		   
		   
		   if (cmd.hasOption("u"))
		   {
			   if (cmd.hasOption("u") && cmd.hasOption("s") && cmd.hasOption("t"))
			   {
				   updateRepo(cmd);
			   }else
			   {
				   log.log(Level.SEVERE, "All of the following options required to create a repo: -c,-s,-t!!");
				    help();
			   }
		   }
		   
		   if (cmd.hasOption("o"))
		   {
			   if (cmd.hasOption("o") && cmd.hasOption("s") && cmd.hasOption("t") && cmd.hasOption("d"))
			   {
				   checkOut(cmd);
			   }else
			   {
				   log.log(Level.SEVERE, "All of the following options required to create a repo: -c,-s,-t -t!!");
				    help();
			   }
		   }
		   
		    
		  } catch (ParseException e) {
		   log.log(Level.SEVERE, "Failed to parse command line options", e);
		   help();
		  }
		 }

		 //These happen when create repo command is used..
		 private void createRepo(CommandLine cmd) throws Exception {
			 try{
			 source_path=cmd.getOptionValue("s");
			 dest_path=cmd.getOptionValue("t");
				final File folder = new File(source_path);
				
				int create_flag=1;
				Manipulator.replicator(folder,create_flag);
				SCM.mh = new ManifestHandler();
				TreeWalker.listFilesNFolder(folder);
				String ipCmd=Arrays.toString(ArgParser.args);
				ipCmd=ipCmd.substring(1, ipCmd.length()-1).replaceAll(",","");
				SCM.mh.addCommand(ipCmd);
				log.log(Level.INFO, "Repo successfully created!");

				

			 }catch (NullPointerException e){
				 System.err.println("Invalid source path!");
				 System.exit(0);
			 } catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		 
		//These happen when commit command is used..
		 private void updateRepo(CommandLine cmd) throws Exception {
			 try{
			 source_path=cmd.getOptionValue("s");
			 dest_path=cmd.getOptionValue("t");
				final File folder = new File(source_path);
				Manipulator.replicator(folder, 0);
				SCM.mh = new ManifestHandler();
				TreeWalker.listFilesNFolder(folder);
				String ipCmd=Arrays.toString(ArgParser.args);
				ipCmd=ipCmd.substring(1, ipCmd.length()-1).replaceAll(",","");
				SCM.mh.addCommand(ipCmd);
				log.log(Level.INFO, "Repo successfully created!");

				

			 }catch (NullPointerException e){
				 System.err.println("Invalid source path!");
				 System.exit(0);
			 } catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		private void checkOut(CommandLine cmd) throws Exception {
			source_path=cmd.getOptionValue("t");
			dest_path=cmd.getOptionValue("s");
			SCM.mh = new ManifestHandler();
			SCM.mh.doCheckOut(cmd.getOptionValue("d"));
		}

		 //Help invoked when invalid arguments are passed. Possible options are listed.
		private void help() {
		  HelpFormatter formater = new HelpFormatter();
		  formater.printHelp("Help", options);
		  System.exit(0);
		 }
		}
	

