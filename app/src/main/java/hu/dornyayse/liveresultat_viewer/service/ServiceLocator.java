package hu.dornyayse.liveresultat_viewer.service;

import hu.dornyayse.liveresultat_viewer.database.LiveresultatDatabase;
import hu.dornyayse.liveresultat_viewer.network.ApiManager;

public class ServiceLocator {
    private static ServiceLocator instance = null;

    private ApiManager apiManager;
    private DataHolder dataHolder;
    private LiveresultatDatabase liveresultatDatabase;

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

    public LiveresultatDatabase getLiveresultatDatabase() {
        return liveresultatDatabase;
    }
}
