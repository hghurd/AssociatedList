import java.util.ArrayList;
import java.util.List;

public class AssociatedList implements SimpleMap{
    private ArrayList<Entry> entryList = new ArrayList<>();
    private int size = 0;

    @Override
    public Integer get(String key) {
        return null;
    }

    @Override
    public Integer put(String key, Integer value) {
        Entry newEntry = new Entry( key, value );
        entryList.add( newEntry );
        size++;
        return null;
    }

    @Override
    public Integer remove(String key) {

        size--;
        return null;
    }

    @Override
    public boolean isEmpty() {
        return ( size == 0 );
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Entry> entryList() {
        return null;
    }
}
