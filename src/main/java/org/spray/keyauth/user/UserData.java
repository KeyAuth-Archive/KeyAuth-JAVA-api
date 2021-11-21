package org.spray.keyauth.user;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserData {

	private String username;
	private String subscription;
	private String expiry;
	private String key;
	private UserStatus status;

	public UserData(JSONObject json, String key, String status) {
		JSONObject info = json.getJSONObject("info");

		JSONObject object = info.getJSONObject("subscriptions");
		JSONArray subArray = object.getJSONArray("subscriptions");
		JSONObject subObject = subArray.getJSONObject(0);
		
		this.username = object.getString("username");
		this.subscription = subObject.getString("subscription");
		this.expiry = subObject.getString("expiry");
		this.key = key;
		this.status = UserStatus.valueOf(status);
		
//		System.out.println("Username: " + username);
//		System.out.println("subscription: " + subscription);
//		System.out.println("expiry: " + expiry);
//		System.out.println("key: " + key);
//		System.out.println("status: " + status);
	}

	public String getUsername() {
		return username;
	}

	public String getSubscription() {
		return subscription;
	}

	public String getExpiry() {
		return expiry;
	}

	public String getKey() {
		return key;
	}
	
	public UserStatus getStatus() {
		return status;
	}
	
	public enum UserStatus {
		SUCCESS, ERROR;
	}

}
