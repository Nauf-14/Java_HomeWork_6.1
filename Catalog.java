import java.util.*;

public class Catalog {
    private Set<Notebook> notebooks;

    public Catalog() {
        this.notebooks = new HashSet<>();
    }

    public void addNotebook(Notebook notebook) {
        notebooks.add(notebook);
    }

    public void printFilteredNotebooks(Map<Integer, Object> filterCriteria) {
        Filter filter = new Filter(filterCriteria);
        List<Notebook> filteredNotebooks = getFilteredNotebooks(filter);

        if (filteredNotebooks.isEmpty()) {
            System.out.println("No notebooks matching the filter criteria.");
        } else {
            for (Notebook notebook : filteredNotebooks) {
                System.out.println(notebook);
            }
        }
    }

    private List<Notebook> getFilteredNotebooks(Filter filter) {
        List<Notebook> filteredNotebooks = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            if (filter.matches(notebook)) {
                filteredNotebooks.add(notebook);
            }
        }
        return filteredNotebooks;
    }
}
