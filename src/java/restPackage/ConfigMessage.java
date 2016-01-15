/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restPackage;

/**
 *
 * @author Salah
 */
public class ConfigMessage {
    public String delay, messageSize;

    public ConfigMessage(String delay, String messageSize) {
        this.delay = delay;
        this.messageSize = messageSize;
    }
}