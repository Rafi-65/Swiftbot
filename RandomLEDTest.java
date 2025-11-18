package com.pi4j.example;

import com.pi4j.io.gpio.digital.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class RandomLEDTest {

    @Test
    public void testRandomBlink() throws Exception {
        DigitalOutput mockLed = Mockito.mock(DigitalOutput.class);

        RandomLED randomLED = new RandomLED(mockLed);

        randomLED.randomBlink();

        // At least once ON and OFF
        verify(mockLed, atLeastOnce()).high();
        verify(mockLed, atLeastOnce()).low();
    }
}
