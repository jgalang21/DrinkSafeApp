package org.springframework.samples.drink_safe.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Welcome controller, first page a user sees
 * 
 * @author Jeremy and Nick
 *
 */
@RestController
class WelcomeController {

	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
}
