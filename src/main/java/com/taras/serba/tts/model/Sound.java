package com.taras.serba.tts.model;


import lombok.Data;

import java.io.File;

@Data
public class Sound {

    private File sound;

    private String name;

    public Sound(File sound) {
        this.sound = sound;
    }
}
