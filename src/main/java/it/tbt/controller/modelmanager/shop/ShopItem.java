package it.tbt.controller.modelmanager.shop;

/**
 * Stripped down item, to be used in controller and viwe.
 */
public class ShopItem {
    private final String name;
    private int count;
    private final int value;

    /**
     * Default contructor.
     * @param name
     * @param count
     * @param value
     */
    public ShopItem(final String name, final int count, final int value) {
        this.name = name;
        this.count = count;
        this.value = value;
    }

    public ShopItem(final ShopItem item) {
        this.name = item.getName();
        this.count = 1;
        this.value = item.getValue();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * increase counter;
     */
    public void incCount() {
        count = count + 1;
    }

    /**
     * decrease counter;
     */
    public void decCount() {
        count = count - 1;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
