package jp.co.salonwebsystem;
import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class ReservationForm {
    /* 予約画面　フォームクラス */
	// ID
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 年
	private String yyear;

	// 月
	private String mmonth;

	// 日
	private String dday;

	// 時
	private String hhour;

	// 分
	private String mminutes;

	// 名前
	private String name;

	// 性別
	private Integer gender;

	// 電話番号
	private String tel;

	// メッセージ
	private String message;
}
