package jp.co.salonwebsystem.salonwebsystem;

import lombok.Data;

@Data
public class ContactForm {
    /* お問い合わせ画面 フォームクラス */
    // id
	private Integer id;

	// 名前
	private String name;

	// Email
	private String email;

	// お問い合わせ内容
	private String message;
}
