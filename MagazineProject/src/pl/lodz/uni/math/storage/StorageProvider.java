package pl.lodz.uni.math.storage;

public class StorageProvider implements Storage {
    Storage instance = null;

    public void setInstance(Storage storageEnum){
        instance = storageEnum;
    }
    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getLength() {
        return length;
    }
}
