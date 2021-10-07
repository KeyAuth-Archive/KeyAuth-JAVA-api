package org.spray.keyauth.api;

import org.json.JSONObject;
import org.spray.keyauth.user.UserData;
import org.spray.keyauth.util.HWID;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class KeyAuth extends KeyApi {

	public KeyAuth(String appname, String ownerid, String version, String url) {
		super(appname, ownerid, appname, url);
	}

	@Override
	public void login(String username, String password) {
		if (!initialized) {
			System.out.println("\n\n Please initzalize first");
			return;
		}

		HttpResponse<String> response;
		try {
			String hwid = HWID.getHWID();

			response = Unirest.post(url).field("type", "login").field("username", username).field("pass", password)
					.field("hwid", hwid).field("sessionid", sessionid).field("name", appname).field("ownerid", ownerid)
					.asString();

			try {
				JSONObject responseJSON = new JSONObject(response.getBody());

				if (!responseJSON.getBoolean("success")) {
					System.out.println("Error");
					// System.exit(0);
				} else {
					userData = new UserData(responseJSON);

					// optional success msg
				}

			} catch (Exception e) {

			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void upgrade(String username, String key) {
		if (!initialized) {
			System.out.println("\n\n Please initzalize first");
			return;
		}

		HttpResponse<String> response;
		try {
			String hwid = HWID.getHWID();

			response = Unirest.post(url).field("type", "upgrade").field("username", username).field("key", key)
					.field("hwid", hwid).field("sessionid", sessionid).field("name", appname).field("ownerid", ownerid)
					.asString();

			try {
				JSONObject responseJSON = new JSONObject(response.getBody());

				if (!responseJSON.getBoolean("success")) {
					System.out.println("Error");
					// System.exit(0);
				} else {

					// optional success msg
				}

			} catch (Exception e) {

			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void license(String key) {
		if (!initialized) {
			System.out.println("\n\n Please initzalize first");
			return;
		}

		HttpResponse<String> response;
		try {
			String hwid = HWID.getHWID();

			response = Unirest.post(url).field("type", "license").field("key", key).field("hwid", hwid)
					.field("sessionid", sessionid).field("name", appname).field("ownerid", ownerid).asString();

			try {
				JSONObject responseJSON = new JSONObject(response.getBody());

				if (!responseJSON.getBoolean("success")) {
					System.out.println("the license does not exist");
					// System.exit(0);
				} else {
					userData = new UserData(responseJSON);
					
					// optional success msg
				}

			} catch (Exception e) {

			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ban() {
		if (!initialized) {
			System.out.println("\n\n Please initzalize first");
			return;
		}

		HttpResponse<String> response;
		try {
			String hwid = HWID.getHWID();

			response = Unirest.post(url).field("type", "ban").field("sessionid", sessionid).field("name", appname)
					.field("ownerid", ownerid).asString();

			try {
				JSONObject responseJSON = new JSONObject(response.getBody());

				if (!responseJSON.getBoolean("success")) {
					System.out.println("Error");
					// System.exit(0);
				} else {

					// optional success msg
				}

			} catch (Exception e) {

			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void webhook(String webid, String param) {
		if (!initialized) {
			System.out.println("\n\n Please initzalize first");
			return;
		}

		HttpResponse<String> response;
		try {
			String hwid = HWID.getHWID();

			response = Unirest.post(url).field("type", "webhook").field("webid", webid).field("params", param)
					.field("sessionid", sessionid).field("name", appname).field("ownerid", ownerid).asString();

			try {
				JSONObject responseJSON = new JSONObject(response.getBody());

				if (!responseJSON.getBoolean("success")) {
					System.out.println("Error");
					// System.exit(0);
				} else {

					// optional success msg
				}

			} catch (Exception e) {

			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}
}
