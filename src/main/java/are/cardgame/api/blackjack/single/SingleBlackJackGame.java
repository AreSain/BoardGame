package are.cardgame.api.blackjack.single;

import java.math.BigDecimal;

import are.cardgame.api.common.Card;
import are.cardgame.api.common.CardDeck;
import are.cardgame.api.common.Hands;
import are.cardgame.data.entity.User;

public class SingleBlackJackGame {
	private SingleBlackJackDealer dealer;
	private SingleBlackJackPlayer player;
	private BigDecimal bet;
	private boolean isPlayerTurn;
	private boolean isDealerTurn;
	private CardDeck cardDeck;

	public SingleBlackJackGame(User user) {
		this.dealer = new SingleBlackJackDealer();
		this.player = new SingleBlackJackPlayer(user);
		this.isPlayerTurn = false;
		this.isDealerTurn = true;
	}

	public void startGame(User user, BigDecimal bet) {
		this.player = new SingleBlackJackPlayer(user);
		this.cardDeck = new CardDeck();
		this.cardDeck.shuffle(this.cardDeck.getCards());
		this.bet = bet;
		this.player.getHands().addCard(this.cardDeck.drawCard(2));
		this.dealer.getHands().addCard(this.cardDeck.drawCard(2));
		this.isPlayerTurn = true;
	}

	public BigDecimal playerHit() {
		if (isPlayerTurn) {
			this.player.getHands().addCard(this.cardDeck.drawCard(1));
		} else {
			// exception
		}

		if (calculateScore(this.player.getHands()) > 21) {
			this.player.busted();
			this.isPlayerTurn = false;
			return playerLose();
		}
		return this.bet;
	}

	public void playerStand() {
		if (isPlayerTurn) {
			if (checkBlackJack(this.player.getHands())) {
				this.player.blackJack();
			}
			this.isPlayerTurn = false;
			this.isDealerTurn = true;
		} else {
			// exception
		}
	}

	public BigDecimal dealerPlay() {
		if (checkBlackJack(this.dealer.getHands()))
			this.dealer.blackJack();
		if (isDealerTurn && calculateScore(this.dealer.getHands()) < 17) {
			this.dealer.getHands().addCard(this.cardDeck.drawCard(1));
		} else {
			// exception
		}

		if (calculateScore(this.dealer.getHands()) > 21) {
			this.dealer.busted();
			this.isDealerTurn = false;
			return playerWin();
		} else {
			return compareScore();
		}
	}

	public BigDecimal compareScore() {
		if (this.player.isBlackJack() && this.dealer.isBlackJack()) {
			return this.bet;
		} else if (this.player.isBlackJack()) {
			return playerWin();
		} else if (this.dealer.isBlackJack()) {
			return playerLose();
		} else if (calculateScore(this.player.getHands()) > calculateScore(this.dealer.getHands())) {
			return playerWin();
		} else if (calculateScore(this.player.getHands()) < calculateScore(this.dealer.getHands())) {
			return playerLose();
		} else {
			return this.bet;
		}
	}

	public BigDecimal playerWin() {
		if (checkBlackJack(this.player.getHands())) {
			return this.bet.add(this.bet.multiply(new BigDecimal("1.5")));
		} else {
			return this.bet.multiply(new BigDecimal("2"));
		}
	}

	public BigDecimal playerLose() {
		return new BigDecimal("0");
	}

	private	boolean checkBlackJack(Hands hands) {
		return calculateScore(hands) == 21 && hands.countCard() == 2;
	}

	private int calculateScore(Hands hands) {
		int score = 0, aceCount = 0;
		for (Card card : hands.getCards()) {
			int value = card.getValue();
			if (value == 1) {
				aceCount++;
				value = 11;
			}
			score += value;
			while (score > 21 && aceCount > 0) {
				score -= 10;
				aceCount--;
			}
		}
		return score;
	}
}
