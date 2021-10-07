package org.spray.keyauth;

import java.util.Scanner;

import org.spray.keyauth.api.KeyApi;
import org.spray.keyauth.api.KeyAuth;

public class Application {

	private static String url = "https://keyauth.com/api/1.1/";
	
	private static String ownerid = ""; // You can find out the owner id in the profile settings keyauth.com
	private static String appname = ""; // Application name
	private static String version = ""; // Application version

	private static KeyApi keyapi = new KeyAuth(appname, ownerid, version, url);

	public static void main(String[] args) throws InterruptedException {
		println("KeyAuth API Example");
		println("\n\n Connecting...");
		keyapi.init();

		println("\n\n [1] Login\n [2] Upgrade\n [3] License key only\n\n Choose option: ");

		Scanner scanner = new Scanner(System.in);

		String username;
		String password;
		String key;

		int option = Integer.parseInt(scanner.nextLine());
		switch (option) {
		case 1:
			println("\n\n Enter username: ");
			username = scanner.nextLine();
			println("\n\n Enter password: ");
			password = scanner.nextLine();

			keyapi.login(username, password);
			break;
		case 2:
			println("\n\n Enter username: ");
			username = scanner.nextLine();
			println("\n\n Enter license key: ");
			key = scanner.nextLine();

			keyapi.upgrade(username, key);
			break;
		case 3:
			println("\n\n Enter license key: ");
			key = scanner.nextLine();

			keyapi.license(key);
			break;
		default:
			println("\n\n Invalid Selection");
			Thread.sleep(3000);
			System.exit(0);
			break;
		}

		println("\n\n  Exit the program...");

		Thread.sleep(3200);
		System.exit(0);
	}

	private static void println(String text) {
		System.out.println(text);
	}

}
