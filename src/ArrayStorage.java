import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int index;

    void clear() {
        Arrays.fill(storage, 0, index, null);
        index = 0;
    }

    void save(Resume r) {
        storage[index] = r;
        index++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].uuid.equals(uuid)) {
                while (i != index) {
                    storage[i] = storage[i + 1];
                    i++;
                }
            }
        }
        index--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, index);
    }

    int size() {
        return index;
    }
}
