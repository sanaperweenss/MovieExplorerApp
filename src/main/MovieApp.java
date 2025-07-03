package main; // 📁 Put this in the 'main' package

// ✅ Import FlatLaf for modern dark theme
import com.formdev.flatlaf.FlatDarkLaf;

// ✅ Import the GUI frame to launch
import ui.MovieSearchFrame;

import javax.swing.*;

public class MovieApp {
    public static void main(String[] args) {
        // 🔧 Set the look and feel to FlatDarkLaf (nice modern dark theme)
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("❌ Failed to apply dark theme: " + e.getMessage());
        }

        // 🚀 Launch the MovieSearchFrame (your main GUI window)
        new MovieSearchFrame();
    }
}
