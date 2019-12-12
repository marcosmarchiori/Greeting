package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

		User quote = restTemplate.getForObject("http://5def950d02b2d90014e1b5b0.mockapi.io/users/1", User.class);
		System.out.println(quote.toString());

		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping(value = "/transformar")
	public Usuario transformar(@RequestParam(value = "id", defaultValue = "1") String id) {

		User user = restTemplate.getForObject("http://5def950d02b2d90014e1b5b0.mockapi.io/users/" + id, User.class);
		System.out.println(user.toString());

		Usuario usuario = new Usuario();
		usuario.setNombre(user.getName());
		usuario.setCreado(user.getCreatedAt());
		
		return usuario;
	}
}
