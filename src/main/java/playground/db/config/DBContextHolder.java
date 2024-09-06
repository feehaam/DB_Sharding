package playground.db.config;

import playground.entity.Region;

public class DBContextHolder {
    private static final ThreadLocal<Region> contextHolder = new ThreadLocal<>();
    private static void setCurrentDb(Region region) {
        contextHolder.set(region);
    }
    public static Region getCurrentDb() {
        return contextHolder.get();
    }

    public static void determineShard(String region){
        switch (region.toUpperCase()) {
            case "NORTHERN":
                determineShard(Region.NORTHERN);
                break;
            case "SOUTHERN":
                determineShard(Region.SOUTHERN);
                break;
            case "PACIFIC":
                determineShard(Region.PACIFIC);
                break;
        }
    }

    public static void determineShard(Region region){
        setCurrentDb(region);
    }
}