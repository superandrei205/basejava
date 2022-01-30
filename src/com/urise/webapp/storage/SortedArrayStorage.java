package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            if (currentIdx < STORAGE_LIMIT) {
                int position = Math.abs(index) - 1;
                System.arraycopy(storage, position, storage, position + 1, currentIdx);
                storage[position] = r;
                currentIdx++;
            } else {
                System.out.println("Не достаточно места для добавления резюме c uuid : " + r.getUuid());
            }
        } else {
            System.out.println("Уже существует резюме c uuid : " + r.getUuid());
        }
    }

    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, currentIdx, searchKey);
    }
}
