package it.tbt.model.command.explore;

import it.tbt.model.command.api.Command;
import it.tbt.model.statechange.InventoryTrigger;
import it.tbt.model.statechange.PauseTrigger;

public class CommandInventory implements Command {

    private InventoryTrigger inventoryTrigger;

    /**
     * @param inventoryTrigger which will be used to trigger the Inventory GameState
     */
    public CommandInventory(final InventoryTrigger inventoryTrigger) {
        this.inventoryTrigger = inventoryTrigger;
    }

    /**
     * Action that the Command should be doing.
     */
    @Override
    public void execute() {
        this.inventoryTrigger.triggerInventory();
    }
}
