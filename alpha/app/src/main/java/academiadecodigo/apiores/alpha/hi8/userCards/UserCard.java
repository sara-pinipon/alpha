package academiadecodigo.apiores.alpha.hi8.userCards;

public class UserCard {

    private String userId;
    private String name;

    public UserCard(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
