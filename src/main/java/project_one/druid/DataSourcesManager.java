package project_one.druid;

public class DataSourcesManager {

    private static final ThreadLocal<DataSourcesEnum> dataSourcesTypes=new ThreadLocal<DataSourcesEnum>();

    public static void set(DataSourcesEnum dataSourcesEnum) {
        dataSourcesTypes.set(dataSourcesEnum);
    }

    public static DataSourcesEnum get() {
        return dataSourcesTypes.get();
    }

    public static void reset() {
        dataSourcesTypes.set(DataSourcesEnum.MASTER);
    }
}
