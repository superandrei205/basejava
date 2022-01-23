package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int index;

    public void clear() {
        Arrays.fill(storage, 0, index, null);
        index = 0;
    }

    public int checkStorage(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        int idxUuid = checkStorage(r.getUuid());
        if (idxUuid == -1 && index < storage.length) {
            storage[index] = r;
            index++;
        } else {
            System.out.println("Не возможно добавить в резюме");
        }
    }

    public Resume get(String uuid) {
        if (checkStorage(uuid) >= 0) {
            int idxUuid = checkStorage(uuid);
            return storage[idxUuid];
        }
        System.out.println("Такого резюме нет в базе");
        return null;
    }

    public void update(Resume r) {
        int idxUuid = checkStorage(r.getUuid());
        if (idxUuid >= 0) {
            storage[idxUuid] = r;
        } else {
            System.out.println("Такого резюме нет в базе");
        }
    }

    public void delete(String uuid) {
        int idxUuid = checkStorage(uuid);
        if (idxUuid >= 0) {
            while (idxUuid < index - 1) {
                storage[idxUuid] = storage[idxUuid + 1];
                idxUuid++;
            }
            index--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, index);
    }

    public int size() {
        return index;
    }
}
