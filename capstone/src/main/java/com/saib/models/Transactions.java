package com.saib.models;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")

public class Transactions {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private long transaction_id;
	
	@Column(name="from_account")
	private String from_account;
	
	@Column(name="to_account")
	private String to_account;
	
	@Column(name="from_account_name")
	private String from_account_name;
	
	@Column(name="to_account_name")
	private String to_account_name;
	
	@Column(name="same_bank_transaction")
	private boolean same_bank_transaction;
	
	@Column(name="other_bank")
	private String other_bank;
	
	@Column(name="amount")
	private double amount;
	
	
	@Column(name="date")
	private LocalDateTime date;

	@Column(name="time")
	private LocalDateTime time;
	
	@Column(name="transaction_type")
	private String transaction_type;
	
	@Column(name="status")
	private String status;

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(long transaction_id, String from_account, String to_account, String from_account_name,
			String to_account_name, boolean same_bank_transaction, String other_bank, double amount, LocalDateTime date,
			LocalDateTime time, String transaction_type, String status) {
		super();
		this.transaction_id = transaction_id;
		this.from_account = from_account;
		this.to_account = to_account;
		this.from_account_name = from_account_name;
		this.to_account_name = to_account_name;
		this.same_bank_transaction = same_bank_transaction;
		this.other_bank = other_bank;
		this.amount = amount;
		this.date = date;
		this.time = time;
		this.transaction_type = transaction_type;
		this.status = status;
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getFrom_account() {
		return from_account;
	}

	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}

	public String getTo_account() {
		return to_account;
	}

	public void setTo_account(String to_account) {
		this.to_account = to_account;
	}

	public String getFrom_account_name() {
		return from_account_name;
	}

	public void setFrom_account_name(String from_account_name) {
		this.from_account_name = from_account_name;
	}

	public String getTo_account_name() {
		return to_account_name;
	}

	public void setTo_account_name(String to_account_name) {
		this.to_account_name = to_account_name;
	}

	public boolean isSame_bank_transaction() {
		return same_bank_transaction;
	}

	public void setSame_bank_transaction(boolean same_bank_transaction) {
		this.same_bank_transaction = same_bank_transaction;
	}

	public String getOther_bank() {
		return other_bank;
	}

	public void setOther_bank(String other_bank) {
		this.other_bank = other_bank;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", from_account=" + from_account + ", to_account="
				+ to_account + ", from_account_name=" + from_account_name + ", to_account_name=" + to_account_name
				+ ", same_bank_transaction=" + same_bank_transaction + ", other_bank=" + other_bank + ", amount="
				+ amount + ", date=" + date + ", time=" + time + ", transaction_type=" + transaction_type + ", status="
				+ status + "]";
	}
	


}
