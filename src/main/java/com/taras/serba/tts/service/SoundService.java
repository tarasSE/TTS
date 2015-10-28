package com.taras.serba.tts.service;

import com.taras.serba.tts.model.Sound;
import com.taras.serba.tts.model.TextToSpeechURL;
import com.taras.serba.tts.model.enums.Format;
import com.taras.serba.tts.model.enums.Lang;
import com.taras.serba.tts.model.enums.Speaker;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class SoundService {

    public Sound getSoundByURL(final URL url) {

        File file = new File("/home/taras/IdeaProjects/TTS/src/main/resources/sounds/" + new Date().toString() + ".mp3");
        try {
            FileUtils.copyURLToFile(url, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Sound(file);

    }

    public void playSoundFile(){


    }

    public static void main(String[] args) {

        SoundService soundService = new SoundService();

        TextToSpeechURL textToSpeechURL = new TextToSpeechURL.Builder()
                .format(Format.MP3)
                .lang(Lang.RU)
                .speaker(Speaker.MALE_ERMIL)
                .robot(false)
                .text("Это самый обычный текст")
                .build();

        URL soundURL = textToSpeechURL.toURL();

        System.out.println(soundURL);

        soundService.getSoundByURL(soundURL);

    }
}
