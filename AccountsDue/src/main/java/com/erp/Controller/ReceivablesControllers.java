package com.erp.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class ReceivablesControllers {
	
	
	@GetMapping("/receivables")
	public String index() {
		return "Receivables";
	}
	
}
