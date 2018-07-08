# Version-Control-System
This is the project carried out as a part of CECS 529 at CSULB.

Class: CECS 543 Advanced Software Engineering 	
Section: 01 10341 					
Team Name: KBDS 					
Project Name: Version Control System		
Project Part 2	 				

Contact: darrylcdmello@gmail.com
	 hemanth.bm12@gmail.com
	 contact.luqman.khan@gmail.com
	 sspasha93@gmail.com

Introduction:
	This project part is to form a development team and to build the second part of our SCM (Source Code Management) project (AKA a VCS: Version Control System).  This project implements the next parts of our VCS: Check-in and Check-out. Check-in creates a new version of the project. “Artifact” is a version of a file. Corresponding entries for updated files are created and the current snapshot of the projcet is recorded in the manifest. Check-out creates a local copy of the repo. The SCM repository will hold multiple versions of a given file, hence the original file name is not sufficient to distinguish between two of its artifacts; hence, within the repository we use a code name for each artifact and put all the artifacts of a particular file in a folder which has the original file's name. 

External Requirements:
	Java JRE 1.7+ (JRE System library 1.7+), Runs on Windows or any Unix based OS.
	Add Apache Commons CLI library (commons-cli-1.x.x.jar)to your project library. Apache Commons CLI Download: http://commons.apache.org/proper/commons-cli/download_cli.cgi
	(Also packaged in the lib folder of submission.)
Build:
	V1.1

Setup:
	Requires installation of JAVA JDK and JRE 1.7+

Usage:
Extract the .zip file and use the following command
Ubuntu: 
javac -cp "./lib/commons-cli-1.3.1.jar:." *.java
java -cp "./lib/commons-cli-1.3.1.jar:." SCM -c -s "source_path" -t "destination_path" //create repo
java -cp "./lib/commons-cli-1.3.1.jar:." SCM -u -s "source_path" -t "destination_path" //update/check-in repo
java -cp "./lib/commons-cli-1.3.1.jar:." SCM -o -s "source_path" -t "destination_path -d "date" //check out repo

Eg: java -cp "/home/chimera/Eclipse/aaa/SCM/lib/commons-cli-1.3.1.jar:." SCM -c -s ~/ASE/testrepo/ -t ~/ASE/destrepo



Use equivalent java standard commands to compile and run java programs on other operating systems.. (dont forget to mention the classpath, also note, the delimeter for class path is ";" instead of ":" in windows)
	
Testing:

1. On the prior minimal ptree and file:    
mypt/      
h.txt  // Contains only the letter “H”;  

Directory is created!
May 12, 2017 9:06:05 PM ArgParser createRepo
INFO: Repo successfully created!


****Src file structure****
C:\Users\luqma\workspace\SCM\myPt\h.txt
May 12, 2017 9:07:24 PM ArgParser updateRepo
INFO: Repo successfully created!

 Directory of C:\Users\luqma\workspace\SCM\myPt

05/12/2017  09:06 PM    <DIR>          .
05/12/2017  09:06 PM    <DIR>          ..
05/12/2017  09:06 PM                 1 h.txt
               1 File(s)              1 bytes
               2 Dir(s)  864,580,997,120 bytes free

C:\Users\luqma\workspace\SCM\myPt>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPt\h.txt

****Repo structure after check in****
Directory of C:\Users\luqma\workspace\SCM\myPtSrc

05/12/2017  09:06 PM    <DIR>          .
05/12/2017  09:06 PM    <DIR>          ..
05/12/2017  09:06 PM    <DIR>          Manifest
05/12/2017  09:07 PM    <DIR>          myPt
               0 File(s)              0 bytes
               4 Dir(s)  864,580,272,128 bytes free

C:\Users\luqma\workspace\SCM\myPtSrc>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPtSrc\Manifest
C:\Users\luqma\workspace\SCM\myPtSrc\myPt
C:\Users\luqma\workspace\SCM\myPtSrc\Manifest\manifesto.xml
C:\Users\luqma\workspace\SCM\myPtSrc\myPt\h.txt
C:\Users\luqma\workspace\SCM\myPtSrc\myPt\h.txt\72.1.txt


