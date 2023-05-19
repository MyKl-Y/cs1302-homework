package cs1302.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * A basic JavaFX program which takes a user specified URL and loads it
 * into an {@code ImageView}.
 *
 */
public class ImageApp extends Application {
    /** Instance variables for the stage and scene. */
    Stage stage;
    Scene scene;

    /** The root container for the application scene graph. */
    VBox vbox;

    /** The container for the url textfield and the load image button. */
    HBox urlLayer;
    TextField url;
    Button loadButton;

    /** The container for the loaded image. */
    ImageView imgView;

    // Declare instance variables for the components and other shared objects
    // of your application so that they can be accessed from instance methods
    HBox resizeLayer;
    Button enlargeButton;
    Button shrinkButton;
    Button actualButton;
    ImageView enlargeImage;
    ImageView shrinkImage;
    ImageView actualImage;
    private static final String ENLARGE_IMG = "resources/photo_size_enlarge.png";
    private static final String SHRINK_IMG = "resources/photo_size_shrink.png";
    private static final String ACTUAL_IMG = "resources/photo_size_actual.png";

    /** A default image which loads when the application starts. */
    private static final String DEFAULT_IMG = "resources/default.png";

    /** Default height and width for Images. */
    private static final int DEF_HEIGHT = 300;
    private static final int DEF_WIDTH = 300;

    /**
     * Constructs a new {@code ImageApp} object.
     */
    public ImageApp() {
        System.out.println("2) Creating an instance of the ImageApp Application");

        // 1) Setup instance variables for the stage and scene so that we can
        // access them from other instance methods; an app MUST ONLY construct
        // a Scene or a Stage object in the start(Stage) method or after the
        // start(Stage) method is called -- therefore, we will reassign these
        // later in the start(Stage) method.
        this.stage = null;
        this.scene = null;

        // 2) Construct other nodes/components and objects.
        vbox = new VBox();
        urlLayer = new HBox(8);
        imgView = new ImageView();
        loadButton = new Button("Load");
        url = new TextField("https://");
        resizeLayer = new HBox(10);
        enlargeButton = new Button();
        shrinkButton = new Button();
        actualButton = new Button();
        enlargeImage = new ImageView();
        shrinkImage = new ImageView();
        actualImage = new ImageView();
    } // ImageApp

    @Override
    public void init() {
        System.out.println("3) Executing the init method");
        vbox.getChildren().addAll(urlLayer, imgView, resizeLayer);
        urlLayer.getChildren().addAll(url, loadButton);
        resizeLayer.getChildren().addAll(enlargeButton, shrinkButton, actualButton);
        enlargeButton.setMaxWidth(193);
        shrinkButton.setMaxWidth(193);
        actualButton.setMaxWidth(193);
        HBox.setHgrow(url, Priority.ALWAYS);
        HBox.setHgrow(loadButton, Priority.ALWAYS);
        HBox.setHgrow(enlargeButton, Priority.ALWAYS);
        HBox.setHgrow(shrinkButton, Priority.ALWAYS);
        HBox.setHgrow(actualButton, Priority.ALWAYS);
        Image enlargeImageImg = new Image("file:" + ENLARGE_IMG);
        Image shrinkImageImg = new Image("file:" + SHRINK_IMG);
        Image actualImageImg = new Image("file:" + ACTUAL_IMG);
        Image defaultImage = new Image("file:" + DEFAULT_IMG);
        imgView.setImage(defaultImage);
        enlargeImage.setImage(enlargeImageImg);
        shrinkImage.setImage(shrinkImageImg);
        actualImage.setImage(actualImageImg);
        enlargeButton.setGraphic(enlargeImage);
        shrinkButton.setGraphic(shrinkImage);
        actualButton.setGraphic(actualImage);
        imgView.setPreserveRatio(true);
        imgView.setFitHeight(300);
        double originalHeight = imgView.getFitHeight();
        EventHandler<ActionEvent> mouseClickHandler = (ActionEvent e) -> {
            this.loadImage(e);
        };
        enlargeButton.setOnAction(event -> {
            double height = imgView.getFitHeight();
            if ((height + 25) > (originalHeight * 2)) {
                enlargeButton.setDisable(true);
                stage.sizeToScene();
            } else {
                imgView.setFitHeight(height + 25);
                shrinkButton.setDisable(false);
                stage.sizeToScene();
            } // if
        });
        shrinkButton.setOnAction(event -> {
            double height = imgView.getFitHeight();
            if ((height - 25) <= 0) {
                shrinkButton.setDisable(true);
                stage.sizeToScene();
            } else {
                imgView.setFitHeight(height - 25);
                enlargeButton.setDisable(false);
                stage.sizeToScene();
            } // if
        });
        actualButton.setOnAction(event -> {
            imgView.setFitHeight(originalHeight);
            enlargeButton.setDisable(false);
            shrinkButton.setDisable(false);
            stage.sizeToScene();
        });
        loadButton.setOnAction(mouseClickHandler);
    } // init

    /**
     * The entry point for our image viewer application.
     *
     * @param stage A reference to the stage object (window) created by the system.
     */
    public void start(Stage stage) {
        // Add the components to the scene and show the stage.
        // You may not need to modify this method.

        System.out.println("4) Executing the start method");

        this.stage = stage;

        // Add the root of the scene graph to the stage.
        scene = new Scene(vbox);

        // Set up the stage and set it to be visible
        stage.setScene(scene);
        stage.setTitle("1302 Image Viewer!");
        stage.sizeToScene();
        stage.show();
    } // start

    /**
     * Students will provide javadoc comments here.
     *
     * @param event source event
     */
    private void loadImage(ActionEvent event) {
        try {
            Image newImg = new Image(url.getText(), DEF_HEIGHT, DEF_WIDTH, false, false);
            if (newImg.isError()) {
                throw new IOException(newImg.getException());
            } // if
            imgView.setImage(newImg);
        } catch (IOException | IllegalArgumentException e) {
            alertError(e);
        } // try
    } // loadImage

    @Override
    public void stop() {
        // This method is typically used for application cleanup. It is not
        // needed for this application, but we included it so that you can
        // see that it does get called as part of the JavaFX application
        // life-cycle.

        System.out.println("6) Executing the stop method");
    } // stop

    /**
     * Show a modal error alert based on {@code cause}.
     * @param cause a {@link java.lang.Throwable Throwable} that caused the alert
     */
    public static void alertError(Throwable cause) {
        TextArea text = new TextArea(cause.toString());
        text.setEditable(false);
        Alert alert = new Alert(AlertType.ERROR);
        alert.getDialogPane().setContent(text);
        alert.setResizable(true);
        alert.showAndWait();
    } // alertError


} // ImageApp
