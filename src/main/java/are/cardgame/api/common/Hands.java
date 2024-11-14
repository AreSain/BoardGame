package are.cardgame.api.common;

import java.util.List;

import lombok.Getter;

@Getter
public class Hands {
	private List<Card> cards;

	public void addCard(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public int countCard() {
		return this.cards.size();
	}
}
