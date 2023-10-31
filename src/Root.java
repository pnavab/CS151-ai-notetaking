package src;
import javafx.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Root extends JFrame {
    private JTextArea textArea;
    private JTextField titleField;
    private JList<Note> noteList;
    private DefaultListModel<Note> listModel;

    private List<Note> notes;

    public Root() {
        setTitle("Root");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        titleField = new JTextField(); // Add a JTextField for the initial title
        listModel = new DefaultListModel<>();
        noteList = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane listScrollPane = new JScrollPane(noteList);

        JPanel buttonPanel = new JPanel();
        JButton newButton = new JButton("New");
        JButton saveButton = new JButton("Save");

        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);

        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                titleField.setText(""); // Clear the title field
                noteList.clearSelection();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentNoteContent = textArea.getText();
                String currentTitle = titleField.getText(); // Get the title from the title field
                int selectedIndex = noteList.getSelectedIndex();

                if (selectedIndex != -1) {
                    Note selectedNote = notes.get(selectedIndex);
                    selectedNote.setTitle(currentTitle); // Update the title
                    selectedNote.setContent(currentNoteContent);
                    listModel.set(selectedIndex, selectedNote);
                } else {
                    Note newNote = new Note(currentTitle, currentNoteContent);
                    notes.add(newNote);
                    listModel.addElement(newNote);
                }
            }
        });

        noteList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index = noteList.getSelectedIndex();
                if (index >= 0 && index < notes.size()) {
                    Note selectedNote = notes.get(index);
                    textArea.setText(selectedNote.getContent());
                    titleField.setText(selectedNote.getTitle()); // Set the title in the title field
                }
            }
        });

        // Add the title field above the text area
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(titleField, BorderLayout.NORTH);
        inputPanel.add(scrollPane, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.CENTER);
        add(listScrollPane, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);

        notes = new ArrayList<>();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Root app = new Root();
                app.setVisible(true);
            }
        });
    }

}