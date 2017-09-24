package com.example.chinny.mhacksxmusicplayer;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * Created by chinny on 9/23/17.
 */

public class SongEmotionFinder {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    String TAG = "SongEmotionFinder";

    enum emotion {
        HAPPY,
        SAD,
        AGGRESSIVE,
        RELAXED
    }

    public String[][] whichSongs(emotion emote) {

        Double emoteMeasure = 0.0;
        String artist = null;
        String songTitle = null;
        final double emoteVals[] = new double[5];
        String songNamesandArts[][] = new String[5][2];
        Random rand = new Random();
        int i = rand.nextInt(2000) + 1;

        DatabaseReference songEmoteVal = myRef.child(Integer.toString(i)).child("highlevel");
        switch(emote) {
            case HAPPY:
                songEmoteVal = songEmoteVal.child("mood_happy").child("all").child("happy");
                break;
            case SAD:
                songEmoteVal = songEmoteVal.child("mood_sad").child("all").child("sad");
                break;
            case AGGRESSIVE:
                songEmoteVal = songEmoteVal.child("mood_aggressive").child("all").child("aggressive");
                break;
            case RELAXED:
                songEmoteVal = songEmoteVal.child("mood_relaxed").child("all").child("relaxed");
                break;
        }

        DatabaseReference songTitleVal = myRef.child(Integer.toString(i)).child("metadata")
                .child("tags").child("title").child("0");

        DatabaseReference songArtistVal = myRef.child(Integer.toString(i)).child("metadata")
                .child("tags").child("album").child("0");

        for(int v = 0; v <= 4; i = rand.nextInt(2000) + 1){
            // Read from the database
            songEmoteVal.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    Double emoteMeasure = (Double) dataSnapshot.getValue();
                    String songTitle = (String) dataSnapshot.getValue();
                    String artist = (String) dataSnapshot.getValue();
                    Log.d(TAG, "Value is: " + emoteMeasure);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            if (emoteMeasure > 0.8) {
                emoteVals[v] = emoteMeasure;
                songNamesandArts[v][0] = songTitle;
                songNamesandArts[v][1] = artist;
                v++;
            }
        }
        return songNamesandArts;
    }
}
