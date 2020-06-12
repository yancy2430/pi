package com.tdeado.pi.sensor;

import com.pi4j.io.gpio.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 多路继电器
 */
public class RelayMultiSensorService {
    private final GpioController gpio = GpioFactory.getInstance();
    private Pin[] pins =null;
    private List<GpioPinDigitalOutput> giopPins;
    public RelayMultiSensorService(Pin... pins) {
        giopPins = new ArrayList<>();
        this.pins = pins;
        for (Pin pin : pins) {
            giopPins.add(gpio.provisionDigitalOutputPin(pin,PinState.LOW));
        }
    }
    public RelayMultiSensorService(PinState state,Pin... pins) {
        giopPins = new ArrayList<>();
        this.pins = pins;
        for (Pin pin : pins) {
            giopPins.add(gpio.provisionDigitalOutputPin(pin,state));
        }
    }

    /**
     * 继电器开
     * @param num 继电器编号
     * @return
     */
    public boolean open(int num){
        giopPins.get(num).setState(PinState.HIGH);
        return true;
    }

    /**
     * 继电器关
     * @return
     */
    public boolean close(int num){
        giopPins.get(num).setState(PinState.LOW);
        return true;
    }
    /**
     * 继电器状态
     */
    public boolean state(int num){
        return giopPins.get(num).getState().isHigh();
    }
    /**
     * 卸载GPIO
     */
    public void unexport(){
        gpio.unexport(pins);
    }

}
