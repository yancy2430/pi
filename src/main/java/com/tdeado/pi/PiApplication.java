package com.tdeado.pi;

import ch.qos.logback.core.util.TimeUtil;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.tdeado.pi.sensor.RelaySensorService;
import com.tdeado.pi.sensor.UltrasonicSensorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.math.BigDecimal;
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
        System.err.println("开始");
        UltrasonicSensorService ultrasonicSensorService = new UltrasonicSensorService(RaspiPin.GPIO_24,RaspiPin.GPIO_25);
        for (int i = 0; i < 5; i++) {
            long nm = ultrasonicSensorService.ranging();
            System.err.println("计算结果 距离"+nm+"毫米");
            System.err.println("计算结果 距离"+nm/10+"厘米");
            TimeUnit.SECONDS.sleep(5);
        }
        gpio.unexportAll();
        System.err.println("结束测量");
    }

}
