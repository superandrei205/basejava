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

    private int findIndex(String uuid) {
        for (int i = 0; i < index; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index == -1) {
            if (index < storage.length) {
                storage[this.index] = r;
                this.index++;
            } else {
                System.out.println("Не возможно добавить в резюме c uuid : " + r.getUuid() + " так как нет места");
            }
        } else {
            System.out.println("Не возможно добавить в резюме c uuid : " + r.getUuid() +
                    " так такое резюме уже существует");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("В базе нет резюме с uuid : " + uuid);
        return null;
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("В базе нет резюме с uuid : " + r.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, this.index - 1);
            this.index--;
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
