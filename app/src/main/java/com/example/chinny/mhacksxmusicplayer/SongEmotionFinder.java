package com.example.chinny.mhacksxmusicplayer;

/**
 * Created by chinny on 9/23/17.
 */

public class SongEmotionFinder {

    enum emotion {
        HAPPY,
        SAD,
        AGGRESSIVE,
        RELAXED
    }

    public String whichSong(emotion emote) {
        String songId;
        String emoteSearch;
        switch(emote) {
            case HAPPY:
                emoteSearch = "happy";
                break;
            case SAD:
                emoteSearch = "sad";
                break;
            case AGGRESSIVE:
                emoteSearch = "aggressive";
                break;
            case RELAXED:
                emoteSearch = "relaxed";
                break;
        }

        return songId = "hello";
    }
}
