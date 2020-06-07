package com.tdeado.pi.sensor;

import com.pi4j.io.gpio.*;

/**
 * 单路继电器
 */
public class RelaySensorService {
    private final GpioController gpio = GpioFactory.getInstance();
    private Pin pin =null;
    private GpioPinDigitalOutput giopPin;
    public RelaySensorService(Pin pin) {
        this.pin = pin;
        giopPin = gpio.provisionDigitalOutputPin(pin);
    }
    /**
     * 继电器开
     * @return
     */
    public boolean open(){
        giopPin.setState(PinState.HIGH);
        return true;
    }

    /**
     * 继电器关
     * @return
     */
    public boolean close(){
        giopPin.setState(PinState.LOW);
        return true;
    }
    /**
     * 继电器状态
     */
    public boolean state(){
        return giopPin.getState().isHigh();
    }
    /**
     * 卸载GPIO
     */
    public void unexport(){
        gpio.unexport(giopPin);
    }

}
