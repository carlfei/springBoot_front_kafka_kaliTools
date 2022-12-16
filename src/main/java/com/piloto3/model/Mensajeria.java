package com.piloto3.model;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
@EnableAsync
@Repository
public class Mensajeria {

	private String paquete;
	private String EndSend;
	private Object operator;
	private String UserId;
	private int numerIndex;
	private int stoper;
	private long timer;
	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	public int getStoper() {
		return stoper;
	}

	public void setStoper(int stoper) {
		this.stoper = stoper;
	}

	public int getNumerIndex() {
		return numerIndex;
	}

	public void setNumerIndex(int numerIndex) {
		this.numerIndex = numerIndex;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getEndSend() {
		return EndSend;
	}

	public Object getOperator() {
		return operator;
	}

	public void setOperator(Object operator) {
		this.operator = operator;
		operatorObject = operator;
		setOperatorObject(operatorObject);
	}

	public void setEndSend(String endSend) {
		EndSend = endSend;
		endSending = EndSend;
		setEndSending(endSending);
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
		// totalPaquete=totalPaquete+paquete;
		totalPaquete = paquete;
		setTotalPaquete(totalPaquete);
	}

	public String getTotalPaquete() {
		return totalPaquete;
	}

	public void setTotalPaquete(String totalPaquete) {
		this.totalPaquete = totalPaquete;
	}

	public String getEndSending() {
		return endSending;
	}

	public void setEndSending(String endSending) {
		this.endSending = endSending;
	}

	private String totalPaquete = "";
	private String endSending = "";
	private Object operatorObject = "";

	public Object getOperatorObject() {
		return operatorObject;
	}

	public void setOperatorObject(Object operatorObject) {
		this.operatorObject = operatorObject;
	}

}
