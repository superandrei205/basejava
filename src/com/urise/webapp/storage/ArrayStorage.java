package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index == -1) {
            if (currentIdx < STORAGE_LIMIT) {
                storage[currentIdx] = r;
                currentIdx++;
            } else {
                System.out.println("Не достаточно места для добавления резюме c uuid : " + r.getUuid());
            }
        } else {
            System.out.println("Уже существует резюме c uuid : " + r.getUuid());
        }
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < currentIdx; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
