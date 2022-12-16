package com.piloto3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Piloto3Controller {

	@GetMapping("/nmap-web")
	public String nmap(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		// model.addAttribute("name", name);
		return "nmap-web";
	}

	@GetMapping("/whatweb-web")
	public String whatweb(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "whatweb-web";
	}

	@GetMapping("/urlcrazy-web")
	public String urlcrazy(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "urlcrazy-web";
	}
	
	@GetMapping("/traceroute-web")
	public String traceroute(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "traceroute-web";
	}
	
	
	@GetMapping("/wpscan-web")
	public String wpscan(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "wpscan-web";
	}

	@GetMapping("/nikto-web")
	public String nikto(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "nikto-web";
	}

	/*
	 * 
	 * @GetMapping("/sqliv-web") public String sqliv(@RequestParam(name = "name",
	 * required = false, defaultValue = "World") String name, Model model) {
	 * 
	 * return "sqliv-web"; }
	 */

	@GetMapping("/whois-web")
	public String whois(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "whois-web";

	}

	@GetMapping("/theharvester-web")
	public String theharvester(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "theharvester-web";
	}

	@GetMapping("/sherlock-web")
	public String sherlock(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {

		return "sherlock-web";
	}

}
