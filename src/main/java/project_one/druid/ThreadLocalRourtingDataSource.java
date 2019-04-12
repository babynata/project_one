package project_one.druid;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ThreadLocalRourtingDataSource extends AbstractRoutingDataSource{

    protected Object determineCurrentLookupKey() {
        return DataSourcesManager.get();
    }
}
