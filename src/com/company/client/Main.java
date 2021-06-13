package com.company.client;

import com.company.client.io.MBTerminal;

/**
 * @author Anna Mikhailova
 */

public class Main {

    public static void main(String[] args) {
        new MBTerminal(Integer.parseInt(args[0])).start();
    }

}
