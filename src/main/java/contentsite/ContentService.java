package contentsite;

import java.util.HashSet;
import java.util.Set;

public class ContentService {

    private Set<User> users = new HashSet<>();
    private Set<Content> contents = new HashSet<>();

    public Set<User> getAllUsers() {
        return users;
    }

    public Set<Content> getAllContent() {
        return contents;
    }

    public void registerUser(String userName, String password) {
        if (!users.stream().map(User::getUserName).toList().contains(userName)) {
            users.add(new User(userName, password));
        } else {
            throw new IllegalArgumentException("Username is already taken: " + userName);
        }
    }

    public void addContent(Content content) {
        if (contents.stream().filter(c -> content.getTitle().equals(c.getTitle())).toList().size() > 0) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }
        contents.add(content);
    }

    public void logIn(String username, String password) {
        if (users.stream().filter(user -> username.equals(user.getUserName())).toList().size() == 0) {
            throw new IllegalArgumentException("Username is wrong!");
        }
        User user = users.stream().filter(u -> username.equals(u.getUserName())).toList().get(0);
        if (user.getPassword() != (username + password).hashCode()) {
            throw new IllegalArgumentException("Password is Invalid!");
        }
        user.setLogIn(true);
    }

    public void clickOnContent(User user, Content content) {
        if (user.isLogIn()) {
            if (content.isPremiumContent() && !user.isPremiumMember()) {
                throw new IllegalStateException("Upgrade for Premium to watch this content!");
            }
            content.click(user);
        } else {
            throw new IllegalStateException("Log in to watch this content!");
        }
    }
}
