package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void addResume(Resume r, int index) {
        int position = Math.abs(index) - 1;
        System.arraycopy(storage, position, storage, position + 1, currentIdx);
        storage[position] = r;
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, currentIdx, searchKey);
    }
}
