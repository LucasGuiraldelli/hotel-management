package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exception.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {    // "throws" propaga o método quando não é necessário/ a função tratar, mas como estou usando o RuntimeExcpetion isso não se faz necessário
		if (!checkOut.after(checkIn)){      // tratando as exceções no construtor, isso se chama programação defensiva  (boa prática)
			throw new DomainException("check-out must be after check-in date.");   // IllegalArgumentException aparece no programa principal no teste de erro
			
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();             // pega os dias do checkIn e faz o cálculo de milesegundos para dias
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	public void UpdateDates(Date checkIn, Date checkOut) {	// "throws" permite que eu possa lançar uma exceção, quando não é necessário/a função, dessa classe, mas como estou usando o RuntimeExcpetion isso não se faz necessário
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for udpdate must be future dates.");  // usado para lançar o argumento no try catch 
		}
	
		this.checkIn = checkIn;
		this.checkOut = checkOut;	
	}
	
	@Override          // sobre-escreve o método herdado
	public String toString() {
		return "Room "
				+ roomNumber
				+", check-In "
				+ sdf.format(checkIn)
				+ ", check-Out"
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights ";
	}
}
