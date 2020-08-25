package br.com.guilherme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WsAccountmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsAccountmanagementApplication.class, args);
	}

}
