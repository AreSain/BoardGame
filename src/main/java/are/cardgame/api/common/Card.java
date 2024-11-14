package are.cardgame.api.common;

import lombok.Getter;

@Getter
public class Card {
	private Suit suit;
	private int value;

	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
	}
}
