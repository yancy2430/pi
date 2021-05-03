package com.tdeado.pi;

import com.pi4j.io.gpio.*;
import com.tdeado.pi.sensor.RelayMultiSensorService;
import com.tdeado.pi.sensor.StepperMotorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.concurrent.TimeUnit;
@Slf4j
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
            RelayMultiSensorService relayMultiSensorService = new RelayMultiSensorService(RaspiPin.GPIO_22,RaspiPin.GPIO_23,RaspiPin.GPIO_24,RaspiPin.GPIO_25);
            for (int i = 0; i < 4; i++) {
                log.info("{}",relayMultiSensorService.state(i));
                relayMultiSensorService.open(i);
                log.info("{}",relayMultiSensorService.state(i));
                Thread.sleep(1000);
                relayMultiSensorService.close(i);
                log.info("{}",relayMultiSensorService.state(i));
            }
        }finally {
            System.err.println("清理gpio");
            gpio.unexportAll();
        }
    }

}