****Manifest reflecting the steps****
This XML file does not appear to have any style information associated with it. The document tree is shown below.
<KBD>
<Repo_Created_At>2017_05_12_21_06_05</Repo_Created_At>
<Version>
<Date>2017_05_12_21_06_05</Date>
<Action>
<Command>
-c -s C:\Users\luqma\workspace\SCM\myPt -t C:\Users\luqma\workspace\SCM\myPtSrc
</Command>
</Action>
<changed/>
<unchanged/>
</Version>
<Version>
<Date>2017_05_12_21_07_24</Date>
<Action>
<Command>
-u -s C:\Users\luqma\workspace\SCM\myPt -t C:\Users\luqma\workspace\SCM\myPtSrc
</Command>
</Action>
<changed>
<Artifact>
<ArtifactID>72.1.txt</ArtifactID>
<ArtifactName>h.txt</ArtifactName>
<ArtifactPath>
C:\Users\luqma\workspace\SCM\myPtSrc\myPt\h.txt\72.1.txt
</ArtifactPath>
</Artifact>
</changed>
<unchanged/>
</Version>
</KBD>

****checked out folder structure****
 Directory of C:\Users\luqma\workspace\SCM\myPtDest

05/12/2017  09:17 PM    <DIR>          .
05/12/2017  09:17 PM    <DIR>          ..
05/12/2017  09:06 PM                 1 h.txt
               1 File(s)              1 bytes
               2 Dir(s)  864,580,165,632 bytes free

C:\Users\luqma\workspace\SCM\myPtDest>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPtDest\h.txt


****Manifest reflecting the steps****
This XML file does not appear to have any style information associated with it. The document tree is shown below.
<KBD>
<Checkout_Created_At>2017_05_12_21_06_05</Checkout_Created_At>
<Version>
<Date>2017_05_12_21_08_45</Date>
<Command>
-o -s C:\Users\luqma\workspace\SCM\myPtSrc -t C:\Users\luqma\workspace\SCM\myPtDest -d 2017_05_12_21_06_05
</Command>
</Version>
</KBD>

2. On the prior tiny ptree with files:    
mypt2/      
h.txt  // As above.      
hello.txt // Contains one line: “Hello world”.      
goodbye.txt // Contains two lines: “Good” and then “bye”. 


****Src file structure****
Directory is created!
May 12, 2017 9:54:05 PM ArgParser createRepo
INFO: Repo successfully created!

Directory of C:\Users\luqma\workspace\SCM\myPt2

05/12/2017  09:57 PM    <DIR>          .
05/12/2017  09:57 PM    <DIR>          ..
05/12/2017  09:57 PM                11 goodbye.txt
05/12/2017  09:55 PM                 1 h.txt
05/12/2017  09:56 PM                11 hello.txt
               3 File(s)             23 bytes
               2 Dir(s)  864,528,011,264 bytes free

C:\Users\luqma\workspace\SCM\myPt2>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPt2\goodbye.txt
C:\Users\luqma\workspace\SCM\myPt2\h.txt
C:\Users\luqma\workspace\SCM\myPt2\hello.txt

C:\Users\luqma\workspace\SCM\myPt2\goodbye.txt
C:\Users\luqma\workspace\SCM\myPt2\h.txt
C:\Users\luqma\workspace\SCM\myPt2\hello.txt
May 12, 2017 9:59:22 PM ArgParser updateRepo
INFO: Repo successfully created!

****Repo structure after check in****
 Directory of C:\Users\luqma\workspace\SCM\myPtSrc2

05/12/2017  09:54 PM    <DIR>          .
05/12/2017  09:54 PM    <DIR>          ..
05/12/2017  09:54 PM    <DIR>          Manifest
05/12/2017  09:59 PM    <DIR>          myPt2
               0 File(s)              0 bytes
               4 Dir(s)  864,528,072,704 bytes free

