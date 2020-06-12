package com.tdeado.pi.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class StepperMotorService {
    private final GpioController gpio = GpioFactory.getInstance();

    public StepperMotorService() {

    }
}
