package com.example.ainotetakingfx;

import java.util.ArrayList;
import java.util.List;

//Import all necessary classes from JavaFX
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Root extends Application {

    //Initialize all areas
    private TextArea textArea;
    private TextField titleField;
    private TextField queryField; // New TextField for queries
    private ListView<Note> noteList;
    private ObservableList<Note> listModel;

    private List<Note> notes;

    public Root() {
        // No need for the constructor
    }

    @Override
    public void start(Stage primaryStage) {

        //Create intitial window
        primaryStage.setTitle("Root");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        //Create areas for text input and viewing note list
        textArea = new TextArea();
        titleField = new TextField();
        queryField = new TextField(); // Initialize the new TextField
        listModel = FXCollections.observableArrayList();
        noteList = new ListView<>(listModel);

        //Create panel for all buttons
        HBox buttonPanel = new HBox();
        buttonPanel.setAlignment(Pos.CENTER); // Center content both horizontally and vertically
        Button newButton = new Button("New");
        newButton.getStyleClass().add("note-title");
        Button saveButton = new Button("Save");
        Button queryButton = new Button("Ask"); // Button for sending queries

        buttonPanel.getChildren().addAll(newButton, saveButton, queryButton);

        //New Button: Clears text areas and unselects note from list
        newButton.setOnAction(e -> {
            textArea.setText("");
            titleField.setText("");
            noteList.getSelectionModel().clearSelection();
        });

        //Save Button
        saveButton.setOnAction(e -> {

            //Grabs content of text fields
            String currentNoteContent = textArea.getText();
            String currentTitle = titleField.getText();
            //check if a title was specified, set title to '*untitled note' if not
            if( currentTitle.equals("")){
                currentTitle = "*untitled note";
            }
            int selectedIndex = noteList.getSelectionModel().getSelectedIndex();

            //Clears all text fields to prepare for new note
            textArea.clear();
            titleField.clear();
            queryField.clear();

            //Creates new note and sets all content
            if (selectedIndex != -1) {
                Note selectedNote = notes.get(selectedIndex);
                selectedNote.setTitle(currentTitle);
                selectedNote.setContent(currentNoteContent);

                //adds note to note list
                listModel.set(selectedIndex, selectedNote);
            } else {
                Note newNote = new Note(currentTitle, currentNoteContent);
                notes.add(newNote);
                listModel.add(newNote);
            }
        });

        //Grabs text from query text field and utilizes ChatGPT AI
        //Adds fetched response to note text field
        queryButton.setOnAction(e -> {
            String query = queryField.getText();
            String response = Gpt.chatGPT(query);
            textArea.appendText("\n" + response);
        });

        // Sets up listener for selected item in noteList
        noteList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Note selectedNote = noteList.getSelectionModel().getSelectedItem();

            //Check if note is selected
            if (selectedNote != null) {

                //Updates text field and title with selected note
                textArea.setText(selectedNote.getContent());
                titleField.setText(selectedNote.getTitle());
            }
        });

        // HBox for the title field and label
        HBox titlePanel = new HBox();
        titlePanel.setAlignment(Pos.CENTER); // Center content both horizontally and vertically
        titlePanel.getChildren().addAll(new Label("Title"), titleField);
        HBox.setHgrow(titlePanel, Priority.ALWAYS); // Make it grow horizontally

        // HBox for the query field and button
        HBox queryPanel = new HBox();
        queryPanel.setAlignment(Pos.CENTER); // Center content both horizontally and vertically
        queryPanel.getChildren().addAll(new Label("ChatGPT"), queryField, queryButton);
        HBox.setHgrow(queryPanel, Priority.ALWAYS); // Make it grow horizontally

        // VBox for the text area 
        VBox textAreaPanel = new VBox();
        textAreaPanel.setAlignment(Pos.CENTER); // Center content both horizontally and vertically
        textAreaPanel.getChildren().addAll(new Label("Note"), textArea);
        VBox.setVgrow(textAreaPanel, Priority.ALWAYS); // Make it grow both horizontally and vertically

        //Creates root VBox and adds all panels to VBox
        VBox root = new VBox();
        root.getChildren().addAll(titlePanel, queryPanel, textAreaPanel, noteList, buttonPanel);

        //Create scene to implement CSS Styling
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        notes = new ArrayList<>();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