C:\Users\luqma\workspace\SCM\myPtSrc2>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPtSrc2\Manifest
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2
C:\Users\luqma\workspace\SCM\myPtSrc2\Manifest\manifesto.xml
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\goodbye.txt
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\h.txt
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\hello.txt
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\goodbye.txt\5404.11.txt
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\h.txt\72.1.txt
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\hello.txt\7988.11.txt


****Manifest reflecting the steps****
This XML file does not appear to have any style information associated with it. The document tree is shown below.
<KBD>
<Repo_Created_At>2017_05_12_21_54_05</Repo_Created_At>
<Version>
<Date>2017_05_12_21_54_05</Date>
<Action>
<Command>
-c -s C:\Users\luqma\workspace\SCM\myPt2 -t C:\Users\luqma\workspace\SCM\myPtSrc2
</Command>
</Action>
<changed/>
<unchanged/>
</Version>
<Version>
<Date>2017_05_12_21_59_22</Date>
<Action>
<Command>
-u -s C:\Users\luqma\workspace\SCM\myPt2 -t C:\Users\luqma\workspace\SCM\myPtSrc2
</Command>
</Action>
<changed>
<Artifact>
<ArtifactID>5404.11.txt</ArtifactID>
<ArtifactName>goodbye.txt</ArtifactName>
<ArtifactPath>
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\goodbye.txt\5404.11.txt
</ArtifactPath>
</Artifact>
<Artifact>
<ArtifactID>72.1.txt</ArtifactID>
<ArtifactName>h.txt</ArtifactName>
<ArtifactPath>
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\h.txt\72.1.txt
</ArtifactPath>
</Artifact>
<Artifact>
<ArtifactID>7988.11.txt</ArtifactID>
<ArtifactName>hello.txt</ArtifactName>
<ArtifactPath>
C:\Users\luqma\workspace\SCM\myPtSrc2\myPt2\hello.txt\7988.11.txt
</ArtifactPath>
</Artifact>
</changed>
<unchanged/>
</Version>
</KBD>

****checked out folder structure****
 Directory of C:\Users\luqma\workspace\SCM\myPtDest2

05/12/2017  10:16 PM    <DIR>          .
05/12/2017  10:16 PM    <DIR>          ..
05/12/2017  09:57 PM                11 goodbye.txt
05/12/2017  09:55 PM                 1 h.txt
05/12/2017  09:56 PM                11 hello.txt
               3 File(s)             23 bytes
               2 Dir(s)  864,527,110,144 bytes free

C:\Users\luqma\workspace\SCM\myPtDest2>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPtDest2\goodbye.txt
C:\Users\luqma\workspace\SCM\myPtDest2\h.txt
C:\Users\luqma\workspace\SCM\myPtDest2\hello.txt


****Manifest reflecting the steps****
This XML file does not appear to have any style information associated with it. The document tree is shown below.
<KBD>
<Checkout_Created_At>2017_05_12_21_34_25</Checkout_Created_At>
<Version>
<Date>2017_05_12_21_10_34</Date>
<Command>
-o -s C:\Users\luqma\workspace\SCM\myPtSrc2 -t C:\Users\luqma\workspace\SCM\myPtDest2 -d 2017_05_12_21_54_05
</Command>
</Version>
</KBD>

3. On a small ptree with a sub-folder (called “src”) and files:    
mypt3/      
h.txt  // As above.      
src/  // A sub-folder.        
main.fool // Main pgm file, Foo Language. Heh.          
// Contains one lines:  "Defoo main, darn sock."        
darn.fool // Exotic hole-filler component file.          
// Contains two lines:          
// 1. "Defoo darn stuff: set thread.color to param.color;"          
// 2. " find hole; knit hole closed." 

****Src file structure****
C:\Users\luqma\workspace\SCM\myPt3\h.txt
C:\Users\luqma\workspace\SCM\myPt3\src\darn.fool
C:\Users\luqma\workspace\SCM\myPt3\src\main.fool
May 12, 2017 10:06:24 PM ArgParser updateRepo
INFO: Repo successfully created!

****Repo structure after check in****
 Directory of C:\Users\luqma\workspace\SCM\myPtSrc3

