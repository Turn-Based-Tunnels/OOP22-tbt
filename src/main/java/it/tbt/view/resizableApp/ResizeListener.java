package it.tbt.view.resizableApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static it.tbt.view.resizableApp.ResizableApp.ASPECT_RATIO;

public class ResizeListener implements ChangeListener<Number> {
    private final Stage stage;
    private final Rectangle content;
    private final VBox vbox;

    public ResizeListener(Stage stage, Rectangle content, VBox vbox) {
        this.stage = stage;
        this.content = content;
        this.vbox = vbox;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        double width = stage.getWidth();
        double height = stage.getHeight();

        double calculatedHeight = width / ASPECT_RATIO;
        double calculatedWidth = height * ASPECT_RATIO;

        if (calculatedHeight > height) {
            content.setWidth(calculatedWidth);
            content.setHeight(height);
        } else {
            content.setWidth(width);
            content.setHeight(calculatedHeight);
        }

        content.setX((width - content.getWidth()) / 2);
        content.setY((height - content.getHeight()) / 2);

        // Adjust the size and font of menu buttons based on the new content size
        double buttonFontSize = content.getWidth() * 0.03;
        Font buttonFont = Font.font("Arial", FontWeight.BOLD, buttonFontSize);
        vbox.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setFont(buttonFont);
            }
        });
    }



    public void invokeChanged() {
        double oldValue = 0; // Pass the appropriate old value here
        double newValue = 0; // Pass the appropriate new value here
        this.changed(null, oldValue, newValue);
    }
}