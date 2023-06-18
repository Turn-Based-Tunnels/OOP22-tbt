package it.tbt.view.javaFx;

import it.tbt.controller.modelmanager.shop.ShopItem;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.view.api.GameViewShop;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * UI of the shop.
 */
public class JavaFxShopView extends AbstractJavaFxView implements GameViewShop {

    private final ShopState shopState;
    private final Scene scene;

    /**
     * Default constructor.
     * @param viewController
     * @param stage
     * @param scene
     * @param shopState
     */
    protected JavaFxShopView(
        final ViewController viewController,
        final Stage stage,
        final Scene scene,
        final ShopState shopState
    ) {
        super(viewController, stage, scene);
        this.scene = scene;
        this.shopState = shopState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        Platform.runLater(() -> {
            final BorderPane root = new BorderPane();
            root.getChildren().clear();

            // party items on the left
            final VBox partyItemsBox = new VBox();
            int count = 0;
            for (final ShopItem item : shopState.getPartyItems()) {
                final Label label = new Label(
                    item.getName()
                    + " x" + item.getCount()
                    + " $" + item.getValue()
                );
                if (count == shopState.getPartyFocus()) {
                    if (shopState.isPartyListFocused()) {
                        label.setStyle("-fx-background-color: yellow;");
                    } else {
                        label.setStyle("-fx-background-color: lightblue;");
                    }
                }
                partyItemsBox.getChildren().add(label);
                count++;
            }
            // shop items on the right
            final VBox shopItemsBox = new VBox();
            count = 0;
            for (final ShopItem item : shopState.getShopItems()) {
                final Label label = new Label(
                    item.getName()
                    + " x" + item.getCount()
                    + " $" + item.getValue()
                );
                if (count == shopState.getShopFocus()) {
                    if (shopState.isPartyListFocused()) {
                        label.setStyle("-fx-background-color: lightblue;");
                    } else {
                        label.setStyle("-fx-background-color: yellow;");
                    }
                }
                shopItemsBox.getChildren().add(label);
                count++;
            }

            final Label partyTitle = new Label("Party");
            partyTitle.setStyle("-fx-font-weight: bold;");
            final Label partySubTitle = new Label("wallet: " + shopState.getPartyWallet());
            partySubTitle.setStyle("-fx-font-weight: light;");
            final VBox partyBox = new VBox(10, partyTitle, partySubTitle, partyItemsBox);

            final Label shopTitle = new Label("Shop");
            shopTitle.setStyle("-fx-font-weight: bold;");
            final Label shopSubTitle = new Label("wallet: " + shopState.getShopWallet());
            shopSubTitle.setStyle("-fx-font-weight: light;");
            final VBox shopBox = new VBox(10, shopTitle, shopSubTitle, shopItemsBox);

            // main pane
            root.setLeft(partyBox);
            root.setRight(shopBox);
            root.setStyle("-fx-background-color: #F5F5F5;");

            scene.setRoot(root);
        });
    }

}
