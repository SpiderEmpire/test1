package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CentralAuthority.PKG;
import algorithm.Decrypt;
import dal.Dbconnect;

@WebServlet("/AdminFileDownload")
public class AdminFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PKG pkg = new PKG();
	String pkgn;

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		String path = context.getInitParameter("path");

		try {

			// int uid = Integer.parseInt(request.getParameter("uid"));
			String region = request.getParameter("region");
			String filenm = request.getParameter("filenm");
			 pkgn= request.getParameter("n");
			String filepath = path +  File.separator + filenm;

			System.out.println(filepath);

			if (AmazonStorage.downloadFile(region, filenm, filepath)) {

				/*File f = new File(filepath);
				InputStream is = new FileInputStream(f);

				// Get the size of the file
				long length = f.length();
				if (length > Integer.MAX_VALUE) {
					throw new IOException("Could not completely read file "
							+ f.getName() + " as it is too long (" + length
							+ " bytes, max supported " + Integer.MAX_VALUE
							+ ")");
				}

				// Create the byte array to hold the data
				byte[] bytes = new byte[(int) length];

				// Read in the bytes
				int offset = 0;
				int numRead = 0;
				while (offset < bytes.length
						&& (numRead = is.read(bytes, offset, bytes.length
								- offset)) >= 0) {
					offset += numRead;
				}

				// Ensure all the bytes have been read in
				if (offset < bytes.length) {
					throw new IOException("Could not completely read file "
							+ f.getName());
				}

				// Close the input stream and return bytes
				is.close();
				// return bytes
				System.out.println("bytes" + bytes);*/

				String serverids[] = Dbconnect.getserverid("admin");
				String key = Dbconnect.getPrivateKey(serverids[1]);
				BigInteger private_key = new BigInteger(key);

			
				BigInteger nn=new BigInteger(pkgn);
				PKG pkg = new PKG();
				BigInteger Private_key = pkg.get_private_key(serverids[1]);
				
				
				File file = new File(filepath);
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				File decoded=new File(path+"\\decoded.txt");
				decoded.createNewFile();
				BufferedWriter bw1 = null;
				FileWriter fw1 = null;	
			
				String line1;
				while ((line1 = bufferedReader.readLine()) != null) {				
					
				/*	
					byte[] dec=line1.getBytes();			
					byte[] decoded1 = Base64.decodeBase64(dec);
					String ln=new String(decoded1);
					BigInteger aa = new BigInteger(ln);		
					
				//	System.out.println("aa"+aa);
					BigInteger pt = aa.modPow(private_key, nn);
					String test = new String(pt.toByteArray());
					System.out.println("test" + test);*/

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
				
		
			/*	Decrypt decryptMessage = new Decrypt(serverids[1], nn,
						private_key);
				String msg = decryptMessage.decryption(bytes);
				System.out.println("after decryption " + msg);
				org.apache.commons.io.FileUtils.writeStringToFile(f, msg);*/

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


			} else {
				PrintWriter out = response.getWriter();

				out.println("<script type=\"text/javascript\">");
				out.println("alert('failed to download file...Please try again');");
				out.println("location='Personal.jsp';");
				out.println("</script>");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
