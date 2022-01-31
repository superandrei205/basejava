package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void addResume(Resume r, int index) {
        storage[currentIdx] = r;
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < currentIdx; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
