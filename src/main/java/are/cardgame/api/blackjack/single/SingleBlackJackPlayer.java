package are.cardgame.api.blackjack.single;

import are.cardgame.api.common.Hands;
import are.cardgame.data.entity.User;
import lombok.Getter;

@Getter
public class SingleBlackJackPlayer {
	private User user;
	private Hands hands;
	private boolean isBlackJack;
	private boolean isBusted;

	public SingleBlackJackPlayer(User user) {
		this.user = user;
		this.hands = new Hands();
		this.isBlackJack = false;
		this.isBusted = false;
	}

	public void blackJack() {
		this.isBlackJack = true;
	}

	public void busted() {
		this.isBusted = true;
	}
}
