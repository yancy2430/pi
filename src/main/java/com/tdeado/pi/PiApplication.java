package com.tdeado.pi;

import com.pi4j.io.gpio.*;
import com.tdeado.pi.sensor.StepperMotorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class PiApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(PiApplication.class);
    }
    public final GpioController gpio = GpioFactory.getInstance();
    @Override
    public void run(String... args) throws Exception {
        try {
            gpio.unexportAll();
            StepperMotorService stepperMotorService = new StepperMotorService(RaspiPin.GPIO_22,RaspiPin.GPIO_23,RaspiPin.GPIO_24,RaspiPin.GPIO_25);
            stepperMotorService.start();
            TimeUnit.SECONDS.sleep(20);
            stepperMotorService.stop();
            System.err.println("结束");
        }finally {
            gpio.unexportAll();
        }
    }

}
