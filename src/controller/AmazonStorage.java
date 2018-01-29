package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AmazonStorage {

	public static String keyName = null;
	private static String bucketName = "automaticmeter";
	private static String accessKey = "AKIAJSXK4BOMD22BKLDA";
	private static String secretKey = "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";
	static boolean flag = false;

	public static void main(String[] args) {
		String filenm = "Dipali.txt";
		String filepath = "E\\abc\\dip.txt";
		if (AmazonStorage.deleteFile(filenm, "admin")) {
			System.out.println("done");
		}
	}

	static boolean uploadUsage(int id, String filenm, File file, String rn) {

		try {
			keyName = rn+"/"+Integer.toString(id) + ".txt";
			//bucketName = rn;
			// uploadFileName=rn+"\\"+filenm;
			AWSCredentials credentials = new BasicAWSCredentials(accessKey,
					secretKey);

			AmazonS3 s3client = new AmazonS3Client(credentials);

			s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
			System.out.println("done123");
			flag = true;

		} catch (AmazonServiceException aws) {
			System.out.println("Error massage" + aws.getMessage());
		} catch (AmazonClientException ace) {
			System.out.println("Error massage client " + ace.getMessage());
		}
		return flag;

	}
	
	static boolean downloadusageFile(String region, String filenm, String filepath) {
		try {
			AWSCredentials credentials = new BasicAWSCredentials(accessKey,
					secretKey);
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			String keynm=region+"/"+filenm;
			s3client.getObject(new GetObjectRequest(bucketName, keynm),
					new File(filepath));
			flag = true;
			System.out.println("file downloded from s3");
		}

		catch (AmazonServiceException aws) {
			System.out.println("Error massage" + aws.getMessage());
		}

		catch (AmazonClientException ace) {
			System.out.println("Error massage client " + ace.getMessage());
		}
		return flag;
	}


	static boolean downloadFile(String region, String filenm, String filepath) {
		try {
			AWSCredentials credentials = new BasicAWSCredentials(accessKey,
					secretKey);
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			String keynm=region+"/"+filenm;
			s3client.getObject(new GetObjectRequest(bucketName, keynm),
					new File(filepath));
			flag = true;
			System.out.println("file downloded from s3");
		}

		catch (AmazonServiceException aws) {
			System.out.println("Error massage" + aws.getMessage());
		}

		catch (AmazonClientException ace) {
			System.out.println("Error massage client " + ace.getMessage());
		}
		return flag;
	}

	static boolean Uploadfile(String filenm, File file, String rn) {
		Boolean flag = false;
		// bucketName = rn;

		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		// s3client.setRegion(Region.getRegion(Regions.US_WEST_2));
		try {

			keyName = rn + "/" + filenm;
			// bucketName = rn;
			// uploadFileName=rn+"\\"+filenm;
			System.out.println(bucketName + keyName);
			s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
			System.out.println("done123");
			flag = true;

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which "
					+ "means your request made it "
					+ "to Amazon S3, but was rejected with an error response"
					+ " for some reason.");

		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which "
					+ "means the client encountered "
					+ "an internal error while trying to "
					+ "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

		return flag;
	}

	static boolean CreateRegionBucket(String rn) {
		Boolean flag = false;
		bucketName = rn;

		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3client = new AmazonS3Client(credentials);
		s3client.setRegion(Region.getRegion(Regions.US_WEST_2));

		try {
			if (!(s3client.doesBucketExist(bucketName))) {
				s3client.createBucket(new CreateBucketRequest(bucketName));
				flag = true;
			} else {
				System.out.println("already exist");
				flag = false;
			}

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which "
					+ "means your request made it "
					+ "to Amazon S3, but was rejected with an error response"
					+ " for some reason.");

		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which "
					+ "means the client encountered "
					+ "an internal error while trying to "
					+ "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}

		return flag;
	}

	static boolean createFolder(String folderName) {
		boolean flag = false;
		try {

			AWSCredentials credentials = new BasicAWSCredentials(accessKey,
					secretKey);
			AmazonS3 s3client = new AmazonS3Client(credentials);
			// s3client.setRegion(Region.getRegion(Regions.US_WEST_2));
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);

			InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					bucketName, folderName + "/", emptyContent, metadata);
			
			if (!(s3client.doesObjectExist(bucketName, folderName))) {

				s3client.putObject(putObjectRequest);
				flag = true;
			} else {
				System.out.println("already exist");
				flag = false;
			}		
			
			System.out.println("region created in cloud");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	static boolean deleteFile(String filenm,String rn)
	{
		boolean flag=false;
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
		AmazonS3 s3Client = new AmazonS3Client(credentials);
	        try {
	        	keyName=rn+"/"+filenm;
	            s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
	            System.out.println("delete done");
	            flag=true;
	        } catch (AmazonServiceException ase) {
	            System.out.println("Caught an AmazonServiceException.");
	            System.out.println("Error Message:    " + ase.getMessage());
	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
	            System.out.println("Error Type:       " + ase.getErrorType());
	            System.out.println("Request ID:       " + ase.getRequestId());
	        } catch (AmazonClientException ace) {
	            System.out.println("Caught an AmazonClientException.");
	            System.out.println("Error Message: " + ace.getMessage());
	        }
		return flag;
		
	}
}
