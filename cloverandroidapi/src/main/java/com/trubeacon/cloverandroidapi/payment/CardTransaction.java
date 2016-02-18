package com.trubeacon.cloverandroidapi.payment;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CardTransaction {

	@JsonProperty
	private String authCode;
	@JsonProperty
	private EntryType entryType;
	@JsonProperty
	private Map<String, String> extra; // ???: is this for sure string values???
	@JsonProperty
	private State state;
	@JsonProperty
	private String referenceId;
	@JsonProperty
	private String transactionNo;
	@JsonProperty
	private Type type;
	@JsonProperty
	private String last4;
	@JsonProperty
	private CardType cardType;
	
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public EntryType getEntryType() {
		return entryType;
	}
	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}
	public Map<String, String> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getLast4() {
		return last4;
	}
	public void setLast4(String last4) {
		this.last4 = last4;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	public enum EntryType {
		
		SWIPED,
		KEYED,
		VOICE,
		VAULTED,
		OFFLINE_SWIPED,
		OFFLINE_KEYED,
		EMV_CONTACT,
		EMV_CONTACTLESS,
		MSD_CONTACTLESS,
		PINPAD_MANUAL_ENTRY;

		@JsonCreator
		public static EntryType fromString(String entryTypeString) {
			EntryType entryType = null;
			for (EntryType e : EntryType.values()) {
				if (e.toString().equalsIgnoreCase(entryTypeString)) {
					entryType = e;
					break;
				}
			}
			return entryType;
		}
		
	}

	public enum State {
		
		PENDING,
		CLOSED;
		
		@JsonCreator
		public static State fromString(String stateString) {
			State state = null;
			for (State s : State.values()) {
				if (s.toString().equalsIgnoreCase(stateString)) {
					state = s;
					break;
				}
			}
			return state;
		}
		
	}
	
	public enum Type {
		
		AUTH,
		PREAUTH,
		PREAUTHCAPTURE,
		ADJUST,
		VOID,
		VOIDRETURN,
		RETURN,
		REFUND,
		NAKEDREFUND,
		BATCHCLOSE,
		ACTIVATE,
		BALANCE_LOCK,
		LOAD,
		CASHOUT,
		CASHOUT_ACTIVE_STATUS,
		REDEMPTION,
		REDEMPTION_LOCK,
		RELOAD;
		
		@JsonCreator
		public static Type fromString(String typeString) {
			Type type = null;
			for (Type t : Type.values()) {
				if (t.toString().equalsIgnoreCase(typeString)) {
					type = t;
					break;
				}
			}
			return type;
		}
		
	}

	public enum CardType {
		
		VISA("Visa"),
		MC("MasterCard"),
		AMEX("American Express"),
		DISCOVER("Discover"),
		DINERS_CLUB("Diner's Club"),
		JCB("JCB"),
		MAESTRO("Maestro"),
		SOLO("Solo"),
		LASER("Laser"),
		CHINA_UNION_PAY("China Union Bay"),
		CARTE_BLANCHE("Carte Blanche"),
		UNKNOWN("Unknown"),
		GIFT_CARD("Gift");
		
		private String pretty;
		
		private CardType(String pretty) {
			this.pretty = pretty;
		}
		
		@JsonCreator
		public static CardType fromString(String cardTypeString) {
			CardType cardType = null;
			for (CardType c : CardType.values()) {
				if (c.toString().equalsIgnoreCase(cardTypeString) || c.pretty().equalsIgnoreCase(cardTypeString)) {
					cardType = c;
					break;
				}
			}
			return cardType;
		}
		
		public String pretty() {
			return pretty;
		}
		
	} 	

}
