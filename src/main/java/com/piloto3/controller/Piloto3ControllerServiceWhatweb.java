package com.piloto3.controller;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.piloto3.model.DataNet;

import com.piloto3.model.MensajeriaWhatweb;

import com.piloto3.service.Piloto3ServiceGeneral;
import com.piloto3.service.Piloto3ServiceWhatweb;

@RestController
public class Piloto3ControllerServiceWhatweb {

	String test = "";
	JSONObject jsonObject;

	@Autowired
	Piloto3ServiceWhatweb piloto3ServiceWhatweb;

	@Autowired
	MensajeriaWhatweb mensajeriaWhatweb;

	@Autowired
	Piloto3ServiceGeneral piloto3ServiceGeneral;

	@Autowired
	DataNet dataNet;

	int dataAjax = 0;
	String userId = "";

	ArrayList<MensajeriaWhatweb> allusers = new ArrayList<MensajeriaWhatweb>();

	@RequestMapping(value = "whatweb_service", method = RequestMethod.POST)
	public void doPostWhatwebrouter(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {

		String ping = "ping -a 8.8.8.8";

		if (req != null && "POST".equalsIgnoreCase(req.getMethod())) {
			test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));// depura el dato de
																								// entrada
		}

		if (test.length() > 10 && test.length() < 40) {

			String[] partsStop = test.split(";");

			partsStop[0] = partsStop[0] + '"';

			int esta_user_stop = 0;
			if (allusers != null) {
				for (int i = 0; i < allusers.size(); i++) {
					if ((allusers.get(i).getUserId()).equals(partsStop[0])) {

						esta_user_stop = i;
						i = allusers.size();

					}
				}
			}

			allusers.get(esta_user_stop).setStoper(1);
			this.metodoEnd(allusers.get(esta_user_stop).getUserId(), allusers);

		}

