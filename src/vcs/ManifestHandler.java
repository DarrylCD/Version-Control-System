package vcs;

/*:
 * 
 * @author: Luqman
 * email : contact.luqman.khan@gmail.com
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.print.DocFlavor.STRING;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// This class handles all the methods to create and edit manifest file
public class ManifestHandler {
    
    static Document doc;
    public static String mfold = "Manifest", mfile = "manifesto.xml";
    String changed = "changed", unchanged = "unchanged", document = "document", entry = "Entry", action = "Action", date = "Date";
    Node rootElement, changed_tag, unchanged_tag, action_tag, version_tag, date_tag;
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    File newFile;
    
    // constructor to create the manifest.xml file directory and file
    public ManifestHandler () throws Exception{
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        
        // Create directory if directory does not exist
        newFile=new File(Paths.get(ArgParser.dest_path,mfold,mfile).toString());
        if(!newFile.getParentFile().exists())
        {
        	newFile.getParentFile().mkdirs();
        }
        
        // select the old manifest.xml file if file is already created
        if(newFile.exists()){
            doc = docBuilder.parse(newFile);
            rootElement = doc.getElementsByTagName("KBD").item(0);
        } else { // when there is no manifest.xml file for the project, create a new one
            doc = docBuilder.newDocument() ;
            rootElement = doc.createElement("KBD");
            date_tag = doc.createElement("Repo_Created_At");
            date_tag.setTextContent(new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()));
            rootElement.appendChild(date_tag);
            doc.appendChild(rootElement);
        }
        
        // create a tag "Version" for every version of code
        version_tag = doc.createElement("Version");
        date_tag = doc.createElement("Date");
        date_tag.setTextContent(new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()));
        version_tag.appendChild(date_tag);
        rootElement.appendChild(version_tag);
        
        // create action tag to add the action performed on the project
        action_tag = doc.createElement(action);
        version_tag.appendChild(action_tag);
        
        // If a file is changed then it gets added to created tag
        changed_tag = doc.createElement(changed);
        version_tag.appendChild(changed_tag);
        
        // If the file remains unchanged then add the name to the unchanged tag for that version 
        unchanged_tag = doc.createElement(unchanged);
        version_tag.appendChild(unchanged_tag);
        
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(newFile);
        transformer.transform(source, result);

    }
    //add updated node entry to manifest
    public void addUpdatedArtifact(File aritfact) throws TransformerException {
		
    Element artifact = doc.createElement("Artifact");
    Element id = doc.createElement("ArtifactID");
    id.setTextContent(aritfact.getName());
    artifact.appendChild(id);
    Element name = doc.createElement("ArtifactName");
    name.setTextContent(aritfact.getParentFile().getName());
    artifact.appendChild(name);
    Element path = doc.createElement("ArtifactPath");
    path.setTextContent(aritfact.getAbsolutePath());
    artifact.appendChild(path);
    changed_tag.appendChild(artifact);
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(newFile);
    transformer.transform(source, result);
    
    }
  //add unupdated node entry to manifest
    public void addNonUpdatedArtifact(File aritfact) throws TransformerException {
		
        Element artifact = doc.createElement("Artifact");
        Element id = doc.createElement("ArtifactID");
        id.setTextContent(aritfact.getName());
        artifact.appendChild(id);
        Element name = doc.createElement("ArtifactName");
        name.setTextContent(aritfact.getParentFile().getName());
        artifact.appendChild(name);
        Element path = doc.createElement("ArtifactPath");
        path.setTextContent(aritfact.getAbsolutePath());
        artifact.appendChild(path);
        unchanged_tag.appendChild(artifact);
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(newFile);
        transformer.transform(source, result);
        
        }
    // add command entry to manifest
    public void addCommand (String change) throws TransformerException{
        
        Element newEntry = doc.createElement("Command");
        newEntry.setTextContent(change);
        action_tag.appendChild(newEntry);    
        // saving in format specified by the extension of file path (XML)
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(newFile);
        transformer.transform(source, result);
    }
    
	// code to get files from that date
    public void doCheckOut (String date) throws IOException{

        NodeList nodelist = doc.getElementsByTagName("Date");
        Node node = null;
        for(int i=0; i<nodelist.getLength(); i++){
            if(nodelist.item(i).getTextContent().equals(date)){
                node = nodelist.item(i).getParentNode();
                break;
            }
        }

        if(node!=null){
            nodelist = node.getChildNodes();
            for(int i=0; i<nodelist.getLength(); i++){
                if(nodelist.item(i).getNodeName().endsWith("changed") || nodelist.item(i).getNodeName().endsWith("unchanged")){
                    NodeList subnodes = nodelist.item(i).getChildNodes();
                    for(int j=0; j<subnodes.getLength(); j++){
                    	NodeList sub = subnodes.item(j).getChildNodes();
                		String path = sub.item(2).getTextContent();
                		String base = ArgParser.dest_path;
                		String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();
                		int index=relative.lastIndexOf('/');
            		    relative=relative.substring(0,index);
            		    index = relative.indexOf("/");
            		    relative=relative.substring(index,relative.length());
            		    String finals = ArgParser.source_path+relative;
            		    File leaf = new File(finals);
            		    File lll = new File(sub.item(2).getTextContent());
            		    leaf.mkdirs();
            		    Files.copy(lll.toPath(), leaf.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        } else {
            System.out.println("No entry found on this date \n Use listCommit() command to verify the dates");
        }
    }
    
}
