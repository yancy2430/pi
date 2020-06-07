package com.tdeado.pi.sensor;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * 超声波雷达模块
 */
public class UltrasonicSensorService {
    private final GpioController gpio = GpioFactory.getInstance();
    private Pin trig =null;
    private Pin echo =null;
    private GpioPinDigitalOutput trigGiopPin;
    private GpioPinDigitalInput echoGiopPin;

    public UltrasonicSensorService(Pin trig,Pin echo) {
        this.trig = trig;
        this.echo = echo;
        trigGiopPin = gpio.provisionDigitalOutputPin(trig);
        echoGiopPin = gpio.provisionDigitalInputPin(echo);
    }

    public long ranging() throws InterruptedException {
        trigGiopPin.setState(PinState.HIGH);
        TimeUnit.MICROSECONDS.sleep(10);
        trigGiopPin.setState(PinState.LOW);
        long end = 0;
        long start = 0;
        while (echoGiopPin.getState().isLow()){
             start = System.nanoTime();
        }
        while (echoGiopPin.getState().isHigh()){
             end =System.nanoTime();
        }
        long cost=end-start;// 为什么是准确的呢？
        gpio.unexport(trig,echo);
        return new BigDecimal(0.000340).multiply(new BigDecimal(cost)).divide(new BigDecimal(2)).setScale(0,ROUND_HALF_UP).longValue();
    }

}
