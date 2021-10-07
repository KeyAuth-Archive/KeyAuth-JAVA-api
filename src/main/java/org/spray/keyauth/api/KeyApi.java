package org.spray.keyauth.api;

import org.json.JSONObject;
import org.spray.keyauth.user.UserData;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public abstract class KeyApi {

	public final String appname;
	public final String ownerid;
	public final String version;
	public final String url;

	protected String sessionid;
	protected boolean initialized;

	protected UserData userData;

	public KeyApi(String appname, String ownerid, String version, String url) {
		this.appname = appname;
		this.ownerid = ownerid;
		this.version = version;
		this.url = url;
	}

	public UserData getUserData() {
		return userData;
	}

	public void init() {
		HttpResponse<String> response;
		try {
			response = Unirest.post(url).field("type", "init").field("ver", version).field("name", appname)
					.field("ownerid", ownerid).asString();

			try {
				JSONObject responseJSON = new JSONObject(response.getBody());

				if (response.getBody().equalsIgnoreCase("KeyAuth_Invalid")) {
					// Ñalling the method with a disabled connection
					// System.exit(0);
					System.out.println("invalid");
				}

				if (responseJSON.getBoolean("success")) {
					sessionid = responseJSON.getString("sessionid");
					initialized = true;
					System.out.println("Session ID: " + responseJSON.getString("sessionid"));

				} else if (responseJSON.getString("message").equalsIgnoreCase("invalidver")) {
					// Calling the method with a disabled version
					// System.out.println(reponseJSON.getString("download"));

				} else {
					System.out.println(responseJSON.getString("message"));
					// System.exit(0);
				}

			} catch (Exception e) {

			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	public abstract void login(String username, String password);

	public abstract void upgrade(String username, String key);

	public abstract void license(String key);

	public abstract void ban();

	public abstract void webhook(String webid, String param);

}
