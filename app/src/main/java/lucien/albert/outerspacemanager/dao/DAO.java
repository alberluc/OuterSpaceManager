package lucien.albert.outerspacemanager.dao;

import io.realm.Realm;

public class DAO {

    Realm realm;

    DAO () {
        this.realm = Realm.getDefaultInstance();
    }

}
