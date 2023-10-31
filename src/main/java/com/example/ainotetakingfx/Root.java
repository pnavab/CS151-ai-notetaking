package com.example.ainotetakingfx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Root extends Application {
    private TextArea textArea;
    private TextField titleField;
    private ListView<Note> noteList;
    private ObservableList<Note> listModel;

    private List<Note> notes;

    public Root() {
        // No need for the constructor, you can initialize your variables in the start() method.
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Root");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        textArea = new TextArea();
        titleField = new TextField();
        listModel = FXCollections.observableArrayList();
        noteList = new ListView<>(listModel);

        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        HBox buttonPanel = new HBox();
        Button newButton = new Button("New");
        Button saveButton = new Button("Save");

        buttonPanel.getChildren().addAll(newButton, saveButton);

        newButton.setOnAction(e -> {
            textArea.setText("");
            titleField.setText("");
            noteList.getSelectionModel().clearSelection();
        });

        saveButton.setOnAction(e -> {
            String currentNoteContent = textArea.getText();
            String currentTitle = titleField.getText();
            int selectedIndex = noteList.getSelectionModel().getSelectedIndex();

            if (selectedIndex != -1) {
                Note selectedNote = notes.get(selectedIndex);
                selectedNote.setTitle(currentTitle);
                selectedNote.setContent(currentNoteContent);
                listModel.set(selectedIndex, selectedNote);
            } else {
                Note newNote = new Note(currentTitle, currentNoteContent);
                notes.add(newNote);
                listModel.add(newNote);
            }
        });

        noteList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Note selectedNote = noteList.getSelectionModel().getSelectedItem();
            if (selectedNote != null) {
                textArea.setText(selectedNote.getContent());
                titleField.setText(selectedNote.getTitle());
            }
        });

        // Add the title field above the text area
        BorderPane inputPanel = new BorderPane();
        inputPanel.setTop(titleField);
        inputPanel.setCenter(scrollPane);

        VBox root = new VBox();
        root.getChildren().addAll(inputPanel, noteList, buttonPanel);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        notes = new ArrayList<>();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
