package com.tdeado.pi.sensor;

import com.pi4j.io.gpio.*;

public class WaterSensorService {
    private final GpioController gpio = GpioFactory.getInstance();
    private Pin pin =null;
    GpioPinAnalogInput gp =null;
    public WaterSensorService(Pin pin) {
        this.pin = pin;
        gp = gpio.provisionAnalogInputPin(pin);
        gp.setMode(PinMode.ANALOG_INPUT);
    }
}
