import java.util.Map;
import java.util.Set;


public class Filter {
    private Map<Integer, Object> criteria;

    public Filter(Map<Integer, Object> criteria) {
        this.criteria = criteria;
    }

    public boolean matches(Notebook notebook) {
        Set<Integer> keys = criteria.keySet();
        for (int key : keys) {
            Object value = criteria.get(key);
            if (!matchesCriteria(notebook, key, value)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesCriteria(Notebook notebook, int key, Object value) {
        switch (key) {
            case 1: // RAM
                int minRam = (int) value;
                return notebook.getRam() >= minRam;
            case 2: // Storage
                int minStorage = (int) value;
                return notebook.getStorage() >= minStorage;
            case 3: // OS
                String requiredOs = (String) value;
                return notebook.getOs().equalsIgnoreCase(requiredOs);
            case 4: // Color
                String requiredColor = (String) value;
                return notebook.getColor().equalsIgnoreCase(requiredColor);
            default:
                return false;
        }
    }
}
