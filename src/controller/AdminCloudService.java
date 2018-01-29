package controller;

import services.ClService;
import dal.Dbconnect;

public class AdminCloudService {
	
	static boolean Upload(ClService cl) {
		Boolean flag = false;
		try {
			if (AmazonStorage.Uploadfile(cl.getName(),cl.getF(),cl.getRegion())) {

				if (Dbconnect.SaveAdminFiles(cl)) {

					flag = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
