package com.example.evlrhawk.digitaljukebox;

import android.bluetooth.BluetoothAdapter;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class bluetoothTest {

    @Test
    public void blueToothTest(){
        BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();

        assertEquals(true, BA);
    }
}
