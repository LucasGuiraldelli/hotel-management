package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exception.DomainException;

public class Program 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {		
			System.out.print("Room Number: ");
			int number = sc.nextInt();
			System.out.print("CheckIn date (dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next());
			System.out.print("CheckOut date (dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());
			
			Reservation reservation = new Reservation(number, checkIn, checkOut);	
			System.out.println();
			
			System.out.println("Enter data to update the reservation: ");
			System.out.print("CheckIn date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("CheckOut date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			
			reservation.UpdateDates(checkIn, checkOut); // método (date) responsável por atualizar as datas.
			System.out.println("Reservation: " + reservation);
		}
		catch (ParseException e) {              // trata o erro do parse  (necessita do try catch)
			System.out.println("Invalid date format");
		}
		catch (DomainException e) {    // agora sim tratando o DomainException
			System.out.println("Erro in reservation " + e.getMessage());
		}
		catch (RuntimeException e) {   // trata um possível erro além dos previstos, uma exceção inesperada
			System.out.println("Unexpected error");
		}
	
	
		sc.close();
	}

}
