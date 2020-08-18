package kata.exercise.socialnetwork.services;

import kata.exercise.socialnetwork.models.User;

public interface UserService {

    User getOrCreateUser(String id);

}
