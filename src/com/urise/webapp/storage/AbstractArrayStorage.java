package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int currentIdx;

    protected abstract int findIndex(String uuid);

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    protected abstract void addResume(Resume r, int index);

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            if (currentIdx < STORAGE_LIMIT) {
                addResume(r, index);
                currentIdx++;
            } else {
                throw new StorageException("Storage overflow", r.getUuid());
            }
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, currentIdx - index - 1);
            currentIdx--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, currentIdx, null);
        currentIdx = 0;
    }

    public int size() {
        return currentIdx;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, currentIdx);
    }
}
