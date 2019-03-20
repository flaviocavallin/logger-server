package com.example.logfileserver.services;

import java.io.OutputStream;

public interface OutputStreamManager {

    OutputStream getOutputStream();

    boolean activateNextOutputStream();

    OutputStream getNextOutputStream();

}
