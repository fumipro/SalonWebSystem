package jp.co.salonwebsystem.salonwebsystem;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class ContactForm {
    /* お問い合わせ画面 フォームクラス */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 名前
	private String name;

	// Email
	private String email;

	// お問い合わせ内容
	private String message;
}
