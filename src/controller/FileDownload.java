package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.PrivateKey;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import CentralAuthority.PKG;
import algorithm.Decrypt;
import dal.Dbconnect;

@WebServlet("/FileDownload")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrivateKey spk = null;
	PKG pkg = new PKG();
	String pkgn;
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = getServletContext();
		String path = context.getInitParameter("path");

		try {

			int uid = Integer.parseInt(request.getParameter("uid"));
			String region = request.getParameter("region");
			String filenm = request.getParameter("filenm");
			String filepath = path +  File.separator + filenm;
			 pkgn= request.getParameter("n");
			System.out.println(filepath);
			
			String serverids[] = Dbconnect.getserverid(Integer
					.toString(uid));
			//BigInteger n = pkg.getn();
			String key = Dbconnect.getPrivateKey(serverids[1]);
			BigInteger private_key = new BigInteger(key);
			BigInteger nn=new BigInteger(pkgn);

			if (AmazonStorage.downloadFile(region, filenm, filepath)) {
				
				File file = new File(filepath);
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				File decoded=new File(path+"\\decoded.txt");
				decoded.createNewFile();
				BufferedWriter bw1 = null;
				FileWriter fw1 = null;	
			
				String line1;
				while ((line1 = bufferedReader.readLine()) != null) {

					
				/*	byte[] dec=line1.getBytes();			
					byte[] decoded1 = Base64.decodeBase64(dec);
					String ln=new String(decoded1);
					BigInteger aa = new BigInteger(ln);		
					
				//	System.out.println("aa"+aa);
					BigInteger pt = aa.modPow(private_key, nn);
					String test = new String(pt.toByteArray());
					//System.out.println("test" + test);*/

					Decrypt decryptMessage = new Decrypt(serverids[1], nn,
							private_key);

					String test = decryptMessage.msgdecryption(line1);

					fw1 = new FileWriter(decoded,true);
					bw1 = new BufferedWriter(fw1);
					bw1.write(test);	
					bw1.write("\n");

					bw1.close();
					fw1.close();

				}
				fileReader.close();
				
								

				System.out.println("data decoded writed");

				response.setContentType("text/html");

				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filenm + "\"");

				// use inline if you want to view the content in browser,
				// helpful
				// for pdf file
				response.setHeader("Content-Disposition", "inline; filename=\""
						+ filenm + "\"");
				File fd = decoded;
				OutputStream out = response.getOutputStream();
				try (FileInputStream in = new FileInputStream(fd)) {
					byte[] buffer = new byte[4096];
					int length1;
					while ((length1 = in.read(buffer)) > 0) {
						out.write(buffer, 0, length1);
					}
				}
				out.flush();
				
				
				if(file.delete() && decoded.delete())
				{
					System.out.println("decoded files deleted");
				}

			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Failed to download file ..please try again later');");
				out.println("location='Personal.jsp';");
				out.println("</script>");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
