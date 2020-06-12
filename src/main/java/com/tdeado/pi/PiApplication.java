package com.tdeado.pi;

import com.pi4j.io.gpio.*;
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
        gpio.unexportAll();
        GpioPinPwmOutput in1 = gpio.provisionSoftPwmOutputPin(RaspiPin.GPIO_22);
        GpioPinDigitalOutput in2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, PinState.LOW);
        System.err.println("开始");
        for (int i = 1; i <= 100; i++) {
            in1.setPwm(i);
            System.err.println(i);
            TimeUnit.SECONDS.sleep(1);
        }
        in1.setPwm(0);
        in2.setState(PinState.LOW);
        gpio.unexportAll();
        System.err.println("结束");
    }

}