05/12/2017  10:02 PM    <DIR>          .
05/12/2017  10:02 PM    <DIR>          ..
05/12/2017  10:02 PM    <DIR>          Manifest
05/12/2017  10:06 PM    <DIR>          myPt3
               0 File(s)              0 bytes
               4 Dir(s)  864,527,904,768 bytes free

C:\Users\luqma\workspace\SCM\myPtSrc3>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPtSrc3\Manifest
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3
C:\Users\luqma\workspace\SCM\myPtSrc3\Manifest\manifesto.xml
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\h.txt
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\src
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\h.txt\72.1.txt
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\src\darn.fool
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\src\main.fool
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\src\darn.fool\58216.80.fool
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\src\main.fool\14775.22.fool

****Manifest reflecting the steps****
This XML file does not appear to have any style information associated with it. The document tree is shown below.
<KBD>
<Repo_Created_At>2017_05_12_22_02_31</Repo_Created_At>
<Version>
<Date>2017_05_12_22_02_31</Date>
<Action>
<Command>
-c -s C:\Users\luqma\workspace\SCM\myPt3 -t C:\Users\luqma\workspace\SCM\myPtSrc3
</Command>
</Action>
<changed/>
<unchanged/>
</Version>
<Version>
<Date>2017_05_12_22_06_24</Date>
<Action>
<Command>
-u -s C:\Users\luqma\workspace\SCM\myPt3 -t C:\Users\luqma\workspace\SCM\myPtSrc3
</Command>
</Action>
<changed>
<Artifact>
<ArtifactID>72.1.txt</ArtifactID>
<ArtifactName>h.txt</ArtifactName>
<ArtifactPath>
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\h.txt\72.1.txt
</ArtifactPath>
</Artifact>
<Artifact>
<ArtifactID>58216.80.fool</ArtifactID>
<ArtifactName>darn.fool</ArtifactName>
<ArtifactPath>
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\src\darn.fool\58216.80.fool
</ArtifactPath>
</Artifact>
<Artifact>
<ArtifactID>14775.22.fool</ArtifactID>
<ArtifactName>main.fool</ArtifactName>
<ArtifactPath>
C:\Users\luqma\workspace\SCM\myPtSrc3\myPt3\src\main.fool\14775.22.fool
</ArtifactPath>
</Artifact>
</changed>
<unchanged/>
</Version>
</KBD>

****checked out folder structure****
 Directory of C:\Users\luqma\workspace\SCM\myPtDest3

05/12/2017  10:18 PM    <DIR>          .
05/12/2017  10:18 PM    <DIR>          ..
05/12/2017  10:03 PM                 1 h.txt
05/12/2017  10:18 PM    <DIR>          src
               1 File(s)              1 bytes
               3 Dir(s)  864,527,106,048 bytes free

C:\Users\luqma\workspace\SCM\myPtDest3>dir /s /b /o:gn
C:\Users\luqma\workspace\SCM\myPtDest3\src
C:\Users\luqma\workspace\SCM\myPtDest3\h.txt
C:\Users\luqma\workspace\SCM\myPtDest3\src\darn.fool
C:\Users\luqma\workspace\SCM\myPtDest3\src\main.fool


****Manifest reflecting the steps****
This XML file does not appear to have any style information associated with it. The document tree is shown below.
<KBD>
<Checkout_Created_At>2017_05_12_21_10_34</Checkout_Created_At>
<Version>
<Date>2017_05_12_21_10_34</Date>
<Command>
-o -s C:\Users\luqma\workspace\SCM\myPtSrc2 -t C:\Users\luqma\workspace\SCM\myPtDest2 -d 2017_05_12_22_11_47
</Command>
</Version>
</KBD>

Extra Features:
	Standard help menu to explain usage of commands.
	Features Apache Common CLI argument parser.
	Manifest is in XML format which makes it easier and faster to parse.
	MAnifest has extra data about "Changed" and "Unchanged" files.   

Bugs:
	Tell us if any.. :)

References:
	Stackoverflow: http://stackoverflow.com/
	Apache Commons docs: https://docs.oracle.com/javase/tutorial/
	Java SE docs: https://docs.oracle.com/javase/tutorial/
