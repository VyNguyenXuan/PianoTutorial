package com.example.pianotutorial.models;

import java.util.HashMap;
import java.util.Map;

public class NoteMapper {
    private static final Map<String, Integer> noteNameToIdMap = new HashMap<>();
    private static final Map<Integer, String> noteIdToNameMap = new HashMap<>();

    static {
        // Octave 1
        noteNameToIdMap.put("C1", 1);
        noteNameToIdMap.put("D1", 2);
        noteNameToIdMap.put("E1", 3);
        noteNameToIdMap.put("F1", 4);
        noteNameToIdMap.put("G1", 5);
        noteNameToIdMap.put("A1", 6);
        noteNameToIdMap.put("B1", 7);
        noteNameToIdMap.put("C1b", 57);
        noteNameToIdMap.put("D1b", 58);
        noteNameToIdMap.put("E1b", 59);
        noteNameToIdMap.put("F1b", 60);
        noteNameToIdMap.put("G1b", 61);
        noteNameToIdMap.put("A1b", 62);
        noteNameToIdMap.put("B1b", 63);
        noteNameToIdMap.put("C1#", 113);
        noteNameToIdMap.put("D1#", 114);
        noteNameToIdMap.put("E1#", 115);
        noteNameToIdMap.put("F1#", 116);
        noteNameToIdMap.put("G1#", 117);
        noteNameToIdMap.put("A1#", 118);
        noteNameToIdMap.put("B1#", 119);

        // Octave 2
        noteNameToIdMap.put("C2", 8);
        noteNameToIdMap.put("D2", 9);
        noteNameToIdMap.put("E2", 10);
        noteNameToIdMap.put("F2", 11);
        noteNameToIdMap.put("G2", 12);
        noteNameToIdMap.put("A2", 13);
        noteNameToIdMap.put("B2", 14);
        noteNameToIdMap.put("C2b", 64);
        noteNameToIdMap.put("D2b", 65);
        noteNameToIdMap.put("E2b", 66);
        noteNameToIdMap.put("F2b", 67);
        noteNameToIdMap.put("G2b", 68);
        noteNameToIdMap.put("A2b", 69);
        noteNameToIdMap.put("B2b", 70);
        noteNameToIdMap.put("C2#", 120);
        noteNameToIdMap.put("D2#", 121);
        noteNameToIdMap.put("E2#", 122);
        noteNameToIdMap.put("F2#", 123);
        noteNameToIdMap.put("G2#", 124);
        noteNameToIdMap.put("A2#", 125);
        noteNameToIdMap.put("B2#", 126);

        // Octave 3
        noteNameToIdMap.put("C3", 15);
        noteNameToIdMap.put("D3", 16);
        noteNameToIdMap.put("E3", 17);
        noteNameToIdMap.put("F3", 18);
        noteNameToIdMap.put("G3", 19);
        noteNameToIdMap.put("A3", 20);
        noteNameToIdMap.put("B3", 21);
        noteNameToIdMap.put("C3b", 71);
        noteNameToIdMap.put("D3b", 72);
        noteNameToIdMap.put("E3b", 73);
        noteNameToIdMap.put("F3b", 74);
        noteNameToIdMap.put("G3b", 75);
        noteNameToIdMap.put("A3b", 76);
        noteNameToIdMap.put("B3b", 77);
        noteNameToIdMap.put("C3#", 127);
        noteNameToIdMap.put("D3#", 128);
        noteNameToIdMap.put("E3#", 129);
        noteNameToIdMap.put("F3#", 130);
        noteNameToIdMap.put("G3#", 131);
        noteNameToIdMap.put("A3#", 132);
        noteNameToIdMap.put("B3#", 133);

        // Octave 4
        noteNameToIdMap.put("C4", 22);
        noteNameToIdMap.put("D4", 23);
        noteNameToIdMap.put("E4", 24);
        noteNameToIdMap.put("F4", 25);
        noteNameToIdMap.put("G4", 26);
        noteNameToIdMap.put("A4", 27);
        noteNameToIdMap.put("B4", 28);
        noteNameToIdMap.put("C4b", 78);
        noteNameToIdMap.put("D4b", 79);
        noteNameToIdMap.put("E4b", 80);
        noteNameToIdMap.put("F4b", 81);
        noteNameToIdMap.put("G4b", 82);
        noteNameToIdMap.put("A4b", 83);
        noteNameToIdMap.put("B4b", 84);
        noteNameToIdMap.put("C4#", 134);
        noteNameToIdMap.put("D4#", 135);
        noteNameToIdMap.put("E4#", 136);
        noteNameToIdMap.put("F4#", 137);
        noteNameToIdMap.put("G4#", 138);
        noteNameToIdMap.put("A4#", 139);
        noteNameToIdMap.put("B4#", 140);

        // Octave 5
        noteNameToIdMap.put("C5", 29);
        noteNameToIdMap.put("D5", 30);
        noteNameToIdMap.put("E5", 31);
        noteNameToIdMap.put("F5", 32);
        noteNameToIdMap.put("G5", 33);
        noteNameToIdMap.put("A5", 34);
        noteNameToIdMap.put("B5", 35);
        noteNameToIdMap.put("C5b", 85);
        noteNameToIdMap.put("D5b", 86);
        noteNameToIdMap.put("E5b", 87);
        noteNameToIdMap.put("F5b", 88);
        noteNameToIdMap.put("G5b", 89);
        noteNameToIdMap.put("A5b", 90);
        noteNameToIdMap.put("B5b", 91);
        noteNameToIdMap.put("C5#", 141);
        noteNameToIdMap.put("D5#", 142);
        noteNameToIdMap.put("E5#", 143);
        noteNameToIdMap.put("F5#", 144);
        noteNameToIdMap.put("G5#", 145);
        noteNameToIdMap.put("A5#", 146);
        noteNameToIdMap.put("B5#", 147);

        // Octave 6
        noteNameToIdMap.put("C6", 36);
        noteNameToIdMap.put("D6", 37);
        noteNameToIdMap.put("E6", 38);
        noteNameToIdMap.put("F6", 39);
        noteNameToIdMap.put("G6", 40);
        noteNameToIdMap.put("A6", 41);
        noteNameToIdMap.put("B6", 42);
        noteNameToIdMap.put("C6b", 92);
        noteNameToIdMap.put("D6b", 93);
        noteNameToIdMap.put("E6b", 94);
        noteNameToIdMap.put("F6b", 95);
        noteNameToIdMap.put("G6b", 96);
        noteNameToIdMap.put("A6b", 97);
        noteNameToIdMap.put("B6b", 98);
        noteNameToIdMap.put("C6#", 148);
        noteNameToIdMap.put("D6#", 149);
        noteNameToIdMap.put("E6#", 150);
        noteNameToIdMap.put("F6#", 151);
        noteNameToIdMap.put("G6#", 152);
        noteNameToIdMap.put("A6#", 153);
        noteNameToIdMap.put("B6#", 154);

        // Octave 7
        noteNameToIdMap.put("C7", 43);
        noteNameToIdMap.put("D7", 44);
        noteNameToIdMap.put("E7", 45);
        noteNameToIdMap.put("F7", 46);
        noteNameToIdMap.put("G7", 47);
        noteNameToIdMap.put("A7", 48);
        noteNameToIdMap.put("B7", 49);
        noteNameToIdMap.put("C7b", 99);
        noteNameToIdMap.put("D7b", 100);
        noteNameToIdMap.put("E7b", 101);
        noteNameToIdMap.put("F7b", 102);
        noteNameToIdMap.put("G7b", 103);
        noteNameToIdMap.put("A7b", 104);
        noteNameToIdMap.put("B7b", 105);
        noteNameToIdMap.put("C7#", 155);
        noteNameToIdMap.put("D7#", 156);
        noteNameToIdMap.put("E7#", 157);
        noteNameToIdMap.put("F7#", 158);
        noteNameToIdMap.put("G7#", 159);
        noteNameToIdMap.put("A7#", 160);
        noteNameToIdMap.put("B7#", 161);

        // Octave 8
        noteNameToIdMap.put("C8", 50);
        noteNameToIdMap.put("D8", 51);
        noteNameToIdMap.put("E8", 52);
        noteNameToIdMap.put("F8", 53);
        noteNameToIdMap.put("G8", 54);
        noteNameToIdMap.put("A8", 55);
        noteNameToIdMap.put("B8", 56);
        noteNameToIdMap.put("C8b", 106);
        noteNameToIdMap.put("D8b", 107);
        noteNameToIdMap.put("E8b", 108);
        noteNameToIdMap.put("F8b", 109);
        noteNameToIdMap.put("G8b", 110);
        noteNameToIdMap.put("A8b", 111);
        noteNameToIdMap.put("B8b", 112);
        noteNameToIdMap.put("C8#", 162);
        noteNameToIdMap.put("D8#", 163);
        noteNameToIdMap.put("E8#", 164);
        noteNameToIdMap.put("F8#", 165);
        noteNameToIdMap.put("G8#", 166);
        noteNameToIdMap.put("A8#", 167);
        noteNameToIdMap.put("B8#", 168);

        // Populate reverse map
        for (Map.Entry<String, Integer> entry : noteNameToIdMap.entrySet()) {
            noteIdToNameMap.put(entry.getValue(), entry.getKey());
        }
    }

    public static int getNoteId(String noteName) {
        return noteNameToIdMap.getOrDefault(noteName, -1);
    }

    public static String getNoteName(int noteId) {
        return noteIdToNameMap.getOrDefault(noteId, "Invalid Note ID");
    }
}

