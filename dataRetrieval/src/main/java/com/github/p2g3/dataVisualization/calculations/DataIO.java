package com.github.p2g3.dataVisualization.calculations;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.github.p2g3.dataVisualization.io.StorageVar;

/**
 * This class performs the majority of the data input/output operations,
 * including loading file from S3 bucket, and converting CSV to arraylists data
 * structure.
 * 
 * @author Revature Team 3 (Cynthia, Phuc, Christian, Brandon)
 * @version 0.1.0
 *
 */
public class DataIO {
	private ArrayList<Double> x = new ArrayList<Double>(0); // **
	private ArrayList<Double> y = new ArrayList<Double>(0); // **
	static String xTitle; // **
	static String yTitle; // **
	String bucketName = "project-2-group-3-bucket-cbpc"; // **
	private ArrayList<String> allKeys = new ArrayList<String>(0);
	static String key;
	StorageVar saveVar;

	/**
	 * This class performs input from the S3 bucket, and sets the independent and
	 * dependent data list.
	 * 
	 * @param aKey S3 Access Key
	 * @param sKey S3 Secret Key
	 */
	public StorageVar S3List(String aKey, String sKey) {
		AWSCredentials credentials = new BasicAWSCredentials(aKey, sKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build();

		ObjectListing objectListing = s3client.listObjects("project-2-group-3-bucket-cpbc");
		for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
			this.allKeys.add(os.getKey());
		}

		S3Object fullObject = null;
		int index = helloKey(this.allKeys);
		DataIO.key = this.allKeys.get(index);
		fullObject = s3client.getObject(new GetObjectRequest("project-2-group-3-bucket-cpbc", DataIO.key));

		BufferedReader reader = new BufferedReader(new InputStreamReader(fullObject.getObjectContent()));
		String line;
		int count = 0;
		
		try {
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (count == 0) {
					saveVar = new StorageVar(DataIO.yTitle = values[1].toString(), DataIO.xTitle = values[0].toString());
					count++;
				} else {
					Double temp = Double.valueOf(values[0]);
					Double temp2 = Double.valueOf(values[1]);
					this.x.add(temp);
					this.y.add(temp2);
				}
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saveVar;
	}

	/**
	 * This method takes in an arraylist of object names from the S3, filters
	 * through all items, and returns csv located inside the "hello" folder.
	 * 
	 * @param allKeys2, All the object keys located within the specified S3 bucket
	 * @return the integer index of the object key of interest
	 */
	private int helloKey(ArrayList<String> allKeys2) {
		// TODO Auto-generated method stub
		int cntr = 0;
		int index = 0;
		for (String string : allKeys2) {
			if (string.matches("(.*)(hello.csv/p)(.*)")) {
				index = cntr;
			}
			cntr++;
		}
		return index;
	}
	// getters/setters methods for private variables
	public ArrayList<Double> getX() {
		return this.x;
	}

	public void setX(ArrayList<Double> x) {
		this.x = x;
	}

	public ArrayList<Double> getY() {
		return this.y;
	}

	public void setY(ArrayList<Double> y) {
		this.y = y;
	}

	public String getxTitle() {
		return xTitle;
	}

	public String getyTitle() {
		return yTitle;
	}

}
