package are.cardgame.data.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "nickname", length = 30)
	private String nickname;

	@Column(name = "email", length = 60)
	private String email;

	@Column(name = "password", length = 20)
	private String password;

	@Column(name = "money")
	private BigDecimal money;

	@Builder
	public User(String id, String nickname, String email, String password, BigDecimal money) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.money = money;
	}

	public void addMoney(BigDecimal money) {
		this.money = this.money.add(money);
	}


}
