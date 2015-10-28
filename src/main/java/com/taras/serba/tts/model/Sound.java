package com.taras.serba.tts.model;


import lombok.Data;

import java.io.File;

@Data
public class Sound {

    private File soundFile;

    private String name;

    public Sound(File file) {
        this.soundFile = file;
    }
}
