package com.jerry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
public class MySpringBootPlaygroundApplication {

	private volatile int a = 0;

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootPlaygroundApplication.class, args);
	}

	@GetMapping("/hello")
	public String test () {
		synchronized (this){
			System.out.println("当前实例："+this + "  当前线程:" +
					Thread.currentThread().getName()+"  变量值：" + a++);
		}
		return "Hello";
	}
}
