package pl.lodz.uni.math.storage;

import pl.lodz.uni.math.storage.implement.FurnituresStorage;
import pl.lodz.uni.math.storage.implement.ToysStorage;

public enum EnumPackage {
    Furnitures,
    Toys;
        public static Storage getStorage(EnumPackage Package){
            switch(Package){
                case Furnitures:
                    return (Storage) new FurnituresStorage();
                case Toys:
                    return (Storage) new ToysStorage();
                default:
                    return null;
            }
        }
}
