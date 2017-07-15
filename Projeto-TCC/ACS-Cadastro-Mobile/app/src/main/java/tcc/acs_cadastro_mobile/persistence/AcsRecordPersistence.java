package tcc.acs_cadastro_mobile.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.exceptions.RealmMigrationNeededException;

public class AcsRecordPersistence {

    public static final int DEFAULT_INT = 0;
    public static final String DEFAULT_STR = "-";
    private static final String DB_VERSION = "DB_VERSION";

    public static long version = 0;

    public static void startDatabase(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(DB_VERSION, Context.MODE_PRIVATE);
        delete(false, preferences);

        try {

            init(preferences, context);

        } catch (RealmMigrationNeededException exception) {

            migrate(preferences);
        }
    }

    private static void init(SharedPreferences preferences, Context context){
        version = preferences.getLong(DB_VERSION, 0);

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(version)
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private static void delete(boolean flag, SharedPreferences preferences){
        if(flag){
            Realm.deleteRealm(new RealmConfiguration.Builder().build());
            preferences.edit().remove(DB_VERSION).putLong(DB_VERSION, 0).apply();
        }
    }

    private static void migrate(SharedPreferences preferences){

        RealmConfiguration newConfig = new RealmConfiguration.Builder()
                .migration(migration())
                .schemaVersion(++version)
                .build();
        preferences.edit().putLong(DB_VERSION, version).apply();

        Realm.setDefaultConfiguration(newConfig);
    }

    private static RealmMigration migration() {

        return new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                Log.e("Before Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);
                switch ((int)oldVersion){
                    case 0: break;
                }
                oldVersion++;
                Log.e("After Migration", "version: " + version + " oldVersion: " + oldVersion + " newVersion: " + newVersion);
            }
        };
    }
}