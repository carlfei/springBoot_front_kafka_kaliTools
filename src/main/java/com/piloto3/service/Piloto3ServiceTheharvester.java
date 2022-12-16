package com.piloto3.service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.piloto3.model.MensajeriaTheharvester;

@EnableAsync
@Service("Piloto3ServiceTheharvester")
public class Piloto3ServiceTheharvester {

	@Async
	public String engine(String command, MensajeriaTheharvester obj, ArrayList<MensajeriaTheharvester> users) {// el
																												// procedimiento
		try {
			long timer_start = (Instant.now().getEpochSecond() / 60);

			String line = "";
			String id = obj.getUserId();
			obj.setStoper(0);
			obj.setTimer(timer_start + 3);
			
			Process proc = Runtime.getRuntime().exec(command.trim());
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(proc.getInputStream(), System.getProperty("file.encoding")));
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);

				if (obj.getStoper() == 1) {
					System.out.println(" el break ");

					break;
				}

				if (line.length() > 3) {
					saleDbucle(line, id, users);
				}

				if ((Instant.now().getEpochSecond() / 60) >= (obj.getTimer())) {
					System.out.println(" el timer break ");

					break;
				}

			}

			if (obj.getStoper() == 0) {
				saleDbucle("0", id, users);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "";
		
	}

	String mensaje = "";

	@Async
	public void saleDbucle(String datos, String id, ArrayList<MensajeriaTheharvester> users) { // los datos que van
																								// saliendo del
																								// procedimiento cmd
		int este_user = 0;
		if (users != null) {
			for (int i = 0; i < users.size(); i++) {
				if ((users.get(i).getUserId()).equals(id)) {
					este_user = i;
					i = users.size();

				}
			}
		}

		if (mensaje != datos) {

			if (datos != "0" && datos != "1") {
				mensaje = mensaje + datos + "\n\r&nbsp;";

				users.get(este_user).setPaquete(mensaje);
				users.get(este_user).setEndSend("1");

			} else if (datos == "0") { // ultima linea
				mensaje = mensaje + "\n\n\r&nbsp;";

				users.get(este_user).setPaquete(mensaje);
				users.get(este_user).setEndSend("0");

			} else if (datos == "1" && id != "0") {

				users.get(este_user).setPaquete("");
				users.get(este_user).setEndSend("");

				users.remove(este_user);
				mensaje = "\n\n\r";

			}

		}

	}

}