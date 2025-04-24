package editor;

import javax.swing.*;
import java.io.*;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader BReader = new BufferedReader(new FileReader(file))){
                textArea.setText("");
                String Line;
                while ((Line = BReader.readLine()) != null) {
                    textArea.append(Line + "\n");
                }
                textEditor.currentFile = file;
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "can not open file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null){
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(textArea.getText());
                    textEditor.currentFile = file;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(textEditor, "can not save file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(textEditor, "can not save file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
    }
}
