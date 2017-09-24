package com.example.chinny.mhacksxmusicplayer;

/**
 * Created by chinny on 9/24/17.
 */

public class Song {

    private String title;
    private String artist;

    public Song (String songTitle, String songArtist) {
        title = songTitle;
        artist = songArtist;
    }

    public String getTitle(){return title;}
    public String getArtist(){return artist;}

}
