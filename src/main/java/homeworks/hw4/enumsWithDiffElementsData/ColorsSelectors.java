package homeworks.hw4.enumsWithDiffElementsData;

public enum ColorsSelectors {
    RED_SELECTOR("option:nth-child(1)"),
    GREEN_SELECTOR("option:nth-child(2)"),
    BLUE_SELECTOR("option:nth-child(3)"),
    YELLOW_SELECTOR("option:nth-child(4)");

    private String value;

    ColorsSelectors (String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
