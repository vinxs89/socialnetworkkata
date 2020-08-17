package kata.exercise.socialnetwork.database;

import kata.exercise.socialnetwork.models.User;

public interface UserService {

    User getOrCreateUser(String id);

}
