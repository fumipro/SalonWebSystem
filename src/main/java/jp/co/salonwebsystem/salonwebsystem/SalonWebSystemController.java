package jp.co.salonwebsystem.salonwebsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class SalonWebSystemController {

	// JDBC
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;


    // トップ画面
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// コンセプト画面
	@GetMapping("/concept")
	public String concept() {
		return "concept";
	}

	// アクセス画面
	@GetMapping("/access")
	public String secret() {
		return "access";
	}

	// 予約画面
	@GetMapping("/reservation")
    public String reservation(@ModelAttribute ReservationForm reservationForm) {
		return "reservation";
	}


	// 予約確認画面
    @PostMapping("/reservation_confirm")
	public String reservationConfirm(@ModelAttribute ReservationForm reservationForm){
		return "reservation_confirm";
	}

	// 予約完了画面
	@PostMapping("/reservation_finish")
    public String create(@ModelAttribute ReservationForm reservation) {

        String sql = "INSERT INTO reservation(yyear, mmonth, dday, hhour, mminutes, name, gender, tel, message) VALUES(:yyear, :mmonth, :dday, :hhour, :mminutes, :name, :gender, :tel, :message)";
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(reservation);
        npJdbcTemplate.update(sql, paramSource);
        return "reservation_finish";
    }

	// お問い合わせ画面
	@GetMapping("/contact")
	public String contact(@ModelAttribute ContactForm contactForm) {
		return "contact";
	}

	// お問い合わせ確認画面
	@PostMapping("/contact_confirm")
	public String contact_confirm(@ModelAttribute ContactForm contactForm) {
		return "contact_confirm";
	}

	//	お問い合わせ完了画面
	@PostMapping("/contact_finish")
    public String create(@ModelAttribute ContactForm contact) {

        String sql = "INSERT INTO contact(name, email, message) VALUES(:name, :email, :message)";
        
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(contact);
        npJdbcTemplate.update(sql, paramSource);
        return "contact_finish";
    }

	// 管理者ログイン画面
	@GetMapping("/admin/login")
	public String adminLogin() {
		return "login";
	}

	// 管理画面　トップ画面
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	// 管理画面　お問い合わせ内容確認画面
	@GetMapping("/admin/check_contact")
	public String checkContact(Model model) {
		// contactテーブルからデータを取得するSQL
		String sql = "SELECT id, name, email, message FROM contact";

		// データベースからデータを取得を行う準備
		RowMapper<ContactForm> rowMapper = new RowMapper<ContactForm>() {
			@Override
			public ContactForm mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContactForm contactForm = new ContactForm();
				contactForm.setId(rs.getInt("id"));
				contactForm.setName(rs.getString("name"));
				contactForm.setEmail(rs.getString("email"));
				contactForm.setMessage(rs.getString("message"));
				return contactForm;				
				
			}
		};
		
		// SQLを実行しcontactテーブルからデータを取得
		List<ContactForm> contactForm = jdbcTemplate.query(sql, rowMapper);

		// htmlに対してデータの表示ができるように設定
		model.addAttribute("contactForm", contactForm);
		
		return "check_contact";
	}

	// 管理画面　予約情報確認画面
	@GetMapping("/admin/check_reservation")
	public String checkReservation(Model model) {
		// reservationテーブルからデータを取得するSQL
		String sql = "SELECT id, yyear, mmonth, dday, hhour, mminutes, name, gender, tel, message FROM reservation";
		
		// データベースからデータの取得を行う準備
		RowMapper<ReservationForm> rowMapper = new RowMapper<ReservationForm>() {

			@Override
			public ReservationForm mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReservationForm reservationForm = new ReservationForm();
				reservationForm.setId(rs.getInt("id"));
				reservationForm.setYyear(rs.getString("yyear"));
				reservationForm.setMmonth(rs.getString("mmonth"));
				reservationForm.setDday(rs.getString("dday"));
				reservationForm.setHhour(rs.getString("hhour"));
				reservationForm.setMminutes(rs.getString("mminutes"));
				reservationForm.setName(rs.getString("name"));
				reservationForm.setGender(rs.getInt("gender"));
				reservationForm.setMessage(rs.getString("message"));			
				return reservationForm;				
				
			}
		};
		// SQLを実行しcontactテーブルからデータを取得
		List<ReservationForm> reservationForm = jdbcTemplate.query(sql, rowMapper);

		// htmlに対してデータの表示ができるように設定
		model.addAttribute("reservationForm", reservationForm);
			
		return "check_reservation";
	}
}
