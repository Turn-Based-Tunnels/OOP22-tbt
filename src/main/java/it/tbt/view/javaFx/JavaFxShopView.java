package it.tbt.view.javaFx;

import it.tbt.controller.modelmanager.shop.ShopItem;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.view.api.GameViewShop;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * UI of the shop.
 */
public class JavaFxShopView extends AbstractJavaFxView implements GameViewShop {

    private final ShopState shopState;
    private Scene scene;

    protected JavaFxShopView(
        final ViewController viewController,
        final Stage stage,
        final Scene scene,
        final ShopState shopState
    ) {
        super(viewController, stage, scene);
        this.shopState = shopState;
    }

    @Override
    public void render() {
        Platform.runLater(() -> {
            Group root = new Group();
            final GridPane pane = new GridPane();
            // party items on the left
            int count = 0;
            for (final ShopItem item : shopState.getPartyItems()) {
                pane.add(new Label(
                    item.getName()
                    + " x" + item.getCount()
                    + " $" + item.getValue()
                ), 0, count);
                count = count + 1;
            }
            count = 0;
            for (final ShopItem item : shopState.getShopItems()) {
                pane.add(new Label(
                    item.getName()
                    + " x" +item.getCount()
                    + " $" + item.getValue()
                ), 1, count);
                count = count + 1;
            }
            // TODO

        });
    }

}
