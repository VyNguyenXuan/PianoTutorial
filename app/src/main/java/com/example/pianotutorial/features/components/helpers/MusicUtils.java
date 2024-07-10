package com.example.pianotutorial.features.components.helpers;

public class MusicUtils {
    public static int countDottedNotes(float duration) {
        // Define the base note durations
        float wholeNote = 4.0f;
        float halfNote = 2.0f;
        float quarterNote = 1.0f;
        float eighthNote = 0.5f;
        float sixteenthNote = 0.25f;

        // Check if the duration is a base note duration
        if (duration == wholeNote || duration == halfNote || duration == quarterNote || duration == eighthNote || duration == sixteenthNote) {
            return 0; // No dotted notes
        }

        // If not a base note duration, it must be a dotted note
        // Reduce the duration by dividing by 1.5 and increment the dotted note counter
        return 1 + countDottedNotes(duration / 1.5f);
    }
}