		if (test.length() == 10) { // el identificador desde la segunda llamada

			int esta_user = 0;
			if (allusers != null) {
				for (int i = 0; i < allusers.size(); i++) {
					if ((allusers.get(i).getUserId()).equals(test)) {
						esta_user = i;
						i = allusers.size();

					}
				}
			}

			int sale = 0;

			if (allusers.get(esta_user).getEndSending() == "1") {

				String[] partsData = allusers.get(esta_user).getTotalPaquete().split("&nbsp;");

				String cadenaData = "";

				if (partsData.length > 0) {

					dataAjax = allusers.get(esta_user).getNumerIndex();

					for (int s = dataAjax; s < partsData.length; s++) {

						cadenaData = cadenaData + partsData[s];

					}

					allusers.get(esta_user).setNumerIndex(partsData.length);

					cadenaData = cadenaData + " \r";

				}

				res.getWriter().write(cadenaData);
				res.getWriter().flush();

			}

			if (allusers.get(esta_user).getEndSending() == "0" && sale == 0) { // ha terminado el proceso

				String cadenaData2 = "";

				String[] partsData2 = allusers.get(esta_user).getTotalPaquete().split("&nbsp;");

				if (partsData2.length > 0) {

					for (int x = dataAjax; x < partsData2.length; x++) {

						cadenaData2 = cadenaData2 + partsData2[x];

					}

				}
				dataAjax = partsData2.length;

				res.getWriter().write(cadenaData2);

				res.getWriter().flush();

				sale = 1;

				this.metodoEnd(allusers.get(esta_user).getUserId(), allusers); // finaliza el servicio

			}

		} else if (test.length() > 45) {

			System.out.println(" hola ");
			System.out.println(" se inicia el srv : ");

			System.out.println(Runtime.getRuntime().totalMemory());
			System.out.println(Runtime.getRuntime().freeMemory());

			if (this.metodoOperador(test, ping) == "no" || piloto3ServiceGeneral.testUrl(dataNet.getIp()) == "no") {

				res.getWriter().write("fail_address");

				this.metodoEnd("-", allusers);

			} else {

				allusers.add(new MensajeriaWhatweb());
				allusers.get(allusers.size() - 1).setUserId('"' + userId + '"');
				allusers.get(allusers.size() - 1).setNumerIndex(0);
				//String command = "ping -a 8.8.8.8";
				// command = term + " " + cadenaCommands + " " +
				// ((dataNet).getIp()).toLowerCase();
				String command = "sudo docker run -t alexandreoda/whatweb whatweb " +dataNet.getIp();
				// command = "ping -a 8.8.8.8"; sudo docker run -t theyahya/sherlock user123
				// String command = "sudo docker run -t frapsoft/nikto -host " +dataNet.getIp();
				// dataNet.getIp();
				// command ="sudo docker run -t theyahya/sherlock " +
				// ((dataNet).getIp()).toLowerCase();
				// command ="sudo docker run -t theyahya/sherlock user1";

				ExecutorService service = Executors.newFixedThreadPool(4);
				service.submit(new Runnable() {
					public void run() {

						piloto3ServiceWhatweb.engine(command, allusers.get(allusers.size() - 1), allusers);

					}
				}); // para que se ejecute el while en background

			}

		}

	}

	@Async
	public String metodoOperador(String test, String term) {///////////////////////////////////////////////////////////////////

		mensajeriaWhatweb.setOperator(mensajeriaWhatweb); // manda el objeto para que opere desde el

		this.metodoOperadorService(test); // servicio

		/*
		 * /////////////////////////////////////////////////// String procesa = "";
		 * String tipoDato = ""; tipoDato =
		 * piloto3ServiceGeneral.metodoIpOrUrl(((dataNet)).getIp()); // saber si es ip p
		 * url
		 * 
		 * int no_work = 0;
		 * 
		 * if (tipoDato == "ip" && piloto3ServiceGeneral.testIp((dataNet).getIp()) !=
		 * "no") { System.out.println("el dato es:  " + tipoDato); procesa = "ip";
		 * no_work = 1; } if (tipoDato == "url"
		 * 
		 * && piloto3ServiceGeneral.testUrl(dataNet.getIp()) == "si") { procesa = "url";
		 * no_work = 1; }
		 * 
		 * if (no_work == 0) { return "no"; }
		 * 
		 * if (procesa == "url" || procesa == "ip") {
		 * 
		 * String[] partsCommand = (piloto3ServiceGeneral).spliter(dataNet.getCommand(),
		 * ";");// coge los parametros // del comando separados // por ; en el json
		 * 
		 * String cadenaCommands = ""; for (String i : partsCommand) {
		 * 
		 * cadenaCommands = cadenaCommands + " " + i; // coge al maximo 3 comandos
		 * 
		 * }
		 * 
		 * } else { return "no"; }
		 * 
		 */
//////////////////////////////////////////////////////////////////////////////////////////
		return "si";
	}

	public void metodoEnd(String id, ArrayList<MensajeriaWhatweb> users) { // finaliza el servicio

		System.out.println(" finaliza el srv : ");
		System.out.println(Runtime.getRuntime().totalMemory());
		System.out.println(Runtime.getRuntime().freeMemory());

		if (id != "-") {
			piloto3ServiceWhatweb.saleDbucle("1", id, users); // manda vaciar y eliminar el objeto
		}

		System.gc();
		System.out.println(" se limpia el srv final: ");
		System.out.println(Runtime.getRuntime().totalMemory());
		System.out.println(Runtime.getRuntime().freeMemory());

	}

	public void metodoOperadorService(String test) {

		System.out.println(test);

		try {

			jsonObject = new JSONObject(test);

			dataNet.setIp(jsonObject.getString("ip"));
			dataNet.setCommand(jsonObject.getString("command"));
			dataNet.setTxt(jsonObject.getString("tx"));
			dataNet.setUser_id(jsonObject.getString("user_id"));
			System.out.println("ip:   " + dataNet.getIp() + " command: " + dataNet.getCommand() + "" + " txt: "
					+ dataNet.getTxt() + " user_id  " + dataNet.getUser_id());
			userId = dataNet.getUser_id();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
