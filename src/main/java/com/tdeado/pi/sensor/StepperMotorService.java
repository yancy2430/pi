package com.tdeado.pi.sensor;

import com.pi4j.io.gpio.*;

import java.util.ArrayList;
import java.util.List;

public class StepperMotorService {
    private final GpioController gpio = GpioFactory.getInstance();
    private List<GpioPinDigitalOutput> gpioPinDigitalOutputs = new ArrayList<>();

    private int delay = 10;
    private Pin[] pins;

    public StepperMotorService(Pin... pins) {
        this.pins = pins;
        for (Pin pin : pins) {
            gpioPinDigitalOutputs.add(gpio.provisionDigitalOutputPin(pin, PinState.LOW));
        }

    }

    public void start(int num) throws InterruptedException {
        try {
            for (int i = 0; i < num; i++) {
                forward();
            }
            System.err.println("转动了1次");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws InterruptedException {
        gpio.unexport(pins);
    }

    public void forward() throws InterruptedException {
        setStep(true, false, false, false);
        Thread.sleep(delay);
        setStep(false, true, false, false);
        Thread.sleep(delay);
        setStep(false, false, true, false);
        Thread.sleep(delay);
        setStep(false, false, false, true);
        Thread.sleep(delay);
    }

    public void setStep(boolean w1, boolean w2, boolean w3, boolean w4) {
        gpioPinDigitalOutputs.get(0).setState(w1);
        gpioPinDigitalOutputs.get(1).setState(w2);
        gpioPinDigitalOutputs.get(2).setState(w3);
        gpioPinDigitalOutputs.get(3).setState(w4);
    }

}
