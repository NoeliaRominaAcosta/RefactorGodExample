package Repository;

import java.util.List;

public interface DatabaseRepository {

    List<String> getUserData(String userId);
    void setUserData(String userId, List<String> data);

}
