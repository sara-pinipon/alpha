package academiadecodigo.apiores.alpha.hi8.userCards;

public class UserCard {

    private String userId;
    private String name;

    private int cardId;

    public UserCard(String userId, String name, int cardId) {
        this.userId = userId;
        this.name = name;
        this.cardId = cardId;
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


    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
