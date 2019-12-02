package hu.dornyayse.liveresultat_viewer.service;

import hu.dornyayse.liveresultat_viewer.network.ApiManager;

public class ServiceLocator {
    private static ServiceLocator instance = null;

    private ApiManager apiManager = new ApiManager();
    private DataHolder dataHolder = new DataHolder();

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public ApiManager getApiManager() {
        return apiManager;
    }

    public DataHolder getDataHolder() {
        return dataHolder;
    }
}
