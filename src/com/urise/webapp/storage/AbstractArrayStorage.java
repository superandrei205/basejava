package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int currentIdx;

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("В базе нет резюме с uuid : " + uuid);
        return null;
    }

    public abstract void save(Resume r);

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, currentIdx - 1);
            currentIdx--;
        } else {
            System.out.println("Ошибка удаления резюме");
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("В базе нет резюме с uuid : " + r.getUuid());
        }
    }


    public void clear() {
        Arrays.fill(storage, 0, currentIdx, null);
        currentIdx = 0;
    }

    public int size() {
        return currentIdx;
    }


    protected abstract int findIndex(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, currentIdx);
    }
}
