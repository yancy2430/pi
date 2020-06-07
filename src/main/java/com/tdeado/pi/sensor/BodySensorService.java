package com.tdeado.pi.sensor;


import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.tdeado.pi.PiUtil;

/**
 * 人体传感器
 */
public class BodySensorService {
    public final GpioController gpio = GpioFactory.getInstance();

    public BodySensorService(Pin pin){
        GpioPinDigitalInput gpioPinDigitalInput = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29);
        gpio.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {

            }
        });

    }

}
