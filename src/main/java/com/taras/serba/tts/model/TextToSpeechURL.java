package com.taras.serba.tts.model;

import com.taras.serba.tts.exceptions.UnknownItemException;
import com.taras.serba.tts.model.enums.Format;
import com.taras.serba.tts.model.enums.Lang;
import com.taras.serba.tts.model.enums.Speaker;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Getter
@EqualsAndHashCode
public class TextToSpeechURL {

    private final String startURL;

    private final String keyAPI;

    private final String format;

    private final String lang;

    private final String speaker;

    private final String robot;

    private final String text;

    public TextToSpeechURL(final Builder ttsURL) {

        this.startURL = ttsURL.getStartURL();

        this.keyAPI = ttsURL.getKeyAPI();

        this.format = ttsURL.getFormat();

        this.lang = ttsURL.getFormat();

        this.speaker = ttsURL.getSpeaker();

        this.text = ttsURL.getText();

        this.robot = ttsURL.getRobot();

    }

    @Override
    public String toString() {
        return startURL + format + lang + speaker + robot + keyAPI + text;
    }

    public URL toURL() {

        try {
            return new URL(toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        throw new UnknownItemException();
    }

    @Getter
    public static class Builder {

        private String startURL = "https://tts.voicetech.yandex.net/generate?";

        private String keyAPI = "&key=48b1317e-ecba-4a2f-ae72-9de4cc6ee7d9";

        private String lang = "&lang=";

        private String format = "&format=";

        private String speaker = "&speaker=";

        private String robot = "&robot=";

        private String text = "&text=";

        public Builder lang(final Lang lang) {

            this.lang += lang == Lang.RU ? "ru-RU" : "todo"; // todo in future

            return this;
        }

        public Builder format(final Format format) {

            String formatStr = null;

            switch (format) {

                case MP3:

                    formatStr = "mp3";

                    break;

                case WAV:

                    formatStr = "wav";

                    break;

            }

            this.format += formatStr;

            return this;
        }

        public Builder speaker(final Speaker speaker) {
            String speakerStr = null;

            switch (speaker) {

                case FEMALE_JANE:

                    speakerStr = "jane";

                    break;

                case FEMALE_OMAZH:

                    speakerStr = "omazh";

                    break;

                case MALE_ZAHAR:

                    speakerStr = "zahar";

                    break;

                case MALE_ERMIL:

                    speakerStr = "ermil";

                    break;
                default:
                    throw new UnknownItemException();
            }

            this.speaker += speakerStr;

            return this;
        }

        public Builder text(final String text) {

            try {
                this.text += URLEncoder.encode(text, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return this;
        }

        public Builder robot(boolean coise) {

            this.robot += coise ? "true" : "false";

            return this;
        }

        public TextToSpeechURL build() {

            return new TextToSpeechURL(this);
        }

    }
}
