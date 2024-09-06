package playground.db.config;

import playground.entity.Region;

public class DBContextHolder {
    private static final ThreadLocal<Region> contextHolder = new ThreadLocal<>();
    public static void setCurrentDb(Region region) {
        contextHolder.set(region);
    }
    public static Region getCurrentDb() {
        return contextHolder.get();
    }
    public static void clear() {
        contextHolder.remove();
    }
}