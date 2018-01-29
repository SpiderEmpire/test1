package controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dal.Dbconnect;
import CentralAuthority.PKG;
import algorithm.Encrypt;
import algorithm.IBE;
import algorithm.KeyGeneration;
import services.ClService;
import services.FileDetails;
import uk.ac.ic.doc.jpair.ibe.BFCtext;

@WebServlet("/FileUploader")
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;

	FileDetails fd = new FileDetails();
	String region = null, fullpath = null;
	String name = null;
	int uid;
	static PublicKey spub = null;
	static PrivateKey spri = null;
	int serviceid;
	static PublicKey serpub = null;
	static PrivateKey serpri = null;
	static PKG pkg = new PKG();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		String path = context.getInitParameter("path");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		if (session != null) {
			uid = Integer.parseInt((String) session.getAttribute("uid"));
			region = (String) session.getAttribute("region");
			// fd.setUid(uid);
		}

		//serviceid = Dbconnect.getServiceId(region);

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				// upload to local folder
				//spub = setup(request);
				// call to function which compute keys for service A //remaining
				// coding

				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						name = new File(item.getName()).getName();
						fullpath = path + "\\" +  name;
						item.write(new File(fullpath));
					}
				}

				
				String serverids[]=Dbconnect.getserverid(Integer.toString(uid));
				if(algorithm.Setup.setup(Integer.toString(uid),serverids[1]))
				{
					System.out.println("setup done");
				}
			
				BigInteger Public_key=new BigInteger(Dbconnect.getPublicKey(serverids[1]));
				BigInteger n = pkg.getn();	
				
				// encryption of file
				
				
				BufferedWriter bw = null;
				FileWriter fw = null;

				File file = new File(fullpath);
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
			
				String line;
				File encoded=new File(path+"\\encoded.txt");
				encoded.createNewFile();
				while ((line = bufferedReader.readLine()) != null) {
					
					
					Encrypt encrypt1 = new Encrypt(serverids[1],line,n,Public_key); 				
					String cipher=encrypt1.msgencryption();					

					fw = new FileWriter(encoded,true);
					bw = new BufferedWriter(fw);
					bw.write(cipher);	
					//bw.write(ct.toString());
					bw.write("\n");

					bw.close();
					fw.close();

				}
				fileReader.close();
				
										
						
				  /*BufferedOutputStream bos = null;
			      
				    try {
				      FileOutputStream fos = new FileOutputStream(file);
				      bos = new BufferedOutputStream(fos); 
				      bos.write(cipher);
				    }finally {
				      if(bos != null) {
				        try  {
				          //flush and close the BufferedOutputStream
				          bos.flush();
				          bos.close();
				        } catch(Exception e){}
				      }
				    }*/

				ClService cl = new ClService();
				cl.setF(encoded);
				cl.setName(name);
				cl.setRegion(region);
				cl.setServerpub(spub);
			//	cl.setCipher(cipher);
				cl.setUserid(uid);
				cl.setN(n.toString());

				if (CloudService.Upload(cl)) {
					
					
					/*if(file.delete() && encoded.delete())
					{
						System.out.println("encoded files deleted");
					}*/
					out.println("<script type=\"text/javascript\">");
					out.println("alert('send to cloud');");
					out.println("location='Personal.jsp';");
					out.println("</script>");
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('failed to send cloud');");
					out.println("location='Uploadfile.jsp';");
					out.println("</script>");
				}

				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static PublicKey setup(HttpServletRequest request) throws IOException {
		String uid = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			uid = (String) session.getAttribute("uid");
		}

		if (KeyGeneration.ServerSetup(uid)) {
			spub = KeyGeneration.getPub();  System.out.println("spub123"+spub);
			// session.setAttribute("ServerPubKey",spub);
			spri = KeyGeneration.getPri(); System.out.println("spri123"+spri);
			if (KeyGeneration.setup(uid)) {
				System.out.println("setup done");
			} else {
				System.out.println("user setup not done");
			}
		} else {
			System.out.println("server setup not done");
		}

		return spub;
	}
}
