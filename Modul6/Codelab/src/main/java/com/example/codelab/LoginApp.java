package com.example.codelab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Dialog");

        // Create GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        // Add UI elements
        Text scenetitle = new Text("Selamat Datang!");
        scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        // Method to handle login
        Runnable loginAction = () -> {
            if (userTextField.getText().equals("Ken") && pwBox.getText().equals("ken123")){
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Login Successful");
                showWelcomePage(primaryStage);
            } else {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Username/Password Salah");
            }
        };

        // Set action for button click
        btn.setOnAction(e -> loginAction.run());

        // Set action for pressing Enter key
        pwBox.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    loginAction.run();
                    break;
                default:
                    break;
            }
        });

        userTextField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    loginAction.run();
                    break;
                default:
                    break;
            }
        });

        primaryStage.show();
    }

    private void showWelcomePage(Stage stage) {
        GridPane welcomeGrid = new GridPane();
        welcomeGrid.setAlignment(Pos.CENTER);
        welcomeGrid.setHgap(10);
        welcomeGrid.setVgap(10);
        welcomeGrid.setPadding(new Insets(25, 25, 25, 25));

        Scene welcomeScene = new Scene(welcomeGrid, 300, 275);
        stage.setScene(welcomeScene);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefWidth(Double.MAX_VALUE);

        Text welcomeText = new Text("Halo Kennn!\nSelamat datang di Codelab!!");
        welcomeText.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        hbox.getChildren().add(welcomeText);
        welcomeGrid.add(hbox, 0, 0, 2, 1);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

