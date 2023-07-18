package ts.andrey.giphy.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

@UtilityClass
public class GiphyLinkHashUtil {

    private static final int CASH_SIZE = 200;

    private static final LinkedList<String> BROKE_GIPHY_LIST = new LinkedList<>();

    private static final LinkedList<String> RICH_GIPHY_LIST = new LinkedList<>();

    public String getRichHash() {
        if (RICH_GIPHY_LIST.isEmpty()) {
            return null;
        }
        final var list = new ArrayList<>(RICH_GIPHY_LIST);
        Collections.shuffle(list);
        return list.get(0);
    }

    public String getBrokeHash() {
        if (BROKE_GIPHY_LIST.isEmpty()) {
            return null;
        }
        final var list = new ArrayList<>(BROKE_GIPHY_LIST);
        Collections.shuffle(list);
        return list.get(0);
    }

    public void addRichLink(String richLink) {
        if (!RICH_GIPHY_LIST.contains(richLink)) {
            if (RICH_GIPHY_LIST.size() >= CASH_SIZE) {
                RICH_GIPHY_LIST.removeFirst();
            }
            RICH_GIPHY_LIST.add(richLink);
        }
    }

    public void addBrokeLink(String brokeLink) {
        if (!BROKE_GIPHY_LIST.contains(brokeLink)) {
            if (BROKE_GIPHY_LIST.size() >= CASH_SIZE) {
                BROKE_GIPHY_LIST.removeFirst();
            }
            BROKE_GIPHY_LIST.add(brokeLink);
        }
    }

}
