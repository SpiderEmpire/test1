package controller;

import dal.Dbconnect;
import services.ClService;

public class CloudService {

	static boolean Upload(ClService cl) {
		Boolean flag = false;
		try {
			if (AmazonStorage.Uploadfile(cl.getName(),cl.getF(),cl.getRegion())) {

				if (Dbconnect.SavePersonal(cl)) {

					flag = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
