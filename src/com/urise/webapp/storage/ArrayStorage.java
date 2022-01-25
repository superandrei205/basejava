package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int currentIdx;

    public void clear() {
        Arrays.fill(storage, 0, currentIdx, null);
        currentIdx = 0;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < currentIdx; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index == -1) {
            if (currentIdx < storage.length) {
                storage[currentIdx] = r;
                currentIdx++;
            } else {
                System.out.println("Не достаточно места для добавления резюме c uuid : " + r.getUuid());
            }
        } else {
            System.out.println("Уже существует резюме c uuid : " + r.getUuid());
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
            System.arraycopy(storage, index + 1, storage, index, currentIdx - 1);
            currentIdx--;
        } else {
            System.out.println("Ошибка удаления резюме");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, currentIdx);
    }

    public int size() {
        return currentIdx;
    }
}
