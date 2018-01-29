package dal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import algorithm.IBE;
import services.ClService;
import services.Complaints;
import services.Contactus;
import services.PublicNotice;
import services.Region;
import services.ReportDetails;
import services.User;

public class Dbconnect {
	public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://DESKTOP-PK3KJ65;databaseName=SmartGrid";
	static final String USER = "Mahesh";
	static final String PASS = "Mahesh@55";
	static Statement st;
	static ResultSet rs;

	public static Connection sqlconn() {
		Connection con = null;
		try {
			Class.forName(JDBC_DRIVER);
			//System.out.println("Successfully loaded");
		} catch (ClassNotFoundException ex) {

			ex.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return con;

	}

	public static boolean login(User cl) {
		boolean flag = false;
		int cnt = 0;

		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select count(UID) as cnt from UserDetails where username='"
					+ cl.getUsername()
					+ "' and password='"
					+ cl.getPwd()
					+ "' and status=1";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cnt = rs.getInt("cnt");
				// System.out.println(cnt);
				// flag=true;
			}
			rs.close();
			if (cnt == 1) {
				flag = true;

			}
			// System.out.println();
			System.out.println("Login success");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;

	}

	public static boolean adminlogin(User cl) {
		boolean flag = false;
		int cnt = 0;

		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select count(id) as cnt from admin where username='"
					+ cl.getUsername() + "' and password='" + cl.getPwd() + "'";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				cnt = rs.getInt("cnt");
				// System.out.println(cnt);
				// flag=true;
			}
			rs.close();
			if (cnt == 1) {
				flag = true;

			}
			// System.out.println();
			System.out.println("Login success");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;

	}

	public static String[] getUserDetails(String unm) {

		String user[] = new String[12];
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from UserDetails where username='" + unm
					+ "'";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				user[0] = rs.getString("UId");
				user[1] = rs.getString("fname") + " " + rs.getString("lname");
				user[2] = rs.getString("region");
				user[3] = rs.getString("MeterNo");
				user[4] = rs.getString("address");
				user[5] = rs.getString("email");
				user[6] = rs.getString("username");
				user[7] = rs.getString("password");
				user[8] = rs.getString("fname");
				user[9] = rs.getString("lname");
				user[10] = rs.getString("phone");
				user[11] = rs.getString("consumer");

				// user[5]=rs.getString("")
				// System.out.println(name);
			}
			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}

	public static int registration(User cl) {
		int uid = 0;
		int cnt = 0;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql1 = "select count(UId) as cnt from UserDetails where username='"
					+ cl.getUsername() + "'";
			rs = st.executeQuery(sql1);
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			rs.close();
			System.out.println("cnt" + cnt);
			if (cnt == 1) {
			} else {
				String sql = "insert into UserDetails(fname,lname,phone,password,email,region,MeterNo,status,address,consumer,username) values('"
						+ cl.getFnm()
						+ "','"
						+ cl.getLnm()
						+ "','"
						+ cl.getPhone()
						+ "','"
						+ cl.getPwd()
						+ "','"
						+ cl.getEmail()
						+ "','"
						+ cl.getRegion()
						+ "','"
						+ cl.getMeterNo()
						+ "',1,'"
						+ cl.getAddress()
						+ "','"
						+ cl.getConsumer() + "','" + cl.getUsername() + "');";
				System.out.println(sql);
				if (st.executeUpdate(sql) == 1) {
					String query = "select SCOPE_IDENTITY() as uid; ";
					rs = st.executeQuery(query);
					while (rs.next()) {
						uid = rs.getInt("uid");
					}
				}

				System.out.println("Inserted");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return uid;

	}

	public static boolean SaveUsage(User cl) {

		boolean flag = false;

		// String LogMessage = null;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "insert into Data(UserID,date,MeterReading,filename,filepath,MeterNo,fromdate,todate) values('"
					+ cl.getId()
					+ "', GETDATE(),'"
					+ cl.getUsage()
					+ "','"
					+ cl.getFilenm()
					+ "','"
					+ cl.getFilepath()
					+ "','"
					+ cl.getMeterNo() + "','"

					+ cl.getFromdate() + "','" + cl.getTodate() + "')";
			System.out.println(sql);
			st.executeUpdate(sql);
			System.out.println("data usage Inserted");
			flag = true;

			// DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			// Date dateobj = new Date();
			// System.out.println(df.format(dateobj));
			// LogMessage = "<UserID>: " + sess.getAttribute("UId")
			// + " <Date>: " + df.format(dateobj);
			// LogMessage = LogMessage + " <Usage>: " + cl.getUsage();
			//
			// System.out.println(LogMessage);
			// LogUtils.LogMessage(LogMessage);

			// flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static Boolean saveMK(KeyPair masterKey) {

		Boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "update master_key set master_key='" + masterKey + "'";
			System.out.println(sql);
			int a = st.executeUpdate(sql);
			if (a == 1) {
				System.out.println("master key set");
				flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static String getMK() {

		// java.security.KeyPair mk1 = null;
		String mk = null;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select master_key from master_key";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				mk = rs.getString("master_key");
			}
			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return mk;
	}

	public static Boolean saveKeys(BigInteger Public_key,
			BigInteger Private_key, String id) throws IOException {

		Boolean flag = false;

		try {
			//System.out.println("hi keys");
			Connection con = sqlconn();
			st = con.createStatement();

			String sql1 = "If Not Exists(select * from private_key where userid=?) Begin "
					+ "insert into public_key values (?,?);"
					+ "insert into private_key values (?,?);"
					+ "End else Begin update public_key set public_key=? where userid=?;"
					+ "update private_key set private_key=? where userid=? End";

			//System.out.println(sql1);

			PreparedStatement pst = con.prepareStatement(sql1);
			pst.setString(1, id);
			pst.setString(2, id);
			pst.setString(3, Public_key.toString());
			pst.setString(4, id);
			pst.setString(5, Private_key.toString());

			pst.setString(6, Public_key.toString());
			pst.setString(7, id);
			pst.setString(8, Private_key.toString());
			pst.setString(9, id);
			pst.executeUpdate();

			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static String[] getserverid(String userid) {

		String[] sid = new String[2];
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = null;

			if (userid.equals("admin")) {
				System.out.println("userid" + userid);
				sql = "select ServerName,ServerId from Server s,admin u,Regional_Cloud r "
						+ "where username='"
						+ userid
						+ "' "
						+ "and u.region=r.RegionName and r.RegionalNo=s.RegionalNo";
			} else {
				sql = "select ServerName,ServerId from Server s,UserDetails u,Regional_Cloud r "
						+ "where UID='"
						+ userid
						+ "' and u.region=r.RegionName and r.RegionalNo=s.RegionalNo";
			}

			//System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sid[0] = rs.getString("ServerName");
				sid[1] = rs.getString("ServerId");
			}
			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sid;
	}

	public static String[] getadminserverid(String userid) {

		String[] sid = new String[2];
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select ServerName,ServerId from Server s,Admin u,Regional_Cloud r ";
			sql += "where username='"
					+ userid
					+ "' and u.region=r.RegionName and r.RegionalNo=s.RegionalNo";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				sid[0] = rs.getString("ServerName");
				sid[1] = rs.getString("ServerId");

				System.out.println("serverid" + sid[0] + sid[1]);
			}
			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return sid;
	}

	public static Boolean AddComplaint(String consumer, String nm,
			String email, String msg, String phone, String region,
			String address, String type, int uid) {

		boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "insert into Complaints values('" + nm + "','" + email
					+ "','" + msg + "','" + region + "','" + consumer + "','"
					+ phone + "','" + address + "','" + type + "','" + uid
					+ "')";
			// System.out.println(sql);
			st.executeUpdate(sql);
			System.out.println("Comaplaint Inserted");
			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static ArrayList<String> getAllRegion() {

		ArrayList<String> region = new ArrayList<String>();
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select RegionName from Regional_Cloud where RegionName not in ('admin')";
			System.out.println(sql);
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				region.add(rs.getString("RegionName"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("region" + region);
		return region;
	}

	public static Boolean addRegion(Region r) {

		boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = " Insert into Regional_Cloud values('"
					+ r.getRname()
					+ "',"
					+ "(select CloudNo from Cloud where Name='Amezon'));"
					+ "  insert into DataCenter values('"
					+ r.getDCnm()
					+ "','IST','"
					+ r.getCost()
					+ "',"
					+ "(SELECT TOP 1 RegionalNo FROM Regional_Cloud ORDER BY RegionalNo DESC));"
					+ "insert into Server values('"
					+ r.getServernm()
					+ "','"
					+ r.getSdesc()
					+ "',"
					+ "1000,900,(SELECT TOP 1 DCID FROM DataCenter ORDER BY DCID DESC),"
					+ "(SELECT TOP 1 RegionalNo FROM Regional_Cloud ORDER BY RegionalNo DESC),"
					+ "'SQL Server') insert into Service values('ServiceA','(SELECT TOP 1 RegionalNo"
					+ " FROM Regional_Cloud ORDER BY RegionalNo DESC)')";
			System.out.println(sql);
			int a = st.executeUpdate(sql);

			if (a == 1) {
				flag = true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag;
	}

	public static ArrayList<User> getAllUser(String filter) {
		ArrayList<User> UserList = new ArrayList<User>();
		try {

			String sql="";
			Connection con = sqlconn();
			st = con.createStatement();
			
			if(filter.contains("All"))
			{
			 sql = "select * from UserDetails";
			}
			else
			{
				sql="select * from UserDetails where region='"+filter+"'";
			}
			
			rs = st.executeQuery(sql);
			while (rs.next()) {
				User u = new User();
				u.setFnm(rs.getString("fname"));
				u.setLnm(rs.getString("lname"));
				u.setEmail(rs.getString("email"));
				u.setPhone(rs.getString("phone"));
				u.setPwd(rs.getString("password"));
				u.setMeterNo(rs.getString("MeterNo"));
				u.setRegion(rs.getString("region"));
				u.setStatus(rs.getString("status"));
				u.setConsumer(rs.getString("consumer"));
				u.setUId(rs.getInt("UId"));
				UserList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return UserList;
	}

	public static ArrayList<Region> getAllRegionDetails() {
		ArrayList<Region> RegionList = new ArrayList<Region>();
		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select RegionName,DCName,ServerName,Discription,CostperMemory from Regional_Cloud r,"
					+ "DataCenter dc,Server s where r.RegionalNo=dc.RegionalNo and s.DCID=dc.DCID";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Region u = new Region();
				u.setRname(rs.getString("RegionName"));
				u.setDCnm(rs.getString("DCName"));
				u.setServernm(rs.getString("ServerName"));
				u.setSdesc(rs.getString("Discription"));
				u.setCost(rs.getString("CostperMemory"));
				RegionList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return RegionList;
	}

	public static Boolean changeUserStatus(int uid, int status) {

		boolean flag = false;
		try {
			Connection con = sqlconn();
			String sql = "update UserDetails set status=? where UID=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, status);
			pst.setInt(2, uid);
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;

	}

	public static ArrayList<ClService> getUserFiles(int userid)
			throws IOException, ClassNotFoundException {
		ArrayList<ClService> FileList = new ArrayList<ClService>();
		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from Personal where userid='" + userid + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ClService u = new ClService();
				u.setRegion(rs.getString("region"));
				u.setName(rs.getString("filename"));
				u.setFileid(rs.getInt("pid"));
				u.setUserid(Integer.parseInt(rs.getString("userid")));
				//u.setCipher(rs.getBytes("cipher"));
				u.setN(rs.getString("N"));
				FileList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return FileList;
	}

	public static boolean SavePersonal(ClService cl) throws IOException {

		boolean flag = false;
		try {

			/*ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			oos.writeObject(cl.getCipher());
			oos.flush();
			oos.close();
			bos.close();

			byte[] data = bos.toByteArray();*/
			Connection con = sqlconn();
			String sql = "insert into Personal values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cl.getName());
			pst.setString(2, cl.getRegion());
			pst.setInt(3, cl.getUserid());
			//pst.setObject(4, data);
			pst.setString(4, cl.getN().toString());
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static boolean SaveAdminFiles(ClService cl) throws IOException {

		boolean flag = false;
		try {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			oos.writeObject(cl.getCipher());
			oos.flush();
			oos.close();
			bos.close();

			byte[] data = bos.toByteArray();
			Connection con = sqlconn();
			String sql = "insert into AdminFiles values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cl.getName());
			System.out.println(cl.getName());
			pst.setString(2, cl.getRegion());
			System.out.println(cl.getRegion());
			/*pst.setObject(3, data);
			System.out.println(data);*/

			pst.setString(3, cl.getN().toString());
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static int getServiceId(String region) {

		int serviceid = 0;
		try {
			Connection con = sqlconn();
			String sql = "select ServiceId from Service where RegionalNo=(select RegionalNo from Regional_Cloud where RegionName=?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, region);

			rs = pst.executeQuery();
			while (rs.next()) {
				serviceid = rs.getInt(serviceid);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return serviceid;
	}

	public static IBE getobject(int i) {
		IBE a = null;
		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from demo where id=1";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ByteArrayInputStream bais;

				ObjectInputStream ins;
				try {

					bais = new ByteArrayInputStream(rs.getBytes("obj"));
					ins = new ObjectInputStream(bais);
					a = (IBE) ins.readObject();
					// System.out.println("Object in value ::" + a.getFirst());
					ins.close();
				} catch (Exception e) {

					e.printStackTrace();
				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return a;
	}

	/*
	 * public static BFC getCipher(int id) throws IOException,
	 * ClassNotFoundException {
	 * 
	 * BFC text = null; try {
	 * 
	 * Connection con = sqlconn(); st = con.createStatement(); String sql =
	 * "select cipher from Personal where pid='" + id + "'"; rs =
	 * st.executeQuery(sql); while (rs.next()) { ByteArrayInputStream bais;
	 * ObjectInputStream ins;
	 * 
	 * bais = new ByteArrayInputStream(rs.getBytes("cipher")); ins = new
	 * ObjectInputStream(bais); text = (BFC) ins.readObject(); ins.close(); }
	 * 
	 * } catch (SQLException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return text; }
	 */

	/*
	 * public static Keys getServerPK(int uid) throws IOException,
	 * ClassNotFoundException {
	 * 
	 * Keys spk = null; try {
	 * 
	 * Connection con = sqlconn(); st = con.createStatement(); String sql =
	 * "select private_key from private_key where userid=(select ServerName " +
	 * "from Server s,UserDetails u,Regional_Cloud r where UID='" + uid +
	 * "' and u.region=r.RegionName " + "and r.RegionalNo=s.RegionalNo)"; rs =
	 * st.executeQuery(sql); while (rs.next()) { ByteArrayInputStream bais;
	 * ObjectInputStream ins;
	 * 
	 * bais = new ByteArrayInputStream(rs.getBytes("private_key")); ins = new
	 * ObjectInputStream(bais); spk = (Keys) ins.readObject(); ins.close();
	 * 
	 * }
	 * 
	 * } catch (SQLException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return spk; }
	 */
	public static List<ReportDetails> getReportData(String from, String to,
			int uid) {
		ArrayList<ReportDetails> FileList = new ArrayList<ReportDetails>();
		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select MeterReading,date from Data where UserID='"
					+ uid + "' and date between '" + from
					+ " 00:00:00.000' AND '" + to + " 00:00:00.000'";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ReportDetails u = new ReportDetails();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				u.setDate(df.format(rs.getDate("date")));
				u.setUsage(Integer.parseInt(rs.getString("MeterReading")));
				FileList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return FileList;
	}

	public static ArrayList<ClService> getAdminFiles() throws IOException,
			ClassNotFoundException {
		ArrayList<ClService> FileList = new ArrayList<ClService>();
		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from AdminFiles";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ClService u = new ClService();
				u.setRegion(rs.getString("bucket"));
				u.setName(rs.getString("filename"));
				u.setFileid(rs.getInt("fid"));
				u.setN(rs.getString("N"));

				FileList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return FileList;
	}

	public static String getPublicKey(String id) {
		String key = null;

		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from public_key where userid='" + id + "'";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				key = rs.getString("public_key");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return key;
	}

	public static String getPrivateKey(String id) {
		String key = null;

		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from private_key where userid='" + id + "'";
			//System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				key = rs.getString("private_key");

				//System.out.println("key" + key);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return key;
	}

	public static boolean admindeleteFile(String filenm, String region) {
		boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "delete from AdminFiles where filename='" + filenm
					+ "' and bucket='" + region + "'";
			System.out.println(sql);
			st.execute(sql);
			flag = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag;
	}

	public static boolean deleteFile(String filenm, String region, int uid) {
		boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "delete from Personal where filename='" + filenm
					+ "' and region='" + region + "' and userid='" + uid + "'";
			System.out.println(sql);
			st.execute(sql);
			flag = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag;
	}

	public static ArrayList<Complaints> getUserComplaints() throws IOException,
			ClassNotFoundException {
		ArrayList<Complaints> ComplaintList = new ArrayList<Complaints>();
		try {

			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from Complaints";
			System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Complaints u = new Complaints();
				u.setAddress(rs.getString("address"));
				u.setName(rs.getString("Name"));
				u.setComplaint(rs.getString("Message"));
				u.setConsumer(rs.getString("consumer"));
				u.setEmail(rs.getString("Email"));
				u.setPhone(rs.getString("phone"));
				u.setRegion(rs.getString("region"));
				u.setType(rs.getString("type"));
				ComplaintList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ComplaintList;
	}

	public static boolean AddNotice(String date, String msg) {
		// TODO Auto-generated method stub

		boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "insert into public_notice values(0,'" + date + "','"
					+ msg + "')";
			System.out.println(sql);
			st.executeUpdate(sql);
			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;

	}

	public static ArrayList<PublicNotice> GetNotice() {

		ArrayList<PublicNotice> NoticeList = new ArrayList<PublicNotice>();
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from public_notice";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				PublicNotice u = new PublicNotice();
				u.setDate(rs.getString("notice_date"));
				u.setMsg(rs.getString("notice_message"));
				NoticeList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return NoticeList;
	}

	public static int GetNewNoticeCount() {
		int count = 0;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select count(*) as count from public_notice where status=0";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				count = Integer.parseInt(rs.getString("count"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static boolean UpdateNoticeStatus() {

		boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "update public_notice set status=1";
			// System.out.println(sql);
			st.executeUpdate(sql);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean AddContactUS(String nm, String email, String msg,
			String phone) {
		boolean flag = false;
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "insert into contactus values('" + nm + "','" + phone
					+ "','" + email + "','" + msg + "')";
			System.out.println(sql);
			st.executeUpdate(sql);
			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	public static ArrayList GetContactUs() {

		ArrayList<Contactus> NoticeList = new ArrayList<Contactus>();
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select * from contactus";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Contactus u = new Contactus();
				u.setName(rs.getString("name"));
				u.setMessage(rs.getString("message"));
				u.setPhone(rs.getString("phone"));
				u.setEmail(rs.getString("email"));
				NoticeList.add(u);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return NoticeList;
	}

	public static String[] getBillDetails(String month, String year,String userid) {
		// TODO Auto-generated method stub
		
		String bill[] = new String[1];
		try {
			Connection con = sqlconn();
			st = con.createStatement();
			String sql = "select sum(MeterReading) as reading from Data	where UserID='"+userid+"'"
					+ " and datename(yyyy,fromdate)='"+year+"'"
					+ " and datepart(m,fromdate)='"+month+"'";
			// System.out.println(sql);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				bill[0] = rs.getString("reading");				

				// user[5]=rs.getString("")
				// System.out.println(name);
			}
			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return bill;		
	}

	public static boolean updateprofile(User cl) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		try {
			Connection con = sqlconn();
			String sql = "update UserDetails set fname=?,lname=?,phone=?,"
					+ "address=?,email=?,password=? where UID=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cl.getFnm());
			pst.setString(2, cl.getLnm());
			pst.setString(3, cl.getPhone());
			pst.setString(4, cl.getAddress());
			pst.setString(5, cl.getEmail());
			pst.setString(6, cl.getPwd());			
			pst.setInt(7, cl.getUId());
			pst.executeUpdate();
			flag = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
		
	}
}
