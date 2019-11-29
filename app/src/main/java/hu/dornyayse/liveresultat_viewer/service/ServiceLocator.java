package hu.dornyayse.liveresultat_viewer.service;

import android.content.Context;

import hu.dornyayse.liveresultat_viewer.database.LiveresultatDatabase;
import hu.dornyayse.liveresultat_viewer.network.ApiManager;

public class ServiceLocator {
    private static ServiceLocator instance = null;

    private ApiManager apiManager;
    private DataHolder dataHolder;
    private LiveresultatDatabase liveresultatDatabase;
    private Context applicationContext;

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

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }
}
