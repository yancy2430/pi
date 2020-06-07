package com.tdeado.pi;

import com.pi4j.io.gpio.*;
import com.tdeado.pi.sensor.RelayMultiSensorService;
import com.tdeado.pi.sensor.WaterSensorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class PiApplication extends SpringBootServletInitializer implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(PiApplication.class);
    }
    @Override
    public void run(String... args) throws Exception {
        RelayMultiSensorService relayMultiSensorService = new RelayMultiSensorService(RaspiPin.GPIO_22,RaspiPin.GPIO_23,RaspiPin.GPIO_24,RaspiPin.GPIO_25);
        relayMultiSensorService.open(0);
        Thread.sleep(1000);
        relayMultiSensorService.open(1);
        Thread.sleep(1000);
        relayMultiSensorService.open(2);
        Thread.sleep(1000);
        relayMultiSensorService.open(3);
        Thread.sleep(1000);
        relayMultiSensorService.close(0);
        Thread.sleep(1000);
        relayMultiSensorService.close(1);
        Thread.sleep(1000);
        relayMultiSensorService.close(2);
        Thread.sleep(1000);
        relayMultiSensorService.close(3);
        Thread.sleep(1000);
        relayMultiSensorService.unexport();
    }

}
