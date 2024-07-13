package com.example.pianotutorial.features.components.helpers;

public class MusicUtils {
    public static int countDottedNotes(float duration) {
        // Define the base note durations
        float[] noteDurations = {4.0f, 2.0f, 1.0f, 0.5f, 0.25f};
        int count = 0;

        for (float noteDuration : noteDurations) {
            if (duration == noteDuration) {
                return 0;
            } else if (duration > noteDuration) {
                while (duration > noteDuration) {
                    duration -= noteDuration + noteDuration / 2;
                    count++;
                    if (duration <= 0) return count;
                }
            }
        }

        return count;
    }

    public static String getFirstCharacter(String pitch) {
        if (pitch == null || pitch.isEmpty()) {
            return "";
        }
        return String.valueOf(pitch.charAt(0));
    }

    public static boolean isNoteOnLine(String pitch, int octave) {
        pitch=getFirstCharacter(pitch);
        // Define the notes that are on lines for the treble clef across several octaves
        String[][] lineNotes = {
                {"E", "G", "B", "D", "F"},  // 4th octave (E4, G4, B4, D5, F5)
                {"C", "E", "G", "B", "D"},  // 5th octave (C5, E5, G5, B5, D6)
                {"A", "C", "E", "G", "B"},  // 3rd octave (A3, C4, E4, G4, B4)
                {"F", "A", "C", "E", "G"},  // 6th octave (F5, A5, C6, E6, G6)
                {"D", "F", "A", "C", "E"},  // 2nd octave (D3, F3, A3, C4, E4)
                {"B", "D", "F", "A", "C"},  // 1st octave (B2, D3, F3, A3, C4)
                {"G", "B", "D", "F", "A"},  // 7th octave (G5, B5, D6, F6, A6)
                {"E", "G", "B", "D", "F"}   // 8th octave (E6, G6, B6, D7, F7)
        };

        int[] lineOctaves = {4, 5, 3, 6, 2, 1, 7, 8};

        for (int i = 0; i < lineNotes.length; i++) {
            for (int j = 0; j < lineNotes[i].length; j++) {
                if (lineNotes[i][j].equals(pitch) && lineOctaves[i] == octave) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int calculateLedgerLines(String pitch, int octave) {
        pitch=getFirstCharacter(pitch);
        int ledgerLines = 0;
        if (octave < 4 || (octave == 4 && "C".equals(pitch)) || (octave == 4 && "B".equals(pitch)) || (octave == 4 && "A".equals(pitch))) {
            switch (pitch) {
                case "C":
                case "B":
                    ledgerLines = 1;
                    break;
                case "A":
                case "G":
                    ledgerLines = 2;
                    break;
                case "F":
                case "E":
                    ledgerLines = 3;
                    break;
                case "D":
                    ledgerLines = 4;
                    break;
                default:
                    ledgerLines = 0;
                    break;
            }
        } else if (octave > 5) {
            switch (pitch) {
                case "A":
                case "B":
                    ledgerLines = 1;
                    break;
                case "C":
                case "D":
                    ledgerLines = 2;
                    break;
                case "E":
                case "F":
                    ledgerLines = 3;
                    break;
                case "G":
                    ledgerLines = 4;
                    break;
                default:
                    ledgerLines = 0;
                    break;
            }
        }
        return ledgerLines;
    }

    public static int pitchValue(String pitch) {
        pitch=getFirstCharacter(pitch);
        switch (pitch) {
            case "C":
                return 0;
            case "D":
                return 1;
            case "E":
                return 2;
            case "F":
                return 3;
            case "G":
                return 4;
            case "A":
                return -2;
            case "B":
                return -1;
            default:
                return 5;
        }
    }
}

