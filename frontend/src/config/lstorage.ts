const LOCAL_STORAGE_PREFIX = "vrs_";

export const setLocalStorageKey = (key: string, value: any): void => {
    localStorage.setItem(LOCAL_STORAGE_PREFIX + key, value);
};

export const getLocalStorageKey = (key: string): any => {
    return localStorage.getItem(LOCAL_STORAGE_PREFIX + key);
};

export const delLocalStorageKey = (key: string): void => {
    localStorage.removeItem(LOCAL_STORAGE_PREFIX + key);
};
