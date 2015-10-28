package com.taras.serba.tts.service;

import com.taras.serba.tts.exceptions.TextTooLongException;
import com.taras.serba.tts.model.Sound;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SoundService {

    private static final String startURL = "http://translate.google.com/translate_tts?ie=UTF-8&q=";

    private static final String endURL = "&tl=ru";


    public static URL getSoundURL(final String text) throws TextTooLongException {

        if (text.length() > 90) throw new TextTooLongException();


        try {

            String buildedStringURL = startURL + URLEncoder.encode(text, "UTF-8") + endURL;

            return new URL(buildedStringURL);

        } catch (MalformedURLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Sound createSound(final URL url) {

        HttpURLConnection httpURLConnection = null;

        BufferedInputStream bufferedInputStream = null;

        BufferedOutputStream bufferedOutputStream = null;

        File file = null;

        int next = 0;


        try {

            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.connect();

            bufferedInputStream = new BufferedInputStream(url.openStream());

            file = new File(
                    "/home/taras/IdeaProjects/TTS/src/main/resources/sounds/"
                            + Math.random() + ".mp3");

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

            while ((next = bufferedInputStream.read()) != -1) {

                bufferedOutputStream.write(next);

            }

            bufferedInputStream.close();;
            bufferedOutputStream.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        return new Sound(file);

    }

    public static void main(String[] args) {
        String s = "hfkfkl flflf; ;;f;ffkfkf ";

        SoundService soundService = new SoundService();

        URL soundURL = SoundService.getSoundURL(s);

        System.out.println(soundURL);

        soundService.createSound(soundURL);
    }
}
