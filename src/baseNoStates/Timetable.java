package baseNoStates;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;


/*
Classe Horari que s'assigna als grups d'usuaris per a controlar el seu accés a l'edifici.
Es crea una instància per cada grup d'usuaris existent.
Aquest horari conté data d'inici, de final, els dies que pot accedir, i les hores en que té
accés aquest grup.
*/
public class Timetable {
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final ArrayList<DayOfWeek> days;
  private final LocalTime startTime;
  private final LocalTime endTime;

  public Timetable(LocalDate startDate, LocalDate endDate, ArrayList<DayOfWeek> days,
                   LocalTime startTime, LocalTime endTime) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.days = days;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public ArrayList<DayOfWeek> getDays() {
    return days;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }
}
