package com.piloto3.service;

import org.json.JSONObject;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service("Piloto3ServiceGeneral")
public class Piloto3ServiceGeneral {
	JSONObject jsonObject;

	public String metodoCompruebaCmd(String term) { // comprueba que el comando ha ejecutar esta en la lista
		String[] cmd_on = { "nmap", "ping", "tracert" };
		for (int i = 0; i < cmd_on.length; i++) {
			if (cmd_on[i] == term) {
				return "si";
			}
		}

		return "si";
	}

	public String metodoIpOrUrl(String testerIpUrl) { // ve si es formato ip o url
		String[] testIpUrl = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "x",
				"u", "w", "y", "z" }; // hay que poner espacio en blanco

		for (int i = 0; i < testIpUrl.length; i++) {
			if (testerIpUrl.toLowerCase().contains(testIpUrl[i])) {
				return "url";
			}
		}

		return "ip";
	}

	public String testIp(String ip) { // comprueba el formato de ip
		String onOff = "";
		if (ip.length() <= 18 && ip.length() >= 7) {

			if (ip.contains("/")) { // ve si la ip tiene mascara
				onOff = this.testIpMask(ip);
				if (onOff == "no") {
					System.out.println("sale;  ");
					return "no";
				} else {

					String miIp[] = this.spliter(ip, "/");

					ip = miIp[0];
				}

			}

			if (onOff == "no") {
				return "no";
			}

			String parseIp = ip;
			int count = org.springframework.util.StringUtils.countOccurrencesOf(parseIp, ".");

			String parts[] = this.spliter(parseIp, "\\.");

			if (count == 3 && parts.length == 4) {
				String the_ip = "";
				int counter = 0;
				for (String i : parts) {

					int foo;

					try {
						foo = Integer.parseInt(i);
					} catch (NumberFormatException e) {
						foo = 0;
						return "no";
					}

					if (foo >= 0 && foo <= 255) {
						if (counter < 3) {
							the_ip = the_ip + foo + ".";

						} else {
							the_ip = the_ip + foo;
						}

					} else {
						return "no";
					}
					counter++;

				}

			} else {
				return "no";
			}

			return parseIp.trim();

		} else {

			return "no";
		}

	}

	public String[] spliter(String dat, String spliteado) { // hace un split

		String[] parts = dat.split(spliteado);

		return parts;
	}

	public String testIpMask(String ip) { // si introducen una ip con mascara --> /24,,comprueba el formato de la
											// mascara
		String[] partsMap = spliter(ip, "/");

		String mask = partsMap[1];

		try {
			int foos = Integer.parseInt(mask);
			if (foos <= 24 && foos > 0) {

				return "si";
			} else {

				return "no";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "no";
	}

	public String testUrl(String url) { // comprueba el formato de la url
		String[] testurl = { "\'", "\"", "^", "[", "]", "¨", "|", "$", "*", ",", " ", "!", "¡", "<", ">", "º", "ª", "´",
				"á", "é", "í", "ó", "ú", "à", "è", "ì", "ò", "ù" }; // hay que poner espacio en blanco
		String url_lowercase = url.toLowerCase();
		for (int i = 0; i < testurl.length; i++) {

			if (url_lowercase.contains(testurl[i])) {
				return "no";
			}
		}

		return "si";
	}

}