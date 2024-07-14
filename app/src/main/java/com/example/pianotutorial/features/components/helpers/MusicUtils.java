package com.example.pianotutorial.features.components.helpers;

public class MusicUtils {
    public static int countDottedNotes(float duration) {
        int count = 0;

        // Loop to check for dotted notes
        while (duration > 0) {
            if (duration == 4.0f || duration == 2.0f || duration == 1.0f || duration == 0.5f || duration == 0.25f) {
                return count;
            } else {
                if (duration > 4.0f) {
                    duration -= 4.0f;
                } else if (duration > 2.0f) {
                    duration -= 2.0f;
                } else if (duration > 1.0f) {
                    duration -= 1.0f;
                } else if (duration > 0.5f) {
                    duration -= 0.5f;
                } else if (duration > 0.25f) {
                    duration -= 0.25f;
                } else if (duration > 0.125f) {
                duration -= 0.125f;
            }

                count++;
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
        if (octave < 4 || (octave == 4 && "C".equals(pitch))) {
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
                    break;
            }
        } else if (octave > 5 || (octave==5 && (("A".equals(pitch)) || ("B".equals(pitch))))) {
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
                return 5;
            case "B":
                return 6;
            default:
                return -1;
        }
    }
}

