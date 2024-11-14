package are.cardgame.api.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class CardDeck {
	private List<Card> cards;


	public CardDeck() {
		this.cards = createDeck();
	}

	private List<Card> createDeck() {
		List<Card> cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			for (int i = 1; i <= 13; i++) {
				cards.add(new Card(suit, i));
			}
		}
		return cards;
	}

	public void shuffle(List<Card> cards) {
		Collections.shuffle(cards);
	}

	public List<Card> drawCard(int num) {
		List<Card> drawedCards = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			drawedCards.add(cards.remove(0));
		}
		return drawedCards;
	}

	public void reset() {
		this.cards = createDeck();
	}
}
