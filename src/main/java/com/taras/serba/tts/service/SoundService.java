package com.taras.serba.tts.service;

import com.taras.serba.tts.model.Sound;
import com.taras.serba.tts.model.TextToSpeechURL;
import com.taras.serba.tts.model.enums.Format;
import com.taras.serba.tts.model.enums.Lang;
import com.taras.serba.tts.model.enums.Speaker;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class SoundService {

    public Sound getSoundByURL(final URL url) {

        HttpURLConnection httpURLConnection = null;

        BufferedInputStream bufferedInputStream = null;

        BufferedOutputStream bufferedOutputStream = null;

        File file = null;

        int next = 0;


        try {

            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");

            httpURLConnection.connect();

            bufferedInputStream = new BufferedInputStream(url.openStream());

            file = new File(
                    "/home/taras/IdeaProjects/TTS/src/main/resources/sounds/"
                            + new Date().toString() + ".mp3");

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

            while ((next = bufferedInputStream.read()) != -1) {

                bufferedOutputStream.write(next);

            }

            bufferedInputStream.close();
            ;
            bufferedOutputStream.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        return new Sound(file);

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

    }
}
