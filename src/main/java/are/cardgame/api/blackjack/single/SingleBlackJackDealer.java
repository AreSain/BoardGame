package are.cardgame.api.blackjack.single;

import are.cardgame.api.common.Hands;
import lombok.Getter;

@Getter
public class SingleBlackJackDealer {
	private Hands hands;
	private boolean isBlackJack;
	private boolean isBusted;

	public SingleBlackJackDealer() {
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
