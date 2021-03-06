package homeworks.hw3.enumsWithPageData;

public enum HomePageData {
    INDEX_HTML_URL("https://epam.github.io/JDI/index.html"),
    HOME_PAGE_TITLE("Home Page"),
    SUB_HEADER_TEXT("JDI GITHUB"),
    SUB_HEADER_LINK_TEXT("https://github.com/epam/JDI");

    private String value;

    HomePageData(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
